package com.theprogrammingturkey.ld46.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.theprogrammingturkey.ld46.game.GameCore;
import com.theprogrammingturkey.ld46.rendering.Renderer;
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
					return theGame.keyDown(keycode);
				return true;
			}

			@Override
			public boolean keyUp(int keycode)
			{
				if(!super.keyUp(keycode))
					return theGame.keyUp(keycode);
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
		if(currenOverlay != null)
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
		this.currenOverlay = overlay;
	}

	public Overlay getCurrentOverlay()
	{
		return currenOverlay;
	}
}