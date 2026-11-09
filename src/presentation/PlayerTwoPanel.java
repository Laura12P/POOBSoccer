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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * pantalla donde se escoge el jugador de cada uno
 * entre los del pais que eligio antes
 * sirve para jugador vs jugador y para maquina vs maquina
 * @author Jeronimo Avila Castillo
 * @author Laura Juliana Parra Velandia
 */
public class PlayerTwoPanel extends JPanel implements ActionListener {
    private MainWindow window;
    private boolean machines;
    private JLabel title;
    private PlayerSelector selectorOne;
    private PlayerSelector selectorTwo;
    private JButton readyButton;
    private JButton backButton;

    /**
     * CONSTRUCTOR
     * crea la pantalla de seleccion de jugadores para dos
     * @param window es la ventana principal
     * @param machines es true si los dos son maquinas, false si son personas
     */
    public PlayerTwoPanel(MainWindow window, boolean machines) {
        this.window = window;
        this.machines = machines;
        prepareElements();
        prepareActions();
    }

    /**
     * coloca el titulo, los dos selectores y los botones
     */
    private void prepareElements() {
        setLayout(new BorderLayout());
        setBackground(new Color(40, 120, 60));
        setBorder(BorderFactory.createEmptyBorder(30, 60, 20, 60));
        title = new JLabel("seleccion de jugadores", SwingConstants.CENTER);
        title.setFont(new Font("Comic Sans MS", Font.BOLD, 40));
        title.setForeground(Color.WHITE);
        add(title, BorderLayout.NORTH);
        if (machines) {
            selectorOne = new PlayerSelector("maquina 1");
            selectorTwo = new PlayerSelector("maquina 2");
        } else {
            selectorOne = new PlayerSelector("jugador 1");
            selectorTwo = new PlayerSelector("jugador 2");
        }
        JPanel center = new JPanel(new GridLayout(2, 1, 0, 20));
        center.setOpaque(false);
        center.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        center.add(selectorOne);
        center.add(selectorTwo);
        add(center, BorderLayout.CENTER);
        backButton = new JButton("Volver");
        readyButton = new JButton("Listo");
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
     * muestra los jugadores de los paises que se eligieron antes
     * @param countryOne es el pais del primero
     * @param countryTwo es el pais del segundo
     */
    public void loadCountries(String countryOne, String countryTwo) {
        selectorOne.loadCountry(countryOne);
        selectorTwo.loadCountry(countryTwo);
    }

    /**
     * si ya escogieron, va a los controles (personas) o al juego (maquinas)
     * @param e es el evento cuando se da click
     */
   public void actionPerformed(ActionEvent e) {
    if (e.getSource() == readyButton) {
        if (selectorOne.hasSelected() && selectorTwo.hasSelected()) {
            window.savePlayers(selectorOne.getSelected(), selectorTwo.getSelected());
            if (machines) {
                window.startMatch();
            } else {
                window.showScreen(MainWindow.CONTROLS_TWO);
            }
        } else {
            JOptionPane.showMessageDialog(this, "deben elegir jugador");
        }
    } else if (e.getSource() == backButton) {
        if (machines) {
            window.showScreen(MainWindow.COUNTRY_MACHINES);
        } else {
            window.showScreen(MainWindow.COUNTRY_TWO);
        }
    }
}
}