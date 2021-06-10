package com.arioval.pokedex.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
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
	public Map<String, PokemonModel> getPokemons(int page) {
		String url = Common.makeUrl(page);
		JSONObject jsonObject = this.makeRequest(url);
		Map<String, PokemonModel> listPokemon = this.getListPokemon(jsonObject);
		System.out.println(jsonObject);
		return listPokemon;
	}

	private Map<String, PokemonModel> getListPokemon(JSONObject jsonObject) {
		Map<String, PokemonModel> pokemons = new HashMap<>();
		JSONArray ArrayPokemon = jsonObject.getJSONArray("results");
		for(int x = 0; x < ArrayPokemon.length(); x++) {
			JSONObject obj = ArrayPokemon.getJSONObject(x);
			String name = obj.getString("name");
			PokemonModel poke = this.getPokemonByName(name);
			pokemons.put(name, poke);
		}
		return pokemons;
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
			List<String> evolutions = Common.getEvolutions(jsonObject);
			PokemonModel pokemon = this.getPokemonByName(name);
			List<PokemonModel> evolList = new ArrayList<>();
			for(int x = 0; x < evolutions.size(); x++) {
				PokemonModel pokemonEvo = this.getPokemonByName(name);
				evolList.add(pokemonEvo);
			}
			pokemon.setEvolutions(evolList);
			listPokemon.put(name, pokemon);
		}
		return listPokemon;
	}

}
