package main.java.starter;

import javafx.application.Application;
import javafx.stage.Stage;
import main.java.context.CloakroomContext;
import main.java.controller.CloakroomController;
import main.java.view.CloakroomView;

public class AppStarter extends Application {
    static CloakroomView microwaveView;
    static CloakroomController microwaveController;


    @Override
    public void start(Stage primaryStage) {
        microwaveView = new CloakroomView();
        primaryStage.setScene(microwaveView.getScene());
        microwaveController = new CloakroomController(new CloakroomContext(),microwaveView);
        primaryStage.show();
    }


    @Override
    public void stop() throws Exception {
        super.stop();
        microwaveController.stopApplication();
        microwaveController = null;
        microwaveView = null;
    }

    public void defaultStart(String[] args) {
        launch(args);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
