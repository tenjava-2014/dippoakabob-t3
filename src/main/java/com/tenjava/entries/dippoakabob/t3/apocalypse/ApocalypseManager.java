package com.tenjava.entries.dippoakabob.t3.apocalypse;

import com.tenjava.entries.dippoakabob.t3.TenJava;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
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

	private static int radius = 1;
	private static Location startLocation;
	private static int days;
	private static long startingTicks;
	private static long ticksElapsed = 0;

	private static boolean started = false;

	/**
	 * Starts timers and such for playing events.
	 */
	public static void init(){

		//days = random.nextInt(3) + 2;
		//startingTicks = days * 24000;

		startingTicks = 400;

		new BukkitRunnable() {

			@Override
			public void run() {
				ticksElapsed += 20;
				long remaining = startingTicks - ticksElapsed;

				if(remaining <= 0 || started){
					startApocalypse();
					this.cancel();
				}else if((remaining <= 100 && remaining % 20 == 0) //5 second
						|| remaining == 200 //10 second
						|| remaining == 300){ //15 second
					Bukkit.broadcastMessage(ChatColor.RED + "The apocalypse is starting in " + (remaining/20) + " second" + ((remaining/20 == 1) ? "":"s" + "!"));
				}

			}

		}.runTaskTimer(TenJava.getInstance(), 0, 20);
	}

	public static void startApocalypse(){
		startLocation = generateStartLocation();

		started = true;

		TenJava.getInstance().getConfig().set("apocalypse.started", true);
		TenJava.getInstance().saveConfig();

		Bukkit.broadcastMessage(ChatColor.DARK_RED + "Starting the Apocalypse!");
		Bukkit.broadcastMessage(ChatColor.RED + "Starting at " + startLocation.getX() + "x " + startLocation.getZ() + "z");

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
		addEvent(event, false);
	}

	public static void addEvent(ApocalypseEvent event, boolean isStatic){
		if(isStatic){
			staticEvents.add(event);
		}else{
			events.add(event);
		}

		Bukkit.getPluginManager().registerEvents(event, TenJava.getInstance());
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

	public static Location generateStartLocation(){
		Location loc;

		if(Bukkit.getOnlinePlayers().length > 0){
			loc = Bukkit.getOnlinePlayers()[TenJava.getRandom().nextInt(Bukkit.getOnlinePlayers().length)].getLocation();

		}else{
			loc = Bukkit.getWorlds().get(0).getSpawnLocation();
		}
		return loc;
	}

	public static Long getTicksRemaining(){
		return startingTicks - ticksElapsed;
	}

	public static List<ApocalypseEvent> getEvents(){
		return events;
	}

	public static List<ApocalypseEvent> getStaticEvents(){
		return staticEvents;
	}

	public static long getStartingTicks(){
		return startingTicks;
	}

	public static boolean hasStarted(){
		return started;
	}

}
