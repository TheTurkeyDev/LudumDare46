package com.theprogrammingturkey.ld46.sounds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.theprogrammingturkey.ld46.game.GameCore;
import com.theprogrammingturkey.ld46.game.GameSettings;

import java.util.ArrayList;
import java.util.List;

public class SoundManager
{
	private static long bgID = -1;
	public static Sound background;
	public static List<Sound> birds = new ArrayList<>();
	public static List<Sound> snips = new ArrayList<>();
	public static Sound chop;
	public static Sound plant;
	public static Sound watering;
	public static Sound click;

	public static void initSounds()
	{
		background = Gdx.audio.newSound(Gdx.files.internal("sounds/backgroundnoise.ogg"));
		birds.add(Gdx.audio.newSound(Gdx.files.internal("sounds/bird1.ogg")));
		birds.add(Gdx.audio.newSound(Gdx.files.internal("sounds/bird2.ogg")));
		snips.add(Gdx.audio.newSound(Gdx.files.internal("sounds/snip.ogg")));
		snips.add(Gdx.audio.newSound(Gdx.files.internal("sounds/snip2.ogg")));
		chop = Gdx.audio.newSound(Gdx.files.internal("sounds/chop.ogg"));
		plant = Gdx.audio.newSound(Gdx.files.internal("sounds/plant.ogg"));
		watering = Gdx.audio.newSound(Gdx.files.internal("sounds/watering.ogg"));
		click = Gdx.audio.newSound(Gdx.files.internal("sounds/click.ogg"));
	}

	public static void startBGSound()
	{
		if(GameSettings.sounds)
		{
			bgID = background.play(GameSettings.backgroundVolume);
			background.setLooping(bgID, true);
		}
	}

	public static void stopBGSound()
	{
		if(bgID != -1)
			background.stop(bgID);
		bgID = -1;
	}

	public static void playSound(Sound sound)
	{
		if(GameSettings.sounds)
			sound.play(GameSettings.soundLevel);
	}

	public static void playRandomBird()
	{
		playSound(birds.get(GameCore.rand.nextInt(birds.size())));
	}

	public static void playRandomSnip()
	{
		playSound(snips.get(GameCore.rand.nextInt(snips.size())));
	}

	public static void disposeSounds()
	{
		background.dispose();
		for(Sound s : birds)
			s.dispose();
		for(Sound s : snips)
			s.dispose();
		chop.dispose();
		plant.dispose();
		watering.dispose();
	}
}
