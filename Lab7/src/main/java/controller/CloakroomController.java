package controller;

import au.com.ds.ef.EasyFlow;
import au.com.ds.ef.call.ContextHandler;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import cloakroom.Cloakroom;
import cloakroom.Employee;
import context.CloakroomContext;
import resources.ResourceUtils;
import view.CloakroomView;

import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static au.com.ds.ef.FlowBuilder.from;
import static au.com.ds.ef.FlowBuilder.on;
import static enums.CloakroomEvents.*;
import static enums.CloakroomStates.*;

public class CloakroomController {
    private Cloakroom cloakroom;
    private CloakroomView cloakroomView;
    private ExecutorService stateMachine_executor;
    private int eatTimeCook = 0;
    private boolean isApply = false;
    private boolean isStopped = false;
    private boolean isWorking = false;
    private LinkedList<MyButton> buttons;


    public CloakroomController(CloakroomContext cloakroomContext, CloakroomView cloakroomView) {
        cloakroom = new Cloakroom(cloakroomContext, 300, 10000);
        this.cloakroomView = cloakroomView;
        stateMachine_executor = Executors.newSingleThreadExecutor();
        initButtons();
        initStateMachine(cloakroomContext);
        initViewTriggersToStateMachineEvents(cloakroomContext);
        bindTimeToCook(cloakroom.getWorkingDay());

    }

    private void initButtons() {
        buttons = new LinkedList<>();
        buttons.add(new MyButton("Start", 1351 + 78, 17 + 2, 64, 72));
        buttons.add(new MyButton("George", 1479, 181, 24, 24));
        buttons.add(new MyButton("John", 1479, 221, 24, 24));
        buttons.add(new MyButton("Paul", 1479, 257, 24, 24));
        buttons.add(new MyButton("Apply", 1369, 308, 41, 90));
        buttons.add(new MyButton("NewDay", 1358, 633, 39, 122));
        buttons.add(new MyButton("Settings", 1357, 687, 39, 122));
    }

    private void initStateMachine(CloakroomContext cloakroomContext) {
        EasyFlow<CloakroomContext> cloakroomFlow = initStates();
        initEvents(cloakroomFlow);
        cloakroomFlow.start(cloakroomContext);
    }

    private void initEvents(EasyFlow<CloakroomContext> cloakroomFlow) {
        cloakroomFlow.whenEnter(OPEN, (ContextHandler<CloakroomContext>) flowContext -> {
            logStateMachine(flowContext);
            if (!isStopped) {
                cloakroom.hireEmployee(new Employee("George", 1));
                cloakroom.hireEmployee(new Employee("John", 2));
                cloakroom.hireEmployee(new Employee("Paul", 3));
            }
            cloakroomView.editEmployees(cloakroom.getEmployees());
            cloakroomView.cloakroomToOpen();
            cloakroom.setSpots(100);
            isStopped = false;
            isApply = false;
        });

        cloakroomFlow.whenEnter(READY_TO_WORK, (ContextHandler<CloakroomContext>) flowContext -> {
            logStateMachine(flowContext);
            isApply = true;
            cloakroomView.cloakroomToReadyToWork();
        });

        cloakroomFlow.whenEnter(WORKING1, (ContextHandler<CloakroomContext>) flowContext -> {
            logStateMachine(flowContext);
            cloakroomView.cloakroomToWorking();
            isWorking = true;
        });

        cloakroomFlow.whenEnter(WORKING2, (ContextHandler<CloakroomContext>) flowContext -> {
            logStateMachine(flowContext);
            cloakroomView.cloakroomToWorking();
        });

        cloakroomFlow.whenEnter(DONT_TAKE, (ContextHandler<CloakroomContext>) flowContext -> {
            logStateMachine(flowContext);
            cloakroomView.cloakroomToDontTake();
        });

        cloakroomFlow.whenEnter(CLOSE, (ContextHandler<CloakroomContext>) flowContext -> {
            logStateMachine(flowContext);
            cloakroomView.cloakroomToClose();
            isStopped = true;
            isWorking = false;
            isApply = false;
        });
    }

    private MyButton whichButton(double x, double y) {
        for (MyButton button : buttons) {
            if (button.isClicked(x, y))
                return button;
        }
        return null;
    }

    private void initViewTriggersToStateMachineEvents(CloakroomContext cloakroomContext) {
        EventHandler<MouseEvent> mouseEventEventHandler = event -> {
            double x = event.getX();
            double y = event.getY();

            MyButton button = whichButton(x, y);

            if (button != null) {
                switch (button.getName()) {
                    case "Start":
                        if (isApply) {
                            cloakroom.makeReady();
                        }
                        break;
                    case "George":
                    case "John":
                    case "Paul":
                        if (!isApply) {
                            if (button.getClicked()) {
                                cloakroom.fireEmployee(button.getName());
                                button.setClicked(false);
                            } else {
                                cloakroom.hireEmployee(button.getName());
                                button.setClicked(true);
                            }
                            cloakroomView.editEmployees(cloakroom.getEmployees());
                        }
                        cloakroomView.cloakroomToOpen();
                        break;
                    case "Apply":
                        if (!isApply) {
                            cloakroomContext.safeTrigger(HIRE_EMPLOYEES);
                        }
                        break;
                    case "NewDay":
                        if (isStopped) {
                            cloakroomContext.safeTrigger(START);
                        }
                        break;
                    case "Settings":
                        if (!isWorking) {
                            showSettings();
                        }
                        break;
                }
            }
        };
        cloakroomView.setMouseEvent(mouseEventEventHandler);
    }

    private void showSettings() {
        //TODO
    }

   /* private int getNumberButtonByCoord(int buttonStart, int lengthNumberButton, int lengthWithoutButton) {
        int numberButton = 0;
        if (buttonStart < lengthNumberButton) numberButton = 1;
        else if (buttonStart < lengthNumberButton + lengthWithoutButton) return numberButton;
        else if (buttonStart < lengthNumberButton * 2 + lengthWithoutButton) numberButton = 2;
        else if (buttonStart < lengthNumberButton * 2 + lengthWithoutButton * 2) return numberButton;
        else numberButton = 3;
        return numberButton;
    }*/

    private EasyFlow<CloakroomContext> initStates() {
        return from(OPEN).transit(
                on(HIRE_EMPLOYEES).to(READY_TO_WORK).transit(
                        on(VISITOR_GIVE_JACKET).to(WORKING1).transit(
                                on(VISITOR_GIVE_JACKET).to(WORKING2).transit(
                                        on(VISITOR_TAKE_JACKET).to(WORKING1),
                                        on(VISITOR_GIVE_JACKET).to(WORKING1),
                                        on(NO_SPOT).to(DONT_TAKE).transit(
                                                on(VISITOR_TAKE_JACKET).to(WORKING1),
                                                on(STOP).to(CLOSE)
                                        ),
                                        on(STOP).to(CLOSE).transit(
                                                on(START).to(OPEN)
                                        )
                                ),
                                on(VISITOR_TAKE_JACKET).to(WORKING2),
                                on(NO_SPOT).to(DONT_TAKE),
                                on(STOP).to(CLOSE)
                        )
                )
        ).executor(stateMachine_executor);
    }

    private void logStateMachine(CloakroomContext flowContext) {
        Platform.runLater(() -> cloakroomView.setState("STATE: " + flowContext.getState().name() + "\n LAST EVENT: " + flowContext.getLastEvent()));
    }

    public void stopApplication() {
        stateMachine_executor.shutdownNow();
        cloakroom.stopApplication();
    }

    private void bindTimeToCook(IntegerProperty timeToCook) {
        timeToCook.addListener((observable, oldValue, newValue) -> {
            int newTimeToCook = newValue.intValue();
            setTimeCookMills(newTimeToCook);
        });
    }

    private void setTimeCookMills(int newTimeToCook) {
        String timeToCookStr = (newTimeToCook > 0) ? ResourceUtils.convertMillisecondsToMicrowaveTimestamp(newTimeToCook) : "00:00";
        Platform.runLater(() -> cloakroomView.setTimeToWork(timeToCookStr));
    }
}
