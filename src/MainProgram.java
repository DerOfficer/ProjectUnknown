import Control.ProjectUnknownProperties;
import com.Physics2D.PhysicsWorld;

import javax.swing.*;

import static com.Physics2D.PhysicsWorld.TIMER_CONSTANT;

public class MainProgram {

    public static void main (String[] args){
        SwingUtilities.invokeLater(() -> MainProgram.setup());
    }

    private static void setup(){
        new ProjectUnknownProperties();
        TIMER_CONSTANT = 10;
    }

    public synchronized strictfp static final void CRASH(){
        throw new Error("baumwolle");
    }
}
