package com.theprogrammingturkey.ld46.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.theprogrammingturkey.ld46.entity.state.PlayerState;
import com.theprogrammingturkey.ld46.rendering.GameColors;
import com.theprogrammingturkey.ld46.rendering.Renderer;
import com.theprogrammingturkey.ld46.rendering.WrapperTR;
import com.theprogrammingturkey.ld46.util.Direction;

public class Player extends Entity
{
	public static int placement_radius = 40;
	public static float MOVE_SPEED = 1f;

	// W/A/S/D
	public boolean[] moveKeys = new boolean[4];

	public PlayerState state = PlayerState.NONE;

	public Player(Vector2 location)
	{
		super(location, new Vector2(32, 32), new WrapperTR(64, 0));
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

	@Override
	public void render(float delta)
	{
		if(state == PlayerState.PLACING)
		{
			float centerX = location.x + size.x / 2;
			float centerY = location.y + size.y / 2;
			int mx = Gdx.input.getX();
			int my = Gdx.graphics.getHeight() - Gdx.input.getY();
			boolean inside = isVAlidPlacementLoc(mx, my);
			Renderer.drawCircle(centerX, centerY, placement_radius, inside ? GameColors.VALID_AREA : GameColors.INVALID_AREA, true);
			Renderer.drawCircle(centerX, centerY, placement_radius, inside ? GameColors.VALID_AREA_NO_ALPHA : GameColors.INVALID_AREA_NO_ALPHA, false);
		}

		super.render(delta);
	}

	public void setMoveState(Direction direction, boolean state)
	{
		moveKeys[direction.getVal()] = state;
	}

	public void setState(PlayerState state)
	{
		this.state = state;
	}

	public PlayerState getState()
	{
		return this.state;
	}

	public boolean isVAlidPlacementLoc(int x, int y)
	{
		float centerX = location.x + size.x / 2;
		float centerY = location.y + size.y / 2;
		float dx = centerX - x;
		float dy = centerY - y;
		return dx * dx + dy * dy <= placement_radius * placement_radius;
	}
}
