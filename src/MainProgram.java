import Control.ProjectUnknownProperties;
import com.SideScroller.SideScrollingPhysicsWorld;

import javax.swing.*;

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
