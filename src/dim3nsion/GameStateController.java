package dim3nsion;

class GameStateController {
    final float LOAD_LENGTH = 14;

    public int gameState = 0;
    public int loadCountup = -1;
    public GameStateController(){

    }
    public void setMode(int mode){
        gameState = mode;
        switch(mode){
            case 0:
               loadCountup = -1;
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
