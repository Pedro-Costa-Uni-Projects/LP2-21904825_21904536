package pt.ulusofona.lp2.theWalkingDEISIGame;

import org.junit.Test;
import java.io.File;

import static org.junit.Assert.assertEquals;

public class TestTWDMove {

    @Test
    public void testForaDoMapa() {
        TWDGameManager game = new TWDGameManager();
        File ficheiro = new File("test-files/Input.txt");
        game.startGame(ficheiro);
        boolean obtido = game.move(3,3,4,6);
        boolean esperado = false;
        assertEquals(esperado,obtido);
    }

    @Test
    public void testCasaOcupada() {
        TWDGameManager game = new TWDGameManager();
        File ficheiro = new File("test-files/Input.txt");
        game.startGame(ficheiro);
        boolean obtido = game.move(3,3,3,4);
        boolean esperado = false;
        assertEquals(esperado,obtido);
    }

    @Test
    public void testDiagonal() {
        TWDGameManager game = new TWDGameManager();
        File ficheiro = new File("test-files/Input.txt");
        game.startGame(ficheiro);
        boolean obtido = game.move(3,3,2,2);
        boolean esperado = false;
        assertEquals(esperado,obtido);
    }

    @Test
    public void testCoordenadaSuperiorAoPermitido() {
        TWDGameManager game = new TWDGameManager();
        File ficheiro = new File("test-files/Input.txt");
        game.startGame(ficheiro);
        boolean obtido = game.move(2,2,2,0);
        boolean esperado = false;
        assertEquals(esperado,obtido);
    }


}
