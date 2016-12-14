import Control.ProjectUnknownProperties;

import javax.swing.*;

/**
 * Created by Jean-Pierre on 15.11.2016.
 */
public class MainProgram {

    public static void main (String[] args){
        SwingUtilities.invokeLater(() -> MainProgram.setup());
    }

    private static void setup(){
        new ProjectUnknownProperties();
    }

    public synchronized strictfp static final void CRASH(){
        throw new Error("baumwolle");
    }
}
