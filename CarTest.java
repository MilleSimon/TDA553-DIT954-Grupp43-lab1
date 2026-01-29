import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class CarTest {
    // Ideally there'd be a test class that is a blank utility of the Car class as it is abstract
    int testInstanceIteration = 0;
    double testInstanceReferenceSpeed;
    Car testInstance;

    // Increase speed for both test instances, to help with tests that rely on existing speed
    @BeforeEach
    void setUp() {
        testInstance.startEngine();
        for (int i = 1; i < 5; i++) { // i < 5 is arbitrary here, simply being enough so that neither vehicle hits their speed ceiling after one more full gas nor can hit their speed floor after one break.
            testInstance.gas(1);
        }
        testInstanceReferenceSpeed = testInstance.getCurrentSpeed();
    }

    @AfterEach
    void tearDown() {
        testInstance.stopEngine();
    }

    @Test
    void getCurrentSpeed() {
        while (testInstance.getCurrentSpeed() > 0) {
            testInstance.brake(1);
        }
        assertFalse(testInstance.getCurrentSpeed() < 0);

        while (testInstance.getCurrentSpeed() < testInstance.getEnginePower()) {
            testInstance.gas(1);
        }
        testInstance.gas(1);
        assertFalse(testInstance.getCurrentSpeed() > testInstance.getEnginePower());
    }

    // Test that gas() only accelerates and never accelerates with input above 1
    @ParameterizedTest
    @ValueSource(doubles = {-1, -0.5, -0.25, 0, 0.25, 0.5, 1, 1.25, 1.5, 1.75, 2})
    void gas(double testNum) {
        testInstance.gas(testNum);
        assertFalse(testInstance.getCurrentSpeed() > (testInstanceReferenceSpeed + testInstance.speedFactor()) || testInstance.getCurrentSpeed() < testInstanceReferenceSpeed);
    }

    // Test that brake() only decelerates and never decelerates with input above 1
    @ParameterizedTest
    @ValueSource(doubles = {-1, -0.5, -0.25, 0, 0.25, 0.5, 1, 1.25, 1.5, 1.75, 2})
    void brake(double testNum) {
        testInstance.brake(testNum);
        assertFalse(testInstance.getCurrentSpeed() < (testInstanceReferenceSpeed - testInstance.speedFactor()) || testInstance.getCurrentSpeed() > testInstanceReferenceSpeed);
    }
}