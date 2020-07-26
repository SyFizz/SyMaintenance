package fr.syfizz.symaintenance.utils;

import fr.syfizz.symaintenance.SyMaintenance;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class YamlConfig {
    private static final String CONFIG_NAME = "config.yml";
    private final YamlConfiguration configuration;
    private final File savedFile;

    public YamlConfig(SyMaintenance main){
        this.savedFile = new File(main.getDataFolder(), CONFIG_NAME);
        this.configuration = YamlConfiguration.loadConfiguration(savedFile);
    }

    public void writeConfig() throws IOException {
        configuration.set("maintenance_state", SyMaintenance.ENABLED);
        configuration.set("broadcasts_enabled", SyMaintenance.BROADCASTS_ENABLED);
        configuration.save(savedFile);
    }
    public void readConfig(){
        SyMaintenance.ENABLED = configuration.getBoolean("maintenance_state");
        SyMaintenance.BROADCASTS_ENABLED = true;
    }
}

