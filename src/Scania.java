import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Scania extends Car implements FlatbedCar{

    final private DegreeRamp ramp;

    public Scania(){
        super(2, 50, Color.gray, "Scania", 80);
        ramp = new DegreeRamp(70);
        try {
            BufferedImage image = ImageIO.read(Scania.class.getResourceAsStream("pics/Scania.jpg"));
            setImage(image);

        } catch (IOException e){
            e.printStackTrace();
        }
    }

     @Override
     public void startEngine(){
        if (!ramp.isOpen() && getFlatBedAngle() == 0) {
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

    public void dumpFlatBed() {
        if (this.getCurrentSpeed() == 0 ) {
            ramp.open();
        }
    }

    public void levelFlatBed() {
        if (this.getCurrentSpeed() == 0) {
            ramp.close();
        }
    }

    public int getFlatBedMaxAngle() {
        return ramp.getMaxAngle();
    }

    public int getFlatBedAngle() {
        return ramp.getAngle();
    }

    @Override
    protected BufferedImage fetchImage() {
        try {
            return ImageIO.read(Car.class.getResourceAsStream("pics/Scania.jpg"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
