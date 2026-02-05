public class Position {
    double x;
    double y;

    public Position(double x, double y) {
        setX(x);
        setY(y);
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public Position distanceTo(Position comparison) {
        double distanceX = Math.abs(x - comparison.getX());
        double distanceY = Math.abs(y - comparison.getY());
        return new Position(distanceX, distanceY);
    }

    public Boolean withinRange(Position position, double rangeX, double rangeY) {
        Position distance = distanceTo(position);
        return (distance.getX() <= rangeX && distance.getY() <= rangeY);
    }
}
