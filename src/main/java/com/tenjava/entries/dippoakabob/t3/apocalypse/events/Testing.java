package com.tenjava.entries.dippoakabob.t3.apocalypse.events;

import com.tenjava.entries.dippoakabob.t3.apocalypse.ApocalypseEvent;
import org.bukkit.Bukkit;
import org.bukkit.Location;

/**
 * Created by dippoakabob.
 */
public class Testing extends ApocalypseEvent {

	public Testing(){
		super("Test", "It's a test, what more do you want from me?", 5);
	}

	@Override
	public void play(Location location, int radius) {
		Bukkit.broadcastMessage("THIS IS A CONFIRMATION THAT THE THINGY WORKDDDDDDd");
	}

}
