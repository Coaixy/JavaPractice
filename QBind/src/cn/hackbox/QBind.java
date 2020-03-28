package cn.hackbox;

import java.io.*;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicLong;


public class QBind
{
    public static AtomicLong i = new AtomicLong();
    public static long max;
    public static long lenth;
    public static StringBuilder endY;
    public static String head;
    public static String foot;

    public static void main(String[] args) throws IOException {
        String phone = null;//获取手机号样式
        phone  = new Scanner(System.in).nextLine();
        String unKonw = getSubString(phone,"#","#");
        args = phone.split("#");
        lenth  = unKonw.length();//获取未知长度
        int tnum = Integer.parseInt(args[3]);
        StringBuilder sb  = new StringBuilder("");
        for (int i = 0; i < lenth; i++) {
            sb.append("9");
        }
        max = Long.parseLong(sb.toString()); //获取最大数值
        System.out.println(max);
        head = args[0];
        foot = args[2];
        File f =  new File("p.csv");
        if (!f.exists()){f.createNewFile();}
        endY = new  StringBuilder("姓,名,前缀,后缀,其他手机\n");
        Writer fos = new FileWriter(f);
        new Thread(
                ()->{
                    while(i.get() <max){
                        i.getAndIncrement();
//                        System.out.println(i.get());
                        StringBuilder stringBuilder = new StringBuilder(String.valueOf(i.get()));
                        while(stringBuilder.toString().length()!=lenth){
                            stringBuilder.insert(0,0);
                        }
                        String ephone =head+stringBuilder.toString()+foot;
                        endY.append(i.get()+",,,,"+ephone+"\n");
                    }
                    try {
                        fos.write(endY.toString());
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
        ).start();
    }
    public void t(int n ){
        for (int j = 0; j < n-1; j++) {
            new Thread(
                    ()->{
                        while(i.get() <max){
                            i.getAndIncrement();
                            StringBuilder stringBuilder = new StringBuilder(String.valueOf(i.get()));
                            while(stringBuilder.toString().length()!=lenth){
                                stringBuilder.insert(0,0);
                            }
                            String ephone =head+stringBuilder.toString()+foot;
                            endY.append(i.get()+",,,,"+ephone+"\n");
                        }
                    }
            ).start();
        }
    }
    public static String getSubString(String text, String left, String right) {
        String result = "";
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
        if (yLen < 0 || right == null || right.isEmpty()) {
            yLen = text.length();
        }
        result = text.substring(zLen, yLen);
        return result;
    }
}
