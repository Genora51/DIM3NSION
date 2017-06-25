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
    public String[] level;
    public Timer timer;
    private GraphicsContext canvas;
    private final int DESIRED_FRAME_RATE = 32;
    private GridPane gp;
    private Actor actor;
    private ActorListener al;
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
        try {
            InputStream is = new FileInputStream("src/res/levels/1.txt");
            BufferedReader buf = new BufferedReader(new InputStreamReader(is));
            String line = buf.readLine();
            StringBuilder sb = new StringBuilder();
            while (line != null) {
                sb.append(line).append("\n");
                line = buf.readLine();
            }
            level = sb.toString().split("\n");
        }catch(IOException e){
            level = new String[]{"0"};
            e.printStackTrace();
        }
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
        //canvas.setFill(Color.BLUE);
        //canvas.fillRect(50,50,300,300);
        actor = new Actor(100, 100);
        this.timer = new Timer();
        timer.schedule(new GameLoop(), 0, 1000 / DESIRED_FRAME_RATE);
    }

    private void draw(){
        canvas.setFill(Color.BLACK);
        canvas.fillRect(0,0,w,h);
        int posy=0;
        canvas.setFill(Color.RED);
        for (String str : level){
            int posx = 0;
            for (char let : str.toCharArray()){
                posx += 32;
                if (let == '1') canvas.fillRect(posx,posy,32,32);
            }
            posy += 32;
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
        if (gsc.gameState == 1) {
            if (al.UP) actor.y -= 5;
            if (al.DOWN) actor.y += 5;
            if (al.RIGHT) actor.x += 5;
            if (al.LEFT) actor.x -= 5;
            canvas.setFill(Color.BLUE);
            canvas.fillRect(actor.x, actor.y, 50, 50);
        }
    }
}
