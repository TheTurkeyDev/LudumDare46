package com.theprogrammingturkey.ld46.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Align;
import com.theprogrammingturkey.ld46.LD46;
import com.theprogrammingturkey.ld46.rendering.GameColors;
import com.theprogrammingturkey.ld46.rendering.Renderer;
import com.theprogrammingturkey.ld46.rendering.Textures;
import com.theprogrammingturkey.ld46.util.MathHelper;
import com.theprogrammingturkey.ld46.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

public class HelpScreen implements Screen
{
	private Stage stage;

	private List<Rectangle> buttons = new ArrayList<>();

	private TextureRegion market = new TextureRegion(Textures.atlas, 29 * 64, 0, 64, 64);
	private TextureRegion heatLamp = new TextureRegion(Textures.atlas, 15 * 64, 128, 64, 64);

	@Override
	public void show()
	{
		int x = Gdx.graphics.getWidth() / 2;

		buttons.add(new Rectangle(15, 15, 200, 50));

		stage = new Stage()
		{
			@Override
			public boolean touchDown(int screenX, int screenY, int pointer, int button)
			{
				int flipY = Gdx.graphics.getHeight() - screenY;
				for(int i = 0; i < buttons.size(); i++)
				{
					if(buttons.get(i).contains(screenX, flipY))
					{
						if(i == 0)
						{
							LD46.INSTANCE.setScreen(new MainMenuScreen());
						}
					}
				}
				return true;
			}
		};
		Gdx.input.setInputProcessor(stage);
	}

	@Override
	public void render(float delta)
	{
		stage.act(delta);

		Renderer.drawRect(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), GameColors.DIRT_BROWN, true);
		Renderer.drawStringAligned(Renderer.font, Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() - 50, "HOW TO PLAY", 2.5f, Align.center, GameColors.PLANT_GREEN);
		Renderer.drawStringAligned(Renderer.font, 115, 40, "BACK", 1.5f, Align.center, GameColors.PLANT_GREEN);

		Renderer.drawStringAligned(Renderer.rust, 150, Gdx.graphics.getHeight() - 125, "CONTROLS", 0.5f, Align.center, GameColors.PLANT_GREEN);
		int indent = 15;
		Renderer.drawStringAligned(Renderer.rust, indent, Gdx.graphics.getHeight() - 160, "- WASD to move", 0.25f, Align.left, GameColors.PLANT_GREEN);
		Renderer.drawStringAligned(Renderer.rust, indent, Gdx.graphics.getHeight() - 180, "- Hold left shift to run", 0.25f, Align.left, GameColors.PLANT_GREEN);
		Renderer.drawStringAligned(Renderer.rust, indent, Gdx.graphics.getHeight() - 200, "- Space to plant", 0.25f, Align.left, GameColors.PLANT_GREEN);
		Renderer.drawStringAligned(Renderer.rust, indent, Gdx.graphics.getHeight() - 220, "- Right click plants and objects to interact", 0.25f, Align.left, GameColors.PLANT_GREEN);
		Renderer.drawStringAligned(Renderer.rust, indent, Gdx.graphics.getHeight() - 240, "- Left click plants and objects to view info", 0.25f, Align.left, GameColors.PLANT_GREEN);
		Renderer.drawStringAligned(Renderer.rust, indent, Gdx.graphics.getHeight() - 260, "- E to open/ close inventrory", 0.25f, Align.left, GameColors.PLANT_GREEN);
		Renderer.drawStringAligned(Renderer.rust, indent, Gdx.graphics.getHeight() - 280, "- Escape to close overlay windows", 0.25f, Align.left, GameColors.PLANT_GREEN);
		Renderer.drawStringAligned(Renderer.rust, indent, Gdx.graphics.getHeight() - 300, "- Scroll wheel to change selected hotbar item", 0.25f, Align.left, GameColors.PLANT_GREEN);

		Renderer.drawStringAligned(Renderer.rust, 150, Gdx.graphics.getHeight() - 350, "GAME PLAY", 0.5f, Align.center, GameColors.PLANT_GREEN);
		Renderer.drawStringAligned(Renderer.rust, indent, Gdx.graphics.getHeight() - 380, "The goal of this game is to build up your plants in your emporium!", 0.25f, Align.left, GameColors.PLANT_GREEN);
		Renderer.drawStringAligned(Renderer.rust, indent, Gdx.graphics.getHeight() - 400, "To do so, trim, or chop down trees to gather saplings and other", 0.25f, Align.left, GameColors.PLANT_GREEN);
		Renderer.drawStringAligned(Renderer.rust, indent, Gdx.graphics.getHeight() - 420, "resources to sell in the market. Note, gathering currently does not", 0.25f, Align.left, GameColors.PLANT_GREEN);
		Renderer.drawStringAligned(Renderer.rust, indent, Gdx.graphics.getHeight() - 440, "do anything. The money you earn can then be used to buy new plants!", 0.25f, Align.left, GameColors.PLANT_GREEN);
		Renderer.drawStringAligned(Renderer.rust, indent, Gdx.graphics.getHeight() - 460, "Watch out though!  Plants have health and water and temperature", 0.25f, Align.left, GameColors.PLANT_GREEN);
		Renderer.drawStringAligned(Renderer.rust, indent, Gdx.graphics.getHeight() - 480, "attributes! Don't let these go outside of their happy range or else", 0.25f, Align.left, GameColors.PLANT_GREEN);
		Renderer.drawStringAligned(Renderer.rust, indent, Gdx.graphics.getHeight() - 500, "they may die! ", 0.25f, Align.left, GameColors.PLANT_GREEN);

		Renderer.draw(market, indent, Gdx.graphics.getHeight() - 625, 64, 64);
		Renderer.drawStringAligned(Renderer.rust, 100, Gdx.graphics.getHeight() - 600, "<- The Market", 0.25f, Align.left, GameColors.PLANT_GREEN);

		indent = (Gdx.graphics.getWidth() / 2) + 50;
		Renderer.drawStringAligned(Renderer.rust, indent + 300, Gdx.graphics.getHeight() - 125, "PLANT ATTRIBUTES", 0.5f, Align.center, GameColors.PLANT_GREEN);

		float lowerbound = 0;
		float upperbound = 100;
		float currentValue = 65.4f;
		float minValue = 35f;
		float maxValue = 92f;
		int barWidth = 256;
		int x = indent + 150;
		int y = Gdx.graphics.getHeight() - 155;
		Renderer.draw(Textures.thermometer, x - 15, y - 32, 32, 32);
		Renderer.drawGradientRect(x + 25, y - 32, barWidth, 32, 0, Color.BLUE, Color.RED, true);
		float tempX = MathHelper.convertValRange(currentValue, lowerbound, upperbound, 0, 1) * barWidth;
		float lowerX = MathHelper.convertValRange(minValue, lowerbound, upperbound, 0, 1) * barWidth;
		float upperX = MathHelper.convertValRange(maxValue, lowerbound, upperbound, 0, 1) * barWidth;
		Renderer.drawRect(x + 25 + lowerX, y - 36, 3, 40, 0, Color.GREEN, true);
		Renderer.drawRect(x + 25 + upperX, y - 36, 3, 40, 0, Color.GREEN, true);
		Renderer.drawRect(x + 25 + tempX, y - 36, 3, 40, 0, Color.WHITE, true);

		Renderer.drawStringAligned(Renderer.rust, x + 25, y + 10, String.valueOf(lowerbound), 0.175f, Align.center, Color.BLACK);
		Renderer.drawStringAligned(Renderer.rust, x + barWidth + 25, y + 10, String.valueOf(upperbound), 0.175f, Align.center, Color.BLACK);
		Renderer.drawStringAligned(Renderer.rust, x + lowerX + 25, y + 10, String.valueOf(minValue), 0.175f, Align.center, Color.BLACK);
		Renderer.drawStringAligned(Renderer.rust, x + upperX + 25, y + 10, String.valueOf(maxValue), 0.175f, Align.center, Color.BLACK);
		Renderer.drawStringAligned(Renderer.rust, x + 50 + tempX, y - 15, StringUtil.formatDecimal(currentValue), 0.175f, Align.center, Color.WHITE);

		Renderer.drawStringAligned(Renderer.rust, indent, Gdx.graphics.getHeight() - 210, "Plant attributes will show like the bar above. The", 0.25f, Align.left, GameColors.PLANT_GREEN);
		Renderer.drawStringAligned(Renderer.rust, indent, Gdx.graphics.getHeight() - 230, "bar shows each plants happy range between the two", 0.25f, Align.left, GameColors.PLANT_GREEN);
		Renderer.drawStringAligned(Renderer.rust, indent, Gdx.graphics.getHeight() - 250, "green bars. The text above the bars is their numerical", 0.25f, Align.left, GameColors.PLANT_GREEN);
		Renderer.drawStringAligned(Renderer.rust, indent, Gdx.graphics.getHeight() - 270, "value. Keep the plants attribute value, the white bar,", 0.25f, Align.left, GameColors.PLANT_GREEN);
		Renderer.drawStringAligned(Renderer.rust, indent, Gdx.graphics.getHeight() - 290, "between these two green bars to keep it happy and", 0.25f, Align.left, GameColors.PLANT_GREEN);
		Renderer.drawStringAligned(Renderer.rust, indent, Gdx.graphics.getHeight() - 310, "growing! If the value goes outside this range the", 0.25f, Align.left, GameColors.PLANT_GREEN);
		Renderer.drawStringAligned(Renderer.rust, indent, Gdx.graphics.getHeight() - 330, "will start to die!", 0.25f, Align.left, GameColors.PLANT_GREEN);

		Renderer.draw(heatLamp, indent + 100, Gdx.graphics.getHeight() - 405, 64, 64);
		Renderer.drawStringAligned(Renderer.rust, indent + 180, Gdx.graphics.getHeight() - 360, "The heat lamp will increase", 0.25f, Align.left, GameColors.PLANT_GREEN);
		Renderer.drawStringAligned(Renderer.rust, indent + 180, Gdx.graphics.getHeight() - 380, "the temperature of nearby", 0.25f, Align.left, GameColors.PLANT_GREEN);
		Renderer.drawStringAligned(Renderer.rust, indent + 180, Gdx.graphics.getHeight() - 400, "plants by 15 degrees!", 0.25f, Align.left, GameColors.PLANT_GREEN);

		Renderer.draw(Textures.backgroundtile, indent + 120, Gdx.graphics.getHeight() - 450, 32, 32);
		Renderer.draw(Textures.sand, indent + 100, Gdx.graphics.getHeight() - 480, 32, 32);
		Renderer.draw(Textures.water, indent + 140, Gdx.graphics.getHeight() - 480, 32, 32);
		Renderer.drawStringAligned(Renderer.rust, indent + 180, Gdx.graphics.getHeight() - 430, "Each tile provides different amounts", 0.25f, Align.left, GameColors.PLANT_GREEN);
		Renderer.drawStringAligned(Renderer.rust, indent + 180, Gdx.graphics.getHeight() - 450, "of water to the plants. Most plants", 0.25f, Align.left, GameColors.PLANT_GREEN);
		Renderer.drawStringAligned(Renderer.rust, indent + 180, Gdx.graphics.getHeight() - 470, "can only live on 1 type of tile!", 0.25f, Align.left, GameColors.PLANT_GREEN);

		indent = 300;
		Renderer.drawStringAligned(Renderer.rust, indent + 450, Gdx.graphics.getHeight() - 530, "DOING THINGS", 0.5f, Align.center, GameColors.PLANT_GREEN);
		Renderer.drawStringAligned(Renderer.rust, indent, Gdx.graphics.getHeight() - 560, "To interact with game objects, first hover your mouse over the object, this will show a", 0.25f, Align.left, GameColors.PLANT_GREEN);
		Renderer.drawStringAligned(Renderer.rust, indent, Gdx.graphics.getHeight() - 580, "a circle around the object. Your players feet must be inside this circle in order to", 0.25f, Align.left, GameColors.PLANT_GREEN);
		Renderer.drawStringAligned(Renderer.rust, indent, Gdx.graphics.getHeight() - 600, "interact with the object! Once you are, right clicking will pop open an actions radial", 0.25f, Align.left, GameColors.PLANT_GREEN);
		Renderer.drawStringAligned(Renderer.rust, indent, Gdx.graphics.getHeight() - 620, "menu (if applicable), and left clicking will open a ui.", 0.25f, Align.left, GameColors.PLANT_GREEN);
		Renderer.drawStringAligned(Renderer.rust, indent, Gdx.graphics.getHeight() - 640, "To plant or place objects down, simply make sure you have selected it in your hotbar via", 0.25f, Align.left, GameColors.PLANT_GREEN);
		Renderer.drawStringAligned(Renderer.rust, indent, Gdx.graphics.getHeight() - 660, "the scroll wheel. Next hit space and a circle should appear around your player. Next", 0.25f, Align.left, GameColors.PLANT_GREEN);
		Renderer.drawStringAligned(Renderer.rust, indent, Gdx.graphics.getHeight() - 680, "click inside this circle to place at that location. Hitting space again will cancel.", 0.25f, Align.left, GameColors.PLANT_GREEN);

		Renderer.end();
		stage.draw();
		Renderer.begin();
	}

	@Override
	public void resize(int width, int height)
	{

	}

	@Override
	public void pause()
	{

	}

	@Override
	public void resume()
	{

	}

	@Override
	public void hide()
	{

	}

	@Override
	public void dispose()
	{

	}
}
