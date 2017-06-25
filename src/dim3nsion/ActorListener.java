package dim3nsion;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;

/**
 * Created by Genora51 on 24/06/2017.
 */
public class ActorListener {
    public volatile boolean W,A,S,D,LEFT,RIGHT,UP,DOWN;
    public ActorListener(GridPane gp){
        gp.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()){
                    case W: W = true; break;
                    case A: A = true; break;
                    case S: S = true; break;
                    case D: D = true; break;
                    case UP: UP = true; break;
                    case DOWN: DOWN = true; break;
                    case LEFT: LEFT = true; break;
                    case RIGHT: RIGHT = true; break;

                }
            }
        });
        gp.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()){
                    case W: W = false; break;
                    case A: A = false; break;
                    case S: S = false; break;
                    case D: D = false; break;
                    case UP: UP = false; break;
                    case DOWN: DOWN = false; break;
                    case LEFT: LEFT = false; break;
                    case RIGHT: RIGHT = false; break;

                }
            }
        });
    }
}
