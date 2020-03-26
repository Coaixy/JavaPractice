package com.lcfh.fortress;

import com.lcfh.Lcfh;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class throneCmd implements CommandExecutor {
    private final Lcfh plugin;
    public throneCmd(Lcfh plugin) {
        this.plugin = plugin;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (sender instanceof Player){
            Player player = (Player)sender;
            if (cmd.getName().equalsIgnoreCase("throne")){
                File file = new File(plugin.getDataFolder(), "throne.yml");
                YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
                SimpleDateFormat df = new SimpleDateFormat("HH");//设置日期格式
                String date = df.format(new Date()); //获取小时
                if (args[0].equalsIgnoreCase("attack") && args.length==2){ //仅供
                    if (date.equalsIgnoreCase(config.getString(args[2]+".time"))){
                        if (config.getString(args[2]+".player").equalsIgnoreCase(null)){
                            config.set(args[2]+".player",player.getName());
                            try {
                                config.save(file);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            player.sendMessage("发起成功");
                            return true;
                        }
                    }
                }if (args[0].equalsIgnoreCase("run") && args.length==1){ //逃跑
                    Set<String> set = config.getKeys(false);
                    List<String> setList=new ArrayList<>(set);
                    for (int i = 0; i < setList.size(); i++) {
                        if (config.getString(setList.get(i)+".player").equals(player.getName())){
                            player.sendMessage("逃跑成功");
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}
