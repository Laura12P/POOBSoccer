

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
 * pantalla donde cada jugador escoje 
 * el pais
 * @author Jeronimo Avila Castillo
 * @author Laura Juliana Parra Velandia
 */
public class PanelPaisDos extends JPanel implements ActionListener {
    private VentanaPrincipal ventana;
    private JLabel titulo;
    private SelectorPais selectorUno;
    private SelectorPais selectorDos;
    private JButton botonListo;
    private JButton botonVolver;
    /**
     * CONSTRUCTOR
     * es la que crea la pantalla de seleccion para dos jugadores
     * @param ventana es la ventana principal
     */
    public PanelPaisDos(VentanaPrincipal ventana){
        this.ventana = ventana;
        prepararElementos();
        prepararAcciones();
    }
    /**
     * coloca el titulo
     * las filas de los jugadores
     * y los botones importantes
     */
     private void prepararElementos() {
        setLayout(new BorderLayout());
        setBackground(new Color(40, 120, 60));
        setBorder(BorderFactory.createEmptyBorder(30, 60, 20, 60));
        titulo = new JLabel("seleccion de paises", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 40));
        titulo.setForeground(Color.WHITE);
        add(titulo, BorderLayout.NORTH);
        selectorUno = new SelectorPais("jugador 1");
        selectorDos = new SelectorPais("jugador 2");
        JPanel centro = new JPanel(new GridLayout(2, 1, 0, 40));
        centro.setOpaque(false);
        centro.setBorder(BorderFactory.createEmptyBorder(40, 0, 40, 0));
        centro.add(selectorUno);
        centro.add(selectorDos);
        add(centro, BorderLayout.CENTER);
        botonVolver = new JButton("Volver");
        botonListo = new JButton("Listo");
        JPanel abajo = new JPanel(new BorderLayout());
        abajo.setOpaque(false);
        abajo.add(botonVolver, BorderLayout.WEST);
        abajo.add(botonListo, BorderLayout.EAST);
        add(abajo, BorderLayout.SOUTH);
     }
     /**
      * registra el panel para ver que clicks llegan a los botones
      */
     private void prepararAcciones() {
        botonListo.addActionListener(this);
        botonVolver.addActionListener(this);
     }
     /**
      * va a los controles si ya se selecciono pais los dos
      * @param e es un evento de clicks 
      * se puede ver que boton se activo
      * 
      */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botonListo) {
            if (selectorUno.hayElegido() && selectorDos.hayElegido()) {
                ventana.mostrar(VentanaPrincipal.CONTROLES_DOS);
            } else {
                JOptionPane.showMessageDialog(this, "deben elegir pais");
            }
        } else if (e.getSource() == botonVolver) {
            ventana.mostrar(VentanaPrincipal.MODO);
        }
    }
}