package de.cwlounge.cwloungedelobbysystem;

import de.cwlounge.cwloungedelobbysystem.Database.ConfigManager;
import de.cwlounge.cwloungedelobbysystem.Database.DatabaseManager;
import de.cwlounge.cwloungedelobbysystem.Listeners.*;
import de.cwlounge.cwloungedelobbysystem.LobbyTools.Hidingtool;
import de.cwlounge.cwloungedelobbysystem.LobbyTools.LobbyChangeTool;
import de.cwlounge.cwloungedelobbysystem.LobbyTools.Navigator;
import de.cwlounge.cwloungedelobbysystem.LobbyTools.Settings.Movement;
import de.cwlounge.cwloungedelobbysystem.LobbyTools.Settings.SettingHandler;
import de.cwlounge.cwloungedelobbysystem.LobbyTools.SilentLobby;
import de.cwlounge.cwloungedelobbysystem.commands.GMCommand;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    public static ConfigManager configmanager = new ConfigManager("Lobby").addString("host").addString("username")
            .addString("database").addString("password").addString("port");

    public static DatabaseManager db;

    public static Plugin plugin;

    //yOpo1efO1EwiG4PE64LOgu3OKO33w6

    @Override
    public void onEnable() {
        listenerRegistration();
        commandRegistration();
        Bukkit.getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        Navigator.setupNavigatorGUI();
        SettingHandler.setupSettingsInv();
        LobbyChangeTool.setupLobbyChangeInv();
        plugin = this;
        configmanager.setup();

        db = new DatabaseManager(configmanager.getString("host"), configmanager.getString("port"), configmanager.getString("username"), configmanager.getString("database"), configmanager.getString("password")).setAsync(false).Open();

        new PlayerManager(db);

        Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
            @Override
            public void run() {
                for (Player all : Bukkit.getOnlinePlayers()) {
                    if (all.getLocation().getY() <= 45) {
                        all.teleport(new Location(all.getWorld(), 0, 66,0));
                        all.playSound(all.getLocation(), Sound.ENDERMAN_TELEPORT, 1,1);
                    }
                }
            }
        }, 0, 1);

        Bukkit.getLogger().fine("Lobbysystem enabled.");
    }

    @Override
    public void onDisable() {
        Bukkit.getMessenger().unregisterIncomingPluginChannel(this);
        Bukkit.getLogger().fine("Lobbysystem disabled.");
    }

    private void commandRegistration() {
        getCommand("gm").setExecutor(new GMCommand());
    }

    private void listenerRegistration() {
        PluginManager manager = Bukkit.getPluginManager();
        manager.registerEvents(new JoinQuitListener(), this);
        manager.registerEvents(new DamageListener(), this);
        manager.registerEvents(new DoubleJump(), this);
        manager.registerEvents(new BlockInteractListener(), this);
        manager.registerEvents(new Navigator(), this);
        manager.registerEvents(new Hidingtool(), this);
        manager.registerEvents(new SilentLobby(), this);
        manager.registerEvents(new SettingHandler(), this);
        manager.registerEvents(new HungerEvent(), this);
        manager.registerEvents(new Movement(), this);
        manager.registerEvents(new LobbyChangeTool(), this);
        manager.registerEvents(new ChatListener(), this);
        manager.registerEvents(new cancelDrop(), this);
        manager.registerEvents(new cancelWeather(), this);
        manager.registerEvents(new cancelInvMove(), this);
    }

    public static Plugin getPlugin() {
        return plugin;
    }

    public static String getPrefix() {
        return configmanager.prefix();
    }
}