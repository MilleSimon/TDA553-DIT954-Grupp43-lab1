public class VolvoWorkshop extends Workshop {

    public VolvoWorkshop(Position position, Rotation rotation) {
        super(position, rotation);
    }

    @Override
    public boolean load(Car item) {
        if (item instanceof Volvo240) {
            return super.load(item);
        }
        return false;
    }
}
