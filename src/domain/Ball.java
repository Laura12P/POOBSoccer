package domain;

/**
 * el balon del partido
 * cae con la gravedad, rebota en el suelo, el techo y las paredes
 * y rebota cuando choca con un jugador
 * @author Jeronimo Avila Castillo
 * @author Laura Juliana Parra Velandia
 */
public class Ball {
    private static final int RADIUS = 12;
    private static final double GRAVITY = 0.25;
    private static final double BOUNCE = 0.8;
    private static final double FRICTION = 0.99;
    private double x;
    private double height;
    private double speedX;
    private double speedY;

    /**
     * CONSTRUCTOR
     * crea el balon quieto en el suelo
     */
    public Ball() {
        x = 0;
        height = RADIUS;
        speedX = 0;
        speedY = 0;
    }

    /**
     * pone el balon en un lugar y lo deja quieto
     * @param x es la posicion horizontal del centro
     * @param height es la altura del centro sobre el suelo
     */
    public void place(double x, double height) {
        this.x = x;
        this.height = height;
        speedX = 0;
        speedY = 0;
    }

    /**
     * mueve el balon un paso: gravedad y rebotes
     * @param left es el limite de la izquierda
     * @param right es el limite de la derecha
     * @param ceiling es la altura maxima
     */
    public void update(int left, int right, int ceiling) {
        speedY = speedY - GRAVITY;
        x = x + speedX;
        height = height + speedY;
        if (height <= RADIUS) {
            height = RADIUS;
            speedY = -speedY * BOUNCE;
            speedX = speedX * FRICTION;
            if (speedY < 1) {
                speedY = 0;
            }
        }
        if (height >= ceiling - RADIUS) {
            height = ceiling - RADIUS;
            speedY = -speedY * BOUNCE;
        }
        if (x <= left + RADIUS) {
            x = left + RADIUS;
            speedX = -speedX * BOUNCE;
        }
        if (x >= right - RADIUS) {
            x = right - RADIUS;
            speedX = -speedX * BOUNCE;
        }
    }

    /**
     * si el balon toca al jugador, lo saca de ahi y lo manda hacia afuera
     * el jugador se toma como un circulo de radio 45
     * @param player es el jugador con el que se revisa el choque
     */
    public void collideWithPlayer(Player player) {
        double[] heights = {20, 65};
        double[] radii = {20, 25};
        for (int i = 0; i < 2; i++) {
            double centerX = player.getX();
            double centerY = player.getHeight() + heights[i];
            double dx = x - centerX;
            double dy = height - centerY;
            double distance = Math.sqrt(dx * dx + dy * dy);
            double minimum = radii[i] + RADIUS;
            if (distance < minimum && distance > 0) {
                double nx = dx / distance;
                double ny = dy / distance;
                x = centerX + nx * minimum;
                height = centerY + ny * minimum;
                double speed = Math.sqrt(speedX * speedX + speedY * speedY);
                if (speed < 3) {
                    speed = 3;
                }
                if (speed > 8) {
                    speed = 8;
                }
                speedX = nx * speed;
                speedY = ny * speed;
            }
        }
    }
    /**
     * le da una patada al balon
     * @param speedX es la velocidad horizontal del balon cuando se da la patada
     * @param speedY es la velocidad vertical del balon cuando se da la patada
     */
    public void kick(double speedX, double speedY) {
        this.speedX = speedX;
        this.speedY = speedY;
    }

    /**
     * @return la posicion horizontal del centro del balon
     */
    public double getX() {
        return x;
    }

    /**
     * @return la altura del centro del balon sobre el suelo
     */
    public double getHeight() {
        return height;
    }
}