package com.tenjava.entries.dippoakabob.t3.apocalypse;

import com.tenjava.entries.dippoakabob.t3.TenJava;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dippoakabob.
 *
 *
 */
public class ApocalypseManager {

	private static List<ApocalypseEvent> events = new ArrayList<ApocalypseEvent>();

	public ApocalypseManager(){

	}

	/**
	 * Starts timers and such for playing events.
	 */
	public static void init(){
		//TODO make this
	}

	public static void addEvent(ApocalypseEvent event){
		events.add(event);
		Bukkit.getPluginManager().registerEvents(event, TenJava.getInstance());
	}

	public static List<ApocalypseEvent> getEvents(){
		return events;
	}

}
