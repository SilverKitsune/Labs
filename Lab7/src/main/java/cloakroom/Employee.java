package cloakroom;

public class Employee {

    private String name;

    public boolean isHired;

    public boolean isBusy;

    private int salary;

    private int efficiency;

    public Employee(String name, int efficiency) {
        this.name = name;
        this.efficiency = efficiency;
        salary = 0;

    }

    public void hire() {
        isHired = true;
        setSalary();
        System.out.println("Сотрудник " + name +" нанят");
    }

    public void fire() {
        isHired = false;
        salary = 0;
        System.out.println("Сотрудник " + name +" уволен");
    }

    public void work(Spot spot, boolean toTake) {
        System.out.println("Сотрудник " + name);
        if (toTake)
            spot.take();
        else
            spot.free();
    }

    public String getName() {
        return name;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(){
        salary = efficiency*100;
    }

    public int getEfficiency() {
        return efficiency;
    }

}