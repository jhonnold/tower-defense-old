
/**
 * Bullet Class
 * ============
 * Authors: Jay Honnold
 *          Sam Miller
 * 
 */
/**
 * 
 * This class is what all the bullets
 * are made from. It stores the angle at
 * which the bullet is going along with its speed
 * location and power.
 * 
 */
public class Bullet {

    private double angle;
    private double locx, locy;
    private int speed;
    private Map map;
    private int power;
    private Enemy tracked;
    private boolean freeze = false;

    /**
     * The constructor takes in the parameters for
     * the locx, locy, angle, speed, and power.
     * 
     */
    public Bullet(int x, int y, double a, int s, int p, Enemy e) {
        locx = x;
        locy = y;
        angle = a;
        speed = s;
        power = p;
        map = new Map();
        tracked = e;
        if (power == 0) {
            freeze = true;
        }
    }

    public double getX() {
        return locx;
    }

    public double getY() {
        return locy;
    }

    public int getPower() {
        return power;
    }

    public Enemy getEnemy() {
        return tracked;
    }

    /**
     * This method is the method that checks
     * to see if the enemy the bullet is 
     * tracking is actually existent.
     *  
     */
    public boolean stillActive() {
        if (tracked.getHealth() <= 0) {
            return false;
        }
        return true;
    }

    /**
     * This method changes the angle of the
     * bullet because it must track down the 
     * enemy.
     * 
     */
    public void setNewAngle() {

        double x = tracked.getX() + 10 - locx;
        double y = tracked.getY() + 10 - locy;

        angle = Math.atan2(y, x);
    }

    /**
     * This method moves the bullet according
     * to the angle at which it was fired.
     * 
     */
    public void moveBullet() {
        locx += speed * Math.cos(angle);
        locy += speed * Math.sin(angle);
    }

    /**
     * Takes in an enemy and compares with itself
     * to see if it has collided with it.
     * 
     */
    public boolean collision(Enemy e) {
        double x = e.getY();
        double y = e.getX();

        if (e.getX() < locx && e.getX() + 20 > locx && e.getY() < locy && e.getY() + 20 > locy) {
            return true;
        }
        return false;
    }
}
