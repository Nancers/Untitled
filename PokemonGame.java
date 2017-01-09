import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;

/**
 * Insert file/class javadoc here
 */
public class PokemonGame extends JFrame {
    public static final int WIDTH = 700;
    public static final int HEIGHT = 700;
    
    // Declaration of primary panels
    // There may only be one primary panel active at a time. Subpanels, such as
    // menus within the game, are subpanels within the primary panel
    // Battles will probably have their own panel
    private StartScreen startScreen;
    private GamePanel gameScreen;
    
    public static void main(String[] args) {
        PokemonGame game = new PokemonGame();
        
        game.setSize(WIDTH, HEIGHT);
        game.setLocationRelativeTo(null);
        game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.setVisible(true);
    }
    
    public PokemonGame() {
        setLayout(new GridLayout(2, 1));
        setTitle("Pokemon");
        // Initialize start screen
        startScreen = new StartScreen();
        add(startScreen);
    }
    
    //---------------------------Transition functions---------------------------
    public void loadGame() {
        gameScreen = new GamePanel();
        
        remove(startScreen);
        add(gameScreen);
        gameScreen.requestFocusInWindow();
        invalidate();
        validate();
    }
    
    //--------------------------------------------------------------------------
    //--------------------------Panel Inner Classes-----------------------------
    //--------------------------------------------------------------------------
    // TODO Perhaps it's better to put each of these in their own file when they
    // get bit enough. Not sure on the pros and cons of inner classes

    //------------------------------Start Screen--------------------------------
    public class StartScreen extends JPanel {
        JLabel dummy = new JLabel("Press enter!");

        public StartScreen() {
            add(dummy);

            this.setFocusable(true);
            addKeyListener(new StartKeyListener());
        }
        
        public class StartKeyListener implements KeyListener {
            public void keyPressed(KeyEvent e) {
                int kc = e.getKeyCode();
                // Load the game when enter is pressed
                if (kc == KeyEvent.VK_ENTER)
                    loadGame();
            }

            public void keyReleased(KeyEvent e) {}
            public void keyTyped(KeyEvent e) {}
        }
    } // end class StartScreen

    
    //-------------------------------Game Screen--------------------------------
    public class GamePanel extends JPanel {
        // TODO Possibly keep this as a focus disabler / game pauser?
        // Unless there's a better way...
        private boolean enabled;
        // TODO eventually move this into some sort of map class
        BufferedImage mapImage;
        Player player;

        // Player movement variables
        private static final int WALK_DELAY = 30;
        int lastKeyPressed;
        int keyCycles;
        

        // Do game initializations here
        public GamePanel() {
            enabled = true;
            this.setFocusable(true);
            addKeyListener(new GameKeyListener());
            
            // Fires to render every 10 ms
            Timer t = new Timer(10, new TimerListener());
            t.start();

            // TODO eventually move this into some sort of map class
            try {
                mapImage = ImageIO.read(new File("pics" + File.separator + "pallet_town.png"));
            }
            catch (IOException e) {}

            player = new Player(50, 50);

            lastKeyPressed = KeyEvent.VK_UNDEFINED;
            keyCycles = 0;
        }
        
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            // For more advanced 2D graphics
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            
            // Render stuff
            AffineTransform mapScale = new AffineTransform();
            mapScale.scale(1.5, 1.5);
            g2.drawImage(mapImage, new AffineTransformOp(mapScale, AffineTransformOp.TYPE_BILINEAR), 0, 0);

            // Draw the player's sprite
            g2.drawImage(player.getSprite(), player.getX(), player.getY(), null);
            
            requestFocus();
        } // end method paintComponent
        
        private void gameScreenTick() {
            // Operations for moving when a key is held
            keyCycles++;
            if ((keyCycles % WALK_DELAY) == 0) {
                switch (lastKeyPressed) {
                    case KeyEvent.VK_LEFT:
                        player.moveLeft();
                        break;

                    case KeyEvent.VK_RIGHT:
                        player.moveRight();
                        break;

                    case KeyEvent.VK_UP:
                        player.moveUp();
                        break;

                    case KeyEvent.VK_DOWN:
                        player.moveDown();
                        break;
                }
            }

            repaint();
        }


        protected class GameKeyListener implements KeyListener {
            public void keyPressed(KeyEvent e) {
                int kc = e.getKeyCode();
                // Handle held keys separately from the OS's implementation
                if (kc == lastKeyPressed)
                    return;

                lastKeyPressed = kc;
                keyCycles = 0;

                switch (kc) {
                    case KeyEvent.VK_LEFT:
                        player.moveLeft();
                        break;

                    case KeyEvent.VK_RIGHT:
                        player.moveRight();
                        break;

                    case KeyEvent.VK_UP:
                        player.moveUp();
                        break;

                    case KeyEvent.VK_DOWN:
                        player.moveDown();
                        break;
                }
            }
            
            public void keyReleased(KeyEvent e) {
                lastKeyPressed = KeyEvent.VK_UNDEFINED;
                keyCycles = 0;
            }
            
            public void keyTyped(KeyEvent e) {}
        }
        
        public class TimerListener implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                if (enabled) {
                    gameScreenTick();
                }
            }
        }
    } // end class GamePanel


    //-------------------------------Game Screen--------------------------------
    public class BattlePanel extends JPanel {
        // TODO Possibly keep this as a focus disabler / game pauser?
        // Unless there's a better way...
        private boolean enabled;
        
        public BattlePanel() {
            enabled = true;
            this.setFocusable(true);
            addKeyListener(new BattleKeyListener());
            
            // Fires to render every 10 ms
            Timer t = new Timer(10, new TimerListener());
            t.start();
        }
        
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            // For more advanced 2D graphics
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            
            // Render stuff
            
            requestFocus();
        } // end method paintComponent
        
        private void battleScreenTick() {
            repaint();
        }


        protected class BattleKeyListener implements KeyListener {
            public void keyPressed(KeyEvent e) {
                int kc = e.getKeyCode();

                switch (kc) {
                }
            }
            
            public void keyReleased(KeyEvent e) {}
            
            public void keyTyped(KeyEvent e) {}
        }
        
        public class TimerListener implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                if (enabled) {
                    battleScreenTick();
                }
            }
        }
    } // end class GamePanel
}
