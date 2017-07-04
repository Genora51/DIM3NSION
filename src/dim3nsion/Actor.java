package dim3nsion;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static dim3nsion.SharedFuncs.*;


class Actor {
    private int x;
    private int y;
    private float yAcc;
    boolean[] collisions = {false,false,false,false};
    private GraphicsContext canvas;
    private ActorListener al;
    private GameStateController gsc;
    private static final Integer[] dv = {-1,5,6};
    private static final Set<Integer> downVals = new HashSet<Integer>(Arrays.asList(dv));


    Actor(int x, int y, GraphicsContext canvas, ActorListener al, GameStateController gsc){
        this.x = x;
        this.y = y;
        this.yAcc = 0;
        this.canvas = canvas;
        this.gsc = gsc;
        this.al = al;
    }
    void move(FieldState fs){
        int[][] curBlocks = fs.getState();
        if (gsc.gameState == 1) {
            if (collisions[1] && al.RIGHT) x += pWid * 2;
            if (collisions[3] && al.LEFT) x -= pWid * 2;

            yAcc -= pHei / 3;
            if (al.UP && !collisions[2]) yAcc = pHei * 4;
            if (al.DOWN && yAcc > 0) yAcc = 0;
            if(!collisions[0]) yAcc = 0;
            y -= yAcc;
            collisions = detectCollisions(curBlocks);
            if (!collisions[2]){
                yAcc = 0;
                y = Math.floorDiv(y, bHei) * bHei;
            }
            if (!collisions[0]){
                yAcc = 0;
                y = (Math.floorDiv(y, bHei) + 1) * bHei;
            }
            if (!collisions[1]) x = Math.floorDiv(x, bWid) * bWid;
            if (!collisions[3]) x = (Math.floorDiv(x, bWid) + 1) * bWid;
            canvas.setFill(Color.BLUE);
            canvas.fillRect(x + fs.xDiff, y + fs.yDiff, bWid, bHei);
        }
    }

    private boolean[] detectCollisions(int[][] curBlocks) {
        int yPos = Math.floorDiv(y, bHei);
        int xPos = Math.floorDiv(x, bHei);
        int xiPos = Math.floorDiv(x + (bWid/2), bWid);
        int yiPos = Math.floorDiv(y + (bHei/2), bHei);
        boolean[] cols = new boolean[4];
        int[] xs = {xiPos, xPos + 1, xiPos, xPos};
        int[] ys = {yPos, yiPos, yPos + 1, yiPos};
        for (int a=0; a<4; a++){
            try{
                cols[a] = curBlocks[ys[a]][xs[a]] < 4;
            }catch(ArrayIndexOutOfBoundsException e){
                cols[a] = false;
            }

        }
        return cols;
    }
}
