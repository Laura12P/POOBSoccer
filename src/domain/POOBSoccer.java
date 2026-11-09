package domain;
/**
 * fachada del juego
 * usando patron de fachada
 * unica clase que tiene comunicacion
 * con presentacion
 * guarda los dos jugadores, el balon, el tiempo
 * y es encargado de marcar los goles y dice cual 
 * es el ganador
 * @author Jeronimo Avila Castillo
 * @author Laura Juliana Parra Velandia
 */
public class POOBSoccer {
    public static final int GOAL_WIDTH = 60;
    public static final int GOAL_HEIGHT = 160;
    private static final int SIDE_MARGIN = 25;
    private static final int START_ONE = 220;
    private static final int START_TWO = 660;
    private static final int BALL_START_X = 440;
    private static final int BALL_START_HEIGHT = 200;
    private static final int BALL_RADIUS = 12;
    private static final int MATCH_SECONDS = 60;
    private static final int PLAYER_WIDTH = 50;
    private static final int PLAYER_HEIGHT = 90;
    private static final int FOOT_REACH = 35;
    private static final int FOOT_HEIGHT = 15;
    private static final int KICK_RANGE = 30;
    private static final double KICK_SPEED_X = 10;
    private static final double KICK_SPEEED_Y = 7; 

    private Player playerOne;
    private Player playerTwo;
    private Ball ball;
    private int width;
    private int height;
    private int seconds;
    private boolean goldenGoal;
    private boolean over;

    /**
     * CONSTRUCTOR
     * prepara el partido con los jugadores en su lugar
     * @param one es el jugador del lado izq
     * @param two es el jugador del lado der
     * @param width es el ancho de la cancha
     * @param height es la altura de la cancha
     */
    public POOBSoccer(Player one, Player two, int width, int height){
        playerOne = one;
        playerTwo = two;
        this.width = width;
        this.height = height;
        ball = new Ball();
        seconds = MATCH_SECONDS;
        goldenGoal = false;
        over = false;
        reset();
    }
    /**
     * mueve a la izq al jugador
     * @param number es 1 el jugador izq, y 2 el de la der
     */
    public void moveLeft(int number){
        getPlayer(number).moveLeft(SIDE_MARGIN);
    }
    /**
     * mueve a la der el jugador indicado
     * @param number es 1 el jugador izq, y 2 el de la der
     */
    public void moveRight(int number){
        getPlayer(number).moveRight(width - SIDE_MARGIN);
    }
    /**
     * salta el jugador que se indique
     */
    public void jump(int number){
        getPlayer(number).jump();
    }
    /**
     * hace que un jugador que esta indicado estire el pie para patear
     * @param number es 1 para el jugador izq y 2 para el jugador der
     */
    public void kick(int number) {
        getPlayer(number).startKick();
    }

    /** 
     * mueve a los jugadores y al balon si hubo gol
     * cuando hacen gol todos vuelven a la posicion inicial
     */
    public boolean update() {
        boolean goal = false;
        playerOne.update();
        playerTwo.update();
        collidePlayers();
        ball.collideWithPlayer(playerOne);
        ball.collideWithPlayer(playerTwo);
        checkKick(playerOne, playerTwo, 1);
        checkKick(playerTwo, playerOne, -1);
        ball.update(0, width, height);
        if (ball.getX()+ BALL_RADIUS <= GOAL_WIDTH && ball.getHeight() + BALL_RADIUS <= GOAL_HEIGHT){
            playerTwo.addGoal();
            goal = true;
        } else if (ball.getX() - BALL_RADIUS >= width - GOAL_WIDTH - 1 && ball.getHeight() + BALL_RADIUS <= GOAL_HEIGHT){
            playerOne.addGoal();
            goal = true;
        }
        if (goal) {
            reset();
            if (goldenGoal){
                over = true;
            }
        }
        return goal;
    }
    /**
     * le va quitando segundos al partido
     * si el tiempo llega a cero,y van empatados arranca el gol de oro
     */
    public void passSecond(){
        if (!over && !goldenGoal && seconds > 0){
            seconds = seconds -1;
            if (seconds == 0){
                if (playerOne.getGoals() == playerTwo.getGoals()){
                    goldenGoal = true;
                } else {
                    over = true;
                }
            }
        }
    }
    /**
     * retorna el jugador que va ganando
     */
    public Player getWinner(){
        if (playerOne.getGoals() > playerTwo.getGoals()){
            return playerOne;
        }
        return playerTwo;
    }
    /**
     * retorna el jugador de la izd
     */
    public Player getPlayerOne(){
        return playerOne;
    }
    /**
     * retorna el jugador de la der
     */
    public Player getPlayerTwo() {
        return playerTwo;
    }
    /**
     * retorna el balon del partido
     */
    public Ball getBall(){
        return ball;
    }
    /**
     * retorna los segundos que le quedan a la pertida
     */
    public int getSeconds(){
        return seconds;
    }
    /**
     * retorna si esta en gol de oro
     */
    public boolean isGoldenGoal() {
        return goldenGoal;
    }
    /**
     * retorna true si se acabo el partido
     */
    public boolean isOver() {
        return over;
    }
    private Player getPlayer(int number){
        if (number == 1){
            return playerOne;
        }
        return playerTwo;
    }
    /**
     * cuando los jugadores se tocan, se separan para que no se atraviesen
     * si pasa por encima no se choca
     */
    private void collidePlayers() {
        int heightDifference = Math.abs(playerOne.getHeight() - playerTwo.getHeight());
        if (heightDifference < PLAYER_HEIGHT) {
            if (playerOne.getX() <= playerTwo.getX()){
                separate(playerOne, playerTwo);
            } else {
                separate(playerTwo, playerOne);
            }
        }
    }
    /**
     * separa cuando los jugadores estan muy cerca
     * si estan contra la paren a la mitad
     * @param left es el jugador que esta mas a la izq
     * @param right es el jugador que esta a la der
     */
    private void separate(Player left, Player right) {
        int overlap = PLAYER_WIDTH - (right.getX() - left.getX());
        if (overlap > 0) {
            int half = overlap / 2;
            left.push(-half, SIDE_MARGIN, width - SIDE_MARGIN);
            right.push(overlap - half, SIDE_MARGIN, width - SIDE_MARGIN);
            overlap = PLAYER_WIDTH - (right.getX() - left.getX());
            if (overlap > 0) {
                left.push(-overlap, SIDE_MARGIN, width - SIDE_MARGIN);
            }   
        }
    }
    /**
     * revisa si el pide del jugador que patea toca el balon
     * o en su defecto toca al rival
     * @param kicker es el jugador que patea
     * @param rival es el otro jugador
     * @param direction es 1 si patea a der, y -1 si patea a izq
     */
    private void checkKick(Player kicker, Player rival, int direction) {
        if (kicker.canHit()) {
            double footX = kicker.getX() + direction * FOOT_REACH;
            double footY = kicker.getHeight() + FOOT_HEIGHT;
            double dx = ball.getX() - footX;
            double dy = ball.getHeight() - footY;
            if (Math.sqrt(dx * dx + dy * dy) < KICK_RANGE){
                ball.kick(direction * KICK_SPEED_X, KICK_SPEEED_Y);
                kicker.useKick();
            } else if (Math.abs(rival.getX() - footX) < PLAYER_WIDTH / 2
                    && Math.abs(rival.getHeight() - kicker.getHeight()) < PLAYER_HEIGHT){
                rival.receiveDamage(kicker.getKickDamage());
                kicker.useKick();
            } 
        }
    }
    
    
    /**
     * pone a los jugadores y el balon en la salida
     */
    private void reset(){
        playerOne.place(START_ONE);
        playerTwo.place(START_TWO);
        ball.place(BALL_START_X, BALL_START_HEIGHT);
    }
}
