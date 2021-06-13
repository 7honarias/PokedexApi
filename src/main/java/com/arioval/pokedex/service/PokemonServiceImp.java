package com.arioval.pokedex.service;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.arioval.pokedex.common.Common;
import com.arioval.pokedex.models.PokemonModel;
import com.arioval.pokedex.models.ResponseModel;

import reactor.core.publisher.Mono;

/**
 * Service implementation to show list of pokemons and pokemon details.
 *
 * @author jhon.arias
 *
 */

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
	
	 /**
     * This method get from the pokeApi the pokemon data without evolutions .
     * .
     * @param name can be a pokemon name or pokemon id
     * 
     * @return Pokemon object with all data.
     *                               
     */
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

	/**
     * This method get from the pokeApi list of pokemons.
     * .
     * @param page number of current page
     * 
     * @return Pokemon object with all data.
     *                               
     */
	@Override
	public ResponseEntity<Object> getPokedex(int page) {
		String url = Common.URL + "evolution-chain/";
		ResponseModel response = new ResponseModel();
		response.setNext(Common.getNextUrl(page));
		response.setPrevious(Common.getPreviousUrl(page));
		int index = page * 10 + 1;
		List<PokemonModel> pokemons = new ArrayList<>();

		for(int i = index; i < index + 10; i++) {
			JSONObject jsonObject = this.makeRequest(url+i);
			String name = jsonObject.getJSONObject("chain").getJSONObject("species").getString("name");
			PokemonModel pokemon = this.getPokemonByName(name);
			pokemons.add(pokemon);
		}
		response.setResults(pokemons);
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}
	
	/**
     * This method get data of evolutions of a pokemon.
     * 
     * @param id can be a pokemon name or pokemon id
     * 
     * @return Pokemon object with all data and evolutions.
     *                               
     */
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
