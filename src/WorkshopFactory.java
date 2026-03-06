
/**
 * Available types: Volvo Workshop
 */
public class WorkshopFactory extends PositionableFactory<Workshop> {
    public WorkshopFactory() {
        saveType(VolvoWorkshop.class, "Volvo Workshop");
    }
}
