package main.java.resources;

import javafx.scene.image.Image;

import java.io.InputStream;

public class ResourceUtils {
    private static Image microwaveEmpty;
    private static Image microwaveReadyToCook;
    private static Image microwaveCooking;
    private static Image microwaveInterrupted;
    private static Image microwaveCookingComplete;

    private static InputStream getRecourse(String name) {
        InputStream in;
        try {
            in = ResourceUtils.class.getClassLoader().getResourceAsStream(name );
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return in;
    }

    private static Image lazyInitImage(Image image, String name) {
        if (image == null) {
            InputStream is = ResourceUtils.getRecourse(name);
            if (is != null)
                return new Image(is);
        }
        return image;
    }

    public static Image getMicrowaveInterrupted() {
        microwaveInterrupted = lazyInitImage(microwaveInterrupted, "microwave_interrupted.jpg");
        return microwaveInterrupted;
    }

    public static Image getMicrowaveEmpty() {
        microwaveEmpty = lazyInitImage(microwaveEmpty, "microwave_empty.jpg");
        return microwaveEmpty;
    }

    public static Image getMicrowaveCooking() {
        microwaveCooking = lazyInitImage(microwaveCooking,"microwave.gif");
        return microwaveCooking;
    }

    public static Image getMicrowaveToReadyToCook() {
        microwaveReadyToCook = lazyInitImage(microwaveReadyToCook,"microwave_ready.jpg");
        return microwaveReadyToCook;
    }

    public static Image getMicrowaveToCookingComplete() {
        microwaveCookingComplete = lazyInitImage(microwaveCookingComplete,"microwave_complete.jpg");
        return microwaveCookingComplete;
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
