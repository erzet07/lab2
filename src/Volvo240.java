import java.awt.*;

public class Volvo240 extends Car {
    public static final double TRIM_FACTOR = 1.25;

    public Volvo240() {
        super(4,"Volvo240", 100, Color.black);
    }

    public double speedFactor() {
        return getEnginePower() * 0.01 * TRIM_FACTOR;
    }


}
