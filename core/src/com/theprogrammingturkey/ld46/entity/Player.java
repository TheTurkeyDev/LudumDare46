package com.theprogrammingturkey.ld46.entity;

import com.badlogic.gdx.math.Vector2;
import com.theprogrammingturkey.ld46.util.Direction;

public class Player extends Entity
{
	public static float MOVE_SPEED = 1f;

	// W/A/S/D
	public boolean[] moveKeys = new boolean[4];

	public Player(Vector2 location)
	{
		super(location, new Vector2(16, 16));
	}

	public void update()
	{
		super.update();
		for(Direction d : Direction.values())
		{
			if(moveKeys[d.getVal()])
			{
				d.addToLoc(location, MOVE_SPEED);
			}
		}
	}

	public void render(float delta)
	{
		super.render(delta);
	}

	public void setMoveState(Direction direction, boolean state)
	{
		moveKeys[direction.getVal()] = state;
	}
}
