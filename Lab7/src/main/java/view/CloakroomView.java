package main.java.view;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import main.java.resources.ResourceUtils;

public class CloakroomView {

    private Scene scene;
    private ImageView cloakroomState;
    private Label timeToCook;
    private Label logStateLabel;

    public CloakroomView(){

    }

    public Scene getScene(){
        if (scene == null) {
            scene = getInitScene();
        }
        return scene;
    }

    public Scene getInitScene(){
        cloakroomState = new ImageView();
        timeToCook = new Label("00:00");
        logStateLabel = new Label("");
        timeToCook.setTextFill(Color.WHITE);
        timeToCook.setFont(new Font("Arial", 24));
        logStateLabel.setFont(new Font("Arial", 14));
        timeToCook.setBackground(new Background(new BackgroundFill(Color.BLACK, new CornerRadii(0.0), new Insets(0))));
        Pane stackPane = new Pane(cloakroomState, timeToCook,logStateLabel);
        timeToCook.setLayoutX(410.0);
        timeToCook.setLayoutY(80.0);
        logStateLabel.setLayoutX(34);
        logStateLabel.setLayoutY(342);
        scene = new Scene(stackPane, 500, 380);
        scene.setFill(Color.WHITE);
        return scene;
    }

    public void cloakroomToOpen(){
        changeImageTo(ResourceUtils.getMicrowaveEmpty());
    }

    public void cloakroomToReadyToWork(){
        changeImageTo(ResourceUtils.getMicrowaveToReadyToCook());
    }

    public void cloakroomToWorking(){
        changeImageTo(ResourceUtils.getMicrowaveCooking());
    }

    public void cloakroomToDontTake(){
        changeImageTo(ResourceUtils.getMicrowaveInterrupted());
    }

    public void cloakroomToClose(){
        changeImageTo(ResourceUtils.getMicrowaveToCookingComplete());
    }

    public void setState(String state) {
        //this.logStateLabel.setText(state);
    }

    public void setMouseEvent(EventHandler<MouseEvent> mouseEventEventHandler) {
        scene.setOnMouseClicked(mouseEventEventHandler);
    }

    private void changeImageTo(Image microwaveToReadyToCook) {
        if(cloakroomState.getImage()!=microwaveToReadyToCook)
            cloakroomState.setImage(microwaveToReadyToCook);
    }

    public void setTimeToCook(String timeToCookStr) {

    }

    public void editEmployees(){
        //TODO
    }

    public void showSettings(){

    }

}
