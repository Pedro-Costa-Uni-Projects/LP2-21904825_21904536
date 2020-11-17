package pt.ulusofona.lp2.theWalkingDEISIGame;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class TWDGameManager {
    int[] linhaColuna = new int[2];
    int idEquipaStart;
    int idEquipaAtual;
    int criaturasJogo;
    int equipamentosJogo;
    int numeroDeJogadas;
    //boolean turno; true = diurno, false = noturno.
    int dia;
    int noite;
    ArrayList<Humano> listaHumanos = new ArrayList<>();
    ArrayList<Zombie> listaZombie = new ArrayList<>();
    ArrayList<Equipamento> listaEquipamento = new ArrayList<>();

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
                            if (dados[1].equals("1")) {
                                Humano humano = new Humano(id,tipo,nome,x,y);
                                listaHumanos.add(humano);
                            } else {
                                Zombie zombie = new Zombie(id,tipo,nome,x,y);
                                listaZombie.add(zombie);
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

    public List<Humano> getHumans() {
        return listaHumanos;
    }

    public List<Zombie> getZombies() {
        return listaZombie;
    }

    public boolean move(int xO, int yO, int xD, int yD) {
        int idCriatura = 0;
        int verificaDirecao = 0;
        int[] cordenadaSemiValidade = new int[2];
        Equipamento equipamentoRetirar = new Equipamento();

        //verifica se é a equipa atual a jogar
       if (idEquipaAtual == 0) {
            for (Humano humano : listaHumanos) {
                if (humano.getX() == xO && humano.getY() == yO) {
                    idCriatura = humano.getId();
                }
            }
        } else {
            for (Zombie zombie : listaZombie) {
                if (zombie.getX() == xO && zombie.getY() == yO) {
                    idCriatura = zombie.getId();
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
        //verifica se sai fora das bordas
        if(xD < 0 || yD < 0 || xD >= linhaColuna[0] || yD >= linhaColuna[1]) {
            return false;
        }
        //verificar se a jogada destino é uma destas
        if (norte[0] == xD && norte[1] == yD) {
            verificaDirecao = 1;
        } else if (sul[0] == xD && sul[1] == yD) {
            verificaDirecao = 2;
        } else if (este[0] == xD && este[1] == yD) {
            verificaDirecao = 3;
        } else if (oeste[0] == xD && oeste[1] == yD) {
            verificaDirecao = 4;
        }
        switch (verificaDirecao) {
            case 0:
                return false;
            case 1:
               cordenadaSemiValidade = norte;
               break;
            case 2:
                cordenadaSemiValidade = sul;
                break;
            case 3:
                cordenadaSemiValidade = este;
                break;
            case 4:
                cordenadaSemiValidade = oeste;
                break;
        }
        //verificar se já não existe lá um jogador
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

        if(idEquipaAtual == 1) {
            for (Zombie zombie : listaZombie) {
                if (zombie.getX() == xO && zombie.getY() == yO) {
                    for (Equipamento equipamento : listaEquipamento) {
                        if (equipamento.getX() == xD && equipamento.getY() == yD) {
                            equipamentoRetirar = equipamento;
                            zombie.addEquipamentosDestruidos();
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
                                equipamentoDrop.alteraCoordenada(xD,yD);
                                listaEquipamento.add(equipamentoDrop);
                                humano.addEquipamentosAtual(listaEquipamento.get(i));
                                equipamentoRetirar = listaEquipamento.get(i);
                            }
                            humano.addEquipamentosApanhados();
                        }
                    }
                    humano.alteraCoordenada(xD,yD);
                    idEquipaAtual = 1;
                    numeroDeJogadas++;
                    listaEquipamento.remove(equipamentoRetirar);
                }
            }
        }
        return true;
    }

    public boolean gameIsOver() {
        return dia == 3 && noite == 3;
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
        for (Humano humano : listaHumanos) {
            if (humano.getX() == x && humano.getY() == y) {
                return humano.getId();
            }
        }
        for (Zombie zombie : listaZombie) {
            if (zombie.getX() == x && zombie.getY() == y) {
                return zombie.getId();
            }
        }
        for (Equipamento equipamento : listaEquipamento) {
            if (equipamento.getX() == x && equipamento.getY() == y) {
                return equipamento.getId();
            }
        }
        return 0;
    }

    public List<String> getSurvivors() {
        ArrayList<String> sobreviventes = new ArrayList<>();

        return sobreviventes;
    }

    public boolean isDay() {
        int[] diasPossiveis = {0, 1, 4, 5, 8, 9};

        if(numeroDeJogadas == 2 || numeroDeJogadas == 6 || numeroDeJogadas == 10) {
            dia++;
        }

        if(numeroDeJogadas == 4 || numeroDeJogadas == 8 || numeroDeJogadas == 11) {
            noite++;
        }

        for (int dia : diasPossiveis) {
            if (dia == numeroDeJogadas) {
                return true;
            }
        }
        return false;
    }

    public boolean hasEquipment(int creatureId, int equipmentTypeId) {
        for (Humano humano : listaHumanos) {
            if (humano.getId() == creatureId) {
                if(humano.getEquipamentoAtual() != null) {
                    if (humano.getEquipamentoAtual().getTipo() == equipmentTypeId) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}