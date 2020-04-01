package com.lcfh.fortress;

import com.lcfh.Lcfh;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;

public class Command implements CommandExecutor {
    private final Lcfh plugin;
    public Command(Lcfh plugin) {
        this.plugin = plugin;
    }
    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command cmd, String label, String[] args) {
        if (sender instanceof Player){
            Player player = (Player)sender;
            if (cmd.getName().equalsIgnoreCase("fortress") && player.isOp()){
                File file = new File(plugin.getDataFolder(), "fortess.yml"); //堡垒配置文件
                YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
                //设置堡垒的名称和坐标
                if (args[0].equalsIgnoreCase("create") && args.length==2){
                    String location = player.getLocation().getX()+","+(player.getLocation().getY()-1)+","+player.getLocation().getZ();
                    config.set(args[1]+".l2",location);
                }
                if (args[0].equalsIgnoreCase("setl1") && args.length==2){
                    String location = player.getLocation().getX()+","+(player.getLocation().getY()-1)+","+player.getLocation().getZ();
                    config.set(args[1]+".l1",location);
                }
            }
        }
        return false;
    }
}
