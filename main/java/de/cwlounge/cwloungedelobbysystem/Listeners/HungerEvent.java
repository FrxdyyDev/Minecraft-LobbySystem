package de.cwlounge.cwloungedelobbysystem.Listeners;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class HungerEvent implements Listener {

    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent event) {
        if (event.getEntity().getType().equals(EntityType.PLAYER)) {
            event.setCancelled(true);
        }
    }
}
