package dim3nsion;

/**
 * Created by Genora51 on 24/06/2017.
 */
public class GameStateController {
    public final float LOAD_LENGTH = 32;

    public int gameState = 0;
    public int loadCountup = 0;
    public GameStateController(){

    }
    public void setMode(int mode){
        gameState = mode;
        switch(mode){
            case 0:
               loadCountup = 0;
               break;
        }
    }
    public void run(){
        switch (gameState) {
            case 0:
                if(loadCountup < LOAD_LENGTH){
                    loadCountup++;
                }else{
                    setMode(1);
                }
                break;
        }
    }
}
