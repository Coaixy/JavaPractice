package me.yixiao.miner;

import java.util.Random;
import java.util.Scanner;

/**
 * @author Yxiaoc
 */
public class Main {
    private static final int SIZE = 36;
    private static int[] pan = new int[36];
    private static int[] blank = new int[36];
    public static void main(String[] args) throws Exception {
        init();
        sendpan();
        System.out.println();
        Scanner sc= new Scanner(System.in);
        String zb = null;
        zb = sc.nextLine();
        while (!"exit".equals(zb)){
            String[] str = new String[2];
            str = zb.split("#");
            int num = (Integer.parseInt(str[0])-1)*6 + Integer.parseInt(str[1])-1;
//            blank[num] = 1;
            event(num);
            sendpan();
            sc=new Scanner(System.in);
            zb = sc.nextLine();

        }

    }
    private static  void event(int num){
        if (pan[num]== -1){
            for (int i = 0; i < SIZE ; i++){
                if (i%6 == 0){
                    System.out.println();
                }
                System.out.print(pan[i]+" ");
            }
            System.out.println();
            System.exit(0);
        }else{
            blank[num] = 1;
            dfs(num);
        }
    }

    private static void dfs(int num){
        try {
            if (pan[num - 7] != -1) {
                blank[num - 7] = 1;
//                dfs(num - 7);
            }
            if (pan[num - 6] != -1) {
                blank[num - 6] = 1;
//                dfs(num - 6);
            }
            if (pan[num - 5] != -1) {
                blank[num - 5] = 1;
                dfs(num - 5);
            }
            if (pan[num - 1] != -1) {
                blank[num - 1] = 1;
//                dfs(num - 1);
            }
            if (pan[num + 1] != -1) {
                blank[num + 1] = 1;
//                dfs(num + 1);
            }
            if (pan[num + 5] != -1) {
                blank[num + 5] = 1;
//                dfs(num + 5);
            }
            if (pan[num + 6] != -1) {
                blank[num + 6] = 1;
//                dfs(num + 6);
            }
            if (pan[num + 7] != -1) {
                blank[num + 7] = 1;
//                dfs(num + 7);
            }
        }catch (Exception e){

        }
        }

    private static void sendpan(){
        for (int i = 0; i < SIZE ; i++){
            if (i%6 == 0){
                System.out.println();
            }
            System.out.print(blank[i]+" ");
        }
        System.out.println();
    }
    private static void init(){
        Random random = new Random();
        for (int i = 0 ; i < SIZE ; i++){
            int rand = random.nextInt(10);
            if (rand <= 4){
                pan[i] = -1;
            }else{
                pan[i]  = 0;
            }
        }
        for (int i = 0 ; i < SIZE ; i ++){
            blank[i] = -2;
        }
    }
}
