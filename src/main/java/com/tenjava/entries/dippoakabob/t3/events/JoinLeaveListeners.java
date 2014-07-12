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
		ApocalypseManager.playerJoined();

		Player player = event.getPlayer();

		player.sendMessage(ChatColor.GRAY + "[]===========[" + ChatColor.RED + " Apocalypse " + ChatColor.GRAY + "]===========[]");
		if(ApocalypseManager.hasStarted()){
			player.sendMessage(ChatColor.RED + "The Apocalypse has already started!");

		}else{
			long remaining = ApocalypseManager.getTicksRemaining();
			int sec = (int) remaining/20;
			player.sendMessage(ChatColor.GOLD + "The Apocalypse begins in " + sec/1200 + "h " + sec%1200/60 + "m " + sec%1200 + "s");
			player.sendMessage(ChatColor.GRAY + "Collect items while you can, beware!");
		}
		player.sendMessage(ChatColor.GRAY + "Good Luck!");

	}

}
