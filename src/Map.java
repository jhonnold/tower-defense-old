
/**
 * Map Class
 * =========
 * Authors: Jay Honnold
 *          Sam Miller
 * 
 */
import java.util.ArrayList;
import java.io.*;

public class Map {

    private int sizex, sizey;
    private String file;
    private int[][] trail;
    private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
    private ArrayList<Enemy> waitingenemy = new ArrayList<Enemy>();
    private ArrayList<Tower> towers = new ArrayList<Tower>();
    private ArrayList<Bullet> bullets = new ArrayList<Bullet>();
    private Level l;

    public Map() {
    }

    public Map(String f) {
        file = f;

        readFile(file);

        l = new Level("level.txt", start(), trail);
    }

    /**
     * This is the method that reads the file for the level
     * to be generated. It gets the size of it from the first
     * two numbers in the txt document. Then goes through all the 
     * 0's and 1's to check what is road and not.
     * 
     * Ex: 5 5
     *     1 0 0 0 0
     *     1 1 0 0 0 
     *     0 1 1 0 0
     *     0 0 1 1 0
     *     0 0 0 1 1
     * 
     */
    public void readFile(String f) {
        EasyReader reader = new EasyReader(f);

        sizex = reader.readInt();
        sizey = reader.readInt();

        trail = new int[sizex][sizey];

        for (int i = 0; i < sizey; i++) {
            for (int j = 0; j < sizex; j++) {
                trail[j][i] = reader.readInt();
            }
        }


    }

    public Location start() {
        for (int i = 0; i < getX(); i++) {
            for (int j = 0; j < getY(); j++) {
                if (trail[i][j] == 8) {
                    return new Location(i, j);
                }
            }
        }
        return null;
    }

    /**
     * This method just goes through the map and prints out
     * the 0's and 1's in order of the map.
     * 
     */
    public void printmap() {
        for (int i = 0; i < sizex; i++) {
            for (int j = 0; j < sizey; j++) {
                System.out.print(trail[i][j] + " ");
            }
            System.out.println();
        }
    }

    public int getX() {
        return sizex;
    }

    public int getY() {
        return sizey;
    }

    public int[][] getTrail() {
        return trail;
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public ArrayList<Enemy> getWaiting() {
        return waitingenemy;
    }

    public ArrayList<Tower> getTowers() {
        return towers;
    }

    public ArrayList<Bullet> getBullets() {
        return bullets;
    }

    /**
     * Checks to see if the location given is within the
     * boundaries of the map and then if yes returns true.
     * 
     */
    public boolean isValid(Location loc) {
        if (loc.getX() >= 0 && loc.getX() < sizex && loc.getY() >= 0 && loc.getY() < sizey) {
            return true;
        }
        return false;
    }

    /**
     * Checks to make sure that the location given is on 
     * the trail of the map that is defined in the array
     * trail.
     * 
     */
    public boolean onTrail(Location loc) {
        if (trail[loc.getX()][loc.getY()] != 0) {
            return true;
        }
        return false;
    }

    public void addTower(Tower t) {
        towers.add(t);
    }

    public void addEnemies(Enemy e) {
        enemies.add(e);
    }

    public void addBullet(Bullet b) {
        bullets.add(b);
    }

    public void removeBullet(int index) {
        if (index < bullets.size()) {
            bullets.remove(index);
        }
    }

    public void removeTower(int index) {
        towers.remove(index);
    }

    public void removeEnemies(int index) {
        enemies.remove(index);
    }

    /**
     * Returns the closest grid location according to exact coords
     * 
     */
    public Location getGridLocation(double x, double y) {
        return new Location((int) x / 20, (int) y / 20);
    }

    /**
     * Gets the next level wanted
     * 
     */
    public void getNextLevel() {
        waitingenemy = l.nextLevel();
    }

    /**
     * Transfers the enemy from the waiting array to the 
     * new array that actually puts the enemies onto the
     * screen.
     * 
     */
    public void transferEnemy() {
        if (waitingenemy.size() > 0) {
            enemies.add(waitingenemy.get(0));
            waitingenemy.remove(0);
        }
    }

    /**
     * Returns to see if the inputed location is
     * on the road or not.
     *
     */
    public boolean onRoad(Location loc) {
        if (trail[loc.getX()][loc.getY()] != 0) {
            return false;
        }
        return true;
    }

    public Tower removeTower(Location loc) {
        for (int i = 0; i < towers.size(); i++) {
            if (towers.get(i).getLocation().equals(loc)) {
                return towers.remove(i);
            }
        }
        return null;
    }

    public boolean towerLocated(Location loc) {
        for (int i = 0; i < towers.size(); i++) {
            if (towers.get(i).getLocation().equals(loc)) {
                return true;
            }
        }
        return false;
    }

    public Tower getTower(Location loci) {
        for (int i = 0; i < towers.size(); i++) {
            if (towers.get(i).getLocation().equals(loci)) {
                return towers.get(i);
            }
        }
        return null;
    }
}
