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
        assertTrue(game.move(1, 1, 2, 0)); //zombie adulto para cima de criança com espada e transforma
        assertEquals(game.getElementId(2,0),6);
    }

    @Test
    public void ZombieVsAlhoVsDia() {
        TWDGameManager game = new TWDGameManager();
        game.startGame(ficheiro);
        assertTrue(game.move(0, 0, 0, 1)); //jogada normal vivos só para mudar de equipa
        assertFalse(game.move(6, 0, 5, 0)); //zombie vampiro não pode mover de dia
        assertTrue(game.move(0, 4, 3, 1)); //jogada normal zombies
        assertFalse(game.isDay()); //Deve estar de noite
        assertTrue(game.move(0, 3, 1, 4)); //jogada normal vivos
        assertFalse(game.move(6, 0, 6, 1)); //zombie vampiro para cima de alho
        assertTrue(game.move(6, 0, 5, 0));  //zombie vampiro para casa vazia e esta de noite
        assertTrue(game.isDay());
    }
}
