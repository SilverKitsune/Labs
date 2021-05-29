package view;

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
import resources.ResourceUtils;

public class CloakroomView {

    private Scene scene;
    private ImageView cloakroomState;
    private Label timeToWork;
    private Label emptySpace;

    private Label budget;
    private Label profit;

    private Label logStateLabel;
    private String employees;

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

        budget = new Label("00000");
        profit = new Label("00000");
        budget.setFont(new Font("consolas", 18));
        profit.setFont(new Font("consolas", 18));
        profit.setTextFill(Color.GREEN);


        timeToWork = new Label("01:00");
        emptySpace = new Label("100");
        logStateLabel = new Label("");
        timeToWork.setTextFill(Color.RED);
        emptySpace.setTextFill(Color.RED);
        timeToWork.setFont(new Font("consolas", 46));
        emptySpace.setFont(new Font("consolas", 64));
        logStateLabel.setFont(new Font("consolas", 14));
        logStateLabel.setTextFill(Color.WHITE);
        timeToWork.setBackground(new Background(new BackgroundFill(Color.BLACK, new CornerRadii(0.0), new Insets(0))));
        emptySpace.setBackground(new Background(new BackgroundFill(Color.BLACK, new CornerRadii(0.0), new Insets(0))));
        Pane stackPane = new Pane(cloakroomState, timeToWork,logStateLabel, emptySpace, profit, budget);

        profit.setLayoutX(564.0);
        profit.setLayoutY(714.0);

        budget.setLayoutX(753.0);
        budget.setLayoutY(714.0);

        timeToWork.setLayoutX(1352.0);
        timeToWork.setLayoutY(466.0);
        emptySpace.setLayoutX(592.0);
        emptySpace.setLayoutY(11.0);
        logStateLabel.setLayoutX(34);
        logStateLabel.setLayoutY(600);
        scene = new Scene(stackPane, 1528, 743);
        scene.setFill(Color.WHITE);
        return scene;
    }

    public void cloakroomToOpen(){
        changeImageTo(ResourceUtils.getCloakroomOpen(employees));
    }

    public void cloakroomToReadyToWork(){
        changeImageTo(ResourceUtils.getCloakroomReadyToWork());
    }

    public void cloakroomToWorking(){
        changeImageTo(ResourceUtils.getCloakroomWorking());
    }

    public void cloakroomToDontTake(){
        changeImageTo(ResourceUtils.getCloakroomNoSpot());
    }

    public void cloakroomToClose(){
        changeImageTo(ResourceUtils.getCloakroomClosed());
    }

    public void setState(String state) {
        this.logStateLabel.setText(state);
    }

    public void setMouseEvent(EventHandler<MouseEvent> mouseEventEventHandler) {
        scene.setOnMouseClicked(mouseEventEventHandler);
    }

    private void changeImageTo(Image image) {
        if(cloakroomState.getImage()!=image)
            cloakroomState.setImage(image);
    }

    public void setTimeToWork(String timeToCookStr) {

    }

    public void editEmployees(String names){
        employees = names;
    }

    public void showSettings(){
        //TODO
    }

}
