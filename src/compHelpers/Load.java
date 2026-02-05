import java.util.ArrayList;
import java.util.List;

public class Load implements Loadable<Car>,Positionable{

    public Ramp ramp;
    public ArrayList<Car> currentLoad;
    private final int MaxSize;
    private Position position;
    private Rotation rotation;

    public Load(int maxSize) {
        this.ramp = new Ramp();
        this.currentLoad = new ArrayList<>();
        this.MaxSize = maxSize;
        this.position = new Position(0,0);
        this.rotation = new Rotation(0);
    }

    @Override
    public boolean load(Car item) {
        if (ramp.isOpen() && currentLoad.size() < MaxSize) {
            if (getPosition().withinRange(item.getPosition(), Const.range, Const.range)) {
                if (item.getWeight() < 50) {
                    currentLoad.add(item);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Car[] unload(int amount) {
        if (currentLoad.isEmpty()) {
            return null;
        }
        if (ramp.isOpen()) {
            List<Car> unloaded = new ArrayList<>();
            for(int i = 0;i < amount;i++) {
                Car item = currentLoad.getLast();
                unloaded.add(item);
                currentLoad.removeLast();
                item.getPosition().setX(this.getPosition().getX() - 10);
                item.getPosition().setY(this.getPosition().getY() - 10);
            }
            return unloaded.toArray(new Car[0]);
        }
        return null;
    }

    @Override
    public boolean findItemInLoad(Car item) {
        return currentLoad.contains(item);
    }

    public Car getItemInLoad(Car item) {
        for (Car car : currentLoad) {
            if (car == item) {
                currentLoad.remove(item);
                return item;
            }
        }
        return null;
    }

    @Override
    public Car[] getCurrentLoad() {
        return currentLoad.toArray(new Car[0]);
    }

    @Override
    public int getLoadSize() {
        return currentLoad.size();
    }

    @Override
    public int getMaxSize() {
        return MaxSize;
    }

    @Override
    public void updateItemPositions() {
        for (Car item : currentLoad) {
            item.setPosition(getPosition());
        }
    }

    public void openRamp() {
        ramp.open();
    }

    public void closeRamp() {
        ramp.close();
    }

    public boolean isRampOpen() {
        return ramp.isOpen();
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public boolean setPosition(Position position) {
        this.position = position;
        return true;
    }

    @Override
    public Rotation getRotation() {
        return rotation;
    }

    @Override
    public boolean setRotation(Rotation rotation) {
        this.rotation = rotation;
        return true;
    }
}
