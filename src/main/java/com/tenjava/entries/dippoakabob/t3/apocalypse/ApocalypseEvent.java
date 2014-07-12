package com.tenjava.entries.dippoakabob.t3.apocalypse;

import com.tenjava.entries.dippoakabob.t3.TenJava;
import org.bukkit.Location;
import org.bukkit.event.Listener;

import java.util.Random;

/**
 * Created by dippoakabob.
 *
 *
 */
public abstract class ApocalypseEvent implements Listener {

	private String name;
	private String description;

	private int rarity;

	public ApocalypseEvent(String name, String description){
		this(name, description, 0);
	}

	public ApocalypseEvent(String name, String description, int rarity){
		this.name = name;
		this.description = description;

		this.rarity = rarity;
	}

	public String getName(){
		return name;
	}

	public String getDescription(){
		return description;
	}

	public double getRarity(){
		return rarity;
	}

	public abstract void play(Location location, int radius);

}
