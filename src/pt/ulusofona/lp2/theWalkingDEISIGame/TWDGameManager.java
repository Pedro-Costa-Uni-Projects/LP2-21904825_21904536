package pt.ulusofona.lp2.theWalkingDEISIGame;

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
                            if (tipo >= 5 && tipo <= 9) {
                                Humano humano = new Humano(id,tipo,nome,x,y);
                                creatures.add(humano);
                            } else {
                                Zombie zombie = new Zombie(id,tipo,nome,x,y);
                                creatures.add(zombie);
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
                            Equipamento equipamento = new Equipamento(id,tipo,x,y);
                            listaEquipamento.add(equipamento);
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
        int idCriatura = 0;
        int[] cordenadaSemiValidade = new int[2];
        Equipamento equipamentoRetirar = new Equipamento();

        //verifica se sai fora das bordas
        if(xD < 0 || yD < 0 || xD > linhaColuna[0] || yD > linhaColuna[1]) {
            return false;
        }

        //verifica se é a equipa atual a jogar
        for(Creature creature : creatures) {
            if (idEquipaAtual == 0) {
                if(creature.getTipo() >= 0 && creature.getTipo() <= 4) {
                    if (creature.getX() == xO && creature.getY() == yO) {
                        idCriatura = creature.getId();
                    }
                }
            } else {
                if(creature.getTipo() >= 5 && creature.getTipo() <= 9) {
                    if (creature.getX() == xO && creature.getY() == yO) {
                        idCriatura = creature.getId();
                    }
                }
            }
        }

        if (idCriatura == 0) {
            return false;
        }

        int[] norte = {xO,yO-1};
        int[] sul = {xO,yO+1};
        int[] este = {xO+1,yO};
        int[] oeste = {xO-1,yO};

        //verificar se a jogada destino é uma destas
        if (norte[0] == xD && norte[1] == yD) {
            cordenadaSemiValidade = norte;
        } else if (sul[0] == xD && sul[1] == yD) {
            cordenadaSemiValidade = sul;
        } else if (este[0] == xD && este[1] == yD) {
            cordenadaSemiValidade = este;
        } else if (oeste[0] == xD && oeste[1] == yD) {
            cordenadaSemiValidade = oeste;
        } else {
            return false;
        }

        /*verificar se já não existe lá um humano ou jogador
        for (Humano humano : listaHumanos) {
            if (humano.getX() == cordenadaSemiValidade[0] && humano.getY() == cordenadaSemiValidade[1]) {
                return false;
            }
        }
        for (Zombie zombie : listaZombie) {
            if (zombie.getX() == cordenadaSemiValidade[0] && zombie.getY() == cordenadaSemiValidade[1]) {
                return false;
            }
        }

        //se chegou aqui a jogada foi validada
        if(idEquipaAtual == 1) {
            for (Zombie zombie : listaZombie) {
                if (zombie.getX() == xO && zombie.getY() == yO) {
                    for (Equipamento equipamento : listaEquipamento) {
                        if (equipamento.getX() == xD && equipamento.getY() == yD) {
                            equipamentoRetirar = equipamento;
                            zombie.addEquipamentos();
                        }
                    }
                    zombie.alteraCoordenada(xD,yD);
                    idEquipaAtual = 0;
                    numeroDeJogadas++;
                    listaEquipamento.remove(equipamentoRetirar);
                }
            }
        } else {
            for (Humano humano : listaHumanos) {
                if (humano.getX() == xO && humano.getY() == yO) {
                    for (int i = 0; i < listaEquipamento.size(); i++) {
                        if (listaEquipamento.get(i).getX() == xD && listaEquipamento.get(i).getY() == yD) {
                            if(humano.getEquipamentoAtual() == null) {
                                humano.addEquipamentosAtual(listaEquipamento.get(i));
                                equipamentoRetirar = listaEquipamento.get(i);
                            } else {
                                Equipamento equipamentoDrop = humano.getEquipamentoAtual();
                                equipamentoDrop.alteraCoordenada(xO,yO);
                                listaEquipamento.add(equipamentoDrop);
                                humano.addEquipamentosAtual(listaEquipamento.get(i));
                                equipamentoRetirar = listaEquipamento.get(i);
                            }
                            humano.addEquipamentos();
                        }
                    }
                    humano.alteraCoordenada(xD,yD);
                    idEquipaAtual = 1;
                    numeroDeJogadas++;
                    listaEquipamento.remove(equipamentoRetirar);
                }
            }
        }
         */
        return true;
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

    /*public List<String> getSurvivors() {
        ArrayList<String> sobreviventes = new ArrayList<>();
        sobreviventes.add("Nr. de turnos terminados:");
        sobreviventes.add(String.valueOf(numeroDeJogadas));
        sobreviventes.add("\n");
        sobreviventes.add("OS VIVOS");
        for (Humano humano : listaHumanos) {
            sobreviventes.add(humano.getId() + " " + humano.getNome());
        }
        sobreviventes.add("\n");
        for (Zombie zombie : listaZombie) {
            sobreviventes.add(zombie.getId() + " (antigamente conhecido como " + zombie.getNome() + ")");
        }
        return sobreviventes;
    }*/
    public List<String> getGameResults() {
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
        return new ArrayList<>();
    }

    public boolean isDoorToSafeHaven(int x, int y) {
        return true;
    }

    public int getEquipmentTypeId(int equipmentId) {
        for(Equipamento equipamento : listaEquipamento) {
            if (equipamento.getId() == equipmentId) {
                return equipamento.getTipo();
            }
        }
        return 11; //ver com prof
    }

    public String getEquipmentInfo(int equipmentId) {
        return null;
    }

    public boolean saveGame(File fich) {
        return true;
    }

    public boolean loadGame(File fich) {
        return true;
    }

    public String[] popCultureExtravaganza() {
        return new String[0];
    }
}