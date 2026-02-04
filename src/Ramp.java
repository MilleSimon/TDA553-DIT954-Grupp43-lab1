/***
 * This object acts as an entrance for any container that needs opening and closing.
 */
public class Ramp {

    private boolean open = false;
    /**
     * Open the ramp. Returns a bool specifying if the ramp has successfully opened, or it is already open.
     * @return true if ramp is opened, false if it is unable to
     */
    public boolean open() {
        open = true;
        return true;
    }

    /**
     * Close the ramp. Returns a bool specifying if the ramp has successfully closed, or it is already closed.
     * @return true if ramp is closed, false if it is unable to
     */
    public boolean close() {
        open = false;
        return true;
    }

    /***
     * Gets the current state of the ramp
     * @return true if the ramp is open, false if the ramp is closed
     */
    public boolean isOpen() {
        return open;
    }
}
