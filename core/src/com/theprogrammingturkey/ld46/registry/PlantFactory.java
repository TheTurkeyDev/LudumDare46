package com.theprogrammingturkey.ld46.registry;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.theprogrammingturkey.ld46.entity.Plant;
import com.theprogrammingturkey.ld46.entity.PlantType;
import com.theprogrammingturkey.ld46.entity.attributes.LifePointsAttribute;
import com.theprogrammingturkey.ld46.entity.attributes.LightAttribute;
import com.theprogrammingturkey.ld46.entity.attributes.NutrientAttribute;
import com.theprogrammingturkey.ld46.entity.attributes.TempratureAttribute;
import com.theprogrammingturkey.ld46.entity.attributes.WaterAttribute;
import com.theprogrammingturkey.ld46.entity.tree.BonsaiTree;
import com.theprogrammingturkey.ld46.entity.tree.Cactus;
import com.theprogrammingturkey.ld46.entity.tree.CherryBlossomTree;
import com.theprogrammingturkey.ld46.entity.tree.DeadTree;
import com.theprogrammingturkey.ld46.entity.tree.OakTree;
import com.theprogrammingturkey.ld46.game.World;
import com.theprogrammingturkey.ld46.rendering.WrapperTR;

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

	public Plant create(World world, Vector2 loc, int maxHealth)
	{
		try
		{
			String id = type.getName() + "/" + plantID;
			JsonObject json = plantsToJson.get(id);
			Plant p = plantsToClasses.get(id).getDeclaredConstructor(World.class, Vector2.class).newInstance(world, loc.cpy());

			p.setDisplayName(json.get("display").getAsString());
			if(json.has("base_growth_speed"))
				p.setGrowthRate(json.get("base_growth_speed").getAsFloat());
			if(json.has("base_growth_pow"))
				p.setGrowthRatePow(json.get("base_growth_pow").getAsFloat());
			if(json.has("size_scalar"))
				p.setSizeScalar(json.get("size_scalar").getAsFloat());

			p.setLifePointsAttribute(new LifePointsAttribute(maxHealth));

			for(JsonElement element : json.getAsJsonArray("attributes"))
			{
				JsonObject attribute = element.getAsJsonObject();
				float base = 0;
				if(attribute.has("base"))
					base = attribute.get("base").getAsFloat();
				switch(attribute.get("name").getAsString())
				{
					case "water":
						float decay = attribute.get("decay").getAsFloat();
						WaterAttribute waterAttribute = new WaterAttribute(base, 0, 100, decay);
						p.setWaterAttribute(waterAttribute);
						break;
					case "light":
						LightAttribute lightAttribute = new LightAttribute(base, 0, 100);
						p.setLightAttribute(lightAttribute);
						break;
					case "nutrient":
						NutrientAttribute nutrientAttribute = new NutrientAttribute(base, 0, 100);
						p.addNutrientAttributes(nutrientAttribute);
						break;
					case "temperature":
						float min = attribute.get("min").getAsFloat();
						float max = attribute.get("max").getAsFloat();
						TempratureAttribute tempratureAttribute = new TempratureAttribute(base, min, max);
						p.setTempratureAttribute(tempratureAttribute);
						break;
				}
			}

			for(JsonElement element : json.getAsJsonArray("textures"))
			{
				JsonObject texture = element.getAsJsonObject();
				int width = texture.get("width").getAsInt();
				int height = texture.get("height").getAsInt();
				int x = texture.get("x").getAsInt() * width;
				int y = texture.get("y").getAsInt() * height;
				WrapperTR wrapperTR = new WrapperTR(x, y, width, height);

				if(texture.has("base_tint"))
					wrapperTR.setTint(Color.valueOf(texture.get("base_tint").getAsString()));

				p.addTexture(wrapperTR);
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
		plantsToClasses.put("tree/dead", DeadTree.class);
		plantsToClasses.put("tree/bonsai", BonsaiTree.class);
		plantsToClasses.put("tree/cactus", Cactus.class);
		plantsToClasses.put("tree/cherry_blossom", CherryBlossomTree.class);


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
