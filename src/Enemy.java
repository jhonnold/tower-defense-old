
/**
 * Enemy Class
 * ===========
 * Authors: Jay Honnold
 *          Sam Miller
 * 
 */
/**
 * 
 * This is the enemy class that stores all the 
 * enemies and has its Location on the grid along
 * with the actual location health, speed, and direction
 * 
 */
public class Enemy {

    private Location loc;
    private double locx, locy;
    private double health, speed;
    private int dir;
    private int[][] grid;
    public int money;
    private int maxhealth;
    public boolean isFrozen = false;
    private int frozenTime = 0;
    private double multSpeed = 1;

    public Enemy(Location l, int h, int s, int[][] g) {
        loc = l;
        health = h;
        maxhealth = h;
        speed = s;
        grid = g;
        dir = 90;

        locx = l.getX() * 20;
        locy = l.getY() * 20;

        money = (int)health*(int)+(3*(speed-1));
    }

    public Location getLoc() {
        return loc;
    }

    public int getX() {
        return (int)locx;
    }

    public int getY() {
        return (int)locy;
    }

    public int getHealth() {
        return (int)health;
    }

    public int maxHealth() {
        return maxhealth;
    }

    public int getSpeed() {
        return (int)speed;
    }

    public int getDirection() {
        return dir;
    }

    public void setDirection(int d) {
        dir = d;
    }

    public void loseHealth(int power) {
        health -= power;
        if (power == 0) {
            isFrozen = true;
            multSpeed = .25;
            frozenTime = 1000;
        }
    }

    /**
     * This method moves the enemy forward depending
     * on its direction in which it is facing.
     * 
     */
    public void moveForward() {
        if (dir == 0) {
            locy -= speed * multSpeed;
        } else if (dir == 90) {
            locx += speed * multSpeed;
        } else if (dir == 180) {
            locy += speed * multSpeed;
        } else if (dir == 270) {
            locx -= speed * multSpeed;
        }

        if ((double) locx / 20 - (int) locx / 20 < (double) speed / 20 && (double) locy / 20 - (int) locy / 20 < (double) speed / 20) {
            loc = new Location((int) locx / 20, (int) locy / 20);
        }
        
    }

    /**
     * This method just locks the enemy onto the grid
     * into the exact location point that it is 
     * supposed to be located at.
     * 
     */
    public void lockGrid() {
        locx = loc.getX() * 20;
        locy = loc.getY() * 20;
    }
    
    public void checkFrozen() {
        if (isFrozen && frozenTime <= 0) {
            isFrozen = false;
            multSpeed = 1;
        }
        
        frozenTime--;
    }
}
