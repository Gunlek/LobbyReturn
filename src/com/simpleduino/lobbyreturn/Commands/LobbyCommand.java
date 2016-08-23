package com.simpleduino.lobbyreturn.Commands;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import com.simpleduino.lobbyreturn.LobbyReturnPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Simple-Duino on 07/07/2016.
 * Copyrights Simple-Duino, all rights reserved
 */

public class LobbyCommand implements CommandExecutor {

    File cfgFile = new File("plugins/LobbyReturn/config.yml");
    YamlConfiguration cfg = YamlConfiguration.loadConfiguration(cfgFile);

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        String[] lobby = cfg.get("returnLobby").toString().replace(" ", "").split(",");
        if(sender instanceof Player)
        {
            Player p = (Player)sender;
            ByteArrayDataOutput out = ByteStreams.newDataOutput();
            out.writeUTF("ConnectOther");
            out.writeUTF(p.getName());
            out.writeUTF(lobby[0]);

            p.sendPluginMessage(LobbyReturnPlugin.getPlugin(LobbyReturnPlugin.class), "BungeeCord", out.toByteArray());
        }
        return false;
    }
}
