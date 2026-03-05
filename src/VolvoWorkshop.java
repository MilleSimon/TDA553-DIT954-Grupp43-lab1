import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class VolvoWorkshop extends Workshop {

    public VolvoWorkshop(Position position, Rotation rotation) {
        super(position, rotation);
        try {
            BufferedImage image = ImageIO.read(VolvoWorkshop.class.getResourceAsStream("pics/VolvoBrand.jpg"));
            setImage(image);

        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public boolean load(Car item) {
        if (item instanceof Volvo240) {
            return super.load(item);
        }
        return false;
    }
}
