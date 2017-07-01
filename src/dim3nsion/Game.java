package dim3nsion;

import com.sun.prism.Texture;
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

import static dim3nsion.SharedFuncs.*;

class Game {
    public Timer timer;
    private GraphicsContext canvas;
    private final int DESIRED_FRAME_RATE = 32;
    private GridPane gp;
    private Actor actor;
    private ActorListener al;
    private FieldState fs;
    private Textures textures;
    private double h,w;
    private GameStateController gsc;

    private void startMusic(){
        //String abspath = absPath("src/res/audio");
        String respat = getClass().getResource("/audio/1.mp3").toString();
        System.out.println(respat);
        Media media = new Media(respat);
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
        SharedFuncs.setVals(canvas);
        this.textures = new Textures();
        //System.out.println(new java.io.File(".").getAbsolutePath());

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
        fs.load("1", canvas);
        //canvas.setFill(Color.BLUE);
        //canvas.fillRect(50,50,300,300);
        actor = new Actor(100, 100, canvas, al, gsc);
        this.timer = new Timer();
        timer.schedule(new GameLoop(), 0, 1000 / DESIRED_FRAME_RATE);
    }

    private void draw(){
        canvas.setFill(Color.SKYBLUE);
        canvas.fillRect(fs.xDiff,fs.yDiff,gWid,gHei);
        int posy=0;
        for (int[] str : fs.level){
            int posx = 0;
            for (int let : str){
                if (let != -1){
                    //canvas.fillRect(posx + fs.xDiff,posy + fs.yDiff,48,48);
                    canvas.drawImage(textures.get[let], posx + fs.xDiff,posy + fs.yDiff,bWid, bHei);
                }
                posx += 48;
            }
            posy += 48;
        }
    }

    private void detectGameEvents(){
    }

    private void moveCharacter(){
        actor.move(fs);
    }
}
