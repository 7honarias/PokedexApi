package com.arioval.pokedex.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.arioval.pokedex.common.Common;
import com.arioval.pokedex.models.PokemonModel;

import reactor.core.publisher.Mono;


@Service
public class PokemonServiceImp implements PokemonService{

	@Autowired
	private WebClient webClient;
	
	public PokemonServiceImp() {
		
	}
	public JSONObject makeRequest(String url) {		
		Mono<String> responseFromApi = webClient.get()
				.uri(url)
				.retrieve()
				.bodyToMono(String.class);
		JSONObject jsonObject = new JSONObject(responseFromApi.block());
		return jsonObject;
	}

	

	@Override
	public PokemonModel getPokemonByName(String name) {
		PokemonModel pokemon = new PokemonModel();
		String url = Common.URL + "pokemon/" + name;
		JSONObject objPoke = this.makeRequest(url);
		int id = objPoke.getInt("id");
		String imageUrl = Common.getImageUrl(objPoke);
		List<String> abilities = Common.getAbilities(objPoke);
		List<String> types = Common.getTypes(objPoke);
		
		pokemon.setId(id);
		pokemon.setName(objPoke.getString("name"));
		pokemon.setWeight(objPoke.getInt("weight"));
		pokemon.setImage(imageUrl);
		pokemon.setAbilities(abilities);
		pokemon.setType(types);
		return pokemon;
	}
	@Override
	public Map<String, PokemonModel> getPokedex(int page) {
		String url = "https://pokeapi.co/api/v2/evolution-chain/";
		Map<String, PokemonModel> listPokemon = new HashMap<>();
		int index = page * 10 + 1;

		for(int i = index; i < index + 10; i++) {
			JSONObject jsonObject = this.makeRequest(url+i);
			String name = jsonObject.getJSONObject("chain").getJSONObject("species").getString("name");
			PokemonModel pokemon = this.getPokemonByName(name);
			listPokemon.put(name, pokemon);
		}
		return listPokemon;
	}
	
	@Override
	public PokemonModel getPokemonAdv(String id) {
		String url = Common.URL + "pokemon-species/";
		PokemonModel pokemon = this.getPokemonByName(id);
		JSONObject jsonSpecies = this.makeRequest(url+id);
		String urlEvolutions = jsonSpecies.getJSONObject("evolution_chain").getString("url");
		JSONObject jsonObject = this.makeRequest(urlEvolutions);
		List<String> evolutions = Common.getEvolutions(jsonObject);
		List<PokemonModel> evolList = new ArrayList<>();
		
		for(int x = 0; x < evolutions.size(); x++) {
			String name = evolutions.get(x);
			PokemonModel pokemonEvo = this.getPokemonByName(name);
			evolList.add(pokemonEvo);
		}
		pokemon.setEvolutions(evolList);
		
		return pokemon;
	}

}
