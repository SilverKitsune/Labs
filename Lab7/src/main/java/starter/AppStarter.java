package starter;

import javafx.application.Application;
import javafx.stage.Stage;
import context.CloakroomContext;
import controller.CloakroomController;
import view.CloakroomView;

public class AppStarter extends Application {
    static CloakroomView cloakroomView;
    static CloakroomController cloakroomController;


    @Override
    public void start(Stage primaryStage) {
        cloakroomView = new CloakroomView();
        primaryStage.setScene(cloakroomView.getScene());
        cloakroomController = new CloakroomController(new CloakroomContext(), cloakroomView);
        primaryStage.show();
    }


    @Override
    public void stop() throws Exception {
        super.stop();
        cloakroomController.stopApplication();
        cloakroomController = null;
        cloakroomView = null;
    }

    public void defaultStart(String[] args) {
        launch(args);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
