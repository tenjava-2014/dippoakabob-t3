package com.tenjava.entries.dippoakabob.t3.events;

import com.tenjava.entries.dippoakabob.t3.TenJava;
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

	private String[] joinMessages = new String[] {
			"  Welcome to the Apocalypse!",
			"  Hey there! Glad you were able to join us!\n" +
					"  This is the Apocalypse!",
			"  Greetings traveler! Are you ready for the Apocalypse?",
			"  Howdy partner! This here is the Apocalypse! \n" +
					"  Glad you could join us.",
			"  What's up dude! \n" +
					"  Seems as though we're in a bit of a pickle\n" +
					"  Apparently this is the Apocalypse. Care to help out?"
	};

	@EventHandler
	public void onJoin(PlayerJoinEvent event){
		ApocalypseManager.playerJoined();

		Player player = event.getPlayer();

		player.sendMessage(ChatColor.DARK_GRAY + "" + ChatColor.STRIKETHROUGH + "-------------------------------");
		player.sendMessage(ChatColor.YELLOW + joinMessages[TenJava.getRandom().nextInt(joinMessages.length)] + "\n ");

		if(ApocalypseManager.hasStarted()){
			player.sendMessage(ChatColor.GRAY + "  It appears as though the Apocalypse has " + ChatColor.RED + "already started!\n" +
					ChatColor.GRAY + "  Better watch out!");

		}else{
			long remaining = ApocalypseManager.getTicksRemaining();
			int sec = (int) remaining/20;
			player.sendMessage(ChatColor.GRAY + "  Looks like there's still some time left!");
			player.sendMessage(ChatColor.GRAY + "  The Apocalypse begins in " + ChatColor.GOLD + sec/3600 + "h " + sec%3600/60 + "m " + sec%1200 + "s");
			player.sendMessage(ChatColor.GREEN + "\n  Collect items and materials fast!\n" +
					"  We need as much as we can get!");
		}
		player.sendMessage(ChatColor.AQUA + "\n  Good Luck!");
		player.sendMessage(ChatColor.DARK_GRAY + "" + ChatColor.STRIKETHROUGH + "-------------------------------");

	}

}
