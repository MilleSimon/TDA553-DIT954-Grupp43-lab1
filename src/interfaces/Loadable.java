/**
 * Interface for classes that a maximum set of other specified classes. The class will update the movables position.
 * @param <E> Any class that implements Movable interface
 */
public interface Loadable<E extends Positionable> {
    /**
     * Load a new item into the loadable, will refuse if it is full.
     * @param item the item being put in the load.
     * @return returns true if the item was successfully loaded
     */
     boolean load(E item);
    /**
     * Unload an item from the loadable, will return the removed item.
     * @return The unloaded item.
     */
    E[] unload(int amount);
    /**
     * Look for specified item in the loadable.
     * @return true if item found, false if not.
     */
    boolean findItemInLoad(E item);
    /**
     * Get the amount of items in the load.
     * @return array of items in the load.
     */
    E[] getCurrentLoad();
    /**
     * Get the amount of items in the load.
     * @return number of items in the load represented as an integer.
     */
    int getLoadSize();
    /**
     * Get the maximum amount of items that can be in the load.
     * @return the maximum load represented as an integer.
     */
    int getMaxSize();
    void updateItemPositions();
}
