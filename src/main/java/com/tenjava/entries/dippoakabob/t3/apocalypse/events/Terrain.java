package com.tenjava.entries.dippoakabob.t3.apocalypse.events;

import com.tenjava.entries.dippoakabob.t3.TenJava;
import com.tenjava.entries.dippoakabob.t3.apocalypse.ApocalypseEvent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Created by dippoakabob.
 */
public class Terrain extends ApocalypseEvent {

	private static final int FLAME_AMOUNT = 12;
	private static final int BORDER_RADIUS_CHANCE = 1;
	private static final int GRASS_RADIUS_CHANCE = 3;
	private static final int GRASS_CHANCE = 10;

	private static final int MAX_RADUIS = 200;

	public Terrain() {
		super("Terrain", "Devastate the Terrain");
	}

	@Override
	public void play(final Location location, int radius) {

		Bukkit.getLogger().info("Started the Flame event: let's hope this works.");

		new BukkitRunnable(){

			int borderRadius = 1;
			int grassRadius = 1;

			@Override
			public void run() {
				//Looping through a boarder
				if(borderRadius <= MAX_RADUIS){
					for(int x = -borderRadius; x <= borderRadius; x++) {
						for (int z = -borderRadius; z <= borderRadius; z++) {
							Location loc = location.clone().add(x, 0, z);

							//Check blocks around boarder
							if (Math.abs(loc.getX()) == Math.abs(location.getX() + borderRadius) || Math.abs(loc.getZ()) == Math.abs(location.getZ() + borderRadius)) {

								// /Light things on fire
								if(TenJava.getRandom().nextInt(FLAME_AMOUNT) == 0){
									loc.getWorld().getHighestBlockAt(loc).getRelative(BlockFace.UP).setType(Material.FIRE);
								}

								//Evaporate Water
								Location waterloc = loc;

								for (int y = 256; y >= 0; y--) {
									waterloc.setY(y);

									if (waterloc.getBlock().getType() == Material.WATER || waterloc.getBlock().getType() == Material.STATIONARY_WATER) {
										waterloc.getBlock().setType(Material.SAND);
										waterloc.getBlock().setData((byte)(TenJava.getRandom().nextBoolean() ? 0:1));
									}
								}
							}
						}
					}
					if(TenJava.getRandom().nextInt(BORDER_RADIUS_CHANCE) == 0){
						borderRadius += 1;
					}
				}

				//Loop through blocks slower to change top terrain type
				if(grassRadius <= MAX_RADUIS){

					for(int x = -grassRadius; x <= grassRadius; x++) {
						for (int z = -grassRadius; z <= grassRadius; z++) {
							for (int y = 0; y <= 256; y++) {
								Location loc = new Location(location.getWorld(),
									location.getX() + x,
									y,
									location.getZ() + z);

								Block block = loc.getBlock();

								if(block.getType() == Material.GRASS){

									//Don't change all the blocks, just a random amount
									if(TenJava.getRandom().nextInt(GRASS_CHANCE) == 0){
										block.setType(Material.DIRT);

										if(TenJava.getRandom().nextBoolean()){
											block.setData((byte) 0);
										}else{
											block.setData((byte) 2);
										}
									}
								}
							}
						}
					}
					if(TenJava.getRandom().nextInt(GRASS_RADIUS_CHANCE) == 0){
						grassRadius += 2;
					}
				}

			}
		}.runTaskTimer(TenJava.getInstance(), 0, 20);

	}
}
