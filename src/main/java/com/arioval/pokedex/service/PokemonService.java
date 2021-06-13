package com.arioval.pokedex.service;

import org.springframework.http.ResponseEntity;

import com.arioval.pokedex.models.PokemonModel;


public interface PokemonService {
	
    PokemonModel getPokemonByName(String name);
    ResponseEntity<Object> getPokedex(int page);
    PokemonModel getPokemonAdv(String id);
}
