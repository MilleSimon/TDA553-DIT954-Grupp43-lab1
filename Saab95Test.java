import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class Saab95Test extends CarTest{

    public Saab95Test() {
        super(new Saab95());
    }

    @Test
    void TestSaab95() {
        Saab95 TestInstance = new Saab95();

        // Make sure the instance properly initiated
        assertEquals("Saab95", TestInstance.getModelName());

        // Test unique trait of Saab95, turbo
        TestInstance.setTurboOff();
        assertEquals(1.25, TestInstance.speedFactor()); // Check default value after changing turbo state
        TestInstance.setTurboOn();
        assertEquals(1.25*1.3, TestInstance.speedFactor()); // Check turbo value after enabling turbo
    }
}