package dim3nsion;

import javafx.scene.image.Image;

import static dim3nsion.SharedFuncs.bHei;
import static dim3nsion.SharedFuncs.bWid;

public class Textures {
    Image[] get;
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
            get[a] = new Image(getClass().getResourceAsStream("/textures/" + names[a]), bWid, bHei, true, false);
        }
    }
}
