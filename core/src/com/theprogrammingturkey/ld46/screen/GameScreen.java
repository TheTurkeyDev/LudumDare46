package com.theprogrammingturkey.ld46.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Align;
import com.theprogrammingturkey.ld46.game.GameCore;
import com.theprogrammingturkey.ld46.inventory.Inventory;
import com.theprogrammingturkey.ld46.inventory.Slot;
import com.theprogrammingturkey.ld46.rendering.Renderer;
import com.theprogrammingturkey.ld46.rendering.Textures;
import com.theprogrammingturkey.ld46.screen.overlay.InventoryExtensionOverlay;
import com.theprogrammingturkey.ld46.screen.overlay.Overlay;

public class GameScreen implements Screen
{
	private Stage stage;
	private GameCore theGame;

	private Overlay currenOverlay;

	public GameScreen()
	{
		theGame = new GameCore(this);

		stage = new Stage()
		{
			@Override
			public boolean keyDown(int keycode)
			{
				if(!super.keyDown(keycode))
				{
					if(currenOverlay != null)
						return currenOverlay.keyDown(keycode);
					return theGame.keyDown(keycode);
				}
				return true;
			}

			@Override
			public boolean keyUp(int keycode)
			{
				if(!super.keyUp(keycode))
				{
					if(currenOverlay != null)
						return currenOverlay.keyUp(keycode);
					return theGame.keyUp(keycode);
				}
				return true;
			}

			public boolean touchDown(int screenX, int screenY, int pointer, int button)
			{
				if(!super.touchDown(screenX, screenY, pointer, button))
				{
					if(currenOverlay != null)
						return currenOverlay.touchDown(screenX, screenY, pointer, button);
					return theGame.touchDown(screenX, screenY, pointer, button);
				}
				return true;
			}
		};
		Gdx.input.setInputProcessor(stage);
	}

	@Override
	public void show()
	{
	}

	public void update()
	{
		theGame.update();
	}

	@Override
	public void render(float delta)
	{
		stage.act(delta);
		update();

		theGame.render(delta);

		if(currenOverlay instanceof InventoryExtensionOverlay)
			currenOverlay.render(delta);

		int left = (Gdx.graphics.getWidth() / 2) - 180;
		Renderer.draw(Textures.hotbar, left - 10, 0, Textures.hotbar.getRegionWidth(), Textures.hotbar.getRegionHeight());
		Inventory inv = theGame.getPlayer().getInventory();
		for(int i = 0; i < 5; i++)
		{
			Slot s = inv.getSlot(i);
			if(s.getItem().getTextureRegion() == null)
				continue;

			Renderer.draw(s.getItem().getTextureRegion(), left + (74 * i), 10, 64, 64);
			Renderer.drawStringAligned(Renderer.rust, left + (74 * (i + 1)) - 12, 15, String.valueOf(s.getAmount()), 0.2f, Align.right, Color.WHITE);
		}

		if(currenOverlay != null && !(currenOverlay instanceof InventoryExtensionOverlay))
			currenOverlay.render(delta);

		Renderer.end();
		stage.draw();
		Renderer.begin();
	}

	@Override
	public void resize(int width, int height)
	{
		stage.getViewport().update(width, height, true);
	}

	@Override
	public void pause()
	{

	}

	@Override
	public void resume()
	{

	}

	@Override
	public void hide()
	{

	}

	@Override
	public void dispose()
	{

	}

	public void setCurrentOverlay(Overlay overlay)
	{
		theGame.getPlayer().stop();
		this.currenOverlay = overlay;
	}

	public Overlay getCurrentOverlay()
	{
		return currenOverlay;
	}
}