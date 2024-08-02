package de.cwlounge.cwloungedelobbysystem.Listeners;

import de.cwlounge.cwloungedelobbysystem.PlayerManager;
import de.dytanic.cloudnet.CloudNet;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        String msg = event.getMessage();
        event.setCancelled(true);
        for (Player all : Bukkit.getOnlinePlayers()) {
            if (PlayerManager.getInstance().getLobbyPlayer(all).isInSilentLobby()) {
                if (event.getPlayer() == all) {
                    all.sendMessage(event.getPlayer().getName() + ChatColor.DARK_GRAY + " » §7" + msg);
                }
            }else {
                all.sendMessage(event.getPlayer().getName() + ChatColor.DARK_GRAY + " » §7" + msg);
            }
        }
    }
}
