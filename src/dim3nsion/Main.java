package dim3nsion;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.GridPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;

public class Main extends Application {
    private FXMLLoader loader;

    @Override
    public void start(Stage primaryStage) throws Exception{
        loader = new FXMLLoader(getClass().getResource("main.fxml"));
        Parent root = loader.load();

        // Set timer for mediaView
        Timeline timeline = new Timeline(new KeyFrame(
                Duration.millis(1000),
                ae->getController().startAndBind()));
        timeline.play();
        // Start game
        primaryStage.setTitle("DIM3NSION");
        Scene rts = new Scene(root, 1920, 1080);
        primaryStage.setScene(rts);
        primaryStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        primaryStage.setFullScreen(true);
        primaryStage.show();
        rts.setCursor(Cursor.NONE);
        rts.getRoot().requestFocus();
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                getController().game.timer.cancel();
            }
        });
    }

    private Controller getController(){
        return loader.getController();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
