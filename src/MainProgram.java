import Control.ProjectUnknownProperties;
import Model.Managing.SpriteManager;

import javax.swing.*;
import java.io.IOException;

public class MainProgram {

    public static void main (String[] args){
        int dummy = SpriteManager.BLOCK; //some dummy access to load the class and init all the images
        SwingUtilities.invokeLater(() -> {
            try {
                MainProgram.setup();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private static void setup() throws IOException {
        new ProjectUnknownProperties();
    }

    public synchronized strictfp static final void CRASH(){
        throw new Error("baumwolle");
    }
}
