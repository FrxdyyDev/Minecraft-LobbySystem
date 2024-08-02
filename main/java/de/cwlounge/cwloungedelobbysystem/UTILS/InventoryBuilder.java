package de.cwlounge.cwloungedelobbysystem.UTILS;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class InventoryBuilder {

    public InventoryBuilder() {

    }

    public Inventory buildInventory(InventoryHolder holder, InventoryType inventoryType, String name) {
        return buildInventory(holder, inventoryType.getDefaultSize(), name);
    }

    public Inventory buildInventory(InventoryHolder holder, InventoryType inventoryType, String name, HashMap<Integer, ItemStack> items) {
        return buildInventory(holder, inventoryType.getDefaultSize(), name, items);
    }

    public Inventory buildInventory(InventoryHolder holder, Integer size, String name) {
        return buildInventory(holder, size, name, new HashMap<>());
    }

    public Inventory buildInventory(InventoryHolder holder, Integer size, String name, HashMap<Integer, ItemStack> items) {
        Inventory inventory;
        inventory = Bukkit.createInventory(holder, size, name);
        for (int i = 0; i<items.size(); i++) {
            inventory.setItem(i, items.get(i));
        }
        return inventory;
    }

    public void fillInventory(Inventory inventory, ItemStack itemStack) {
        for (int i = 0; i<inventory.getSize(); i++) {
            inventory.setItem(i, itemStack);
        }
    }

}
