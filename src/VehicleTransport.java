import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class VehicleTransport extends Car implements Loadable{
    final protected Ramp ramp;
    protected Movable[] CurrentLoad = new Movable[0];
    private final int MaxSize;
    public VehicleTransport() {
        super(2, 50, Color.white, "VehicleTransport");
        this.MaxSize = 10;
        ramp = new Ramp();
    }

    @Override
    public boolean load(Movable item) {
        if (getCurrentSpeed() == 0) {
            if (ramp.isRampOpen()) {
                if (getPosition().getX() - item.getPosition().getX() <= 10) {
                    if (getPosition().getY() - item.getPosition().getY() <= 10) {
                        if (item.getModelName() != "VehicleTransport") {
                            if (CurrentLoad.length < MaxSize) {
                                Arrays.asList(CurrentLoad).add(item);
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    @Override
    public Movable unload() {
        if (CurrentLoad.length == 0) {
            return null;
        }
        if (ramp.isRampOpen()) {
            int size = Arrays.asList(CurrentLoad).size();
            Movable item = Arrays.asList(CurrentLoad).getLast();
            Arrays.asList(CurrentLoad).remove(size-1);
            item.getPosition().setX(this.getPosition().getX() - 10);
            item.getPosition().setY(this.getPosition().getY() - 10);
            return item;
        }
        return null;
    }

    @Override
    public boolean findItemInLoad(Movable item) {
        return List.of(CurrentLoad).contains(item);
    }

    @Override
    public Movable[] getCurrentLoad() {
        return CurrentLoad;
    }

    @Override
    public int getLoadSize() {
        return CurrentLoad.length;
    }

    @Override
    public int getMaxSize() {
        return MaxSize;
    }
}
