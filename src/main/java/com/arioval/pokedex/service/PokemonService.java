package com.arioval.pokedex.service;

import java.util.Map;

import com.arioval.pokedex.models.PokemonModel;


public interface PokemonService {
	
    PokemonModel getPokemonByName(String name);
    Map<String, PokemonModel> getPokedex(int page);
    PokemonModel getPokemonAdv(String id);
}
