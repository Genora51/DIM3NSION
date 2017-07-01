package dim3nsion;

import javafx.scene.image.Image;

import static dim3nsion.SharedFuncs.bHei;
import static dim3nsion.SharedFuncs.bWid;

public class Textures {
    Image[] get;
    String[] names = {
            "grass",
            "soil",
            "sandtop",
            "sand",
            "brick",
            "cobble",
            "vinecobble",
            "iron",
            "waterbody",
            "wood"
    };
    public Textures(){
        get = new Image[names.length + 1];
        for(int a =0; a<names.length; a++){
            get[a] = new Image(getClass().getResourceAsStream("/textures/" + names[a] + ".bmp"), bWid, bHei, true, false);
        }
        get[names.length] = new Image(getClass().getResourceAsStream("/textures/endstar.gif"), bWid, bHei, true, false);
    }
}
