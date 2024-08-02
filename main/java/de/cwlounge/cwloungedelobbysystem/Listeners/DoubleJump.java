package de.cwlounge.cwloungedelobbysystem.Listeners;

import com.google.common.util.concurrent.AbstractScheduledService;
import de.cwlounge.cwloungedelobbysystem.Main;
import de.cwlounge.cwloungedelobbysystem.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

public class DoubleJump implements Listener {

    List<Player> triggeredDoublejump = new ArrayList<>();

    @EventHandler
    public void onFlyToggleEvent(PlayerToggleFlightEvent event) {
        event.setCancelled(true);
        Player player = event.getPlayer();
        if (!player.getGameMode().equals(GameMode.SPECTATOR)) {
            if (PlayerManager.getInstance().getLobbyPlayer(player).doublejumpEnabled()) {
                if (triggeredDoublejump.contains(player)) {

                }else {
                    player.setVelocity(player.getLocation().getDirection().multiply(1.2).add(new Vector(0,0.8,0)));
                    triggeredDoublejump.add(player);
                }
                event.setCancelled(true);
            }else if (PlayerManager.getInstance().getLobbyPlayer(player).canFly() && PlayerManager.getInstance().getLobbyPlayer(player).isAbleToFly()) {
                if (PlayerManager.getInstance().getLobbyPlayer(player).isFlying()) {
                    player.setFlying(false);
                    player.sendMessage("flying disabled");
                    PlayerManager.getInstance().getLobbyPlayer(player).setFlying(false);
                }else if (!PlayerManager.getInstance().getLobbyPlayer(player).isFlying()) {
                    player.setFlying(true);
                    player.sendMessage("flying enabled");
                    PlayerManager.getInstance().getLobbyPlayer(player).setFlying(true);
                }
            }else {
            }
        }
    }

    @SuppressWarnings("deprecation")
    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (!player.getGameMode().equals(GameMode.SPECTATOR)) {
            if (!player.getLocation().subtract(0, 2, 0).getBlock().getType().isSolid() && PlayerManager.getInstance().getLobbyPlayer(player).doublejumpEnabled() && triggeredDoublejump.contains(player) && player.getLocation().subtract(0,1,0).getBlock().getType().equals(Material.AIR)) {
                player.setAllowFlight(true);
                player.getWorld().playEffect(player.getLocation(), Effect.SMOKE, 1);
            }else {
                triggeredDoublejump.remove(player);
            }
        }
    }

}
