package com.arioval.pokedex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arioval.pokedex.models.PokemonModel;
import com.arioval.pokedex.service.PokemonServiceImp;


@RestController
@CrossOrigin
@RequestMapping("/api")
public class PokeController {
	
	@Autowired
	private PokemonServiceImp pokemonService;
	
	/**
     * This method takes a GET request to pass it to the correct service layer in order
     * to get the suggested item list to pokemons
     *
     * @param page the request, which is composed of the list of pokemons.
     * @return ResponseEntity<Object> with list of pokemons and link next and previous.
     *
     */
	
	@GetMapping(path="/pokemons/{page}", produces = "application/json")
	public ResponseEntity<Object> getPokedex(@PathVariable int page) {
		return pokemonService.getPokedex(page);
	}
	
	/**
     * This method takes a GET request to pass it to the correct service layer in order
     * to get the suggested item pokemon with evolutions.
     *
     * @param pokemon id, which is composed of the list of pokemons.
     * @return PokemonModel
     *
     */
	
	@GetMapping("/pokemon/{id}")
	public PokemonModel getPokemonAdv(@PathVariable String id) {
		return pokemonService.getPokemonAdv(id);
	}
	

}
