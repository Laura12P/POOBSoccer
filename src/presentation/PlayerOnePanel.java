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

/**
 * pantalla donde el jugador escoge su personaje
 * entre los del pais que eligio antes
 * @author Jeronimo Avila Castillo
 * @author Laura Juliana Parra Velandia
 */
public class PlayerOnePanel extends JPanel implements ActionListener {
    private MainWindow window;
    private JLabel title;
    private PlayerSelector selector;
    private JButton readyButton;
    private JButton backButton;

    /**
     * CONSTRUCTOR
     * crea la pantalla de seleccion de jugador
     * @param window es la ventana principal
     */
    public PlayerOnePanel(MainWindow window) {
        this.window = window;
        prepareElements();
        prepareActions();
    }

    /**
     * coloca el titulo, el selector y los botones
     */
    private void prepareElements() {
        setLayout(new BorderLayout());
        setBackground(new Color(40, 120, 60));
        setBorder(BorderFactory.createEmptyBorder(30, 60, 20, 60));
        title = new JLabel("seleccion de jugador", SwingConstants.CENTER);
        title.setFont(new Font("Comic Sans MS", Font.BOLD, 40));
        title.setForeground(Color.WHITE);
        add(title, BorderLayout.NORTH);
        selector = new PlayerSelector("jugador 1");
        JPanel center = new JPanel(new BorderLayout());
        center.setOpaque(false);
        center.setBorder(BorderFactory.createEmptyBorder(80, 0, 80, 0));
        center.add(selector, BorderLayout.CENTER);
        add(center, BorderLayout.CENTER);
        backButton = new JButton("volver");
        readyButton = new JButton("listo");
        JPanel bottom = new JPanel(new BorderLayout());
        bottom.setOpaque(false);
        bottom.add(backButton, BorderLayout.WEST);
        bottom.add(readyButton, BorderLayout.EAST);
        add(bottom, BorderLayout.SOUTH);
    }

    /**
     * registra el panel para atender los clicks de los botones
     */
    private void prepareActions() {
        readyButton.addActionListener(this);
        backButton.addActionListener(this);
    }

    /**
     * muestra los jugadores del pais que se eligio antes
     * @param country es el pais elegido
     */
    public void loadCountry(String country) {
        selector.loadCountry(country);
    }

    /**
     * va a los controles si ya se escogio jugador
     * @param e es el evento cuando se da click
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == readyButton) {
            if (selector.hasSelected()) {
                window.savePlayers(selector.getSelected(), PlayerSelector.atRandom());
                window.showScreen(MainWindow.CONTROLS_ONE);
            } else {
                JOptionPane.showMessageDialog(this, "primero elija un jugador");
            }
        } else if (e.getSource() == backButton) {
            window.showScreen(MainWindow.COUNTRY_ONE);
        }
    }
}