package com.theprogrammingturkey.ld46.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Align;
import com.theprogrammingturkey.ld46.LD46;
import com.theprogrammingturkey.ld46.entity.Plant;
import com.theprogrammingturkey.ld46.entity.PlantType;
import com.theprogrammingturkey.ld46.registry.PlantFactory;
import com.theprogrammingturkey.ld46.rendering.GameColors;
import com.theprogrammingturkey.ld46.rendering.Renderer;

import java.util.ArrayList;
import java.util.List;

public class MainMenuScreen implements Screen
{
	private Stage stage;

	private List<Plant> plants = new ArrayList<>();
	private List<Rectangle> buttons = new ArrayList<>();

	@Override
	public void show()
	{
		int x = Gdx.graphics.getWidth() / 2;

		buttons.add(new Rectangle(x - 300, Gdx.graphics.getHeight() - 275, 600, 50));
		buttons.add(new Rectangle(x - 300, Gdx.graphics.getHeight() - 375, 600, 50));
		buttons.add(new Rectangle(x - 300, Gdx.graphics.getHeight() - 475, 600, 50));

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
							LD46.INSTANCE.setScreen(new GameScreen());
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
		Renderer.drawStringAligned(Renderer.font, Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() - 50, "TURKEY'S PLANT EMPORIUM", 2.5f, Align.center, GameColors.PLANT_GREEN);

		Renderer.drawStringAligned(Renderer.font, Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() - 250, "START", 1.5f, Align.center, GameColors.PLANT_GREEN);
		Renderer.drawStringAligned(Renderer.font, Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() - 350, "HELP", 1.5f, Align.center, GameColors.PLANT_GREEN);
		Renderer.drawStringAligned(Renderer.font, Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() - 450, "CREDITS", 1.5f, Align.center, GameColors.PLANT_GREEN);

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
