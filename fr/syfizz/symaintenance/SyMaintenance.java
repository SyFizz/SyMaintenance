package fr.syfizz.symaintenance;

import fr.syfizz.symaintenance.utils.YamlAuthorized;
import fr.syfizz.symaintenance.utils.YamlConfig;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class SyMaintenance extends JavaPlugin {

    public static boolean ENABLED = false;
    public static boolean SCHEDULED = false;
    public static boolean BROADCASTS_ENABLED = true;
    public static int DELAY_BEFORE_MAINTENANCE = 0;
    public static int MAINTENANCE_DURATION = 0;

    private PluginManager pm;
    private static Set<UUID> AUTHORIZED = new HashSet<>();

    public void onEnable(){
        readList();
        readMaintenanceState();
        pm = Bukkit.getServer().getPluginManager();
        getCommand("maintenance").setExecutor(new MaintenanceCommand(this));
        pm.registerEvents(new PlayerConnectListener(), this);


    }

    public void onDisable(){

    }

    public static Set<UUID> getAUTHORIZED() {
        return AUTHORIZED;
    }

    private void readList(){

        SyMaintenance.AUTHORIZED = new YamlAuthorized(this).readAllowed();

    }
    private void readMaintenanceState(){
        new YamlConfig(this).readConfig();
    }
}
