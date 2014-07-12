package com.tenjava.entries.dippoakabob.t3;

import com.tenjava.entries.dippoakabob.t3.apocalypse.ApocalypseManager;
import com.tenjava.entries.dippoakabob.t3.apocalypse.commands.StartCommand;
import com.tenjava.entries.dippoakabob.t3.apocalypse.events.Lightning;
import com.tenjava.entries.dippoakabob.t3.apocalypse.events.Meteor;
import com.tenjava.entries.dippoakabob.t3.apocalypse.events.Terrain;
import com.tenjava.entries.dippoakabob.t3.events.JoinLeaveListeners;
import com.tenjava.entries.dippoakabob.t3.events.WaterListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Random;

public class TenJava extends JavaPlugin {

	private static TenJava instance;

	private static Random random = new Random();

	public void onEnable(){
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

		//Commands
		getCommand("start").setExecutor(new StartCommand());

		//Begin running apocalypse timers and so on
		ApocalypseManager.init();
	}


	public void onDisable(){

	}

	public static Random getRandom(){
		return random;
	}

	public static TenJava getInstance(){
		return instance;
	}

}
