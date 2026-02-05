import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public abstract class CarTest {
    double testInstanceReferenceSpeed;
    private final Car testInstance;

    public CarTest(Car instance){
        this.testInstance = instance;
    }

    // Increase speed for test instance, to help with tests that rely on existing speed
    @BeforeEach
    void setUpInstance() {
        testInstance.startEngine();
        for (int i = 1; i < 5; i++) { // i < 5 is arbitrary here, more than 1 but not enough to hit Engine Power
            testInstance.gas(1);
        }
        testInstanceReferenceSpeed = testInstance.getCurrentSpeed();
    }

    @AfterEach
    void tearDownInstance() {
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