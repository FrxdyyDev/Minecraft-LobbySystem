package de.cwlounge.cwloungedelobbysystem.LobbyTools.Settings;

import de.cwlounge.cwloungedelobbysystem.Listeners.JoinQuitListener;
import de.cwlounge.cwloungedelobbysystem.PlayerManager;
import de.cwlounge.cwloungedelobbysystem.UTILS.GlassPane;
import de.cwlounge.cwloungedelobbysystem.UTILS.InventoryBuilder;
import de.cwlounge.cwloungedelobbysystem.UTILS.ItemBuilder;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class SettingHandler implements Listener {

    public static Inventory SettingsInventory;

    public static InventoryBuilder inventoryBuilder = new InventoryBuilder();

    public static ItemStack MovementItem;
        public static ItemStack Sprinting;
        public static ItemStack DoubleJump;
        public static ItemStack Flying;
    public static ItemStack DayNightItem;

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        try {
            if (event.getItem().equals(JoinQuitListener.SettingsItem)) {
                if (event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                    if (PlayerManager.getInstance().getLobbyPlayer(player).getDayToggled().equals(true)) {
                        SettingsInventory.setItem(11, DayNightItem = new ItemBuilder(new ItemStack(Material.getMaterial(347))).setDisplayName(ChatColor.DARK_BLUE + "Whitemode").getItem());
                    }else {
                        SettingsInventory.setItem(11, DayNightItem = new ItemBuilder(new ItemStack(Material.getMaterial(347))).setDisplayName(ChatColor.DARK_BLUE + "Darkmode").getItem());
                    }
                    player.openInventory(SettingsInventory);
                }
            }
        }catch (NullPointerException e) {
        }
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        try {
            if (event.getCurrentItem().equals(MovementItem)) {
                player.openInventory(updateSettingsInv(player, "movement"));
                event.setCancelled(true);
            }else if (event.getCurrentItem().equals(DayNightItem)) {
                player.openInventory(updateSettingsInv(player, "TimeChanger"));
                event.setCancelled(true);
            }else if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.DARK_BLUE + "Coming soon")) {
                event.setCancelled(true);
            }else if (event.getCurrentItem().equals(JoinQuitListener.Navigator) || event.getCurrentItem().equals(JoinQuitListener.SettingsItem)
                    || event.getCurrentItem().equals(JoinQuitListener.LobbyChangeItem) || event.getCurrentItem().equals(JoinQuitListener.SilentLobbyItem)
                    || event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.DARK_GREEN + "Everyone" + ChatColor.DARK_GRAY + " - (right click)")
                    || event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.DARK_PURPLE + "VIPS" + ChatColor.DARK_GRAY + " - (right click)")
                    || event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.RED + "Nobody" + ChatColor.DARK_GRAY + " - (right click)")) {
                event.setCancelled(true);
            }
        }catch (NullPointerException e) {
        }
    }


    public static void setupSettingsInv() {
        SettingsInventory = inventoryBuilder.buildInventory(null, 9*3, ChatColor.DARK_RED + "Settings");
        inventoryBuilder.fillInventory(SettingsInventory, new ItemBuilder(new ItemStack(GlassPane.BLACK)).setDisplayName(" ").getItem());
        SettingsInventory.setItem(10, MovementItem = new ItemBuilder(new ItemStack(Material.DIAMOND_BOOTS)).setDisplayName(ChatColor.DARK_BLUE + "Movement").getItem());
        SettingsInventory.setItem(12, new ItemBuilder(new ItemStack(Material.BARRIER)).setDisplayName(ChatColor.DARK_BLUE + "Coming soon").getItem());
        SettingsInventory.setItem(13, new ItemBuilder(new ItemStack(Material.BARRIER)).setDisplayName(ChatColor.DARK_BLUE + "Coming soon").getItem());
        SettingsInventory.setItem(14, new ItemBuilder(new ItemStack(Material.BARRIER)).setDisplayName(ChatColor.DARK_BLUE + "Coming soon").getItem());
        SettingsInventory.setItem(15, new ItemBuilder(new ItemStack(Material.BARRIER)).setDisplayName(ChatColor.DARK_BLUE + "Coming soon").getItem());
        SettingsInventory.setItem(16, new ItemBuilder(new ItemStack(Material.BARRIER)).setDisplayName(ChatColor.DARK_BLUE + "Coming soon").getItem());
    }

    public static Inventory updateSettingsInv(Player player, String invstatus) {
        Inventory inventory = inventoryBuilder.buildInventory(null, 9*3, ChatColor.DARK_RED + "Settings");
        inventoryBuilder.fillInventory(inventory, new ItemBuilder(new ItemStack(GlassPane.BLACK)).setDisplayName(" ").getItem());
        if (invstatus.equals("movement")) {
            if (!PlayerManager.getInstance().getLobbyPlayer(player).doublejumpEnabled() && !PlayerManager.getInstance().getLobbyPlayer(player).isAbleToFly()) {
                inventory.setItem(10, Sprinting = new ItemBuilder(new ItemStack(Material.LEATHER_BOOTS)).setDisplayName("Sprinting").addEnchant(Enchantment.DEPTH_STRIDER, 3).hideEnchants().getItem());
                inventory.setItem(13, DoubleJump = new ItemBuilder(new ItemStack(Material.GOLD_BOOTS)).setDisplayName("Doublejump").getItem());
                inventory.setItem(16, Flying = new ItemBuilder(new ItemStack(Material.FEATHER)).setDisplayName("Flying").getItem());
            }else if (PlayerManager.getInstance().getLobbyPlayer(player).doublejumpEnabled() && !PlayerManager.getInstance().getLobbyPlayer(player).isAbleToFly()) {
                inventory.setItem(10, Sprinting = new ItemBuilder(new ItemStack(Material.LEATHER_BOOTS)).setDisplayName("Sprinting").getItem());
                inventory.setItem(13, DoubleJump = new ItemBuilder(new ItemStack(Material.GOLD_BOOTS)).setDisplayName("Doublejump").addEnchant(Enchantment.DEPTH_STRIDER, 3).hideEnchants().getItem());
                inventory.setItem(16, Flying = new ItemBuilder(new ItemStack(Material.FEATHER)).setDisplayName("Flying").getItem());
            }
            if (PlayerManager.getInstance().getLobbyPlayer(player).canFly()) {
                if (!PlayerManager.getInstance().getLobbyPlayer(player).doublejumpEnabled() && PlayerManager.getInstance().getLobbyPlayer(player).isAbleToFly()) {
                    inventory.setItem(10, Sprinting = new ItemBuilder(new ItemStack(Material.LEATHER_BOOTS)).setDisplayName("Sprinting").getItem());
                    inventory.setItem(13, DoubleJump = new ItemBuilder(new ItemStack(Material.GOLD_BOOTS)).setDisplayName("Doublejump").getItem());
                    inventory.setItem(16, Flying = new ItemBuilder(new ItemStack(Material.FEATHER)).setDisplayName("Flying").addEnchant(Enchantment.DEPTH_STRIDER, 3).hideEnchants().getItem());
                }
            }else {
                inventory.setItem(10, Sprinting = new ItemBuilder(new ItemStack(Material.LEATHER_BOOTS)).setDisplayName("Sprinting").getItem());
                inventory.setItem(13, DoubleJump = new ItemBuilder(new ItemStack(Material.GOLD_BOOTS)).setDisplayName("Doublejump").getItem());
                inventory.setItem(16, Flying = new ItemBuilder(new ItemStack(Material.BARRIER)).setDisplayName("Flying").getItem());
            }
        }else if (invstatus.equals("TimeChanger")) {
            inventory.setItem(10, MovementItem);
            inventory.setItem(12, new ItemBuilder(new ItemStack(Material.BARRIER)).setDisplayName(ChatColor.DARK_BLUE + "Coming soon").getItem());
            inventory.setItem(13, new ItemBuilder(new ItemStack(Material.BARRIER)).setDisplayName(ChatColor.DARK_BLUE + "Coming soon").getItem());
            inventory.setItem(14, new ItemBuilder(new ItemStack(Material.BARRIER)).setDisplayName(ChatColor.DARK_BLUE + "Coming soon").getItem());
            inventory.setItem(15, new ItemBuilder(new ItemStack(Material.BARRIER)).setDisplayName(ChatColor.DARK_BLUE + "Coming soon").getItem());
            inventory.setItem(16, new ItemBuilder(new ItemStack(Material.BARRIER)).setDisplayName(ChatColor.DARK_BLUE + "Coming soon").getItem());
            if (PlayerManager.getInstance().getLobbyPlayer(player).getDayToggled().equals(true)) {
                inventory.setItem(11, DayNightItem = new ItemBuilder(new ItemStack(Material.getMaterial(347))).setDisplayName(ChatColor.DARK_BLUE + "Whitemode").getItem());
                PlayerManager.getInstance().getLobbyPlayer(player).setDayToggled(false);
                player.setPlayerTime(6000L, false);
            }else {
                inventory.setItem(11, DayNightItem = new ItemBuilder(new ItemStack(Material.getMaterial(347))).setDisplayName(ChatColor.DARK_BLUE + "Darkmode").getItem());
                PlayerManager.getInstance().getLobbyPlayer(player).setDayToggled(true);
                player.setPlayerTime(18000L, false);
            }
        }
        return inventory;
    }

}
