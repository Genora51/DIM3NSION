package dim3nsion;

import javafx.stage.Screen;

public class SharedFuncs {
    static int gWid, gHei, bWid, bHei, pWid, pHei;
    public static void setVals(){
        int w =(int) Screen.getPrimary().getVisualBounds().getWidth();
        //int h =(int) ca.getHeight();
        pWid = pHei = Math.floorDiv(w, 16*32);
        bWid = bHei = pWid * 16;
        gWid = bWid * 32;
        gHei = bHei * 16;
        /*String str = "Canvas: " + w + ", " + h + "\n";
        str += "World: " + gWid + ", " + gHei + "\n";
        str += "Block: " + bWid + ", " + bHei + "\n";
        str += "Pixel: " + pWid + ", " + pHei + "\n";
        System.out.println(str);*/
    }
}
