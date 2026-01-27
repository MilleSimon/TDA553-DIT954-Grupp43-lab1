import java.awt.*;

public abstract class Car implements Movable {

    private final int nrDoors; // Number of doors on the car
    private final double enginePower; // Engine power of the car
    private final String modelName; // The car model name
    private Color color; // Color of the car
    private double currentSpeed; // The current speed of the car
    private double[] position;
    private double rotation;

    public Car(int nrDoors, double enginePower, Color color, String modelName) {
        this.nrDoors = nrDoors;
        this.enginePower = enginePower;
        this.color = color;
        this.modelName = modelName;
        stopEngine();
    }

    public int getNrDoors() {
        return nrDoors;
    }

    public double getEnginePower() {
        return enginePower;
    }

    public double getCurrentSpeed() {
        return currentSpeed;
    }

    public String getModelName() {
        return modelName;
    }

    public Color getColor() {
        return color;
    }

    public double[] getPosition() {
        return position;
    }

    public double getDirection() {
        return rotation;
    }

    public void setColor(Color clr) {
        color = clr;
    }

    public void startEngine() {
        currentSpeed = 0.1;
    }

    public void stopEngine() {
        currentSpeed = 0;
    }

    protected double speedFactor(){
        return enginePower * 0.01;
    }

    // TODO fix this method according to lab pm
    public void gas(double amount) {
        incrementSpeed(amount);
    }

    // TODO fix this method according to lab pm
    public void brake(double amount) {
        decrementSpeed(amount);
    }

    public void move() {

    }

    public void turnLeft() {

    }

    public void turnRight() {

    }

    private void incrementSpeed(double amount){
        currentSpeed = Math.min(getCurrentSpeed() + speedFactor() * amount,enginePower);
    }

    private void decrementSpeed(double amount){
        currentSpeed = Math.max(getCurrentSpeed() - speedFactor() * amount,0);
    }

    private double[] getDirectionVector(double rotation) {
        double x = Math.sin(rotation);
        double y = Math.cos(rotation);
        double[] dir = new double[2];
        dir[0] = x;
        dir[1] = y;
        return dir;
    }
}
