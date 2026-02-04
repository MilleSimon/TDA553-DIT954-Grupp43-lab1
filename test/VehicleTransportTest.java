import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collections;

public class VehicleTransportTest extends CarTest {

    public VehicleTransportTest() {
        super(new VehicleTransport());
    }

    @Test
    void TestInMotion() {
        VehicleTransport instanceVehicleTransport = new VehicleTransport();
        instanceVehicleTransport.startEngine();
        instanceVehicleTransport.gas(1);

        // Make sure the instance properly initiated
        assertEquals("VehicleTransport", instanceVehicleTransport.getModelName());

        assertFalse(instanceVehicleTransport.isRampOpen()); // Ramp should start closed, VehicleTransport should have getRampStatus method
        instanceVehicleTransport.openRamp(); // Attempt to open whilst in motion, VehicleTransport should have setRampStatus method
        assertFalse(instanceVehicleTransport.isRampOpen()); // Make sure that the ramp could not be opened whilst in motion
        Car item = new Saab95();
        assertTrue(instanceVehicleTransport.getPosition().getX()- item.getPosition().getX() <= 10);
        assertTrue(instanceVehicleTransport.getPosition().getY()- item.getPosition().getY() <= 10);
        assertEquals(0, instanceVehicleTransport.getLoadSize());
        instanceVehicleTransport.load(item);
        assertEquals(0, instanceVehicleTransport.getLoadSize());
    }

    @Test
    void TestRampBaseFunctionality() {
        VehicleTransport instanceVehicleTransport = new VehicleTransport();
        instanceVehicleTransport.stopEngine();
        Car item = new Saab95();

        instanceVehicleTransport.load(item);
        assertEquals(0, instanceVehicleTransport.getLoadSize());
        instanceVehicleTransport.openRamp();
        assertTrue(instanceVehicleTransport.isRampOpen());
        assertTrue(instanceVehicleTransport.getPosition().getX()- item.getPosition().getX() <= 10);
        assertTrue(instanceVehicleTransport.getPosition().getY()- item.getPosition().getY() <= 10);
        instanceVehicleTransport.load(item);
        assertEquals(instanceVehicleTransport.getPosition().getX(), item.getPosition().getX());
        assertEquals(instanceVehicleTransport.getPosition().getY(), item.getPosition().getY());
        assertEquals(1, instanceVehicleTransport.getLoadSize());
        instanceVehicleTransport.unload(1);
        assertTrue(instanceVehicleTransport.getPosition().getX()- item.getPosition().getX() <= 10);
        assertTrue(instanceVehicleTransport.getPosition().getY()- item.getPosition().getY() <= 10);
        assertEquals(0, instanceVehicleTransport.getLoadSize());
        instanceVehicleTransport.closeRamp();
        assertFalse(instanceVehicleTransport.isRampOpen());
    }
    @BeforeEach
    void clearAll() {
        VehicleTransport instanceVehicleTransport = new VehicleTransport();
        for (int i = 0; i < 9999; i++) {
            instanceVehicleTransport.unload(1);
            if (instanceVehicleTransport.getLoadSize() == 0)
                return;
        }
        fail("Tried removing car from load but got stuck in an infinite loop");
    }

    @Test
    void loadRemoveItem() {
        VehicleTransport instanceVehicleTransport = new VehicleTransport();
        instanceVehicleTransport.openRamp();
        Car item = new Saab95();
        boolean isIn = instanceVehicleTransport.load(item);
        if (!isIn) {
            fail("Could not put item in Loadable");
        }

        Car returnItem = instanceVehicleTransport.unload(1)[0];
        assertEquals(item, returnItem);
    }

    @ParameterizedTest
    @ValueSource(ints = { 5, 3, 0, -1, -4, 20 })
    void loadRemoveItems(int offsetSizes) {
        // Load the maximum amount of cars
        VehicleTransport instanceVehicleTransport = new VehicleTransport();
        instanceVehicleTransport.openRamp();
        ArrayList<Car> allItems = new ArrayList<Car>();
        for (int i = 0; i < instanceVehicleTransport.getMaxSize() + offsetSizes; i++) {
            allItems.add(new Saab95());
            System.out.println(allItems.size());
        }

        ArrayList<Car> successfulItems = new ArrayList<>();

        boolean allIn = true;
        for (Car car : allItems) {
            boolean isIn = instanceVehicleTransport.load(car);
            if (isIn) {
                successfulItems.add(car);
            } else if (allIn) {
                allIn = false;
            }
            if (!isIn && offsetSizes <= 0) {
                fail("Expected to successfully put item in loadable but loadable refused");
            }
        }
        if (allIn && offsetSizes > 0) {
            fail("Expected at least one item to be refused by loadable but loadable allowed all items");
        }

        ArrayList<Car> returnItems = new ArrayList<>();
        for (int i = 0; i < allItems.size() - Math.max(offsetSizes, 0); i++) {
            returnItems.add(instanceVehicleTransport.unload(1)[0]);
        }
        Collections.reverse(returnItems);
        if (successfulItems.size() != returnItems.size()) {
            fail("The amount of removed items does not correspond to the amount of successfully loaded items");
        }
        for (int i = 0; i < successfulItems.size(); i++) {
            if (successfulItems.get(i) != returnItems.get(i))
                fail("Expected " + successfulItems.get(i).toString() + " on order " + i + " got " + returnItems.get(i));
        }
    }
}
