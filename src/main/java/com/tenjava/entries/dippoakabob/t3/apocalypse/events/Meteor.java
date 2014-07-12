package com.tenjava.entries.dippoakabob.t3.apocalypse.events;

import com.tenjava.entries.dippoakabob.t3.TenJava;
import com.tenjava.entries.dippoakabob.t3.apocalypse.ApocalypseEvent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * Created by dippoakabob.
 */
public class Meteor extends ApocalypseEvent {

	private static final int RANDOM_SPAWN_RADIUS = 15;
	private static final int METEOR_RADIUS = 3;

	Random random = TenJava.getRandom();

	HashMap<String, ArrayList<FallingBlock>> blocks = new HashMap<String, ArrayList<FallingBlock>>();

	public Meteor() {
		super("Meteor", "It starts raining flaming meteors!", 1);
	}

	@Override
	public void play(Location location, int radius) {

		for(Player player : Bukkit.getOnlinePlayers()){

			Location loc = player.getLocation();

			int randX = random.nextInt(RANDOM_SPAWN_RADIUS * 2) - RANDOM_SPAWN_RADIUS;
			int randZ = random.nextInt(RANDOM_SPAWN_RADIUS * 2) - RANDOM_SPAWN_RADIUS;

			loc.add(randX, 0, randZ);
			loc.setY(256);

			spawnMeteor(loc, player.getName());

		}

	}

	private void spawnMeteor(Location loc, String name) {

		double vecX = (random.nextDouble() * 2D - 1D);
		double vecY = (random.nextDouble() * 2D - 1D);
		double vecZ = (random.nextDouble() * 2D - 1D);

		Vector vector = new Vector(vecX, vecY, vecZ).normalize();



		for(int x = -METEOR_RADIUS; x <= METEOR_RADIUS; x++){
			for(int y = -METEOR_RADIUS; y <= METEOR_RADIUS; y++){
				for(int z = -METEOR_RADIUS; z <= METEOR_RADIUS; z++){
					Location blockLoc = loc.clone().add(x, y, z);

					//Check Circle
					if(blockLoc.distanceSquared(loc) <= METEOR_RADIUS * METEOR_RADIUS){
						FallingBlock block = loc.getWorld().spawnFallingBlock(blockLoc, (random.nextBoolean() ? Material.NETHERRACK : Material.SOUL_SAND), (byte) 0);
						block.setVelocity(vector);
						block.setDropItem(false);
						block.setFireTicks(Integer.MAX_VALUE);
						if(!blocks.containsKey(name)){
							blocks.put(name, new ArrayList<FallingBlock>());
						}
						blocks.get(name).add(block);
					}
				}
			}
		}
	}

	@EventHandler
	public void onMeteorHit(EntityChangeBlockEvent event){
		if(event.getEntity().getType() == EntityType.FALLING_BLOCK){
			event.setCancelled(true);

			if(event.getBlock().getType() == Material.AIR){
				event.getBlock().getLocation().getWorld().createExplosion(event.getBlock().getLocation(), 5F, true);

				for(String name : blocks.keySet()){
					if(blocks.get(name).contains(event.getEntity())){
						for(FallingBlock block : blocks.get(name)){
							block.remove();
						}
					}
				}
			}

		}
	}

}
