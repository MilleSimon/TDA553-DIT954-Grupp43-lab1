public interface PickableLoad<E extends Positionable> extends Loadable<E> {
    /**
     * Picks an item specified from the loadable and removes it.
     * @param item the specific item that will be picked
     * @return the item that was removed
     */
    public E pick(E item);
}
