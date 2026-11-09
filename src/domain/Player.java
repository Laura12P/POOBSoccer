package domain;

/**
 * Un jugador que se puede elegir para el partido
 * @author Jeronimo Thomas Avila Castillo
 * @author Laura Juliana Parra Velandia
 * Jugador
 */

public class Player {
    private static final int MAX_LIFE = 100;
    private static final int KICK_TIME = 12;
    private static final int KICK_DAMAGE = 10;

    private String name;
    private String country;
    private int speed;
    private int jump;
    private int strength;
    private String power;
    private int x;
    private int height;
    private int verticalSpeed;
    private int goals;
    private int life;
    private int kickTime;
    private boolean kickUsed;

    public Player(String name, String country, int speed, int jump, int strength, String power){
        this.name = name;
        this.country = country;
        this.speed = speed;
        this.jump = jump;
        this.strength = strength;
        this.power = power;
        life = MAX_LIFE;
    }

    /**
     * @return el nombre del jugador
     */
    public String getName(){
        return name;
    }

    /**
     * @return el pais del jugador
     */
    public String getCountry(){
        return country;
    }

    /**
     * @return la velocidad del jugador
     */
    public int getSpeed(){
        return speed;
    }
    
    /**
     * @return el salto, de 1 a 5
     */
    public int getJump() {
        return jump;
    }

    /**
     * @return la fuerza, de 1 a 5
     */
    public int getStrength() {
        return strength;
    }

    /**
     * @return el nombre del poder
     */
    public String getPower() {
        return power;
    }

    /**
     * hace una copia del jugador para usarla en un partido
     * @return es un jugador nuevo con los mismos datos
     */
    public Player copy() {
        return new Player(name, country, speed, jump, strength, power);
    }

    /**
     * pone al jugador en su lugar de salida
     * @param x es la posicion horizontal de salida
     */
    public void place(int x) {
        this.x = x;
        height = 0;
        verticalSpeed = 0;
        kickTime = 0;
    }

    /**
     * mueve al jugador a la izquierda segun su velocidad
     * @param limit es lo mas a la izquierda que puede llegar
     */
    public void moveLeft(int limit) {
        x = x - (2 + speed);
        if (x < limit) {
            x = limit;
        }
    }

    /**
     * mueve al jugador a la derecha segun su velocidad
     * @param limit es lo mas a la derecha que puede llegar
     */
    public void moveRight(int limit) {
        x = x + (2 + speed);
        if (x > limit) {
            x = limit;
        }
    }
    /**
     * empuja al jugador hacia el lado sin salir de la cancha
     * @param distance distancia que se mueve un jugador horizontalmente
     * @param left es el maximo que se puede avanzar a la izq
     * @@param right es el maximo que se puede avanzar a la der
     */
    public void push(int distance, int left, int right){
        x = x + distance;
        if (x < left){
            x = left;
        }
        if (x > right){
            x = right;
        }

    }
    /**
     * hace saltar al jugador, solo si esta en el suelo
     */
    public void jump() {
        if (height == 0 && verticalSpeed == 0) {
            verticalSpeed = 8 + 2 * jump;
        }
    }

    /**
     * actualiza el salto, la gravedad le quita 1 a la velocidad en cada paso
     */
    public void update() {
        if (height > 0 || verticalSpeed > 0) {
            height = height + verticalSpeed;
            verticalSpeed = verticalSpeed - 1;
            if (height <= 0) {
                height = 0;
                verticalSpeed = 0;
            }
        }
        if (kickTime > 0){
             kickTime = kickTime - 1;
        }
    }


    /**
     * @return la posicion horizontal del jugador
     */
    public int getX() {
        return x;
    }

    /**
     * @return cuanto esta separado del suelo
     */
    public int getHeight() {
        return height;
    }

    /**
     * le suma un gol al jugador
     */
    public void addGoal() {
        goals = goals + 1;
    }

    /**
     * @return los goles que lleva el jugador en el partido
     */
    public int getGoals() {
        return goals;
    }
    /**
     * principio de una patada si no esta pateando ya
     */
    public void startKick() {
        if (kickTime == 0) {
            kickTime = KICK_TIME;
            kickUsed = false;
        }
    }
    /**
     * retorna true si el jugador esta con el pie estirado
     */
    public boolean isKicking(){
        return kickTime > 0;
    }
    /**
     * retorna true si aun no ah pegado a nada la patada
     */
    public boolean canHit() {
        return kickTime > 0 && !kickUsed;
    }
    /**
     * la patada le esta pegando a algo
     */
    public void useKick() {
        kickUsed = true;
    }
    /**
     * punto de fuerza por encima de 1 suma un 10% mas de damage
     * retorna el multiplicador de la patada
     */
    public double getKickMultiplier() {
        return 1 + 0.10 * (strength -1);
    }
    /**
     * retorna el damage que hace una patada ak jugador
     */
    public int getKickDamage() {
        return (int) Math.round(KICK_DAMAGE * getKickMultiplier());
    }
    /**
     * se le quita la vida al jugador, pero la vida nunca baja de cero
     * @param damage es la vida que va perdiendo el jugador
     */
    public void receiveDamage(int damage) {
        life = life - damage;
        if (life < 0) {
            life = 0;
        }
    }
    /**
     * retorna la vida que le queda a un jugador
     */
    public int getLife() {
        return life;
    }
    /**
     * retorna la vida maxima que tiene el jugador
     */
    public int getMaxLife() {
        return MAX_LIFE;
    }
    
}   