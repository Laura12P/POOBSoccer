import javax.swing.JSeparator;
import java.awt.Cursor;
import java.awt.BorderLayout;
import java.awt.Color;
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
import javax.swing.SwingConstants;

/**
 * es la pantalla donde el usuario elige el modo de juego
 * solo contra la maquina, o contra otro jugador
 * @Author Jeronimo Avila Castillo
 * @Author Laura Juliana Parra Velandia
 */
public class PanelModo extends JPanel implements ActionListener {

    private VentanaPrincipal ventana;
    private JLabel titulo;
    private JButton botonUnJugador;
    private JButton botonDosJugadores;
    private JButton buttonMvP;
    private JButton buttonMvM;
    private JButton botonVolver;

    /**
     * CONSTRUCTOR
     * crea la pantalla de seleccion de moodo
     * @param ventana es la ventana principal
     */
        public PanelModo(VentanaPrincipal ventana) {
        this.ventana = ventana;
        prepararElementos();
        prepararAcciones();
    }
    /**
     * coloca el titulo y los dos modos en el centro
     */
    private void prepararElementos() {
        setLayout(new BorderLayout());
        final Image background = new ImageIcon("imagenes/modo.png").getImage();
        JPanel panel = new JPanel(new BorderLayout()) {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
            }
        };
        panel.setBorder(BorderFactory.createEmptyBorder(40, 60, 40, 60));

        titulo = new JLabel("MODO DE JUEGO", SwingConstants.CENTER);
        titulo.setFont(new Font("Comic Sans MS", Font.BOLD, 64));
        titulo.setForeground(new Color(230, 190, 40));
        titulo.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        panel.add(titulo, BorderLayout.NORTH);

        botonUnJugador = new JButton("1 jugador");
        botonUnJugador.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        botonUnJugador.setBackground(new Color(230, 190, 40));
        botonUnJugador.setForeground(Color.BLACK);
        botonUnJugador.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
        botonUnJugador.setFocusPainted(false);
        botonUnJugador.setCursor(new Cursor(Cursor.HAND_CURSOR));

        botonDosJugadores = new JButton("2 jugadores");
        botonDosJugadores.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        botonDosJugadores.setBackground(new Color(230, 190, 40));
        botonDosJugadores.setForeground(Color.BLACK);
        botonDosJugadores.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
        botonDosJugadores.setFocusPainted(false);
        botonDosJugadores.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JSeparator linea = new JSeparator();
        linea.setForeground(Color.WHITE);
        buttonMvP = new JButton("Machine vs Player");
        buttonMvP.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        buttonMvP.setBackground(new Color(230, 190, 40));
        buttonMvP.setForeground(Color.BLACK);
        buttonMvP.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
        buttonMvP.setFocusPainted(false);
        buttonMvP.setCursor(new Cursor(Cursor.HAND_CURSOR));

        buttonMvM = new JButton("Machine vs Machine");
        buttonMvM.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        buttonMvM.setBackground(new Color(230, 190, 40));
        buttonMvM.setForeground(Color.BLACK);
        buttonMvM.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
        buttonMvM.setFocusPainted(false);
        buttonMvM.setCursor(new Cursor(Cursor.HAND_CURSOR));

        
        JPanel modos = new JPanel(new GridLayout(5, 1, 0, 4));
        modos.setOpaque(false);
        modos.setBorder(BorderFactory.createEmptyBorder(30, 200, 30, 200));
        modos.add(botonUnJugador);
        modos.add(botonDosJugadores);
        modos.add(linea);
        modos.add(buttonMvP);
        modos.add(buttonMvM);
        panel.add(modos, BorderLayout.CENTER);

        botonVolver = new JButton("Volver");
        botonVolver.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
        botonVolver.setBackground(Color.WHITE);
        botonVolver.setForeground(Color.BLACK);
        botonVolver.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        botonVolver.setFocusPainted(false);
        botonVolver.setCursor(new Cursor(Cursor.HAND_CURSOR));
        JPanel abajo = new JPanel(new FlowLayout(FlowLayout.LEFT));
        abajo.setOpaque(false);
        abajo.add(botonVolver);
        panel.add(abajo, BorderLayout.SOUTH);

        add(panel, BorderLayout.CENTER);
    }
    /**
     * registra el panel como el que atiende los clicks que se llegan a dar
     */
    private void prepararAcciones() {

        botonUnJugador.addActionListener(this);
        botonDosJugadores.addActionListener(this);
        botonVolver.addActionListener(this);
    }
    /**
     * lleva al usuario a la siguiente interfaz que es la de seleccion de pais
     * @param e es el evento cuando se da click
     */
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == botonUnJugador){
            ventana.mostrar(VentanaPrincipal.PAIS_UNO);
        } else if (e.getSource() == botonDosJugadores) {
            ventana.mostrar(VentanaPrincipal.PAIS_DOS);
        } else if (e.getSource() == botonVolver) {
            ventana.mostrar(VentanaPrincipal.INICIO);
        }
    }
}