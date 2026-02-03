/**
 * Interface for classes that a maximum set of other specified classes. The class will update the movables position.
 * @param <E> Any class that implements Movable interface
 */
public class Loadable<E extends Movable> {
    /**
     * Load a new item into the loadable, will refuse if it is full.
     * @param item the item being put in the load.
     * @return returns true if the item successfully fits in the load.
     */
    public boolean load(E item) {
        // TODO
        return false;
    }

    /**
     * Remove a new item into the loadable, will return the removed item.
     * @return The removed item.
     */
    public E remove() {
        // TODO
        return null;
    }

    /**
     * Get the amount of items in the load.
     * @return number of items in the load represented as an integer.
     */
    public int getCurrentLoad() {
        // TODO
        return -1;
    }

    /**
     * Get the maximum amount of items that can be in the load.
     * @return the maximum load represented as an integer.
     */
    public int getMaxSize() {
        // TODO
        return -1;
    }
}
