import Control.ProjectUnknownProperties;
import com.Physics2D.PhysicsWorld;

import javax.swing.*;

import java.io.IOException;

public class MainProgram {

    public static void main (String[] args){
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
