package com.lcfh;

import com.lcfh.fortress.EventGet;
import com.lcfh.fortress.throneCmd;
import com.lcfh.team.Chat;
import com.lcfh.team.Login;
import com.lcfh.team.Pvp;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class Lcfh extends JavaPlugin {
    @Override
    public void onEnable(){
        getLogger().info("【军团争霸】载入成功");
        saveDefaultConfig();
        getServer().getPluginManager().registerEvents(new Login(this), this);
        getServer().getPluginManager().registerEvents(new EventGet(this), this);
        getServer().getPluginManager().registerEvents(new Pvp(this), this);
        getServer().getPluginManager().registerEvents(new Chat(this), this);
        File file = new File(getDataFolder(), "team.yml");
        if (!file.exists()){saveResource("team.yml",false);}
        File file1 = new File(getDataFolder(), "fortress.yml");
        if (!file1.exists()){saveResource("fortress.yml",false);}
        this.getCommand("team").setExecutor(new com.lcfh.team.Command(this));
        this.getCommand("throne").setExecutor(new com.lcfh.team.Command(this));
        this.getCommand("fortress").setExecutor(new throneCmd(this));
    }
    @Override
    public void onDisable(){
        getLogger().info("【军团争霸】卸载成功");
    }
}
