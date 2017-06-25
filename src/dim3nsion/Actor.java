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
    public void move(){
        if (gsc.gameState == 1) {
            if (al.RIGHT) x += 5;
            if (al.LEFT) x -= 5;
            canvas.setFill(Color.BLUE);
            canvas.fillRect(x, y, 48, 48);
        }
    }
}
