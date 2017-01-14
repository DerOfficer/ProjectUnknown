package Control;

import Model.Managing.SoundManager;
import Model.UI.NotificationArea;
import View.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;


public class ProjectUnknownProperties {

    private NotificationArea notificationArea;

    private SoundManager soundManager;

    private MainFrame frame;
    private Font gameFont;

    private int level;

    public ProjectUnknownProperties() {
        this.level = 1;

        this.soundManager = new SoundManager();
        this.notificationArea = new NotificationArea(this);

        tryLoadGameFont();

        this.frame = new MainFrame("ProjectUnknown",0,0, Toolkit.getDefaultToolkit().getScreenSize().width,Toolkit.getDefaultToolkit().getScreenSize().height, this);
    }

    private void tryLoadGameFont(){
        try {
            this.gameFont = Font.createFont(Font.TRUETYPE_FONT, new File("Fonts\\font_galaxy.ttf")).deriveFont(16f);
        } catch (IOException | FontFormatException e) {
            ProjectUnknownProperties.raiseException(e);
        }
    }

    public MainFrame getFrame() {
        return frame;
    }

    public NotificationArea getNotificationArea() {
        return notificationArea;
    }

    public Font getGameFont(){
        return gameFont;
    }

    public SoundManager getSoundManager(){
        return this.soundManager;
    }

    public static void raiseException(Exception e){
        String formattedStackTrace = "";
        for(StackTraceElement element : e.getStackTrace()){
            formattedStackTrace += element.toString()+"\n";
        }
        JOptionPane.showMessageDialog(null,
                "Due to the following Error, Project Unknown was unable to start/run: \n"+e.getMessage()+"\n\n"+ formattedStackTrace,
                "Severe Error", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
        System.exit(-1);
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
