package com.theprogrammingturkey.ld46.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.theprogrammingturkey.ld46.entity.Entity;
import com.theprogrammingturkey.ld46.entity.Market;
import com.theprogrammingturkey.ld46.entity.Plant;
import com.theprogrammingturkey.ld46.entity.PlantType;
import com.theprogrammingturkey.ld46.registry.PlantFactory;
import com.theprogrammingturkey.ld46.rendering.GameColors;
import com.theprogrammingturkey.ld46.rendering.Renderer;
import com.theprogrammingturkey.ld46.rendering.Textures;
import com.theprogrammingturkey.ld46.sounds.SoundManager;

import java.util.ArrayList;
import java.util.List;

public class World
{
	private GameCore game;

	private List<Plant> plantList = new ArrayList<>();
	private List<Entity> entityList = new ArrayList<>();

	private Time gameTime;
	private Weather weather;
	private float light = 0f;
	private float sunAngle = 0f;

	public World(GameCore gameCore)
	{
		this.game = gameCore;

		gameTime = new Time();
		weather = new Weather();
	}

	public void initWorld()
	{
		PlantFactory factory = new PlantFactory(PlantType.TREE, "oak");
		Plant plant = factory.create(this, new Vector2(400, 400), 100);
		plantList.add(plant);

		entityList.add(new Market(this, new Vector2(50, 50)));

		SoundManager.startBGSound();
	}

	public void update()
	{
		if(GameCore.rand.nextInt(1500) == 54)
			SoundManager.playRandomBird();

		gameTime.update();
		weather.update(gameTime);

		int hours = gameTime.getHour();
		int minutes = gameTime.getMinute();
		sunAngle = (((hours + (minutes / 60f)) / 24f) * (float) Math.PI * 2f) - ((float) Math.PI / 2);
		light = MathUtils.clamp((float) Math.sin(sunAngle) + .65f, 0f, 1f);

		for(int i = plantList.size() - 1; i >= 0; i--)
		{
			Plant p = plantList.get(i);
			p.update();
			if(p.isDead())
				plantList.remove(i);
			//TODO: Deal with overlaps/ shadows
		}

		for(Entity ent : entityList)
			ent.update();
	}

	public void render(float delta, boolean focused)
	{
		for(int x = 0; x < Gdx.graphics.getWidth(); x += 64)
		{
			for(int y = 0; y < Gdx.graphics.getHeight(); y += 64)
			{
				Renderer.draw(Textures.backgroundtile, x, y, 64, 64);
			}
		}

		for(Plant plant : plantList)
		{
			if(focused && plant.getBoundingBox().contains(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY()))
			{
				Renderer.drawCircle(plant.getLocation().x, plant.getLocation().y, GameValues.PLANT_INTERACT_RADIUS, GameColors.VALID_AREA, true);
				Renderer.drawCircle(plant.getLocation().x, plant.getLocation().y, GameValues.PLANT_INTERACT_RADIUS, GameColors.VALID_AREA_NO_ALPHA, false);
			}
			plant.render(delta);
		}

		for(Entity ent : entityList)
		{
			if(focused && ent.getBoundingBox().contains(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY()))
			{
				Vector2 loc = ent.getLocation();
				Vector2 size = ent.getSize();
				Renderer.drawCircle(loc.x + (size.x / 2), loc.y + (size.y / 2), GameValues.PLANT_INTERACT_RADIUS, GameColors.VALID_AREA, true);
				Renderer.drawCircle(loc.x + (size.x / 2), loc.y + (size.y / 2), GameValues.PLANT_INTERACT_RADIUS, GameColors.VALID_AREA_NO_ALPHA, false);
			}

			ent.render(delta);
		}
	}

	public void spawnPlant(Plant p)
	{
		plantList.add(p);
	}

	public float getSunAngle()
	{
		return this.sunAngle;
	}

	public Weather getWeather()
	{
		return weather;
	}

	public Time getGameTime()
	{
		return gameTime;
	}

	public float getCurrentLight()
	{
		return light;
	}

	public List<Plant> getPlantList()
	{
		return plantList;
	}

	public List<Entity> getEntityList()
	{
		return entityList;
	}
}
