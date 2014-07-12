package com.tenjava.entries.dippoakabob.t3.apocalypse.events;

import com.tenjava.entries.dippoakabob.t3.TenJava;
import com.tenjava.entries.dippoakabob.t3.apocalypse.ApocalypseEvent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.BlockFace;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Created by dippoakabob.
 */
public class Terrain extends ApocalypseEvent {

	private static final int FLAME_AMOUNT = 3;
	private static final int MAX_RADUIS = 200;

	public Terrain() {
		super("Terrain", "Devastate the Terrain");
	}

	@Override
	public void play(final Location location, int radius) {

		Bukkit.getLogger().info("Started the Flame event: let's hope this works.");

		new BukkitRunnable(){

			int effectRaduis = 1;

			@Override
			public void run() {
				for(int x = -effectRaduis; x <= effectRaduis; x++ ){
					for(int z = -effectRaduis; z <= effectRaduis; z++ ){
						Location loc = new Location(location.getWorld(),
								location.getX() + x,
								location.getY(),
								location.getZ() + z);

						if(Math.abs(loc.getX()) == location.getX() + effectRaduis || Math.abs(loc.getZ()) == location.getZ() + effectRaduis){
							//Light things on fire
							loc.getWorld().getHighestBlockAt(loc).getRelative(BlockFace.UP).setType(Material.FIRE);

							//Evaporate Water
							Location waterloc = loc;
							for(int y = 256; y >= 0; y++){
								waterloc.setY(y);
								if(waterloc.getBlock().getType() == Material.WATER || waterloc.getBlock().getType() == Material.STATIONARY_WATER){
									waterloc.getBlock().setType(Material.AIR);
									waterloc.getWorld().playSound(waterloc, Sound.FIZZ, 1, 1);
								}
							}
						}
					}
				}
				effectRaduis += 1;

				if(effectRaduis >= MAX_RADUIS){
					this.cancel();
				}
			}
		}.runTaskTimer(TenJava.getInstance(), 0, 20);

	}
}
