package com.lcfh.team;

import com.lcfh.Lcfh;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Login implements Listener {
    private final Lcfh plugin;
    public Login(Lcfh plugin) {
        this.plugin = plugin;
    }
    @EventHandler(priority = EventPriority.LOW)//低优先级
    public void onLogin(PlayerJoinEvent event) throws IOException {
        Player player = event.getPlayer();
        player.setWalkSpeed((float) (player.getWalkSpeed()+0.2));
        File file2 = new File(plugin.getDataFolder(), "fortess.yml"); //堡垒配置文件
        YamlConfiguration fconfig = YamlConfiguration.loadConfiguration(file2);
        Set<String> set = fconfig.getKeys(false);
        List<String> setList=new ArrayList<>(set);
        for (int i = 0; i < setList.size(); i++) {
            int tn = fconfig.getList(setList.get(i)+".players").size();
            for (int j = 0; j < tn; j++) {
                if(player.getName().equals(fconfig.getStringList(setList.get(i)+".players").get(j))){
                    List <String> list = fconfig.getStringList(setList.get(i)+".players");
                    Iterator<String> it = list.iterator();
                    while(it.hasNext()){
                        String str = (String)it.next();
                        if(player.getName().equals(str)){
                            it.remove();
                        }
                    }
                    fconfig.set(setList.get(i)+".players",list);
                    player.sendMessage("已清除你的争霸战信息");
                    fconfig.save(file2);
                }
            }
        }
    }
}
