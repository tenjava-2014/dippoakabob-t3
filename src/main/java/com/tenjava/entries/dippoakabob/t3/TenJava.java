package com.tenjava.entries.dippoakabob.t3;

import com.tenjava.entries.dippoakabob.t3.apocalypse.ApocalypseManager;
import com.tenjava.entries.dippoakabob.t3.apocalypse.types.Testing;
import org.bukkit.plugin.java.JavaPlugin;

public class TenJava extends JavaPlugin {

	private static TenJava instance;

	public void onEnable(){
		instance = this;

		//Adding Apocalypse Types
		ApocalypseManager.addEvent(new Testing());

		//Begin running apocalypse timers and so on
		ApocalypseManager.init();
	}


	public void onDisable(){

	}

	public static TenJava getInstance(){
		return instance;
	}

}
