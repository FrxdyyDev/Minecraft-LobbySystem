package de.cwlounge.cwloungedelobbysystem.LobbyTools;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import de.cwlounge.cwloungedelobbysystem.UTILS.GlassPane;
import de.cwlounge.cwloungedelobbysystem.UTILS.InventoryBuilder;
import de.cwlounge.cwloungedelobbysystem.UTILS.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class LobbyChangeTool implements Listener {

    public static Inventory LobbyChangeItem;

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        try {
            if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK) || event.getAction().equals(Action.RIGHT_CLICK_AIR)) {
                if (event.getItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.DARK_RED + "Change lobby" + ChatColor.DARK_GRAY + " - (right click)")) {
                    LobbyChangeItem.setItem(10, new ItemBuilder(new ItemStack(Material.NETHER_STAR)).setDisplayName(ChatColor.GOLD + "Lobby 1").getItem());
                    LobbyChangeItem.setItem(13, new ItemBuilder(new ItemStack(Material.NETHER_STAR)).setDisplayName(ChatColor.GOLD + "Lobby 2").getItem());
                    LobbyChangeItem.setItem(16, new ItemBuilder(new ItemStack(Material.NETHER_STAR)).setDisplayName(ChatColor.GOLD + "Lobby 3").getItem());
                    player.openInventory(LobbyChangeItem);
                }
            }
        }catch (NullPointerException e) {
        }
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        ByteArrayDataOutput output = ByteStreams.newDataOutput();
        try {
            if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GOLD + "Lobby 1")) {
                event.setCancelled(true);
                output.writeUTF("Connect");
                output.writeUTF("Lobby1");
            }else if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GOLD + "Lobby 2")) {
                event.setCancelled(true);
                output.writeUTF("Connect");
                output.writeUTF("Lobby2");
            }else if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GOLD + "Lobby 3")) {
                event.setCancelled(true);
                output.writeUTF("Connect");
                output.writeUTF("Lobby3");
            }
        }catch (NullPointerException e) {
        }
    }

    public static void setupLobbyChangeInv() {
        LobbyChangeItem = Bukkit.createInventory(null, InventoryType.CHEST, ChatColor.DARK_RED + "Lobbychange");
        InventoryBuilder inventoryBuilder = new InventoryBuilder();
        inventoryBuilder.fillInventory(LobbyChangeItem, GlassPane.BLACK);
    }

}
