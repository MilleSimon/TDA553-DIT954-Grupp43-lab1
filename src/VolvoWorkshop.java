import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

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

    @Override
    protected BufferedImage fetchImage() {
        try {
            return ImageIO.read(Car.class.getResourceAsStream("pics/VolvoBrand.jpg"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
