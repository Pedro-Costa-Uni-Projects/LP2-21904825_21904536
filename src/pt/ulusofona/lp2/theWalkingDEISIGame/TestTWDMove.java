package pt.ulusofona.lp2.theWalkingDEISIGame;

import org.junit.Test;
import java.io.File;

import static org.junit.Assert.*;

public class TestTWDMove {
    private File ficheiro = new File("test-files/JUnit.txt");

    @Test
    public void testForaDoMapa() {
        TWDGameManager game = new TWDGameManager();
        game.startGame(ficheiro);
        boolean obtido = game.move(3,3,4,6);
        boolean esperado = false;
        assertEquals(esperado,obtido);
    }

    @Test
    public void testSafeHeavenZombieHumano() {
        TWDGameManager game = new TWDGameManager();
        game.startGame(ficheiro);
        assertTrue(game.move(5, 6, 6, 6)); //humano para safe heaven
        assertEquals((int)game.getIdsInSafeHaven().get(0),7); //verifica se esta no safe heaven
        assertFalse(game.move(6, 5, 6, 6)); //zombie para safe Heaven
    }

    @Test
    public void criancaComEspada() {
        TWDGameManager game = new TWDGameManager();
        game.startGame(ficheiro);
        assertTrue(game.move(0, 0, 1, 0)); //crinca apanha espada
        assertTrue(game.move(2, 0, 1, 0)); //zombie criança para cima de crinça com espada
        assertEquals(game.getElementId(2,0),0); //se devolver zero o zombie crinça morreu
        assertFalse(game.move(1, 0, 1, 1)); //criança com espada para cima de zombie não criança
        assertTrue(game.move(1, 0, 2, 0)); //crianca vai para o lado
        assertTrue(game.move(1, 1, 2, 0)); //zombie adulto para cima de criança com espada
    }

}
