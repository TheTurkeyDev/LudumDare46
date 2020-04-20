package com.theprogrammingturkey.ld46.rendering;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.utils.Align;

public class Renderer
{
	private static SpriteBatch batch;
	private static ShapeRenderer shape;
	private static OrthographicCamera camera;

	public static BitmapFont font;
	public static BitmapFont rust;
	private static GlyphLayout glyphLayout = new GlyphLayout();


	public static void init()
	{
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		batch = new SpriteBatch();
		shape = new ShapeRenderer();

		font = new BitmapFont(Gdx.files.internal("fonts/test.fnt"), false);
		rust = new BitmapFont(Gdx.files.internal("fonts/rust.fnt"), false);
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

	public static void draw(TextureRegion region, float x, float y, float width, float height)
	{
		if(batch.isDrawing())
		{
			batch.draw(region, x, y, width, height);
		}
	}

	public static void draw(TextureRegion region, float x, float y, float width, float height, Color tint)
	{
		if(batch.isDrawing())
		{
			Color cache = batch.getColor().cpy();
			batch.setColor(tint);
			batch.draw(region, x, y, width, height);
			batch.setColor(cache);
		}
	}

	public static void drawString(BitmapFont f, float x, float y, String str, float scale, Color color)
	{
		f.setColor(color);
		f.getData().setScale(scale);
		f.draw(batch, str, x, y);
	}

	public static float drawStringAligned(BitmapFont f, float x, float y, String str, float scale, int align, Color color)
	{
		f.getData().setScale(scale);
		glyphLayout.setText(f, str, color, 0, align, false);
		f.setColor(color);
		float drawX = x;
		if(align == Align.center)
		{
			drawX = x - (glyphLayout.width / 2);
		}
		else if(align == Align.right)
		{
			drawX = x - glyphLayout.width;
		}
		f.draw(batch, str, drawX, y + (glyphLayout.height / 2));
		return glyphLayout.width;
	}


	public static void drawStringCenteredWithBG(BitmapFont f, float x, float y, String str, float scale, Color color)
	{
		f.getData().setScale(scale);
		glyphLayout.setText(f, str, color, 0, Align.center, false);
		f.setColor(color);
		Renderer.drawRect(x - (glyphLayout.width / 2) - 30, y - (glyphLayout.height / 2) - 10, glyphLayout.width + 60, glyphLayout.height + 20, GameColors.TEXT_ALPHA_BG, true);
		f.draw(batch, str, x - (glyphLayout.width / 2), y + (glyphLayout.height / 2));
	}

	public static void drawRect(float x, float y, float width, float height, Color color, boolean filled)
	{
		drawRect(x, y, width, height, 0, color, filled);
	}

	public static void drawRect(float x, float y, float width, float height, int degrees, Color color, boolean filled)
	{
		batch.end();
		Gdx.gl.glEnable(GL20.GL_BLEND);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		if(filled)
			shape.begin(ShapeType.Filled);
		else
			shape.begin(ShapeType.Line);
		shape.setColor(color);
		shape.rect(x, y, 8, 8, width, height, 1, 1, degrees);
		shape.end();
		batch.begin();
	}

	public static void drawGradientRect(float x, float y, float width, float height, int degrees, Color colorLeft, Color colorRight, boolean filled)
	{
		batch.end();
		if(filled)
			shape.begin(ShapeType.Filled);
		else
			shape.begin(ShapeType.Line);
		shape.rect(x, y, 8, 8, width, height, 1, 1, degrees, colorLeft, colorRight, colorRight, colorLeft);
		shape.end();
		batch.begin();
	}


	public static void drawCircle(float x, float y, float radius, Color color, boolean filled)
	{
		batch.end();
		Gdx.gl.glEnable(GL20.GL_BLEND);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		if(filled)
			shape.begin(ShapeType.Filled);
		else
			shape.begin(ShapeType.Line);
		shape.setColor(color);
		shape.circle(x, y, radius, 100);
		shape.end();
		batch.begin();
	}

	public static void drawArc(float x, float y, float radius, float start, float radians, Color color, boolean filled)
	{
		batch.end();
		Gdx.gl.glEnable(GL20.GL_BLEND);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		if(filled)
		{
			shape.begin(ShapeType.Filled);
		}
		else
		{
			shape.begin(ShapeType.Line);
			Gdx.gl.glLineWidth(2f);
		}
		shape.setColor(color);
		shape.arc(x, y, radius, (float) Math.toDegrees(start), (float) Math.toDegrees(radians));
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