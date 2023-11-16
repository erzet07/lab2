import java.awt.*;
import java.util.LinkedList;

public class CarTransporter extends Car{

    public double speedFactor() {return 3; }
    private final int kapacitet;
    public boolean rampUppe;

    private LinkedList<Car> loadedCars = new LinkedList<>();




    public CarTransporter(int kapacitet) {
        super(2, "Daf", 225, Color.gray);
        this.kapacitet = kapacitet;
        this.rampUppe = true;

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
            rampUppe = false;
        } else {
            System.out.println("Kan inte sänka rampen då transporten rör sig.");
        }
    }
    public void raiseRamp() {
        if (loadedCars.isEmpty()) {
            rampUppe = true;
            System.out.println("Ramp höjd");
        } else {
            System.out.println("Kan inte höja ramp om transporten är lastad");
        }
    }
    public void loadCar(Car bil) {
        if (!rampUppe && loadedCars.size() < kapacitet && !(bil instanceof CarTransporter)) {
            double distance = calculateDistance(bil.getPosition(), getPosition());



            if (loadedCars.size() < kapacitet && distance < 2) {
                loadedCars.add(bil);
                System.out.println("Bil lastad på transportör.");
            }




            }else{
                System.out.println("Kunde inte lasta på bilen. Kolla status på både ramp och Biltransportörens hastighet.");
            }
        System.out.println("Dubbelkolla rampen och överskrid inte kapaciteten");
    }

    private double calculateDistance(Point pos1, Point pos2) {
        return Math.sqrt(Math.pow(pos2.getX() - pos1.getX(), 2) + Math.pow(pos2.getY() - pos1.getY(), 2));
    }


    public void unloadCar() {
            if (!rampUppe && getAntalLastadeBilar() > 0) {

                Point newCarPosition = getNewCarPosition();
                loadedCars.get(loadedCars.size()-1).setPosition(newCarPosition);
                loadedCars.remove(loadedCars.size()-1);
                System.out.println("Bil lastades av från biltransporten.");

            } else {
                System.out.println("Kunde inte lasta på bilen. Kolla status på både ramp och Biltransportens hastighet.");
            }
        }

    private Point getNewCarPosition() {
        Point transporterPosition = getPosition().getLocation();
        double angle = Math.toRadians(getDirection() + 180);
        double distance = 1;
        double newX = transporterPosition.getX() + distance * Math.cos(angle);
        double newY = transporterPosition.getY() + distance * Math.sin(angle);
        Point newCarPosition = new Point((int) newX, (int) newY);
        return newCarPosition;
    }

    public int getAntalLastadeBilar() {
            return loadedCars.size();
        }



        public static void main (String[]args){
            CarTransporter t = new CarTransporter(2);
            Saab95 s = new Saab95();
            Volvo240 v = new Volvo240();
            Volvo240 StulenVolvo = new Volvo240();
            System.out.println(t.getPosition());
            System.out.println(s.getPosition());
            System.out.println(StulenVolvo.getPosition());

        }

    public Car getSenasteBilen() {
        return loadedCars.get(loadedCars.size()-1);
    }
}


