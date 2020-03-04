package io.github.yxiaoc;

import com.sun.istack.internal.NotNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Yxiaoc
 */
public class Inject {
    private String location;
    Inject(@NotNull String l){
        this.location = l;
    }
    void init(){ //获取目录，并删除缓存文件
        this.location = this.location+"\\cache\\modslib";
        File f = new File(this.location);
        File[] tempList = f.listFiles();
        assert tempList != null;
        for (File file : tempList) {
            if (file.isFile()) {
                boolean del = file.delete();
            }
        }
    }
    public void lock(){ //监听文件生成并写入文件
        String location  = this.location;
        Thread t = new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        while(true){
                            try {
                                Thread.sleep(1000);
                                File f = new File(location);
                                File[] tempList = f.listFiles();
                                assert tempList != null;
                                for (File file : tempList) {
                                    if (file.toString()=="ca42e9faaf222f5ac85f9616f87024e5.bin") {
                                        boolean yes = file.delete();
                                        File mod = new File("mod.jar");
                                        if (!mod.exists()){
                                            System.out.println("mod.jar不存在，请放在运行目录");
                                        }
                                        System.out.println("注入成功！");
                                        copyFile(mod, new File(location + "\\ca42e9faaf222f5ac85f9616f87024e5.bin"));
                                        break;
                                    }
                                }
                            } catch (InterruptedException | IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
        );
    }
    private static void copyFile(File source, File dest) throws IOException, IOException {
        FileChannel inputChannel = null;
        FileChannel outputChannel = null;
        try {
            inputChannel = new FileInputStream(source).getChannel();
            outputChannel = new FileOutputStream(dest).getChannel();
            outputChannel.transferFrom(inputChannel, 0, inputChannel.size());
        } finally {
            assert inputChannel != null;
            inputChannel.close();
            assert outputChannel != null;
            outputChannel.close();
        }
    }
}
