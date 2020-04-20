package com.theprogrammingturkey.ld46.rendering;

import com.badlogic.gdx.graphics.Color;

public class GameColors
{
	public static final Color VALID_AREA = new Color(0, .75f, 0, .25f);
	public static final Color VALID_AREA_NO_ALPHA = new Color(0, .75f, 0, 1f);
	public static final Color INVALID_AREA = new Color(.75f, 0, 0, .25f);
	public static final Color INVALID_AREA_NO_ALPHA = new Color(.75f, 0, 0, 1f);

	public static final Color TEXT_ALPHA_BG = new Color(0.1f, 0.1f, 0.1f, .5f);
	public static final Color HIGHLIGHT_ALPHA_BG = new Color(0.6f, 0.6f, 0.6f, .5f);

	public static final Color HEAT = new Color(0.93f, 0.37f, 0.11f, .25f);
	public static final Color HEAT_NO_ALPHA = new Color(0.93f, 0.37f, 0.11f, 1f);

	public static final Color GROUND = Color.GREEN.cpy();
	public static final Color SKY = Color.BLUE.cpy();

	public static final Color PLANT_GREEN = new Color(0.32f, 0.62f, 0.18f, 1f);
	public static final Color DIRT_BROWN = new Color(0.4f, 0.22f, 0.07f, 1f);
}