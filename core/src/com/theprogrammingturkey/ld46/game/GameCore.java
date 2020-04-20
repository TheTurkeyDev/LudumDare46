package com.theprogrammingturkey.ld46.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.theprogrammingturkey.ld46.LD46;
import com.theprogrammingturkey.ld46.entity.Entity;
import com.theprogrammingturkey.ld46.entity.Plant;
import com.theprogrammingturkey.ld46.entity.Player;
import com.theprogrammingturkey.ld46.entity.state.PlayerState;
import com.theprogrammingturkey.ld46.game.action.Action;
import com.theprogrammingturkey.ld46.game.action.ChopAction;
import com.theprogrammingturkey.ld46.game.action.TrimAction;
import com.theprogrammingturkey.ld46.game.action.WateringAction;
import com.theprogrammingturkey.ld46.item.ItemStack;
import com.theprogrammingturkey.ld46.screen.GameScreen;
import com.theprogrammingturkey.ld46.screen.overlay.ActionsOverlay;
import com.theprogrammingturkey.ld46.screen.overlay.InventoryExtensionOverlay;
import com.theprogrammingturkey.ld46.screen.overlay.PlantStatusOverlay;
import com.theprogrammingturkey.ld46.sounds.SoundManager;
import com.theprogrammingturkey.ld46.util.Direction;

import java.util.Random;

public class GameCore
{
	public static final Random rand = new Random();

	private GameScreen screen;
	private Player thePlayer;
	private World world;


	public GameCore(GameScreen screen)
	{
		world = new World(this);

		this.screen = screen;
		thePlayer = new Player(world, new Vector2(200, 200));

		world.initWorld();
	}

	public void update()
	{
		world.update();
		thePlayer.update();
	}

	public void render(float delta)
	{
		world.render(delta, screen.getCurrentOverlay() == null);
		thePlayer.render(delta);
	}

	public boolean keyDown(int keycode)
	{
		if(keycode == Input.Keys.E)
		{
			if(screen.getCurrentOverlay() != null)
				screen.getCurrentOverlay().close();
			else
				screen.setCurrentOverlay(new InventoryExtensionOverlay(screen, null, thePlayer));
			return true;
		}
		else if(keycode == Input.Keys.SPACE)
		{
			if(thePlayer.getState() == PlayerState.NONE)
			{
				ItemStack currentItem = thePlayer.getCurrentSlot().getStack();
				if(currentItem != ItemStack.EMPTY && currentItem.getItem().isPlaceable())
					thePlayer.setState(PlayerState.PLACING);
			}
			else if(thePlayer.getState() == PlayerState.PLACING)
			{
				thePlayer.setState(PlayerState.NONE);
			}
			return true;
		}
		else if(keycode == Input.Keys.SHIFT_LEFT)
		{
			thePlayer.setRunning(true);
			return true;
		}

		if(keycode == Input.Keys.W)
		{
			thePlayer.setMoveState(Direction.UP, true);
			return true;
		}
		else if(keycode == Input.Keys.A)
		{
			thePlayer.setMoveState(Direction.LEFT, true);
			return true;
		}
		else if(keycode == Input.Keys.S)
		{
			thePlayer.setMoveState(Direction.DOWN, true);
			return true;
		}
		else if(keycode == Input.Keys.D)
		{
			thePlayer.setMoveState(Direction.RIGHT, true);
			return true;
		}
		return false;
	}

	public boolean keyUp(int keycode)
	{
		if(keycode == Input.Keys.SHIFT_LEFT)
		{
			thePlayer.setRunning(false);
		}

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
				thePlayer.getCurrentSlot().getStack().getItem().onPlace(world, new Vector2(screenX, yFlip));
				thePlayer.getCurrentSlot().decrementSlotCount();
				SoundManager.playSound(SoundManager.plant);
				thePlayer.setState(PlayerState.NONE);
				return true;
			}
			else
			{
				LD46.SNACK_BAR.createSnackMessage("PLACEMENT OUT OF RADIUS", Color.RED);
			}
		}
		else if(button == 1)
		{
			ItemStack stack = thePlayer.getCurrentSlot().getStack();
			if(stack != ItemStack.EMPTY && stack.getItem().isUseable())
			{
				stack.getItem().onUse(world, thePlayer.getLocation());
			}
		}

		Vector2 playercenter = thePlayer.getLocation().cpy().add(thePlayer.getSize().x / 2, 0);
		Vector2 entCenter = new Vector2();
		for(Plant plant : world.getPlantList())
		{
			Rectangle rect = plant.getBoundingBox();
			if(rect.contains(screenX, yFlip))
			{
				if(rect.getPosition(entCenter).add(rect.getWidth() / 2, 0).dst(playercenter) < GameValues.INTERACT_RADIUS)
				{
					if(button == 0)
					{

						screen.setCurrentOverlay(new PlantStatusOverlay(plant, screen, null));
						return true;
					}
					else if(button == 1)
					{
						screen.setCurrentOverlay(new ActionsOverlay(plant, screen, null,
								new WateringAction(plant, thePlayer),
								new TrimAction(plant, thePlayer),
								new ChopAction(plant, thePlayer),
								new Action("Gather", () -> System.out.println("Gather!"))
						));
						return true;
					}
				}
				else
				{
					LD46.SNACK_BAR.createSnackMessage("TOO FAR AWAY", Color.RED);
				}
			}
		}

		for(Entity entity : world.getEntityList())
		{
			Rectangle rect = entity.getBoundingBox();
			if(rect.contains(screenX, yFlip))
			{
				if(rect.getPosition(entCenter).add(rect.getWidth() / 2, rect.getHeight() / 2).dst(playercenter) < GameValues.INTERACT_RADIUS)
				{
					if(button == 1)
					{
						entity.onClick(screen, thePlayer);
						return true;
					}
				}
				else
				{
					LD46.SNACK_BAR.createSnackMessage("TOO FAR AWAY", Color.RED);
				}
			}
		}

		return false;
	}

	public boolean scrolled(int amount)
	{
		if(!thePlayer.getState().equals(PlayerState.PLACING))
			thePlayer.adjCurrentSlotIndex(amount);
		return true;
	}

	public Player getPlayer()
	{
		return thePlayer;
	}

	public World getCurrentWorld()
	{
		return world;
	}
}
