

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
public class PanelPaisUno extends JPanel implements ActionListener {
    private VentanaPrincipal ventana;
    private JLabel titulo;
    private SelectorPais selector;
    private JButton botonListo;
    private JButton botonVolver;
    /**
     * CONSTRUCTOR
     * crea la pantalla de seleccion
     * @param ventana es la ventana principal
     */
    public PanelPaisUno(VentanaPrincipal ventana) {
        this.ventana = ventana;
        prepararElementos();
        prepararAcciones();
    }
    /**
     * coloca el titulo arriba
     * fila de paises
     * y los botones necesarios
     */
    private void prepararElementos() {
        setLayout(new BorderLayout());
        setBackground(new Color(40, 120, 60));
        setBorder(BorderFactory.createEmptyBorder(30, 60, 20, 60));
        titulo = new JLabel("seleccion de pais", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 40));
        titulo.setForeground(Color.WHITE);
        add(titulo, BorderLayout.NORTH);
        selector = new SelectorPais("jugador 1");
        JPanel centro = new JPanel(new BorderLayout());
        centro.setOpaque(false);
        centro.setBorder(BorderFactory.createEmptyBorder(120, 0, 120, 0));
        centro.add(selector, BorderLayout.CENTER);
        add(centro, BorderLayout.CENTER);
        botonVolver = new JButton("volver");
        botonListo = new JButton("listo");
        JPanel abajo = new JPanel(new BorderLayout());
        abajo.setOpaque(false);
        abajo.add(botonVolver, BorderLayout.WEST);
        abajo.add(botonListo, BorderLayout.EAST);
        add(abajo, BorderLayout.SOUTH);
    }
    /**
     * registra el panel para ver como se atiende los clicks
     */
    private void prepararAcciones() {
        botonListo.addActionListener(this);
        botonVolver.addActionListener(this);
    }
    /**
     * muestra los controles ccuando ya se ah escogido pais
     * @param e es un evento de un click, se sabe cuando se le da click
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botonListo) {
            if (selector.hayElegido()) {
                ventana.mostrar(VentanaPrincipal.CONTROLES_UNO);
            } else {
                JOptionPane.showMessageDialog(this, "primero elija un pais");
            }
        } else if (e.getSource() == botonVolver) {
            ventana.mostrar(VentanaPrincipal.MODO);
        }
    }
}