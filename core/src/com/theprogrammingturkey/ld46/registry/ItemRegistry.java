package com.theprogrammingturkey.ld46.registry;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.theprogrammingturkey.ld46.item.Item;
import com.theprogrammingturkey.ld46.item.ItemHeatLamp;
import com.theprogrammingturkey.ld46.item.ItemSapling;
import com.theprogrammingturkey.ld46.item.ItemStack;
import com.theprogrammingturkey.ld46.item.WateringCan;
import com.theprogrammingturkey.ld46.rendering.Textures;

import java.util.HashMap;
import java.util.Map;

public class ItemRegistry
{
	private static Map<String, Item> items = new HashMap<>();

	public static void registerItems()
	{
		loadItem(new ItemSapling("bonsai"));
		loadItem(new ItemSapling("cactus"));
		loadItem(new ItemSapling("cherry_blossom"));
		loadItem(new ItemSapling("elm"));
		loadItem(new ItemSapling("yucca"));
		loadItem(new ItemSapling("jade"));
		loadItem(new ItemSapling("oak"));
		loadItem(new ItemSapling("peace_lily"));
		loadItem(new ItemSapling("pine"));
		loadItem(new ItemSapling("rotala"));
		loadItem(new Item("stick"));
		loadItem(new Item("log"));
		loadItem(new WateringCan("watering_can"));
		loadItem(new ItemHeatLamp("heat_lamp"));
	}

	public static Item getItem(String id)
	{
		return items.getOrDefault(id, null);
	}

	public static ItemStack getItemStack(String id)
	{
		return getItemStack(id, 1);
	}

	public static ItemStack getItemStack(String id, int count)
	{
		Item i = getItem(id);
		if(i == null)
			return ItemStack.EMPTY;
		return new ItemStack(i, count);
	}

	public static void loadItem(Item item)
	{
		FileHandle file = Gdx.files.internal("items/" + item.getId() + ".json");
		JsonElement json = JsonParser.parseString(file.readString());
		if(!json.isJsonObject())
			return;

		JsonObject itemJson = json.getAsJsonObject();

		if(!itemJson.has("id") || !itemJson.get("id").getAsString().equals(item.getId()))
			return;

		if(itemJson.has("display_name"))
			item.setDisplayName(itemJson.get("display_name").getAsString());

		if(itemJson.has("texture"))
		{
			JsonObject textureJson = itemJson.getAsJsonObject("texture");
			int width = textureJson.get("width").getAsInt();
			int height = textureJson.get("height").getAsInt();
			int x = textureJson.get("x").getAsInt() * width;
			int y = textureJson.get("y").getAsInt() * height;

			item.setTextureRegion(new TextureRegion(Textures.atlas, x, y, width, height));
		}

		items.put(item.getId(), item);
	}
}
