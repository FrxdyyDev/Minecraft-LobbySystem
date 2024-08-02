package de.cwlounge.cwloungedelobbysystem.LobbyTools.Settings;

import de.cwlounge.cwloungedelobbysystem.LobbyPlayer;
import de.cwlounge.cwloungedelobbysystem.PlayerManager;
import de.cwlounge.cwloungedelobbysystem.UTILS.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class Movement implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        try {
            Player player = (Player) event.getWhoClicked();
            LobbyPlayer lobbyPlayer = PlayerManager.getInstance().getLobbyPlayer(player);
            if (event.getCurrentItem().getType().equals(Material.FEATHER) && event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("Flying")) {
                event.setCancelled(true);
                if (event.getCurrentItem().getItemMeta().getEnchants().isEmpty()) {
                    player.getOpenInventory().setItem(16, new ItemBuilder(new ItemStack(Material.FEATHER)).setDisplayName("Flying").addEnchant(Enchantment.DEPTH_STRIDER, 3).hideEnchants().getItem());
                    player.getOpenInventory().setItem(13, new ItemBuilder(new ItemStack(Material.GOLD_BOOTS)).setDisplayName("Doublejump").getItem());
                    player.getOpenInventory().setItem(10, new ItemBuilder(new ItemStack(Material.LEATHER_BOOTS)).setDisplayName("Sprinting").getItem());
                    lobbyPlayer.setAbleToFly(true);
                    lobbyPlayer.setDoublejump(false);
                    player.setAllowFlight(true);
                }
            }else if (event.getCurrentItem().getType().equals(Material.GOLD_BOOTS) && event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("Doublejump")) {
                event.setCancelled(true);
                if (event.getCurrentItem().getItemMeta().getEnchants().isEmpty()) {
                    player.getOpenInventory().setItem(13, new ItemBuilder(new ItemStack(Material.GOLD_BOOTS)).setDisplayName("Doublejump").addEnchant(Enchantment.DEPTH_STRIDER, 3).hideEnchants().getItem());
                    if (PlayerManager.getInstance().getLobbyPlayer(player).canFly()) {
                        player.getOpenInventory().setItem(16, new ItemBuilder(new ItemStack(Material.FEATHER)).setDisplayName("Flying").getItem());
                    }else {
                        player.getOpenInventory().setItem(16, new ItemBuilder(new ItemStack(Material.BARRIER)).setDisplayName("Flying").getItem());
                    }
                    player.getOpenInventory().setItem(10, new ItemBuilder(new ItemStack(Material.LEATHER_BOOTS)).setDisplayName("Sprinting").getItem());
                    lobbyPlayer.setAbleToFly(false);
                    lobbyPlayer.setDoublejump(true);
                    player.setFlying(false);
                    player.setAllowFlight(true);
                }
            }else if (event.getCurrentItem().getType().equals(Material.LEATHER_BOOTS) && event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("Sprinting")) {
                event.setCancelled(true);
                if (event.getCurrentItem().getItemMeta().getEnchants().isEmpty()) {
                    player.getOpenInventory().setItem(10, new ItemBuilder(new ItemStack(Material.LEATHER_BOOTS)).setDisplayName("Sprinting").addEnchant(Enchantment.DEPTH_STRIDER, 3).hideEnchants().getItem());
                    if (PlayerManager.getInstance().getLobbyPlayer(player).canFly()) {
                        player.getOpenInventory().setItem(16, new ItemBuilder(new ItemStack(Material.FEATHER)).setDisplayName("Flying").getItem());
                    }else {
                        player.getOpenInventory().setItem(16, new ItemBuilder(new ItemStack(Material.BARRIER)).setDisplayName("Flying").getItem());
                    }
                    player.getOpenInventory().setItem(13, new ItemBuilder(new ItemStack(Material.GOLD_BOOTS)).setDisplayName("Doublejump").getItem());
                    lobbyPlayer.setAbleToFly(false);
                    lobbyPlayer.setDoublejump(false);
                    player.setFlying(false);
                    player.setAllowFlight(false);
                }
            }
        }catch (NullPointerException e) {
        }
    }
}
