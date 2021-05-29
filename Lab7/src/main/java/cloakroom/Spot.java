package cloakroom;

public class Spot {

    public int number;

    public boolean isTaken;

    Spot(int num){
        number = num;
        isTaken = false;
    }

    public void take() {
        System.out.println("Номерок №" + number + " заняли");
        isTaken = true;
    }

    public void free() {
        System.out.println("Номерок №" + number + " освободили");
        isTaken = false;
    }

    public boolean isFree() {
        return isTaken;
    }

}