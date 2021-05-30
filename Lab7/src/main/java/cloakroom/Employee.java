package cloakroom;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicBoolean;

public class Employee {

    private String name;

    public boolean isHired;

    private AtomicBoolean isBusy;

    private int efficiency;

    private AtomicBoolean isTake;
    private AtomicBoolean isGive;
    private AtomicBoolean isNotEnd;

    private Spot s;

    public Employee(String name, int efficiency, ExecutorService timerService) {
        this.name = name;
        this.efficiency = efficiency;
        hire();
        isBusy = new AtomicBoolean(false);
        isTake = new AtomicBoolean(false);
        isGive = new AtomicBoolean(false);
        isNotEnd = new AtomicBoolean(true);
        Runnable thread = () -> {
          while(isNotEnd.get()){
              if(isBusy.get()){
                  try {
                      work();
                  } catch (InterruptedException e) {
                      e.printStackTrace();
                  }
              }
          }
        };
        timerService.submit(thread);
    }

    public void hire() {
        isHired = true;
        System.out.println("Сотрудник " + name + " нанят");
    }

    public void fire() {
        isHired = false;
        System.out.println("Сотрудник " + name + " уволен");
    }

    public void work() throws InterruptedException {
        Thread.sleep(Math.abs(efficiency - 4) * 300);
        System.out.println("Сотрудник " + name + " занят");
        if (isTake.get())
            s.take();
        if (isGive.get())
            s.free();
        isGive.set(false);
        isTake.set(false);
        isBusy.set(false);
        System.out.println("Сотрудник " + name + " свободен");
    }

    public String getName() {
        return name;
    }

    public int getSalary() {
        return efficiency * 10;
    }

    public boolean getIsBusy(){
        return isBusy.get();
    }

    public void setIsTake(Spot spot) {
        isBusy.set(true);
        this.isTake.set(true);
        s = spot;
    }

    public void setIsGive(Spot spot) {
        isBusy.set(true);
        isGive.set(true);
        s = spot;
    }

    public void stop(){
        isNotEnd.set(false);
    }
}