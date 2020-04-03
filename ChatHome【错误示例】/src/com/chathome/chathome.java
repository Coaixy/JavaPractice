package com.chathome;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class chathome extends JavaPlugin {
    Home[] homes;
    int homeNum = 0;
    @Override
    public void onEnable(){
        getServer().getConsoleSender().sendMessage("§e【ChatHome】§a载入成功");
    }
    @Override
    public void onDisable(){
        getServer().getConsoleSender().sendMessage("§e【ChatHome】§a卸载成功");
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player && command.getName().equalsIgnoreCase("ch")){
            Player p = (Player)sender;
            if (args.length == 0 ){
                help(p);
                return false;
            }
            if(args.length == 2 && args[0].equalsIgnoreCase("create")){
                //创建聊天室
                boolean joined = false;
                for (int i = 0; i < homes.length; i++) {
                    if (homes[i].getOwner().equals(p.getName())){joined = true;break;} //是房主
                    for (int j = 0; j < homes[i].getNums(); j++) {//是成员
                        if (homes[i].getPlayers()[j].equals(p.getName())){
                            joined = true;break;
                        }
                    }
                }
                if (!joined){
                    homes[homeNum] = new Home(p.getName(),args[1]); //成为房主
                    homeNum++;
                }else{
                    p.sendMessage("§4【ChatHome】§3你已经加入了一个聊天室");
                    return false;
                }
                p.sendMessage("§4【ChatHome】§3你成功创建了"+args[1]);
                return true;
            }
            if (args.length == 2 && args[0].equalsIgnoreCase("join")){
                //加入聊天室
                boolean joined = false;
                for (int i = 0; i < homes.length; i++) {
                    if (homes[i].getOwner().equals(p.getName())){joined = true;break;} //是房主
                    for (int j = 0; j < homes[i].getPlayers().length; j++) {//是成员
                        if (homes[i].getPlayers()[j].equals(p.getName())){
                            joined = true;break;
                        }
                    }
                }
                if (joined){
                    p.sendMessage("§4【ChatHome】§3你已经加入了一个聊天室");
                    return false;
                }
                Home home = null;
                for (int i = 0; i < homeNum; i++) {
                    if (homes[i].getName().equals(args[1])){
                        home = homes[i];
                        String[] players = home.getPlayers();
                        int nums = home.getNums();
                        home.setNums(nums+1);
                        players[nums] = p.getName();
                        home.setPlayers(players);
                        p.sendMessage("§4【ChatHome】§3你成功加入了"+args[1]);
                        return true;
                    }
                }
            }
        }
        return false;
    }
    private void help(Player p) {
        String helpInfo = "§3---------------§4ChatHome§3---------------\n" +
                "/Ch Create <Name> 创建聊天室\n" +
                "/Ch Leave 离开聊天室\n" +
                "/Ch Join <Name> 加入聊天室" +
                "/Ch chat 开启聊天室模式\n" +
                "/ch Lchat 关闭聊天室模式\n" +
                "§3---------------§4ChatHome§3---------------";
        p.sendMessage(helpInfo);
    }
}
