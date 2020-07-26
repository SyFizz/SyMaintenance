package fr.syfizz.symaintenance;

import fr.syfizz.symaintenance.runnables.MaintenanceDurationRunnable;
import fr.syfizz.symaintenance.runnables.ScheduledMaintenanceRunnable;
import fr.syfizz.symaintenance.utils.Messages;
import fr.syfizz.symaintenance.utils.ServerManagement;
import fr.syfizz.symaintenance.utils.YamlAuthorized;
import fr.syfizz.symaintenance.utils.YamlConfig;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.util.UUID;

public class MaintenanceCommand implements CommandExecutor {

    private final SyMaintenance main;
    public MaintenanceCommand(SyMaintenance main){this.main=main;}


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (label.equalsIgnoreCase("maintenance")){
            if (args.length==1){

                //Enabling maintenance mode
                if (args[0].equalsIgnoreCase("on")){
                    if (sender.isOp()){
                        if (!SyMaintenance.ENABLED){
                            if (!SyMaintenance.SCHEDULED) {
                                try {
                                    if(SyMaintenance.BROADCASTS_ENABLED) {
                                        Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', Messages.MAINTENANCE_ENABLED_BROADCAST.getMessage()));
                                    }
                                    SyMaintenance.ENABLED = true;
                                    new YamlConfig(main).writeConfig();
                                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Messages.MAINTENANCE_ENABLED.getMessage()));
                                    ServerManagement.kickUnallowedPlayers();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Messages.COMMAND_ERROR_OCCURED.getMessage()));
                                }
                            } else {
                                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Messages.MAINTENANCE_ALREADY_SCHEDULED.getMessage()));
                            }
                        } else {
                            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Messages.MAINTENANCE_ALREADY_ENABLED.getMessage()));
                        }
                    } else {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Messages.COMMAND_NO_PERM.getMessage()));
                    }


                //Disabling maintenance mode
                } else if (args[0].equalsIgnoreCase("off")){
                    if (sender.isOp()){
                        if (SyMaintenance.ENABLED){
                            try {
                                SyMaintenance.ENABLED=false;
                                new YamlConfig(main).writeConfig();
                                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Messages.MAINTENANCE_DISABLED.getMessage()));
                                if(SyMaintenance.BROADCASTS_ENABLED) {
                                    Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', Messages.MAINTENANCE_ENDED.getMessage()));
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Messages.COMMAND_ERROR_OCCURED.getMessage()));
                            }
                        } else if (SyMaintenance.SCHEDULED) {
                            SyMaintenance.SCHEDULED = false;
                            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Messages.MAINTENANCE_SCHEDULED_CANCELLED.getMessage()));
                        } else {
                            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Messages.MAINTENANCE_ALREADY_DISABLED.getMessage()));
                        }
                    } else {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Messages.COMMAND_NO_PERM.getMessage()));
                    }


                //Displaying list of allowed players
                } else if (args[0].equalsIgnoreCase("list")){
                    if(sender.isOp()) {
                        OfflinePlayer player;
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Messages.ALLOWED_LIST.getMessage()));
                        for (UUID uuid : SyMaintenance.getAUTHORIZED()) {
                            player = Bukkit.getOfflinePlayer(uuid);
                            if (player != null) {
                                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e-&a " + player.getName()));
                            }
                        }
                    } else {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Messages.COMMAND_NO_PERM.getMessage()));
                    }
                } else {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Messages.COMMAND_WRONG_ARGS.getMessage()));
                }

            } else if (args.length<1){
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Messages.COMMAND_TOO_FEW_ARGS.getMessage()));
            } else if (args.length>1){
                if (args[0].equalsIgnoreCase("on") || args[0].equalsIgnoreCase("off")){
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Messages.COMMAND_TOO_MANY_ARGS.getMessage()));
                } else if (args[0].equalsIgnoreCase("add") ||args[0].equalsIgnoreCase("remove")){
                    if(sender.isOp()) {


                        //Adding player to allowed players list
                        if (args[0].equalsIgnoreCase("add")) {
                            final String name = args[1];
                            final Player player = Bukkit.getPlayer(name);
                            try {
                                final UUID uuid = player.getUniqueId();
                                if (!SyMaintenance.getAUTHORIZED().contains(uuid)) {
                                    SyMaintenance.getAUTHORIZED().add(uuid);
                                    new YamlAuthorized(main).writeAllowed();
                                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Messages.ALLOWED_SUCCESSFULLY_ADDED.getMessage().replace("%p", player.getName())));
                                } else {
                                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Messages.ALLOWED_ALREADY_ADDED.getMessage().replace("%p", player.getName())));
                                }
                            } catch (Exception e) {
                                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Messages.ALLOWED_PLAYER_NOT_FOUND.getMessage().replace("%p", player.getName())));
                            }


                        //Removing player from allowed players list
                        } else if (args[0].equalsIgnoreCase("remove")) {
                            final String name = args[1];

                            final UUID uuid = Bukkit.getPlayer(name).getUniqueId();
                            try {
                                if (SyMaintenance.getAUTHORIZED().contains(uuid)) {
                                    SyMaintenance.getAUTHORIZED().remove(uuid);
                                    if(SyMaintenance.ENABLED){ServerManagement.kickUnallowedPlayers();}
                                    new YamlAuthorized(main).writeAllowed();
                                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Messages.ALLOWED_SUCCESSFULLY_REMOVED.getMessage().replace("%p", Bukkit.getPlayer(name).getName())));
                                } else {
                                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Messages.ALLOWED_NOT_IN_LIST.getMessage().replace("%p", Bukkit.getPlayer(name).getName())));
                                }
                            } catch (IOException e) {
                                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Messages.ALLOWED_PLAYER_NOT_FOUND.getMessage().replace("%p", "")));
                            }
                        }
                    } else {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Messages.COMMAND_NO_PERM.getMessage()));
                    }
                } else if (args[0].equalsIgnoreCase("schedule")){

                    final int delay = Integer.parseInt(args[1]);
                    SyMaintenance.DELAY_BEFORE_MAINTENANCE = delay;
                    if(!SyMaintenance.SCHEDULED) {
                        if(!SyMaintenance.ENABLED) {
                            new ScheduledMaintenanceRunnable(delay).runTaskTimer(main, 0L, 20L);
                            SyMaintenance.SCHEDULED = true;
                            if(SyMaintenance.BROADCASTS_ENABLED) {
                                Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', Messages.MAINTENANCE_SCHEDULED_BROADCAST_MESSAGE.getMessage()).replace("%s", delay + "&e&lsecondes"));
                            }
                            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Messages.MAINTENANCE_SUCCESSFULLY_SCHEDULED.getMessage().replace("%s", Integer.toString(delay))));
                        } else {
                            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Messages.MAINTENANCE_ALREADY_ENABLED.getMessage()));
                        }
                    } else {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Messages.MAINTENANCE_ALREADY_SCHEDULED.getMessage()));
                    }
                } else if (args[0].equalsIgnoreCase("duration")){
                    if(!SyMaintenance.SCHEDULED) {
                        if (!SyMaintenance.ENABLED) {
                            final int duration = Integer.parseInt(args[1]);
                            SyMaintenance.MAINTENANCE_DURATION = duration;
                            new MaintenanceDurationRunnable(duration).runTaskTimer(main, 0L, 20L);
                            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "maintenance on");
                            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Messages.MAINTENANCE_SUCCESSFULLY_ENABLED_DURATION.getMessage().replace("%s", Integer.toString(duration))));
                        } else {
                            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Messages.MAINTENANCE_ALREADY_ENABLED.getMessage()));
                        }
                    } else {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Messages.MAINTENANCE_ALREADY_SCHEDULED.getMessage()));
                    }
                } else {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Messages.COMMAND_WRONG_ARGS.getMessage()));
                }
            }
            return true;
        }
        return false;
    }
}
