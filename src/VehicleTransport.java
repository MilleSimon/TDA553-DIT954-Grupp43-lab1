import java.awt.*;

public class VehicleTransport extends Car implements Loadable<Car>{
    final private Load load;
    public VehicleTransport() {
        super(2, 50, Color.white, "VehicleTransport", 100);
        load = new Load(10);
    }

    public boolean load(Car item) {
        if (getCurrentSpeed() == 0) {
            return load.load(item);
        }
        return false;
    }

    public Car[] unload(int amount) {
        return load.unload(amount);
    }

    public void openRamp() {
        if (this.getCurrentSpeed() == 0 ) {
            load.openRamp();
        }
    }

    public void closeRamp() {
        if (this.getCurrentSpeed()  == 0 ) {
            load.closeRamp();
        }
    }

    public boolean isRampOpen() {
        return load.isRampOpen();
    }

    @Override
    public void startEngine(){
        if (!load.isRampOpen()) {
            super.startEngine();
        }
    }

    @Override
    public void move() {
        Position position;
        Position dirVec = getRotation().getDirectionVector();
        double newX = getPosition().getX() + dirVec.getX() * getCurrentSpeed();
        double newY = getPosition().getY() + dirVec.getY() * getCurrentSpeed();
        position = new Position(newX, newY);
        setPosition(position);
        load.setPosition(position);
        load.updateItemPositions();
    }

    @Override
    public boolean findItemInLoad(Car item) {
        return load.findItemInLoad(item);
    }

    @Override
    public Car[] getCurrentLoad() {
        return load.getCurrentLoad();
    }

    @Override
    public int getLoadSize() {
        return load.getLoadSize();
    }

    @Override
    public int getMaxSize() {
        return load.getMaxSize();
    }

    @Override
    public void updateItemPositions() {
        load.updateItemPositions();
    }
}
