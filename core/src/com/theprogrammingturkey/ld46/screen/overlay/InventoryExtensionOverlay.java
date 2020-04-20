package com.theprogrammingturkey.ld46.screen.overlay;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Align;
import com.theprogrammingturkey.ld46.entity.Player;
import com.theprogrammingturkey.ld46.game.GameCore;
import com.theprogrammingturkey.ld46.inventory.Inventory;
import com.theprogrammingturkey.ld46.inventory.Slot;
import com.theprogrammingturkey.ld46.item.ItemStack;
import com.theprogrammingturkey.ld46.rendering.GameColors;
import com.theprogrammingturkey.ld46.rendering.Renderer;
import com.theprogrammingturkey.ld46.rendering.Textures;
import com.theprogrammingturkey.ld46.screen.GameScreen;

import java.util.HashMap;
import java.util.Map;

public class InventoryExtensionOverlay extends Overlay
{
	private Player player;

	private boolean isAnimatingIn;
	private boolean isAnimatingOut = false;

	private int animateIndex;

	private Map<Rectangle, Slot> slotsBB = new HashMap<>();

	private ItemStack pickedUp = ItemStack.EMPTY;

	public InventoryExtensionOverlay(GameScreen screen, Overlay parent, Player player)
	{
		super(screen, parent);
		this.player = player;
		isAnimatingIn = true;
		animateIndex = -Textures.hotbarExtension.getRegionHeight() - 25;

		int left = (Gdx.graphics.getWidth() / 2) - 180;
		Inventory inv = player.getInventory();
		for(int i = 0; i < 5; i++)
		{
			Rectangle rect = new Rectangle(left + (74 * i), 10, 64, 64);
			slotsBB.put(rect, inv.getSlot(i));
		}
		for(int i = 5; i < 25; i++)
		{

			Slot s = inv.getSlot(i);
			int row = i / 5;
			int col = i % 5;

			Rectangle rect = new Rectangle(left + (74 * col), (69 * row) + 13, 64, 64);
			slotsBB.put(rect, s);
		}
	}

	@Override
	public void render(float delta)
	{
		int mx = Gdx.input.getX();
		int my = Gdx.graphics.getHeight() - Gdx.input.getY();

		Renderer.drawRect(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), GameColors.TEXT_ALPHA_BG, true);
		int left = (Gdx.graphics.getWidth() / 2) - 180;
		TextureRegion tr = Textures.hotbarExtension;
		Renderer.draw(tr, left - 10, animateIndex, tr.getRegionWidth(), tr.getRegionHeight());
		Renderer.draw(Textures.hotbar, left - 10, 0, Textures.hotbar.getRegionWidth(), Textures.hotbar.getRegionHeight());

		for(Rectangle rect : slotsBB.keySet())
		{
			if(rect.y == 10)
				continue;
			ItemStack stack = slotsBB.get(rect).getStack();
			if(stack == ItemStack.EMPTY || stack.getItem().getTextureRegion() == null)
				continue;

			Renderer.draw(stack.getItem().getTextureRegion(), rect.x, animateIndex + rect.y - 24, 64, 64);
			Renderer.drawStringAligned(Renderer.rust, rect.x + 64, animateIndex + rect.y - 20, String.valueOf(stack.getAmount()), 0.2f, Align.right, Color.WHITE);
		}

		for(Rectangle rect : slotsBB.keySet())
		{
			if(rect.contains(mx, my))
			{
				ItemStack stack = slotsBB.get(rect).getStack();
				if(stack != ItemStack.EMPTY)
				{
					Renderer.drawStringCenteredWithBG(Renderer.rust, rect.x + (rect.width / 2), rect.y + rect.height + 20, stack.getItem().getDisplayName().toUpperCase(), 0.25f, Color.WHITE);
				}
			}
		}

		if(pickedUp != ItemStack.EMPTY)
		{
			Renderer.draw(pickedUp.getItem().getTextureRegion(), mx - 32, my - 32, 64, 64);
			Renderer.drawStringAligned(Renderer.rust, mx + 32, my + -25, String.valueOf(pickedUp.getAmount()), 0.2f, Align.right, Color.WHITE);
		}

		if(isAnimatingIn)
			animateIndex += 20;
		else if(isAnimatingOut)
			animateIndex -= 20;

		if(isAnimatingIn && animateIndex > 25)
		{
			animateIndex = 25;
			isAnimatingIn = false;
		}
		else if(isAnimatingOut && animateIndex <= -tr.getRegionHeight() - 25)
		{
			isAnimatingOut = false;
			screen.setCurrentOverlay(null);
		}
	}

	public boolean keyDown(int keycode)
	{
		if(keycode == Input.Keys.E || keycode == Input.Keys.ESCAPE)
		{
			isAnimatingOut = true;
			return true;
		}
		return false;
	}

	public boolean touchDown(int screenX, int screenY, int pointer, int button)
	{
		int flipY = Gdx.graphics.getHeight() - screenY;
		for(Rectangle rect : slotsBB.keySet())
		{
			if(rect.contains(screenX, flipY))
			{
				Slot s = slotsBB.get(rect);
				if(pickedUp == ItemStack.EMPTY)
				{
					pickedUp = s.removeFromSlot(100);
				}
				else
				{
					if(s.addItemToSlot(pickedUp))
						pickedUp = ItemStack.EMPTY;
				}
			}
		}
		return false;
	}

	@Override
	public void close()
	{
		isAnimatingOut = true;
	}
}
