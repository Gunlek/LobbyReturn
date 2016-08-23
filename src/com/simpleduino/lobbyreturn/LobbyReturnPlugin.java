package com.simpleduino.lobbyreturn;

import com.simpleduino.lobbyreturn.Commands.LobbyCommand;
import com.simpleduino.lobbyreturn.Messaging.MessageListener;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

/**
 * Created by Simple-Duino on 07/07/2016.
 * Copyrights Simple-Duino, all rights reserved
 */

public class LobbyReturnPlugin extends JavaPlugin {

    public void onEnable()
    {
        this.getServer().getPluginCommand("lobby").setExecutor(new LobbyCommand());

        this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        this.getServer().getMessenger().registerIncomingPluginChannel(this, "BungeeCord", new MessageListener());

        File cfgFile = new File("plugins/LobbyReturn/config.yml");
        if(!cfgFile.exists())
        {
            cfgFile.getParentFile().mkdirs();
            try {
                cfgFile.createNewFile();
                YamlConfiguration cfg = YamlConfiguration.loadConfiguration(cfgFile);
                cfg.set("returnLobby", "lb_1");
                cfg.save(cfgFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void onDisable()
    {

    }

}
