package domain;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * pruebas de la fachada POOBSoccer
 * revisa los goles, tiempo, el gol de oro
 * choque entre jugadores y la patada
 * @author Jeronimo Avila Castillo
 * @author Laura Juliana Parra Velandia
 */
public class POOBSoccerTest {
    private static final int WIDTH = 880;
    private static final int HEIGHT = 480;
    private Player one;
    private Player two;
    private POOBSoccer soccer;

    /**
     * antes de cada prueba crea un partido nuevo con dos jugadores iguales
     */
    @Before
    public void setUp() {
        one = new Player("Uno", "Colombia", 3, 3, 3, "ninguno");
        two = new Player("Dos", "Brasil", 3, 3, 3, "ninguno");
        soccer = new POOBSoccer(one, two, WIDTH, HEIGHT);
    }

    /**
     * deja pasar todo el tiempo del partido
     */
    private void passAllTheTime() {
        for (int i = 0; i < 60; i++) {
            soccer.passSecond();
        }
    }

    @Test
    public void shouldStartWithSixtySecondsAndNoGoals() {
        assertEquals(60, soccer.getSeconds());
        assertEquals(0, one.getGoals());
        assertEquals(0, two.getGoals());
        assertFalse(soccer.isOver());
        assertFalse(soccer.isGoldenGoal());
    }

    @Test
    public void shouldGivePlayerTwoAGoalWhenBallEntersLeftGoal() {
        soccer.getBall().place(30, 50);
        assertTrue(soccer.update());
        assertEquals(1, two.getGoals());
        assertEquals(0, one.getGoals());
    }

    @Test
    public void shouldGivePlayerOneAGoalWhenBallEntersRightGoal() {
        soccer.getBall().place(WIDTH - 30, 50);
        assertTrue(soccer.update());
        assertEquals(1, one.getGoals());
        assertEquals(0, two.getGoals());
    }

    @Test
    public void shouldNotScoreWhenBallIsInTheMiddle() {
        assertFalse(soccer.update());
        assertEquals(0, one.getGoals());
        assertEquals(0, two.getGoals());
    }

    @Test
    public void shouldPutEverythingBackAfterAGoal() {
        for (int i = 0; i < 20; i++) {
            soccer.moveRight(1);
        }
        soccer.getBall().place(30, 50);
        soccer.update();
        assertEquals(220, one.getX());
        assertEquals(660, two.getX());
        assertEquals(440, soccer.getBall().getX(), 0.01);
    }

    @Test
    public void shouldLoseOneSecondEachTime() {
        soccer.passSecond();
        soccer.passSecond();
        assertEquals(58, soccer.getSeconds());
    }

    @Test
    public void shouldEndWithAWinnerWhenTimeRunsOut() {
        soccer.getBall().place(WIDTH - 30, 50);
        soccer.update();
        passAllTheTime();
        assertTrue(soccer.isOver());
        assertFalse(soccer.isGoldenGoal());
        assertSame(one, soccer.getWinner());
    }

    @Test
    public void shouldStartGoldenGoalWhenTiedAtTheEnd() {
        passAllTheTime();
        assertTrue(soccer.isGoldenGoal());
        assertFalse(soccer.isOver());
    }

    @Test
    public void shouldEndWithTheGoldenGoal() {
        passAllTheTime();
        soccer.getBall().place(30, 50);
        soccer.update();
        assertTrue(soccer.isOver());
        assertSame(two, soccer.getWinner());
    }

    @Test
    public void shouldNotLoseTimeAfterTheEnd() {
        soccer.getBall().place(30, 50);
        soccer.update();
        passAllTheTime();
        soccer.passSecond();
        assertEquals(0, soccer.getSeconds());
    }

    @Test
    public void shouldNotLetPlayersGoThroughEachOther() {
        soccer.getBall().place(440, 400);
        for (int i = 0; i < 150; i++) {
            soccer.moveRight(1);
            soccer.moveLeft(2);
            soccer.update();
            assertTrue(two.getX() - one.getX() >= 50);
        }
    }

    @Test
    public void shouldPushRivalWhenWalkingIntoHim() {
        soccer.getBall().place(440, 400);
        int start = one.getX();
        for (int i = 0; i < 150; i++) {
            soccer.moveLeft(2);
            soccer.update();
        }
        assertTrue(one.getX() < start);
    }

    @Test
    public void shouldKickTheBallTowardsTheRivalGoal() {
        soccer.getBall().place(260, 12);
        soccer.kick(1);
        soccer.update();
        soccer.update();
        assertTrue(soccer.getBall().getX() > 260);
        assertEquals(two.getMaxLife(), two.getLife());
    }

    @Test
    public void shouldDamageTheRivalWhenTheKickMissesTheBall() {
        soccer.getBall().place(800, 400);
        for (int i = 0; i < 200; i++) {
            soccer.moveLeft(2);
            soccer.update();
        }
        soccer.kick(2);
        soccer.update();
        assertEquals(one.getMaxLife() - two.getKickDamage(), one.getLife());
    }

    @Test
    public void shouldNotDamageTheRivalWhenFarAway() {
        soccer.getBall().place(800, 400);
        soccer.kick(1);
        soccer.update();
        assertEquals(two.getMaxLife(), two.getLife());
    }
}