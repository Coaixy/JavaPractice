package me.yxiaoc.jzq;

import java.util.Random;

/**
 * @author Yxiaoc
 */
public class Main {
    private static int[] pan = new int[]{0,0,0,0,0,0,0,0,0};

    public static void main(String[] args) throws InterruptedException {
        int i = 2;
        while(true){
            while(!isWin()){
                go(i);
                i++;
            }
            pan = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
            System.out.println("决出胜负了！");
            Thread.sleep(3000);
        }
//        System.exit(0);
    }

    private static void go(int i){
        Random r = new Random();
        if (i%2 == 0){
            int a = r.nextInt(8);
            while(pan[a] != 0){
                a = r.nextInt(8);
            }
            pan[a] = 1;
        }else{
            int a = r.nextInt(8);
            while(pan[a] != 0){
                a = r.nextInt(8);
            }
            pan[a] = 2;
        }
        for (int a  = 0 ; a < pan.length;a++){
            System.out.print(pan[a]);
            if (a == 2 || a == 5 || a== 8){
                System.out.println();
            }if (a == 8 ){
                System.out.println();
            }
        }
    }
    // 0 1 2
    // 3 4 5
    // 6 7 8


    private static boolean isWin(){ //是否获得胜利
        if (pan[0] == pan[1] && pan [1] == pan[2] && pan[0] != 0){
            return true;
        }if(pan[0] == pan[3] && pan [3] == pan[6] && pan[0] != 0){
            return true;
        }if (pan[0] == pan[4] && pan [4] == pan[8] && pan[0] != 0) {
            return true;
        }
        if (pan[3] == pan[4] && pan [4] == pan[5] && pan[3] != 0) {
            return true;
        }
        if (pan[6] == pan[7] && pan [7] == pan[8] && pan[6] != 0) {
            return true;
        }
        if (pan[1] == pan[4] && pan [4] == pan[7] && pan[1] != 0) {
            return true;
        }
        if (pan[2] == pan[5] && pan [5] == pan[8] && pan[2] != 0) {
            return true;
        }
        if (pan[2] == pan[4] && pan [4] == pan[6] && pan[2] != 0) {
            return true;
        }
        return false;
    }
}
