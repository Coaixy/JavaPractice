import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        String Burl = null;
        String html = "";
        if (args.length==1){
            Burl = "https://api.bilibili.com/x/web-interface/archive/stat?bvid="+args[0];
        }else{
            Scanner sc=new Scanner(System.in);
            String str=sc.nextLine();
            Burl = "https://api.bilibili.com/x/web-interface/archive/stat?bvid="+str;
        }
        try{
            URL url = new URL(Burl);
            BufferedReader reader=new BufferedReader(new InputStreamReader(url.openStream()));
            String line;
            System.out.println();
            while((line=reader.readLine())!=null){
                html+=line;
            }
            reader.close();
        }catch (Exception e){
            e.printStackTrace();
        }
//        System.out.println(html);
        JsonObject jsonObject = (JsonObject) new JsonParser().parse(html);
        long av = jsonObject.getAsJsonObject().get("aid").getAsLong();
        System.out.println(av);
    }
}
