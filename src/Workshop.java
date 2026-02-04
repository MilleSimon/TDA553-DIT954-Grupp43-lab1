import java.util.ArrayList;
import java.util.List;

public abstract class Workshop implements PickableLoad<Car>, Positionable {
    private Ramp entrance = new Ramp();
    private ArrayList<Car> currentLoad = new ArrayList<>();
    private Position position;
    private Rotation rotation;
    private final int maxSize;

    public Workshop(Position position, Rotation rotation) {
        this.position = position;
        this.rotation = rotation;
        this.maxSize = 20;
    }

    public boolean load(Car item) {
        if (entrance.isOpen()) {
            if (getPosition().getX() - item.getPosition().getX() <= 10) {
                if (getPosition().getY() - item.getPosition().getY() <= 10) {
                    currentLoad.add(item);
                    return true;
                }
            }
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
}
