package dim3nsion;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;


public class Main extends Application {
    private FXMLLoader loader;

    @Override
    public void start(Stage primaryStage) throws Exception{
        //System.out.println(getHostServices().getDocumentBase());

        loader = new FXMLLoader(getClass().getResource("/main.fxml"));
        Parent root = loader.load();
        getController().startAndBind();
        // Set timer for mediaView
        // Start game
        primaryStage.setTitle("DIM3NSION");
        Scene rts = new Scene(root, 1920, 1080);
        primaryStage.setScene(rts);
        primaryStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        primaryStage.setFullScreen(true);
        primaryStage.show();

        rts.setCursor(Cursor.NONE);
        rts.getRoot().requestFocus();
        primaryStage.setOnCloseRequest(event -> {
            try {
                getController().game.timer.cancel();
            }catch (NullPointerException ignored){
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
