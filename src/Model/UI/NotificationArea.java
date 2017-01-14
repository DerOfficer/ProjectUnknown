package Model.UI;

import Control.ProjectUnknownProperties;
import Model.Abstraction.ICanvas;
import Model.Abstraction.IDrawableObject;
import Model.Notification;
import com.SSA.Animation.Animation;
import com.SSA.Animation.AnimationObject;
import com.SSA.Annotation.Animatable;
import com.SSA.Parsing.AnimParser;
import com.SSA.Parsing.AnimationList;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by jardu on 1/2/2017.
 */
public class NotificationArea implements IDrawableObject {

    private Queue<Notification> notificationQueue;

    private Notification currentNotification;

    private AnimationWrapper wrapper;
    private Animation showNotificationAnimation;

    private ICanvas canvas;

    private ProjectUnknownProperties properties;

    public NotificationArea(ProjectUnknownProperties properties) {
        notificationQueue = new LinkedList<>();
        this.properties = properties;
        wrapper = new AnimationWrapper("animation_wrapper");
        AnimationList animationList = AnimParser.parseAnimFile(Paths.get("notification.anim"));
        showNotificationAnimation = animationList.getAnimationByName("show_notification");
    }

    public void addNotification(Notification notification) {
        if (!wrapper.currentlyAnimating) {
            showNotification(notification);
        } else {
            notificationQueue.offer(notification);
        }
    }

    private void showNotification(Notification notification) {
        currentNotification = notification;
        properties.getSoundManager().startSound(3);
        wrapper.animate(showNotificationAnimation);
    }

    @Override
    public void draw() {
        Graphics2D g = canvas.getPencil();
        g.setColor(new Color(0x4B200B));
        g.fillRect(0, wrapper.y, 400, 100);
        g.setColor(new Color(0x6C360F));
        g.fillRect(10, wrapper.y + 10, 380, 80);
        if (currentNotification != null) {
            g.setColor(Color.BLACK);

            Font titleFont = new Font(g.getFont().getName(), Font.BOLD, 20);
            Font messageFont = g.getFont();

            g.setFont(titleFont);
            FontMetrics fm = g.getFontMetrics();

            int titleHeight = fm.getHeight() + fm.getDescent();
            int titleWidth = fm.stringWidth(currentNotification.getTitle());

            g.drawString(currentNotification.getTitle(), 400 / 2 - titleWidth / 2, wrapper.y + 40 / 2 + titleHeight / 2);

            g.setFont(messageFont);
            fm = g.getFontMetrics();

            int msgHeight = fm.getHeight() + fm.getDescent();
            String[] msgArr = currentNotification.getMessage().split("\n");

            for (int i = 0; i < msgArr.length; ++i) {
                int msgWidth = fm.stringWidth(msgArr[i]);
                g.drawString(msgArr[i], 400 / 2 - msgWidth / 2, wrapper.y + 40 + 40 / 2 - msgHeight * msgArr.length / 2 + i * msgHeight + fm.getHeight());
            }
        }
    }

    @Override
    public void update(double dt) {

    }

    @Override
    public void provideCanvas(ICanvas canvas) {
        this.canvas = canvas;
    }

    @Override
    public Shape getBounds() {
        //Since we arent interactable, its doesnt matter what we return here, as long as it isnt null
        return new Rectangle2D.Double();
    }

    private class AnimationWrapper extends AnimationObject {

        private boolean currentlyAnimating;

        @Animatable (onFinished = "onFinished")
        private int y;

        public AnimationWrapper(String name) {
            super(name);
            y = -200;
        }

        private void onFinished() {
            System.out.println(y);
            if (y == -200) {
                currentlyAnimating = false;
                if (notificationQueue.peek() != null) { //implicit null = false W H E N
                    showNotification(notificationQueue.poll());
                } else {
                    currentNotification = null;
                }
            }
        }

        @Override
        public void animate(Animation animation) {
            currentlyAnimating = true;
            super.animate(animation);
        }
    }
}
