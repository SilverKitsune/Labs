package main.java.cloakroom;


import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import main.java.context.CloakroomContext;

import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

import static main.java.enums.CloakroomEvents.NO_SPOT;
import static main.java.enums.CloakroomEvents.STOP;

public class Cloakroom {

    private LinkedList<Employee> listOfEmployees;

    private LinkedList<Spot> spots;
    private LinkedList<Integer> notEmptySpots;

    public int budget;

    private AtomicBoolean isNotEnd;
    private AtomicBoolean isReady;
    private AtomicBoolean isPaused;
    private final CloakroomContext cloakroomContext;
    private ExecutorService timerService;
    private final Runnable runnable;

    private final IntegerProperty workingDay;
    private final int WORKING_DAY = 60000;

    public IntegerProperty getWorkingDay(){
        return workingDay;
    }

    public Cloakroom(CloakroomContext context, int period, int budget) {
        cloakroomContext = context;
        notEmptySpots = new LinkedList<>();
        listOfEmployees = new LinkedList<>();
        Random random = new Random();
        this.budget = budget;
        timerService = Executors.newSingleThreadExecutor();
        isNotEnd = new AtomicBoolean(true);
        isPaused = new AtomicBoolean(false);
        isReady = new AtomicBoolean(false);
        workingDay = new SimpleIntegerProperty(WORKING_DAY);

        runnable = () -> {
            while (isNotEnd.get()) {
                if (isReady.get()) {
                    try {
                        Thread.sleep(period);
                        double lastTime = workingDay.get();
                        if (lastTime > 0) {
                            int newTime = workingDay.get() - period;
                            workingDay.set(newTime);
                            if (newTime <= 0) {
                                cloakroomContext.safeTrigger(STOP);
                                isReady.set(false);
                            } else {
                                int employee = getRandomEmployee(random);
                                if (random.nextBoolean() && !isPaused.get()) {
                                    Spot s = getFirstFreeSpot();
                                    if (s != null)
                                        takeJacket(s, employee);
                                    else {
                                        cloakroomContext.safeTrigger(NO_SPOT);
                                        isPaused.set(true);
                                    }
                                } else {
                                    int num = getRandomNotEmptySpot(random);
                                    if (num > 0)
                                        giveJacket(num, employee);
                                }
                            }
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        timerService.submit(runnable);
    }

    public void setSpots(int count) {
        spots = new LinkedList<>();
        for (int i = 0; i < count; i++) {
            spots.add(new Spot(i + 1));
        }
    }

    public void waitForGive() {

    }

    private int getRandomEmployee(Random random) {
        int num;
        do {
            num = random.nextInt(3);
        } while (!listOfEmployees.get(num).isHired);
        return num;
    }

    private int getRandomNotEmptySpot(Random random) {
        if (!notEmptySpots.isEmpty())
            return notEmptySpots.get(random.nextInt(notEmptySpots.size()));
        else
            return -1;
    }

    public void setListOfEmployees(LinkedList<Employee> listOfEmployees) {
        this.listOfEmployees = listOfEmployees;
        for (Employee e : this.listOfEmployees) {
            e.hire();
        }
    }

    public void hireEmployee(Employee employee) {
        employee.hire();
        listOfEmployees.add(employee);
    }

    public void hireEmployee(String name) {
        for (Employee e : listOfEmployees) {
            if (e.getName().equals(name) && !e.isHired)
                e.hire();
        }
    }

    public void fireEmployee(String name) {
        for (Employee e : listOfEmployees) {
            if (e.getName().equals(name) && e.isHired)
                e.fire();
        }
    }

    public Spot getFirstFreeSpot() {
        for (Spot s : spots) {
            if (!s.isTaken)
                return s;
        }
        return null;
    }

    public void takeJacket(Spot s, int employeeIndex) {
        Employee employee = listOfEmployees.get(employeeIndex);
        employee.work(s, true);
        notEmptySpots.add(s.number);
        paySalary(employee.getSalary());
    }

    public void giveJacket(int number, int employeeIndex) {
        Employee employee = listOfEmployees.get(employeeIndex);
        employee.work(spots.get(number), false);
        notEmptySpots.remove(number);
        paySalary(employee.getSalary());
    }

    public void paySalary(int salary) {
        budget -= salary;
    }

    public void makeReady(){
        isReady.set(true);
    }

    public void stopApplication() {
        isNotEnd.set(false);
        timerService.shutdown();
    }
}