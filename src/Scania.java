import java.awt.*;

public class Scania extends Car {

    final private DegreeRamp ramp;

    public Scania(){
        super(2, 50, Color.gray, "Scania");
        ramp = new DegreeRamp(70);
    }

     @Override
     public void startEngine(){
        if (!ramp.isOpen) {
            super.startEngine();
        }
     }

    public void raiseFlatBed(int angle) {
        if (this.getCurrentSpeed() == 0 ) {
            ramp.lift(angle);
        }
    }

    public void lowerFlatBed(int angle) {
        if (this.getCurrentSpeed()  == 0 ) {
            ramp.lower(angle);
        }
    }

    public int getFlatBedAngle() {
        return ramp.getAngle();
    }
}
