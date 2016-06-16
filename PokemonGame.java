import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

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
        private ImageIcon image;
        private JButton startButton;
        
        public StartScreen() {
            image = new ImageIcon(this.getClass().getResource("start.png"));
            
            startButton = new JButton(image);
            startButton.setBorder(null);
            add(startButton);
            
            startButton.addActionListener(new ButtonActionListener());
        }
        
        public class ButtonActionListener implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                // The main game panel loads when the start command is given
                loadGame();
            }
        }
    } // end class StartScreen

    
    //---------------------------Game Screen---------------------------
    public class GamePanel extends JPanel {
        // TODO Possibly keep this as a focus disabler / game pauser?
        // Unless there's a better way...
        private boolean enabled;
        
        public GamePanel() {
            // Do game initializations here
            
            this.setFocusable(true);
            addKeyListener(new MyKeyListener());
            
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
            
            // TODO render stuff
            
            requestFocus();
        } // end method paintComponent
        
        protected class MyKeyListener implements KeyListener {
            public void keyPressed(KeyEvent e) {}
            
            public void keyReleased(KeyEvent e) {}
            
            public void keyTyped(KeyEvent e) {}
        }
        
        public class TimerListener implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                if (enabled) {
                    // Do stuff, including calling repaint() somewhere
                }
            }
        }
    } // end class GamePanel
}
