import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ScaniaTest extends CarTest {
    public ScaniaTest() {
        super(new Scania());
    }

    Scania instanceScania = new Scania();

    @Test
    void TestInMotion() {

        if (instanceScania.getCurrentSpeed() <= 0) { // Car inheritance should already accelerate, but for test independence it is good to do this I'd say, for now at least
            instanceScania.startEngine();
            instanceScania.gas(1);
            System.out.println("Test did not properly accelerate from car");
        }

        // Make sure the instance properly initiated
        assertEquals("Scania", instanceScania.getModelName());

        assertEquals(0, instanceScania.getRampAngle()); // Starting angle should be 0, Scania should have getRampAngle method
        instanceScania.liftRamp(10); // Attempt to tip whilst in motion, Scania should have liftRamp method
        assertFalse(instanceScania.getRampAngle() > 0); // Make sure that the ramp could not be lifted whilst in motion
    }

    @Test
    void TestRampBaseFunctionality() {
        instanceScania.stopEngine();

        int testAngle = instanceScania.getRampAngle();
        instanceScania.liftRamp(10);
        assertFalse(instanceScania.getRampAngle() <= testAngle);
        testAngle = instanceScania.getRampAngle();
        instanceScania.lowerRamp(5);
        assertFalse(instanceScania.getRampAngle() >= testAngle);
    }

    @Test
    void TestRange() {
        instanceScania.stopEngine();

        int testAngle = instanceScania.getRampAngle();
        int previousTestAngle = instanceScania.getRampAngle();

        while (testAngle != previousTestAngle && previousTestAngle < 70) { // This loop will only exit once the ramp has increased to 70, and then remained at 70 after a subsequent liftRamp call. Test failure exits while-loop for edge cases
            assertFalse(testAngle > 70); // Angle should not be able to go above 70
            assertFalse(testAngle < previousTestAngle); // Angle should not decrease from "liftRamp" method
            previousTestAngle = instanceScania.getRampAngle();
            instanceScania.liftRamp(5);
            testAngle = instanceScania.getRampAngle();
        }

        while (testAngle != previousTestAngle && previousTestAngle != 0) { // This loop will only exit once the ramp has decreased to 0, and then remained at 0 after a subsequent lowerRamp call. - II -
            assertFalse(testAngle < 0); // Angle should not be able to go below 0
            assertFalse(testAngle > previousTestAngle); // Angle should not decrease from "liftRamp" method
            previousTestAngle = instanceScania.getRampAngle();
            instanceScania.lowerRamp(5);
            testAngle = instanceScania.getRampAngle();
        }

    }
}