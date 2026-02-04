import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RampTest {
    private final Ramp testInstance;
    private boolean openTracker;

    private static boolean[] patternlessBooleans = new boolean[] {false, true, false, false, true, false, true, false,
            true, false, false, true, true, false, false, true, true, false, false, false, false, false, false, false,
            true, false, false, true, true, false, false, true, true, false, true, true, false, false, true, true, true,
            false, true, false, true, false, false, true, true, true};

    public RampTest(Ramp testInstance) {
        this.testInstance = testInstance;
    }

    @BeforeEach
    void resetRamp() {
        testInstance.close();
        openTracker = false;
    }

    @Test
    void setOpen() {
        boolean success = testInstance.open();
        openTracker = true;
        assertTrue(success);
    }

    @Test
    void setClosed() {
        boolean success = testInstance.close();
        openTracker = false;
        assertTrue(success);
    }

    @Test
    void RepeatedOpeningClosing() {
        boolean allTrue = true;
        for (int i = 0; i < 100; i++) {
            allTrue = allTrue && testInstance.open();
            allTrue = allTrue && testInstance.close();
            // openTracker = false;
        }
        openTracker = false;
        assertTrue(allTrue);
    }

    @Test
    void PatternlessOpeningClosing() {
        boolean allTrue = true;
        for (int i = 0; i < patternlessBooleans.length; i++) {
            allTrue = allTrue && patternlessBooleans[i] ? testInstance.open() : testInstance.close();
            openTracker = patternlessBooleans[i];
        }
        assertTrue(allTrue);
    }

    @AfterEach
    void checkStatus() {
        assertEquals(openTracker, testInstance.isRampOpen());
    }
}
