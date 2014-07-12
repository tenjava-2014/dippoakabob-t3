package com.tenjava.entries.dippoakabob.t3.apocalypse;

import com.tenjava.entries.dippoakabob.t3.TenJava;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.plugin.PluginManager;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * Created by dippoakabob.
 *
 *
 */
public class ApocalypseManager {

	private static final int EVENT_CHANCE = 30;

	private static List<ApocalypseEvent> events = new ArrayList<ApocalypseEvent>();
	private static List<ApocalypseEvent> staticEvents = new ArrayList<ApocalypseEvent>();

	private static int radius = 0;
	private static Location startLocation;
	private static int days;

	/**
	 * Starts timers and such for playing events.
	 */
	public static void init(){
		//days = random.nextInt(3) + 2;
		days = 0;

		new BukkitRunnable() {
			@Override
			public void run() {
				startApocalypse();
			}
		}.runTaskLater(TenJava.getInstance(), days * 24000);
	}

	public static void startApocalypse(){
		startLocation = Bukkit.getWorlds().get(0).getSpawnLocation();

		for(ApocalypseEvent staticEvent : staticEvents){
			staticEvent.play(startLocation, radius);
		}

		new BukkitRunnable(){

			@Override
			public void run() {

				if(TenJava.getRandom().nextInt(EVENT_CHANCE) == 0){
					playRandomEvent();
				}

				//Expand the apocalypse radius
				radius += 1;
			}
		}.runTaskTimer(TenJava.getInstance(), 0, 20);
	}

	private static void playRandomEvent() {
		ApocalypseEvent event = getRandomEvent();

		if(event != null){
			Bukkit.getLogger().info("Starting running the ApocalypseEvent \"" + event.getName() + "\"" );
			event.play(startLocation, radius);
		}
	}

	public static void addEvent(ApocalypseEvent event){
		if(event.isStaticEvent()){
			staticEvents.add(event);
		}else{
			events.add(event);
		}
	}

	private static ApocalypseEvent getRandomEvent() {
		int total = 0;

		for(ApocalypseEvent event : events){
			total += event.getRarity();
		}

		if(total > 0){
			int select = TenJava.getRandom().nextInt(total);

			for(ApocalypseEvent event : events){
				select -= event.getRarity();
				if(select <= 0){
					return event;
				}
			}
		}
		return null;
	}

	public static List<ApocalypseEvent> getEvents(){
		return events;
	}

	public static List<ApocalypseEvent> getStaticEvents(){
		return staticEvents;
	}

}
