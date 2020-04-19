package com.theprogrammingturkey.ld46.screen.overlay;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Align;
import com.theprogrammingturkey.ld46.entity.Plant;
import com.theprogrammingturkey.ld46.game.action.Action;
import com.theprogrammingturkey.ld46.rendering.Renderer;
import com.theprogrammingturkey.ld46.screen.GameScreen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ActionsOverlay extends Overlay
{
	private List<Action> actions = new ArrayList<>();
	private Plant plant;

	private float centerX;
	private float centerY;

	public ActionsOverlay(Plant plant, GameScreen screen, Overlay parent, Action... actions)
	{
		super(screen, parent);
		this.plant = plant;

		this.actions = Arrays.asList(actions);

		centerX = plant.getLocation().x;
		centerY = plant.getLocation().y + (plant.getSize().y / 2);
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button)
	{
		float mx = screenX - centerX;
		float my = (Gdx.graphics.getHeight() - screenY) - centerY;
		if(mx * mx + my * my < 100 * 100)
		{
			float radiamInc = (2f * (float) Math.PI) / actions.size();
			float mouseRad = (float) Math.atan2(my, mx);
			if(mouseRad < 0)
				mouseRad = mouseRad + ((float) Math.PI * 2f);

			int index = (int) (mouseRad / radiamInc);
			actions.get(index).onClick();
			close();

			return true;
		}
		else
		{
			this.close();
		}
		return false;
	}

	public void render(float delta)
	{
		float mx = Gdx.input.getX() - centerX;
		float my = (Gdx.graphics.getHeight() - Gdx.input.getY()) - centerY;

		float mouseRad = (float) Math.atan2(my, mx);
		if(mouseRad < 0)
			mouseRad = mouseRad + ((float) Math.PI * 2f);

		float radiamInc = (2f * (float) Math.PI) / actions.size();
		float segCount = 0;
		for(Action action : actions)
		{
			float x = centerX + (float) Math.cos(segCount + (radiamInc / 2)) * 60f;
			float y = centerY + (float) Math.sin(segCount + (radiamInc / 2)) * 60f;
			Color color = Color.GRAY;

			float start = segCount;

			if(mouseRad > start && mouseRad < start + radiamInc && mx * mx + my * my < 100 * 100)
			{
				color = Color.ORANGE;
			}

			Renderer.drawArc(centerX, centerY, 100, start, radiamInc, color, true);
			Renderer.drawArc(centerX, centerY, 100, start, radiamInc, Color.BLACK, false);
			Renderer.drawStringAligned(Renderer.rust, x, y, action.getAction(), 0.25f, Align.center, Color.BLACK);

			segCount += radiamInc;
		}
	}
}
