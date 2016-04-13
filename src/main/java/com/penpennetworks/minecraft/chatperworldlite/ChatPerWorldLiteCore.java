package com.penpennetworks.minecraft.chatperworldlite;

import java.util.Set;

import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class ChatPerWorldLiteCore extends JavaPlugin {

	private class ChatEventListener implements Listener {

		@EventHandler
		public void onChatSend( AsyncPlayerChatEvent e ){

			World world = e.getPlayer().getWorld();
			Set<Player> list = e.getRecipients();

			for( Player receiver : list ){
				if( receiver.getWorld() != world ){
					list.remove(receiver);
				}
			}

		}

	}

	private ChatEventListener listener;

	@Override
	public void onEnable(){
		listener = new ChatEventListener();
		getServer().getPluginManager().registerEvents(listener, this);
	}

	@Override
	public void onDisable(){
		HandlerList.unregisterAll(listener);
	}


}
