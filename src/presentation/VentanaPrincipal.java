
import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Ventana principal de POOBSoccer.
 * Guarda todas las pantallas del juego y muestra una a la vez.
 *
 * @author Jeronimo Avila Castillo
 * @author Laura Juliana Parra Velandia
 * @version 1.0
 */
public class VentanaPrincipal extends JFrame {

    public static final String INICIO = "inicio";
    public static final String OPCIONES = "opciones";
    public static final String MODO = "modo";
    public static final String PAIS_UNO = "paisUno";
    public static final String PAIS_DOS = "paisDos";
    public static final String CONTROLES_UNO = "controlesUno";
    public static final String CONTROLES_DOS = "controlesDos";
    public static final String JUEGO = "juego";

    // dimensiones de la ventana
    private static final int ANCHO = 900;
    private static final int ALTO = 600;

    private CardLayout cartas;
    private JPanel contenedor;
    private PanelInicio panelInicio;
    private PanelModo panelModo;
    private PanelPaisUno panelPaisUno;
    private PanelPaisDos panelPaisDos;
    private PanelControles panelControlesUno;
    private PanelControles panelControlesDos;
    private PanelJuego panelJuego;

    /**
     * Crea la ventana con las pantallas.
     */
    public VentanaPrincipal() {
        super("POOBSoccer");
        prepararElementos();
        prepararAcciones();
    }

    /**
     * Define el tamano de la ventana y le agrega las pantallas.
     */
    private void prepararElementos() {
        setSize(ANCHO, ALTO);
        setLocationRelativeTo(null);
        setResizable(false);
        cartas = new CardLayout();
        contenedor = new JPanel(cartas);
        panelInicio = new PanelInicio(this);
        contenedor.add(panelInicio, INICIO);
        panelModo = new PanelModo(this);
        contenedor.add(panelModo, MODO);
        panelPaisUno = new PanelPaisUno(this);
        contenedor.add(panelPaisUno, PAIS_UNO);
        panelPaisDos = new PanelPaisDos(this);
        contenedor.add(panelPaisDos, PAIS_DOS);
        panelControlesUno = new PanelControles(this, false);
        contenedor.add(panelControlesUno, CONTROLES_UNO);
        panelControlesDos = new PanelControles(this, true);
        contenedor.add(panelControlesDos, CONTROLES_DOS);
        panelJuego = new PanelJuego(this);
        contenedor.add(panelJuego, JUEGO);
        add(contenedor);
        cartas.show(contenedor, INICIO);
    }

    /**
     * Hace que al dar clic en la X se cierre el juego.
     */
    private void prepararAcciones() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Cambia la pantalla que se esta mostrando.
     *
     * @param pantalla nombre de la pantalla
     */
    public void mostrar(String pantalla) {
        cartas.show(contenedor, pantalla);
    }

    /**
     * Pregunta al usuario si quiere salir y, si acepta, cierra el juego.
     */
    public void salir() {
        int respuesta = JOptionPane.showConfirmDialog(this,
                "seguro que quieres salir de POOBSoccer?", "Salir",
                JOptionPane.YES_NO_OPTION);
        if (respuesta == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    /**
     * Punto de entrada del programa.
     *
     * @param args no se estan usando
     */
    public static void main(String[] args) {
        VentanaPrincipal ventana = new VentanaPrincipal();
        ventana.setVisible(true);
    }
}