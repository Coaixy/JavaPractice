import java.io.*;

/**
 * @author Yxiaoc
 */
public class Words {
    public String filepath;
    public String word;
    public Words(String filepath){
        this.filepath = filepath;
    }
    public void init() throws IOException {
        boolean c = false;
        File f = new File(this.filepath);
        if (!f.exists()){c = f.createNewFile(); }
        System.out.println(c);
        BufferedReader reader = new BufferedReader(new FileReader(f));
        StringBuffer sbf = new StringBuffer();
        try {
            String temp;
            while ((temp = reader.readLine()) != null){
                sbf.append(temp);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.word = sbf.toString();
    }
    public String[] getWords(){
        return this.word.split("\n");
    }
}
