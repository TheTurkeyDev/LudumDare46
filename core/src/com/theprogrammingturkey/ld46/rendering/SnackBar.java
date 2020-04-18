package com.theprogrammingturkey.ld46.rendering;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;

import java.util.ArrayList;
import java.util.List;

public class SnackBar
{
	public static final int DISPLAY_HEIGHT = 60;

	private List<SnackText> messages = new ArrayList<>();

	public void render()
	{
		for(int i = messages.size() - 1; i >= 0; i--)
		{
			SnackText text = messages.get(i);
			Renderer.drawStringCenteredWithBG(Renderer.font, text.x, text.y, text.message, text.scale, text.color);
			text.tick++;
			if(text.tick < text.inDuration)
			{
				text.y = DISPLAY_HEIGHT * ((float) text.tick / text.inDuration);
			}
			else if(text.tick > text.viewDuration + text.inDuration + text.outDuration)
			{
				messages.remove(i);
			}
			else if(text.tick > text.viewDuration + text.inDuration)
			{
				text.y = DISPLAY_HEIGHT - (DISPLAY_HEIGHT * ((float) (text.tick - (text.viewDuration + text.inDuration)) / text.outDuration));
			}
		}
	}

	public void createSnackMessage(String message, Color color)
	{
		SnackText snackText = new SnackText(message, color);
		messages.add(snackText);
	}

	public static class SnackText
	{
		private int inDuration = 15;
		private int outDuration = 15;
		private int viewDuration = 180;

		public String message;
		public int tick;
		public float scale;
		public Color color;
		public float x;
		public float y;

		public SnackText(String message, Color color)
		{
			this.message = message;
			this.tick = 0;
			this.scale = 1f;
			this.color = color;
			this.x = Gdx.graphics.getWidth() / 2f;
			this.y = 0;
		}
	}
}
