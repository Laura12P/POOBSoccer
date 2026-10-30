

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
 * es la pantalla del juego
 * el tiempo
 * el marcador
 * y el boton de menu
 * @author Jeronimo Avila Castillo
 * @author Laura Juliana Parra Velandia
 */
public class PanelJuego extends JPanel implements ActionListener {
    private VentanaPrincipal ventana;
    private JLabel tiempo;
    private JLabel marcador;
    private JButton botonMenu;
    private PanelCancha cancha;
    /**
     * CONSTRUCTOR
     * crea la pantalla del partido
     * @param ventana es la ventana principal
     */
    public PanelJuego(VentanaPrincipal ventana) {
        this.ventana = ventana;
        prepararElementos();
        prepararAcciones();
    }
    /**
     * es la barra del tiempo
     * con el marcador
     * y el boton de menu
     * y prepara el resto de la cancha
     */
    private void prepararElementos() {
        setLayout(new BorderLayout());
        JPanel barra = new JPanel(new BorderLayout());
        barra.setBackground(Color.BLACK);
        barra.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
        tiempo = new JLabel("Tiempo: 2:00");
        tiempo.setFont(new Font("Arial", Font.BOLD, 18));
        tiempo.setForeground(Color.WHITE);
        barra.add(tiempo, BorderLayout.WEST);
        marcador = new JLabel("0 - 0", SwingConstants.CENTER);
        marcador.setFont(new Font("Arial", Font.BOLD, 28));
        marcador.setForeground(Color.WHITE);
        barra.add(marcador, BorderLayout.CENTER);
        botonMenu = new JButton("Menu");
        barra.add(botonMenu, BorderLayout.EAST);
        add(barra, BorderLayout.NORTH);
        cancha = new PanelCancha();
        add(cancha, BorderLayout.CENTER);
    }
    /**
     * es el que registra el panel cuando se le da click
     * 
     */
    private void prepararAcciones() {
        botonMenu.addActionListener(this);
    }
    /**
     * es menu pregunta si quiere abandonar el partido
     * @param e es el evento del click
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botonMenu) {
            int respuesta = JOptionPane.showConfirmDialog(this,
                    "desea abandonar el partido?", "Menu",
                    JOptionPane.YES_NO_OPTION);
            if (respuesta == JOptionPane.YES_OPTION) {
                ventana.mostrar(VentanaPrincipal.INICIO);

            }
        }
    }

}