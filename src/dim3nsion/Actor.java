package dim3nsion;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


class Actor {
    private int x;
    private int y;
    private float yAcc;
    boolean[] collisions = {false,false,false,false};
    private GraphicsContext canvas;
    private ActorListener al;
    private GameStateController gsc;
    Actor(int x, int y, GraphicsContext canvas, ActorListener al, GameStateController gsc){
        this.x = x;
        this.y = y;
        this.yAcc = 0;
        this.canvas = canvas;
        this.gsc = gsc;
        this.al = al;
    }
    void move(int[][] curBlocks){
        if (gsc.gameState == 1) {
            if (collisions[1] && al.RIGHT) x += 6;
            if (collisions[3] && al.LEFT) x -= 6;

            yAcc -= 1;
            if (al.UP && !collisions[2]) yAcc = 12;
            if(!collisions[0]) yAcc = 0;
            y -= yAcc;
            collisions = detectCollisions(curBlocks);
            if (!collisions[2]){
                yAcc = 0;
                y = Math.floorDiv(y, 48) * 48;
            }
            if (!collisions[0]){
                yAcc = 0;
                y = (Math.floorDiv(y, 48) + 1) * 48;
            }
            if (!collisions[1]) x = Math.floorDiv(x, 48) * 48;
            if (!collisions[3]) x = (Math.floorDiv(x, 48) + 1) * 48;
            canvas.setFill(Color.BLUE);
            canvas.fillRect(x, y, 48, 48);
        }
    }

    private boolean[] detectCollisions(int[][] curBlocks) {
        int yPos = Math.floorDiv(y, 48);
        int xPos = Math.floorDiv(x, 48);
        int xiPos = Math.floorDiv(x + 24, 48);
        int yiPos = Math.floorDiv(y + 24, 48);
        boolean[] cols = new boolean[4];
        int[] xs = {xiPos, xPos + 1, xiPos, xPos};
        int[] ys = {yPos, yiPos, yPos + 1, yiPos};
        for (int a=0; a<4; a++){
            try{
                cols[a] = curBlocks[ys[a]][xs[a]] == 0;
            }catch(ArrayIndexOutOfBoundsException e){
                cols[a] = false;
            }

        }
        return cols;
    }
}
