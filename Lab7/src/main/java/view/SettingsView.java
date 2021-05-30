package view;

import controller.Settings;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Consumer;

public class SettingsView {

    private Dialog<Settings> dialog;

    public SettingsView(Settings settings) {
        dialog = new Dialog<>();
        dialog.setTitle("Диалог с микроволновкой");
        dialog.setHeaderText("Какую еду вы хотите приготовить?");

// Set the button types.
        ButtonType ок = new ButtonType("ОК", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(ок);

// Create the username and password labels and fields.
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField budgetField = new TextField(""+settings.getBudget());
        budgetField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                String value = newValue.replaceAll("[^\\d]", "");
                budgetField.setText(value);
            }
        });
        budgetField.setPromptText("Budget");

        TextField periodField = new TextField(""+settings.getPeriod());
        periodField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                String value = newValue.replaceAll("[^\\d]", "");
                periodField.setText(value);
            }
        });
        periodField.setPromptText("Period");

        TextField time = new TextField(""+settings.getWorkingDay());
        time.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                String value = newValue.replaceAll("[^\\d]", "");
                time.setText(value);
            }
        });
        time.setPromptText("Long");

        grid.add(new Label("Budget:"), 0, 0);
        grid.add(budgetField, 1, 0);

        grid.add(new Label("Long:"), 0, 1);
        grid.add(time, 1, 1);

        grid.add(new Label("Period:"), 0, 2);
        grid.add(periodField, 1, 2);

// Enable/Disable login button depending on whether a username was entered.
        Node loginButton = dialog.getDialogPane().lookupButton(ок);
        //loginButton.setDisable(true);

// Do some validation (using the Java 8 lambda syntax).
        /*time.textProperty().addListener((observable, oldValue, newValue) -> {
            loginButton.setDisable(newValue.trim().isEmpty());
        });*/

        dialog.getDialogPane().setContent(grid);

// Request focus on the username field by default.
        //Platform.runLater(budgetField::requestFocus);

// Convert the result to a username-password-pair when the login button is clicked.
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == ок) {
                int timeToWork = Integer.parseInt(time.getText());
                int period = Integer.parseInt(periodField.getText());
                int budget = Integer.parseInt(budgetField.getText());
                return new Settings(budget, period, timeToWork);
            }
            else dialog.close();
            return null;
        });


    }

    public Settings showAndWait() {
        try {
            Optional<Settings> result = dialog.showAndWait();
            return result.get();
        }catch (NoSuchElementException e){
            return null;
        }

    }

    public Settings getResult(){
        return dialog.getResult();
    }
}
