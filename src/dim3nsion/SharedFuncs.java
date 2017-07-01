package dim3nsion;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class SharedFuncs {
    static int gWid, gHei, bWid, bHei, pWid, pHei;
    public static void setVals(GraphicsContext c){
        Canvas ca = c.getCanvas();
        int w =(int) ca.getWidth();
        pWid = pHei = Math.floorDiv(w, 16*32);
        bWid = bHei = pWid * 16;
        gWid = bWid * 32;
        gHei = bHei * 16;
    }
}
