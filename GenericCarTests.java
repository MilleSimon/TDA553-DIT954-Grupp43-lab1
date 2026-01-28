import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.Parameter;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.awt.Color;

// TODO: Tests are okay rn, they work, but they should be reorganized to run more containerized and desynced

public class GenericCarTests {

    @BeforeAll
    static void initAll() {
    }

    @BeforeEach
    void init() {
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

    @Test
    void TestSaab95() {
        Saab95 InstanceSaab95 = new Saab95();

        // Make sure the instance properly initiated
        assertEquals("Saab95", InstanceSaab95.getModelName());

        // Test unique trait of Saab95, turbo
        InstanceSaab95.setTurboOff();
        assertEquals(1.25, InstanceSaab95.speedFactor()); // Check default value after changing turbo state
        InstanceSaab95.setTurboOn();
        assertEquals(1.25*1.3, InstanceSaab95.speedFactor()); // Check turbo value after enabling turbo
    }

    @ParameterizedTest
    @ValueSource(doubles = {-1, -0.5, -0.25, 0, 0.25, 0.5, 1, 1.25, 1.5, 1.75, 2})
    void TestCarClass(double testNum) {
        Volvo240 InstanceVolvo240 = new Volvo240();
        Saab95 TestCar = new Saab95();

        TestCar.startEngine();
        TestCar.setTurboOff();
        TestCar.gas(testNum);
        assertFalse(TestCar.getCurrentSpeed() > 1.35 || TestCar.getCurrentSpeed() < 0.1);

        final double referenceSpeed = TestCar.getCurrentSpeed();
        TestCar.gas(testNum);
        TestCar.brake(testNum);
        assertEquals(TestCar.getCurrentSpeed(),referenceSpeed); // Gas passed test by this point, brake can therefor rely on it

        TestCar.stopEngine();
        TestCar.startEngine();
        for (int i = 0; i <= Math.abs(testNum)*10; i++) {
            TestCar.brake(1);
        }
        assertFalse(TestCar.getCurrentSpeed() < 0);
        TestCar.setTurboOn();
        for (int i = 0; i <= TestCar.getEnginePower(); i++) {
            TestCar.gas(1);
        }
        assertFalse(TestCar.getCurrentSpeed() > TestCar.getEnginePower());


    }

    @AfterEach
    void tearDown() {
    }

    @AfterAll
    static void tearDownAll() {
    }
}
