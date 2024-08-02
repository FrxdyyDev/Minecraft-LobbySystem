package de.cwlounge.cwloungedelobbysystem.LobbyTools;

import de.cwlounge.cwloungedelobbysystem.Listeners.JoinQuitListener;
import de.cwlounge.cwloungedelobbysystem.Main;
import de.cwlounge.cwloungedelobbysystem.PlayerManager;
import de.cwlounge.cwloungedelobbysystem.UTILS.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class SilentLobby implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        try {
            if (event.getItem().equals(JoinQuitListener.SilentLobbyItem)) {
                if (event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                    if (PlayerManager.getInstance().getLobbyPlayer(player).isInSilentLobby()) {
                        setSilentLobbyStatus(player, false);
                    }else {
                        if (PlayerManager.getInstance().getLobbyPlayer(player).canAccessSilentLobby()) {
                            setSilentLobbyStatus(player, true);
                        }else {
                            player.sendMessage(Main.getPrefix() + "Error");
                        }
                    }
                }
            }
        }catch (NullPointerException e) {
        }
    }


    public static void setSilentLobbyStatus(Player player, Boolean inSilentLobby) {
        if (inSilentLobby) {
            for (Player all : Bukkit.getOnlinePlayers()) {
                player.hidePlayer(all);
                all.hidePlayer(player);
            }
            PlayerManager.getInstance().getLobbyPlayer(player).setInSilentLobby(true);
            player.getInventory().setItem(3, JoinQuitListener.SilentLobbyItem = new ItemBuilder(new ItemStack(Material.NOTE_BLOCK))
                    .setDisplayName(ChatColor.DARK_GREEN + "Silentlobby" + ChatColor.DARK_GRAY + " - (right click)").getItem());
        }else {
            for (Player all : Bukkit.getOnlinePlayers()) {
                player.showPlayer(all);
                all.showPlayer(player);
            }
            PlayerManager.getInstance().getLobbyPlayer(player).setInSilentLobby(false);
            player.getInventory().setItem(3, JoinQuitListener.SilentLobbyItem = new ItemBuilder(new ItemStack(Material.NOTE_BLOCK))
                    .setDisplayName(ChatColor.DARK_RED + "Silentlobby" + ChatColor.DARK_GRAY + " - (right click)").getItem());
        }
    }

}
