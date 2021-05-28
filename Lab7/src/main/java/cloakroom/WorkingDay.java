package main.java.cloakroom;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import main.java.context.CloakroomContext;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

import static main.java.enums.CloakroomEvents.*;

public class WorkingDay {
    private final static int TICK_TIMER = 300;
    private AtomicBoolean isNotEnd;
    private AtomicBoolean isPaused;
    private final CloakroomContext cloakroomContext;
    private ExecutorService timerService;
    private final Runnable runnable;
    private final IntegerProperty timeToWork;

    public WorkingDay(CloakroomContext context){
        cloakroomContext = context;
        timeToWork = new SimpleIntegerProperty(0);
        timerService = Executors.newSingleThreadExecutor();
        isNotEnd = new AtomicBoolean(true);
        isPaused = new AtomicBoolean(false);
        runnable = () -> {
            while (isNotEnd.get()) {
                cloakroomContext.safeTrigger(START);
                double lastTime = timeToWork.get();
                if (lastTime > 0 && !isPaused.get()) {
                    int newTime = timeToWork.get() - TICK_TIMER;
                    timeToWork.set(newTime);
                    if (newTime <= 0) {
                        cloakroomContext.safeTrigger(STOP);
                        isPaused.set(true);
                    } //else cloakroomContext.safeTrigger(NO_SPOT);
                }
            }
        };
        timerService.submit(runnable);
    }
    public void pause() {
        isPaused.set(true);
    }

    public void unPause() {
        isPaused.set(false);
    }

    public void stopAppAndTimer() {
        isPaused.set(true);
        isNotEnd.set(false);
        timerService.shutdown();
    }

    public IntegerProperty getTimeToWork() {
        return timeToWork;
    }

    public void startTimer(Integer timer) {
        isPaused.set(false);
        timeToWork.set(timer);
    }

    public void endTimer() {
        isPaused.set(true);
        timeToWork.set(0);
    }
}
