public abstract class Workshop implements PickableLoad<Car> {

    public boolean load(Car item) {
        // TODO
        return false;
    }

    public Car[] unload(int amount) {
        // TODO
        return null;
    }

    public boolean findItemInLoad(Car item) {
        // TODO
        return false;
    }

    public Car[] getCurrentLoad() {
        // TODO
        return new Car[0];
    }

    public int getLoadSize() {
        // TODO
        return 0;
    }

    public int getMaxSize() {
        // TODO
        return 0;
    }

    public Car pick(Car item) {
        // TODO
        return null;
    }
}
