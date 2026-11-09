package presentation;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Pantalla de inicio de POOBSoccer.
 * Muestra el nombre del juego, el boton para empezar
 * y el acceso a las opciones.
 *
 * @author Jeronimo Avila Castillo
 * @author Laura Juliana Parra Velandia
 * @version 1.0
 */
public class StartPanel extends JPanel implements ActionListener {

    private MainWindow window;
    private JLabel labelTitle;
    private JButton playButton;

    /**
     * Crea la pantalla de inicio.
     *
     * @param window ventana principal, usada para cambiar de pantalla
     */
    public StartPanel(MainWindow window) {
        this.window = window;
        prepareElements();
        prepareActions();
    }

    /**
     * Ubica el titulo arriba, el boton Jugar en el centro
     * y el boton Opciones abajo a la izquierda.
     */
    private void prepareElements() {
        setLayout(new BorderLayout());
        final Image background = new ImageIcon("imagenes/fondoinicio.png").getImage();
        JPanel panel = new JPanel(new GridLayout(3, 1, 20, 20)) {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
            }
        };
        panel.setBorder(BorderFactory.createEmptyBorder(40, 150, 40, 150));

        labelTitle = new JLabel("POOBSoccer", JLabel.CENTER);
        labelTitle.setFont(new Font("comic Sans MS", Font.BOLD, 90));
        labelTitle.setForeground(Color.WHITE);

        playButton = new JButton("Jugar");
        playButton.setFont(new Font("Arial", Font.BOLD, 28));
        playButton.setPreferredSize(new Dimension(220, 70));
        playButton.setBackground(new Color(230, 190, 40));
        playButton.setForeground(Color.BLACK);
        playButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
        playButton.setFocusPainted(false);
        playButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        JPanel center = new JPanel(new FlowLayout(FlowLayout.CENTER));
        center.setOpaque(false);
        center.setBorder(BorderFactory.createEmptyBorder(70,0,0,0));
        center.add(playButton);

        panel.add(labelTitle);
        panel.add(center);
        add(panel, BorderLayout.CENTER);
    }

    /**
     * Registra este panel como el que atiende los clics de sus botones.
     */
    private void prepareActions() {
        playButton.addActionListener(this);
    }

    /**
     * Atiende el clic de cualquiera de los botones y lleva
     * a la pantalla que corresponde.
     *
     * @param e evento del clic; permite saber que boton se oprimio
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == playButton) {
            window.showScreen(MainWindow.MODE);
        }
    }
}