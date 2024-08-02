package de.cwlounge.cwloungedelobbysystem.LobbyTools;

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

public class Hidingtool implements Listener {


    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        try {
            if (event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                if (event.getItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.DARK_GREEN + "Everyone" + ChatColor.DARK_GRAY + " - (right click)")) {
                    setHidingStatus(player, "VIPS");
                }else if (event.getItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.DARK_PURPLE + "VIPS" + ChatColor.DARK_GRAY + " - (right click)")) {
                    setHidingStatus(player, "Nobody");
                }else if (event.getItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.RED + "Nobody" + ChatColor.DARK_GRAY + " - (right click)")) {
                    setHidingStatus(player, "Everyone");
                }
            }
        }catch (NullPointerException e) {
        }
    }

    public static void setHidingStatus(Player player, String status) {
        if (status.equals("Everyone")) {
            player.getInventory().setItem(1, new ItemBuilder(new ItemStack(Material.BANNER, 1, (short) 10))
                    .setDisplayName(ChatColor.DARK_GREEN + "Everyone" + ChatColor.DARK_GRAY + " - (right click)").getItem());
            PlayerManager.getInstance().getLobbyPlayer(player).setHidingStatus("Everyone");
            for (Player all: Bukkit.getOnlinePlayers()) {
                player.showPlayer(all);
            }
        }else if (status.equals("VIPS")) {
            player.getInventory().setItem(1, new ItemBuilder(new ItemStack(Material.BANNER, 1, (short) 5))
                    .setDisplayName(ChatColor.DARK_PURPLE + "VIPS" + ChatColor.DARK_GRAY + " - (right click)").getItem());
            PlayerManager.getInstance().getLobbyPlayer(player).setHidingStatus("VIPS");
            for (Player all: Bukkit.getOnlinePlayers()) {
                player.hidePlayer(all);
                player.showPlayer(Bukkit.getPlayer("Frxdyy"));
            }
        }else if (status.equals("Nobody")) {
            player.getInventory().setItem(1, new ItemBuilder(new ItemStack(Material.BANNER, 1, (short) 1))
                    .setDisplayName(ChatColor.RED + "Nobody" + ChatColor.DARK_GRAY + " - (right click)").getItem());
            for (Player all: Bukkit.getOnlinePlayers()) {
                player.hidePlayer(all);
            }
            PlayerManager.getInstance().getLobbyPlayer(player).setHidingStatus("Nobody" + ChatColor.DARK_GRAY + " - (right click)");
        }
    }
}