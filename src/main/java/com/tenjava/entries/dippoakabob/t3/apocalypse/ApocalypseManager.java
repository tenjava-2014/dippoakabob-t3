package com.tenjava.entries.dippoakabob.t3.apocalypse;

import com.tenjava.entries.dippoakabob.t3.TenJava;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dippoakabob.
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

	private static boolean hasJoined = false;

	/**
	 * Begins timers and initializes variables used for the Apocalypse
	 */
	public static void init() {

		days = 1;
		startingTicks = days * 24000;

		new BukkitRunnable() {

			@Override
			public void run() {
				if (hasJoined) {
					ticksElapsed += 20;
					long remaining = startingTicks - ticksElapsed;

					if (remaining <= 0 || started) {
						startApocalypse();
						this.cancel();
					} else if ((remaining <= 100 && remaining % 20 == 0) //5 second
							|| remaining == 200 //10 second
							|| remaining == 300){ //15 second
						Bukkit.broadcastMessage(ChatColor.RED + "The apocalypse is starting in " + (remaining / 20) + " second" + ((remaining / 20 == 1) ? "" : "s" + "!"));
					}
				}
			}

		}.runTaskTimer(TenJava.getInstance(), 0, 20);
	}

	/**
	 * Starts the Apocalypse
	 */
	private static void startApocalypse() {
		startLocation = generateStartLocation();

		started = true;

		TenJava.getInstance().getConfig().set("apocalypse.started", true);
		TenJava.getInstance().saveConfig();

		Bukkit.broadcastMessage(ChatColor.RED + "The Apocalypse is starting!\n" +
				ChatColor.GRAY + "Try your best to survive! Be careful!");

		for (ApocalypseEvent staticEvent : staticEvents) {
			staticEvent.play(startLocation, radius);
		}

		new BukkitRunnable() {

			@Override
			public void run() {

				if (TenJava.getRandom().nextInt(EVENT_CHANCE) == 0) {
					playRandomEvent();
				}

				//Expand the apocalypse radius
				radius += 1;
			}
		}.runTaskTimer(TenJava.getInstance(), 0, 20);
	}

	/**
	 * Starts a random event in the Apocalypse
	 */
	private static void playRandomEvent() {
		ApocalypseEvent event = getRandomEvent();

		if (event != null) {
			Bukkit.getLogger().info("Starting running the ApocalypseEvent \"" + event.getName() + "\"");
			event.play(startLocation, radius);
		}
	}

	/**
	 * Adds an event to the list of events.
	 *
	 * @param event the event that should be added
	 */
	public static void addEvent(ApocalypseEvent event) {
		addEvent(event, false);
	}

	/**
	 * Adds and event to the list of events
	 *
	 * @param event the event that should be added
	 * @param isStatic whether or not the event is static
	 */
	public static void addEvent(ApocalypseEvent event, boolean isStatic) {
		if (isStatic) {
			staticEvents.add(event);
		} else {
			events.add(event);
		}

		Bukkit.getPluginManager().registerEvents(event, TenJava.getInstance());
	}

	/**
	 * Get a Random ApocalypseEvent
	 *
	 * @return a random (non-static) ApocalypseEvent
	 */
	private static ApocalypseEvent getRandomEvent() {
		int total = 0;

		for (ApocalypseEvent event : events) {
			total += event.getRarity();
		}

		if (total > 0) {
			int select = TenJava.getRandom().nextInt(total);

			for (ApocalypseEvent event : events) {
				select -= event.getRarity();
				if (select <= 0) {
					return event;
				}
			}
		}
		return null;
	}

	/**
	 * Create an apocalypse start location
	 *
	 * @return a location where the Apocalypse should start
	 */
	public static Location generateStartLocation() {
		Location loc;

		if (Bukkit.getOnlinePlayers().length > 0) {
			loc = Bukkit.getOnlinePlayers()[TenJava.getRandom().nextInt(Bukkit.getOnlinePlayers().length)].getLocation();

		} else {
			loc = Bukkit.getWorlds().get(0).getSpawnLocation();
		}
		return loc.getBlock().getLocation();
	}

	/**
	 * Get ticks remaining until the Apocalypse starts
	 *
	 * @return the amount of ticks until the Apocalypse starts
	 */
	public static Long getTicksRemaining() {
		return startingTicks - ticksElapsed;
	}

	/**
	 * Get ApocalypseEvents
	 *
	 * @return a list of non-static events that will be used throughout the Apocalypse
	 */
	public static List<ApocalypseEvent> getEvents() {
		return events;
	}

	/**
	 * Get Static ApocalypseEvents
	 *
	 * @return a list of the static Apcoalypse events, ran at the start of the Apocalypse
	 */
	public static List<ApocalypseEvent> getStaticEvents() {
		return staticEvents;
	}

	/**
	 * Get the total ticks needed to start the Apcoalypse
	 * @return The amount of ticks that the server will start the apocalypse in.
	 */
	public static long getStartingTicks() {
		return startingTicks;
	}

	/**
	 * Starts the Apocalypse
	 */
	public static void start(){
		started = true;
	}

	/**
	 * Has started method
	 * @return whether or not the Apocalypse has started.
	 */
	public static boolean hasStarted() {
		return started;
	}

	/**
	 * A method callled when a player joins, used for delaying the start of the apocalypse
	 */
	public static void playerJoined() {
		hasJoined = true;
	}

	/**
	 * Get radius
	 * @return the current radius of the apocalypse
	 */
	public static int getRadius() {
		return radius;
	}

}
