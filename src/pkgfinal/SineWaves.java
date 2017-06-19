package pkgfinal;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JComponent;
import javax.swing.JFrame;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author simmg9723
 */
public class SineWaves extends JComponent {

    // Height and Width of our game
    static final int WIDTH = 500;
    static final int HEIGHT = 957;
    //Title of the window
    String title = "Sine Waves";
    //player shape
    Rectangle player = new Rectangle(240, 900, 20, 25);
    Rectangle ob1_1 = new Rectangle(1140, 0, 100, 100);     //11
    Rectangle ob1_2 = new Rectangle(1200, 0, 100, 100);     //1
    Rectangle ob1_3 = new Rectangle(1360, 0, 100, 100);     //1
    Rectangle ob2_1 = new Rectangle(1140, 100, 50, 50);     //11
    Rectangle ob2_2 = new Rectangle(1225, 100, 50, 50);     //1
    Rectangle ob2_3 = new Rectangle(1435, 100, 50, 50);     //1
    Rectangle ob3_1 = new Rectangle(1140, 150, 300, 100);   //11
    Rectangle ob3_2 = new Rectangle(1310, 150, 300, 100);   //1
    Rectangle ob4_1 = new Rectangle(1140, 251, 300, 50);    //11
    Rectangle ob4_2 = new Rectangle(1310, 251, 300, 50);    //1
    Rectangle ob5_1 = new Rectangle(1140, 301, 150, 25);    //11
    Rectangle ob5_2 = new Rectangle(1310, 301, 150, 25);    //1
    Rectangle ob6_1 = new Rectangle(940, 326, WIDTH / 2, 50);    //1
    Rectangle ob6_2 = new Rectangle(1310, 326, WIDTH / 2, 50);     //1
    // sets the framerate and delay for our game
    // you just need to select an approproate framerate
    long desiredFPS = 60;
    long desiredTime = (1000) / desiredFPS;
    boolean spacePressed;
    int velocityX = 5;
    int velocityY = 5;
    int score = 0;
    //int counter = 5;
    int distance = 0;
    Font myFont = new Font("Arial", Font.PLAIN, 75);
    Font myFont2 = new Font("Arial", Font.PLAIN, 25);
    // YOUR GAME VARIABLES WOULD GO HERE
    Color PURPLE = new Color(167, 5, 247);
    Color WHITE = new Color(255, 255, 255);
    Color BLACK = new Color(0, 0, 0);
    Robot r;
    int gen = 0;
    boolean death = false;
    boolean done = false;
    // GAME VARIABLES END HERE   

    // Constructor to create the Frame and place the panel in
    // You will learn more about this in Grade 12 :)
    public SineWaves() {
        // creates a windows to show my game
        JFrame frame = new JFrame(title);

        // sets the size of my game
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        // adds the game to the window
        frame.add(this);

        // sets some options and size of the window automatically
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        // shows the window to the user
        frame.setVisible(true);

        // add listeners for keyboard and mouse
        frame.addKeyListener(new Keyboard());
        Mouse m = new Mouse();

        this.addMouseMotionListener(m);
        this.addMouseWheelListener(m);
        this.addMouseListener(m);
    }

    // drawing of the game happens in here
    // we use the Graphics object, g, to perform the drawing
    // NOTE: This is already double buffered!(helps with framerate/speed)
    @Override
    public void paintComponent(Graphics g) {
        // always clear the screen first!
        g.clearRect(0, 0, WIDTH, HEIGHT);

        // GAME DRAWING GOES HERE

        //background
        g.setColor(BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        g.setColor(WHITE);
        g.fillRect(0, 0, 40, HEIGHT);
        g.fillRect(460, 0, 40, HEIGHT);

        //player sprite
        int[] xPoints1 = {player.x - 10, player.x, player.x + 10, player.x};
        int[] xPoints2 = {player.x - 7, player.x, player.x + 7, player.x};
        int[] xPointsob1 = {0, 0, 300};
        int[] xPointsob2 = {500, 500, 200};
        int[] yPoints1 = {player.y + 15, player.y + 10, player.y + 15, player.y - 10};
        int[] yPoints2 = {player.y + 12, player.y + 7, player.y + 12, player.y - 5};
        int[] yPointsob1 = {250, 500, 500};
        int[] yPointsob2 = {500, 250, 250};
        g.setColor(WHITE);
        g.fillPolygon(xPoints1, yPoints1, 4);
        g.setColor(PURPLE);
        g.fillPolygon(xPoints2, yPoints2, 4);

        //obsticles
        g.setColor(WHITE);

        //small box
        g.fillRect(ob2_1.x, ob2_1.y, ob2_1.width, ob2_1.height);
        g.fillRect(ob2_2.x, ob2_2.y, ob2_2.width, ob2_2.height);
        g.fillRect(ob2_3.x, ob2_3.y, ob2_3.width, ob2_3.height);

        //large box(score 50+)
        g.fillRect(ob1_1.x, ob1_1.y, ob1_1.width, ob1_1.height);
        g.fillRect(ob1_2.x, ob1_2.y, ob1_2.width, ob1_2.height);
        g.fillRect(ob1_3.x, ob1_3.y, ob1_3.width, ob1_3.height);

        //small fence
        g.fillRect(ob4_1.x, ob4_1.y, ob4_1.width, ob4_1.height);
        g.fillRect(ob4_2.x, ob4_2.y, ob4_2.width, ob4_2.height);

        //large fence(score 50+)
        g.fillRect(ob3_1.x, ob3_1.y, ob3_1.width, ob3_1.height);
        g.fillRect(ob3_2.x, ob3_2.y, ob3_2.width, ob3_2.height);

        //moving/spinning fence(score 100+, 150+)
        g.fillRect(ob5_1.x, ob5_1.y, ob5_1.width, ob5_1.height);
        g.fillRect(ob5_2.x, ob5_2.y, ob5_2.width, ob5_2.height);

        //closing fence(score 200+)
        g.fillRect(ob6_1.x, ob6_1.y, ob6_1.width, ob6_1.height);
        g.fillRect(ob6_2.x, ob6_2.y, ob6_2.width, ob6_2.height);

        //angled hallway(score 250+)
        //g.fillPolygon(xPointsob1, yPointsob1, 3);
        //g.fillPolygon(xPointsob2, yPointsob2, 3);



        g.setFont(myFont);
        g.setColor(PURPLE);
        g.drawString("" + score, 50, 75);

        if (death == true) {
            g.setColor(BLACK);
            g.fillRect(0, 0, WIDTH, HEIGHT);
            g.setFont(myFont2);
            g.setColor(PURPLE);
            g.drawString("Unfortunately, you have died.", 90, 100);
            g.drawString("Your final score is:", 150, 400);
            g.setFont(myFont);
            g.drawString("" + score, WIDTH / 2 - 19, HEIGHT / 2);
        }

        // GAME DRAWING ENDS HERE
    }

    // This method is used to do any pre-setup you might need to do
    // This is run before the game loop begins!
    public void preSetup() {
        try {
            // Any of your pre setup before the loop starts should go here

            r = new Robot();
        } catch (AWTException ex) {
            Logger.getLogger(SineWaves.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    // The main game loop
    // In here is where all the logic for my game will go
    public void run() {
        // Used to keep track of time used to draw and update the game
        // This is used to limit the framerate later on
        long startTime;
        long deltaTime;

        preSetup();

        // the main game loop section
        // game will end if you set done = false;
        boolean done = false;
        while (!done) {
            // determines when we started so we can keep a framerate
            startTime = System.currentTimeMillis();

            // all your game rules and move is done in here
            // GAME LOGIC STARTS HERE 
            if (distance == 1) {
                int randNum = (int) (Math.random() * (6 - 1 + 1)) + 1;
                System.out.println(randNum);
                if (randNum == 1) {
                    int pos1 = (int) (Math.random() * (3 - 1 + 1)) + 1;
                    if (pos1 == 1 && ob1_1.x > 500) {
                        ob1_1.x = ob1_1.x - 1100;
                        ob1_1.y = -100;
                    } else if (pos1 == 2 && ob1_2.x > 500) {
                        ob1_2.x = ob1_2.x - 1000;
                        ob1_2.y = -100;
                    } else if (pos1 == 3 && ob1_3.x > 500) {
                        ob1_3.x = ob1_3.x - 1000;
                        ob1_3.y = -100;
                    }

                } else if (randNum == 2) {
                    int pos2 = (int) (Math.random() * (3 - 1 + 1)) + 1;
                    if (pos2 == 1 && ob2_1.x > 500) {
                        ob2_1.x = ob2_1.x - 1100;
                        ob2_1.y = -100;
                    } else if (pos2 == 2 && ob2_2.x > 500) {
                        ob2_2.x = ob2_2.x - 1000;
                        ob2_2.y = -100;
                    } else if (pos2 == 3 && ob2_3.x > 500) {
                        ob2_3.x = ob2_3.x - 1000;
                        ob2_3.y = -100;
                    }

                } else if (randNum == 3) {
                    int pos3 = (int) (Math.random() * (2 - 1 + 1)) + 1;
                    if (pos3 == 1 && ob3_1.x > 500) {
                        ob3_1.x = ob3_1.x - 1100;
                        ob3_1.y = -100;
                    } else if (pos3 == 2 && ob3_2.x > 500) {
                        ob3_2.x = ob3_2.x - 1000;
                        ob3_2.y = -100;
                    }

                } else if (randNum == 4) {
                    int pos4 = (int) (Math.random() * (2 - 1 + 1)) + 1;
                    if (pos4 == 1 && ob4_1.x > 500) {
                        ob4_1.x = ob4_1.x - 1100;
                        ob4_1.y = -100;
                    } else if (pos4 == 2 && ob4_2.x > 500) {
                        ob4_2.x = ob4_2.x - 1000;
                        ob4_2.y = -100;
                    }

                } else if (randNum == 5) {
                    int pos5 = (int) (Math.random() * (2 - 1 + 1)) + 1;
                    if (pos5 == 1 && ob5_1.x > 500) {
                        ob5_1.x = ob5_1.x - 1100;
                        ob5_1.y = -100;
                    } else if (pos5 == 2 && ob5_2.x > 500) {
                        ob5_2.x = ob5_2.x - 1000;
                        ob5_2.y = -100;
                    }

                } else if (randNum == 6 && ob6_1.x > 500) {
                    ob6_1.x = ob6_1.x - 1000;
                    ob6_1.y = -100;
                    ob6_2.x = ob6_2.x - 1000;
                    ob6_2.y = -100;
                }
            }
            if (spacePressed && ob1_1.x < 500) {
                ob1_1.y = ob1_1.y + 8;
            }
            if (spacePressed && ob1_2.x < 500) {
                ob1_2.y = ob1_2.y + 8;
            }
            if (spacePressed && ob1_3.x < 500) {
                ob1_3.y = ob1_3.y + 8;
            }
            if (spacePressed && ob2_1.x < 500) {
                ob2_1.y = ob2_1.y + 8;
            }
            if (spacePressed && ob2_1.x < 500) {
                ob2_2.y = ob2_2.y + 8;
            }
            if (spacePressed && ob2_1.x < 500) {
                ob2_3.y = ob2_3.y + 8;
            }
            if (spacePressed && ob3_1.x < 500) {
                ob3_1.y = ob3_1.y + 8;
            }
            if (spacePressed && ob3_2.x < 500) {
                ob3_2.y = ob3_2.y + 8;
            }
            if (spacePressed && ob4_1.x < 500) {
                ob4_1.y = ob4_1.y + 8;
            }
            if (spacePressed && ob4_2.x < 500) {
                ob4_2.y = ob4_2.y + 8;
            }
            if (spacePressed && ob5_1.x < 500) {
                ob5_1.y = ob5_1.y + 8;
            }
            if (spacePressed && ob5_2.x < 500) {
                ob5_2.y = ob5_2.y + 8;
            }
            if (spacePressed && ob6_1.x < 500) {
                ob6_1.y = ob6_1.y + 8;
                ob6_2.y = ob6_2.y + 8;
            }
            if (spacePressed) {
                distance++;
                if (distance == 25) {
                    score++;
                    distance = 0;
                }
            }
            if (done) {
                break;
            }
            if (death) {
                done = true;
            }
            player.x += velocityX;

            if (player.x == WIDTH - 50) {
                velocityX = -velocityX;
            }
            if (player.x == 50) {
                velocityX = 5;
            }
            if (ob1_1.y >= 1200) {
                ob1_1.x = 1140;
                ob1_1.y = 0;
            }
            if (ob1_2.y >= 1200) {
                ob1_2.x = 1200;
                ob1_2.y = 0;
            }
            if (ob1_3.y >= 1200) {
                ob1_3.x = 1360;
                ob1_3.y = 0;
            }
            if (ob2_1.y >= 1200) {
                ob2_1.x = 1140;
                ob2_1.y = 100;
            }
            if (ob2_2.y >= 1200) {
                ob2_2.x = 1225;
                ob2_2.y = 100;
            }
            if (ob2_3.y >= 1200) {
                ob2_3.x = 1435;
                ob2_3.y = 100;
            }
            if (ob3_1.y >= 1200) {
                ob3_1.x = 1140;
                ob3_1.y = 150;
            }
            if (ob3_2.y >= 1200) {
                ob3_2.x = 1310;
                ob3_2.y = 150;
            }
            if (ob4_1.y >= 1200) {
                ob4_1.x = 1140;
                ob4_1.y = 251;
            }
            if (ob4_2.y >= 1200) {
                ob4_2.x = 1310;
                ob4_2.y = 251;
            }
            if (ob5_1.y >= 1200) {
                ob5_1.x = 1140;
                ob5_1.y = 301;
            }
            if (ob5_2.y >= 1200) {
                ob5_2.x = 1310;
                ob5_2.y = 301;
            }
            if (ob6_1.y >= 1200 && ob5_2.y >= 1200) {
                ob6_1.x = 940;
                ob6_1.y = 326;
                ob6_2.x = 1310;
                ob6_2.y = 326;
            }
            collisions();
            // GAME LOGIC ENDS HERE 
            // update the drawing (calls paintComponent)
            repaint();

            // SLOWS DOWN THE GAME BASED ON THE FRAMERATE ABOVE
            // USING SOME SIMPLE MATH
            deltaTime = System.currentTimeMillis() - startTime;
            try {
                if (deltaTime > desiredTime) {
                    //took too much time, don't wait
                    Thread.sleep(1);
                } else {
                    // sleep to make up the extra time
                    Thread.sleep(desiredTime - deltaTime);
                }
            } catch (Exception e) {
            };
        }
    }

    // Used to implement any of the Mouse Actions
    private class Mouse extends MouseAdapter {
        // if a mouse button has been pressed down

        @Override
        public void mousePressed(MouseEvent e) {
        }

        // if a mouse button has been released
        @Override
        public void mouseReleased(MouseEvent e) {
        }

        // if the scroll wheel has been moved
        @Override
        public void mouseWheelMoved(MouseWheelEvent e) {
        }

        // if the mouse has moved positions
        @Override
        public void mouseMoved(MouseEvent e) {
            int mx = e.getX();
            int my = e.getY();
            Color colour = r.getPixelColor(mx, my);

            //System.out.println(colour);
        }
    }

    // Used to implements any of the Keyboard Actions
    private class Keyboard extends KeyAdapter {
        // if a key has been pressed down

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                spacePressed = true;
            }
        }

        // if a key has been released
        @Override
        public void keyReleased(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                spacePressed = false;
            }
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // creates an instance of my game
        SineWaves game = new SineWaves();

        // starts the game loop
        game.run();
    }

    public void collisions() {
        if (player.intersects(ob1_1) || player.intersects(ob1_2) || player.intersects(ob1_3)) {
            death = true;
            done = true;
        }
        if (player.intersects(ob2_1) || player.intersects(ob2_2) || player.intersects(ob2_3)) {
            death = true;
            done = true;
        }
        if (player.intersects(ob3_1) || player.intersects(ob3_2)) {
            death = true;
            done = true;
        }
        if (player.intersects(ob4_1) || player.intersects(ob4_2)) {
            death = true;
            done = true;
        }
        if (player.intersects(ob5_1) || player.intersects(ob5_2)) {
            death = true;
            done = true;
        }
        if (player.intersects(ob6_1) || player.intersects(ob6_2)) {
            death = true;
            done = true;
        }

    }
}