import java.awt.*;

public abstract class Car implements Movable {
    private int nrDoors;
    private double enginePower;
    private double currentSpeed;
    private Color color;
    private String modelName;
    private Point position;
    private double direction;

    public Car(int nrDoors, String modelName, double enginePower, Color color) {
        this.nrDoors = nrDoors;
        this.modelName = modelName;
        this.enginePower = enginePower;
        this.color = color;
        this.position = new Point(0, 0);
        this.direction = 0;
        stopEngine();
    }

    public abstract double speedFactor();

    public void setPosition(Point newPosition) {
        this.position = newPosition;
    }
    public void move() {
        Point initialPosition = getPosition();
        double newX = position.getX() + Math.cos(Math.toRadians(direction)) * getCurrentSpeed();
        double newY = position.getY() + Math.sin(Math.toRadians(direction)) * getCurrentSpeed();
        position.setLocation(newX, newY);

        if (initialPosition.equals(position)) {
            System.out.println("Test failed");
        }
    }

    public int getNrDoors(){
        return nrDoors;
    }

    public String getModelName() {
        return modelName;
    }

    public double getEnginePower() {
        return enginePower;
    }

    public Color getColor() {
        return color;
    }

    public double getCurrentSpeed() {
        return currentSpeed;
    }

    public Point getPosition() {
        return new Point(this.position);
    }

    public double getDirection() {
        return direction;
    }

    public void startEngine() {
        currentSpeed = 0.1;
    }

    public void stopEngine() {
        currentSpeed = 0;
    }

    public void turnLeft() {
        direction = (direction - 90) % 360;
    }

    public void turnRight() {
        direction = (direction + 90) % 360;
    }

    private void incrementSpeed(double amount) {
        currentSpeed = getCurrentSpeed() + speedFactor() * amount;
    }

    private void decrementSpeed(double amount) {
        currentSpeed = getCurrentSpeed() - speedFactor() * amount;
    }

    public void gas(double amount) {
        if (amount >= 0 && amount <= 1 && currentSpeed != 0) {
            incrementSpeed(amount);
            if (currentSpeed > enginePower) {
                currentSpeed = enginePower;
            } else if (currentSpeed < 0) {
                currentSpeed = 0;
            }
        }
    }

    public void brake(double amount){
        if (amount >= 0 && amount <= 1 && currentSpeed - speedFactor()*amount >= 0) {
            decrementSpeed(amount);
        }
    }

}
