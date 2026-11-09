package presentation;
import domain.Player;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import javax.swing.Timer;
import domain.Ball;
import domain.POOBSoccer;

/**
 * cancha de juego
 * dibuja la cancha y mueve a los jugadores con el teclado
 * @author Jeronimo Avila Castillo
 * @author Laura Juliana Parra Velandia
 */
public class FieldPanel extends JPanel implements ActionListener, KeyListener {
    private static final Color GRASS = new Color(40, 120, 60);
    private static final Color LINES = Color.WHITE;
    private static final Color COLOR_ONE = new Color(230, 190, 40);
    private static final Color COLOR_TWO = new Color(200, 40, 40);
    private static final int GROUND_MARGIN = 40;
    
    private GamePanel game;
    private POOBSoccer soccer;
    private Timer timer;
    private boolean leftOne;
    private boolean rightOne;
    private boolean jumpOne;
    private boolean leftTwo;
    private boolean rightTwo;
    private boolean jumpTwo;
    private boolean kickOne;
    private boolean kickTwo;

    /**
     * CONSTRUCTOR
     * hace la cancha con un fondo verde y prepara el teclado y el reloj
     */
    public FieldPanel(GamePanel game) {
        this.game = game;
        setBackground(GRASS);
        setFocusable(true);
        addKeyListener(this);
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                requestFocusInWindow();
            }
        });
        timer = new Timer(16, this);
    }

    /**
     * recibe los jugadores del partido, los ubica y arranca el reloj
     * @param one es el jugador del lado izquierdo
     * @param two es el jugador del lado derecho
     */
    public void setSoccer(POOBSoccer soccer) {
        this.soccer = soccer;
        releaseKeys();
        timer.start();
        repaint();
    }

    /**
     * para el reloj, se usa cuando se abandona el partido
     */
    public void stop() {
        timer.stop();
        releaseKeys();
    }

    /**
     * deja todas las teclas como no oprimidas
     */
    private void releaseKeys() {
        leftOne = false;
        rightOne = false;
        jumpOne = false;
        leftTwo = false;
        rightTwo = false;
        jumpTwo = false;
        kickOne = false;
        kickTwo = false;
    }

    /**
     * en cada paso del reloj mueve a los jugadores segun las teclas oprimidas
     * @param e es el evento del reloj
     */
    public void actionPerformed(ActionEvent e) {
        if (leftOne) {
            soccer.moveLeft(1);
        }
        if (rightOne) {
            soccer.moveRight(1);
        }
        if (jumpOne) {
            soccer.jump(1);
        }
        if (leftTwo) {
            soccer.moveLeft(2);
        }
        if (rightTwo) {
            soccer.moveRight(2);
        }
        if (jumpTwo) {
            soccer.jump(2);
        }
        if (kickOne) {
            soccer.kick(1);
            kickOne = false;
        }
        if (kickTwo) {
            soccer.kick(2);
            kickTwo = false;
        }
        if (soccer.update()) {
            game.updateScoreboard();
        }
        repaint();
    }

    /**
     * marca la tecla como oprimida
     * @param e es el evento del teclado
     */
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A:
                leftOne = true;
                break;
            case KeyEvent.VK_D:
                rightOne = true;
                break;
            case KeyEvent.VK_W:
                jumpOne = true;
                break;
            case KeyEvent.VK_LEFT:
                leftTwo = true;
                break;
            case KeyEvent.VK_RIGHT:
                rightTwo = true;
                break;
            case KeyEvent.VK_UP:
                jumpTwo = true;
                break;
            case KeyEvent.VK_S:
                kickOne = true;
                break;
            case KeyEvent.VK_DOWN:
                kickTwo = true;
                break;
        }
    }

    /**
     * marca la tecla como soltada
     * @param e es el evento del teclado
     */
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A:
                leftOne = false;
                break;
            case KeyEvent.VK_D:
                rightOne = false;
                break;
            case KeyEvent.VK_W:
                jumpOne = false;
                break;
            case KeyEvent.VK_LEFT:
                leftTwo = false;
                break;
            case KeyEvent.VK_RIGHT:
                rightTwo = false;
                break;
            case KeyEvent.VK_UP:
                jumpTwo = false;
                break;
        }
    }

    /**
     * no se usa
     * @param e es el evento del teclado
     */
    public void keyTyped(KeyEvent e) {
    }

    /**
     * este metodo lo llaman cada vez que necesitan pintar el panel
     * @param g es el pincel con el que se dibuja sobre el panel
     */
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int width = getWidth();
        int ground = getHeight() - GROUND_MARGIN;
        drawLines(g, width, ground);
        drawGoals(g, width, ground);
        if (soccer != null) {
            Player playerOne = soccer.getPlayerOne();
            Player playerTwo = soccer.getPlayerTwo();
            Ball ball = soccer.getBall();
            drawPlayer(g, playerOne.getX(), ground - playerOne.getHeight(), COLOR_ONE);
            drawPlayer(g, playerTwo.getX(), ground - playerTwo.getHeight(), COLOR_TWO);
            if (playerTwo.isKicking()){
                drawFoot(g, playerOne.getX(), ground - playerOne.getHeight(), 1);
            }
            if (playerTwo.isKicking()) {
                drawFoot(g, playerTwo.getX(), ground - playerTwo.getHeight(), -1);
            }
            drawLife(g, playerOne, ground);
            drawLife(g, playerTwo, ground);
            g.setColor(Color.WHITE);
            g.drawString(playerOne.getName(), playerOne.getX() - 30, ground - playerOne.getHeight() - 100);
            g.drawString(playerTwo.getName(), playerTwo.getX() - 30, ground - playerTwo.getHeight() - 100);
            drawBall(g, (int) ball.getX(), ground - (int) ball.getHeight());
        }
        
    }

    /**
     * el encargado de dibujar las lineas del suelo
     * @param g es el pincel
     * @param width es el ancho del panel
     * @param ground es la altura de donde esta el suelo
     */
    private void drawLines(Graphics g, int width, int ground) {
        g.setColor(LINES);
        g.fillRect(0, ground, width, 4);
        g.fillRect(width / 2 - 2, 0, 4, ground);
        g.drawOval(width / 2 - 50, ground / 2 - 50, 100, 100);
    }

    /**
     * se dibujan los arcos
     * @param g es el pincel
     * @param width ancho actual del panel
     * @param ground es la altura del suelo
     */
    private void drawGoals(Graphics g, int width, int ground) {
        int top = ground - POOBSoccer.GOAL_HEIGHT;
        int rightStart = width - POOBSoccer.GOAL_WIDTH - 1;
        g.setColor(LINES);
        g.drawRect(0, top, POOBSoccer.GOAL_WIDTH, POOBSoccer.GOAL_HEIGHT);
        g.drawRect(rightStart, top, POOBSoccer.GOAL_WIDTH, POOBSoccer.GOAL_HEIGHT);
        for (int y = top + 20; y < ground; y += 20){
            g.drawLine(0, y, POOBSoccer.GOAL_WIDTH, y);
            g.drawLine(rightStart, y, width - 1, y);
        } 
    }
    

    /**
     * dibuja un jugador sobre el campo
     * @param g es el pincel
     * @param x es la posicion horizontal
     * @param ground es la altura donde pisa el jugador
     * @param color es el color de la camiseta del jugador
     */
    private void drawPlayer(Graphics g, int x, int ground, Color color) {
        g.setColor(color);
        g.fillRect(x - 15, ground - 40, 30, 40);
        g.fillOval(x - 25, ground - 90, 50, 50);
        g.setColor(Color.BLACK);
        g.drawOval(x - 25, ground - 90, 50, 50);
    }
    /**
     * dibuja el pie estirado del jugador al patear
     * @param g es el pincel
     * @param x es la posicion horizontal del jugador
     * @param ground es la altura de donde esta pisando el jugador
     * @param direction es 1 si patea a der, y -1 si patea a la izq
     */
    private void drawFoot(Graphics g, int x, int ground, int direction) {
        g.setColor(Color.BLACK);
        if (direction > 0) {
            g.fillRect(x + 15, ground - 20, 30, 10);
        }
    }
    /**
     * dibuja la barra de vida de los jugadores
     * @param g es el pincel
     * @param player es el jugador que se le va a dibujar la vida
     * @param ground es la altura del suelo
     */
    private void drawLife (Graphics g, Player player, int ground) {
        int x = player.getX() - 25;
        int y = ground - player.getHeight() - 125;
        int full = 50 * player.getLife() / player.getMaxLife();
        g.setColor(Color.BLACK);
        g.fillRect(x, y, 50, 6);
        g.setColor(Color.RED);
        g.fillRect(x, y, full, 6);
    }
    /**
     * es el que dibuja el balon
     * @param g es el pincel
     * @param x es la posicion horizontal del centro del balon
     * @param y es la posicion vertical del centro del balon
     */
    private void drawBall(Graphics g, int x, int y) {
        g.setColor(Color.WHITE);
        g.fillOval(x - 12, y - 12, 24, 24);
        g.setColor(Color.BLACK);
        g.drawOval(x - 12, y - 12, 24, 24);
    }

    /**
     * retorna la altura de la cancha desde el suelo al techo
     */
    public int getFieldHeight(){
        return getHeight() - GROUND_MARGIN;
    }

    /**
     * Sigue el partido despues de una pausa 
     */
    public void resume(){
        timer.start();
        requestFocusInWindow();
    }
}