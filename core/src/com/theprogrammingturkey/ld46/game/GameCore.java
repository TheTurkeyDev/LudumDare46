package com.theprogrammingturkey.ld46.game;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.theprogrammingturkey.ld46.entity.Plant;
import com.theprogrammingturkey.ld46.entity.PlantType;
import com.theprogrammingturkey.ld46.entity.Player;
import com.theprogrammingturkey.ld46.registry.PlantFactory;
import com.theprogrammingturkey.ld46.screen.GameScreen;
import com.theprogrammingturkey.ld46.screen.overlay.PlantStatusOverlay;
import com.theprogrammingturkey.ld46.util.Direction;

import java.util.ArrayList;
import java.util.List;

public class GameCore
{
	private Player thePlayer;

	private List<Plant> plantList = new ArrayList<>();

	private GameScreen screen;

	public GameCore(GameScreen screen)
	{
		this.screen = screen;
		thePlayer = new Player(new Vector2(200, 200));

		PlantFactory.loadPlants();
	}

	public void update()
	{
		thePlayer.update();

		for(Plant plant : plantList)
		{
			plant.update();
		}
	}

	public void render(float delta)
	{
		for(Plant plant : plantList)
		{
			plant.render(delta);
		}

		thePlayer.render(delta);
	}

	public boolean keyDown(int keycode)
	{
		if(keycode == Input.Keys.SPACE)
		{
			PlantFactory factory = new PlantFactory(PlantType.TREE, "oak");
			Plant p = factory.create(thePlayer.getLocation());
			this.plantList.add(p);
		}
		else if(keycode == Input.Keys.E)
		{
			if(screen.getCurrentOverlay() == null)
				screen.setCurrentOverlay(new PlantStatusOverlay(screen, null));
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
}
