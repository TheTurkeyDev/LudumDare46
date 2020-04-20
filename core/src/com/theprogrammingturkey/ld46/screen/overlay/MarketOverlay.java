package com.theprogrammingturkey.ld46.screen.overlay;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Align;
import com.theprogrammingturkey.ld46.LD46;
import com.theprogrammingturkey.ld46.entity.Player;
import com.theprogrammingturkey.ld46.inventory.Slot;
import com.theprogrammingturkey.ld46.item.ItemStack;
import com.theprogrammingturkey.ld46.registry.ItemRegistry;
import com.theprogrammingturkey.ld46.rendering.GameColors;
import com.theprogrammingturkey.ld46.rendering.Renderer;
import com.theprogrammingturkey.ld46.rendering.Textures;
import com.theprogrammingturkey.ld46.screen.GameScreen;
import com.theprogrammingturkey.ld46.screen.overlay.widgets.Button;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MarketOverlay extends Overlay
{
	private float bottom;
	private float left;

	private List<Wrapper> contents = new ArrayList<>();
	private Map<Rectangle, Wrapper> slotsBB = new HashMap<>();

	private Player player;

	private Wrapper selected = new Wrapper(ItemStack.EMPTY, 0, 0);

	public MarketOverlay(GameScreen screen, Overlay parent, Player player)
	{
		super(screen, parent);

		this.player = player;

		contents.add(new Wrapper(ItemRegistry.getItemStack("oak"), 100, 50));
		contents.add(new Wrapper(ItemRegistry.getItemStack("bonsai"), 200, 100));
		contents.add(new Wrapper(ItemRegistry.getItemStack("cactus"), 700, 350));
		contents.add(new Wrapper(ItemRegistry.getItemStack("cherry_blossom"), 600, 300));
		contents.add(new Wrapper(ItemRegistry.getItemStack("elm"), 400, 200));
		contents.add(new Wrapper(ItemRegistry.getItemStack("yucca"), 1000, 500));
		contents.add(new Wrapper(ItemRegistry.getItemStack("jade"), 800, 400));
		contents.add(new Wrapper(ItemRegistry.getItemStack("peace_lily"), 900, 450));
		contents.add(new Wrapper(ItemRegistry.getItemStack("pine"), 500, 250));
		contents.add(new Wrapper(ItemRegistry.getItemStack("rotala"), 300, 150));
		contents.add(new Wrapper(ItemRegistry.getItemStack("stick"), 10, 5));
		contents.add(new Wrapper(ItemRegistry.getItemStack("log"), 50, 25));
		contents.add(new Wrapper(ItemRegistry.getItemStack("heat_lamp"), 400, 200));

		TextureRegion marketTR = Textures.market;
		bottom = (Gdx.graphics.getHeight() / 2f) - (marketTR.getRegionHeight() / 2f) + 100;
		left = (Gdx.graphics.getWidth() / 2f) - (marketTR.getRegionWidth() / 2f);

		float top = bottom + marketTR.getRegionHeight() - 45;
		for(int i = 0; i < contents.size(); i++)
		{
			ItemStack stack = this.contents.get(i).stack;

			int playerCount = 0;
			Slot s = player.inventory.getSlotForItem(stack.getItem());
			if(s != null)
			{
				ItemStack playerStack = s.getStack();
				if(playerStack != ItemStack.EMPTY)
					playerCount = playerStack.getAmount();
			}
			stack.setAmount(playerCount);

			int row = i / 5;
			int col = i % 5;

			Rectangle rect = new Rectangle(left + (74 * col) + 10, top - (69 * row) - 24, 64, 64);
			slotsBB.put(rect, this.contents.get(i));
		}

		Button buyBtn;
		super.addWidget(buyBtn = new Button((int) left + 375, (int) bottom + 5, 108, 64)
		{
			@Override
			public void render(float delta)
			{
				Renderer.drawStringAligned(Renderer.font, left + 425, bottom + 55, "BUY", 1f, Align.center, Color.WHITE);
				Renderer.drawStringAligned(Renderer.rust, left + 402, bottom + 20, String.valueOf(selected.buy), 0.35f, Align.center, Color.WHITE);
				Renderer.draw(Textures.coin, left + 445, bottom + 7, 32, 32);
			}
		});
		buyBtn.setCallback(() ->
		{
			if(selected.stack == ItemStack.EMPTY)
			{
				LD46.SNACK_BAR.createSnackMessage("SELECT AN ITEM TO BUY FIRST!", Color.RED);
				return;
			}

			if(player.getMoney() >= selected.buy)
			{
				player.decMoney(selected.buy);
				selected.stack.inc(1);
				player.inventory.addToInventory(new ItemStack(selected.stack.getItem(), 1));
			}
			else
			{
				LD46.SNACK_BAR.createSnackMessage("YOU DON'T HAVE ENOUGH MONEY!", Color.RED);
			}
		});

		Button sellBtn;
		super.addWidget(sellBtn = new Button((int) left + 484, (int) bottom + 5, 108, 64)
		{
			@Override
			public void render(float delta)
			{
				Renderer.drawStringAligned(Renderer.font, left + 540, bottom + 55, "SELL", 1f, Align.center, Color.WHITE);
				Renderer.drawStringAligned(Renderer.rust, left + 490, bottom + 20, String.valueOf(selected.sell), 0.35f, Align.left, Color.WHITE);
				Renderer.draw(Textures.coin, left + 555, bottom + 7, 32, 32);
			}
		});

		sellBtn.setCallback(() ->
		{
			if(selected.stack == ItemStack.EMPTY)
			{
				LD46.SNACK_BAR.createSnackMessage("SELECT AN ITEM TO SELL FIRST!", Color.RED);
				return;
			}

			if(selected.stack.getAmount() > 0)
			{
				player.incMoney(selected.sell);
				selected.stack.dec(1);
				Slot s = player.inventory.getSlotForItem(selected.stack.getItem());
				if(s != null)
					s.decrementSlotCount();
			}
			else
			{
				LD46.SNACK_BAR.createSnackMessage("YOU DON'T HAVE ANY TO SELL!", Color.RED);
			}

		});

	}

	@Override
	public void render(float delta)
	{
		Renderer.drawRect(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), GameColors.TEXT_ALPHA_BG, true);
		TextureRegion marketTR = Textures.market;
		Renderer.draw(marketTR, left, bottom, marketTR.getRegionWidth(), marketTR.getRegionHeight());

		for(Rectangle rect : slotsBB.keySet())
		{
			ItemStack stack = slotsBB.get(rect).stack;
			if(stack == ItemStack.EMPTY || stack.getItem().getTextureRegion() == null)
				continue;

			Renderer.draw(stack.getItem().getTextureRegion(), rect.x, rect.y, 64, 64);
			Renderer.drawStringAligned(Renderer.rust, rect.x + 60, rect.y + 10, String.valueOf(stack.getAmount()), 0.2f, Align.right, Color.WHITE);
		}

		int mx = Gdx.input.getX();
		int my = Gdx.graphics.getHeight() - Gdx.input.getY();
		for(Rectangle rect : slotsBB.keySet())
		{
			if(rect.contains(mx, my))
			{
				ItemStack stack = slotsBB.get(rect).stack;
				if(stack != ItemStack.EMPTY)
				{
					Renderer.drawStringCenteredWithBG(Renderer.rust, rect.x + (rect.width / 2), rect.y + rect.height + 20, stack.getItem().getDisplayName().toUpperCase(), 0.25f, Color.WHITE);
				}
			}
		}

		if(selected.stack != ItemStack.EMPTY && selected.stack.getItem().getTextureRegion() != null)
		{
			Renderer.draw(selected.stack.getItem().getTextureRegion(), left + 425, bottom + 115, 128, 128);
			Renderer.drawStringAligned(Renderer.rust, left + 485, bottom + 90, selected.stack.getItem().getDisplayName().toUpperCase(), 0.2f, Align.center, Color.WHITE);
		}

		super.render(delta);
	}

	public boolean keyDown(int keycode)
	{
		if(keycode == Input.Keys.ESCAPE)
		{
			close();
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
				Wrapper wrapper = slotsBB.get(rect);
				if(wrapper.stack != ItemStack.EMPTY)
				{
					selected = wrapper;
					return true;
				}
			}
		}
		return super.touchDown(screenX, screenY, pointer, button);
	}

	public static class Wrapper
	{
		public ItemStack stack;
		public int buy;
		public int sell;

		public Wrapper(ItemStack stack, int buy, int sell)
		{
			this.stack = stack;
			this.buy = buy;
			this.sell = sell;
		}
	}
}
