import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.awt.Color;

public class GenericCarTests {

    @BeforeAll
    static void initAll() {
    }

    @BeforeEach
    void init() {
    }

    @Test
    void TestCarClass() {
        // TODO
        fail();
    }

    @Test
    void TestVolvo240() {
        Volvo240 InstanceVolvo240 = new Volvo240();

        // Test Initialization:
        assertEquals(4, InstanceVolvo240.getNrDoors());
        assertEquals(Color.black, InstanceVolvo240.getColor());
        assertEquals(100, InstanceVolvo240.getEnginePower());
        assertEquals("Volvo240", InstanceVolvo240.modelName);

        // Test correct speed & handling


        // TODO
        fail();
    }

    @Test
    void TestSaab95() {
        // TODO
        fail();
    }

    @AfterEach
    void tearDown() {
    }

    @AfterAll
    static void tearDownAll() {
    }
}
