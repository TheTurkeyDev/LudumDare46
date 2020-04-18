package com.theprogrammingturkey.ld46.rendering;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class Renderer
{

	private static SpriteBatch batch;
	private static ShapeRenderer shape;
	private static OrthographicCamera camera;

	public static BitmapFont font;

	public static void init()
	{
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		batch = new SpriteBatch();
		shape = new ShapeRenderer();

		font = new BitmapFont(Gdx.files.internal("fonts/test.fnt"), false);
		//font = new BitmapFont();
	}

	public static void update()
	{
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		shape.setProjectionMatrix(camera.combined);
	}

	public static void begin()
	{
		batch.begin();
	}

	public static void end()
	{
		batch.end();
	}

	public static void resize(int width, int height)
	{
		camera.setToOrtho(false, width, height);
	}

	public static void draw(Renderable renderable, float delta)
	{
		if(batch.isDrawing())
		{
			renderable.getSprite(delta).draw(batch);
		}
	}

	public static void drawString(BitmapFont f, float x, float y, String str, float scale, Color color)
	{
		f.setColor(color);
		f.getData().setScale(scale);
		f.draw(batch, str, x, y);
	}

	public static void drawRect(float x, float y, float width, float height, Color color, boolean filled)
	{
		drawRect(x, y, width, height, 0, color, filled);
	}

	public static void drawRect(float x, float y, float width, float height, int degrees, Color color, boolean filled)
	{
		batch.end();
		if(filled)
			shape.begin(ShapeType.Filled);
		else
			shape.begin(ShapeType.Line);
		shape.setColor(color);
		shape.rect(x, y, 8, 8, width, height, 1, 1, degrees);
		shape.end();
		batch.begin();
	}

	public static void drawLine(float x, float y, float x2, float y2, Color color)
	{
		batch.end();
		shape.begin(ShapeType.Line);
		shape.setColor(color);
		shape.line(x, y, x2, y2);
		shape.end();
		batch.begin();
	}

	public static void dispose()
	{
		batch.dispose();
		shape.dispose();
		font.dispose();
	}
}