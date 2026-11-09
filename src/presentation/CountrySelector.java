package presentation;
import java.awt.Image;
import javax.swing.ImageIcon;
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
public class CountrySelector extends JPanel implements ActionListener{
    private static final String[] COUNTRIES = {"Argentina", "Portugal", "Canada", "Noruega", "Francia"};
    private static final Color NORMAL = Color.WHITE;
    private static final Color SELECTED = new Color(255, 215, 0);

    private JLabel playerName;
    private JLabel message;
    private JButton[] buttons;
    private String selected;
    /**
    * crea la fila de paises para un jugador
    * @param player es el texto que identifica al jugador
    */
    public CountrySelector(String player){
        selected = null;
        prepareElements(player);
        prepareActions();
    }
    /**
     * ubica donde va el nombre del jugador, el boton para el pais
     * y el pais que se escogio 
     */
    private void prepareElements(String player){

        setLayout(new BorderLayout(0,10));
        setOpaque(false);
        playerName = new JLabel(player);
        playerName.setFont(new Font("Arial", Font.BOLD, 24));
        playerName.setForeground(Color.WHITE);
        add(playerName, BorderLayout.NORTH);
        JPanel row = new JPanel(new GridLayout(1, COUNTRIES.length, 10, 0));
        row.setOpaque(false);
        buttons = new JButton[COUNTRIES.length];
        for (int i = 0; i < COUNTRIES.length; i++) {
            buttons[i] = new JButton(COUNTRIES[i]);
            Image flag = new  ImageIcon("imagenes/banderas/"+COUNTRIES[i].toLowerCase() +".png").getImage();
            buttons[i].setIcon(new ImageIcon(flag.getScaledInstance(90, 60, Image.SCALE_SMOOTH)));
            buttons[i].setVerticalTextPosition(JButton.BOTTOM);
            buttons[i].setHorizontalTextPosition(JButton.CENTER);
            buttons[i].setBackground(NORMAL);
            buttons[i].setFocusPainted(false);
            row.add(buttons[i]);
        }
        add(row, BorderLayout.CENTER);
        message = new JLabel("ningun pais elegido");
        message.setForeground(Color.WHITE);
        add(message, BorderLayout.SOUTH);
    }
    /**
     * registra la fila con la que se atiende los clicks
     */
    private void prepareActions() {
        for (int i = 0; i < buttons.length; i++) {
            buttons[i].addActionListener(this);
        }
    }
    /**
     * se marca el pais elegido y desmarca los demas
     * @param e es el evento cuando se da click
     */
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < buttons.length; i++) {
            if (e.getSource() == buttons[i]) {
                selected = COUNTRIES[i]; 
                buttons[i].setBackground(SELECTED);
            } else {
                buttons[i].setBackground(NORMAL);
            }
        }
        message.setText("pais elegido: " + selected);
    }
    /**
     * dice si el jugador ya escogio el pais
     * @return es true si ya esta un pais elegido
     */
    public boolean hasSelected() {
        return selected != null;
    }
    /**
     * devuelve que pais se escogio
     * @return es el pais que se escogio
     */
    public String getSelected() {
        return selected;
    }
}