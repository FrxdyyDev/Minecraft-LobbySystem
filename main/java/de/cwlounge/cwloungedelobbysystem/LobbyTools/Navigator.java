package de.cwlounge.cwloungedelobbysystem.LobbyTools;

import de.cwlounge.cwloungedelobbysystem.Listeners.JoinQuitListener;
import de.cwlounge.cwloungedelobbysystem.UTILS.GlassPane;
import de.cwlounge.cwloungedelobbysystem.UTILS.InventoryBuilder;
import de.cwlounge.cwloungedelobbysystem.UTILS.ItemBuilder;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;


public class Navigator implements Listener {

    public static Inventory NavigatorGUI;
    public static InventoryBuilder inventoryBuilder = new InventoryBuilder();

    @EventHandler
    public void onNavigatorActivate(PlayerInteractEvent event) {
        try {
            Player player = event.getPlayer();
            if (event.getItem().getType() != null) {
                if (event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                    if (event.getItem().equals(JoinQuitListener.Navigator)) {
                        event.setCancelled(true);
                        player.openInventory(NavigatorGUI);
                    }
                }
            }
        }catch (NullPointerException e) {
        }

    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        try {
            if (event.getCurrentItem().getItemMeta().getDisplayName() != null) {
                if (event.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.DARK_RED + "Spawn")) {
                    event.setCancelled(true);
                    player.teleport(new Location(player.getWorld(), 0,66,0));
                    player.playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT, 1, 1);
                }else if (event.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.DARK_RED + "CWBW-Training")) {
                    event.setCancelled(true);
                    player.teleport(player.getLocation().add(0,5,0));
                    player.playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT, 1, 1);
                }else if (event.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.DARK_RED + "Build-FFA")) {
                    event.setCancelled(true);
                    player.teleport(player.getLocation().add(0,5,0));
                    player.playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT, 1, 1);
                }else if (event.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.DARK_RED + "CWBW")) {
                    event.setCancelled(true);
                    player.teleport(player.getLocation().add(0,5,0));
                    player.playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT, 1, 1);
                }else if (event.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.DARK_RED + "Training")) {
                    event.setCancelled(true);
                    player.teleport(player.getLocation().add(0,5,0));
                    player.playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT, 1, 1);
                }
            }
        }catch (NullPointerException e) {
        }
    }

    public static void setupNavigatorGUI() {
        NavigatorGUI = inventoryBuilder.buildInventory(null, 9*3, ChatColor.DARK_RED + "Navigator");
        inventoryBuilder.fillInventory(NavigatorGUI, new ItemBuilder(new ItemStack(GlassPane.BLACK)).setDisplayName(" ").getItem());
        NavigatorGUI.setItem(13, new ItemBuilder(new ItemStack(Material.MAGMA_CREAM)).setDisplayName(ChatColor.DARK_RED + "Spawn").getItem());
        NavigatorGUI.setItem(2, new ItemBuilder(new ItemStack(Material.WEB)).setDisplayName(ChatColor.DARK_RED + "CWBW-Training").getItem());
        NavigatorGUI.setItem(6, new ItemBuilder(new ItemStack(Material.GOLD_SWORD)).setDisplayName(ChatColor.DARK_RED + "Build-FFA").getItem());
        NavigatorGUI.setItem(20, new ItemBuilder(new ItemStack(Material.BED)).setDisplayName(ChatColor.DARK_RED + "CWBW").getItem());
        NavigatorGUI.setItem(24, new ItemBuilder(new ItemStack(Material.ARMOR_STAND)).setDisplayName(ChatColor.DARK_RED + "Training").getItem());
    }

}
