package com.theprogrammingturkey.ld46.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Align;
import com.theprogrammingturkey.ld46.game.GameCore;
import com.theprogrammingturkey.ld46.game.World;
import com.theprogrammingturkey.ld46.inventory.Inventory;
import com.theprogrammingturkey.ld46.inventory.Slot;
import com.theprogrammingturkey.ld46.item.ItemStack;
import com.theprogrammingturkey.ld46.rendering.GameColors;
import com.theprogrammingturkey.ld46.rendering.Renderer;
import com.theprogrammingturkey.ld46.rendering.Textures;
import com.theprogrammingturkey.ld46.screen.overlay.InventoryExtensionOverlay;
import com.theprogrammingturkey.ld46.screen.overlay.Overlay;
import com.theprogrammingturkey.ld46.util.StringUtil;

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

			@Override
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

			public boolean scrolled(int amount)
			{
				if(!super.scrolled(amount))
				{
					if(currenOverlay != null)
						return false;
					//return currenOverlay.scrolled(amount);
					return theGame.scrolled(amount);
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

		World world = theGame.getCurrentWorld();
		int hours = world.getGameTime().getHour();
		int minutes = world.getGameTime().getMinute();
		StringBuilder time = new StringBuilder();
		if(hours == 0)
			time.append("12");
		else if(hours < 10)
			time.append("0").append(hours);
		else if(hours > 12)
			time.append(hours - 12);
		else
			time.append(hours);
		time.append(":");
		if(minutes < 10)
			time.append("0").append(minutes);
		else
			time.append(minutes);

		int cx = Gdx.graphics.getWidth() - 40;
		int cy = Gdx.graphics.getHeight() - 40;

		float sunAngle = world.getSunAngle();
		float light = world.getCurrentLight();

		float sunX = ((float) Math.cos(-sunAngle + Math.PI) * 30) + cx;
		float sunY = ((float) Math.sin(-sunAngle + Math.PI) * 30) + cy;
		float moonX = ((float) Math.cos(-sunAngle) * 30) + cx;
		float moonY = ((float) Math.sin(-sunAngle) * 30) + cy;

		GameColors.GROUND.fromHsv(120, 1, MathUtils.clamp(light, 0.15f, 0.6f));
		GameColors.SKY.fromHsv(190, .75f, MathUtils.clamp(light, 0.15f, 0.95f));

		Renderer.drawRect(cx - 40, cy, 80, 40, GameColors.SKY, true);

		Renderer.draw(Textures.sun, sunX - 10, sunY - 10, 20, 20);
		Renderer.draw(Textures.moon, moonX - 10, moonY - 10, 20, 20);

		Renderer.drawRect(cx - 40, cy - 40, 80, 40, GameColors.GROUND, true);

		Renderer.drawStringAligned(Renderer.rust, cx, cy - 50, time.toString(), .25f, Align.center, Color.WHITE);
		Renderer.drawStringAligned(Renderer.rust, cx, cy - 70, "Day: " + world.getGameTime().getDay(), .2f, Align.center, Color.WHITE);
		Renderer.drawStringAligned(Renderer.rust, cx, cy - 90, "Temp: " + StringUtil.formatToInt(world.getWeather().getTemp()), .2f, Align.center, Color.WHITE);


		if(currenOverlay instanceof InventoryExtensionOverlay)
			currenOverlay.render(delta);

		int left = (Gdx.graphics.getWidth() / 2) - 180;
		Renderer.draw(Textures.hotbar, left - 10, 0, Textures.hotbar.getRegionWidth(), Textures.hotbar.getRegionHeight());
		Inventory inv = theGame.getPlayer().getInventory();
		int currentSlotIndex = theGame.getPlayer().getCurrentSlotIndex();
		for(int i = 0; i < 5; i++)
		{
			Slot s = inv.getSlot(i);

			if(currentSlotIndex == i)
				Renderer.drawRect(left + (74 * i), 10, 64, 64, GameColors.HIGHLIGHT_ALPHA_BG, true);

			ItemStack stack = s.getStack();
			if(stack == ItemStack.EMPTY || stack.getItem().getTextureRegion() == null)
				continue;

			Renderer.draw(stack.getItem().getTextureRegion(), left + (74 * i), 10, 64, 64);
			Renderer.drawStringAligned(Renderer.rust, left + (74 * (i + 1)) - 12, 15, String.valueOf(stack.getAmount()), 0.2f, Align.right, Color.WHITE);
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