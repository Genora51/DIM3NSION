package dim3nsion;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;

import java.io.File;
import java.io.IOException;

public class SharedFuncs {
    public static String absPath(String src){
        String ap;
        try {
            ap = "file:///" +  new File(".").getCanonicalPath().replaceAll("\\\\","/").replaceAll("\\s", "%20") + "/" + src;
        } catch (IOException e) {
            e.printStackTrace();
            ap = "";
        }
        return ap;
    }
}
