package dim3nsion;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Created by Genora51 on 24/06/2017.
 */
public class Actor {
    public int x;
    public int y;
    public GraphicsContext canvas;
    public ActorListener al;
    public GameStateController gsc;
    public Actor(int x, int y, GraphicsContext canvas, ActorListener al, GameStateController gsc){
        this.x = x;
        this.y = y;
        this.canvas = canvas;
        this.gsc = gsc;
        this.al = al;
    }
    public void move(int[][] curBlocks){
        boolean[] collisions = detectCollisions(curBlocks);
        if (gsc.gameState == 1) {
            if (collisions[3] && al.RIGHT) x += 5;
            if (collisions[1] && al.LEFT) x -= 5;
            if (collisions[2] && al.DOWN) y += 5;
            if (collisions[0] && al.UP) y -= 5;
            canvas.setFill(Color.BLUE);
            canvas.fillRect(x, y, 48, 48);
        }
    }

    boolean[] detectCollisions(int[][] curBlocks){
        boolean[] colls = {false, false, false, false};
        int yPos = Math.round(y/48f);
        int xPos = Math.round(x/48f);
        colls[0] = (curBlocks[xPos][yPos - 1]) == 0;
        colls[1] = (curBlocks[xPos - 1][yPos]) == 0;
        colls[2] = (curBlocks[xPos][yPos + 1]) == 0;
        colls[3] = (curBlocks[xPos + 1][yPos]) == 0;
        return colls;
    }
}
