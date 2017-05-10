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
 
 * TowerDefence Class
 * ==================
 * Authors: Jay Honnold
 *          Sam Miller
 * 
 */

import javax.swing.*;

public class TowerDefence {

    public static void main(String[] args) {
        JFrame f = new JFrame();

        f.setSize(800, 600);
        f.setLocation(50, 50);
        f.setUndecorated(true);
        f.setTitle("Tower Defence");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setIconImage(new ImageIcon("icon.png").getImage());
        TowerGame tg = new TowerGame();

        f.add(tg);
        f.setVisible(true);
    }

}
