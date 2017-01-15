package Model.UI.Screen;

import Control.ProjectUnknownProperties;
import Model.Abstraction.IEventInteractableObject;
import Model.UI.Button;
import Model.UI.Label;
import View.DrawingPanel;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by Anthony on 12.01.2017.
 */

public class GameOver extends DrawingPanel{

    private Button back;
    private Label lblHeadline;
    private int fontHeight;

    public GameOver(@NotNull ProjectUnknownProperties properties) {
        super(properties);
        fontHeight = getFontMetrics(properties.getGameFont()).getHeight();

        lblHeadline = new Label(screenWidth / 2, screenHeight / 10 * 2, "GAME OVER - YOU DIED", properties.getGameFont().deriveFont(50f));

        back = new Button(screenWidth / 4, screenHeight / 10 * 8,screenWidth/2, 100, "Back to Menu", properties.getGameFont().deriveFont(30f));
        back.setForegroundColor(new Color(255,255,255));
        back.addEventHandler(IEventInteractableObject.EventType.MOUSE_RELEASED, (event) -> {
            properties.getSoundManager().startSound(4);
            properties.getFrame().setContentPanel(properties.getFrame().getStart());
        });
        back.addEventHandler(IEventInteractableObject.EventType.KEY_PRESSED, (event) -> {
            if (event.getSrcKey() == KeyEvent.VK_ESCAPE) {
                properties.getSoundManager().startSound(4);
                properties.getFrame().setContentPanel(properties.getFrame().getStart());
            }
        });

        addObject(lblHeadline);
        addObject(back);
    }
}
