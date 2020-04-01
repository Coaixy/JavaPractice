package com.lcfh.team;

import com.lcfh.Lcfh;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Command implements CommandExecutor {
    private final Lcfh plugin;
    public Command(Lcfh plugin) {
        this.plugin = plugin;
    }
    @Override
    public boolean onCommand(CommandSender commandSender, org.bukkit.command.Command cmd, String s, String[] args) {
        if (commandSender instanceof Player){
            Player player = (Player)commandSender;
            if(cmd.getName().equalsIgnoreCase("team")){
                File file = new File(plugin.getDataFolder(), "team.yml");//军团配置文件
                YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
                File file2 = new File(plugin.getDataFolder(), "fortess.yml"); //堡垒配置文件
                YamlConfiguration fconfig = YamlConfiguration.loadConfiguration(file2);
                //载入配置文件
                if(args.length == 2){
                    if(args[0].equalsIgnoreCase("Create")){ //创建军团
                        if (player.hasPermission("team.create")){
                            Set<String> set = config.getKeys(false);
                            List<String> setList=new ArrayList<>(set);
                            for (int i = 0 ; i < set.size();i++){
                                int tn = config.getList(setList.get(i)+".players").size();
                                for (int j = 0; j < tn; j++) {
                                    if(player.getName().equals(config.getStringList(setList.get(i)+".players").get(j))){
                                        player.sendMessage("你已经加入或统领了某个军团");
                                        return false;
                                    }
                                }
                                if(player.getName().equals(config.getString(setList.get(i)+".owner"))){
                                    player.sendMessage("你已经加入或统领了某个军团");
                                    return false;
                                }
                            }
                            config.set(args[1]+".owner",player.getName());
                            try {
                                config.save(file);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            return true;
                        }
                    }
                    if(args[0].equalsIgnoreCase("Join")){ //加入军团
                        Set<String> set = config.getKeys(false);
                        List<String> setList=new ArrayList<>(set);
                        for (int i = 0 ; i < set.size();i++){
                            int tn = config.getList(setList.get(i)+".players").size();
                            for (int j = 0; j < tn; j++) {
                                if(player.getName().equals(config.getStringList(setList.get(i)+".players").get(j))){
                                    player.sendMessage("你已经加入或统领了某个军团");
                                    return false;
                                }
                            }
                            if(player.getName().equals(config.getString(setList.get(i)+".owner"))){
                                player.sendMessage("你已经加入或统领了某个军团");
                                return false;
                            }
                        }
                        config.set(args[1]+".players",config.getStringList(args[1]+".players").add(player.getName()));
                        try {
                            config.save(file);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        player.sendMessage("你已经成功加入"+args[1]+"军团");
                        return true;
                    }
                    if(args[0].equalsIgnoreCase("attack")){ //进攻
                        Set<String> set = config.getKeys(false);
                        List<String> setList=new ArrayList<>(set);
                        for (int i = 0 ; i < set.size();i++){
                            if(player.getName().equals(config.getString(setList.get(i)+".owner"))){
                                config.set(setList.get(i)+".attack",args[1]);
                                player.sendMessage("发起成功");
                                try {
                                    config.save(file);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                return true;
                            }
                        }
                        return false;
                    }
                    if(args[0].equalsIgnoreCase("rush")){ //进攻
                        Set<String> set = config.getKeys(false);
                        List<String> setList=new ArrayList<>(set);
                        for (int i = 0 ; i < set.size();i++){
                            if(player.getName().equals(config.getString(setList.get(i)+".owner"))){
                                config.set(setList.get(i)+".attack","");
                                player.sendMessage("撤退成功");
                                try {
                                    config.save(file);
                                }catch (IOException e) {
                                    e.printStackTrace();
                                }
                                return true;
                            }
                        }
                        return false;
                    }
                }else if(args.length == 1){
                    if(args[0].equalsIgnoreCase("leave")){ //离开军团
                        Set<String> set = config.getKeys(false);
                        List<String> setList=new ArrayList<>(set);
                        for (int i = 0 ; i < set.size();i++){
                            int tn = config.getList(setList.get(i)+".players").size();
                            for (int j = 0; j < tn; j++) {
                                if(player.getName().equals(config.getStringList(setList.get(i)+".players").get(j))){
                                    List <String> list = config.getStringList(setList.get(i)+".players");
                                    Iterator<String> it = list.iterator();
                                    while(it.hasNext()){
                                        String str = (String)it.next();
                                        if(player.getName().equals(str)){
                                            it.remove();
                                        }
                                    }
                                    config.set(setList.get(i)+".players",list);
                                    player.sendMessage("离开成功");
                                    try {
                                        config.save(file);
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    return true;
                                }
                            }
                            if(player.getName().equals(config.getString(setList.get(i)+".owner"))){
                                config.set(setList.get(i),null);
                                player.sendMessage("离开成功");
                                try {
                                    config.save(file);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                return true;
                            }
                        }return false;
                    }
                    if(args[0].equalsIgnoreCase("aj")){ //加入堡垒争夺战
                        Set<String> set = config.getKeys(false);//获取所有key
                        List<String> setList=new ArrayList<>(set);
                        for (int i = 0 ; i < set.size();i++){//所有军团名
                            int tn = config.getList(setList.get(i)+".players").size();
                            for (int j = 0; j < tn; j++) { //成员列表
                                if(player.getName().equals(config.getStringList(setList.get(i)+".players").get(j))){
                                    String attack = config.getString(setList.get(i)+".attack");
                                    String[] loction = fconfig.getString(attack+".l1").split(",");
                                    player.getLocation().setX(Double.parseDouble(loction[0]));
                                    player.getLocation().setY(Double.parseDouble(loction[1]));
                                    player.getLocation().setZ(Double.parseDouble(loction[2]));
                                    player.sendMessage("加入成功");
                                    fconfig.set(setList.get(i)+".players",fconfig.getStringList(setList.get(i)+".players").add(player.getName()));
                                    fconfig.set(setList.get(i)+".players."+player.getName(),0);
                                    try {
                                        fconfig.save(file2);
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    return true;
                                }
                            }
                            if(player.getName().equals(config.getString(setList.get(i)+".owner"))){//领导者列表
                                String attack = config.getString(setList.get(i)+".attack");
                                String[] loction = fconfig.getString(attack+".l1").split(",");
                                player.getLocation().setX(Double.parseDouble(loction[0]));
                                player.getLocation().setY(Double.parseDouble(loction[1]));
                                player.getLocation().setZ(Double.parseDouble(loction[2]));
                                player.sendMessage("加入成功");
                                fconfig.set(setList.get(i)+".players",fconfig.getStringList(setList.get(i)+".players").add(player.getName()));
                                try {
                                    fconfig.save(file2);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                return true;
                            }
                        }return false;
                    }
                    if(args[0].equalsIgnoreCase("al")){ //离开堡垒争夺战
                        Set<String> set = config.getKeys(false);//获取所有key
                        List<String> setList=new ArrayList<>(set);
                        for (int i = 0 ; i < set.size();i++){//所有军团名
                            int tn = config.getList(setList.get(i)+".players").size();
                            for (int j = 0; j < tn; j++) { //成员列表
                                if(player.getName().equals(config.getStringList(setList.get(i)+".players").get(j))){
                                    String attack = config.getString(setList.get(i)+".attack");
                                    List <String> list = fconfig.getStringList(attack+".players");
                                    Iterator<String> it = list.iterator();
                                    while(it.hasNext()){
                                        String str = (String)it.next();
                                        if(player.getName().equals(str)){
                                            it.remove();
                                        }
                                    }
                                    player.sendMessage("离开成功");
                                    fconfig.set(attack+".players",fconfig.getStringList(setList.get(i)+".players").add(player.getName()));
                                    try {
                                        fconfig.save(file2);
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    return true;
                                }
                            }
                            if(player.getName().equals(config.getString(setList.get(i)+".owner"))){//领导者列表
                                String attack = config.getString(setList.get(i)+".attack");
                                List <String> list = fconfig.getStringList(attack+".players");
                                Iterator<String> it = list.iterator();
                                while(it.hasNext()){
                                    String str = (String)it.next();
                                    if(player.getName().equals(str)){
                                        it.remove();
                                    }
                                }
                                player.sendMessage("离开成功");
                                fconfig.set(setList.get(i)+".players",fconfig.getStringList(setList.get(i)+".players").add(player.getName()));
                                try {
                                    fconfig.save(file2);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                return true;
                            }
                        }return false;
                    }
                    if(args[0].equalsIgnoreCase("status")){ //查看军团信息
                        Set<String> set = config.getKeys(false);
                        List<String> setList=new ArrayList<>(set);
                        for (int i = 0 ; i < set.size();i++){
                            int tn = config.getList(setList.get(i)+".players").size();
                            for (int j = 0; j < tn; j++) {
                                if(player.getName().equals(config.getStringList(setList.get(i)+".players").get(j))){
                                    player.sendMessage("你好,亲爱的"+player.getName()+"\n你是"+setList.get(i)+"的成员\n" +
                                            "你加入的军团拥有"+(config.getStringList(setList.get(i)+".players").size()+1)+"个成员");
                                    return true;
                                }
                            }
                            if(player.getName().equals(config.getString(setList.get(i)+".owner"))){
                                player.sendMessage("你好,亲爱的"+player.getName()+"\n你是"+setList.get(i)+"的领导者\n" +
                                        "你的军团拥有"+(config.getStringList(setList.get(i)+".players").size()+1)+"个成员");
                                return true;
                            }
                        }return false;
                    }
            }
        }
    }return false; }
}
