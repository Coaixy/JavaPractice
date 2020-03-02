package pw.yjxzx.fy2020;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
public class fy2020 extends JavaPlugin {
    int time;
    FileConfiguration config;
    boolean open;
    @Override
    public void onEnable(){
        getLogger().info ("【2020肺炎】插件载入成功");
        saveDefaultConfig();
        config = getConfig();
        open = config.getBoolean("open");
        getLogger().info ("【2020肺炎】定时提示状态为开启");
        time = config.getInt("time");
        Thread t = new Thread(
                () -> {
                    while(true){
                        while (open){ try {
                            Thread.sleep(1000*time);
                            getServer().broadcastMessage("【2020肺炎】"+getfydb());
                        } catch (InterruptedException | IOException e) {
                            e.printStackTrace();
                        } }
                        try {
                            Thread.sleep(1200000);
                            getLogger().info("【2020肺炎】定时器未开启");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });
        t.start();
    }
    @Override
    public void onDisable(){
        getLogger().info("【2020肺炎】插件卸载成功");
    }
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender.hasPermission("fy2020.getfy") && cmd.getName().equalsIgnoreCase("getfy")){
            String fydb = null;
            try {
                fydb = getfydb();
            } catch (IOException e) {
                e.printStackTrace();
            }
            sender.sendMessage("§e【2020肺炎】"+fydb);
            return true;
        }
        if (sender.hasPermission("fy2020.setfy") && cmd.getName().equalsIgnoreCase("setfy")){
            if (args.length==1){
                time = Integer.parseInt(args[0]);
                config.set("time",time);
                saveConfig();
                sender.sendMessage("【2020肺炎】设置成功");
                return true;
            }else{
                return false;
            }
        }
        if (sender.hasPermission("fy2020.openfy") && cmd.getName().equalsIgnoreCase("openfy")){
            if (args.length==1){
                open = Boolean.parseBoolean(args[0]);
                config.set("open",open);
                sender.sendMessage("【2020肺炎】当前定时器："+ open);
                saveConfig();
                sender.sendMessage("【2020肺炎】设置成功");
                return true;
            }else{
                return false;
            }
        }
        return false;
    }
    public static String getfydb() throws IOException { //获取最新的肺炎数据
        Document doc = Jsoup.connect("http://news.163.com/special/epidemic/").get();
        Element element_sum = doc.select("div.cover_tit_des").first();
        String sdb = element_sum.text();
        String sumdb = getSubString(sdb,"确诊 "," 例，死亡");
        String die = getSubString(sdb,"，死亡 "," 例，治愈");
        String glive = getSubString(sdb,"，治愈"," 例");
        return "当前总确诊人数："+sumdb + "\n"+"当前总死亡人数："+die+"\n"+"当前总治愈人数："+glive;
    }
    public static String getSubString(String text, String left, String right) { //取文本中间
        String result;
        int zLen;
        if (left == null || left.isEmpty()) {
            zLen = 0;
        } else {
            zLen = text.indexOf(left);
            if (zLen > -1) {
                zLen += left.length();
            } else {
                zLen = 0;
            }
        }
        int yLen = text.indexOf(right, zLen);
        if (yLen < 0 || right.isEmpty()) {
            yLen = text.length();
        }
        result = text.substring(zLen, yLen);
        return result;
    }
}
