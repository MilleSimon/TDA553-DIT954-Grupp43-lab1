import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

abstract class PositionableFactory<T extends Positionable> {
    private final Map<Class<? extends T>, String> types = new HashMap<>();

    public String[] availableTypes() {
        Collection<String> strings = types.values();
        return strings.toArray(new String[types.size()]);
    }

    public Positionable createPositionable(String type) {
        for (Map.Entry<Class<? extends T>, String> carName : types.entrySet()) {
            if (type.equals(carName.getValue())) {
                try {
                    return carName.getKey().getConstructor().newInstance();
                }
                catch (Exception e) {
                    return null;
                }
            }
        }
        return null;
    }

    protected final void saveType(Class<? extends T> type, String name) {
        types.put(type, name);
    }
}
