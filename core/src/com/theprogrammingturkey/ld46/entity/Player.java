package com.theprogrammingturkey.ld46.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.theprogrammingturkey.ld46.entity.state.PlayerState;
import com.theprogrammingturkey.ld46.game.GameCore;
import com.theprogrammingturkey.ld46.rendering.Animation;
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
	private Animation currentAnimation;
	private Animation frontAnimation;

	public PlayerState state = PlayerState.NONE;

	public Player(GameCore gameCore, Vector2 location)
	{
		super(gameCore, location, new Vector2(64, 64), new WrapperTR(64, 0));

		//TextureRegion[] leftTextures = new TextureRegion[5];
		//TextureRegion[] rightTextures = new TextureRegion[5];
		TextureRegion[] frontTextures = new TextureRegion[4];
		//TextureRegion[] backTextures = new TextureRegion[5];
		for(int i = 0; i < 4; i++)
		{
			//leftTextures[i - 1] = new TextureRegion("textures/player/playerLeft" + i + ".png");
			//rightTextures[i - 1] = new TextureRegion("textures/player/playerRight" + i + ".png");
			frontTextures[i] = new TextureRegion(Renderer.atlas, 64 * i, 64, 64, 64);
			//backTextures[i - 1] = new TextureRegion("textures/player/playerBack" + i + ".png");
		}

		//leftAnimation = new Animation(10, true, leftTextures);
		//rightAnimation = new Animation(10, true, rightTextures);
		frontAnimation = new Animation(10, true, frontTextures);
		//backAnimation = new Animation(10, true, backTextures);

		currentAnimation = frontAnimation;
	}

	public void update()
	{
		super.update();
		currentAnimation.setMoving(false);

		for(Direction d : Direction.values())
		{
			if(moveKeys[d.getVal()])
			{
				currentAnimation.setMoving(true);
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

		currentAnimation.update();
		Renderer.draw(this.currentAnimation.getCurrentTexture(), location.x, location.y, size.x, size.y);
		//super.render(delta);
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
