package com.theprogrammingturkey.ld46.rendering;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Textures
{
	public static Texture atlas;
	public static Texture hotbarTex;


	public static TextureRegion backgroundtile;
	public static TextureRegion sun;
	public static TextureRegion sunOutline;
	public static TextureRegion moon;
	public static TextureRegion waterDroplet;
	public static TextureRegion waterDropletOutline;
	public static TextureRegion thermometer;
	public static TextureRegion arrow;

	public static TextureRegion hotbar;
	public static TextureRegion hotbarExtension;

	public static void init()
	{
		atlas = new Texture("textures/spritesheet.png");
		hotbarTex = new Texture("gui/inventory.png");

		backgroundtile = new TextureRegion(atlas, 0, 0, 64, 64);
		sun = new TextureRegion(atlas, 20 * 64, 0, 64, 64);
		sunOutline = new TextureRegion(atlas, 23 * 64, 0, 64, 64);
		moon = new TextureRegion(atlas, 22 * 64, 0, 64, 64);
		waterDroplet = new TextureRegion(atlas, 21 * 64, 0, 64, 64);
		waterDropletOutline = new TextureRegion(atlas, 24 * 64, 0, 64, 64);
		thermometer = new TextureRegion(atlas, 28 * 64, 0, 64, 64);
		arrow = new TextureRegion(atlas, 19 * 64, 0, 64, 64);

		hotbar = new TextureRegion(hotbarTex, 0, 0, 380, 79);
		hotbarExtension = new TextureRegion(hotbarTex, 0, 80, 380, 333);
	}

	public static void dispose()
	{
		atlas.dispose();
		hotbarTex.dispose();
	}
}
