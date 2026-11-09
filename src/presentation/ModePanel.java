package presentation;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
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
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

/**
 * es la pantalla donde el usuario elige el modo de juego
 * contra otro jugador o contra la maquina
 * @author Jeronimo Avila Castillo
 * @author Laura Juliana Parra Velandia
 */
public class ModePanel extends JPanel implements ActionListener {

    private MainWindow window;
    private JLabel title;
    private JButton twoPlayersButton;
    private JButton buttonMvP;
    private JButton buttonMvM;
    private JButton backButton;

    /**
     * CONSTRUCTOR
     * crea la pantalla de seleccion de modo
     * @param window es la ventana principal
     */
    public ModePanel(MainWindow window) {
        this.window = window;
        prepareElements();
        prepareActions();
    }

    /**
     * coloca el titulo y los modos en el centro
     */
    private void prepareElements() {
        setLayout(new BorderLayout());
        final Image background = new ImageIcon("imagenes/modo.png").getImage();
        JPanel panel = new JPanel(new BorderLayout()) {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
            }
        };
        panel.setBorder(BorderFactory.createEmptyBorder(40, 60, 40, 60));

        title = new JLabel("MODO DE JUEGO", SwingConstants.CENTER);
        title.setFont(new Font("Comic Sans MS", Font.BOLD, 64));
        title.setForeground(new Color(230, 190, 40));
        title.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        panel.add(title, BorderLayout.NORTH);

        twoPlayersButton = new JButton("Player vs Player");
        twoPlayersButton.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        twoPlayersButton.setBackground(new Color(230, 190, 40));
        twoPlayersButton.setForeground(Color.BLACK);
        twoPlayersButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
        twoPlayersButton.setFocusPainted(false);
        twoPlayersButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JSeparator line = new JSeparator();
        line.setForeground(Color.WHITE);

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

        JPanel modes = new JPanel(new GridLayout(4, 1, 0, 12));
        modes.setOpaque(false);
        modes.setBorder(BorderFactory.createEmptyBorder(30, 200, 30, 200));
        modes.add(twoPlayersButton);
        modes.add(line);
        modes.add(buttonMvP);
        modes.add(buttonMvM);
        panel.add(modes, BorderLayout.CENTER);

        backButton = new JButton("Volver");
        backButton.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
        backButton.setBackground(Color.WHITE);
        backButton.setForeground(Color.BLACK);
        backButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        backButton.setFocusPainted(false);
        backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.LEFT));
        bottom.setOpaque(false);
        bottom.add(backButton);
        panel.add(bottom, BorderLayout.SOUTH);

        add(panel, BorderLayout.CENTER);
    }

    /**
     * registra el panel como el que atiende los clicks de los botones
     */
    private void prepareActions() {
        twoPlayersButton.addActionListener(this);
        buttonMvP.addActionListener(this);
        buttonMvM.addActionListener(this);
        backButton.addActionListener(this);
    }

    /**
     * lleva al usuario a la siguiente pantalla, que es la de seleccion de pais
     * @param e es el evento cuando se da click
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == twoPlayersButton) {
            window.showScreen(MainWindow.COUNTRY_TWO);
        } else if (e.getSource() == buttonMvP) {
            window.showScreen(MainWindow.COUNTRY_ONE);
        } else if (e.getSource() == buttonMvM) {
            window.showScreen(MainWindow.COUNTRY_MACHINES);
        } else if (e.getSource() == backButton) {
            window.showScreen(MainWindow.START);
        }
    }
}