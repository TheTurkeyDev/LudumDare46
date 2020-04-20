package com.theprogrammingturkey.ld46.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Align;
import com.theprogrammingturkey.ld46.LD46;
import com.theprogrammingturkey.ld46.entity.Plant;
import com.theprogrammingturkey.ld46.entity.PlantType;
import com.theprogrammingturkey.ld46.game.GameSettings;
import com.theprogrammingturkey.ld46.registry.PlantFactory;
import com.theprogrammingturkey.ld46.rendering.GameColors;
import com.theprogrammingturkey.ld46.rendering.Renderer;
import com.theprogrammingturkey.ld46.sounds.SoundManager;
import org.w3c.dom.css.Rect;

import java.util.ArrayList;
import java.util.List;

public class SettingsScreen implements Screen
{
	private Stage stage;

	private List<Plant> plants = new ArrayList<>();
	private List<Rectangle> buttons = new ArrayList<>();

	public Screen fromScreen;

	public SettingsScreen(Screen from)
	{
		this.fromScreen = from;
	}

	@Override
	public void show()
	{
		int x = Gdx.graphics.getWidth() / 2;

		buttons.add(new Rectangle(15, 15, 200, 50));
		buttons.add(new Rectangle(x - 300, Gdx.graphics.getHeight() - 575, 600, 50));
		buttons.add(new Rectangle(x - 300, Gdx.graphics.getHeight() - 675, 600, 50));

		buttons.add(new Rectangle(x - 150, Gdx.graphics.getHeight() - 275, 50, 50));
		buttons.add(new Rectangle(x - 150, Gdx.graphics.getHeight() - 350, 400, 50));
		buttons.add(new Rectangle(x - 150, Gdx.graphics.getHeight() - 425, 400, 50));

		stage = new Stage()
		{
			@Override
			public boolean touchDown(int screenX, int screenY, int pointer, int button)
			{
				int flipY = Gdx.graphics.getHeight() - screenY;
				for(int i = 0; i < buttons.size(); i++)
				{
					Rectangle rect = buttons.get(i);
					if(rect.contains(screenX, flipY))
					{
						if(i == 0)
						{
							LD46.INSTANCE.setScreen(fromScreen);
						}
						else if(i == 1)
						{
							LD46.INSTANCE.setScreen(new MainMenuScreen());
						}
						else if(i == 2)
						{
							System.exit(0);
						}
						else if(i == 3)
						{
							GameSettings.sounds = !GameSettings.sounds;
						}
						else if(i == 4)
						{
							GameSettings.soundLevel = (screenX - rect.x) / rect.width;
						}
						else if(i == 5)
						{
							GameSettings.backgroundVolume = (screenX - rect.x) / rect.width;
						}
					}
				}
				return true;
			}
		};
		Gdx.input.setInputProcessor(stage);

		PlantFactory plantFactory = new PlantFactory(PlantType.TREE, "oak");
		plants.add(plantFactory.create(null, new Vector2(x - 400, 100), 50));
		plantFactory = new PlantFactory(PlantType.TREE, "elm");
		plants.add(plantFactory.create(null, new Vector2(x + 400, 100), 50));
		plantFactory = new PlantFactory(PlantType.TREE, "bonsai");
		plants.add(plantFactory.create(null, new Vector2(x - 400, 300), 500));
		plantFactory = new PlantFactory(PlantType.TREE, "cactus");
		plants.add(plantFactory.create(null, new Vector2(x + 400, 300), 150));
		plantFactory = new PlantFactory(PlantType.TREE, "cherry_blossom");
		plants.add(plantFactory.create(null, new Vector2(x - 400, 500), 50));
		plantFactory = new PlantFactory(PlantType.TREE, "rotala");
		plants.add(plantFactory.create(null, new Vector2(x + 400, 500), 250));
	}

	@Override
	public void render(float delta)
	{
		stage.act(delta);

		Renderer.drawRect(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), GameColors.DIRT_BROWN, true);
		Renderer.drawStringAligned(Renderer.font, Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() - 50, "SETTINGS", 2.5f, Align.center, GameColors.PLANT_GREEN);
		Renderer.drawStringAligned(Renderer.font, 115, 40, "BACK", 1.5f, Align.center, GameColors.PLANT_GREEN);

		int index = (Gdx.graphics.getWidth() / 2) - 200;
		Renderer.drawStringAligned(Renderer.rust, index, Gdx.graphics.getHeight() - 250, "Mute", 0.5f, Align.right, GameColors.PLANT_GREEN);
		Renderer.drawRect(index + 50, Gdx.graphics.getHeight() - 275, 50, 50, Color.BLACK, true);
		if(GameSettings.sounds)
			Renderer.drawRect(index + 65, Gdx.graphics.getHeight() - 260, 20, 20, Color.WHITE, true);

		Renderer.drawStringAligned(Renderer.rust, index, Gdx.graphics.getHeight() - 325, "FX Vol", 0.5f, Align.right, GameColors.PLANT_GREEN);
		Renderer.drawRect(index + 50, Gdx.graphics.getHeight() - 350, 400, 50, Color.BLACK, true);
		Renderer.drawRect((index + 50) + (GameSettings.soundLevel * 400), Gdx.graphics.getHeight() - 350, 10, 50, Color.WHITE, true);

		Renderer.drawStringAligned(Renderer.rust, index, Gdx.graphics.getHeight() - 400, "BG Vol", 0.5f, Align.right, GameColors.PLANT_GREEN);
		Renderer.drawRect(index + 50, Gdx.graphics.getHeight() - 425, 400, 50, Color.BLACK, true);
		Renderer.drawRect((index + 50) + (GameSettings.backgroundVolume * 400), Gdx.graphics.getHeight() - 425, 10, 50, Color.WHITE, true);


		Renderer.drawStringAligned(Renderer.font, Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() - 550, "MAIN MENU", 1.5f, Align.center, GameColors.PLANT_GREEN);
		Renderer.drawStringAligned(Renderer.font, Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() - 650, "QUIT", 1.5f, Align.center, GameColors.PLANT_GREEN);


		for(Plant plant : plants)
			plant.render(delta);

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
