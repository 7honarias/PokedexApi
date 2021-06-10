package com.arioval.pokedex.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	@GetMapping("/pokemons/{page}")
	public Map<String, PokemonModel> getPokedex(@PathVariable int page) {
		return pokemonService.getPokedex(page);
	}
	@GetMapping("/pokemon/{id}")
	public PokemonModel getPokemonByName(@PathVariable String id) {
		return pokemonService.getPokemonByName(id);
	}
	

}
