import Control.ProjectUnknownProperties;
import Model.Managing.SpriteManager;

import javax.swing.*;

public class MainProgram {

    public static void main(String[] args) {
        int dummy = SpriteManager.BLOCK; //some dummy access to load the class and init all the images
        SwingUtilities.invokeLater(() -> MainProgram.setup());
    }

    private static void setup() {
        new ProjectUnknownProperties();
    }

}
