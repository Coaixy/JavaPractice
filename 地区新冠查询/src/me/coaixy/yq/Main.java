package me.coaixy.yq;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.swing.*;
import java.net.URLEncoder;


public class Main {
    public static void main(String[] args){
        System.out.println("Made By 陈奕杰");
        String location = JOptionPane.showInputDialog( "请输入位置");
        if (location.equals("")){
            JOptionPane.showMessageDialog(null,"请输入正确数值","错误",JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
        String html = get_html(location);
        Document doc = Jsoup.parse(html);
        Elements numbers = doc.getElementsByClass("total virus-item-text");
        String[] texts = new String[4];
        int i =0;
        for (Element number : numbers) {
            texts[i] = number.text();
            i++;
        }
        String message = "现有确诊:"+texts[0]+"    累计确诊:"+texts[1]+"    累计治愈:"+texts[2]+"    累计死亡:"+texts[3];
        JOptionPane.showMessageDialog(null,message,location+"的当前疫情",JOptionPane.PLAIN_MESSAGE);
        System.exit(0);
    }

    /**
     * 获取网页源码
     * @return 源码
     */
    public static String get_html(String location){
        try{
            location = location+"疫情";
            String url = "https://www.baidu.com/s?ie=utf-8&f=8&rsv_bp=1&tn=baidu&wd="+ URLEncoder.encode(location,"UTF-8");
            System.out.println(url);
            return Jsoup.connect(url).get().html();
        }catch (Exception e){
            return "error";
        }
    }
}
