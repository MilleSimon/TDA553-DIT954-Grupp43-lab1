
/**
 * Available types: Saab95, Volvo240, Scania and VehicleTransport
 */
public class CarFactory extends PositionableFactory<Car> {
    public CarFactory() {
        saveType(Saab95.class, "Saab95");
        saveType(Volvo240.class, "Volvo240");
        saveType(Scania.class, "Scania");
        saveType(VehicleTransport.class, "VehicleTransport");
    }
}
