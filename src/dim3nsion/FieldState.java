package dim3nsion;

import javafx.scene.canvas.GraphicsContext;

import java.io.*;

import static dim3nsion.SharedFuncs.bHei;
import static dim3nsion.SharedFuncs.bWid;


public class FieldState {
    public int[][] level;
    public int xDiff;
    public int yDiff;
    public int[][] getState(){
        return level;
    }

    public void load(String lname, GraphicsContext canvas){
        int[][] res;
        try {
            InputStream is = getClass().getResourceAsStream("/levels/" + lname + ".d3l");// new FileInputStream("src/res/levels/" + lname + ".d3l");
            BufferedReader buf = new BufferedReader(new InputStreamReader(is));
            String line = buf.readLine();
            StringBuilder sb = new StringBuilder();
            while (line != null) {
                sb.append(line).append("\n");
                line = buf.readLine();
            }
            String[] level = sb.toString().split("\n");
            res = new int[level.length][];
            for (int i=0; i<level.length; i++) {
                int[] resi = new int[level[i].length()];
                for (int j = 0; j < level[i].length(); j++) {
                    resi[j] = Integer.parseInt(
                            String.valueOf(level[i].charAt(j)), 16
                    ) - 1;
                }
                res[i] = resi;
            }
        }catch(IOException e){
            e.printStackTrace();
            res = new int[][] {{0}};
        }
        this.level =  res;
        this.xDiff = (int) (canvas.getCanvas().getWidth() - (res[0].length*bWid))/2;
        this.yDiff = (int) (canvas.getCanvas().getHeight()- (res.length*bHei))/2;
    }
}
