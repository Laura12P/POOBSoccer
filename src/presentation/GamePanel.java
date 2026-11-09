package presentation;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import domain.POOBSoccer;
import domain.Player;
/**
 * es la pantalla del juego
 * el tiempo
 * el marcador
 * y el boton de menu
 * @author Jeronimo Avila Castillo
 * @author Laura Juliana Parra Velandia
 */
public class GamePanel extends JPanel implements ActionListener {
    private MainWindow window;
    private JLabel time;
    private JLabel scoreboard;
    private JButton menuButton;
    private FieldPanel field;

    private JLabel infoOne;
    private JLabel infoTwo;
    private POOBSoccer soccer;
    private Timer timer;
    /**
     * CONSTRUCTOR
     * crea la pantalla del partido
     * @param window es la ventana principal
     */
    public GamePanel(MainWindow window) {
        this.window = window;
        prepareElements();
        prepareActions();
    }
    /**
     * es la barra del tiempo
     * con el marcador
     * y el boton de menu
     * y prepara el resto de la cancha
     */
    private void prepareElements() {
        setLayout(new BorderLayout());
        JPanel bar = new JPanel(new BorderLayout());
        bar.setBackground(Color.BLACK);
        bar.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
        time = new JLabel("Tiempo: 1:00");
        time.setFont(new Font("Arial", Font.BOLD, 18));
        time.setForeground(Color.WHITE);
        bar.add(time, BorderLayout.WEST);
        scoreboard = new JLabel("0 - 0", SwingConstants.CENTER);
        scoreboard.setFont(new Font("Arial", Font.BOLD, 28));
        scoreboard.setForeground(Color.WHITE);
        bar.add(scoreboard, BorderLayout.CENTER);
        menuButton = new JButton("Menu");
        menuButton.setFocusable(false);
        bar.add(menuButton, BorderLayout.EAST);
        add(bar, BorderLayout.NORTH);
        field = new FieldPanel(this);
        add(field, BorderLayout.CENTER);
        JPanel info = new JPanel(new BorderLayout());
        info.setBackground(Color.BLACK);
        info.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
        infoOne = new JLabel(" ");
        infoOne.setFont(new Font("Arial", Font.BOLD, 16));
        infoOne.setForeground(Color.WHITE);
        infoTwo = new JLabel(" ");
        infoTwo.setFont(new Font("Arial", Font.BOLD, 16));
        infoTwo.setForeground(Color.WHITE);
        info.add(infoOne, BorderLayout.WEST);
        info.add(infoTwo, BorderLayout.EAST);
        add(info, BorderLayout.SOUTH);
        timer = new Timer(1000, this);
    }

    /**
     * es el que registra el panel cuando se le da click
     * 
     */
    private void prepareActions() {
        menuButton.addActionListener(this);
    }

    /**
     * es menu pregunta si quiere abandonar el partido
     * @param e es el evento del click
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == menuButton) {
            timer.stop();
            field.stop();
            String[] options = {"Continuar", "Modo de juego", "Inicio"};
            int answer = JOptionPane.showOptionDialog(this, "juego en pausa", "Menu",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                    null, options, options[0]);
            if (answer == 1) {
                window.showScreen(MainWindow.MODE);
            } else if (answer == 2) {
                window.showScreen(MainWindow.START);
            } else {
                if (soccer.getSeconds() > 0) {
                    timer.start();
                }
                field.resume();
            }
        } else if (e.getSource() == timer) {
            soccer.passSecond();
            int seconds = soccer.getSeconds();
            if (seconds < 10) {
                time.setText("Tiempo: 0:0" + seconds);
            } else {
                time.setText("Tiempo: 0:" + seconds);
            }
            if (soccer.isGoldenGoal()) {
                timer.stop();
                time.setText("GOL DE ORO");
            } else if (soccer.isOver()) {
                    endMatch();
            }
        }
    }
    
    /**
     * recibe los jugadores del partido y los muestra
     * @param one es el jugador del lado izquierdo
     * @param two es el jugador del lado derecho
     */
    public void prepareMatch(Player one, Player two) {
        soccer = new POOBSoccer(one, two, field.getWidth(), field.getFieldHeight());
        time.setText("Tiempo: 1:00");
        timer.restart();
        field.setSoccer(soccer);
        updateScoreboard();
    }

    /**
     * muestra los goles de cada jugador en el marcador y en la barra de abajo
     */
    public void updateScoreboard() {
        Player playerOne = soccer.getPlayerOne();
        Player playerTwo = soccer.getPlayerTwo();
        scoreboard.setText(playerOne.getGoals() + "-" + playerTwo.getGoals());
        infoOne.setText(playerOne.getName() + "-" + playerOne.getCountry() + 
        " Goles " + playerOne.getGoals());
        infoTwo.setText(playerTwo.getName() + "-" + playerTwo.getCountry() + 
        " Goles " + playerTwo.getGoals());
        if (soccer.isOver()) {
            endMatch();
        }
    }

    /**
     * le da el teclado a la cancha para que los jugadores respondan
     */
    public void begin() {
        field.requestFocusInWindow();
    }

    /**
     * termina el partido: para los relojes, dice quien gano y vuelve al inicio
     */
    private void endMatch() {
        timer.stop();
        field.stop();
        Player winner = soccer.getWinner();
        String message = "gana" + winner.getName() + "(" + winner.getCountry() + ")";
        JOptionPane.showMessageDialog(this, message + "\n"
           + soccer.getPlayerOne().getGoals() + "-" + soccer.getPlayerTwo().getGoals());
        window.showScreen(MainWindow.START);
        
    }
}