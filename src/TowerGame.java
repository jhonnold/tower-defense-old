
/**
_____________
|_____  _____|
| |    _________  __     __   ________   __ __
| |   |   ___  | | |    | |  |   ___ |  | |/ /
| |   |  |  |  | | | || | |  |  _____|  |   /
| |   |  |__|  | | |_||_| |  | |______  |  |
|_|   |________| |________|  |_______|  |__|
_________                 ____
|   ____  \               /    \
|  |   |  \   _________  |  /\_\  ________   __ ___   _______   ________
|  |   |  |  |   ___  |  | |__   |   ___ |  | |/   \ |  ____|  |   ___ |
|  |   |  |  |  ______|  | __|   |  _____|  |      | | |       |  _____|
|  |___|  |  | |_______  | |     | |______  |   |  | | |_____  | |______
|________/   |________|  |_|     |_______|  |__|\__| |______|  |_______|

 * TowerGame Class
 * ===============
 * Authors: Jay Honnold
 *          Sam Miller
 * 
 */
import javax.swing.Timer;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.*;
import javax.swing.JPanel;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import java.util.Random;
import java.awt.font.*;
import java.text.*;

public class TowerGame extends JPanel implements ActionListener, KeyListener, MouseListener, MouseMotionListener {

    Timer t = new Timer(10, this);
    private Map m;
    private Location keysLoc;
    private boolean mouseIn = true;
    private int launchEnemy = 100;
    Random rand = new Random();
    private Image grassimg = new ImageIcon("images/thing" + /*(rand.nextInt(9) + 1)*/ "1" + ".jpg").getImage();
    private Image ingame = new ImageIcon("images/bg.png").getImage();
    private Image start = new ImageIcon("images/start.jpg").getImage();
    private Image fin = new ImageIcon("images/fin.jpg").getImage();
    private Image turret1shop = new ImageIcon("images/turret1.png").getImage();
    private Image turret2shop = new ImageIcon("images/turret2.png").getImage();
    private Image turret3shop = new ImageIcon("images/turret3.png").getImage();
    private Image turret4shop = new ImageIcon("images/turret4.png").getImage();
    private Image turret5shop = new ImageIcon("images/turret5.png").getImage();
    private Image turret1 = new ImageIcon("images/turret1ingame.png").getImage();
    private Image turret2 = new ImageIcon("images/turret2ingame.png").getImage();
    private Image turret3 = new ImageIcon("images/turret3ingame.png").getImage();
    private Image turret4 = new ImageIcon("images/turret4ingame.png").getImage();
    private Image turret5 = new ImageIcon("images/turret5ingame.png").getImage();
    private Image background = new ImageIcon("images/background.jpg").getImage();
    private Image startText = new ImageIcon("images/StartButtonUp.png").getImage();
    private Image startGlow = new ImageIcon("images/StartButtonDown.png").getImage();
    private Image quitText = new ImageIcon("images/ExitButtonUp.png").getImage();
    private Image quitGlow = new ImageIcon("images/ExitButtonDown.png").getImage();
    private Image highscores = new ImageIcon("images/highscores.png").getImage();
    private Image highscoresglow = new ImageIcon("images/highscoresglow.png").getImage();
    private Image enemy1 = new ImageIcon("images/enemy1.png").getImage();
    private Image enemy2 = new ImageIcon("images/enemy2.png").getImage();
    private Image enemy3 = new ImageIcon("images/enemy3.png").getImage();
    private Image enemy4 = new ImageIcon("images/enemy4.png").getImage();
    private Image startImage, quitImage, highImage;
    private Image updownroad = new ImageIcon("images/roadupdown.jpg").getImage();
    private Image leftrightroad = new ImageIcon("images/roadleftright.jpg").getImage();
    private Image northeastturn = new ImageIcon("images/northeast.png").getImage();
    private Image northwestturn = new ImageIcon("images/northwest.png").getImage();
    private Image southeastturn = new ImageIcon("images/southeast.png").getImage();
    private Image southwestturn = new ImageIcon("images/southwest.png").getImage();
    private Image intersection = new ImageIcon("images/intersection.jpg").getImage();
    private Image gui = new ImageIcon("images/gui.jpg").getImage();
    private Image towernobullet = new ImageIcon("images/turretnobullets.png").getImage();
    private Image frozenImage = new ImageIcon("images/frozen.png").getImage();
    private int money = 50;
    private boolean levelRunning;
    private boolean buyingGui;
    private boolean mainMenu = true, highscoregui = false;
    private int mousex, mousey;
    private int seltower = -1;
    private int currentTower = -1;
    private int currentMenu = -1;
    private int lives = 10;
    private int level = 0;
    private int highscore = (new EasyReader("data.txt")).readInt();
    private String name;
    private int score = 0;
    private boolean editor;

    public TowerGame() {
        addKeyListener(this);
        addMouseListener(this);
        addMouseMotionListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);

        createGame();

        keysLoc = new Location(0, 0);
        levelRunning = false;
        buyingGui = false;
        editor = false;
        

        t.start();
    }

    /**
     * A short method that creates the map and that
     * justs starts the game overall.
     * 
     */
    public void createGame() {
        m = new Map("map.txt");
    }

    /**
     * This is what draws all the stuff onto the screen
     * and overides the paintComponent method from the
     * extension.
     * 
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        if (!mainMenu) {
            g2.drawImage(ingame, 0, 0, null);

            int[][] trail = m.getTrail();

            /**
             * This short for loop just goes through and draws the 
             * parts of the trail onto the screen where they are
             * supposed to be drawn.
             * 
             */
            for (int i = 0; i < m.getX(); i++) {
                for (int j = 0; j < m.getY(); j++) {
                    if (trail[i][j] == 1) {
                        g2.drawImage(leftrightroad, 20 * i, 20 * j, null);
                    } else if (trail[i][j] == 2) {
                        g2.drawImage(updownroad, i * 20, j * 20, null);
                    } else if (trail[i][j] == 3) {
                        g2.drawImage(southwestturn, i * 20, j * 20, null);
                    } else if (trail[i][j] == 4) {
                        g2.drawImage(northeastturn, i * 20, j * 20, null);
                    } else if (trail[i][j] == 5) {
                        g2.drawImage(northwestturn, i * 20, j * 20, null);
                    } else if (trail[i][j] == 6) {
                        g2.drawImage(southeastturn, i * 20, j * 20, null);
                    } else if (trail[i][j] == 7) {
                        g2.drawImage(intersection, i * 20, j * 20, null);
                    } else if (trail[i][j] == 8) {
                        g2.drawImage(start, i * 20, j * 20, null);
                    } else if (trail[i][j] == 9) {
                        g2.drawImage(fin, i * 20, j * 20, null);
                    }
                }
            }

            /**
             * Goes through and draws all the enemies onto the screen
             * as little orange circles.
             * 
             */
            for (Enemy e : m.getEnemies()) {
                if (e.getSpeed() == 1) {
                    g2.drawImage(enemy1, e.getX(), e.getY(), null);
                } else if (e.getSpeed() == 2) {
                    g2.drawImage(enemy2, e.getX(), e.getY(), null);
                } else if (e.getSpeed() == 3) {
                    g2.drawImage(enemy3, e.getX(), e.getY(), null);
                } else if (e.getSpeed() == 4) {
                    g2.drawImage(enemy4, e.getX(), e.getY(), null);
                }

                if (e.isFrozen) {
                    g2.drawImage(frozenImage, e.getX(), e.getY(), null);
                }

                g2.setColor(Color.RED);

                double healthpart = (double) e.getHealth() / e.maxHealth();
                g2.setColor(Color.GREEN);
                g2.fill(new Rectangle(e.getX(), e.getY() - 4, (int) (20.0 * healthpart), 3));
                g2.setColor(Color.RED);
                g2.fill(new Rectangle(e.getX() + (int) (20.0 * healthpart), e.getY() - 4, 20 - (int) (20.0 * healthpart), 3));
            }

            /**
             * This goes through and draws the towers onto the
             * screen depending on what color they are.
             * 
             */
            for (Tower t : m.getTowers()) {
                if (t.getColor() == Color.BLUE) {
                    g2.drawImage(turret1, t.getLocation().getX() * 20, t.getLocation().getY() * 20, null);
                } else if (t.getColor() == Color.RED) {
                    g2.drawImage(turret2, t.getLocation().getX() * 20, t.getLocation().getY() * 20, null);
                } else if (t.getColor() == Color.GREEN) {
                    g2.drawImage(turret3, t.getLocation().getX() * 20, t.getLocation().getY() * 20, null);
                } else if (t.getColor() == Color.WHITE) {
                    g2.drawImage(turret4, t.getLocation().getX() * 20, t.getLocation().getY() * 20, null);
                } else if (t.getColor() == Color.PINK) {
                    g2.drawImage(turret5, t.getLocation().getX() * 20, t.getLocation().getY() * 20, null);
                }

                if (t.bullets <= 0) {
                    g2.drawImage(towernobullet, t.getLocation().getX() * 20, t.getLocation().getY() * 20, null);
                }


                /**
                 * If the keys location matchs up with the location of
                 * a tower then it will display the data of that specific tower.
                 * 
                 */
                if (keysLoc.equals(t.getLocation())) {
                    if (t.getColor() == Color.BLUE) {
                        g2.setColor(Color.RED);
                        g2.draw(new Ellipse2D.Double(20 * keysLoc.getX() - 90, 20 * keysLoc.getY() - 90, 200, 200));
                    } else if (t.getColor() == Color.RED) {
                        g2.setColor(Color.RED);
                        g2.draw(new Ellipse2D.Double(20 * keysLoc.getX() - 40, 20 * keysLoc.getY() - 40, 100, 100));
                    } else if (t.getColor() == Color.GREEN) {
                        g2.setColor(Color.RED);
                        g2.draw(new Ellipse2D.Double(20 * keysLoc.getX() - 190, 20 * keysLoc.getY() - 190, 400, 400));
                    } else if (t.getColor() == Color.WHITE) {
                        g2.setColor(Color.RED);
                        g2.draw(new Ellipse2D.Double(20 * keysLoc.getX() - 90, 20 * keysLoc.getY() - 90, 200, 200));
                    } else if (t.getColor() == Color.PINK) {
                        g2.setColor(Color.RED);
                        g2.draw(new Ellipse2D.Double(20 * keysLoc.getX() - 190, 20 * keysLoc.getY() - 190, 400, 400));
                    }

                    g2.drawString(getString("Power: " + t.getPower(), 12).getIterator(), keysLoc.getX() * 20 - 10, keysLoc.getY() * 20 + 35);
                    g2.drawString(getString("Speed: " + t.getSpeed(), 12).getIterator(), keysLoc.getX() * 20 - 10, keysLoc.getY() * 20 + 50);
                    g2.drawString(getString("Right Click to Sell Tower", 12).getIterator(), keysLoc.getX() * 20 - 40, keysLoc.getY() * 20 + 65);
                    g2.drawString(getString("Bullets: " + t.bullets, 12).getIterator(), keysLoc.getX() * 20 - 10, keysLoc.getY() * 20 - 20);
                    if (t.bullets <= 0) {
                        g2.drawString(getString("Click on me for full bullets", 12).getIterator(), keysLoc.getX() * 20 - 40, keysLoc.getY() * 20 - 10);
                    }
                }

            }

            /**
             * Goes through the array of bullets and draws
             * them as little red dots on the screen.
             * 
             */
            for (Bullet b : m.getBullets()) {
                g2.setColor(Color.RED);
                g2.fill(new Ellipse2D.Double(b.getX(), b.getY(), 3, 3));
            }

            /**
             * This checks to see if the buyingGUI is not open then
             * it will display the current tower that is being bought
             * and none if none is being bought.
             * 
             */
            if (!buyingGui) {
                if (currentTower == 0) {
                    g2.drawImage(turret1, 20 * keysLoc.getX(), 20 * keysLoc.getY(), null);
                    g2.setColor(Color.RED);
                    g2.draw(new Ellipse2D.Double(20 * keysLoc.getX() - 90, 20 * keysLoc.getY() - 90, 200, 200));
                } else if (currentTower == 1) {
                    g2.drawImage(turret2, 20 * keysLoc.getX(), 20 * keysLoc.getY(), null);
                    g2.setColor(Color.RED);
                    g2.draw(new Ellipse2D.Double(20 * keysLoc.getX() - 40, 20 * keysLoc.getY() - 40, 100, 100));
                } else if (currentTower == 2) {
                    g2.drawImage(turret3, 20 * keysLoc.getX(), 20 * keysLoc.getY(), null);
                    g2.setColor(Color.RED);
                    g2.draw(new Ellipse2D.Double(20 * keysLoc.getX() - 190, 20 * keysLoc.getY() - 190, 400, 400));
                } else if (currentTower == 3) {
                    g2.drawImage(turret4, 20 * keysLoc.getX(), 20 * keysLoc.getY(), null);
                    g2.setColor(Color.RED);
                    g2.draw(new Ellipse2D.Double(20 * keysLoc.getX() - 90, 20 * keysLoc.getY() - 90, 200, 200));
                } else if (currentTower == 4) {
                    g2.drawImage(turret5, 20 * keysLoc.getX(), 20 * keysLoc.getY(), null);
                    g2.setColor(Color.RED);
                    g2.draw(new Ellipse2D.Double(20 * keysLoc.getX() - 90, 20 * keysLoc.getY() - 90, 200, 200));
                }

                g2.setColor(Color.RED);
                g2.draw(new Rectangle2D.Double(20 * keysLoc.getX(), 20 * keysLoc.getY(), 20, 20));

            }

            g2.drawString(getString("Money: " + money, 18).getIterator(), 1, 15);
            g2.drawString(getString("Lives: " + lives, 14).getIterator(), 725, 15);
            g2.drawString(getString("Level: " + level, 14).getIterator(), 725, 35);
            g2.drawString(getString("Score: " + score, 14).getIterator(), 725, 55);
            g2.drawString(getString("Click To Open Shop", 18).getIterator(), 1, 590);

            /**
             * If the buying GUI is open then it will draw all the towers
             * and to the screen and all the data about them before you
             * actually buy them.
             * 
             */
            if (buyingGui) {
                g2.drawImage(gui, 50, 50, null);
                g2.drawImage(turret1shop, 100, 75, null);
                g2.drawImage(turret2shop, 225, 75, null);
                g2.drawImage(turret3shop, 350, 75, null);
                g2.drawImage(turret4shop, 475, 75, null);
                g2.drawImage(turret5shop, 600, 75, null);
                g2.setColor(Color.RED);
                g2.drawString(getString("Price: 100", 16).getIterator(), 100, 70);
                g2.drawString(getString("Power Tower", 12).getIterator(), 100, 175);
                g2.drawString(getString("Power: 4", 12).getIterator(), 110, 200);
                g2.drawString(getString("Range: 100", 12).getIterator(), 103, 225);
                g2.drawString(getString("Speed: 50", 12).getIterator(), 103, 250);
                g2.drawString(getString("Price: 100", 16).getIterator(), 225, 70);
                g2.drawString(getString("Speed Tower", 12).getIterator(), 225, 175);
                g2.drawString(getString("Power: 2", 12).getIterator(), 235, 200);
                g2.drawString(getString("Range: 50", 12).getIterator(), 228, 225);
                g2.drawString(getString("Speed: 25", 12).getIterator(), 228, 250);
                g2.drawString(getString("Price: 100", 16).getIterator(), 350, 70);
                g2.drawString(getString("Range Tower", 12).getIterator(), 360, 175);
                g2.drawString(getString("Power: 3", 12).getIterator(), 370, 200);
                g2.drawString(getString("Range: 200", 12).getIterator(), 363, 225);
                g2.drawString(getString("Speed: 75", 12).getIterator(), 363, 250);
                g2.drawString(getString("Price: 25", 16).getIterator(), 485, 70);
                g2.drawString(getString("Normal Tower", 12).getIterator(), 485, 175);
                g2.drawString(getString("Power: 1", 12).getIterator(), 485, 200);
                g2.drawString(getString("Range: 100", 12).getIterator(), 488, 225);
                g2.drawString(getString("Speed: 50", 12).getIterator(), 488, 250);
                g2.drawString(getString("Price: 150", 16).getIterator(), 610, 70);
                g2.drawString(getString("Freeze Tower", 12).getIterator(), 610, 175);
                g2.drawString(getString("Slows enemies", 12).getIterator(), 610, 200);
                g2.drawString(getString("Range: 100", 12).getIterator(), 613, 225);
                g2.drawString(getString("Speed: 50", 12).getIterator(), 613, 250);
                g2.drawString(getString("Right Click to Exit", 14).getIterator(), 55, 340);
            }

            /**
             * If the mouse is within the area of a tower then draw a red
             * rectangle and make the tower buyable.
             * 
             */
            if (mousex > 100 && mousex < 175 && mousey > 75 && mousey < 150 && buyingGui) {
                g2.setColor(Color.red);
                g2.draw(new Rectangle2D.Double(100, 75, 75, 75));
                seltower = 0;
            } else if (mousex > 225 && mousex < 300 && mousey > 75 && mousey < 150 && buyingGui) {
                g2.setColor(Color.red);
                g2.draw(new Rectangle2D.Double(225, 75, 75, 75));
                seltower = 1;
            } else if (mousex > 350 && mousex < 425 && mousey > 75 && mousey < 150 && buyingGui) {
                g2.setColor(Color.red);
                g2.draw(new Rectangle2D.Double(350, 75, 75, 75));
                seltower = 2;
            } else if (mousex > 475 && mousex < 550 && mousey > 75 && mousey < 150 && buyingGui) {
                g2.setColor(Color.RED);
                g2.draw(new Rectangle2D.Double(475, 75, 75, 75));
                seltower = 3;
            } else if (mousex > 600 && mousex < 675 && mousey > 75 && mousey < 150 && buyingGui) {
                g2.setColor(Color.RED);
                g2.draw(new Rectangle2D.Double(600, 75, 75, 75));
                seltower = 4;
            } else {
                seltower = -1;
            }

            if (!levelRunning) {
                g2.drawString("Press Space For the Next Level", 315, 10);
            }
        } else {

            /**
             * This is the mainMenu part of the code it
             * just changes the images depending on what
             * the mouse is located upon.
             * 
             */
            g2.drawImage(background, 0, 0, null);

            if (mousex > 75 && mousex < 225 && mousey > 542 && mousey < 582) {
                startImage = startGlow;
            } else {
                startImage = startText;
            }

            if (mousex > 633 && mousex < 725 && mousey > 542 && mousey < 582) {
                quitImage = quitGlow;
            } else {
                quitImage = quitText;
            }

            if (!highscoregui) {
                g2.drawImage(startImage, 75, 542, null);
                g2.drawImage(quitImage, 633, 542, null);
            }

            g2.setColor(Color.BLACK);
            g2.drawString(getString("" + highscore, 50).getIterator(), 70, 465);

        }
    }

    /**
     * This is what updtaes the enemies on the screen and moves
     * them to where they should be.
     * 
     */
    public void updateEnemies() {
        ArrayList<Enemy> enemies = m.getEnemies();

        /**
         * First checks to see where it should be moved to and
         * then moves it there or adjusts its direction so it
         * moves the right location.
         */
        for (Enemy e : enemies) {
            Location l = e.getLoc().getLocationForward(e.getDirection());

            if (m.isValid(l) && m.onTrail(l)) {
                e.moveForward();
            } else {
                int dir = e.getDirection();
                if (dir == 0 || dir == 180) {
                    l = e.getLoc().getLocationForward(90);
                    e.setDirection(90);
                } else if (dir == 90 || dir == 270) {
                    l = e.getLoc().getLocationForward(0);
                    e.setDirection(0);
                }

                if (m.isValid(l) && m.onTrail(l)) {
                    e.lockGrid();
                    e.moveForward();
                } else {
                    if (dir == 0 || dir == 180) {
                        l = e.getLoc().getLocationForward(270);
                        e.setDirection(270);
                    } else if (dir == 90 || dir == 270) {
                        l = e.getLoc().getLocationForward(180);
                        e.setDirection(180);
                    }
                    if (m.isValid(l) && m.onTrail(l)) {
                        e.lockGrid();
                        e.moveForward();
                    }
                }
            }

        }

        for (Enemy e : enemies) {
            e.checkFrozen();
        }


        /**
         * This for loop just goes through and
         * if the enemy is in the finish spot
         * removes it and takes your health.
         * 
         */
        for (int i = 0; i < enemies.size(); i++) {
            Enemy e = enemies.get(i);

            if (m.getTrail()[e.getLoc().getX()][e.getLoc().getY()] == 9) {
                m.removeEnemies(i);
                lives--;
            }

        }

        launchEnemy--;

        if (launchEnemy < 0) {
            m.transferEnemy();
            launchEnemy = 30;
        }

        /**
         * Checks to see if the sizes of the enemy arrays
         * are either both zero and if so ends the level.
         * 
         */
        if (m.getWaiting().isEmpty() && m.getEnemies().isEmpty()) {
            levelRunning = false;
            while (m.getBullets().size() > 0) {
                m.removeBullet(0);
            }

        }
    }

    /**
     * This method is what updates all the towers to do what
     * they are supposed to do such as shoot bullets.
     * 
     */
    public void updateTowers() {
        ArrayList<Tower> towers = m.getTowers();

        /**
         * This for loop goes through both enemies and towers to
         * check to see if any towers can shoot at the enemies and
         * if so does and then resets to delay the bullets.
         * 
         */
        for (Tower t : towers) {
            Enemy e;

            if (t.getColor() == Color.PINK) {
                e = t.getFirstNonFrozenEnemyInRange(m.getEnemies());
            } else {
                e = t.getFirstEnemyInRange(m.getEnemies());
            }
            if (e != null && t.speedinc < 0 && t.bullets > 0) {
                Bullet b = new Bullet(t.getLocation().getX() * 20 + 10, t.getLocation().getY() * 20 + 10, t.getFireAngle(e.getLoc()), 4, t.getPower(), e);
                m.addBullet(b);
                t.speedinc = t.delay;
                t.bullets--;
            } else {
                t.speedinc--;
            }
        }

    }

    /**
     * This method goes through all the bullets and moves them 
     * and destroys them and checks them to make sure that they
     * havent collided with any enemies yet.
     * 
     */
    public void updateBullets() {
        ArrayList<Bullet> bullets = m.getBullets();

        /**
         * A very simple loop that just adjusts the angle of each
         * bullet and moves it.
         * 
         */
        for (Bullet b : bullets) {
            b.setNewAngle();
            b.moveBullet();
        }

        /**
         * This is a loop that checks to make sure if the bullets
         * have collided with any of the enemies and then if they
         * have then it removes some of their health according to 
         * the bullets power.
         * 
         */
        for (int j = 0; j < m.getBullets().size(); j++) {
            Bullet b = m.getBullets().get(j);

            for (int i = 0; i < m.getEnemies().size(); i++) {
                Enemy e = m.getEnemies().get(i);
                if (b.collision(e)) {
                    m.getEnemies().get(i).loseHealth(b.getPower());
                    m.removeBullet(j);
                }
            }
        }

        /**
         * Checks for any dead enemies and removes them if they are
         * and then if they are adds the money we get from it.
         * 
         */
        for (int i = 0; i < m.getEnemies().size(); i++) {
            Enemy e = m.getEnemies().get(i);
            if (m.getEnemies().get(i).getHealth() <= 0) {
                money += m.getEnemies().get(i).money;
                score++;
                m.removeEnemies(i);
            }
        }

        /**
         * A method that removes inactive bullets that dont have any 
         * enemy to be tracking.
         * 
         */
        for (int j = 0; j < m.getBullets().size(); j++) {
            Bullet b = m.getBullets().get(j);
            if (!b.stillActive()) {
                m.removeBullet(j);
            }
        }
    }

    /**
     * This method over rides the actionperformed and then updates
     * all of the stuff on the screen and then redraws.
     * 
     */
    public void actionPerformed(ActionEvent e) {

        if (!buyingGui) {
            updateEnemies();
            updateTowers();
            updateBullets();
        }

        if (lives <= 0) {
            if (score > highscore) {
                EasyWriter fileout = new EasyWriter("data.txt");
                fileout.println(score);
                fileout.close();
                highscore = score;
            }
            mainMenu = true;

        }

        repaint();
    }

    /**
     * This overides the keyPressed from the KeyListener Interface
     * It is used to register the arrow keys and space bar.
     * 
     */
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        Location loc = new Location(-1, -1);

        if (code == KeyEvent.VK_LEFT) {
            loc = new Location(keysLoc.getX() - 1, keysLoc.getY());
        }

        if (code == KeyEvent.VK_RIGHT) {
            loc = new Location(keysLoc.getX() + 1, keysLoc.getY());
        }

        if (code == KeyEvent.VK_UP) {
            loc = new Location(keysLoc.getX(), keysLoc.getY() - 1);
        }

        if (code == KeyEvent.VK_DOWN) {
            loc = new Location(keysLoc.getX(), keysLoc.getY() + 1);
        }

        /**
         * This is the space bar being presssed
         * 
         */
        if (code == KeyEvent.VK_SPACE && !levelRunning && !mainMenu) {
            levelRunning = true;
            m.getNextLevel();
            level++;
        }

        if (code == KeyEvent.VK_ESCAPE) {
            if (score > highscore) {
                EasyWriter fileout = new EasyWriter("data.txt");
                fileout.println(score);
                fileout.close();
                highscore = score;
            }
            resetAll();
            mainMenu = true;
        }

        if (m.isValid(loc)) {
            keysLoc = loc;
        }
    }

    public void keyReleased(KeyEvent e) {
    }

    public void keyTyped(KeyEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
        mouseIn = true;
    }

    /**
     * This method overides the interface again and this time
     * for mouse clicks.
     * 
     */
    public void mouseClicked(MouseEvent e) {
        int code = e.getButton();

        if (!mainMenu) {
            /**
             * These are all the possible mouse clicks that 
             * could occur and what is going to happen 
             * because of each one.
             *
             */
            if (code == MouseEvent.BUTTON1 && !buyingGui && currentTower == -1 && !m.towerLocated(keysLoc)) {
                buyingGui = true;
            } else if (code == MouseEvent.BUTTON1 && buyingGui && seltower != -1 && !m.towerLocated(keysLoc)) {
                buyingGui = false;
                setTower(seltower);
            } else if (code == MouseEvent.BUTTON1 && !buyingGui && currentTower != -1 && !m.towerLocated(keysLoc)) {
                if (!m.onTrail(keysLoc)) {
                    buyTower(currentTower);
                }
            } else if (code == MouseEvent.BUTTON3 && !buyingGui && currentTower != -1) {
                currentTower = -1;
            } else if (code == MouseEvent.BUTTON3 && buyingGui) {
                buyingGui = false;
            } else if (code == MouseEvent.BUTTON3 && m.towerLocated(keysLoc) && !buyingGui) {
                money += m.removeTower(keysLoc).getSellPrice();
            } else if (code == MouseEvent.BUTTON1 && m.towerLocated(keysLoc) && !buyingGui) {
                if (money >= m.getTower(keysLoc).getSellPrice()) {
                    money -= m.getTower(keysLoc).getSellPrice();
                    m.getTower(keysLoc).resetBullets();
                }
            }
        } else {
            if (code == MouseEvent.BUTTON1 && !highscoregui && mousex > 75 && mousex < 225 && mousey > 542 && mousey < 582) {
                resetAll();
                mainMenu = false;
            } else if (code == MouseEvent.BUTTON1 && !highscoregui && mousex > 633 && mousex < 725 && mousey > 542 && mousey < 582) {
                System.exit(0);
            }
        }
    }

    public void mouseExited(MouseEvent e) {
        mouseIn = false;
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseDragged(MouseEvent e) {
    }

    /**
     * This method overides from the interface and just
     * makes it so when you move the mouse it gets the
     * rectangle of where it is supposed to be.
     *  
     */
    public void mouseMoved(MouseEvent e) {
        if (mouseIn) {
            int x = e.getX();
            int y = e.getY();

            mousex = x;
            mousey = y;

            Location loc = m.getGridLocation(x, y);

            if (m.isValid(loc)) {
                keysLoc = loc;
            }
        }
    }

    /**
     * This just sets the tower to what your going to buy
     *  
     */
    public void setTower(int buytower) {
        currentTower = buytower;
        seltower = -1;
    }

    /**
     * Creates a tower according to what tower you had selected
     * and where your keysLoc was at the time of purchase.
     *  
     */
    public void buyTower(int tower) {
        if (tower == 0 && money >= 100) {
            m.addTower(new Tower(keysLoc, "power"));
            currentTower = -1;
            money -= 100;
        }
        if (tower == 1 && money >= 100) {
            m.addTower(new Tower(keysLoc, "speed"));
            currentTower = -1;
            money -= 100;
        }
        if (tower == 2 && money >= 100) {
            m.addTower(new Tower(keysLoc, "range"));
            currentTower = -1;
            money -= 100;
        }
        if (tower == 3 && money >= 25) {
            m.addTower(new Tower(keysLoc, "normal"));
            currentTower = -1;
            money -= 25;
        }
        if (tower == 4 && money >= 150) {
            m.addTower(new Tower(keysLoc, "special"));
            currentTower = -1;
            money -= 150;
        }

    }

    /**
     * This method changes the text in the param to Impact font
     * and then returns the AtributedString of it so it
     * can be displayed on the screen.
     * 
     */
    public AttributedString getString(String text, int size) {
        AttributedString attributedString = new AttributedString(text);
        Font font1 = new Font("Impact", Font.PLAIN, size);
        attributedString.addAttribute(TextAttribute.FONT, font1);
        return attributedString;
    }

    /**
     * This method just resets all the stuff back to 0
     * 
     */
    public void resetAll() {
        m = new Map("map.txt");
        money = 50;
        seltower = -1;
        currentTower = -1;
        currentMenu = -1;
        lives = 10;
        level = 0;
        score = 1;
    }
}