/**
 * Location Class
 * ==============
 * Authors: Jay Honnold
 *          Sam Miller
 */

public class Location {

    private int locx, locy;

    public Location(int x, int y) {
        locx = x;
        locy = y;
    }

    public int getX() {
        return locx;
    }

    public int getY() {
        return locy;
    }

    @Override
    public String toString() {
        return "(" + locx + "," + locy + ")";
    }
    
    /**
     * This method just takes in the current direction
     * and then returns the location that is forward of
     * itself in that direction.
     *  
     */
    public Location getLocationForward(int dir) {

        Location loc;

        if (dir == 0) {
            loc = new Location(locx, locy - 1);
        } else if (dir == 90) {
            loc = new Location(locx + 1, locy);
        } else if (dir == 180) {
            loc = new Location(locx, locy + 1);
        } else if (dir == 270) {
            loc = new Location(locx - 1, locy);
        } else {
            loc = null;
        }

        return loc;
    }
    
    /**
     * Checks to see if the row s and the cols are the 
     * same and if they are then it is equal.
     * 
     */
    public boolean equals(Location loc) {
        return loc.getX() == locx && loc.getY() == locy;
    }
}
