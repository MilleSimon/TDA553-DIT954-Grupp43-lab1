import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class CarTest {
    // Ideally there'd be a test class that is a blank utility of the Car class as it is abstract
    Volvo240 InstanceV240 = new Volvo240();
    Saab95 InstanceS95 = new Saab95();
    double referenceSpeedV240;
    double referenceSpeedS95;

    // Increase speed for both test instances, to help with tests that rely on existing speed
    @BeforeEach
    void setUp() {
        InstanceV240.startEngine();
        InstanceS95.startEngine();
        for (int i = 1; i < 5; i++) { // i < 5 is arbitrary here, simply being enough so that neither vehicle hits their speed ceiling after one more full gas nor can hit their speed floor after one break.
            InstanceV240.gas(1);
            InstanceS95.gas(1);
        }
        referenceSpeedS95 = InstanceS95.getCurrentSpeed();
        referenceSpeedV240 = InstanceV240.getCurrentSpeed();
    }

    @AfterEach
    void tearDown() {
        InstanceV240.stopEngine();
        InstanceS95.stopEngine();
    }

    @Test
    void getCurrentSpeed() {
        while (InstanceV240.getCurrentSpeed() > 0 && InstanceS95.getCurrentSpeed() > 0) {
            InstanceV240.brake(1);
            InstanceS95.brake(1);
        }
        assertFalse(InstanceV240.getCurrentSpeed() < 0);
        assertFalse(InstanceS95.getCurrentSpeed() < 0);

        while (InstanceV240.getCurrentSpeed() < InstanceV240.getEnginePower() && InstanceS95.getCurrentSpeed() < InstanceS95.getEnginePower()) {
            InstanceV240.gas(1);
            InstanceS95.gas(1);
        }
        InstanceV240.gas(1);
        InstanceS95.gas(1);
        assertFalse(InstanceV240.getCurrentSpeed() > InstanceV240.getEnginePower());
        assertFalse(InstanceS95.getCurrentSpeed() > InstanceS95.getEnginePower());
    }

    // Test that gas() only accelerates and never accelerates with input above 1
    @ParameterizedTest
    @ValueSource(doubles = {-1, -0.5, -0.25, 0, 0.25, 0.5, 1, 1.25, 1.5, 1.75, 2})
    void gas(double testNum) {
        InstanceS95.gas(testNum);
        InstanceV240.gas(testNum);
        assertFalse(InstanceS95.getCurrentSpeed() > (referenceSpeedS95 + InstanceS95.speedFactor()) || InstanceS95.getCurrentSpeed() < referenceSpeedS95);
        assertFalse(InstanceV240.getCurrentSpeed() > (referenceSpeedV240 + InstanceV240.speedFactor()) || InstanceV240.getCurrentSpeed() < referenceSpeedV240);
    }

    // Test that brake() only decelerates and never decelerates with input above 1
    @ParameterizedTest
    @ValueSource(doubles = {-1, -0.5, -0.25, 0, 0.25, 0.5, 1, 1.25, 1.5, 1.75, 2})
    void brake(double testNum) {
        InstanceS95.brake(testNum);
        InstanceV240.brake(testNum);
        assertFalse(InstanceS95.getCurrentSpeed() < (referenceSpeedS95 - InstanceS95.speedFactor()) || InstanceS95.getCurrentSpeed() > referenceSpeedS95);
        assertFalse(InstanceV240.getCurrentSpeed() < (referenceSpeedV240 - InstanceV240.speedFactor()) || InstanceV240.getCurrentSpeed() > referenceSpeedV240);
    }
}