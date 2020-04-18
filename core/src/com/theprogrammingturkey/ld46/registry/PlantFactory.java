package com.theprogrammingturkey.ld46.registry;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.Vector2;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.theprogrammingturkey.ld46.entity.OakTree;
import com.theprogrammingturkey.ld46.entity.Plant;
import com.theprogrammingturkey.ld46.entity.PlantType;
import com.theprogrammingturkey.ld46.entity.attributes.LifePointsAttribute;
import com.theprogrammingturkey.ld46.entity.attributes.LightAttribute;
import com.theprogrammingturkey.ld46.entity.attributes.NutrientAttribute;
import com.theprogrammingturkey.ld46.entity.attributes.TempratureAttribute;
import com.theprogrammingturkey.ld46.entity.attributes.WaterAttribute;

import java.util.HashMap;
import java.util.Map;

public class PlantFactory
{
	private static Map<String, Class<? extends Plant>> plantsToClasses = new HashMap<>();
	private static Map<String, JsonObject> plantsToJson = new HashMap<>();

	private PlantType type;
	private String plantID;

	public PlantFactory(PlantType plant, String id)
	{
		this.type = plant;
		this.plantID = id;
	}

	public Plant create(Vector2 loc)
	{
		try
		{

			String id = type.getName() + "/" + plantID;
			JsonObject json = plantsToJson.get(id);
			Plant p = plantsToClasses.get(id).getDeclaredConstructor(Vector2.class).newInstance(loc.cpy());
			p.setDisplayName(json.get("display").getAsString());
			if(json.has("base_growth_speed"))
				p.setGrowthRate(json.get("base_growth_speed").getAsFloat());

			for(JsonElement element : json.getAsJsonArray("attributes"))
			{
				JsonObject attribute = element.getAsJsonObject();
				float base = 0;
				if(attribute.has("base"))
					base = attribute.get("base").getAsFloat();
				switch(attribute.get("name").getAsString())
				{
					case "lp":
						LifePointsAttribute lpAttrib = new LifePointsAttribute(base, base);
						p.addAttribute(lpAttrib);
						break;
					case "water":
						float decay = attribute.get("decay").getAsFloat();
						WaterAttribute waterAttribute = new WaterAttribute(base, 0, 100, decay);
						p.addAttribute(waterAttribute);
						break;
					case "light":
						LightAttribute lightAttribute = new LightAttribute(base, 0, 100);
						p.addAttribute(lightAttribute);
						break;
					case "nutrient":
						NutrientAttribute nutrientAttribute = new NutrientAttribute(base, 0, 100);
						p.addAttribute(nutrientAttribute);
						break;
					case "temperature":
						TempratureAttribute tempratureAttribute = new TempratureAttribute(base, 0, 100);
						p.addAttribute(tempratureAttribute);
						break;
				}
			}

			return p;
		} catch(Exception e)
		{
			e.printStackTrace();
		}

		return null;
	}


	public static void loadPlants()
	{
		plantsToClasses.put("tree/oak", OakTree.class);


		FileHandle f = Gdx.files.internal("plants/");
		String[] files = f.readString().split("\n");
		for(String fileName : files)
		{
			FileHandle file = Gdx.files.internal("plants/" + fileName);
			JsonElement json = JsonParser.parseString(file.readString());
			if(!json.isJsonObject())
				continue;
			JsonObject plantJson = json.getAsJsonObject();
			String id = plantJson.get("id").getAsString();
			String type = plantJson.get("type").getAsString();
			plantsToJson.put(type + "/" + id, plantJson);
		}
	}
}
