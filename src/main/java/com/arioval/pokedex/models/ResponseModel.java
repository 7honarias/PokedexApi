package com.arioval.pokedex.models;

import java.util.List;

/**
 * model represents an item, which is composed of the pokemon list, next,
 *                              previous link.
 *
 * @author jhon.arias
 *
 */

public class ResponseModel {
	String next;
	String previous;
	List<PokemonModel> results;

	public ResponseModel() {
		
	}
	
	public String getNext() {
		return next;
	}
	public void setNext(String next) {
		this.next = next;
	}
	public String getPrevious() {
		return previous;
	}
	public void setPrevious(String previous) {
		this.previous = previous;
	}
	public List<PokemonModel> getResults() {
		return results;
	}
	public void setResults(List<PokemonModel> results) {
		this.results = results;
	}
}
