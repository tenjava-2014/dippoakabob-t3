package com.tenjava.entries.dippoakabob.t3.apocalypse.events;

import com.tenjava.entries.dippoakabob.t3.TenJava;
import com.tenjava.entries.dippoakabob.t3.apocalypse.ApocalypseEvent;
import com.tenjava.entries.dippoakabob.t3.apocalypse.ApocalypseManager;
import org.bukkit.Location;
import org.bukkit.entity.CreatureType;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Entity;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.rmi.server.Skeleton;

/**
 * Created by dippoakabob.
 */
public class MobSpawning extends ApocalypseEvent {

	private static final int MOB_CHANCE = 50;

	public MobSpawning() {
		super("Mob Spawning", "Handles extra mob spawning.");
	}

	@Override
	public void play(final Location location, int radius) {

		new BukkitRunnable() {

			CreatureType[] types = {CreatureType.CAVE_SPIDER, CreatureType.MAGMA_CUBE, CreatureType.SKELETON, CreatureType.ZOMBIE, CreatureType.PIG_ZOMBIE};

			@Override
			public void run() {
				int radius = ApocalypseManager.getRadius();
				for(int x = -radius; x <= radius; x++) {
					for (int z = -radius; z <= radius; z++) {
						Location mobLoc = location.clone().add(x, 0, z);

						mobLoc.getWorld().spawnCreature(mobLoc, types[TenJava.getRandom().nextInt(types.length)]);

					}
				}
			}
		}.runTaskTimer(TenJava.getInstance(), 0L, 20L);

	}
}
