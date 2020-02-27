import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.IOException;

/**
 * @author Yxiaoc
 */
public class Main {
    public static void main(String[] args) throws IOException, AWTException, InterruptedException {
        //获取词汇
        Words wds = new Words(args[0]);
        wds.init();
        String[] words = wds.getWords();
        System.out.println("共导入"+words.length+"个句子");
        Keys key = new Keys();
        System.out.println("请等待5s");
        Thread.sleep(5000);
        String outkey = null;
        //打出随机字符进行误导
        for (String word : words) {
            key.getNum();
            int[] keys = key.getKeys();
            Robot r = new Robot();
            for (int i : keys) {
                r.keyPress(i);
                r.keyRelease(i);
                Thread.sleep(200);
                outkey += i;
            }
            System.out.println(outkey);
            //删除原文本
            for (int j : keys) {
                r.keyPress(KeyEvent.VK_BACK_SPACE);
                r.keyRelease(KeyEvent.VK_BACK_SPACE);
            }
            //复制粘贴
            Thread.sleep(500);
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(word), null);
            //ctrl + c ctrl + v
            r.keyPress(KeyEvent.VK_CONTROL);
            r.keyPress(KeyEvent.VK_C);
            r.keyRelease(KeyEvent.VK_CONTROL);
            r.keyRelease(KeyEvent.VK_C);
            r.keyPress(KeyEvent.VK_CONTROL);
            r.keyPress(KeyEvent.VK_V);
            r.keyRelease(KeyEvent.VK_CONTROL);
            r.keyRelease(KeyEvent.VK_V);
            r.keyPress(KeyEvent.VK_ENTER);
            r.keyRelease(KeyEvent.VK_ENTER);
            System.out.println(word);
        }
    }

}
