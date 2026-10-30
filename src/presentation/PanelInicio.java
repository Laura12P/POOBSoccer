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
public class PanelInicio extends JPanel implements ActionListener {

    private VentanaPrincipal ventana;
    private JLabel labelTitle;
    private JButton botonJugar;

    /**
     * Crea la pantalla de inicio.
     *
     * @param ventana ventana principal, usada para cambiar de pantalla
     */
    public PanelInicio(VentanaPrincipal ventana) {
        this.ventana = ventana;
        prepararElementos();
        prepararAcciones();
    }

    /**
     * Ubica el titulo arriba, el boton Jugar en el centro
     * y el boton Opciones abajo a la izquierda.
     */
    private void prepararElementos() {
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

        botonJugar = new JButton("Jugar");
        botonJugar.setFont(new Font("Arial", Font.BOLD, 28));
        botonJugar.setPreferredSize(new Dimension(220, 70));
        botonJugar.setBackground(new Color(230, 190, 40));
        botonJugar.setForeground(Color.BLACK);
        botonJugar.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
        botonJugar.setFocusPainted(false);
        botonJugar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        JPanel centro = new JPanel(new FlowLayout(FlowLayout.CENTER));
        centro.setOpaque(false);
        centro.setBorder(BorderFactory.createEmptyBorder(70,0,0,0));
        centro.add(botonJugar);

        panel.add(labelTitle);
        panel.add(centro);
        add(panel, BorderLayout.CENTER);
    }

    /**
     * Registra este panel como el que atiende los clics de sus botones.
     */
    private void prepararAcciones() {
        botonJugar.addActionListener(this);
    }

    /**
     * Atiende el clic de cualquiera de los botones y lleva
     * a la pantalla que corresponde.
     *
     * @param e evento del clic; permite saber que boton se oprimio
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botonJugar) {
            ventana.mostrar(VentanaPrincipal.MODO);
        }
    }
}