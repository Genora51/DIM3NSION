package dim3nsion;

import javafx.scene.image.Image;

import static dim3nsion.SharedFuncs.*;

public class Textures {
    Image[] get;
    Image[] glitchin;
    String[] names = {
            "deco1.png",
            "deco2.png",
            "deco3.png",
            "deco4.png",
            "block1.bmp",
            "block2.bmp",
            "block3.bmp",
            "block4.bmp",
            "climb.png",
            "ladder.png",
            "spike1.png",
            "spike2.png",
            "waterbody.bmp",
            "watertop.png",
            "endstar.gif"
    };
    public Textures(){
        glitchin = new Image[15];
        for(int i=0;i<glitchin.length;i++){
            String name = String.format("glitch_%05d.png", i);
            glitchin[i] = new Image(getClass().getResourceAsStream("/images/glitches/glitchin/" + name), gWid, gHei, false, false);
        }
    }
    public void load(int wNum){
        get = new Image[names.length + 1];
        for(int a =0; a<names.length; a++){
            get[a] = new Image(getClass().getResourceAsStream("/images/textures/" + wNum + "/" + names[a]), bWid, bHei, true, false);
        }
    }
}
