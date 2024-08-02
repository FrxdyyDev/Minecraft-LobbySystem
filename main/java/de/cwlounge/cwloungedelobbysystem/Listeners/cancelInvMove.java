package de.cwlounge.cwloungedelobbysystem.Listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryMoveItemEvent;

public class cancelInvMove implements Listener {

    @EventHandler
    public void onItemMove(InventoryMoveItemEvent event) {
        event.setCancelled(true);
    }
}
