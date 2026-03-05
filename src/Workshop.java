import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public abstract class Workshop implements PickableLoad<Car>, Positionable {
    final private Load load;
    private Position position;
    private Rotation rotation;
    private BufferedImage image;
  
    public Workshop(Position position, Rotation rotation) {
        this.position = position;
        this.rotation = rotation;
        this.load = new Load(20);
        this.image = fetchImage();
    }

    public boolean load(Car item) {
        return load.load(item);
    }

    public Car[] unload(int amount) {
        return load.unload(amount);
    }

    public boolean findItemInLoad(Car item) {
        return load.findItemInLoad(item);
    }

    public Car[] getCurrentLoad() {
        return load.getCurrentLoad();
    }

    public int getLoadSize() {
        return load.getLoadSize();
    }

    public int getMaxSize() {
        return load.getMaxSize();
    }

    public void setImage(BufferedImage image) { this.image = image; }

    public BufferedImage getImage() { return image; }

    public Car pick(Car desiredCar) {
        return load.getItemInLoad(desiredCar);
    }

    public void updateItemPositions() {
        load.updateItemPositions();
    }

    public Position getPosition() { return position; }

    public Rotation getRotation() { return rotation; }

    public boolean setPosition(Position position) {
        this.position = position;
        return true;
    }

    public boolean setRotation(Rotation rotation) {
        this.rotation = rotation;
        return true;
    }

    public void openDoors() {
        entrance.open();
    }

    public void closeDoors() {
        entrance.close();
    }

    public void updateItemPositions() {
        for (Car item : currentLoad) {
            item.setPosition(getPosition());
        }
    }

    public boolean detection(Car car) {
        Position carPos = car.getPosition();
        BufferedImage carImage = car.getImage();

        // Calculating if the car and workshop overlap on the x-axis. Separating the axis-es for code cleanliness :]
        if (carPos.getX() + carImage.getWidth() > position.getX() && carPos.getX() < position.getX() + carImage.getWidth() ) {
            // Calculating if the car and workshop overlap on the y-axis
            if (carPos.getY() + carImage.getHeight() > position.getY() && carPos.getY() < position.getY() + carImage.getHeight() ) {
                return true;
            }
        }

        return false;
    }

    abstract protected BufferedImage fetchImage();
}
