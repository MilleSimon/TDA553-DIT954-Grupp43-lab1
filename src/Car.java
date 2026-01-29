import java.awt.*;

public abstract class Car implements Movable {

    private final int nrDoors; // Number of doors on the car
    private final double enginePower; // Engine power of the car
    private final String modelName; // The car model name
    private final double rotationSpeed; // Rotation angle per turn
    private Color color; // Color of the car
    private double currentSpeed; // The current speed of the car
    private Position position;
    private Rotation rotation;

    public Car(int nrDoors, double enginePower, Color color, String modelName) {
        this.nrDoors = nrDoors;
        this.enginePower = enginePower;
        this.color = color;
        this.modelName = modelName;
        this.position = new Position(0, 0);
        this.rotation = new Rotation(0);
        this.rotationSpeed = Math.toRadians(4);
        stopEngine();
    }

    public int getNrDoors() { return nrDoors; }

    public double getEnginePower() { return enginePower; }

    public double getCurrentSpeed() { return currentSpeed; }

    public String getModelName() {
        return modelName;
    }

    public Color getColor() {
        return color;
    }

    public Position getPosition() {
        return position;
    }

    public Rotation getRotation() { return rotation; }

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

    public void gas(double amount) {
        if (amount < 0 || amount > 1) {
            return;
        }

        incrementSpeed(amount);
    }

    public void brake(double amount) {
        if (amount < 0 || amount > 1) {
            return;
        }

        decrementSpeed(amount);
    }

    public void move() {
        Position dirVec = rotation.getDirectionVector();
        double newX = position.getX() + dirVec.getX() * currentSpeed;
        double newY = position.getY() + dirVec.getY() * currentSpeed;
        position = new Position(newX, newY);
    }

    public void turnLeft() {
        double newRotation = rotation.getRotation() - rotationSpeed;
        rotation = new Rotation(newRotation);
    }

    public void turnRight() {
        double newRotation = rotation.getRotation() + rotationSpeed;
        rotation = new Rotation(newRotation);
    }

    private void incrementSpeed(double amount){
        currentSpeed = Math.min(getCurrentSpeed() + speedFactor() * amount,enginePower);
    }

    private void decrementSpeed(double amount){
        currentSpeed = Math.max(getCurrentSpeed() - speedFactor() * amount,0);
    }


}
