import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Volvo240Test extends CarTest{

    private final Volvo240 instanceVolvo240 = new Volvo240();

    public Volvo240Test() {
        super(new Volvo240());
    }

    @Test
    void TestVolvo240() {

        // Make sure the instance properly initiated
        assertEquals("Volvo240", instanceVolvo240.getModelName());

        // Test unique trait of Volvo240, trim factor
        assertEquals(1.25, instanceVolvo240.speedFactor());
        instanceVolvo240.startEngine(); // Speed now 0.1
        instanceVolvo240.gas(1); // Increases speed at max step
        assertEquals(1.35, instanceVolvo240.getCurrentSpeed());
    }
}