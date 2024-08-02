package de.cwlounge.cwloungedelobbysystem.Listeners;

import de.cwlounge.cwloungedelobbysystem.LobbyPlayer;
import de.cwlounge.cwloungedelobbysystem.LobbyTools.Hidingtool;
import de.cwlounge.cwloungedelobbysystem.LobbyTools.SilentLobby;
import de.cwlounge.cwloungedelobbysystem.Main;
import de.cwlounge.cwloungedelobbysystem.PlayerManager;
import de.cwlounge.cwloungedelobbysystem.UTILS.ItemBuilder;
import de.cwlounge.cwloungedelobbysystem.UTILS.TaskAPI;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

public class JoinQuitListener implements Listener {

    public static ItemStack Navigator;
    public static ItemStack SilentLobbyItem;
    public static ItemStack SettingsItem;
    public static ItemStack LobbyChangeItem;

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        event.getPlayer().getInventory().clear();
        event.getPlayer().teleport(new Location(event.getPlayer().getWorld(), 0,66,0));
        TaskAPI.getInstance().runAsync(() -> {
            LobbyPlayer lobbyplayer = PlayerManager.getInstance().loadLobbyPlayer(event.getPlayer().getUniqueId());
            Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), () -> {
                GameMode gamemode = lobbyplayer.getGamemode();
                Boolean flying = lobbyplayer.isFlying();
                Boolean day = lobbyplayer.getDayToggled();
                event.getPlayer().setGameMode(gamemode);
                event.getPlayer().setFlying(flying);
                if (flying) {
                    PlayerManager.getInstance().getLobbyPlayer(event.getPlayer()).setFlying(true);
                }
                if (lobbyplayer.canAccessSilentLobby()) {
                    SilentLobby.setSilentLobbyStatus(event.getPlayer(), PlayerManager.getInstance().getLobbyPlayer(event.getPlayer()).isInSilentLobby());
                }
                if (day) {
                    event.getPlayer().setPlayerTime(6000L, false);
                }else {
                    event.getPlayer().setPlayerTime(18000L, false);
                }
                event.getPlayer().getInventory().setItem(4, (SettingsItem = new ItemBuilder(new ItemStack(Material.REDSTONE_TORCH_ON)).setDisplayName(ChatColor.DARK_RED + "Settings" + ChatColor.DARK_GRAY + " - (right click)").getItem()));
                Hidingtool.setHidingStatus(event.getPlayer(), PlayerManager.getInstance().getLobbyPlayer(event.getPlayer()).getHidingStatus());
                event.getPlayer().getInventory().setItem(0, (Navigator = new ItemBuilder(new ItemStack(Material.COMPASS)).setDisplayName(ChatColor.DARK_RED + "Navigator" + ChatColor.DARK_GRAY + " - (right click)").getItem()));
                lobbyplayer.setIP(event.getPlayer().getAddress().toString());
                event.getPlayer().getInventory().setItem(7, (LobbyChangeItem = new ItemBuilder(new ItemStack(Material.NETHER_STAR)).setDisplayName(ChatColor.DARK_RED + "Change lobby" + ChatColor.DARK_GRAY + " - (right click)").getItem()));
            }, 0);
        });
        event.getPlayer().setFoodLevel(20);
        event.getPlayer().setAllowFlight(true);
        event.getPlayer().setFlying(false);
        event.setJoinMessage("");
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        TaskAPI.getInstance().runAsync(() -> {

            LobbyPlayer lobbyplayer = PlayerManager.getInstance().getLobbyPlayer(player.getUniqueId());
            lobbyplayer.setGamemode(player.getGameMode());
            lobbyplayer.setName(player.getName());
            lobbyplayer.setFlying(player.isFlying());
            PlayerManager.getInstance().unloadLobbyPlayer(player.getUniqueId());
        });
        event.setQuitMessage("");
    }
}