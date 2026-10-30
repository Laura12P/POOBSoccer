

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
public class PanelControles extends JPanel implements ActionListener {
    private static final String[] TECLAS_UNO = {"A", "D", "W", "S", "Q"};
    private static final String[] TECLAS_DOS = {"←", "→", "↑", "↓", "Ctrl derecho"};

    private VentanaPrincipal ventana;
    private boolean dosJugadores;
    private JLabel titulo;
    private JButton botonJugar;
    private JButton botonVolver;
    /**
     * CONSTRUCTOR
     * crea la pantala de los controles
     * @param ventana es la ventana principal
     * @param dosJugadores da true si es modo de 2 jugadores, si no da false
     * 
     */
    public PanelControles(VentanaPrincipal ventana, boolean dosJugadores){
         this.ventana = ventana;
         this.dosJugadores = dosJugadores;
         prepararElementos();
         prepararAcciones();
    }
    /**
     * muestra el titulo, la tabla del jugador
     * y los botones con sus funciones correctas
     */
    private void prepararElementos() {
        setLayout(new BorderLayout());
        setBackground(new Color(40, 120, 60));
        setBorder(BorderFactory.createEmptyBorder(30, 60, 20, 60));
        titulo = new JLabel("Controles", SwingConstants.CENTER);   
        titulo.setFont(new Font("Arial", Font.BOLD, 40));
        titulo.setForeground(Color.WHITE);
        add(titulo, BorderLayout.NORTH);
        JPanel centro;
        if (dosJugadores) {
            centro = new JPanel(new GridLayout(1, 2, 60, 0));
            centro.setBorder(BorderFactory.createEmptyBorder(60, 0, 60, 0));
            centro.add(new TablaControles("Jugador 1", TECLAS_UNO));
            centro.add(new TablaControles("Jugador 2", TECLAS_DOS));
        } else {
            centro = new JPanel(new GridLayout(1, 1));
            centro.setBorder(BorderFactory.createEmptyBorder(60, 200, 60, 200));
            centro.add(new TablaControles("Jugador 1", TECLAS_UNO));
        }
        centro.setOpaque(false);
        add(centro, BorderLayout.CENTER);
        botonVolver = new JButton("volver");
        botonJugar = new JButton("jugar");
        JPanel abajo = new JPanel(new BorderLayout());
        abajo.setOpaque(false);
        abajo.add(botonVolver, BorderLayout.WEST);
        abajo.add(botonJugar, BorderLayout.EAST);
        add(abajo, BorderLayout.SOUTH);
    }
    /**
     * este panel guarda o registra como se atienden los clicks del cliente
     */
    private void prepararAcciones() {
        botonJugar.addActionListener(this);
        botonVolver.addActionListener(this);
    }
    /**
     * cuando empieza el partido o se regresa a la seleccion del pais
     * @param e es el evento cuando se da click a algun boton
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botonJugar) {
            ventana.mostrar(VentanaPrincipal.JUEGO);
        } else if (e.getSource() == botonVolver) {
            if (dosJugadores) {
                ventana.mostrar(VentanaPrincipal.PAIS_DOS);
            } else {
                ventana.mostrar(VentanaPrincipal.PAIS_UNO);
            }
        }
    }
}