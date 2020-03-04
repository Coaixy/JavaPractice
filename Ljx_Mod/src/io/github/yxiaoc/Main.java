package io.github.yxiaoc;

import javax.swing.*;
import java.util.Scanner;

/**
 * @author Yxiaoc
 */
public class Main {
    public static void main(String[] args) {
        JOptionPane.showMessageDialog(null, "注意", "本程序免费发布 作者QQ：428267675", JOptionPane.QUESTION_MESSAGE);
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入联机侠安装目录 例：D:\\Program Files\\我的世界联机侠");
        String location = sc.nextLine();
        Inject inject = new Inject(location);
        inject.init();
        inject.lock();
//        System.out.println("注入成功！");
    }
}
