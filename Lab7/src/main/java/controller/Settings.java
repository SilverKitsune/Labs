package controller;

public class Settings {

    private int budget;

    private int period;

    private int workingDay;

    public Settings(){
        budget = 4000;
        period = 1000;
        workingDay = 60000;
    }

    public Settings(int b, int p, int wD){
        budget = b;
        period = p;
        workingDay = wD;
    }

    public int getBudget() {
        return budget;
    }

    public int getPeriod() {
        return period;
    }

    public int getWorkingDay() {
        return workingDay;
    }
}
