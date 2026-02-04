public class DegreeRamp extends Ramp{
    private int angle;
    private boolean open = false;
    final private int maxAngle;

    public DegreeRamp(int maximumAngle){
        this.maxAngle = maximumAngle;
    }

    /**
     * Open the ramp. In the case of the degree based ramp, this is equivalent to dumping it
     * @return true if ramp is opened, false if it is unable to or already is.
     */
    public boolean open() {
        if (!open) {
            while (getAngle() < maxAngle) {
                lift(2);
            }
            open = true;
            return true;
        }
        return false;
    }

    /**
     * Close the ramp. Returns a bool specifying if the ramp has successfully closed, or it is already closed.
     * @return true if ramp is closed, false if it is unable to or already is.
     */
    public boolean close() {
        if (open) {
            while (getAngle() > 0) {
                lower(2);
            }
            open = false;
            return true;
        }
        return false;
    }

    /**
     * Lift / Open the ramp by degrees. If this results in the angle going above max angle, set it to max angle instead.
     */
    public void lift(int degrees) {
        if (getAngle() + degrees < maxAngle) {
            angle += degrees;
        } else {
            angle = maxAngle;
        }
        updateOpenStatus();
    }

    /**
     * Lower / Close the ramp by degrees. If this results in the angle going below 0 degrees, set it to 0 instead.
     */
    public void lower(int degrees) {
        if (getAngle() - degrees > 0) {
            angle -= degrees;
        } else {
            angle = 0;
        }
        updateOpenStatus();
    }

    /**
     * @return current ramp angle.
     */
    public int getAngle() {
        return angle;
    }

    private void updateOpenStatus() {
        if (angle > 0 && !open) {
            open = true;
        } else if (angle == 0 && open){
            open = false;
        }
    }
}
