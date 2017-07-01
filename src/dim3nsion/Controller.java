package dim3nsion;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.util.Duration;


public class Controller {
    public Game game;

    @FXML
    private MediaView mv;
    @FXML
    private GridPane mediaViewPane;


    void startAndBind(){
        MediaPlayer mp = mv.getMediaPlayer();
        mp.setOnEndOfMedia(this::startGame);
        EventHandler<KeyEvent> okp = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.SPACE){
                    mp.play();
                    mediaViewPane.removeEventHandler(KeyEvent.KEY_PRESSED, this);
                }
            }
        };
        Timeline tm = new Timeline(new KeyFrame(Duration.millis(8000), ae -> {
            mp.pause();
            mediaViewPane.addEventHandler(KeyEvent.KEY_PRESSED, okp);
        }));
        tm.play();
        mp.play();
    }

    private void startGame(){
        System.out.println("Finished");
        mediaViewPane.getChildren().remove(mv);
        Canvas canvas = new Canvas(mediaViewPane.getWidth(), mediaViewPane.getHeight());
        mediaViewPane.getChildren().add(canvas);
        GridPane.setHalignment(canvas, HPos.CENTER);
        GridPane.setValignment(canvas, VPos.CENTER);
        this.game = new Game(canvas, mediaViewPane);
        ActorListener al = new ActorListener(mediaViewPane);
        game.start(al);
    }

}
