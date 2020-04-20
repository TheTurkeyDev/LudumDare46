package com.theprogrammingturkey.ld46.entity;

import com.badlogic.gdx.math.Vector2;
import com.theprogrammingturkey.ld46.entity.attributes.TempratureAttribute;
import com.theprogrammingturkey.ld46.game.GameValues;
import com.theprogrammingturkey.ld46.game.World;
import com.theprogrammingturkey.ld46.game.action.PickupAction;
import com.theprogrammingturkey.ld46.registry.ItemRegistry;
import com.theprogrammingturkey.ld46.rendering.GameColors;
import com.theprogrammingturkey.ld46.rendering.Renderer;
import com.theprogrammingturkey.ld46.rendering.WrapperTR;
import com.theprogrammingturkey.ld46.screen.GameScreen;
import com.theprogrammingturkey.ld46.screen.overlay.ActionsOverlay;

public class HeatLamp extends Entity
{
	public HeatLamp(World world, Vector2 location)
	{
		super(world, location, new Vector2(32, 32));
		super.addTexture(new WrapperTR(15 * 64, 128));
	}

	@Override
	public void render(float delta)
	{
		super.render(delta);
		float x = this.location.x + (this.size.x / 2);
		float y = this.location.y + (this.size.y / 2);
		Renderer.drawCircle(x, y, GameValues.HEAT_LAMP_RADIUS, GameColors.HEAT, true);
		//Renderer.drawCircle(x, y, GameValues.HEAT_LAMP_RADIUS, GameColors.HEAT_NO_ALPHA, false);
	}

	@Override
	public void update()
	{
		super.update();
		for(Plant plant : world.getPlantList())
		{
			if(plant.getLocation().dst(this.location) < GameValues.HEAT_LAMP_RADIUS)
			{
				TempratureAttribute tempAttrib = plant.getTempratureAttribute();
				tempAttrib.setCurrentValue(tempAttrib.getCurrentValue() + 15);
			}
		}
	}

	@Override
	public void onClick(GameScreen screen, Player player)
	{
		screen.setCurrentOverlay(new ActionsOverlay(this, screen, null,
				new PickupAction(this, player, ItemRegistry.getItemStack("heat_lamp"))
		));
	}
}
