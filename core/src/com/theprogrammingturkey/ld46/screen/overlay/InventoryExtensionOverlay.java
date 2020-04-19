package com.theprogrammingturkey.ld46.screen.overlay;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Align;
import com.theprogrammingturkey.ld46.entity.Player;
import com.theprogrammingturkey.ld46.inventory.Inventory;
import com.theprogrammingturkey.ld46.inventory.Slot;
import com.theprogrammingturkey.ld46.rendering.GameColors;
import com.theprogrammingturkey.ld46.rendering.Renderer;
import com.theprogrammingturkey.ld46.rendering.Textures;
import com.theprogrammingturkey.ld46.screen.GameScreen;

public class InventoryExtensionOverlay extends Overlay
{
	private Player player;

	private boolean isAnimatingIn;
	private boolean isAnimatingOut = false;

	private int animateIndex;

	public InventoryExtensionOverlay(GameScreen screen, Overlay parent, Player player)
	{
		super(screen, parent);
		this.player = player;
		isAnimatingIn = true;
		animateIndex = -Textures.hotbarExtension.getRegionHeight() - 25;
	}

	@Override
	public void render(float delta)
	{
		Renderer.drawRect(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), GameColors.TEXT_ALPHA_BG, true);
		int left = (Gdx.graphics.getWidth() / 2) - 180;
		TextureRegion tr = Textures.hotbarExtension;
		Renderer.draw(tr, left - 10, animateIndex, tr.getRegionWidth(), tr.getRegionHeight());

		Renderer.draw(Textures.hotbar, left - 10, 0, Textures.hotbar.getRegionWidth(), Textures.hotbar.getRegionHeight());
		Inventory inv = player.getInventory();
		for(int i = 5; i < 25; i++)
		{

			Slot s = inv.getSlot(i);
			if(s.getItem().getTextureRegion() == null)
				continue;

			int row = i / 5;
			int col = i % 5;

			Renderer.draw(s.getItem().getTextureRegion(), left + (74 * col), animateIndex + (69 * row) - 12, 64, 64);
			Renderer.drawStringAligned(Renderer.rust, left + (74 * (col + 1)) - 12, animateIndex + (69 * row) - 3, String.valueOf(s.getAmount()), 0.2f, Align.right, Color.WHITE);
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
		if(keycode == Input.Keys.E)
		{
			isAnimatingOut = true;
			return true;
		}
		return false;
	}

	@Override
	public void close()
	{
		isAnimatingOut = true;
	}
}
