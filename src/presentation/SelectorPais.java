

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
/**
 * fila de paises para que cada jugador escoga el suyo
 * se usa para modo jugador y dos veces para modo de 2 jugadores
 * @author Jeronimo Avila Castillo  
 * @author Laura Juliana Parra Velandia
 */
public class SelectorPais extends JPanel implements ActionListener{
    private static final String[] PAISES = {"Colombia", "Canada", "Brasil", "Japon", "Argentina"};
    private static final Color NORMAL = Color.WHITE;
    private static final Color ELEGIDO = new Color(255, 215, 0);

    private JLabel nombreJugador;
    private JLabel mensaje;
    private JButton[] botones;
    private String elegido;
    /**
    * crea la fila de paises para un jugador
    * @param jugador es el texto que identifica al jugador
    */
    public SelectorPais(String jugador){
        elegido = null;
        prepararElementos(jugador);
        prepararAcciones();
    }
    /**
     * ubica donde va el nombre del jugador, el boton para el pais
     * y el pais que se escogio 
     */
    private void prepararElementos(String jugador){

        setLayout(new BorderLayout(0,10));
        setOpaque(false);
        nombreJugador = new JLabel(jugador);
        nombreJugador.setFont(new Font("Arial", Font.BOLD, 24));
        nombreJugador.setForeground(Color.WHITE);
        add(nombreJugador, BorderLayout.NORTH);
        JPanel fila = new JPanel(new GridLayout(1, PAISES.length, 10, 0));
        fila.setOpaque(false);
        botones = new JButton[PAISES.length];
        for (int i = 0; i < PAISES.length; i++) {
            botones[i] = new JButton(PAISES[i]);
            botones[i].setBackground(NORMAL);
            botones[i].setFocusPainted(false);
            fila.add(botones[i]);
        }
        add(fila, BorderLayout.CENTER);
        mensaje = new JLabel("ningun pais elegido");
        mensaje.setForeground(Color.WHITE);
        add(mensaje, BorderLayout.SOUTH);
    }
    /**
     * registra la fila con la que se atiende los clicks
     */
    private void prepararAcciones() {
        for (int i = 0; i < botones.length; i++) {
            botones[i].addActionListener(this);
        }
    }
    /**
     * se marca el pais elegido y desmarca los demas
     * @param e es el evento cuando se da click
     */
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < botones.length; i++) {
            if (e.getSource() == botones[i]) {
                elegido = PAISES[i]; 
                botones[i].setBackground(ELEGIDO);
            } else {
                botones[i].setBackground(NORMAL);
            }
        }
        mensaje.setText("pais elegido: " + elegido);
    }
    /**
     * dice si el jugador ya escogio el pais
     * @return es true si ya esta un pais elegido
     */
    public boolean hayElegido() {
        return elegido != null;
    }
    /**
     * devuelve que pais se escogio
     * @return es el pais que se escogio
     */
    public String getElegido() {
        return elegido;
    }
}