import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Saab95 extends Car implements TurboCar{

    private boolean turboOn;
    
    public Saab95(){
        super(2, 125, Color.red, "Saab95", 25);
	    turboOn = false;
        try {
            BufferedImage image = ImageIO.read(Saab95.class.getResourceAsStream("pics/Saab95.jpg"));
            setImage(image);

        } catch (IOException e){
            e.printStackTrace();
        }
    }
    
    public void setTurboOn(){
	    turboOn = true;
    }

    public void setTurboOff(){
	    turboOn = false;
    }

    @Override
    protected double speedFactor(){
        double turbo = 1;
        if(turboOn) turbo = 1.3;
        return getEnginePower() * 0.01 * turbo;
    }
}
