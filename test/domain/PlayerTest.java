package domain;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * pruebas de la clase Player
 * revisa la vida, la patada, 
 * el movimiento.
 * @author Jeronimo Avila Castillo
 * @author Laura Juliana Parra Velandia
 */
public class PlayerTest {
    private Player player;

    /**
     * antes de cada prueba crea un jugador nuevo con fuerza 3 parado en el 200
     */
    @Before
    public void setUp() {
        player = new Player("Prueba", "Colombia", 3, 3, 3, "ninguno");
        player.place(200);
    }

    @Test
    public void shouldStartWithFullLife() {
        assertEquals(player.getMaxLife(), player.getLife());
    }

    @Test
    public void shouldLoseLifeWhenDamaged() {
        player.receiveDamage(30);
        assertEquals(70, player.getLife());
    }

    @Test
    public void shouldNotHaveNegativeLife() {
        player.receiveDamage(150);
        assertEquals(0, player.getLife());
    }

    @Test
    public void shouldDoMoreDamageWithMoreStrength() {
        Player weak = new Player("Debil", "Colombia", 3, 3, 1, "ninguno");
        Player strong = new Player("Fuerte", "Colombia", 3, 3, 5, "ninguno");
        assertEquals(10, weak.getKickDamage());
        assertEquals(12, player.getKickDamage());
        assertEquals(14, strong.getKickDamage());
    }

    @Test
    public void shouldHitOnlyOncePerKick() {
        player.startKick();
        assertTrue(player.canHit());
        player.useKick();
        assertFalse(player.canHit());
        player.startKick();
        assertFalse(player.canHit());
    }

    @Test
    public void shouldStopKickingAfterSomeTime() {
        player.startKick();
        for (int i = 0; i < 20; i++) {
            player.update();
        }
        assertFalse(player.isKicking());
    }

    @Test
    public void shouldNotMovePastTheLeftLimit() {
        for (int i = 0; i < 100; i++) {
            player.moveLeft(25);
        }
        assertEquals(25, player.getX());
    }

    @Test
    public void shouldNotMovePastTheRightLimit() {
        for (int i = 0; i < 200; i++) {
            player.moveRight(855);
        }
        assertEquals(855, player.getX());
    }

    @Test
    public void shouldStayInsideTheFieldWhenPushed() {
        player.push(-500, 25, 855);
        assertEquals(25, player.getX());
        player.push(1000, 25, 855);
        assertEquals(855, player.getX());
    }

    @Test
    public void shouldGoUpAndComeBackDownWhenJumping() {
        player.jump();
        player.update();
        assertTrue(player.getHeight() > 0);
        for (int i = 0; i < 100; i++) {
            player.update();
        }
        assertEquals(0, player.getHeight());
    }

    @Test
    public void shouldCopyAsAnIndependentPlayer() {
        Player copy = player.copy();
        copy.addGoal();
        copy.receiveDamage(50);
        assertEquals(player.getName(), copy.getName());
        assertEquals(0, player.getGoals());
        assertEquals(player.getMaxLife(), player.getLife());
    }
}