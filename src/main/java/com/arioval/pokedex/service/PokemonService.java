package com.arioval.pokedex.service;

import org.springframework.http.ResponseEntity;

import com.arioval.pokedex.models.PokemonModel;

/**
 * Interface that exposes the methods for the items.
 *
 * @author jhon.arias
 *
 */

public interface PokemonService {
	
    PokemonModel getPokemonByName(String name);
    ResponseEntity<Object> getPokedex(int page);
    PokemonModel getPokemonAdv(String id);
}
