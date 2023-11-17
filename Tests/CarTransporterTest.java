import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class CarTransporterTest {

    private CarTransporter transporter;
    private Saab95 s;
    private Volvo240 v;
    private Volvo240 StulenVolvo;

    @BeforeEach
    void newVehicles(){
        transporter = new CarTransporter(2);
        s = new Saab95();
        v = new Volvo240();
        StulenVolvo = new Volvo240();

    }
    @Test
    void rampShouldBeLoweredWhenStationary() {
        // Test that the ramp lowers only when the transporter is stationary
        transporter.lowerRamp();
        assertFalse(transporter.getOmRampUppe());
        transporter.gas(1);
        transporter.lowerRamp();
        assertFalse(transporter.getOmRampUppe());
    }
    @Test
    void rampShouldNotLowerWhenMoving(){
        transporter.startEngine();
        transporter.gas(0.5);
        double initialSpeedCarTransporter = transporter.getCurrentSpeed();
        transporter.brake(0.5);
        double expectedSpeedCarTransporter = initialSpeedCarTransporter - (3 * transporter.speedFactor());
        transporter.move();
        transporter.lowerRamp();

       assertTrue(transporter.getOmRampUppe());
    }

    @Test
    void raiseRamp() {
        transporter.lowerRamp();
        transporter.loadCar(s);
        transporter.raiseRamp();
        assertFalse(transporter.getOmRampUppe());
    }
    @Test
    void countingWorks() {
        transporter.lowerRamp();

        transporter.loadCar(s);
        assert(transporter.getLoadedCars().size() == 1);
        transporter.unloadCar();
        assert (transporter.getLoadedCars().isEmpty());
    }
    @Test
    void loadCar() {
        transporter.raiseRamp();
        transporter.loadCar(s);
        transporter.lowerRamp();
        Point motel = new Point(20,20);
        s.setPosition(motel);
        transporter.loadCar(s);
        assertEquals(0,transporter.getAntalLastadeBilar() );

    }

    @Test
    void kapacitetLimitWorks() {
        transporter.lowerRamp();

        Point Origo = new Point(0,0);
        transporter.setPosition(Origo);
        s.setPosition(Origo);
        v.setPosition(Origo);
        StulenVolvo.setPosition(Origo);
        transporter.loadCar(s);
        transporter.loadCar(v);
        transporter.loadCar(StulenVolvo);
        assertEquals(2, transporter.getAntalLastadeBilar());


    }

    @Test
    void unloadCar() {
        transporter.lowerRamp();
        transporter.loadCar(s);
        transporter.getAntalLastadeBilar();
        assertEquals(1, transporter.getAntalLastadeBilar());
        transporter.unloadCar();
        assertEquals(0, transporter.getAntalLastadeBilar());
        assertNotEquals(transporter.getPosition(), s.getPosition());
    }


    @Test
    void getAntalLastadeBilar() {
        transporter.lowerRamp();
        Point Origo = new Point(0,0);
        transporter.setPosition(Origo);
        s.setPosition(Origo);
        v.setPosition(Origo);
        transporter.loadCar(s);
        assertEquals(1,transporter.getAntalLastadeBilar());
        transporter.loadCar(v);
        assertEquals(2,transporter.getAntalLastadeBilar());
    }

}