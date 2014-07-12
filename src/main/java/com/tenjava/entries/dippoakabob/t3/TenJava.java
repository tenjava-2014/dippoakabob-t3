package com.tenjava.entries.dippoakabob.t3;

import com.tenjava.entries.dippoakabob.t3.apocalypse.ApocalypseManager;
import org.bukkit.plugin.java.JavaPlugin;

public class TenJava extends JavaPlugin {

	private static TenJava instance;

	public void onEnable(){

		//Adding Apocalypse Types
		//TODO add event types

		//Begin running apocalypse timers and so on
		ApocalypseManager.init();

		instance = this;
	}


	public void onDisable(){

	}

	public static TenJava getInstance(){
		return instance;
	}

}
