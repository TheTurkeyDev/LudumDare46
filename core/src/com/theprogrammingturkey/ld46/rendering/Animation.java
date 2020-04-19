package com.theprogrammingturkey.ld46.rendering;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Animation
{
	private int stage = 0;
	private int totalStages;
	private int stageDelay;
	private int stageDelayTick;
	private int stageDirection = 1;

	private int idelTexture = 1;
	private boolean moving = false;

	private boolean loops;

	private TextureRegion[] textures;

	private boolean running = true;

	public Animation(int delay, boolean loops, TextureRegion... textures)
	{
		this.totalStages = textures.length - 1;
		this.stageDelay = delay;

		this.loops = loops;

		this.textures = textures;
	}

	public void update()
	{
		if(this.moving || this.stage != this.idelTexture)
		{
			this.stageDelayTick++;
			if(this.stageDelayTick > this.stageDelay)
			{
				this.stage += stageDirection;
				this.stageDelayTick = 0;
				if(this.stage >= this.totalStages || this.stage <= 0)
				{
					if(this.loops)
					{
						this.stage = 0;
					}
					else
					{
						this.stage--;
						this.running = false;
					}
				}
			}
		}
	}

	public TextureRegion getCurrentTexture()
	{
		return this.textures[this.stage];
	}

	public boolean isRunning()
	{
		return this.running;
	}

	public void setMoving(boolean moving)
	{
		this.moving = moving;
	}
}