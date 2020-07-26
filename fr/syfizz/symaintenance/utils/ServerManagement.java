package fr.syfizz.symaintenance.utils;

import fr.syfizz.symaintenance.SyMaintenance;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class ServerManagement {

    public static void kickUnallowedPlayers(){
        for(Player player : Bukkit.getOnlinePlayers()){
            if (player.isOp() || SyMaintenance.getAUTHORIZED().contains(player.getUniqueId())){
                continue;
            } else {
                player.kickPlayer(ChatColor.translateAlternateColorCodes('&', Messages.PLAYER_KICK_MESSAGE.getMessage()));
            }
        }
    }

}
