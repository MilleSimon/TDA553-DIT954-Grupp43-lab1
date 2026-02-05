public class Rotation {
    double rotation;

    public Rotation(double rotation) {
        this.rotation = rotation;
    }

    public double getRotation() {
        return rotation;
    }

    public void setRotation(double rotation) {
        double repeats = Math.floor(rotation / (Math.PI*2));
        this.rotation = rotation - repeats*Math.PI*2;
    }

    public Position getDirectionVector() {
        double x = Math.cos(rotation);
        double y = Math.sin(rotation);
        return new Position(x, y);
    }
}
