package Model;

/**
 * Created by jardu on 1/2/2017.
 */
public class Notification {

    private String title;
    private String message;

    public Notification(String title, String message){
        this.title = title;
        this.message = message;
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }
}
