package de.cwlounge.cwloungedelobbysystem.Database;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class ConfigManager {
    private String ordner = "default";
    private String configfile = "config";
    private String datafile = "data";
    private ArrayList<String> strings = new ArrayList<String>();
    public File file;
    public YamlConfiguration cfg;
    public File data;
    public YamlConfiguration dat;

    public ConfigManager() {
    }

    public ConfigManager(String ordner) {
        this.ordner = ordner;
    }

    public ConfigManager(String ordner, String configfile) {
        this.ordner = ordner;
        this.configfile = configfile;
    }

    public ConfigManager(String ordner, String configfile, String datafile) {
        this.ordner = ordner;
        this.configfile = configfile;
        this.datafile = datafile;
    }

    public ConfigManager saveconfig() {
        try {
            cfg.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this;
    }

    public ConfigManager savedata() {
        try {
            cfg.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this;
    }

    public ConfigManager reloadconfig() {
        file = new File("plugins/" + ordner + "/" + configfile + ".yml");
        cfg = YamlConfiguration.loadConfiguration(file);
        return this;
    }

    public ConfigManager reloaddata() {
        data = new File("plugins/" + ordner + "/" + datafile + ".yml");
        dat = YamlConfiguration.loadConfiguration(data);
        return this;
    }

    public ConfigManager setup() {
        file = new File("plugins/" + ordner + "/" + configfile + ".yml");
        cfg = YamlConfiguration.loadConfiguration(file);
        data = new File("plugins/" + ordner + "/" + datafile + ".yml");
        dat = YamlConfiguration.loadConfiguration(data);
        if (cfg.getString(".Prefix") == null) {
            cfg.set(".Prefix", "&7Servername");
        }
        if (cfg.getString(".License") == null) {
            cfg.set(".License", "0000-0000-0000-0000");
        }

        if (cfg.getString(".scoreboard.displayname") == null) {
            cfg.set(".scoreboard.displayname", "&6Name");
        }
        if (cfg.getString(".placeholder") == null) {
            cfg.set(".placeholder", "Die Placeholder sind %kills% und %deaths% und %coins% und %name% und %ranking%");
        }

        for (int i = 13; i > 0; i--) {
            if (cfg.getString(".scoreboard.score" + i) == null) {
                cfg.set(".scoreboard.score" + i, "&fZeile" + (i + 1));
            }
        }

        for (String s : strings) {
            if (cfg.getString("." + s) == null) {
                cfg.set("." + s, "" + s);
            }
        }

        try {
            cfg.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            dat.save(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this;
    }

    public String license() {
        return cfg.getString(".License");
    }

    public String prefix() {
        return cfg.getString(".Prefix").replaceAll("&", "§");
    }

    public String getString(String s) {
        return cfg.getString("." + s);
    }

    public void setLocation(String str, Location loc) {
        dat.set("." + str + ".World", loc.getWorld().getName());
        dat.set("." + str + ".X", Double.valueOf(loc.getX()));
        dat.set("." + str + ".Y", Double.valueOf(loc.getY()));
        dat.set("." + str + ".Z", Double.valueOf(loc.getZ()));
        dat.set("." + str + ".Yaw", Double.valueOf(loc.getYaw()));
        dat.set("." + str + ".Pitch", Double.valueOf(loc.getPitch()));
        try {
            dat.save(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Location getLocation(String str) {
        World world = Bukkit.getWorld(dat.getString("." + str + ".World"));
        Double x = Double.valueOf(dat.getDouble("." + str + ".X"));
        Double y = Double.valueOf(dat.getDouble("." + str + ".Y"));
        Double z = Double.valueOf(dat.getDouble("." + str + ".Z"));
        float Yaw = dat.getInt("." + str + ".Yaw");
        float Pitch = dat.getInt("." + str + ".Pitch");
        Location loc = new Location(world, x.doubleValue(), y.doubleValue(), z.doubleValue());
        loc.setPitch(Pitch);
        loc.setYaw(Yaw);
        return loc;
    }

    public void saveinventory(Player p, String kit) {
        reloaddata();
        dat.set(".kit." + kit + ".helmet", p.getInventory().getHelmet());
        dat.set(".kit." + kit + ".chestplate", p.getInventory().getChestplate());
        dat.set(".kit." + kit + ".leggings", p.getInventory().getLeggings());
        dat.set(".kit." + kit + ".boots", p.getInventory().getBoots());

        for (int i = 0; i < p.getInventory().getSize(); i++) {
            dat.set(".kit." + kit + ".itemslot" + i, p.getInventory().getItem(i));
        }
        try {
            dat.save(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
        reloaddata();
    }

    public void savelootinventory(Player p) {
        reloaddata();
        for (int i = 0; i < p.getInventory().getSize(); i++) {
            dat.set(".reward.loot" + i, p.getInventory().getItem(i));
        }
        try {
            dat.save(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
        reloaddata();
    }

    public void addlootitems(Player p) {
        reloaddata();
        for (int i = 0; i < p.getInventory().getSize(); i++) {
            if (dat.getItemStack(".reward.loot" + i) != null) {
                p.getInventory().addItem(dat.getItemStack(".reward.loot" + i));
            }
        }
        try {
            dat.save(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
        reloaddata();
    }

    public void setkit(Player p) {
        p.getInventory().clear();
        p.getInventory().setHelmet(dat.getItemStack(".kit.helmet"));
        p.getInventory().setChestplate(dat.getItemStack(".kit.chestplate"));
        p.getInventory().setLeggings(dat.getItemStack(".kit.leggings"));
        p.getInventory().setBoots(dat.getItemStack(".kit.boots"));
        for (int i = 0; i < p.getInventory().getSize(); i++) {
            if (dat.getItemStack(".kit.itemslot" + i) != null) {
                p.getInventory().setItem(i, dat.getItemStack(".kit.itemslot" + i));
            }
        }
    }

    public ArrayList<String> getStrings() {
        return strings;
    }

    public ConfigManager setStrings(ArrayList<String> strings) {
        this.strings = strings;
        return this;
    }

    public ConfigManager addString(String s) {
        strings.add(s);
        return this;
    }

    public ConfigManager removeString(String s) {
        strings.remove(s);
        return this;
    }

    public ConfigManager existsString(String s) {
        strings.contains(s);
        return this;
    }
}
