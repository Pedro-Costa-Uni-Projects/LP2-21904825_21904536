package pt.ulusofona.lp2.theWalkingDEISIGame;

import org.junit.Test;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class TestTWDMove {
    private File ficheiro = new File("test-files/JUnit.txt");

    @Test
    public void testForaDoMapa() throws InvalidTWDInitialFileException, FileNotFoundException {
        TWDGameManager game = new TWDGameManager();
        game.startGame(ficheiro);
        boolean obtido = game.move(3,3,4,6);
        boolean esperado = false;
        assertEquals(esperado,obtido);
    }

    @Test
    public void testSafeHeavenZombieHumano() throws InvalidTWDInitialFileException, FileNotFoundException {
        TWDGameManager game = new TWDGameManager();
        game.startGame(ficheiro);
        assertTrue(game.move(5, 6, 6, 6)); //humano para safe heaven
        assertEquals((int)game.getIdsInSafeHaven().get(0),7); //verifica se esta no safe heaven
        assertFalse(game.move(6, 5, 6, 6)); //zombie para safe Heaven
    }

    @Test
    public void criancaComEspada() throws InvalidTWDInitialFileException, FileNotFoundException {
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
    public void ZombieVsAlhoVsDia() throws InvalidTWDInitialFileException, FileNotFoundException {
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

    @Test
    public void IdosoComArma() throws InvalidTWDInitialFileException, FileNotFoundException {
        TWDGameManager game = new TWDGameManager();
        game.startGame(ficheiro); //já agora testa-se o start game
        assertTrue(game.move(3, 3, 3, 4)); //idoso para cima de Arma
        assertTrue(game.move(0, 4, 3, 4)); //zombie para cima de idoso com arma
        assertEquals(game.getEquipmentInfo(-3),"Pistola Walther PPK | 2"); //verifica se gastou bala
        assertFalse(game.move(3, 4, 3, 3)); //idoso não se pode mover de noite
        assertTrue(game.move(0, 3, 1, 4)); //jogada normal vivos
        assertTrue(game.move(6, 5, 4, 5)); //jogada normal zombie
        assertTrue(game.move(3, 4, 4, 4)); //idoso move-se para o lado, dropa arma
        assertEquals(game.getElementId(3, 4),-3); //se devolver -3 o idoso dropou arma com sucesso
        assertTrue(game.move(4, 5, 4, 4)); //zombie transforma idoso pois já não tem arma
    }

    @Test
    public void ZombieVsCao() throws InvalidTWDInitialFileException, FileNotFoundException {
        TWDGameManager game = new TWDGameManager();
        game.startGame(ficheiro);
        assertFalse(game.move(0, 3, 1, 3)); //cao so pode mover nas diagonais
        assertTrue(game.move(0, 3, 1, 4)); //cao move-se
        assertFalse(game.move(0, 4, 1, 4)); //zombie tenta atacar cão
    }

    @Test
    public void MilitarWoodenShield() throws InvalidTWDInitialFileException, FileNotFoundException {
        TWDGameManager game = new TWDGameManager();
        game.startGame(ficheiro);
        assertTrue(game.move(3, 6, 3, 5)); //Militar apanha escudo
        assertEquals(game.getEquipmentInfo(-4),"Escudo de Madeira | 2");
    }

    @Test
    public void saveGame() throws InvalidTWDInitialFileException, FileNotFoundException {
        File ficheiroSave = new File("SavedGame.txt");
        TWDGameManager game = new TWDGameManager();
        game.startGame(ficheiro);
        assertTrue(game.move(0, 0, 1, 0)); //criança para espada
        assertTrue(game.move(2, 0, 1, 0)); //zombie crianca para crianca com espada
        assertTrue(game.move(5, 6, 6, 6)); //adulto vivo para safe heaven
        assertTrue(game.saveGame(ficheiroSave)); //guarda
        assertTrue(game.move(6, 0, 4, 0)); //move vampiro durante noite para depois da rollback com o load
        assertTrue(game.loadGame(ficheiroSave));
        assertFalse(game.move(2, 0, 3, 0)); //devo devolver false pois a criança zombie morreu
        assertEquals((long)game.getIdsInSafeHaven().get(0),7); //ve se o unico id no safe heaven é do adulto humano
                                                                //CAST PARA LONG POIS ESTAVA A DAR ERRO SEM ISSO
        assertEquals(game.getCurrentTeamId(),20); //verifica se são os outros a jogar
        //apesar de o id da crinca ser 6 na lista de creaturas é get(5) pois começa no zero
        assertEquals(game.getCreatures().get(5).getEquipamentoAtual().getTipo(),1); //ve se tem uma espada a crinca
        assertEquals(game.getCreatures().get(5).getNumEquipamentos(),1); //ve se apanhou só um equipamento
    }

    @Test
    public void resultsGameAndOthers() throws InvalidTWDInitialFileException, FileNotFoundException {
        TWDGameManager game = new TWDGameManager();
        game.startGame(ficheiro);
        assertTrue(game.move(0, 0, 1, 0)); //criança para espada
        assertTrue(game.move(2, 0, 1, 0)); //zombie crianca para crianca com espada
        assertTrue(game.move(0, 3, 1, 2)); //cao para veneno
        assertTrue(game.move(1, 1, 1, 0)); //zombie adulto para crinça com espada
        assertTrue(game.move(5, 6, 6, 6)); //humano adulto para safe heaven
        assertTrue(game.move(1, 1, 3, 3)); //adulto zombie para idoso humano
        assertTrue(game.move(3, 6, 6, 6)); //militar adulto para safe heaven
        assertTrue(game.gameIsOver()); //não há mais humano em jogo deve acabar
        assertEquals(game.getGameResults().size(),29);
    }

    @Test
    public void gameStatistics () throws  InvalidTWDInitialFileException, FileNotFoundException {
        TWDGameManager game = new TWDGameManager();
        game.startGame(ficheiro);
        assertTrue(game.move(0, 0, 1, 0)); //criança para espada
        assertTrue(game.move(2, 0, 1, 0)); //zombie crianca para crianca com espada
        assertTrue(game.move(0, 3, 1, 2)); //cao para veneno
        assertTrue(game.move(0, 4, 3, 4)); //militar zombie destroi pistola
        assertTrue(game.move(3, 6, 3, 5)); //militar humano para escudo
        assertFalse(game.move(3, 4, 3, 6)); //militar zombie saltar por cima - erro
        assertTrue(game.move(3, 4, 3, 5)); //militar zombie para militar com estudo
        assertTrue(game.move(3, 5, 5, 5)); //militar humano movimento
        assertTrue(game.move(1, 1, 1, 0)); //zombie adulto ataca crinca com espada
        assertTrue(game.move(3, 3, 4, 3)); //idoso adulto movimento
        assertTrue(game.move(6, 5, 5, 6)); //tranformação
        assertTrue(game.move(5, 5, 6, 6)); //varandas para safe heaven
        assertTrue(game.move(3, 4, 4, 3)); //militar zombie para idoso
        Map<String, List<String>> obtido = game.getGameStatistics();
        assertEquals(obtido.get("os3ZombiesMaisTramados").size(),3);
        assertEquals(obtido.get("os3VivosMaisDuros").size(),0);
        //falta tiposDeEquipamentoMaisUteis
        //falta tiposDeZombieESeusEquipamentosDestruidos
        assertEquals(obtido.get("criaturasMaisEquipadas").size(),5);
    }
}
