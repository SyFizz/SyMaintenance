package fr.syfizz.symaintenance.runnables;

import fr.syfizz.symaintenance.SyMaintenance;
import fr.syfizz.symaintenance.utils.Messages;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;

public class MaintenanceDurationRunnable extends BukkitRunnable {

    public int time;
    public MaintenanceDurationRunnable(int time) {
        this.time = time;
    }


    @Override
    public void run() {
        if(SyMaintenance.ENABLED) {
            if (time == 3600 && SyMaintenance.BROADCASTS_ENABLED) {
                Bukkit.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', Messages.MAINTENANCE_SCHEDULED_END_BROADCAST_MESSAGE.getMessage()).replace("%s", "1 §eheure"));
            } else if (time == 60 * 30 && SyMaintenance.BROADCASTS_ENABLED) {
                Bukkit.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', Messages.MAINTENANCE_SCHEDULED_END_BROADCAST_MESSAGE.getMessage()).replace("%s", "30 §eminutes"));
            } else if (time == 600 && SyMaintenance.BROADCASTS_ENABLED) {
                Bukkit.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', Messages.MAINTENANCE_SCHEDULED_END_BROADCAST_MESSAGE.getMessage()).replace("%s", "10 §eminutes"));
            } else if (time == 60 * 5 && SyMaintenance.BROADCASTS_ENABLED) {
                Bukkit.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', Messages.MAINTENANCE_SCHEDULED_END_BROADCAST_MESSAGE.getMessage()).replace("%s", "5 §eminutes"));
            } else if (time == 60 && SyMaintenance.BROADCASTS_ENABLED) {
                Bukkit.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', Messages.MAINTENANCE_SCHEDULED_END_BROADCAST_MESSAGE.getMessage()).replace("%s", "1 §eminute"));
            } else if (time == 30 && SyMaintenance.BROADCASTS_ENABLED) {
                Bukkit.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', Messages.MAINTENANCE_SCHEDULED_END_BROADCAST_MESSAGE.getMessage()).replace("%s", "30 §esecondes"));
            } else if (time == 10 && SyMaintenance.BROADCASTS_ENABLED) {
                Bukkit.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', Messages.MAINTENANCE_SCHEDULED_END_BROADCAST_MESSAGE.getMessage()).replace("%s", "10 §esecondes"));
            } else if (time == 5 && SyMaintenance.BROADCASTS_ENABLED) {
                Bukkit.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', Messages.MAINTENANCE_SCHEDULED_END_BROADCAST_MESSAGE.getMessage()).replace("%s", "5 §esecondes"));
            } else if (time == 4 && SyMaintenance.BROADCASTS_ENABLED) {
                Bukkit.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', Messages.MAINTENANCE_SCHEDULED_END_BROADCAST_MESSAGE.getMessage()).replace("%s", "4 §esecondes"));
            } else if (time == 3 && SyMaintenance.BROADCASTS_ENABLED) {
                Bukkit.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', Messages.MAINTENANCE_SCHEDULED_END_BROADCAST_MESSAGE.getMessage()).replace("%s", "3 §esecondes"));
            } else if (time == 2 && SyMaintenance.BROADCASTS_ENABLED) {
                Bukkit.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', Messages.MAINTENANCE_SCHEDULED_END_BROADCAST_MESSAGE.getMessage()).replace("%s", "2 §esecondes"));
            } else if (time == 1 && SyMaintenance.BROADCASTS_ENABLED) {
                Bukkit.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', Messages.MAINTENANCE_SCHEDULED_END_BROADCAST_MESSAGE.getMessage()).replace("%s", "1 §esecondes"));
            } else if (time == 0) {
                if(SyMaintenance.BROADCASTS_ENABLED){
                    Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', Messages.MAINTENANCE_ENDED.getMessage()));
                }
                Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "maintenance off");
                this.cancel();
                return;
            }
            time--;
        } else {
            System.out.println("Error : Maintenance disabled while running.");
            if(SyMaintenance.BROADCASTS_ENABLED) {
                Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', Messages.MAINTENANCE_ENDED.getMessage()));
            }
            this.cancel();
            return;
        }
    }
}
