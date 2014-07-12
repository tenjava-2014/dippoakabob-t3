package com.tenjava.entries.dippoakabob.t3;

import com.tenjava.entries.dippoakabob.t3.apocalypse.ApocalypseManager;
import com.tenjava.entries.dippoakabob.t3.apocalypse.events.Lightning;
import com.tenjava.entries.dippoakabob.t3.apocalypse.events.Meteor;
import com.tenjava.entries.dippoakabob.t3.apocalypse.events.MobSpawning;
import com.tenjava.entries.dippoakabob.t3.apocalypse.events.Terrain;
import com.tenjava.entries.dippoakabob.t3.events.JoinLeaveListeners;
import com.tenjava.entries.dippoakabob.t3.events.WaterListener;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Random;

/**
 * created by dippoakabob
 *
 * This is the main class of the Apocalypse plugin,
 * it handles the basic setup of the different components.
 */

public class TenJava extends JavaPlugin {

	private static TenJava instance;

	private static Random random = new Random();

	/**
	 * Registers events, adds Apocalypse events, and starts timers.
	 */
	public void onEnable() {
		instance = this;

		//Register Events
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new JoinLeaveListeners(), this);
		pm.registerEvents(new WaterListener(), this);

		//Adding Apocalypse Events
		ApocalypseManager.addEvent(new Meteor());
		ApocalypseManager.addEvent(new Lightning());

		//Adding Static Apocalypse Events
		ApocalypseManager.addEvent(new Terrain(), true);
		ApocalypseManager.addEvent(new MobSpawning(), true);

		//Begin running apocalypse timers and so on
		ApocalypseManager.init();
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		//The start command: This allows for the apocalypse to be force-started
		if (label.equalsIgnoreCase("start")) {
			if (!ApocalypseManager.hasStarted()) {
				sender.sendMessage("Starting the Apocalypse!");
				ApocalypseManager.start();                                                                              //Starts the apocalypse
			} else {
				sender.sendMessage("The Apocalypse has already started.");
			}
		}
		return true;
	}

	/**
	 * Getting the Random
	 *
	 * @return an instance of Random
	 */
	public static Random getRandom() {
		return random;
	}

	/**
	 * Get instance
	 *
	 * @return Instance of TenJava plugin
	 */
	public static TenJava getInstance() {
		return instance;
	}

}
