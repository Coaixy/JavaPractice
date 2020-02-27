/**
 * @author Yxiaoc
 */
public class Keys {
    public int num;
    public void getNum(){
        int max=14,min=7;
        long randomNum = System.currentTimeMillis();
        this.num = (int) (randomNum%(max-min)+min);

    }
    public int[] getKeys(){
        int[] keys  = new int[this.num];
        for (int i = 0;i<this.num;++i){
            int max=90,min=65;
            long randomNum = System.currentTimeMillis();
            int ran = (int) (randomNum%(max-min)+min);
            keys[i] = ran;
        }
        return keys;
    }
}
