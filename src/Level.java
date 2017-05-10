
/**
 * Level Class
 * ===========
 * Authors: Jay Honnold
 *          Sam Miller
 * 
 */
import java.util.ArrayList;
import java.util.Random;

public class Level {

    private String filename;

    private EasyReader filein;
    private ArrayList<Enemy> enemylevel;
    private Location startLoc;
    private int[][] grid;
    private int currentlevel = 1;
    

    public Level(String f, Location l, int[][] g) {
        filename = f;
        filein = new EasyReader(filename);

        startLoc = l;
        grid = g;
    }
    
    
    /**
     * This generates the new level for all of the enemies.
     * It gets the speed by taking the current level
     * and dividing it into 2. Then if the speed is greater
     * than 4 it slows it down to 4. It gets the health randomly 
     * by dividing 1.5 and then picking a random number from
     * 1 to that number. It generates, 2 + currentlevel * 3
     * enemies.
     * 
     */
    public ArrayList<Enemy> nextLevel() {

        enemylevel = new ArrayList<Enemy>();

        int speed = (int)(currentlevel / 2.5);
        int maxhealth = (int)(currentlevel / 1.5);
        maxhealth = (int)Math.pow(maxhealth, 1.5);

        if (speed <= 0) { 
            speed = 1;
        } else if (speed > 4) {
            speed = 4;
        }

        if (maxhealth <= 0) {
            maxhealth = 1;
        }

        Random rand = new Random();

        for (int i = 0; i < 2 + currentlevel * 3; i++) {
            enemylevel.add(new Enemy(startLoc, rand.nextInt(maxhealth) + 1, rand.nextInt(speed) + 1, grid));
        }


        currentlevel++;
        return enemylevel;

    }
}
