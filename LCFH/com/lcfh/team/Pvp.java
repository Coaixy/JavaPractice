package com.lcfh.team;

import com.lcfh.Lcfh;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ForkJoinPool;

public class Pvp implements Listener {
    private final Lcfh plugin;
    public Pvp(Lcfh plugin) {
        this.plugin = plugin;
    }
    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event){
        if (event.getDamager() instanceof Player && event.getEntity() instanceof  Player){ //两个打架的是玩家
            Player damager  = (Player)event.getDamager();
            Player byDamege  = (Player)event.getEntity();
            File file = new File(plugin.getDataFolder(), "team.yml");//军团配置文件
            YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
            Set<String> set = config.getKeys(false);
            List<String> setList=new ArrayList<>(set);
                //如果是同一个军团的就不能造成PVP伤害
            for (int i = 0 ; i < set.size();i++) {
                int tn = config.getList(setList.get(i) + ".players").size();
                for (int j = 0; j < tn; j++) {
                    int ok = 0;
                    if (damager.getName().equals(config.getStringList(setList.get(i) + ".players").get(j))) {
                        ok=1;
                    }
                    if (byDamege.getName().equals(config.getStringList(setList.get(i) + ".players").get(j)) && ok == 1){
                        damager.sendMessage("你不能攻击你的队友");
                        byDamege.sendMessage("你不能攻击你的队友");
                    }
                }
                if (damager.getName().equals(config.getString(setList.get(i) + ".owner"))) {
                    tn = config.getList(setList.get(i) + ".players").size();
                    for (int j = 0; j < tn; j++) {
                        if (byDamege.getName().equals(config.getStringList(setList.get(i) + ".players").get(j))){
                            damager.sendMessage("你不能攻击你的队友");
                            byDamege.sendMessage("你不能攻击你的队友");
                        }
                    }
                }
                if (byDamege.getName().equals(config.getString(setList.get(i) + ".owner"))) {
                    tn = config.getList(setList.get(i) + ".players").size();
                    for (int j = 0; j < tn; j++) {
                        if (damager.getName().equals(config.getStringList(setList.get(i) + ".players").get(j))){
                            damager.sendMessage("你不能攻击你的队友");
                            byDamege.sendMessage("你不能攻击你的队友");
                        }
                    }
                }
            }
        }
    }
}
