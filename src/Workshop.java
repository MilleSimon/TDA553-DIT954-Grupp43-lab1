import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public abstract class Workshop implements PickableLoad<Car>, Positionable {
    private Ramp entrance = new Ramp();
    private ArrayList<Car> currentLoad = new ArrayList<>();
    private Position position;
    private Rotation rotation;
    private final int maxSize;
    private BufferedImage image;

    public Workshop(Position position, Rotation rotation) {
        this.position = position;
        this.rotation = rotation;
        this.maxSize = 20;
        this.image = fetchImage();
    }

    public boolean load(Car item) {
        if (entrance.isOpen() && getPosition().withinRange(item.getPosition(), Const.range, Const.range)) {
            currentLoad.add(item);
            return true;
        }
        return false;
    }

    public Car[] unload(int amount) {
        if (currentLoad.isEmpty()) {
            return null;
        }
        if (entrance.isOpen()) {
            List<Car> unloaded = new ArrayList<>();
            for(int i = 0;i < amount;i++) {
                if (currentLoad.isEmpty())
                    break;
                Car item = currentLoad.getLast();
                unloaded.add(item);
                currentLoad.removeLast();
            }
            return unloaded.toArray(new Car[0]);
        }
        return null;
    }

    public boolean findItemInLoad(Car item) {
        return currentLoad.contains(item);
    }

    public Car[] getCurrentLoad() {
        return currentLoad.toArray(new Car[0]);
    }

    public int getLoadSize() {
        return currentLoad.size();
    }

    public int getMaxSize() {
        return maxSize;
    }

    public void setImage(BufferedImage image) { this.image = image; }

    public BufferedImage getImage() { return image; }

    public Car pick(Car desiredCar) {
        for (Car car : currentLoad) {
            if (car == desiredCar) {
                currentLoad.remove(desiredCar);
                return desiredCar;
            }
        }
        return null;
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

    public BufferedImage getImage() {
        return image;
    }

    abstract protected BufferedImage fetchImage();
}
