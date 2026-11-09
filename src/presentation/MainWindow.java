package presentation;
import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import domain.Player;

/**
 * Ventana principal de POOBSoccer.
 * Guarda todas las pantallas del juego y muestra una a la vez.
 *
 * @author Jeronimo Avila Castillo
 * @author Laura Juliana Parra Velandia
 * @version 1.0
 */
public class MainWindow extends JFrame {

    public static final String START = "inicio";
    public static final String OPTIONS = "opciones";
    public static final String MODE = "modo";
    public static final String COUNTRY_ONE = "paisUno";
    public static final String COUNTRY_TWO = "paisDos";
    public static final String CONTROLS_ONE = "controlesUno";
    public static final String CONTROLS_TWO = "controlesDos";
    public static final String GAME = "juego";
    public static final String PLAYER_ONE = "jugadorUno";
    public static final String COUNTRY_MACHINES = "paisMaquinas";
    public static final String PLAYER_TWO = "jugadorDos";
    public static final String PLAYER_MACHINES = "jugadorMaquinas";

    // dimensiones de la ventana
    private static final int WIDTH = 900;
    private static final int HEIGHT = 600;

    private CardLayout cards;
    private JPanel container;
    private StartPanel startPanel;
    private ModePanel modePanel;
    private CountryOnePanel countryOnePanel;
    private CountryTwoPanel countryTwoPanel;
    private ControlsPanel controlsPanelOne;
    private ControlsPanel controlsPanelTwo;
    private GamePanel gamePanel;
    private PlayerOnePanel playerOnePanel;
    private CountryTwoPanel machinesCountryPanel;
    private PlayerTwoPanel playerTwoPanel;
    private PlayerTwoPanel machinesPlayerPanel;
    private Player playerOne;
    private Player playerTwo;

    /**
     * Crea la ventana con las pantallas.
     */
    public MainWindow() {
        super("POOBSoccer");
        prepareElements();
        prepareActions();
    }

    /**
     * Define el tamano de la ventana y le agrega las pantallas.
     */
    private void prepareElements() {
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setResizable(false);
        cards = new CardLayout();
        container = new JPanel(cards);
        startPanel = new StartPanel(this);
        container.add(startPanel, START);
        modePanel = new ModePanel(this);
        container.add(modePanel, MODE);
        countryOnePanel = new CountryOnePanel(this);
        container.add(countryOnePanel, COUNTRY_ONE);
        countryTwoPanel = new CountryTwoPanel(this, false);
        container.add(countryTwoPanel, COUNTRY_TWO);
        machinesCountryPanel = new CountryTwoPanel(this, true);
        container.add(machinesCountryPanel, COUNTRY_MACHINES);
        playerTwoPanel = new PlayerTwoPanel(this, false);
        container.add(playerTwoPanel, PLAYER_TWO);
        machinesPlayerPanel = new PlayerTwoPanel(this, true);
        container.add(machinesPlayerPanel, PLAYER_MACHINES);
        container.add(countryTwoPanel, COUNTRY_TWO);
        controlsPanelOne = new ControlsPanel(this, false);
        container.add(controlsPanelOne, CONTROLS_ONE);
        controlsPanelTwo = new ControlsPanel(this, true);
        container.add(controlsPanelTwo, CONTROLS_TWO);
        gamePanel = new GamePanel(this);
        container.add(gamePanel, GAME);
        add(container);
        cards.show(container, START);
        playerOnePanel = new PlayerOnePanel(this);
        container.add(playerOnePanel, PLAYER_ONE);
    }

    /**
     * Hace que al dar clic en la X se cierre el juego.
     */
    private void prepareActions() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Cambia la pantalla que se esta mostrando.
     *
     * @param screen nombre de la pantalla
     */
    public void showScreen(String screen) {
        cards.show(container, screen);
    }

    /**
     * Pregunta al usuario si quiere salir y, si acepta, cierra el juego.
     */
    public void exit() {
        int answer = JOptionPane.showConfirmDialog(this,
                "seguro que quieres salir de POOBSoccer?", "Salir",
                JOptionPane.YES_NO_OPTION);
        if (answer == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    /**
     * Punto de entrada del programa.
     *
     * @param args no se estan usando
     */
    public static void main(String[] args) {
        MainWindow window = new MainWindow();
        window.setVisible(true);
    }

    /**
     * Muestra la pantalla de jugadores con los del pais elegido.
     *
     * @param country pais que escogio el jugador
     */
    public void choosePlayerOne(String country) {
        playerOnePanel.loadCountry(country);
        cards.show(container, PLAYER_ONE);
    }

    /**
     * Muestra la pantalla de jugadores con los de los paises elegidos.
     *
     * @param countryOne pais del primero
     * @param countryTwo pais del segundo
     * @param machines true si son maquina contra maquina
     */
    public void choosePlayerTwo(String countryOne, String countryTwo, boolean machines) {
        if (machines) {
            machinesPlayerPanel.loadCountries(countryOne, countryTwo);
            cards.show(container, PLAYER_MACHINES);
        } else {
            playerTwoPanel.loadCountries(countryOne, countryTwo);
            cards.show(container, PLAYER_TWO);
        }
    }

    /**
     * Guarda los jugadores que se escogieron para el partido.
     *
     * @param one jugador del lado izquierdo
     * @param two jugador del lado derecho
     */
    public void savePlayers(Player one, Player two) {
        playerOne = one;
        playerTwo = two;
    }

    /**
     * Prepara el partido con copias de los jugadores y muestra la cancha.
     */
    public void startMatch() {
        gamePanel.prepareMatch(playerOne.copy(), playerTwo.copy());
        cards.show(container, GAME);
        gamePanel.begin();
    }
}