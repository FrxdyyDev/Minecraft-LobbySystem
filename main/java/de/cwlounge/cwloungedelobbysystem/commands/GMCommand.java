package de.cwlounge.cwloungedelobbysystem.commands;

import de.cwlounge.cwloungedelobbysystem.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GMCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        if (player.hasPermission("cwlounge.lobby.gm")) {
            if (args.length == 2) {
                Player target = Bukkit.getPlayer(args[1]);
                if (target != null) {
                    if (target != player) {
                        switch (args[0]) {
                            case "0":
                                target.sendMessage("Du bist nun im Überlebensmodus!");
                                target.setGameMode(GameMode.SURVIVAL);
                                target.setAllowFlight(true);
                                target.setFlying(false);
                                player.sendMessage(target.getName() + " ist nun im Überlebensmodus");
                                break;
                            case "1":
                                target.sendMessage("Du bist nun im Kreativmodus!");
                                target.setGameMode(GameMode.CREATIVE);
                                target.setAllowFlight(true);
                                target.setFlying(false);
                                player.sendMessage(target.getName() + " ist nun im Kreativmodus");
                                break;
                            case "2":
                                target.sendMessage("Du bist nun im Abenteuermodus!");
                                target.setGameMode(GameMode.ADVENTURE);
                                target.setAllowFlight(true);
                                target.setFlying(false);
                                player.sendMessage(target.getName() + " ist nun im Abenteuermodus");
                                break;
                            case "3":
                                target.sendMessage("Du bist nun im Zuschauermodus!");
                                target.setGameMode(GameMode.SPECTATOR);
                                PlayerManager.getInstance().getLobbyPlayer(player).setCanFly(true);
                                PlayerManager.getInstance().getLobbyPlayer(player).setDoublejump(false);
                                target.setAllowFlight(true);
                                target.setFlying(false);
                                player.sendMessage(target.getName() + " ist nun im Zuschauermodus");
                                break;
                            default:
                                break;
                        }
                    }else {
                        player.sendMessage("Nutze /gm [0,1,2,3]");
                    }
                }else {
                    player.sendMessage(args[1] + " ist nicht online!");
                }
            }else {
                switch (args[0]) {
                    case "0":
                        player.sendMessage("Du bist nun im Überlebensmodus!");
                        player.setGameMode(GameMode.SURVIVAL);
                        player.setAllowFlight(true);
                        player.setFlying(false);
                        break;
                    case "1":
                        player.sendMessage("Du bist nun im Kreativmodus!");
                        player.setGameMode(GameMode.CREATIVE);
                        break;
                    case "2":
                        player.sendMessage("Du bist nun im Abenteuermodus!");
                        player.setGameMode(GameMode.ADVENTURE);
                        player.setAllowFlight(true);
                        player.setFlying(false);
                        player.setAllowFlight(true);
                        player.setFlying(false);
                        break;
                    case "3":
                        player.sendMessage("Du bist nun im Zuschauermodus!");
                        player.setGameMode(GameMode.SPECTATOR);
                        PlayerManager.getInstance().getLobbyPlayer(player).setCanFly(true);
                        PlayerManager.getInstance().getLobbyPlayer(player).setDoublejump(false);
                        player.setAllowFlight(true);
                        player.setFlying(false);
                        break;
                    default:
                        break;
                }
            }
        }else {
            player.sendMessage("Nicht genug Berechtigungen");
        }
        return false;
    }
}
