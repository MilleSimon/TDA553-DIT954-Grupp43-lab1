import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class VehicleTransport extends Car implements Loadable<Car>{
    final private Ramp ramp;
    protected List<Car> CurrentLoad = new ArrayList<>();
    private final int MaxSize;
    public VehicleTransport() {
        super(2, 50, Color.white, "VehicleTransport");
        this.MaxSize = 10;
        ramp = new Ramp();
    }

    public boolean load(Car item) {
        if (getCurrentSpeed() == 0 && ramp.isOpen()) {
            if (getPosition().withinRange(item.getPosition(), 10, 10)) {
                if (!Objects.equals(item.getModelName(), "VehicleTransport")) {
                    if (CurrentLoad.size() < MaxSize) {
                        System.out.println("Competing at: " + CurrentLoad.size() + ":" + MaxSize);
                        CurrentLoad.add(item);
                        return true;
                    }
                }
            }
        }
        System.out.println("Failed successfully for size: " + CurrentLoad.size() + ":" + MaxSize);
        return false;
    }

    public Car[] unload(int amount) {
        if (CurrentLoad.isEmpty()) {
            return null;
        }
        if (ramp.isOpen()) {
            List<Car> unloaded = new ArrayList<>();
            for(int i = 0;i < amount;i++) {
                Car item = CurrentLoad.getLast();
                unloaded.add(item);
                CurrentLoad.removeLast();
                item.getPosition().setX(this.getPosition().getX() - 10);
                item.getPosition().setY(this.getPosition().getY() - 10);
            }
            return unloaded.toArray(new Car[0]);
        }
        return null;
    }

    public void openRamp() {
        if (this.getCurrentSpeed() == 0 ) {
            ramp.open();
        }
    }

    public void closeRamp() {
        if (this.getCurrentSpeed()  == 0 ) {
            ramp.close();
        }
    }

    public boolean isRampOpen() {
        return ramp.isOpen();
    }

    @Override
    public void startEngine(){
        if (!ramp.isOpen()) {
            super.startEngine();
        }
    }

    @Override
    public boolean findItemInLoad(Car item) {
        return CurrentLoad.contains(item);
    }

    @Override
    public Car[] getCurrentLoad() {
        return CurrentLoad.toArray(new Car[0]);
    }

    @Override
    public int getLoadSize() {
        return CurrentLoad.size();
    }

    @Override
    public int getMaxSize() {
        return MaxSize;
    }
}
