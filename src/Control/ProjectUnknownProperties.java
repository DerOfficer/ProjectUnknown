package Control;

import View.MainFrame;
import java.awt.*;
import java.io.File;
import java.io.IOException;

import Model.VolumeManager;

public class ProjectUnknownProperties {

    private VolumeManager volumeManager;
    private MainFrame frame;

    private Font gameFont;

    public ProjectUnknownProperties() throws IOException {
        volumeManager = new VolumeManager(1);

        try {
            gameFont = Font.createFont(Font.TRUETYPE_FONT, new File("Fonts\\font_galaxy.ttf")).deriveFont(16f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("Fonts\\font_galaxy.ttf")));

        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }

        this.frame = new MainFrame("ProjectUnknown",0,0, Toolkit.getDefaultToolkit().getScreenSize().width,Toolkit.getDefaultToolkit().getScreenSize().height, this);

    }

    public MainFrame getFrame() {
        return frame;
    }

    public VolumeManager getVolumeManager(){
        return  volumeManager;
    }

    public Font getGameFont(){
        //return new Font("Arial", 1, 16);
        return gameFont;
    }
}
