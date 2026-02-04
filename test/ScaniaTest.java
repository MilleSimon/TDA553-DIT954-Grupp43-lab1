import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ScaniaTest extends CarTest {

    public ScaniaTest() {
        super(new Scania());
    }

    @Test
    void TestInMotion() {
        Scania instanceScania = new Scania();
        instanceScania.startEngine();
        instanceScania.gas(1);

        // Make sure the instance properly initiated
        assertEquals("Scania", instanceScania.getModelName());

        assertEquals(0, instanceScania.getFlatBedAngle()); // Starting angle should be 0, Scania should have getFlatBedAngle method
        instanceScania.raiseFlatBed(10); // Attempt to tip whilst in motion, Scania should have raiseFlatBed method
        assertFalse(instanceScania.getFlatBedAngle() > 0); // Make sure that the ramp could not be lifted whilst in motion
    }

    @Test
    void TestRampBaseFunctionality() {
        Scania instanceScania = new Scania();
        instanceScania.stopEngine();

        int testAngle = instanceScania.getFlatBedAngle();
        instanceScania.raiseFlatBed(10);
        assertFalse(instanceScania.getFlatBedAngle() <= testAngle);
        testAngle = instanceScania.getFlatBedAngle();
        instanceScania.lowerFlatBed(5);
        assertFalse(instanceScania.getFlatBedAngle() >= testAngle);
    }

    @Test
    void TestRange() {
        Scania instanceScania = new Scania();
        instanceScania.stopEngine();

        int testAngle = instanceScania.getFlatBedAngle();
        int previousTestAngle = instanceScania.getFlatBedAngle();

        while (testAngle != previousTestAngle && previousTestAngle < 70) { // This loop will only exit once the ramp has increased to 70, and then remained at 70 after a subsequent raiseFlatBed call. Test failure exits while-loop for edge cases
            assertFalse(testAngle > 70); // Angle should not be able to go above 70
            assertFalse(testAngle < previousTestAngle); // Angle should not decrease from "raiseFlatBed" method
            previousTestAngle = instanceScania.getFlatBedAngle();
            instanceScania.raiseFlatBed(5);
            testAngle = instanceScania.getFlatBedAngle();
        }

        while (testAngle != previousTestAngle && previousTestAngle != 0) { // This loop will only exit once the ramp has decreased to 0, and then remained at 0 after a subsequent lowerFlatBed call. - II -
            assertFalse(testAngle < 0); // Angle should not be able to go below 0
            assertFalse(testAngle > previousTestAngle); // Angle should not decrease from "raiseFlatBed" method
            previousTestAngle = instanceScania.getFlatBedAngle();
            instanceScania.lowerFlatBed(5);
            testAngle = instanceScania.getFlatBedAngle();
        }

    }
}