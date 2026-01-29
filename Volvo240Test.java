import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Volvo240Test extends CarTest{

    public Volvo240Test() {
        super(new Volvo240());
    }

    @Test
    void TestVolvo240() {
        Volvo240 InstanceVolvo240 = new Volvo240();

        // Make sure the instance properly initiated
        assertEquals("Volvo240", InstanceVolvo240.getModelName());

        // Test unique trait of Volvo240, trim factor
        assertEquals(1.25, InstanceVolvo240.speedFactor());
        InstanceVolvo240.startEngine(); // Speed now 0.1
        InstanceVolvo240.gas(1); // Increases speed at max step
        assertEquals(1.35, InstanceVolvo240.getCurrentSpeed());
    }
}