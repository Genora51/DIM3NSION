package dim3nsion;

import javafx.scene.image.Image;

import static dim3nsion.SharedFuncs.*;

public class Textures {
    Image[] get;
    Image[] glitchin;
    String[] names = {
            "grass.bmp",
            "soil.bmp",
            "sandtop.bmp",
            "sand.bmp",
            "brick.bmp",
            "cobble.bmp",
            "vinecobble.bmp",
            "iron.bmp",
            "waterbody.bmp",
            "wood.bmp",
            "endstar.gif"
    };
    public Textures(){
        get = new Image[names.length + 1];
        for(int a =0; a<names.length; a++){
            get[a] = new Image(getClass().getResourceAsStream("/images/textures/" + names[a]), bWid, bHei, true, false);
        }
        glitchin = new Image[15];
        for(int i=0;i<glitchin.length;i++){
            String name = String.format("glitch_%05d.png", i);
            glitchin[i] = new Image(getClass().getResourceAsStream("/images/glitches/glitchin/" + name), gWid, gHei, false, false);
        }
    }
}
