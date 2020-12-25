package pt.ulusofona.lp2.theWalkingDEISIGame;

import pt.ulusofona.lp2.theWalkingDEISIGame.equipamentos.*;
import pt.ulusofona.lp2.theWalkingDEISIGame.humanos.*;
import pt.ulusofona.lp2.theWalkingDEISIGame.zombies.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
//
public class TWDGameManager {
    private int[] linhaColuna = new int[2];
    private int idEquipaStart;
    private int idEquipaAtual;
    private int criaturasJogo;
    private int equipamentosJogo;
    private int numeroDeJogadas;
    private int saveHeavenJogo;
    private ArrayList<Creature> creatures = new ArrayList<>();
    private ArrayList<Equipamento> listaEquipamento = new ArrayList<>();
    private ArrayList<SaveHeaven> listaSaveHeaven = new ArrayList<>();
    private static final int ID_EQUIPA_OS_VIVOS = 10;
    private static final int ID_EQUIPA_OS_OUTROS = 20;

    public boolean startGame(File ficheiroInicial) {

        int count = 0;
        String linha;
        String[] dados;
        try {
            Scanner leitorFicheiro = new Scanner(ficheiroInicial);
            while(leitorFicheiro.hasNextLine()) {
                switch (count) {
                    case 0:
                        linha = leitorFicheiro.nextLine();
                        dados = linha.split(" ");
                        linhaColuna[0] = Integer.parseInt(dados[0]);
                        linhaColuna[1] = Integer.parseInt(dados[1]);
                        count++;
                        break;
                    case 1:
                        linha = leitorFicheiro.nextLine();
                        idEquipaStart = Integer.parseInt(linha);
                        idEquipaAtual = idEquipaStart;
                        count++;
                        break;
                    case 2:
                        linha = leitorFicheiro.nextLine();
                        criaturasJogo = Integer.parseInt(linha);
                        count++;
                        break;
                    case 3:
                        for (int i = 0;i<criaturasJogo;i++) {
                            linha = leitorFicheiro.nextLine();
                            dados = linha.split(" : ");
                            int id = Integer.parseInt(dados[0]);
                            int tipo = Integer.parseInt(dados[1]);
                            String nome = dados[2];
                            int x = Integer.parseInt(dados[3]);
                            int y = Integer.parseInt(dados[4]);
                            switch (tipo) {
                                case 0:
                                    CriancaZ criancaZ = new CriancaZ(id,tipo,nome,x,y);
                                    creatures.add(criancaZ);
                                    break;
                                case 1:
                                    AdultoZ adultoZ = new AdultoZ(id,tipo,nome,x,y);
                                    creatures.add(adultoZ);
                                    break;
                                case 2:
                                    MilitarZ militarZ = new MilitarZ(id,tipo,nome,x,y);
                                    creatures.add(militarZ);
                                    break;
                                case 3:
                                    IdosoZ idosoZ = new IdosoZ(id,tipo,nome,x,y);
                                    creatures.add(idosoZ);
                                    break;
                                case 4:
                                    VampiroZ vampiroZ = new VampiroZ(id,tipo,nome,x,y);
                                    creatures.add(vampiroZ);
                                    break;
                                case 5:
                                    CriancaH criancaH = new CriancaH(id,tipo,nome,x,y);
                                    creatures.add(criancaH);
                                    break;
                                case 6:
                                    AdultoH adultoH = new AdultoH(id,tipo,nome,x,y);
                                    creatures.add(adultoH);
                                    break;
                                case 7:
                                    MilitarH militarH = new MilitarH(id,tipo,nome,x,y);
                                    creatures.add(militarH);
                                    break;
                                case 8:
                                    IdosoH idosoH = new IdosoH(id,tipo,nome,x,y);
                                    creatures.add(idosoH);
                                    break;
                                case 9:
                                    CaoH caoH = new CaoH(id,tipo,nome,x,y);
                                    creatures.add(caoH);
                                    break;
                            }
                        }
                        count++;
                        break;
                    case 4:
                        linha = leitorFicheiro.nextLine();
                        equipamentosJogo = Integer.parseInt(linha);
                        count++;
                        break;
                    case 5:
                        for (int i = 0;i<equipamentosJogo;i++) {
                            linha = leitorFicheiro.nextLine();
                            dados = linha.split(" : ");
                            int id = Integer.parseInt(dados[0]);
                            int tipo = Integer.parseInt(dados[1]);
                            int x = Integer.parseInt(dados[2]);
                            int y = Integer.parseInt(dados[3]);
                            switch (tipo) {
                                case 0:
                                    EscudoMadeira escudoMadeira = new EscudoMadeira(id,tipo,x,y);
                                    listaEquipamento.add(escudoMadeira);
                                    break;
                                case 1:
                                    EspadaHanzo espadaHanzo = new EspadaHanzo(id,tipo,x,y);
                                    listaEquipamento.add(espadaHanzo);
                                    break;
                                case 2:
                                    PistolaPPK pistolaPPK = new PistolaPPK(id,tipo,x,y);
                                    listaEquipamento.add(pistolaPPK);
                                    break;
                                case 3:
                                    EscudoTactico escudoTactico = new EscudoTactico(id,tipo,x,y);
                                    listaEquipamento.add(escudoTactico);
                                    break;
                                case 4:
                                    RevistaMaria revistaMaria = new RevistaMaria(id,tipo,x,y);
                                    listaEquipamento.add(revistaMaria);
                                    break;
                                case 5:
                                    CabecaAlho cabecaAlho = new CabecaAlho(id,tipo,x,y);
                                    listaEquipamento.add(cabecaAlho);
                                    break;
                                case 6:
                                    EstacaMadeira estacaMadeira = new EstacaMadeira(id,tipo,x,y);
                                    listaEquipamento.add(estacaMadeira);
                                    break;
                                case 7:
                                    GarrafaLixivia garrafaLixivia = new GarrafaLixivia(id,tipo,x,y);
                                    listaEquipamento.add(garrafaLixivia);
                                    break;
                                case 8:
                                    Veneno veneno = new Veneno(id,tipo,x,y);
                                    listaEquipamento.add(veneno);
                                    break;
                                case 9:
                                    Antidoto antidoto = new Antidoto(id,tipo,x,y);
                                    listaEquipamento.add(antidoto);
                                    break;
                                case 10:
                                    BeskarHelmet beskarHelmet = new BeskarHelmet(id,tipo,x,y);
                                    listaEquipamento.add(beskarHelmet);
                                    break;
                            }
                        }
                        count++;
                        break;
                    case 6:
                        linha = leitorFicheiro.nextLine();
                        saveHeavenJogo = Integer.parseInt(linha);
                        count++;
                        break;
                    case 7:
                        for (int i = 0;i<saveHeavenJogo;i++) {
                            linha = leitorFicheiro.nextLine();
                            dados = linha.split(" : ");
                            int x = Integer.parseInt(dados[0]);
                            int y = Integer.parseInt(dados[1]);
                            SaveHeaven saveHeaven = new SaveHeaven(x,y);
                            listaSaveHeaven.add(saveHeaven);
                        }
                        count++;
                        break;
                }
            }
            leitorFicheiro.close();
        }
        catch(FileNotFoundException exception) {
            System.out.println("Erro: o ficheiro nao foi encontrado.");
            return false;
        }
        return true;
    }

    public int[] getWorldSize() {
        return linhaColuna;
    }

    public int getInitialTeam() {
        return idEquipaStart;
    }

    public List<Creature> getCreatures() {
        return creatures;
    }

    public boolean move(int xO, int yO, int xD, int yD) {
        Creature zombieARemover = null;
        int idCriatura = 0;
        Equipamento equipamentoRemove = null;
        Equipamento equipamentoDrop = null;

        //verifica se sai fora das bordas
        if(xD < 0 || yD < 0 || xD > linhaColuna[0] || yD > linhaColuna[1]) {
            return false;
        }

        //verifica se é a equipa atual a jogar
        for(Creature creature : creatures) {
            if (idEquipaAtual == ID_EQUIPA_OS_VIVOS) {
                if(creature.getTipo() >= 5 && creature.getTipo() <= 9) {
                    if (creature.getX() == xO && creature.getY() == yO) {
                        idCriatura = creature.getId();
                    }
                }
            } else {
                if(creature.getTipo() >= 0 && creature.getTipo() <= 4) {
                    if (creature.getX() == xO && creature.getY() == yO) {
                        idCriatura = creature.getId();
                    }
                }
            }
        }
        if (idCriatura == 0) {
            return false;
        }

        //HUMANOS A JOGAR
        if(idEquipaAtual == ID_EQUIPA_OS_VIVOS) {
            for(Creature creature : creatures) {
                if(creature.getTipo() >= 5 && creature.getTipo() <= 9 ) { //verifica se é humano
                    if(creature.getX() == xO && creature.getY() == yO) {
                        if(creature.movimento(xO,yO,xD,yD)) {
                            for(Creature creature1 : creatures) {
                                if(creature1.getX() == xD && creature1.getY() == yD) {
                                    if(creature1.getTipo() >= 5 && creature1.getTipo() <= 9) {
                                        return false;
                                    }
                                    if(creature1.getTipo() >= 0 && creature1.getTipo() <= 4) {
                                        if(creature.getEquipamentoAtual() != null) {
                                            if(creature.getEquipamentoAtual().isOfensivo()) {
                                                zombieARemover = creature1;
                                            } else {
                                                return false;
                                            }
                                        } else {
                                            return false;
                                        }
                                    }
                                }
                            }
                            for(Equipamento equipamento : listaEquipamento) {
                                if(equipamento.getX() == xD && equipamento.getY() == yD) {
                                    if(creature.getEquipamentoAtual() == null) {
                                        ((Humano)creature).setEquipamentosAtual(equipamento);
                                    } else {
                                        equipamentoDrop = creature.getEquipamentoAtual();
                                        equipamentoDrop.alteraCoordenada(xO,yO);
                                        ((Humano)creature).setEquipamentosAtual(equipamento);
                                    }
                                    creature.addEquipamentos();
                                    equipamentoRemove = equipamento;
                                }
                            }
                           if (isDoorToSafeHaven(xD,yD)) {
                               creature.alteraEstadoSave();
                           }
                            creature.alteraCoordenada(xD,yD);
                        } else {
                            return false;
                        }

                    }
                }

            }

            if(zombieARemover != null) {
                creatures.remove(zombieARemover);
            }

            if(equipamentoRemove != null) {
                listaEquipamento.remove(equipamentoRemove);
            }
            if (equipamentoDrop != null) {
                listaEquipamento.add(equipamentoDrop);
            }
            idEquipaAtual = ID_EQUIPA_OS_OUTROS;
            numeroDeJogadas++;
            return true;

        //ZOMBIES A JOGAR
        } else {
            for(Creature creature : creatures) {
                if(creature.getTipo() >= 0 && creature.getTipo() <= 4) {
                    if(creature.getX() == xO && creature.getY() == yO) {
                        if(creature.movimento(xO,yO,xD,yD)) {
                            for(Creature creature1 : creatures) {
                                if(creature1.getX() == xD && creature1.getY() == yD) {
                                    if(creature1.getTipo() >= 0 && creature1.getTipo() <= 4) {
                                        return false;
                                    }
                                    if(creature1.getTipo() >= 5 && creature1.getTipo() <= 9) {
                                        if(creature1.getEquipamentoAtual() == null) {
                                            zombieARemover = creature1;

                                        }
                                    }
                                }
                            }
                            for(Equipamento equipamento : listaEquipamento) {
                                if(equipamento.getX() == xD && equipamento.getY() == yD) {
                                    equipamentoRemove = equipamento;
                                    creature.addEquipamentos();
                                }
                            }

                            if (isDoorToSafeHaven(xD,yD)) {
                                return false;
                            }
                        creature.alteraCoordenada(xD,yD);
                        } else {
                            return false;
                        }
                    }
                }
            }

            if(zombieARemover != null) {
                creatures.remove(zombieARemover);
            }

            if(equipamentoRemove != null) {
                listaEquipamento.remove(equipamentoRemove);
            }

            idEquipaAtual = ID_EQUIPA_OS_VIVOS;
            numeroDeJogadas++;
            return true;
        }
    }

    public boolean gameIsOver() {
        return numeroDeJogadas == 12;
    }

    public List<String> getAuthors() {
        ArrayList<String> autores = new ArrayList<>();
        String autor1 = "Pedro Costa";
        String autor2 = "Ângelo Bernardes";

        autores.add(autor1);
        autores.add(autor2);

        return autores;

    }

    public int getCurrentTeamId() {
        return idEquipaAtual;
    }

    public int getElementId(int x, int y) {
        for(Creature creature : creatures) {
            if (creature.getX() == x && creature.getY() == y) {
                return creature.getId();
            }
        }
        for (Equipamento equipamento : listaEquipamento) {
            if (equipamento.getX() == x && equipamento.getY() == y) {
                return equipamento.getId();
            }
        }
        return 0;
    }
    
    public List<String> getGameResults() {
        ArrayList<String> resultados = new ArrayList<>();
        resultados.add("Nr. de turnos terminados:\n");
        resultados.add(String.valueOf(numeroDeJogadas));
        resultados.add("\n");
        resultados.add("Ainda pelo bairro:\n");
        resultados.add("OS VIVOS\n");
        for (Creature humano : creatures) {
            if (humano.getTipo() >= 5 && humano.getTipo() <= 9 && humano.verificaSeEstaVivo()) {
                resultados.add(humano.getId() + " " + humano.getNome() + "\n");
            }
        }
        resultados.add("OS OUTROS\n");
        for (Creature zombie : creatures) {
            if (zombie.getTipo() >= 0 && zombie.getTipo() <= 4 && zombie.verificaSeEstaVivo()) {
                resultados.add(zombie.getId() + " " + zombie.getNome() + "\n");
            }
        }
        resultados.add("\n");
        resultados.add("Num safe haven:\n");
        resultados.add("OS VIVOS\n");
        for (Creature humano : creatures) {
            if (humano.getTipo() >= 5 && humano.getTipo() <= 9 && humano.passouSaveHeaven()) {
                resultados.add(humano.getId() + " " + humano.getNome() + "\n");
            }
        }
        resultados.add("Envenados / Destruidos\n");
        resultados.add("OS VIVOS\n");
        for (Creature humano : creatures) {
            if (humano.getTipo() >= 5 && humano.getTipo() <= 9 && !humano.verificaSeEstaVivo()) {
                resultados.add(humano.getId() + " " + humano.getNome() + "\n");
            }
        }
        resultados.add("OS OUTROS\n");
        for (Creature zombie : creatures) {
            if (zombie.getTipo() >= 0 && zombie.getTipo() <= 4 && !zombie.verificaSeEstaVivo()) {
                resultados.add(zombie.getId() + " (antigamente conhecido como " + zombie.getNome() + ")\n");
            }
        }
        return new ArrayList<>();
    }

    public boolean isDay() {
        int[] diasPossiveis = {0, 1, 4, 5, 8, 9};
        for (int dia : diasPossiveis) {
            if (dia == numeroDeJogadas) {
                return true;
            }
        }
        return false;
    }

    public int getEquipmentId(int creatureId) {
        for (Creature creature : creatures) {
            if (creature.getId() == creatureId) {
                if(creature.getEquipamentoAtual() != null) {
                    return creature.getEquipamentoAtual().getId();
                }
            }
        }
        return 0;
    }

    public List<Integer> getIdsInSafeHaven() {
        ArrayList<Integer> idsCreature = new ArrayList<>();
        for (Creature creature : creatures) {
            if (creature.passouSaveHeaven()) {
                idsCreature.add(creature.getId());
            }
        }
        return idsCreature;
    }

    public boolean isDoorToSafeHaven(int x, int y) {
        for(SaveHeaven saveHeaven : listaSaveHeaven) {
            if(saveHeaven.getX() == x && saveHeaven.getY() == y) {
                return true;
            }
        }
        return false;
    }

    public int getEquipmentTypeId(int equipmentId) {
        for(Equipamento equipamento : listaEquipamento) {
            if (equipamento.getId() == equipmentId) {
                return equipamento.getTipo();
            }
        }
        for(Creature creature : creatures) {
            if (creature.getEquipamentoAtual() != null) {
               if (creature.getEquipamentoAtual().getId() == equipmentId) {
                   return creature.getEquipamentoAtual().getTipo();
               }
            }
        }
        return -1;
    }

    public String getEquipmentInfo(int equipmentId) {
        for(Equipamento equipamento : listaEquipamento) {
            if(equipamento.getId() == equipmentId) {
                return equipamento.toString();
            }
        }
        for(Creature creature : creatures) {
            if (creature.getEquipamentoAtual() != null) {
                if (creature.getEquipamentoAtual().getId() == equipmentId) {
                    return creature.getEquipamentoAtual().toString();
                }
            }
        }
        return "";
    }

    public boolean saveGame(File fich) {
        return true;
    }

    public boolean loadGame(File fich) {
        return true;
    }

    public String[] popCultureExtravaganza() {
        String[] respostas = new String[14];
        respostas[0] = "Resident Evil";
        respostas[1] = "Evil Dead";
        respostas[2] = "La nuit a dévoré le monde";
        respostas[3] = "The Village";
        respostas[4] = "Dungeons & Dragons";
        respostas[5] = "Resident Evil";
        respostas[6] = "Mandalorianos";
        respostas[7] = "A Fúria do Dragão";
        respostas[8] = "Kill Bill";
        respostas[9] = "1978";
        respostas[10] = "James Bond";
        respostas[11] = "TWD"; //ver melhor
        respostas[12] = "Cabeça de alho chocho";
        respostas[13] = "Freddie Mercury";
        return respostas;
    }
}