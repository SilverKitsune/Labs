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

    public IntegerProperty budget;
    public IntegerProperty profit;
    private final IntegerProperty workingDay;
    private IntegerProperty freeSpace;
    private IntegerProperty lengthOfTheDay;

    private AtomicBoolean isNotEnd;
    private AtomicBoolean isReady;
    private AtomicBoolean isPaused;

    //private LinkedList<AtomicBoolean> isEmpl;

    private final CloakroomContext cloakroomContext;
    private ExecutorService timerService;
    private final Runnable runnable;


    private int period;

    public Cloakroom(CloakroomContext context) {
        cloakroomContext = context;
        notEmptySpots = new LinkedList<>();
        listOfEmployees = new LinkedList<>();
        workingDay = new SimpleIntegerProperty(0);
        freeSpace = new SimpleIntegerProperty(100);
        budget = new SimpleIntegerProperty(0);
        profit = new SimpleIntegerProperty(0);
        lengthOfTheDay = new SimpleIntegerProperty(0);
        timerService = Executors.newSingleThreadExecutor();
        isNotEnd = new AtomicBoolean(true);
        isPaused = new AtomicBoolean(false);
        isReady = new AtomicBoolean(false);
        /*isEmpl = new LinkedList<>();
        isEmpl.add(new AtomicBoolean(false));
        isEmpl.add(new AtomicBoolean(false));
        isEmpl.add(new AtomicBoolean(false));*/

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
                                Cloakroom.this.work();
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


    public void setBudget(int newBudget) {
        budget.set(newBudget);
    }

    public void setTimeToWork(int newTime) {
        workingDay.set(newTime);
    }

    public void setProfit(int newProfit) {
        profit.set(newProfit);
    }

    public void setSpots(int count) {
        spots = new LinkedList<>();
        for (int i = 0; i < count; i++) {
            spots.add(new Spot(i + 1));
        }
    }

    public void setLengthOfTheDay(int lengthOfTheDay) {
        this.lengthOfTheDay.set(lengthOfTheDay);
    }

    private void work() throws InterruptedException {
        Random random = new Random();
        int randomInt = random.nextInt(10);
        boolean isTake = (randomInt < 7 && workingDay.get() > lengthOfTheDay.get() * 0.5) || (randomInt < 3 && workingDay.get() <= lengthOfTheDay.get() * 0.5);
        boolean isGive = (randomInt > 7 && workingDay.get() > lengthOfTheDay.get() * 0.5) || (randomInt > 3 && workingDay.get() <= lengthOfTheDay.get() * 0.5);
        int employee = getRandomEmployee(random);
        try {
            if (isTake) {
                if (freeSpace.get() > 0) {
                    Spot s = getFirstFreeSpot();
                    cloakroomContext.safeTrigger(VISITOR_GIVE_JACKET);
                    takeJacket(s, 1);
                } else {
                    cloakroomContext.safeTrigger(NO_SPOT);
                    isPaused.set(true);
                }
            }
            if (isGive) {
                Integer num = getRandomNotEmptySpot(random);
                if (num != null) {
                    cloakroomContext.safeTrigger(VISITOR_TAKE_JACKET);
                    giveJacket(num, 1);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
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

    private void takeJacket(Spot s, int employeeIndex) throws InterruptedException {
        Employee employee = listOfEmployees.get(employeeIndex);
        employee.work(s, true);
        notEmptySpots.add(s.number);
        int newFreeSpace = freeSpace.get() - 1;
        freeSpace.set(newFreeSpace);
        paySalary(employee.getSalary());
    }

    private void giveJacket(int number, int employeeIndex) throws InterruptedException {
        Employee employee = listOfEmployees.get(employeeIndex);
        employee.work(spots.get(number - 1), false);
        notEmptySpots.remove(new Integer(number));
        int newFreeSpace = freeSpace.get() + 1;
        freeSpace.set(newFreeSpace);
        paySalary(employee.getSalary());
    }

    private void paySalary(int salary) {
        setProfit(profit.getValue() - salary);
    }

    public IntegerProperty getWorkingDay() {
        return workingDay;
    }

    public String getEmployees() {
        StringBuilder names = new StringBuilder();
        for (Employee e : listOfEmployees) {
            if (e.isHired) {
                names.append("_");
                names.append(e.getName());
            }
        }
        return names.toString();
    }

    private int getRandomEmployee(Random random) {
        int num;
        do {
            num = random.nextInt(3);
        } while (!listOfEmployees.get(num).isHired);
        return num;
    }

    private Integer getRandomNotEmptySpot(Random random) {
        if (!notEmptySpots.isEmpty())
            return notEmptySpots.get(random.nextInt(notEmptySpots.size()));
        else
            return null;
    }

    public Spot getFirstFreeSpot() {
        for (Spot s : spots) {
            if (s.isFree())
                return s;
        }
        return null;
    }

    public IntegerProperty getFreeSpace() {
        return freeSpace;
    }

    public IntegerProperty getBudget() {
        return budget;
    }

    public void makeReady() {
        isReady.set(true);
    }

    public void stopApplication() {
        isNotEnd.set(false);
        timerService.shutdown();
    }

    public IntegerProperty getProfit() {
        return profit;
    }

    public void setPeriod(int period) {
        this.period = period;
    }
}