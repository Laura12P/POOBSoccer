package presentation;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import domain.Player;
import java.util.Random;
import java.awt.Image;
import javax.swing.ImageIcon;
/**
 * fila con los jugadores del pais elegido, para que cada jugador escoja el suyo
 * muestra la ficha del jugador elegido
 * @author Jeronimo Avila Castillo
 * @author Laura Juliana Parra Velandia
 */
public class PlayerSelector extends JPanel implements ActionListener {
    private static final Player[] ALL = {
        new Player("Messi", "Argentina", 5, 2, 3, "Confusion"),
        new Player("Julian Alvarez", "Argentina", 4, 3, 3, "Confusion"),
        new Player("CR7", "Portugal", 3, 4, 3, "Reduccion"),
        new Player("Bruno Fernandes", "Portugal", 3, 3, 4, "Reduccion"),
        new Player("Alphonso Davies", "Canada", 5, 3, 2, "Congelacion"),
        new Player("Jonathan David", "Canada", 4, 3, 3, "Congelacion"),
        new Player("Haaland", "Noruega", 2, 3, 5, "Arco rival mas grande"),
        new Player("Odegaard", "Noruega", 4, 4, 2, "Arco rival mas grande"),
        new Player("Mbappe", "Francia", 4, 3, 3, "Super golpe"),
        new Player("Griezmann", "Francia", 3, 3, 4, "Super golpe")
    };
    private static final Color NORMAL = Color.WHITE;
    private static final Color SELECTED = new Color(255, 215, 0);

    private JLabel playerName;
    private JLabel profile;
    private JPanel row;
    private ArrayList<Player> available;
    private ArrayList<JButton> buttons;
    private Player selected;

    /**
     * CONSTRUCTOR
     * crea el selector vacio para un jugador
     * @param player es el texto que identifica al jugador
     */
    public PlayerSelector(String player) {
        selected = null;
        available = new ArrayList<Player>();
        buttons = new ArrayList<JButton>();
        prepareElements(player);
    }

    /**
     * ubica el nombre del jugador, la fila de botones y la ficha
     * @param player es el texto que identifica al jugador
     */
    private void prepareElements(String player) {
        setLayout(new BorderLayout(0, 10));
        setOpaque(false);
        playerName = new JLabel(player);
        playerName.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
        playerName.setForeground(Color.WHITE);
        add(playerName, BorderLayout.NORTH);
        row = new JPanel(new GridLayout(1, 2, 10, 0));
        row.setOpaque(false);
        add(row, BorderLayout.CENTER);
        profile = new JLabel("ningun jugador elegido");
        profile.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
        profile.setForeground(Color.WHITE);
        add(profile, BorderLayout.SOUTH);
    }

    /**
     * muestra los jugadores del pais que se eligio antes
     * @param country es el pais elegido
     */
    public void loadCountry(String country) {
        row.removeAll();
        available.clear();
        buttons.clear();
        selected = null;
        profile.setText("ningun jugador elegido");
        for (int i = 0; i < ALL.length; i++) {
            if (ALL[i].getCountry().equals(country)) {
                JButton button = new JButton(ALL[i].getName());
                String file = ALL[i].getName().toLowerCase().replace(" ", "");
                Image photo = new ImageIcon("imagenes/jugadores/" + file + ".png").getImage();
                button.setIcon(new ImageIcon(photo.getScaledInstance(70, 70, Image.SCALE_SMOOTH)));
                button.setVerticalTextPosition(JButton.BOTTOM);
                button.setHorizontalTextPosition(JButton.CENTER);
                button.setBackground(NORMAL);
                button.setFocusPainted(false);
                button.addActionListener(this);
                available.add(ALL[i]);
                buttons.add(button);
                row.add(button);
            }
        }
        row.revalidate();
        row.repaint();
    }

    /**
     * marca el jugador elegido, desmarca el otro y muestra su ficha
     * @param e es el evento cuando se da click
     */
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < buttons.size(); i++) {
            if (e.getSource() == buttons.get(i)) {
                selected = available.get(i);
                buttons.get(i).setBackground(SELECTED);
            } else {
                buttons.get(i).setBackground(NORMAL);
            }
        }
        profile.setText("<html>" + selected.getName() + " - " + selected.getCountry()
                + "<br>Velocidad: " + selected.getSpeed()
                + "  Salto: " + selected.getJump()
                + "  Fuerza: " + selected.getStrength()
                + "<br>Poder: " + selected.getPower() + "</html>");
    }

    /**
     * dice si el jugador ya escogio personaje
     * @return es true si ya hay un personaje elegido
     */
    public boolean hasSelected() {
        return selected != null;
    }

    /**
     * devuelve el personaje que se escogio
     * @return es el personaje elegido
     */
    public Player getSelected() {
        return selected;
    }

    /**
     * escoge un jugador cualquiera, para el que controla la maquina
     * @return es un jugador escogido al azar
     */
    public static Player atRandom() {
        Random random = new Random();
        return ALL[random.nextInt(ALL.length)];
    }
}