package com.theprogrammingturkey.ld46.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.theprogrammingturkey.ld46.entity.state.PlayerState;
import com.theprogrammingturkey.ld46.game.World;
import com.theprogrammingturkey.ld46.inventory.Inventory;
import com.theprogrammingturkey.ld46.inventory.Slot;
import com.theprogrammingturkey.ld46.registry.ItemRegistry;
import com.theprogrammingturkey.ld46.rendering.Animation;
import com.theprogrammingturkey.ld46.rendering.GameColors;
import com.theprogrammingturkey.ld46.rendering.Renderer;
import com.theprogrammingturkey.ld46.rendering.Textures;
import com.theprogrammingturkey.ld46.rendering.WrapperTR;
import com.theprogrammingturkey.ld46.util.Direction;

public class Player extends Entity
{
	public static int placement_radius = 40;
	public static float MOVE_SPEED = 1f;

	// W/A/S/D
	public boolean[] moveKeys = new boolean[4];
	private Animation[] animations = new Animation[4];
	private Animation currentAnimation;

	public PlayerState state = PlayerState.NONE;

	private int currentSlot = 0;
	public Inventory inventory = new Inventory(25);

	public Player(World world, Vector2 location)
	{
		super(world, location, new Vector2(64, 64));
		super.addTexture(new WrapperTR(64, 0));

		TextureRegion[] leftTextures = new TextureRegion[5];
		TextureRegion[] rightTextures = new TextureRegion[5];
		TextureRegion[] frontTextures = new TextureRegion[4];
		TextureRegion[] backTextures = new TextureRegion[5];
		for(int i = 0; i < 4; i++)
		{
			leftTextures[i] = new TextureRegion(Textures.atlas, 64 * (8 + i), 64, 64, 64);
			rightTextures[i] = new TextureRegion(Textures.atlas, 64 * (4 + i), 64, 64, 64);
			frontTextures[i] = new TextureRegion(Textures.atlas, 64 * i, 64, 64, 64);
			backTextures[i] = new TextureRegion(Textures.atlas, 64 * (12 + i), 64, 64, 64);
		}

		animations[0] = new Animation(10, true, backTextures);
		animations[1] = new Animation(10, true, leftTextures);
		animations[2] = new Animation(10, true, frontTextures);
		animations[3] = new Animation(10, true, rightTextures);

		currentAnimation = animations[0];

		inventory.addToInventory(ItemRegistry.getItemStack("oak"));
		inventory.addToInventory(ItemRegistry.getItemStack("bonsai"));
		inventory.addToInventory(ItemRegistry.getItemStack("cactus"));
		inventory.addToInventory(ItemRegistry.getItemStack("cherry_blossom"));
	}

	public void update()
	{
		super.update();
		currentAnimation.setMoving(false);

		for(Direction d : Direction.values())
		{
			if(moveKeys[d.getVal()])
			{
				currentAnimation = animations[d.getVal()];
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

	public Inventory getInventory()
	{
		return this.inventory;
	}

	public void adjCurrentSlotIndex(int amount)
	{
		this.currentSlot += amount;
		if(this.currentSlot < 0)
			this.currentSlot += 5;

		this.currentSlot %= 5;
	}

	public int getCurrentSlotIndex()
	{
		return currentSlot;
	}

	public Slot getCurrentSlot()
	{
		return this.inventory.getSlot(currentSlot);
	}

	public void stop()
	{
		for(Direction d : Direction.values())
		{
			moveKeys[d.getVal()] = false;
			currentAnimation.setMoving(true);
		}
	}
}
