package com.theprogrammingturkey.ld46.entity;

import com.badlogic.gdx.math.Vector2;
import com.theprogrammingturkey.ld46.game.World;
import com.theprogrammingturkey.ld46.rendering.WrapperTR;
import com.theprogrammingturkey.ld46.screen.GameScreen;
import com.theprogrammingturkey.ld46.screen.overlay.MarketOverlay;

public class Market extends Entity
{
	public Market(World world, Vector2 location)
	{
		super(world, location, new Vector2(64, 64));
		super.addTexture(new WrapperTR(29 * 64, 0));
	}

	@Override
	public void onClick(GameScreen screen, Player player)
	{
		screen.setCurrentOverlay(new MarketOverlay(screen, null, player));
	}
}
