package Control;

import Model.Physics.Entity.Player;
import Model.Physics.World.AbstractWorld;
import Model.SoundManager;
import Model.UI.NotificationArea;
import View.MainFrame;
import java.awt.*;
import java.io.File;
import java.io.IOException;


public class ProjectUnknownProperties {

    private NotificationArea notificationArea;
    private SoundManager soundManager;
    private MainFrame frame;
    private Font gameFont;
    private Player player;
    private AbstractWorld currentWorld;

    public ProjectUnknownProperties() throws IOException {
        soundManager = new SoundManager();
        notificationArea = new NotificationArea();
        player = new Player(this);

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

    public NotificationArea getNotificationArea() {
        return notificationArea;
    }

    public Font getGameFont(){
        //return new Font("Arial", 1, 16);
        return gameFont;
    }

    public static Dimension getScreenDimension(){
        return Toolkit.getDefaultToolkit().getScreenSize();
    }

    public void setCurrentWorld(AbstractWorld world){
        this.currentWorld = world;
    }

    public AbstractWorld getCurrentWorld(){
        return currentWorld;
    }

    public SoundManager getSoundManager(){
        return this.soundManager;
    }

    public Player getPlayer() {
        return player;
    }
}
