package com.arioval.pokedex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arioval.pokedex.models.PokemonModel;
import com.arioval.pokedex.service.PokemonServiceImp;


@RestController
@RequestMapping("/api")
public class PokeController {
	
	@Autowired
	private PokemonServiceImp pokemonService;
	
	@GetMapping(path="/pokemons/{page}", produces = "application/json")
	public ResponseEntity<Object> getPokedex(@PathVariable int page) {
		return pokemonService.getPokedex(page);
	}
	
	@GetMapping("/pokemon/{id}")
	public PokemonModel getPokemonAdv(@PathVariable String id) {
		return pokemonService.getPokemonAdv(id);
	}
	

}
