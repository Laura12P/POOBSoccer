package domain;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * pruebas de la clase Ball
 * revisa la gravedad, el suelo y la patada al balon
 * @author Jeronimo Avila Castillo
 * @author Laura Juliana Parra Velandia
 */
public class BallTest {
    private Ball ball;

    /**
     * antes de cada prueba crea un balon quieto en el aire
     */
    @Before
    public void setUp() {
        ball = new Ball();
        ball.place(400, 200);
    }

    @Test
    public void shouldFallWithGravity() {
        ball.update(0, 880, 480);
        ball.update(0, 880, 480);
        assertTrue(ball.getHeight() < 200);
    }

    @Test
    public void shouldNotGoThroughTheGround() {
        for (int i = 0; i < 500; i++) {
            ball.update(0, 880, 480);
            assertTrue(ball.getHeight() >= 12);
        }
    }

    @Test
    public void shouldStayInsideTheWalls() {
        ball.kick(-30, 0);
        for (int i = 0; i < 200; i++) {
            ball.update(0, 880, 480);
            assertTrue(ball.getX() >= 0);
            assertTrue(ball.getX() <= 880);
        }
    }

    @Test
    public void shouldMoveRightWhenKickedRight() {
        ball.kick(10, 7);
        ball.update(0, 880, 480);
        assertTrue(ball.getX() > 400);
        assertTrue(ball.getHeight() > 200);
    }

    @Test
    public void shouldMoveLeftWhenKickedLeft() {
        ball.kick(-10, 7);
        ball.update(0, 880, 480);
        assertTrue(ball.getX() < 400);
    }
}