package de.cwlounge.cwloungedelobbysystem.Listeners;

import de.cwlounge.cwloungedelobbysystem.PlayerManager;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockInteractListener implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        if (player != null) {
            if (!player.getGameMode().equals(GameMode.CREATIVE) || !PlayerManager.getInstance().getLobbyPlayer(player).isBuildmode()) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        if (player != null) {
            if (!player.getGameMode().equals(GameMode.CREATIVE) || !PlayerManager.getInstance().getLobbyPlayer(player).isBuildmode()) {
                event.setCancelled(true);
            }
        }
    }

}
