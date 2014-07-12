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

	/**
	 * Create an ApocalypseEvent
	 * @param name of the event
	 * @param description of the event
	 */
	public ApocalypseEvent(String name, String description){
		this(name, description, 0);
	}

	/**
	 * Create an ApocalypseEvent
	 * @param name of the event
	 * @param description of the event
	 * @param rarity of the event
	 */
	public ApocalypseEvent(String name, String description, int rarity){
		this.name = name;
		this.description = description;

		this.rarity = rarity;
	}

	/**
	 * Name
	 * @return name of the event
	 */
	public String getName(){
		return name;
	}

	/**
	 * description
	 * @return the event's description
	 */
	public String getDescription(){
		return description;
	}

	/**
	 *
	 * @return rarity of the event
	 */
	public double getRarity(){
		return rarity;
	}

	public abstract void play(Location location, int radius);

}
