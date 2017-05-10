
/**
 * Tower Class
 * ===========
 * Authors: Jay Honnold
 *          Sam Miller
 * 
 */
import java.util.ArrayList;
import java.awt.Color;

public class Tower {

    public int delay, speedinc, bullets;
    private int speed, range, power, sellprice;
    private Location loc;
    private String type;
    private Color color;

    public Tower(Location l, String t) {
        loc = l;
        type = t;

        setTower();
    }

    /**
     * This just sets all the data for the tower according
     * to the type of the tower that was inputed by
     * the user.
     * 
     */
    public void setTower() {
        if (type.equals("power")) {
            speed = 50;
            range = 100;
            power = 4;
            delay = speed;
            color = Color.BLUE;
            sellprice = 50;
            bullets = 50;
        }
        if (type.equals("speed")) {
            speed = 25;
            range = 50;
            power = 2;
            delay = speed;
            color = Color.RED;
            sellprice = 50;
            bullets = 100;
        }
        if (type.equals("range")) {
            speed = 75;
            range = 200;
            power = 3;
            delay = speed;
            color = Color.GREEN;
            sellprice = 50;
            bullets = 50;
        }
        if (type.equals("normal")) {
            speed = 50;
            range = 100;
            power = 1;
            delay = speed;
            color = Color.WHITE;
            sellprice = 12;
            bullets = 50;
        }
        if (type.equals("special")) {
            speed = 150;
            range = 100;
            power = 0;
            delay = speed;
            color = Color.PINK;
            sellprice = 75;
            bullets = 50;
        }
    }

    public int getSellPrice() {
        return sellprice;
    }

    public Color getColor() {
        return color;
    }

    public int getSpeed() {
        return speed;
    }

    public int getRange() {
        return range;
    }

    public int getPower() {
        return power;
    }

    public Location getLocation() {
        return loc;
    }

    /**
     * A method that uses the phythagorean theorem to check
     * to see if the enemy is within range of the tower.
     *  
     */
    public boolean withInRange(Enemy e) {
        int x = e.getX() - loc.getX() * 20;
        int y = e.getY() - loc.getY() * 20;

        if (Math.sqrt(x * x + y * y) <= range) {
            return true;
        }

        return false;
    }

    /**
     * Returns the angle required for the bullet to travel
     * towards the given location
     * 
     */
    public double getFireAngle(Location loc2) {
        int x = loc2.getX() - loc.getX();
        int y = loc2.getY() - loc.getY();

        return Math.atan2(y, x);
    }

    /**
     * Goes through the arraylist of enemies and then returns
     * the first enemy instance that is within range of the
     * tower.
     *  
     */
    public Enemy getFirstEnemyInRange(ArrayList<Enemy> enemies) {
        for (Enemy e : enemies) {
            if (withInRange(e)) {
                return e;
            }
        }
        return null;
    }
    
    public Enemy getFirstNonFrozenEnemyInRange(ArrayList<Enemy> enemies) {
        for (Enemy e : enemies) {
            if (withInRange(e) && !e.isFrozen) {
                return e;
            }
        }
        return null;
    }

    public void resetBullets() {
	if (color == Color.PINK || color == Color.RED) {
	    bullets = 100;
	} else {
	    bullets = 50;
	}
    }
}
