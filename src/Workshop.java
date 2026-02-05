import java.util.ArrayList;
import java.util.List;

public abstract class Workshop implements PickableLoad<Car>, Positionable {
    final private Load load;
    private Position position;
    private Rotation rotation;

    public Workshop(Position position, Rotation rotation) {
        this.position = position;
        this.rotation = rotation;
        this.load = new Load(20);
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
}
