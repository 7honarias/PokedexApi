package com.arioval.pokedex.models;

import java.util.List;

public class PokemonModel {
	
	private int id;
	private int weight;
	private String image;
	private String name;
	private List<String> type;
	private List<String> abilities;
	private List<PokemonModel> evolutions;
	
	public PokemonModel() {
		
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public List<String> getAbilities() {
		return abilities;
	}
	public void setAbilities(List<String> abilities) {
		this.abilities = abilities;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<String> getType() {
		return type;
	}
	public void setType(List<String> type) {
		this.type = type;
	}
	public List<PokemonModel> getEvolutions() {
		return evolutions;
	}
	public void setEvolutions(List<PokemonModel> evolutions) {
		this.evolutions = evolutions;
	}

}
