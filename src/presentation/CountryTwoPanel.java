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
 * pantalla donde se escoge el pais de los dos jugadores
 * sirve para jugador vs jugador y para maquina vs maquina
 * @author Jeronimo Avila Castillo
 * @author Laura Juliana Parra Velandia
 */
public class CountryTwoPanel extends JPanel implements ActionListener {
    private MainWindow window;
    private boolean machines;
    private JLabel title;
    private CountrySelector selectorOne;
    private CountrySelector selectorTwo;
    private JButton readyButton;
    private JButton backButton;

    /**
     * CONSTRUCTOR
     * crea la pantalla de seleccion de paises para dos
     * @param window es la ventana principal
     * @param machines es true si los dos son maquinas, false si son personas
     */
    public CountryTwoPanel(MainWindow window, boolean machines) {
        this.window = window;
        this.machines = machines;
        prepareElements();
        prepareActions();
    }

    /**
     * coloca el titulo, las filas de paises y los botones
     */
    private void prepareElements() {
        setLayout(new BorderLayout());
        setBackground(new Color(40, 120, 60));
        setBorder(BorderFactory.createEmptyBorder(30, 60, 20, 60));
        title = new JLabel("seleccion de paises", SwingConstants.CENTER);
        title.setFont(new Font("Comic Sans MS", Font.BOLD, 40));
        title.setForeground(Color.WHITE);
        add(title, BorderLayout.NORTH);
        if (machines) {
            selectorOne = new CountrySelector("maquina 1");
            selectorTwo = new CountrySelector("maquina 2");
        } else {
            selectorOne = new CountrySelector("jugador 1");
            selectorTwo = new CountrySelector("jugador 2");
        }
        JPanel center = new JPanel(new GridLayout(2, 1, 0, 40));
        center.setOpaque(false);
        center.setBorder(BorderFactory.createEmptyBorder(40, 0, 40, 0));
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
     * va a la seleccion de jugador si ya se eligio pais los dos
     * @param e es el evento cuando se da click
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == readyButton) {
            if (selectorOne.hasSelected() && selectorTwo.hasSelected()) {
                window.choosePlayerTwo(selectorOne.getSelected(), selectorTwo.getSelected(), machines);
            } else {
                JOptionPane.showMessageDialog(this, "deben elegir pais");
            }
        } else if (e.getSource() == backButton) {
            window.showScreen(MainWindow.MODE);
        }
    }
}