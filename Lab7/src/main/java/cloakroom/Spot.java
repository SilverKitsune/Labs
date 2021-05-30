package cloakroom;

import java.util.concurrent.atomic.AtomicBoolean;

public class Spot {

    public int number;

    public AtomicBoolean isTaken;

    Spot(int num){
        isTaken = new AtomicBoolean(false);
        number = num;
    }

    public void take() {
        System.out.println("Номерок №" + number + " заняли");
        isTaken.set(true);
    }

    public void free() {
        System.out.println("Номерок №" + number + " освободили");
        isTaken.set(false);
    }

    public boolean isFree() {
        return isTaken.get();
    }

}