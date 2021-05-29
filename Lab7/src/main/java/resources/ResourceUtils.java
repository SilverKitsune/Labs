package resources;

import javafx.scene.image.Image;

import java.io.InputStream;

public class ResourceUtils {
    private static Image cloakroomOpen;
    private static Image cloakroomReadyToWork;
    private static Image cloakroomWorking;
    private static Image cloakroomNoSpot;
    private static Image cloakroomClosed;

    private static InputStream getResource(String name) {
        InputStream in;
        try {
            in = ResourceUtils.class.getClassLoader().getResourceAsStream(name);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return in;
    }

    private static Image lazyInitImage(Image image, String name) {
        if (image == null) {
            InputStream is = ResourceUtils.getResource(name);
            if (is != null)
                return new Image(is);
        }
        return image;
    }

    public static Image getCloakroomOpen(String names) {
        cloakroomOpen = new Image("file:///C:/Users/Allo4ka/IdeaProjects/Ane4ka/Lab7/src/images/open" + names + ".png");
        return cloakroomOpen;
    }

    public static Image getCloakroomReadyToWork() {
        cloakroomReadyToWork = new Image("file:///C:/Users/Allo4ka/IdeaProjects/Ane4ka/Lab7/src/images/ready.png");
        return cloakroomReadyToWork;
    }

    public static Image getCloakroomWorking() {
        cloakroomWorking = new Image("file:///C:/Users/Allo4ka/IdeaProjects/Ane4ka/Lab7/src/images/working.png");
        return cloakroomWorking;
    }

    public static Image getCloakroomNoSpot() {
        cloakroomNoSpot = new Image("file:///C:/Users/Allo4ka/IdeaProjects/Ane4ka/Lab7/src/images/no_spot.png");
        return cloakroomNoSpot;
    }

    public static Image getCloakroomClosed() {
        cloakroomClosed = new Image("file:///C:/Users/Allo4ka/IdeaProjects/Ane4ka/Lab7/src/images/closed.png");
        return cloakroomClosed;
    }

    public static String convertMillisecondsToMicrowaveTimestamp(Integer timeToCook) {
        int seconds = timeToCook / 1000;
        int minutes = seconds / 60;
        int viewSeconds = seconds - minutes * 60;
        String seconds_str;
        String minutess_str;
        if (minutes < 10) {
            minutess_str = "0" + String.valueOf(minutes);
        } else {
            minutess_str = String.valueOf(minutes);
        }
        if (viewSeconds < 10) {
            seconds_str = "0" + String.valueOf(viewSeconds);
        } else {
            seconds_str = String.valueOf(viewSeconds);
        }
        return minutess_str + ":" + seconds_str;
    }
}
