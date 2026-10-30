

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 * cancha de juego
 * @author Jeronimo Avila Castillo
 * @author Laura Juliana Parra Velandia
 */
public class PanelCancha extends JPanel {
    private static final Color PASTO = new Color(40, 120, 60);
    private static final Color LINEAS = Color.WHITE;
    private static final Color COLOR_UNO = new Color(230, 190, 40);
    private static final Color COLOR_DOS = new Color(200, 40, 40);
    private static final int MARGEN_SUELO = 40;
    private static final int ANCHO_ARCO = 60;
    private static final int ALTO_ARCO = 160;
    /**
     * CONSTRUCTOR 
     * hace la cancha con un fondo verde
     */
    public PanelCancha() {
        setBackground(PASTO);
    }
    /**
    * este metodo lo llaman cada vez que necesitan pintar el panel
    * @param g es el pincel con el que se dibuja sobre el panel
    */
    protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    int ancho = getWidth();
    int suelo = getHeight() - MARGEN_SUELO;
    dibujarLineas(g, ancho, suelo);
    dibujarArcos(g, ancho, suelo);
    dibujarJugador(g, ancho / 4, suelo, COLOR_UNO);
    dibujarJugador(g, ancho * 3 / 4, suelo, COLOR_DOS);
    dibujarBalon(g, ancho / 2, suelo - 150);
    }
    /**
    * el encargado de dibujar las lineas del suelo
    * @param g es el pincel
    * @param ancho es el acho del panel
    * @param suelo es la altura de donde esta el suelo
    */
    private void dibujarLineas(Graphics g, int ancho, int suelo) {
    g.setColor(LINEAS);
    g.fillRect(0, suelo, ancho, 4);
    g.fillRect(ancho / 2 - 2, 0, 4, suelo);
    g.drawOval(ancho / 2 - 50, suelo / 2 - 50, 100, 100);
    }
    /**
    * se dibuja los arcos
    * @param g es el pincel
    * @param ancho ancho actual del panel
    * @param suelo es la altura del suelo
    */
   private void dibujarArcos(Graphics g, int ancho, int suelo){
    int arriba = suelo - ALTO_ARCO;
    int inicioDerecho = ancho - ANCHO_ARCO - 1;
    g.setColor(LINEAS);
    g.drawRect(0, arriba, ANCHO_ARCO, ALTO_ARCO);
    g.drawRect(inicioDerecho, arriba, ANCHO_ARCO, ALTO_ARCO);
    for (int y = arriba + 20; y < suelo; y += 20) {
        g.drawLine(0, y, ANCHO_ARCO, y);
        g.drawLine(inicioDerecho, y, ancho - 1, y);
    }
   }
   /**
    * dibuja un jugador sobre el campo
    * @param g es el pincel
    * @param x es la posicion horizontal
    * @param suelo  es la altura donde esta el suelo
    * @param color es el color de la camiseta del jugador
    */
   private void dibujarJugador(Graphics g, int x, int suelo, Color color) {
    g.setColor(color);
    g.fillRect(x - 15, suelo - 40, 30, 40);
    g.fillOval(x - 25, suelo - 90, 50, 50);
    g.setColor(Color.BLACK);
    g.drawOval(x - 25, suelo - 90, 50, 50);
   }
   /**
    * es el que dibuja el balon 
    * @param g es el pincel
    * @param x es la posicion horizontal del centro del balon
    * @param y es la posicion vertical del centro del balon
    */
   private void dibujarBalon(Graphics g, int x, int y) {
    g.setColor(Color.WHITE);
    g.fillOval(x - 12, y - 12, 24, 24);
    g.setColor(Color.BLACK);
    g.drawOval(x - 12, y - 12, 24, 24);
   }
}   
