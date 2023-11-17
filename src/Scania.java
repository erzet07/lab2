import java.awt.*;

public class Scania extends Car{

    private double flakVinkel = 0;
    public Scania() {
        super(12,2,"Scania",150, Color.white);
    }

    public void changeFlak(double amount) {
        if (amount >= 0 && amount <= 70 && getCurrentSpeed() == 0) {
            flakVinkel = amount;
        }
        else {
            System.out.println("invalid input");
        }
    }

    @Override
    public void gas(double amount) {
        if (getflakVinkel() == 0) {
            super.gas(amount);
        }

    }

    public double getflakVinkel() {
        return flakVinkel;
    }

    public double speedFactor() {
        return 1;
    }

    public static void main(String[] args) {
        Scania s = new Scania();
        s.startEngine();
        s.gas(1);
        s.gas(1);
        s.move();
        System.out.println(s.getPosition());
    }

}
