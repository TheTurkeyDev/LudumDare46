package com.theprogrammingturkey.ld46.rendering;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Textures
{
	public static TextureRegion backgroundtile;
	public static TextureRegion sun;
	public static TextureRegion sunOutline;
	public static TextureRegion moon;
	public static TextureRegion waterDroplet;
	public static TextureRegion waterDropletOutline;
	public static TextureRegion thermometer;

	public static void init()
	{
		backgroundtile = new TextureRegion(Renderer.atlas, 0, 0, 64, 64);
		sun = new TextureRegion(Renderer.atlas, 20 * 64, 0, 64, 64);
		sunOutline = new TextureRegion(Renderer.atlas, 23 * 64, 0, 64, 64);
		moon = new TextureRegion(Renderer.atlas, 22 * 64, 0, 64, 64);
		waterDroplet = new TextureRegion(Renderer.atlas, 21 * 64, 0, 64, 64);
		waterDropletOutline = new TextureRegion(Renderer.atlas, 24 * 64, 0, 64, 64);
		thermometer = new TextureRegion(Renderer.atlas, 28 * 64, 0, 64, 64);
	}
}
