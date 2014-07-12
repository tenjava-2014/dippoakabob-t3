package com.tenjava.entries.dippoakabob.t3.apocalypse.events;

import com.tenjava.entries.dippoakabob.t3.TenJava;
import com.tenjava.entries.dippoakabob.t3.apocalypse.ApocalypseEvent;
import com.tenjava.entries.dippoakabob.t3.apocalypse.ApocalypseManager;
import org.bukkit.Location;
import org.bukkit.entity.*;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Created by dippoakabob.
 */
public class MobSpawning extends ApocalypseEvent {

	private static final int MOB_CHANCE = 800;

	public MobSpawning() {
		super("Mob Spawning", "Handles extra mob spawning.");
	}

	@Override
	public void play(final Location location, int radius) {

		new BukkitRunnable() {

			Class<? extends Entity>[] types = new Class[]{Skeleton.class, Creeper.class, Zombie.class, PigZombie.class};

			@Override
			public void run() {
				int radius = ApocalypseManager.getRadius();
				for(int x = -radius; x <= radius; x++) {
					for (int z = -radius; z <= radius; z++) {
						if(TenJava.getRandom().nextInt(MOB_CHANCE) == 0){
							Location mobLoc = location.clone().add(x, 0, z);

							mobLoc.getWorld().spawn(mobLoc, types[TenJava.getRandom().nextInt(types.length)]);
						}
					}
				}
			}
		}.runTaskTimer(TenJava.getInstance(), 0L, 400L);

	}
}
