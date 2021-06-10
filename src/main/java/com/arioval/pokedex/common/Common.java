package com.arioval.pokedex.common;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;


public class Common {
	
	public static final String URL = "https://pokeapi.co/api/v2/";

	public static String makeUrl(int page) {
		String urlLimited = URL + "pokemon/?offset=" + (10*page) +  "&limit=10";
		return urlLimited;
	}

	public static String getImageUrl(JSONObject objPoke) {
		JSONObject images = objPoke.getJSONObject("sprites");
		String img = images.getString("front_default");
		return img;
	}

	public static List<String> getAbilities(JSONObject objPoke) {
		List<String> abilities = new ArrayList<String>();
		JSONArray listAbilities = objPoke.getJSONArray("abilities");
		for(int i = 0; i < listAbilities.length(); i++) {
			JSONObject obj = listAbilities.getJSONObject(i);
			JSONObject jsonAbilite = obj.getJSONObject("ability");
			abilities.add(jsonAbilite.getString("name"));
			
		}
		
		return abilities;
	}

	public static List<String> getTypes(JSONObject objPoke) {
		List<String> types = new ArrayList<String>();
		JSONArray listTypes = objPoke.getJSONArray("types");
		for(int i = 0; i < listTypes.length(); i++) {
			JSONObject obj = listTypes.getJSONObject(i);
			JSONObject jsonType = obj.getJSONObject("type");
			types.add(jsonType.getString("name"));
			
		}
		return types;
	}

	public static String getDescription(JSONObject objDescription) {
		String description;
		JSONArray listDescription = objDescription.getJSONArray("descriptions");
		JSONObject obj = listDescription.getJSONObject(2);
		description = obj.getString("description");
		
		return description;
	}

	public static List<String> getEvolutions(JSONObject objEvolution) {
		JSONArray evol = objEvolution.getJSONObject("chain").getJSONArray("evolves_to");
		List<String> evolutions = new ArrayList<>();
		for(int x = 0; x < evol.length(); x++) {
			String first = evol.getJSONObject(x).getJSONObject("species").getString("name");
			String second = null;
			JSONArray secondEvol = evol.getJSONObject(x).getJSONArray("evolves_to");
			for(int i = 0; i < secondEvol.length(); i++) {
				if(i == 0) {
					second = evol.getJSONObject(i).getJSONObject("species").getString("name");
				}
			}
			if(first != null) {
				evolutions.add(first);
			}
			if(second != null) {
				evolutions.add(second);
			}
			
		}
		
		return evolutions;
	}

	
}
