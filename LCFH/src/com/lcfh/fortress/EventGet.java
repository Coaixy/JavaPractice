package com.lcfh.fortress;

import com.lcfh.Lcfh;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class EventGet implements Listener {
    private final Lcfh plugin;
    public EventGet(Lcfh plugin) {
        this.plugin = plugin;
    }
    @EventHandler(priority = EventPriority.HIGHEST) //最高优先级
    public void onBreak(BlockBreakEvent event) throws IOException {
        Player player = event.getPlayer();
        String name = player.getName();
        File file = new File(plugin.getDataFolder(), "fortess.yml");
        YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
        Set<String> set = config.getKeys(false);
        List<String> setList=new ArrayList<>(set);
        for (int i = 0; i < setList.size(); i++) {
            List<String> pList = config.getStringList(setList.get(i)+".players");
            for (int j = 0; j < pList.size(); j++) {
                if (pList.get(j).equals(name)){//玩家是否在列表中
                    String location = config.getString(setList.get(i)+".l2");
                    Block block = event.getBlock();
                    String blocation = block.getLocation().getX()+","+block.getLocation().getY()+
                            ","+block.getLocation().getZ();
                    SimpleDateFormat df = new SimpleDateFormat("HH");//设置日期格式
                    String date = df.format(new Date()); //获取小时
                    if(location.equals(blocation)&&date.equals(config.getString(setList.get(i)+".date"))){ //破坏的坐标相等的话
                        if (config.getInt(setList.get(i)+".players."+pList.get(j))<10){
                            player.sendMessage("完成一次能量破坏");
                            event.setCancelled(true);
                            config.set(setList.get(i)+".players."+pList.get(j),config.getInt(setList.get(i)+".players."+pList.get(j))+1);
                            config.save(file);
                        }else{
                            plugin.getServer().broadcastMessage(player.getName()+"帮助"+setList.get(i)+"夺取了"+setList.get(i)+"堡垒");
                            for (int k = 0; k < pList.size(); k++) {
                                config.set(setList.get(i)+".players."+pList.get(k),null);
                                Player p = Bukkit.getPlayer(pList.get(k));
                                p.getLocation().setZ(player.getBedSpawnLocation().getZ());
                                p.getLocation().setX(player.getBedSpawnLocation().getX());
                                p.getLocation().setY(player.getBedSpawnLocation().getY());
                                //送回老家
                            }
                            config.save(file);
                            //破坏10次
                        }
                    }
                }else if(config.getString(setList.get(i)+".l2").equals(event.getBlock().getLocation().getX()+","+event.getBlock().getLocation().getY()+ ","+event.getBlock().getLocation().getZ())){
                    player.getLocation().setZ(player.getBedSpawnLocation().getZ());
                    player.getLocation().setX(player.getBedSpawnLocation().getX());
                    player.getLocation().setY(player.getBedSpawnLocation().getY());
                    //捣乱的送回家
                }
            }
        }
    }
}
