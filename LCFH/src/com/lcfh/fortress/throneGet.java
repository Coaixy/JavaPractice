package com.lcfh.fortress;

import com.lcfh.Lcfh;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class throneGet implements Listener {
    private final Lcfh plugin;
    public throneGet(Lcfh plugin) {
        this.plugin = plugin;
    }
    @EventHandler
    public void onKill(PlayerDeathEvent event) throws IOException {
        Player player = (Player)event.getEntity();
        String name = player.getName();
        File file = new File(plugin.getDataFolder(), "throne.yml");
        YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
        Set<String> set = config.getKeys(false);
        List<String> setList=new ArrayList<>(set);
        for (int i = 0; i < setList.size(); i++) {
            if (config.getString(setList.get(i)+".owner").equals(name)){
                if(!config.getString(setList.get(i)+".player").equals(null)){
                    config.set(setList.get(i)+".owner",config.getString(setList.get(i)+".player"));
                    config.set(setList.get(i)+".player",null);
                    config.save(file);
                    player.sendMessage("你失去了你的王座");
                }
            }
            if (config.getString(setList.get(i)+".player").equals(name)){
                if(!config.getString(setList.get(i)+".player").equals(null)){
                    config.set(setList.get(i)+".player",null);
                    config.save(file);
                    player.sendMessage("你失败了！");
                }
            }
        }
    }
}
