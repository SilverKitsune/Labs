package main.java.cloakroom;

import java.util.LinkedList;

public class Cloakroom {

    private LinkedList<Employee> listOfEmployees;

    private LinkedList<Spot> spots;

    public int budget;

    public Cloakroom(int size) {
        listOfEmployees = new LinkedList<>();
        spots = new LinkedList<>();
        for (int i = 0; i < size; i++)
            spots.add(new Spot(i + 1));
    }

    public void hireEmployee(Employee employee, int salary) {
        employee.hire(salary);
        listOfEmployees.add(employee);
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

    public void takeJacket(String name) {
        for (Employee e : listOfEmployees) {
            if (e.getName().equals(name) && e.isHired) {
                Spot s = getFirstFreeSpot();
                if (s != null)
                    e.work(s, true);
                else
                    System.out.println("Места больше нет!");
            }
        }
    }

    public Boolean giveJacket(int number, String name) {
        if (!spots.get(number).isFree()) {
            for (Employee e : listOfEmployees) {
                if (e.getName().equals(name) && e.isHired)
                    e.work(spots.get(number), false);
            }
            return true;
        }
        System.out.println("Номерок " + number + " не занят");
        return false;
    }

    public void paySalary() {
        for (Employee e : listOfEmployees) {
            if (e.isHired) {
                budget -= e.getSalary();
            }
        }
    }

}