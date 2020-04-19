package com.theprogrammingturkey.ld46.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Align;
import com.theprogrammingturkey.ld46.LD46;
import com.theprogrammingturkey.ld46.entity.Plant;
import com.theprogrammingturkey.ld46.entity.PlantType;
import com.theprogrammingturkey.ld46.entity.Player;
import com.theprogrammingturkey.ld46.entity.state.PlayerState;
import com.theprogrammingturkey.ld46.registry.PlantFactory;
import com.theprogrammingturkey.ld46.rendering.Renderer;
import com.theprogrammingturkey.ld46.rendering.Textures;
import com.theprogrammingturkey.ld46.screen.GameScreen;
import com.theprogrammingturkey.ld46.screen.overlay.ActionsOverlay;
import com.theprogrammingturkey.ld46.screen.overlay.PlantStatusOverlay;
import com.theprogrammingturkey.ld46.util.Direction;
import com.theprogrammingturkey.ld46.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

public class GameCore
{
	private Player thePlayer;

	private List<Plant> plantList = new ArrayList<>();

	private GameScreen screen;

	private Time gameTime;
	private Weather weather;
	private float light = 0f;
	private float sunAngle = 0f;

	private Color groundColor = Color.GREEN.cpy();
	private Color skyColor = Color.BLUE.cpy();

	public GameCore(GameScreen screen)
	{
		gameTime = new Time();
		weather = new Weather();
		this.screen = screen;
		thePlayer = new Player(this, new Vector2(200, 200));

		PlantFactory.loadPlants();

		PlantFactory factory = new PlantFactory(PlantType.TREE, "oak");
		Plant plant = factory.create(this, new Vector2(400, 400));
		plant.getLifePointsAttribute().setMaxValue(100);
		plant.getLifePointsAttribute().setCurrentValue(100);
		plant.setSize(150f);
		plantList.add(plant);
	}

	public void update()
	{
		gameTime.update();
		weather.update(gameTime);

		int hours = gameTime.getHour();
		int minutes = gameTime.getMinute();
		sunAngle = (((hours + (minutes / 60f)) / 24f) * (float) Math.PI * 2f) - ((float) Math.PI / 2);
		light = MathUtils.clamp((float) Math.sin(sunAngle) + .65f, 0f, 1f);

		thePlayer.update();

		for(Plant plant : plantList)
		{
			plant.update();
			//TODO: Deal with overlaps/ shadows
		}
	}

	public void render(float delta)
	{
		for(int x = 0; x < Gdx.graphics.getWidth(); x += 64)
		{
			for(int y = 0; y < Gdx.graphics.getHeight(); y += 64)
			{
				Renderer.draw(Textures.backgroundtile, x, y, 64, 64);
			}
		}

		int hours = gameTime.getHour();
		int minutes = gameTime.getMinute();
		StringBuilder time = new StringBuilder();
		if(hours == 0)
			time.append("12");
		else if(hours < 10)
			time.append("0").append(hours);
		else if(hours > 12)
			time.append(hours - 12);
		else
			time.append(hours);
		time.append(":");
		if(minutes < 10)
			time.append("0").append(minutes);
		else
			time.append(minutes);

		int cx = Gdx.graphics.getWidth() - 40;
		int cy = Gdx.graphics.getHeight() - 40;

		float sunX = ((float) Math.cos(-sunAngle + Math.PI) * 30) + cx;
		float sunY = ((float) Math.sin(-sunAngle + Math.PI) * 30) + cy;
		float moonX = ((float) Math.cos(-sunAngle) * 30) + cx;
		float moonY = ((float) Math.sin(-sunAngle) * 30) + cy;

		groundColor.fromHsv(120, 1, MathUtils.clamp(light, 0.15f, 0.6f));
		skyColor.fromHsv(190, .75f, MathUtils.clamp(light, 0.15f, 0.95f));

		Renderer.drawRect(cx - 40, cy, 80, 40, skyColor, true);

		Renderer.draw(Textures.sun, sunX - 10, sunY - 10, 20, 20);
		Renderer.draw(Textures.moon, moonX - 10, moonY - 10, 20, 20);

		Renderer.drawRect(cx - 40, cy - 40, 80, 40, groundColor, true);

		Renderer.drawStringAligned(Renderer.rust, cx, cy - 50, time.toString(), .25f, Align.center, Color.WHITE);
		Renderer.drawStringAligned(Renderer.rust, cx, cy - 70, "Day: " + gameTime.getDay(), .2f, Align.center, Color.WHITE);
		Renderer.drawStringAligned(Renderer.rust, cx, cy - 90, "Temp: " + StringUtil.formatDecimal(weather.getTemp()), .2f, Align.center, Color.WHITE);

		for(Plant plant : plantList)
		{
			plant.render(delta);
		}

		thePlayer.render(delta);
	}

	public Weather getWeather()
	{
		return weather;
	}

	public float getCurrentLight()
	{
		return light;
	}

	public boolean keyDown(int keycode)
	{
		if(keycode == Input.Keys.SPACE)
		{
			if(thePlayer.getState() == PlayerState.NONE)
				thePlayer.setState(PlayerState.PLACING);
		}
		else if(keycode == Input.Keys.E)
		{
			if(screen.getCurrentOverlay() != null)
				screen.getCurrentOverlay().close();
		}

		if(keycode == Input.Keys.W)
		{
			thePlayer.setMoveState(Direction.UP, true);
		}
		else if(keycode == Input.Keys.A)
		{
			thePlayer.setMoveState(Direction.LEFT, true);
		}
		else if(keycode == Input.Keys.S)
		{
			thePlayer.setMoveState(Direction.DOWN, true);
		}
		else if(keycode == Input.Keys.D)
		{
			thePlayer.setMoveState(Direction.RIGHT, true);
		}
		return true;
	}

	public boolean keyUp(int keycode)
	{
		if(keycode == Input.Keys.W)
		{
			thePlayer.setMoveState(Direction.UP, false);
		}
		else if(keycode == Input.Keys.A)
		{
			thePlayer.setMoveState(Direction.LEFT, false);
		}
		else if(keycode == Input.Keys.S)
		{
			thePlayer.setMoveState(Direction.DOWN, false);
		}
		else if(keycode == Input.Keys.D)
		{
			thePlayer.setMoveState(Direction.RIGHT, false);
		}
		return true;
	}

	public boolean touchDown(int screenX, int screenY, int pointer, int button)
	{
		int yFlip = Gdx.graphics.getHeight() - screenY;

		if(thePlayer.getState() == PlayerState.PLACING && button == 0)
		{
			if(thePlayer.isVAlidPlacementLoc(screenX, yFlip))
			{
				PlantFactory factory = new PlantFactory(PlantType.TREE, "oak");
				Plant p = factory.create(this, new Vector2(screenX, yFlip));
				plantList.add(p);
				thePlayer.setState(PlayerState.NONE);
				return true;
			}
			else
			{
				LD46.SNACK_BAR.createSnackMessage("PLACEMENT OUT OF RADIUS", Color.RED);
			}
		}

		for(Plant plant : plantList)
		{
			Rectangle rect = plant.getBoundingBox();
			if(rect.contains(screenX, yFlip))
			{
				if(button == 0)
				{
					screen.setCurrentOverlay(new PlantStatusOverlay(plant, screen, null));
					return true;
				}
				else if(button == 1)
				{
					screen.setCurrentOverlay(new ActionsOverlay(plant, screen, null));
					return true;
				}
			}
		}
		return false;
	}
}
