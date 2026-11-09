package presentation;
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
public class ControlsTable extends JPanel {
    private static final String[] ACTIONS = {"mover a la izquierda","mover a la derecha","saltar", "patear", "usar poder"};
    private JLabel playerName;
    /**
     * CONSTRUCTOR
     * crea la tabla de controles para un jugador
     * @param player es el texto que identifica al jugador
     * @param keys es la teclas asignadas para el movimiento
     */
    public ControlsTable(String player, String[] keys) {
        prepareElements(player, keys);
    }
    /**
     * pone el nombre de la tecla y que hace la tecla
     * @param player es el texto que identifica al jugador
     * @param keys es la teclas que hacen ciertas cosas
     */
    private void prepareElements(String player, String[] keys) {
        setLayout(new BorderLayout(0, 10));
        setOpaque(false);
        playerName = new JLabel(player, SwingConstants.CENTER);
        playerName.setFont(new Font("Arial", Font.BOLD, 24));
        playerName.setForeground(Color.WHITE);
        add(playerName, BorderLayout.NORTH);
        JPanel table = new JPanel(new GridLayout(ACTIONS.length, 2, 10, 6));
        table.setOpaque(false);
        for (int i = 0; i < ACTIONS.length; i++) {
            JLabel action = new JLabel(ACTIONS[i]);
            action.setFont(new Font("Arial", Font.PLAIN, 18));
            action.setForeground(Color.WHITE);
            JLabel key = new JLabel(keys[i], SwingConstants.CENTER);
            key.setFont(new Font("Arial", Font.BOLD, 18));
            key.setOpaque(true);
            key.setBackground(Color.WHITE);
            key.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            table.add(action);
            table.add(key);
        }
        add(table, BorderLayout.CENTER);
    }
}