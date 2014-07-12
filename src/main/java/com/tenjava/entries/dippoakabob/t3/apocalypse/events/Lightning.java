package com.tenjava.entries.dippoakabob.t3.apocalypse.events;

import com.tenjava.entries.dippoakabob.t3.TenJava;
import com.tenjava.entries.dippoakabob.t3.apocalypse.ApocalypseEvent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.Random;

/**
 * Created by dippoakabob.
 */
public class Lightning extends ApocalypseEvent {

	private static int PLAYER_RADIUS = 10;

	Random random = TenJava.getRandom();

	public Lightning() {
		super("Lightning", "Strikes lightning near players.", 10);
	}

	@Override
	public void play(Location location, int radius) {
		for(Player player : Bukkit.getOnlinePlayers()){

			Location loc = player.getLocation().clone().add(random.nextInt(PLAYER_RADIUS*2) - PLAYER_RADIUS, 0, random.nextInt(PLAYER_RADIUS*2) - PLAYER_RADIUS);

			loc.getWorld().strikeLightning(loc.getWorld().getHighestBlockAt(loc).getLocation());

		}
	}

}
