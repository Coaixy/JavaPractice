package io.github.coaixy;

import java.io.IOException;
import java.net.InetAddress;

public class DHCP_FIX {
    public static void main(String[] args) throws InterruptedException, IOException {
        Runtime runtime = Runtime.getRuntime();
        while (true){ //定时检测，连接失败自动重启DHCP
            if (getDelay()) {
                runtime.exec("net stop \"dhcp client\" & y\n");
                runtime.exec("net start \"dhcp client\"");
                Thread.sleep(10000);//考虑到重启时间，这里加个延迟
            }
            Thread.sleep(5000);
        }
    }
    //检测与百度是否能连接成功
    private static Boolean getDelay(){
        try{
            return InetAddress.getByName("www.baidu.com").isReachable(3000);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
