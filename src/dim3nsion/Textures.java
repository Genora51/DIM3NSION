package dim3nsion;

import javafx.scene.image.Image;

/**
 * Created by Genora51 on 27/06/2017.
 */
public class Textures {
    Image[] get;
    String[] names = {
            "grass",
            "soil",
            "brick",
            "cobble",
            "vinecobble",
            "waterbody",
            "wood",
            "iron",
            "watertop"
    };
    public Textures(){
        get = new Image[names.length];
        for(int a =0; a<names.length; a++){
            get[a] = new Image(SharedFuncs.absPath("src/res/textures/") + names[a] + ".bmp");
        }
    }
}
