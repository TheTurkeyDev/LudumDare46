package com.theprogrammingturkey.ld46.entity;

public enum PlantType
{
	TREE("tree"),
	FLOWER("flower"),
	BUSH("bush");

	private String name;

	PlantType(String name)
	{
		this.name = name;
	}

	public String getName()
	{
		return name;
	}
}
