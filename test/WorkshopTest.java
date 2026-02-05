import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public abstract class WorkshopTest {
    private final Workshop testInstance;
   
    public WorkshopTest(Workshop testInstance) {
        this.testInstance = testInstance;
    }

    /**
     * Test if the incorrect car classes get rejected.
     */
    abstract void incorrectCarInput();

    @BeforeEach
    void clearAll() {
        Car[] list = testInstance.getCurrentLoad();
        for (Car car : list) {
            testInstance.unload(1);
        }
        if (testInstance.getLoadSize() == 0)
            return;
        fail("Tried removing car from load but got stuck in an infinite loop");
    }

    @Test
    void loadRemoveCar() {
        Car car = new Saab95();
        boolean isIn = testInstance.load(car);
        if (!isIn) {
            fail("Could not put car in Loadable");
        }

        Car[] list = testInstance.getCurrentLoad();
        Car returnCar = testInstance.unload(1)[0];
        assertEquals(car, returnCar);
    }

    @ParameterizedTest
    @ValueSource(ints = { 5, 3, 0, -1, -4, 20 })
    void loadRemoveCars(int offsetSizes) {
        // Load the maximum amount of cars
        ArrayList<Car> allCars = new ArrayList<>();
        for (int i = 0; i < testInstance.getMaxSize() + offsetSizes; i++) {
            allCars.add(new Saab95());
        }

        ArrayList<Car> successfulCars = new ArrayList<>();

        boolean allIn = true;
        for (Car car : allCars) {
            boolean isIn = testInstance.load(car);
            successfulCars.add(car);
            if (!isIn) {
                allIn = false;
            }
            if (!isIn && offsetSizes <= 0) {
                fail("Expected to successfully put car in loadable but loadable refused");
            }
        }
        if (!allIn && offsetSizes > 0) {
            fail("Expected at least one car to be refused by loadable but loadable allowed all cars");
        }

        ArrayList<Car> returnCars = new ArrayList<>();
        for (int i = 0; i < allCars.size(); i++) {
            returnCars.add(testInstance.unload(1)[0]);
        }
        returnCars = (ArrayList<Car>) returnCars.reversed();
        if (successfulCars.size() != returnCars.size()) {
            fail("The amount of removed cars does not correspond to the amount of successfully loaded cars");
        }
        for (int i = 0; i < successfulCars.size(); i++) {
            if (successfulCars.get(i) != returnCars.get(i))
                fail("Expected " + successfulCars.get(i).toString() + " on order " + i + " got " + returnCars.get(i));
        }
    }

    @Test
    void pickingCar() {
        Volvo240 desiredCar1 = new Volvo240();
        ArrayList<Car> allCars1 = new ArrayList<>();
        allCars1.add(new Saab95());
        allCars1.add(desiredCar1);
        allCars1.add(new Saab95());
        loadAndPickPattern(allCars1, desiredCar1, "Could not pick desired car within the middle of the load stack");

        Saab95 desiredCar2 = new Saab95();
        ArrayList<Car> allCars2 = new ArrayList<>();
        allCars2.add(new Volvo240());
        allCars2.add(new Volvo240());
        allCars2.add(desiredCar2);
        loadAndPickPattern(allCars2, desiredCar2, "Could not pick desired car within the end of the load stack");

        Volvo240 desiredCar3 = new Volvo240();
        ArrayList<Car> allCars3 = new ArrayList<>();
        allCars3.add(desiredCar3);
        allCars3.add(new Volvo240());
        allCars3.add(new Saab95());
        loadAndPickPattern(allCars3, desiredCar3, "Could not pick desired car within the start of the load stack");
    }

    private void loadAndPickPattern(ArrayList<Car> allCars, Car desiredCar, String failMessage) {
        for (Car car : allCars) {
            testInstance.load(car);
        }
        Car pick = testInstance.pick(desiredCar);
        if (pick != desiredCar) {
            fail(failMessage);
        }
    }
}
