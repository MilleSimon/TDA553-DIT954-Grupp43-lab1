import java.awt.*;

public class Scania extends Car {


    public Scania(){
        super(2, 50, Color.gray, "Scania");
        ramp = new DegreeRamp();
    }

    public int getRampAngle() {
        return ramp.getAngle();
    }
    public void liftRamp(int angle) {
        if (ramp.getAngle() + angle < 70) {
            ramp.lift(angle);
        } else {
            ramp.lift(70 - ramp.getAngle());
        }
    }
    public void lowerRamp(int angle) {
        if (ramp.getAngle() - angle > 0) {
            ramp.lower(angle);
        } else {
            ramp.lower(ramp.getAngle());
        }
    }
}
