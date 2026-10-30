

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
/**
 * muestra las teclas que tiene cada jugador 
 * tanto para modo 1 jugador, como para 2 jugadores
 * @author Jeronimo Avila Castillo
 * @author Laura Juliana Parra Velandia
 * 
 */
public class TablaControles extends JPanel {
    private static final String[] ACCIONES = {"mover a la izquierda","mover a la derecha","saltar", "patear", "usar poder"};
    private JLabel nombreJugador;
    /**
     * CONSTRUCTOR
     * crea la tabla de controles para un jugador
     * @param jugador es el texto que identifica al jugador
     * @param teclas es la teclas asignadas para el movimiento
     */
    public TablaControles(String jugador, String[] teclas) {
        prepararElementos(jugador, teclas);
    }
    /**
     * pone el nombre de la tecla y que hace la tecla
     * @param jugador es el texto que identifica al jugador
     * @param teclas es la teclas que hacen ciertas cosas
     */
    private void prepararElementos(String jugador, String[] teclas) {
        setLayout(new BorderLayout(0, 10));
        setOpaque(false);
        nombreJugador = new JLabel(jugador, SwingConstants.CENTER);
        nombreJugador.setFont(new Font("Arial", Font.BOLD, 24));
        nombreJugador.setForeground(Color.WHITE);
        add(nombreJugador, BorderLayout.NORTH);
        JPanel tabla = new JPanel(new GridLayout(ACCIONES.length, 2, 10, 6));
        tabla.setOpaque(false);
        for (int i = 0; i < ACCIONES.length; i++) {
            JLabel accion = new JLabel(ACCIONES[i]);
            accion.setFont(new Font("Arial", Font.PLAIN, 18));
            accion.setForeground(Color.WHITE);
            JLabel tecla = new JLabel(teclas[i], SwingConstants.CENTER);
            tecla.setFont(new Font("Arial", Font.BOLD, 18));
            tecla.setOpaque(true);
            tecla.setBackground(Color.WHITE);
            tecla.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            tabla.add(accion);
            tabla.add(tecla);
        }
        add(tabla, BorderLayout.CENTER);
    }
}