package com.theprogrammingturkey.ld46.entity;

import com.badlogic.gdx.math.Vector2;
import com.theprogrammingturkey.ld46.entity.attributes.Attribute;
import com.theprogrammingturkey.ld46.entity.attributes.LifePointsAttribute;
import com.theprogrammingturkey.ld46.entity.attributes.LightAttribute;
import com.theprogrammingturkey.ld46.entity.attributes.NutrientAttribute;
import com.theprogrammingturkey.ld46.entity.attributes.TempratureAttribute;
import com.theprogrammingturkey.ld46.entity.attributes.WaterAttribute;

import java.util.ArrayList;
import java.util.List;

public class Plant extends Entity
{
	private List<Attribute> attributes = new ArrayList<>();

	private String displayName = "";

	public Plant(Vector2 location)
	{
		super(location, new Vector2(32, 32));
	}

	public void setDisplayName(String name)
	{
		this.displayName = name;
	}

	@Override
	public void update()
	{
		super.update();

		for(Attribute attribute : attributes)
		{
			attribute.update(this);
		}
	}

	public void addAttribute(Attribute attribute)
	{
		this.attributes.add(attribute);
	}
}
