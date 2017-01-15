package Model.UI.Screen;

import Control.ProjectUnknownProperties;
import Model.Abstraction.IEventInteractableObject;
import Model.UI.Button;
import Model.UI.Label;
import View.DrawingPanel;
import org.jetbrains.annotations.NotNull;

import java.awt.event.KeyEvent;

/**
 * Created by Anthony on 12.01.2017.
 */

public class GameOver extends DrawingPanel{

    private Button back;
    private Label lblHeadline;
    private Label die1;
    private Label die2;
    private Label die3;
    private Label die4;
    private int fontHeight;

    public GameOver(@NotNull ProjectUnknownProperties properties) {
        super(properties);
        fontHeight = getFontMetrics(properties.getGameFont()).getHeight();

        lblHeadline = new Label(screenWidth / 2, screenHeight / 10 * 2, "GAME OVER - YOU DIED", properties.getGameFont().deriveFont(50f));
        die1 = new Label(screenWidth/4, screenHeight/3, "diediediediediediediediediediediedie", properties.getGameFont());
        die2 = new Label(screenWidth/4, screenHeight/3 + fontHeight, "diediediediediediediediediediediedie", properties.getGameFont());
        die3 = new Label(screenWidth/4, screenHeight/3 + fontHeight*2, "diediediediediediediediediediediedie", properties.getGameFont());
        die4 = new Label(screenWidth/4, screenHeight/3 + fontHeight*3, "diediediediediediediediediediediedie", properties.getGameFont());

        back = new Button(screenWidth / 4, screenHeight / 10 * 9,screenWidth/2, 100, "Back to Menu", properties.getGameFont().deriveFont(30f));
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
        addObject(die1);
        addObject(die2);
        addObject(die3);
        addObject(die4);
    }
}
