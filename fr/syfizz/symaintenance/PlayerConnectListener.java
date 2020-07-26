package fr.syfizz.symaintenance;

import fr.syfizz.symaintenance.utils.Messages;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerConnectListener implements Listener {

    @EventHandler
    public void onPlayerLogin(PlayerJoinEvent e){

        final Player player = e.getPlayer();
        if(SyMaintenance.ENABLED){
            if(player.isOp() || SyMaintenance.getAUTHORIZED().contains(player.getUniqueId())){
                return;
            } else {
                player.kickPlayer(ChatColor.translateAlternateColorCodes('&', Messages.PLAYER_KICK_MESSAGE.getMessage()));
            }
        }

    }
}
