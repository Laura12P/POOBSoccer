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
 * pantalla donde el jugador escoge 
 * pais
 * @author Jeronimo Avila Castillo
 * @author Laura Juliana Parra Velandia
 */
public class CountryOnePanel extends JPanel implements ActionListener {
    private MainWindow window;
    private JLabel title;
    private CountrySelector selector;
    private JButton readyButton;
    private JButton backButton;
    /**
     * CONSTRUCTOR
     * crea la pantalla de seleccion
     * @param window es la ventana principal
     */
    public CountryOnePanel(MainWindow window) {
        this.window = window;
        prepareElements();
        prepareActions();
    }
    /**
     * coloca el titulo arriba
     * fila de paises
     * y los botones necesarios
     */
    private void prepareElements() {
        setLayout(new BorderLayout());
        setBackground(new Color(40, 120, 60));
        setBorder(BorderFactory.createEmptyBorder(30, 60, 20, 60));
        title = new JLabel("seleccion de pais", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 40));
        title.setForeground(Color.WHITE);
        add(title, BorderLayout.NORTH);
        selector = new CountrySelector("jugador 1");
        JPanel center = new JPanel(new BorderLayout());
        center.setOpaque(false);
        center.setBorder(BorderFactory.createEmptyBorder(120, 0, 120, 0));
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
     * registra el panel para ver como se atiende los clicks
     */
    private void prepareActions() {
        readyButton.addActionListener(this);
        backButton.addActionListener(this);
    }
    /**
     * muestra los controles ccuando ya se ah escogido pais
     * @param e es un evento de un click, se sabe cuando se le da click
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == readyButton) {
            if (selector.hasSelected()) {
                window.choosePlayerOne(selector.getSelected());
            } else {
                JOptionPane.showMessageDialog(this, "primero elija un pais");
            }
        } else if (e.getSource() == backButton) {
            window.showScreen(MainWindow.MODE);
        }
    }
}