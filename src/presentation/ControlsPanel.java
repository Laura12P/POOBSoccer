package presentation;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
/**
 * es la pantalla que muestra los controles
 * antes de empezar el partido
 * tanto para un jugador, como para dos jugadores
 * @author Jeronimo Avila Castillo
 * @author Laura Juliana Parra Velandia
 */
public class ControlsPanel extends JPanel implements ActionListener {
    private static final String[] KEYS_ONE = {"A", "D", "W", "S", "Q"};
    private static final String[] KEYS_TWO = {"←", "→", "↑", "↓", "Ctrl derecho"};

    private MainWindow window;
    private boolean twoPlayers;
    private JLabel title;
    private JButton playButton;
    private JButton backButton;
    /**
     * CONSTRUCTOR
     * crea la pantala de los controles
     * @param window es la ventana principal
     * @param twoPlayers da true si es modo de 2 jugadores, si no da false
     * 
     */
    public ControlsPanel(MainWindow window, boolean twoPlayers){
         this.window = window;
         this.twoPlayers = twoPlayers;
         prepareElements();
         prepareActions();
    }
    /**
     * muestra el titulo, la tabla del jugador
     * y los botones con sus funciones correctas
     */
    private void prepareElements() {
        setLayout(new BorderLayout());
        setBackground(new Color(40, 120, 60));
        setBorder(BorderFactory.createEmptyBorder(30, 60, 20, 60));
        title = new JLabel("Controles", SwingConstants.CENTER);   
        title.setFont(new Font("Arial", Font.BOLD, 40));
        title.setForeground(Color.WHITE);
        add(title, BorderLayout.NORTH);
        JPanel center;
        if (twoPlayers) {
            center = new JPanel(new GridLayout(1, 2, 60, 0));
            center.setBorder(BorderFactory.createEmptyBorder(60, 0, 60, 0));
            center.add(new ControlsTable("Jugador 1", KEYS_ONE));
            center.add(new ControlsTable("Jugador 2", KEYS_TWO));
        } else {
            center = new JPanel(new GridLayout(1, 1));
            center.setBorder(BorderFactory.createEmptyBorder(60, 200, 60, 200));
            center.add(new ControlsTable("Jugador 1", KEYS_ONE));
        }
        center.setOpaque(false);
        add(center, BorderLayout.CENTER);
        backButton = new JButton("volver");
        playButton = new JButton("jugar");
        JPanel bottom = new JPanel(new BorderLayout());
        bottom.setOpaque(false);
        bottom.add(backButton, BorderLayout.WEST);
        bottom.add(playButton, BorderLayout.EAST);
        add(bottom, BorderLayout.SOUTH);
    }
    /**
     * este panel guarda o registra como se atienden los clicks del cliente
     */
    private void prepareActions() {
        playButton.addActionListener(this);
        backButton.addActionListener(this);
    }
    /**
     * cuando empieza el partido o se regresa a la seleccion del pais
     * @param e es el evento cuando se da click a algun boton
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == playButton) {
            window.startMatch();
        } else if (e.getSource() == backButton) {
            if (twoPlayers) {
                window.showScreen(MainWindow.PLAYER_TWO);
            } else {
                window.showScreen(MainWindow.PLAYER_ONE);
            }
        }
    }
}