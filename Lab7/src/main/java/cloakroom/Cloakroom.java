package cloakroom;


import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import context.CloakroomContext;

import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

import static enums.CloakroomEvents.*;

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
    private IntegerProperty freeSpace;

    public IntegerProperty getWorkingDay(){
        return workingDay;
    }

    public String getEmployees(){
        StringBuilder names = new StringBuilder();
        for (Employee e : listOfEmployees) {
            if (e.isHired){
                names.append("_");
                names.append(e.getName());
            }
        }
        return names.toString();
    }

    public Cloakroom(CloakroomContext context, int period, int budget) {
        cloakroomContext = context;
        notEmptySpots = new LinkedList<>();
        listOfEmployees = new LinkedList<>();

        this.budget = budget;
        timerService = Executors.newSingleThreadExecutor();
        isNotEnd = new AtomicBoolean(true);
        isPaused = new AtomicBoolean(false);
        isReady = new AtomicBoolean(false);
        workingDay = new SimpleIntegerProperty(WORKING_DAY);
        freeSpace = new SimpleIntegerProperty(100);

        runnable = () -> {
            while (isNotEnd.get()) {
                if (isReady.get()) {
                    try {
                        double lastTime = workingDay.get();
                        if (lastTime > 0) {
                            int newTime = workingDay.get() - period;
                            workingDay.set(newTime);
                            if (newTime <= 0) {
                                cloakroomContext.safeTrigger(STOP);
                                isReady.set(false);
                            } else {
                                work();
                                Thread.sleep(period);
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

    public void work(){
        Random random = new Random();
        int employee = getRandomEmployee(random);
        if (random.nextBoolean() && !isPaused.get()) {
            Spot s = getFirstFreeSpot();
            if (s != null){
                cloakroomContext.safeTrigger(VISITOR_GIVE_JACKET);
                takeJacket(s, employee);
            }
            else {
                cloakroomContext.safeTrigger(NO_SPOT);
                isPaused.set(true);
            }
        } else {
            int num = getRandomNotEmptySpot(random);
            if (num > 0) {
                cloakroomContext.safeTrigger(VISITOR_TAKE_JACKET);
                giveJacket(num, employee);
            }
        }
    }

    public void setSpots(int count) {
        spots = new LinkedList<>();
        for (int i = 0; i < count; i++) {
            spots.add(new Spot(i + 1));
        }
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
        freeSpace.set(freeSpace.get()-1);
        paySalary(employee.getSalary());
    }

    public void giveJacket(int number, int employeeIndex) {
        Employee employee = listOfEmployees.get(employeeIndex);
        employee.work(spots.get(number-1), false);
        notEmptySpots.remove(new Integer(number));
        freeSpace.set(freeSpace.get()+1);
        paySalary(employee.getSalary());
    }

    public int getFreeSpace() {
        return freeSpace.get();
    }

    public int getBudget(){
        return budget;
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