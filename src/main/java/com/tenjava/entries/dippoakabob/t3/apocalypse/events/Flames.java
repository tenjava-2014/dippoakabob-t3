package com.tenjava.entries.dippoakabob.t3.apocalypse.events;

import com.tenjava.entries.dippoakabob.t3.TenJava;
import com.tenjava.entries.dippoakabob.t3.apocalypse.ApocalypseEvent;
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
	public void play(final Location location, final int radius) {

		new BukkitRunnable(){
			@Override
			public void run() {
				for(int x = -radius; x < radius; x++ ){
					for(int z = -radius; z < radius; z++ ){
						Location loc = new Location(location.getWorld(),
								location.getX() + x,
								location.getY(),
								location.getZ() + z);

						if(TenJava.getRandom().nextInt(FLAME_AMOUNT) == 0){
							loc.getWorld().getHighestBlockAt(loc).getRelative(BlockFace.UP).setType(Material.FIRE);
						}
					}
				}
			}
		}.runTaskLater(TenJava.getInstance(), 20);

	}
}
