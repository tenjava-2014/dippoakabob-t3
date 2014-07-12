package com.tenjava.entries.dippoakabob.t3.events;

import com.tenjava.entries.dippoakabob.t3.apocalypse.ApocalypseManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 * Created by dippoakabob.
 */
public class JoinLeaveListeners implements Listener {

	@EventHandler
	public void onJoin(PlayerJoinEvent event){
		Player player = event.getPlayer();

		long remaining = ApocalypseManager.getTicksRemaining();
		int sec = (int) remaining/20;

		player.sendMessage(ChatColor.GRAY + "=======[" + ChatColor.RED + " Apocalypse " + ChatColor.GRAY + "]=======");
		if(ApocalypseManager.hasStarted()){
			player.sendMessage(ChatColor.RED + "The Apocalypse has already started!");

		}else{
			player.sendMessage(ChatColor.GOLD + "the Apocalypse begins in " + String.format("%1h %2h %3m", sec/60, sec%60/60, sec%1200));
		}

	}

}
