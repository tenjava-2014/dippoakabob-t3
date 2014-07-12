package com.tenjava.entries.dippoakabob.t3.apocalypse.events;

import com.tenjava.entries.dippoakabob.t3.TenJava;
import com.tenjava.entries.dippoakabob.t3.apocalypse.ApocalypseEvent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Created by dippoakabob.
 */
public class Flames extends ApocalypseEvent {

	private static final int FLAME_AMOUNT = 10;

	public Flames() {
		super("Fire", "Start burning the world");
	}

	@Override
	public void play(final Location location, int radius) {

		Bukkit.getLogger().info("Started the Flame event: let's hope this works.");

		new BukkitRunnable(){

			int flameRaduis = 1;

			@Override
			public void run() {
				for(int x = -flameRaduis; x < flameRaduis; x++ ){
					for(int z = -flameRaduis; z < flameRaduis; z++ ){
						Location loc = new Location(location.getWorld(),
								location.getX() + x,
								location.getY(),
								location.getZ() + z);

						if(TenJava.getRandom().nextInt(FLAME_AMOUNT) == 0){
							loc.getWorld().getHighestBlockAt(loc).getRelative(BlockFace.UP).setType(Material.FIRE);
						}
					}
				}
				flameRaduis += 1;
			}
		}.runTaskTimer(TenJava.getInstance(), 0, 20);

	}
}
