package com.theprogrammingturkey.ld46.registry;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.theprogrammingturkey.ld46.item.Item;
import com.theprogrammingturkey.ld46.rendering.Renderer;
import com.theprogrammingturkey.ld46.rendering.Textures;

import java.util.HashMap;
import java.util.Map;

public class ItemRegistry
{
	public static final Item EMPTY = new Item("empty");
	private static Map<String, Item> items = new HashMap<>();

	public static void registerItems()
	{
		loadItem(new Item("bonsai"));
		loadItem(new Item("cactus"));
		loadItem(new Item("cherry_blossom"));
		loadItem(new Item("elm"));
		loadItem(new Item("iucca"));
		loadItem(new Item("jade"));
		loadItem(new Item("oak"));
		loadItem(new Item("peace_lily"));
		loadItem(new Item("pine"));
		loadItem(new Item("rotala"));
	}

	public static Item getItem(String id)
	{
		return items.getOrDefault(id, EMPTY);
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
