package com.theprogrammingturkey.ld46.entity;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.theprogrammingturkey.ld46.entity.attributes.LifePointsAttribute;
import com.theprogrammingturkey.ld46.entity.attributes.LightAttribute;
import com.theprogrammingturkey.ld46.entity.attributes.NutrientAttribute;
import com.theprogrammingturkey.ld46.entity.attributes.TempratureAttribute;
import com.theprogrammingturkey.ld46.entity.attributes.WaterAttribute;
import com.theprogrammingturkey.ld46.game.GameCore;
import com.theprogrammingturkey.ld46.game.GameValues;
import com.theprogrammingturkey.ld46.game.World;
import com.theprogrammingturkey.ld46.item.ItemStack;
import com.theprogrammingturkey.ld46.rendering.Renderer;
import com.theprogrammingturkey.ld46.rendering.WrapperTR;

import java.util.ArrayList;
import java.util.List;

public class Plant extends Entity
{
	private LifePointsAttribute lifePointsAttribute;
	private LightAttribute lightAttribute;
	private TempratureAttribute tempratureAttribute;
	private WaterAttribute waterAttribute;
	private List<NutrientAttribute> nutrientAttributes = new ArrayList<>();

	private String displayName = "";

	private float sizeScalar = 1f;
	private float growthRatePow = 0.5f;
	private float growthRate = 0.0005f;

	private float currentLightValue = 0;

	public Plant(World world, Vector2 location)
	{
		super(world, location, new Vector2(32, 32));
	}

	public void setDisplayName(String name)
	{
		this.displayName = name;
	}

	public String getDisplayName()
	{
		return displayName;
	}

	public float getGrowthRate()
	{
		return growthRate;
	}

	public void setGrowthRate(float growthRate)
	{
		this.growthRate = growthRate;
	}

	public void setGrowthRatePow(float pow)
	{
		this.growthRatePow = pow;
	}

	@Override
	public void update()
	{
		super.update();

		this.lifePointsAttribute.increaseMaxLP(growthRate);
		this.updateSize();

		currentLightValue = world.getCurrentLight();

		lifePointsAttribute.update(world, this);
		lightAttribute.update(world, this);
		tempratureAttribute.update(world, this);
		waterAttribute.update(world, this);
		for(NutrientAttribute attribute : nutrientAttributes)
		{
			attribute.update(world, this);
		}
	}

	public void renderPreview(float delta, float x, float y, float width, float height)
	{
		for(WrapperTR region : regions)
		{
			if(region.hasTint())
			{
				Renderer.draw(region.getRegion(), x, y, width, height, region.getTint());
			}
			else
			{
				Renderer.draw(region.getRegion(), x, y, width, height);
			}

		}
	}

	@Override
	public void render(float delta)
	{
		for(WrapperTR region : regions)
		{
			if(region.hasTint())
			{
				Renderer.draw(region.getRegion(), location.x - (size.x / 2), location.y, size.x, size.y, region.getTint());
			}
			else
			{
				Renderer.draw(region.getRegion(), location.x - (size.x / 2), location.y, size.x, size.y);
			}
		}
	}

	public void updateSize()
	{
		this.setSize(this.getSizeForMaxLP());
	}

	public void setSize(float size)
	{
		this.size.set(size, size);
	}

	public void setSizeScalar(float scalar)
	{
		this.sizeScalar = scalar;
	}

	public float getSizeScalar()
	{
		return this.sizeScalar;
	}

	public ItemStack getSaplingItem()
	{
		return ItemStack.EMPTY;
	}

	public LifePointsAttribute getLifePointsAttribute()
	{
		return lifePointsAttribute;
	}

	public void setLifePointsAttribute(LifePointsAttribute lifePointsAttribute)
	{
		this.lifePointsAttribute = lifePointsAttribute;
		this.updateSize();
	}

	public LightAttribute getLightAttribute()
	{
		return lightAttribute;
	}

	public void setLightAttribute(LightAttribute lightAttribute)
	{
		this.lightAttribute = lightAttribute;
	}

	public TempratureAttribute getTempratureAttribute()
	{
		return tempratureAttribute;
	}

	public void setTempratureAttribute(TempratureAttribute tempratureAttribute)
	{
		this.tempratureAttribute = tempratureAttribute;
	}

	public WaterAttribute getWaterAttribute()
	{
		return waterAttribute;
	}

	public void setWaterAttribute(WaterAttribute waterAttribute)
	{
		this.waterAttribute = waterAttribute;
	}

	public List<NutrientAttribute> getNutrientAttributes()
	{
		return nutrientAttributes;
	}

	public void addNutrientAttributes(NutrientAttribute nutrientAttribute)
	{
		this.nutrientAttributes.add(nutrientAttribute);
	}

	@Override
	public Rectangle getBoundingBox()
	{
		return new Rectangle(location.x - (size.x / 2), location.y, size.x, size.y);
	}

	public float getCurrentLightValue()
	{
		return this.currentLightValue;
	}

	public void trim()
	{
		this.getLifePointsAttribute().setMaxValue(this.getLifePointsAttribute().getMaxValue() - GameValues.TRIM_COST);
		this.getLifePointsAttribute().setCurrentValue(this.getLifePointsAttribute().getCurrentValue() - GameValues.TRIM_COST);

		float currentHealth = this.getLifePointsAttribute().getCurrentValue();
		float maxHealth = this.getLifePointsAttribute().getMaxValue();

		if(currentHealth < 20)
		{
			if(currentHealth <= 0 || GameCore.rand.nextInt((int) maxHealth) == 0)
			{
				this.kill();
				return;
			}
		}

		if(maxHealth < currentHealth)
			this.getLifePointsAttribute().setCurrentValue(maxHealth);

		this.updateSize();
	}

	public List<ItemStack> getHarvests()
	{
		return new ArrayList<>();
	}

	public List<ItemStack> getDrops()
	{
		return new ArrayList<>();
	}

	@Override
	public void kill()
	{
		super.kill();
	}

	public float getSizeForMaxLP()
	{
		return (float) (Math.pow(this.lifePointsAttribute.getMaxValue(), growthRatePow) * this.sizeScalar);
	}
}
