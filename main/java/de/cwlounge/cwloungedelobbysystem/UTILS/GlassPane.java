package de.cwlounge.cwloungedelobbysystem.UTILS;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GlassPane {
    public static final ItemStack C0 = get(0), C1 = get(1), C2 = get(2), C3 = get(3), C4 = get(4), C5 = get(5), C6 = get(6), C7 = get(7), C8 = get(8), C9 = get(9), C10 = get(10), C11 = get(11), C12 = get(12), C13 = get(13), C14 = get(14), C15 = get(15);
    public static final ItemStack WHITE = C0, ORANGE = C1, MAGENTA = C2, LIGHT_BLUE = C3, YELLOW = C4, LIME = C5, PINK = C6, GRAY = C7, LIGHT_GRAY = C8, CYAN = C9, PURPLE = C10, BLUE = C11, BROWN = C12, GREEN = C13, RED = C14, BLACK = C15;

    @SuppressWarnings("deprecation")
    private static final ItemStack get(int i) {
        ItemStack is = new ItemStack(160, 1, (byte) i);
        ItemMeta im = is.getItemMeta();
        im.setDisplayName("§a");
        is.setItemMeta(im);
        return is;
    }
}