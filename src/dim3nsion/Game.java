package dim3nsion;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.GridPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;

import java.io.*;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;

class Game {
    public Timer timer;
    private GraphicsContext canvas;
    private final int DESIRED_FRAME_RATE = 32;
    private GridPane gp;
    private Actor actor;
    private ActorListener al;
    private FieldState fs;
    private double h,w;
    private GameStateController gsc;

    private void startMusic(){
        String abspath = "./res/audio";
        try {
            abspath ="file:///" +  new File(".").getCanonicalPath().replaceAll("\\\\","/").replaceAll("\\s", "%20") + "/src/res/audio/";
        } catch (IOException e) {
            e.printStackTrace();
        }
        Media media = new Media(abspath + "1.mp3");
        MediaPlayer mp;
        mp = new MediaPlayer(media);
        MediaView mv = new MediaView(mp);
        gp.getChildren().add(mv);
        mp.play();
    }

    Game(Canvas can, GridPane gp){
        this.gp = gp;
        this.h = can.getHeight();
        this.w = can.getWidth();
        this.canvas = can.getGraphicsContext2D();
        System.out.println(new java.io.File(".").getAbsolutePath());

    }

    public class GameLoop extends TimerTask {
        @Override
        public void run(){
            draw();
            gsc.run();
            detectGameEvents();
            moveCharacter();
        }
    }

    void start(ActorListener al){
        startMusic();
        this.al = al;
        gsc = new GameStateController();
        this.fs = new FieldState();
        fs.load("1");
        //canvas.setFill(Color.BLUE);
        //canvas.fillRect(50,50,300,300);
        actor = new Actor(100, 100, canvas, al, gsc);
        this.timer = new Timer();
        timer.schedule(new GameLoop(), 0, 1000 / DESIRED_FRAME_RATE);
    }

    private void draw(){
        canvas.setFill(Color.BLACK);
        canvas.fillRect(0,0,w,h);
        int posy=0;
        canvas.setFill(Color.RED);
        for (int[] str : fs.level){
            int posx = 0;
            for (int let : str){
                if (let == 1) canvas.fillRect(posx,posy,48,48);
                posx += 48;
            }
            posy += 48;
        }
    }

    private void detectGameEvents(){
        if (gsc.gameState == 0){
            Color newBlue = new Color(0,0,1,(gsc.loadCountup/gsc.LOAD_LENGTH));
            canvas.setFill(newBlue);
            canvas.fillRect(100,100,50,50);
        }
    }

    private void moveCharacter(){
        actor.move();
    }
}
