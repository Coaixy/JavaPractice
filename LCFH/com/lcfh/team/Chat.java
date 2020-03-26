package com.lcfh.team;

import com.lcfh.Lcfh;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

//给军团人员加上聊天前缀
public class Chat implements Listener{
    private final Lcfh plugin;
    public Chat(Lcfh plugin) {
        this.plugin = plugin;
    }
    @EventHandler
    public void onChat(AsyncPlayerChatEvent event){
        File file = new File(plugin.getDataFolder(), "team.yml");//军团配置文件
        YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
        File file2 = new File(plugin.getDataFolder(), "fortess.yml"); //堡垒配置文件
        YamlConfiguration fconfig = YamlConfiguration.loadConfiguration(file2);
        Player player = event.getPlayer();
        Set<String> set = config.getKeys(false);
        List<String> setList=new ArrayList<>(set);
        int ok = 0 ;
        for (int i = 0 ; i < set.size();i++){
            int tn = config.getList(setList.get(i)+".players").size();
            for (int j = 0; j < tn; j++) {
                if(player.getName().equals(config.getStringList(setList.get(i)+".players").get(j))){
                    player.setDisplayName("【"+setList.get(i)+"】"+player.getName());
                    ok = 1;
                }
            }
            if(player.getName().equals(config.getString(setList.get(i)+".owner"))){
                player.setDisplayName("【"+setList.get(i)+"】"+player.getName());
                ok = 1;
            }
        }
        if (ok!=1){
            YamlConfiguration Dconfig = (YamlConfiguration) plugin.getConfig();
            String a = "【自由人】";
            a += Dconfig.get(player.getName());
            player.setDisplayName(a+player.getName());

        }
    }
}
