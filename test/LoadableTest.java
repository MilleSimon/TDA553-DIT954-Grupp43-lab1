// TODO: Revise tests for Loadable implementation. Keep here for now, will be useful!
/*
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class LoadableTest {
    private final Loadable<Car> testInstance;

    public LoadableTest(Loadable<Car> testInstance) {
        this.testInstance = testInstance;
    }

    @BeforeEach
    void clearAll() {
        for (int i = 0; i < 9999; i++) {
            testInstance.remove();
            if (testInstance.getCurrentLoad() == 0)
                return;
        }
        fail("Tried removing car from load but got stuck in an infinite loop");
    }

    @Test
    void loadRemoveItem() {
        Car item = new Saab95();
        boolean isIn = testInstance.load(item);
        if (!isIn) {
            fail("Could not put item in Loadable");
        }

        Car returnItem = testInstance.remove();
        assertEquals(item, returnItem);
    }

    @ParameterizedTest
    @ValueSource(ints = { 5, 3, 0, -1, -4, 20 })
    void loadRemoveItems(int offsetSizes) {
        // Load the maximum amount of cars
        ArrayList<Car> allItems = new ArrayList<>();
        for (int i = 0; i < testInstance.getMaxSize() + offsetSizes; i++) {
            allItems.add(new Saab95());
        }

        ArrayList<Car> successfulItems = new ArrayList<>();

        boolean allIn = true;
        for (Car car : allItems) {
            boolean isIn = testInstance.load(car);
            successfulItems.add(car);
            if (!isIn) {
                allIn = false;
            }
            if (!isIn && offsetSizes <= 0) {
                fail("Expected to successfully put item in loadable but loadable refused");
            }
        }
        if (!allIn && offsetSizes > 0) {
            fail("Expected at least one item to be refused by loadable but loadable allowed all items");
        }

        ArrayList<Car> returnitems = new ArrayList<>();
        for (int i = 0; i < allItems.size(); i++) {
            returnitems.add(testInstance.remove());
        }
        returnitems = (ArrayList<Car>) returnitems.reversed();
        if (successfulItems.size() != returnitems.size()) {
            fail("The amount of removed items does not correspond to the amount of successfully loaded items");
        }
        for (int i = 0; i < successfulItems.size(); i++) {
            if (successfulItems.get(i) != returnitems.get(i))
                fail("Expected " + successfulItems.get(i).toString() + " on order " + i + " got " + returnitems.get(i));
        }
    }
}
*/