import java.awt.*;

public class CarTransporter extends Car{

    public double speedFactor() {return 3; }
    private int kapacitet;
    public boolean rampUppe;
    private Car senasteBilen;
    private Car[] lastadeBilar;

    public CarTransporter(int kapacitet) {
        super(2, "Daf", 225, Color.gray);
        this.kapacitet = kapacitet;
        this.rampUppe = true;
        this.lastadeBilar = new Car[kapacitet];
    }


    public void updateLoadedCarsPosition() {
        if (senasteBilen != null) {
            senasteBilen.setPosition((Point) getPosition().clone());
        }
        for (Car loadedCar : lastadeBilar) {
            if (loadedCar != null) {
                loadedCar.setPosition((Point) getPosition().clone());
            }
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
        if (this.lastadeBilar == null) {
            rampUppe = true;
            System.out.println("Ramp höjd");
        } else {
            System.out.println("Kan inte höja ramp om transporten är lastad");
        }
    }
    public void loadCar(Car bil) {
        if (!rampUppe && getAntalLastadeBilar() < kapacitet) {
            double distance = calculateDistance(bil.getPosition(), getPosition());
            if (distance < 2)
                if (bil instanceof CarTransporter) {
                    System.out.println("Det går inte att lasta en annan Biltransportör på denna transport.");
                } else {

                    for (int k = 0; k < kapacitet; k++) {
                        if (lastadeBilar[k] == null) {
                            lastadeBilar[k] = bil;
                            senasteBilen = bil;
                            System.out.println("Bil lastad på transportör.");
                            updateLoadedCarsPosition();
                            break;
                        }
                    }
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
                for (int i = kapacitet - 1; i >= 0; i--) {
                    if (lastadeBilar[i] != null) {
                        senasteBilen = lastadeBilar[i];
                        lastadeBilar[i] = null;
                        Point newCarPosition = getNewCarPosition();
                        senasteBilen.setPosition(newCarPosition);

                        System.out.println("Bil lastades av från biltransporten.");
                        return;
                    }
                }
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
            int räkna = 0;
            for (Car bil : lastadeBilar) {
                if (bil != null) {
                    räkna++;
                }
            }
            return räkna;
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
        return senasteBilen;
    }
}


