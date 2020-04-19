package com.theprogrammingturkey.ld46.screen.overlay;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Align;
import com.theprogrammingturkey.ld46.entity.Plant;
import com.theprogrammingturkey.ld46.entity.attributes.NutrientAttribute;
import com.theprogrammingturkey.ld46.rendering.Renderer;
import com.theprogrammingturkey.ld46.rendering.Textures;
import com.theprogrammingturkey.ld46.screen.GameScreen;

public class PlantStatusOverlay extends Overlay
{
	private Plant plant;

	private int x;
	private int y;
	private float width;
	private float height;
	private float top;

	private float arrowBounce = 0;
	private float arrowBounceInc = -1.5f;

	public PlantStatusOverlay(Plant p, GameScreen screen, Overlay parent)
	{
		super(screen, parent);
		this.plant = p;
		Rectangle rect = plant.getBoundingBox();
		width = (Gdx.graphics.getWidth() / 2f) - 10;
		height = (Gdx.graphics.getHeight() / 2f) - 10;

		if(rect.x < Gdx.graphics.getWidth() / 2f)
			x = (int) rect.getX() + 62;
		else
			x = (int) (rect.getX() - (30 + width));

		if(rect.y < Gdx.graphics.getHeight() / 2f)
			y = (int) rect.getY() + 62;
		else
			y = (int) (rect.getY() - (30 + height));


		top = height + y;
	}

	public void render(float delta)
	{
		Rectangle plantBB = plant.getBoundingBox();
		Renderer.draw(Textures.arrow, plantBB.x + (plantBB.width / 2) - 32, arrowBounce + plantBB.y + plantBB.height, 64, 64);

		arrowBounce += arrowBounceInc;
		if(arrowBounce > 20 || arrowBounce < -20)
			arrowBounceInc *= -1;

		Renderer.drawRect(x, y, width, height, Color.LIGHT_GRAY, true);
		float textWidth = Renderer.drawStringAligned(Renderer.font, x + (width / 2f), top - 30, plant.getDisplayName().toUpperCase(), 2f, Align.center, Color.BLACK);

		plant.renderPreview(delta, x + (width / 2f) - (textWidth / 2) - 82, top - 70, 64, 64);
		plant.renderPreview(delta, x + (width / 2f) + (textWidth / 2) + 20, top - 70, 64, 64);

		float yInc = top - 90;
		plant.getLifePointsAttribute().renderAsInfoGraphic(delta, x + 30, (int) yInc);
		yInc -= 50;
		plant.getWaterAttribute().renderAsInfoGraphic(delta, x + 30, (int) yInc);
		yInc -= 50;
		plant.getLightAttribute().renderAsInfoGraphic(delta, x + 30, (int) yInc);
		yInc -= 50;
		plant.getTempratureAttribute().renderAsInfoGraphic(delta, x + 30, (int) yInc);

		for(NutrientAttribute nutrientAttribute : plant.getNutrientAttributes())
		{
			yInc -= 50;
			nutrientAttribute.renderAsInfoGraphic(delta, x + 30, (int) yInc);
		}
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
		Rectangle rect = new Rectangle(x, y, width, height);
		if(!rect.contains(screenX, Gdx.graphics.getHeight() - screenY))
			close();

		return true;
	}

}
