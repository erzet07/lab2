import java.awt.*;
import java.util.LinkedList;

public class CarTransporter extends Car{


    public Scania helper = new Scania();

    private final int kapacitet;

    public double speedFactor(){
        return getEnginePower() * 0.1;
    }

    private LinkedList<Car> loadedCars = new LinkedList<>();



    public CarTransporter(int kapacitet) {
        super(10, 2, "Daf", 225, Color.gray);
        this.kapacitet = kapacitet;
        helper.changeFlak(70);

    }



    public void updateLoadedCarsPosition() {

        for (Car loadedCar : loadedCars) {
            loadedCar.setPosition((Point) getPosition().clone());

        }
    }


    @Override
    public void move() {
        super.move();
        updateLoadedCarsPosition();
    }





    public void lowerRamp() {
        if (getCurrentSpeed() == 0) {
            helper.changeFlak(0);
        }
    }


    public void raiseRamp() {
        helper.changeFlak(70);
    }
    public void loadCar(Car bil) {

        if (helper.getflakVinkel() == 0 && loadedCars.size() < kapacitet && bil.getLength() <= 2) {

            double distance = calculateDistance(bil.getPosition(), getPosition());
            if (distance < 2) {
                loadedCars.add(bil);
                System.out.println("Bil lastad på transportör.");
            }
        }
    }

    private double calculateDistance(Point pos1, Point pos2) {
        return Math.sqrt(Math.pow(pos2.getX() - pos1.getX(), 2) + Math.pow(pos2.getY() - pos1.getY(), 2));
    }


    public void unloadCar() {

       if (!getOmRampUppe() && getAntalLastadeBilar() > 0) {


       loadedCars.get(loadedCars.size()-1).setPosition(getNewCarPosition());
       loadedCars.remove(loadedCars.size()-1);
       System.out.println("Bil lastades av från biltransporten.");

       } else {
       System.out.println("Kunde inte lasta på bilen. Kolla status på både ramp och Biltransportens hastighet.");
       }

        }
    private Point getNewCarPosition() {
        Point transporterPosition = getPosition();
        double angle = Math.toRadians(getDirection() + 180);
        double distance = 1;
        double newX = transporterPosition.getX() + distance * Math.cos(angle);
        double newY = transporterPosition.getY() + distance * Math.sin(angle);
        return new Point((int) newX, (int) newY);
    }

    public int getAntalLastadeBilar() {
        return loadedCars.size();
    }


    public Car getSenasteBilen() {
        return loadedCars.get(loadedCars.size()-1);
    }

    public LinkedList<Car> getLoadedCars() {
        return loadedCars;
    }

    @Override
    public void gas(double amount) {
        if (helper.getflakVinkel() == 70)
            super.gas(amount);
    }

    public boolean getOmRampUppe() {
        return helper.getflakVinkel() == 70;
    }

}




