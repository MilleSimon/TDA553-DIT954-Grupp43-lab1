import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Saab95Test extends CarTest{

    private final Saab95 instanceSaab95 = new Saab95();

    public Saab95Test() {
        super(new Saab95());
    }

    @Test
    void TestSaab95() {

        // Make sure the instance properly initiated
        assertEquals("Saab95", instanceSaab95.getModelName());

        // Test unique trait of Saab95, turbo
        instanceSaab95.setTurboOff();
        assertEquals(1.25, instanceSaab95.speedFactor()); // Check default value after changing turbo state
        instanceSaab95.setTurboOn();
        assertEquals(1.25*1.3, instanceSaab95.speedFactor()); // Check turbo value after enabling turbo
    }
}