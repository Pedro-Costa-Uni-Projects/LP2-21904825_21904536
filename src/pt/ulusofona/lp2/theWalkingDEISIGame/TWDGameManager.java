package pt.ulusofona.lp2.theWalkingDEISIGame;

import pt.ulusofona.lp2.theWalkingDEISIGame.equipamentos.*;
import pt.ulusofona.lp2.theWalkingDEISIGame.humanos.*;
import pt.ulusofona.lp2.theWalkingDEISIGame.zombies.*;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//
public class TWDGameManager {
    private int[] linhaColuna = new int[2];
    private int idEquipaStart;
    private int idEquipaAtual;
    private int criaturasJogo;
    private int equipamentosJogo;
    private int numeroDeJogadas;
    private int numeroDeJogadasParaReset;
    private int saveHeavenJogo;
    private ArrayList<Creature> creatures = new ArrayList<>();
    private ArrayList<Equipamento> listaEquipamento = new ArrayList<>();
    private ArrayList<SaveHeaven> listaSaveHeaven = new ArrayList<>();
    private ArrayList<Creature> mortos = new ArrayList<>();
    Comparator<Creature> creatureComparator = Comparator.comparing(Creature::getId);
    Comparator<Equipamento> equipamentoComparator = Comparator.comparing(Equipamento::getId);
    private static final int ID_EQUIPA_OS_VIVOS = 10;
    private static final int ID_EQUIPA_OS_OUTROS = 20;


    public void startGame(File ficheiroInicial) throws InvalidTWDInitialFileException, FileNotFoundException {
        InvalidTWDInitialFileException exception = new InvalidTWDInitialFileException();
        int count = 0;
        String linha;
        String[] dados;
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
                    if (criaturasJogo < 2) {
                        exception.setNumeroCreatures(false);
                        throw exception;
                    }
                    count++;
                    break;
                case 3:
                    for (int i = 0;i<criaturasJogo;i++) {
                        linha = leitorFicheiro.nextLine();
                        dados = linha.split(" : ");
                        if (dados.length == 5) {
                            int id = Integer.parseInt(dados[0]);
                            int tipo = Integer.parseInt(dados[1]);
                            String nome = dados[2];
                            int x = Integer.parseInt(dados[3]);
                            int y = Integer.parseInt(dados[4]);
                            switch (tipo) {
                                case 0:
                                    CriancaZ criancaZ = new CriancaZ(id, tipo, nome, x, y);
                                    creatures.add(criancaZ);
                                    break;
                                case 1:
                                    AdultoZ adultoZ = new AdultoZ(id, tipo, nome, x, y);
                                    creatures.add(adultoZ);
                                    break;
                                case 2:
                                    MilitarZ militarZ = new MilitarZ(id, tipo, nome, x, y);
                                    creatures.add(militarZ);
                                    break;
                                case 3:
                                    IdosoZ idosoZ = new IdosoZ(id, tipo, nome, x, y);
                                    creatures.add(idosoZ);
                                    break;
                                case 4:
                                    VampiroZ vampiroZ = new VampiroZ(id, tipo, nome, x, y);
                                    creatures.add(vampiroZ);
                                    break;
                                case 5:
                                    CriancaH criancaH = new CriancaH(id, tipo, nome, x, y);
                                    creatures.add(criancaH);
                                    break;
                                case 6:
                                    AdultoH adultoH = new AdultoH(id, tipo, nome, x, y);
                                    creatures.add(adultoH);
                                    break;
                                case 7:
                                    MilitarH militarH = new MilitarH(id, tipo, nome, x, y);
                                    creatures.add(militarH);
                                    break;
                                case 8:
                                    IdosoH idosoH = new IdosoH(id, tipo, nome, x, y);
                                    creatures.add(idosoH);
                                    break;
                                case 9:
                                    CaoH caoH = new CaoH(id, tipo, nome, x, y);
                                    creatures.add(caoH);
                                    break;
                            }
                        } else {
                            exception.setValidoDefinicao(false);
                            exception.setLinhaFalhada(linha);
                            throw exception;
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
                        if (dados.length == 4) {
                            int id = Integer.parseInt(dados[0]);
                            int tipo = Integer.parseInt(dados[1]);
                            int x = Integer.parseInt(dados[2]);
                            int y = Integer.parseInt(dados[3]);
                            switch (tipo) {
                                case 0:
                                    EscudoMadeira escudoMadeira = new EscudoMadeira(id, tipo, x, y);
                                    listaEquipamento.add(escudoMadeira);
                                    break;
                                case 1:
                                    EspadaHanzo espadaHanzo = new EspadaHanzo(id, tipo, x, y);
                                    listaEquipamento.add(espadaHanzo);
                                    break;
                                case 2:
                                    PistolaPPK pistolaPPK = new PistolaPPK(id, tipo, x, y);
                                    listaEquipamento.add(pistolaPPK);
                                    break;
                                case 3:
                                    EscudoTactico escudoTactico = new EscudoTactico(id, tipo, x, y);
                                    listaEquipamento.add(escudoTactico);
                                    break;
                                case 4:
                                    RevistaMaria revistaMaria = new RevistaMaria(id, tipo, x, y);
                                    listaEquipamento.add(revistaMaria);
                                    break;
                                case 5:
                                    CabecaAlho cabecaAlho = new CabecaAlho(id, tipo, x, y);
                                    listaEquipamento.add(cabecaAlho);
                                    break;
                                case 6:
                                    EstacaMadeira estacaMadeira = new EstacaMadeira(id, tipo, x, y);
                                    listaEquipamento.add(estacaMadeira);
                                    break;
                                case 7:
                                    GarrafaLixivia garrafaLixivia = new GarrafaLixivia(id, tipo, x, y);
                                    listaEquipamento.add(garrafaLixivia);
                                    break;
                                case 8:
                                    Veneno veneno = new Veneno(id, tipo, x, y);
                                    listaEquipamento.add(veneno);
                                    break;
                                case 9:
                                    Antidoto antidoto = new Antidoto(id, tipo, x, y);
                                    listaEquipamento.add(antidoto);
                                    break;
                                case 10:
                                    BeskarHelmet beskarHelmet = new BeskarHelmet(id, tipo, x, y);
                                    listaEquipamento.add(beskarHelmet);
                                    break;
                            }
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

    public int[] getWorldSize() {
        return linhaColuna;
    }

    public int getInitialTeam() {
        return idEquipaStart;
    }

    public List<Creature> getCreatures() {
        ArrayList<Creature> todasAsCreatures = new ArrayList<>();
        todasAsCreatures.addAll(creatures);
        todasAsCreatures.addAll(mortos);
        todasAsCreatures.sort(creatureComparator);
        return todasAsCreatures;
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
            for(Creature humano : creatures) {
                if(humano.getTipo() >= 5 && humano.getTipo() <= 9 ) { //verifica se é humano
                    if(humano.getX() == xO && humano.getY() == yO) {
                        if(humano.movimento(xO,yO,xD,yD)) {
                            if(passarPorCima(humano, xD, yD)) {
                                return false;
                            }
                            if (humano.getTipo() == 8 && !isDay()) { //se for de noite o idosoH não pode mover
                                return false;
                            }
                            if(humano.getTipo() == 8 && humano.getEquipamentoAtual() != null) { //idoso move e dropa
                                equipamentoDrop = humano.getEquipamentoAtual();
                                ((Humano) humano).setEquipamentosAtual(null);
                            }
                            for(Creature zombie : creatures) {
                                if(zombie.getX() == xD && zombie.getY() == yD) {
                                    if(zombie.getTipo() >= 5 && zombie.getTipo() <= 9 && !zombie.passouSaveHeaven()) {
                                        return false;
                                    }
                                    if(zombie.getTipo() >= 0 && zombie.getTipo() <= 4) {
                                        if(humano.getEquipamentoAtual() != null) {
                                            if(humano.getEquipamentoAtual().isOfensivo()) {
                                                if(humano.getEquipamentoAtual().getTipo() == 2) { //Pistola
                                                    if(zombie.getTipo() == 4) {
                                                        return false;
                                                    }
                                                    if (!((PistolaPPK)humano.getEquipamentoAtual()).disparar()) {
                                                        return false;
                                                    }
                                                }
                                                if(humano.getEquipamentoAtual().getTipo() == 1 && !(zombie.getTipo() == 0)) {
                                                    if(humano.getTipo() == 5) {
                                                        return false;
                                                    }
                                                }
                                                humano.addInteracoes();
                                                zombieARemover = zombie;
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
                                    if(humano.getTipo() == 7 && equipamento.getTipo() == 0) {
                                        if(((EscudoMadeira)equipamento).mostraSeFoiUtilizado()) {
                                            ((EscudoMadeira)equipamento).alteraHit();
                                            ((EscudoMadeira)equipamento).foiUtilizadoPorMilitar();
                                        } else {
                                            ((EscudoMadeira)equipamento).alteraHit();
                                        }
                                    } else {
                                        if(equipamento.getTipo() == 0) {
                                            if(!((EscudoMadeira)equipamento).mostraSeFoiUtilizado()) {
                                                ((EscudoMadeira)equipamento).alteraHit();
                                            }

                                        }

                                    }
                                    if (equipamento.getTipo() == 9 && ((Humano) humano).estadoVeneno()) {
                                        ((Humano) humano).alteraVeneno(false);
                                        ((Humano) humano).reporTurnosPoison();
                                        if(humano.getEquipamentoAtual() == null) {
                                            ((Humano)humano).setEquipamentosAtual(equipamento);
                                        } else {
                                            equipamentoDrop = humano.getEquipamentoAtual();
                                            equipamentoDrop.alteraCoordenada(xO, yO);
                                            ((Humano) humano).setEquipamentosAtual(equipamento);
                                        }
                                    } else if (equipamento.getTipo() != 9){
                                        if(humano.getEquipamentoAtual() == null) {
                                            ((Humano)humano).setEquipamentosAtual(equipamento);
                                        } else {
                                            equipamentoDrop = humano.getEquipamentoAtual();
                                            equipamentoDrop.alteraCoordenada(xO, yO);
                                            ((Humano) humano).setEquipamentosAtual(equipamento);
                                        }

                                        if (equipamento.getTipo() == 8) {
                                            ((Humano) humano).alteraVeneno(true);
                                        }
                                    } else {
                                        return false;
                                    }
                                    humano.addEquipamentos();
                                    equipamentoRemove = equipamento;
                                }

                            }
                           if (isDoorToSafeHaven(xD,yD)) {
                               humano.alteraEstadoSave();
                           }
                           humano.alteraCoordenada(xD,yD);

                        } else {
                            return false;
                        }

                    }
                }

            }

            if(zombieARemover != null) {
                adicionaMortos(zombieARemover);
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
            numeroDeJogadasParaReset++;
            tiraTurnosVeneno();
            organizaListas();
            return true;

        //ZOMBIES A JOGAR
        } else {
            for(Creature zombie : creatures) {
                if(zombie.getTipo() >= 0 && zombie.getTipo() <= 4) {
                    if(zombie.getX() == xO && zombie.getY() == yO) {
                        if(zombie.movimento(xO,yO,xD,yD)) {
                            if(passarPorCima(zombie, xD, yD)) {
                                return false;
                            }
                            if (zombie.getTipo() == 4 && isDay()) { //se for de dia o VampiroZ não pode mover
                                return false;
                            }
                            for(Creature humano : creatures) {
                                if(humano.getX() == xD && humano.getY() == yD && !humano.verificaSaveHeaven) { //zombie nao pode matar em safeheaven
                                    if(humano.getTipo() >= 0 && humano.getTipo() <= 4) {
                                        return false;
                                    }
                                    if(humano.getTipo() >= 5 && humano.getTipo() <= 9) {
                                        if(humano.getTipo() == 9) {
                                            return false;
                                        }

                                        if(humano.getEquipamentoAtual() == null) {
                                            zombie.addInteracoes();
                                            transforma(humano);
                                            creatures.remove(humano);
                                            idEquipaAtual = ID_EQUIPA_OS_VIVOS;
                                            numeroDeJogadas++;
                                            numeroDeJogadasParaReset++;
                                            tiraTurnosVeneno();
                                            organizaListas();
                                            return true;

                                        } else if (humano.getEquipamentoAtual().isDefensivo()) {
                                            if(humano.getEquipamentoAtual().getTipo() == 5) {
                                                if (zombie.getTipo() == 4) {
                                                    humano.getEquipamentoAtual().aumentaNrVezesQueSafou();
                                                    idEquipaAtual = ID_EQUIPA_OS_VIVOS;
                                                    numeroDeJogadas++;
                                                    numeroDeJogadasParaReset++;
                                                    tiraTurnosVeneno();
                                                    organizaListas();
                                                    return true;
                                                } else {
                                                    zombie.addInteracoes();
                                                    transforma(humano);
                                                    creatures.remove(humano);
                                                    idEquipaAtual = ID_EQUIPA_OS_VIVOS;
                                                    numeroDeJogadas++;
                                                    numeroDeJogadasParaReset++;
                                                    organizaListas();
                                                    return true;
                                                }
                                            }
                                            if(humano.getEquipamentoAtual().getTipo() == 7) {
                                                if(!((GarrafaLixivia)humano.getEquipamentoAtual()).retirar()) {
                                                    zombie.addInteracoes();
                                                    transforma(humano);
                                                    creatures.remove(humano);
                                                }
                                            }
                                            if(humano.getEquipamentoAtual().getTipo() == 0) {
                                                if(!((EscudoMadeira)humano.getEquipamentoAtual()).retirar()) {
                                                    zombie.addInteracoes();
                                                    transforma(humano);
                                                    creatures.remove(humano);
                                                }
                                                if(((EscudoMadeira)humano.getEquipamentoAtual()).mostraHits() == 0) {
                                                    ((Humano) humano).setEquipamentosAtual(null);
                                                    idEquipaAtual = ID_EQUIPA_OS_VIVOS;
                                                    numeroDeJogadas++;
                                                    numeroDeJogadasParaReset++;
                                                    tiraTurnosVeneno();
                                                    organizaListas();
                                                    return true;
                                                }
                                            }
                                            if(humano.getEquipamentoAtual().getTipo() == 8) {
                                                if(((Humano)humano).mostraTurnos() == 0) {
                                                    zombie.addInteracoes();
                                                    transforma(humano);
                                                    creatures.remove(humano);
                                                }
                                            }
                                            if(humano.getEquipamentoAtual().getTipo() == 4) {
                                                if(zombie.getTipo() != 3) {
                                                    zombie.addInteracoes();
                                                    transforma(humano);
                                                    creatures.remove(humano);
                                                }
                                            }
                                            humano.getEquipamentoAtual().aumentaNrVezesQueSafou();
                                            idEquipaAtual = ID_EQUIPA_OS_VIVOS;
                                            numeroDeJogadas++;
                                            numeroDeJogadasParaReset++;
                                            tiraTurnosVeneno();
                                            organizaListas();
                                            return true;
                                        } else {
                                            if(humano.getEquipamentoAtual().getTipo() == 2) { //Pistola
                                                if(zombie.getTipo() == 4) {
                                                    zombie.addInteracoes();
                                                    transforma(humano);
                                                    creatures.remove(humano);
                                                    idEquipaAtual = ID_EQUIPA_OS_VIVOS;
                                                    numeroDeJogadas++;
                                                    numeroDeJogadasParaReset++;
                                                    tiraTurnosVeneno();
                                                    organizaListas();
                                                    return true;
                                                }
                                                if (!((PistolaPPK)humano.getEquipamentoAtual()).disparar()) {
                                                    zombie.addInteracoes();
                                                    transforma(humano);
                                                    creatures.remove(humano);
                                                    idEquipaAtual = ID_EQUIPA_OS_VIVOS;
                                                    numeroDeJogadas++;
                                                    numeroDeJogadasParaReset++;
                                                    tiraTurnosVeneno();
                                                    organizaListas();
                                                    return true;
                                                }
                                            }
                                            if(humano.getEquipamentoAtual().getTipo() == 1) {
                                                if(humano.getTipo() == 5 && zombie.getTipo() != 0) {
                                                    zombie.addInteracoes();
                                                    transforma(humano);
                                                    creatures.remove(humano);
                                                    idEquipaAtual = ID_EQUIPA_OS_VIVOS;
                                                    numeroDeJogadas++;
                                                    numeroDeJogadasParaReset++;
                                                    tiraTurnosVeneno();
                                                    organizaListas();
                                                    return true;
                                                }
                                            }
                                            humano.getEquipamentoAtual().aumentaNrVezesQueSafou();
                                            adicionaMortos(zombie);
                                            humano.addInteracoes();
                                            creatures.remove(zombie);
                                            idEquipaAtual = ID_EQUIPA_OS_VIVOS;
                                            numeroDeJogadas++;
                                            numeroDeJogadasParaReset++;
                                            tiraTurnosVeneno();
                                            organizaListas();
                                            return true;

                                        }
                                    }
                                }
                            }
                            for(Equipamento equipamento : listaEquipamento) {
                                if(equipamento.getX() == xD && equipamento.getY() == yD) {
                                    //Zombie Vampiro não pode ir para cima de alho
                                    if(zombie.getTipo() == 4 && equipamento.getTipo() == 5) {
                                        return false;
                                    }
                                    if(equipamento.getTipo() == 8) {
                                        return false;
                                    }
                                    equipamentoRemove = equipamento;
                                    zombie.addEquipamentos();

                                }
                            }

                            if (isDoorToSafeHaven(xD,yD)) {
                                return false;
                            }
                            zombie.alteraCoordenada(xD,yD);
                        } else {
                            return false;
                        }
                    }
                }
            }

            if(equipamentoRemove != null) {
                listaEquipamento.remove(equipamentoRemove);
            }

            idEquipaAtual = ID_EQUIPA_OS_VIVOS;
            numeroDeJogadas++;
            numeroDeJogadasParaReset++;
            tiraTurnosVeneno();
            organizaListas();
            return true;
        }
    }

    public boolean gameIsOver() {
        int numeroVivos = 0;
        for(Creature creature : creatures) {
            if (creature.getTipo() >= 5 && creature.getTipo() <= 9 && !creature.verificaSaveHeaven) {
                numeroVivos++;
            }
        }
        if(numeroVivos == 0) {
            return true;
        }
        if(numeroDeJogadasParaReset == 12){
            return true;
        }
        return false;
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
        organizaListas();
        ArrayList<String> resultados = new ArrayList<>();
        resultados.add("Nr. de turnos terminados:");
        resultados.add(String.valueOf(numeroDeJogadas));
        resultados.add("");
        resultados.add("Ainda pelo bairo:");
        resultados.add("");
        resultados.add("OS VIVOS");
        for (Creature humano : creatures) {
            if (humano.getTipo() >= 5 && humano.getTipo() <= 9 && !humano.passouSaveHeaven()) {
                resultados.add(humano.getId() + " " + humano.getNome());
            }
        }
        resultados.add("");
        resultados.add("OS OUTROS");
        for (Creature zombie : creatures) {
            if (zombie.getTipo() >= 0 && zombie.getTipo() <= 4) {
                resultados.add(zombie.getId() + " (antigamente conhecido como " + zombie.getNome() + ")");
            }
        }
        resultados.add("");
        resultados.add("Num safe haven:");
        resultados.add("");
        resultados.add("OS VIVOS");
        for (Creature humano : creatures) {
            if (humano.getTipo() >= 5 && humano.getTipo() <= 9 && humano.passouSaveHeaven()) {
                resultados.add(humano.getId() + " " + humano.getNome());
            }
        }
        resultados.add("");
        resultados.add("Envenenados / Destruidos");
        resultados.add("");
        resultados.add("OS VIVOS");
        for (Creature humano : mortos) {
            if (humano.getTipo() >= 5 && humano.getTipo() <= 9) {
                resultados.add(humano.getId() + " " + humano.getNome());
            }
        }
        resultados.add("");
        resultados.add("OS OUTROS");
        for (Creature zombie : mortos) {
            if (zombie.getTipo() >= 0 && zombie.getTipo() <= 4) {
                resultados.add(zombie.getId() + " (antigamente conhecido como " + zombie.getNome() + ")");
            }
        }
        return resultados;
    }

    public boolean isDay() {
        for(int i = 0; i < 100; i += 4) {
            if(i == numeroDeJogadas) {
                return true;
            }
            if(i + 1 == numeroDeJogadas) {
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
        for (Creature creature : mortos) {
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
        try {
            FileWriter escritor = new FileWriter(fich.getName());
            escritor.write(linhaColuna[0] + " " + linhaColuna[1] + "\n");
            escritor.write(idEquipaAtual + "\n");
            escritor.write(numeroDeJogadas + "\n");

            escritor.write(creatures.size() + "\n");
            for (Creature creature : creatures) {
                String passou = null;
                if (creature.passouSaveHeaven()) {
                    passou = "true";
                } else {
                    passou = "false";
                }
                escritor.write(creature.getId() + " : " + creature.getTipo() + " : " + creature.getNome() + " : " +
                        creature.getX() + " : " + creature.getY() + " : " + creature.getNumEquipamentos() + " : " +
                        creature.getNumInteracoes() + " : " + passou + "\n");
            }

            escritor.write(mortos.size() + "\n");
            for (Creature creature : mortos) {
                escritor.write(creature.getId() + " : " + creature.getTipo() + " : " + creature.getNome() + " : " +
                        creature.getX() + " : " + creature.getY() + " : " + creature.getNumEquipamentos() + "\n");
            }

            escritor.write(listaEquipamento.size() + "\n");
            for (Equipamento equip : listaEquipamento) {
                escritor.write(equip.getId() + " : " + equip.getTipo() + " : " + equip.getX() + " : " + equip.getY()
                        + " : " + equip.getNrVezesQueSafou() + "\n");
            }

            escritor.write(listaSaveHeaven.size() + "\n");
            for(SaveHeaven save : listaSaveHeaven) {
                escritor.write(save.getX() + " : " + save.getY() + "\n");
            }

            int numeroEquipNasCreaturas = 0;
            for (Creature creature : creatures) {
                if (creature.getEquipamentoAtual() != null) {
                    numeroEquipNasCreaturas++;
                }
            }
            escritor.write(numeroEquipNasCreaturas + "\n");
            for (Creature creature : creatures) {
                if (creature.getEquipamentoAtual() != null) {
                    Equipamento atual = creature.getEquipamentoAtual();
                    escritor.write(creature.getId() + " : " + atual.getId() + " : " + atual.getTipo() + " : " +
                            atual.getX() + " : " + atual.getY() + " : " + atual.getNrVezesQueSafou() + "\n");
                }
            }
            escritor.close();
            return true;
        } catch (IOException exception) {
            return false;
        }
    }

    public boolean loadGame(File fich) {
        creatures.clear();
        listaEquipamento.clear();
        listaSaveHeaven.clear();
        mortos.clear();
        HashMap<Integer, Equipamento> equipsCreaturas = new HashMap<>();
        int numeroEquipNasCreaturas = 0;
        int numMortos = 0;
        int count = 0;
        String linha;
        String[] dados;
        try {
            Scanner leitorFicheiro = new Scanner(fich);
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
                        numeroDeJogadas = Integer.parseInt(linha);
                        count++;
                        break;
                    case 3:
                        linha = leitorFicheiro.nextLine();
                        try {
                            criaturasJogo = Integer.parseInt(linha);
                        } catch (NumberFormatException exception) {
                            criaturasJogo = 0;
                        }
                        count++;
                        break;
                    case 4:
                        for (int i = 0;i<criaturasJogo;i++) {
                            linha = leitorFicheiro.nextLine();
                            dados = linha.split(" : ");
                            if (dados.length == 8) {
                                int id = Integer.parseInt(dados[0]);
                                int tipo = Integer.parseInt(dados[1]);
                                String nome = dados[2];
                                int x = Integer.parseInt(dados[3]);
                                int y = Integer.parseInt(dados[4]);
                                int numEqui = Integer.parseInt(dados[5]);
                                int numInt = Integer.parseInt(dados[6]);
                                String passouSafe = dados[7];
                                switch (tipo) {
                                    case 0:
                                        CriancaZ criancaZ = new CriancaZ(id, tipo, nome, x, y);
                                        criancaZ.addXEquipamentos(numEqui);
                                        criancaZ.addXInteracoes(numInt);
                                        if (passouSafe.equals("true")) {
                                            criancaZ.alteraEstadoSave();
                                        }
                                        creatures.add(criancaZ);
                                        break;
                                    case 1:
                                        AdultoZ adultoZ = new AdultoZ(id, tipo, nome, x, y);
                                        adultoZ.addXEquipamentos(numEqui);
                                        adultoZ.addXInteracoes(numInt);
                                        if (passouSafe.equals("true")) {
                                            adultoZ.alteraEstadoSave();
                                        }
                                        creatures.add(adultoZ);
                                        break;
                                    case 2:
                                        MilitarZ militarZ = new MilitarZ(id, tipo, nome, x, y);
                                        militarZ.addXEquipamentos(numEqui);
                                        militarZ.addXInteracoes(numInt);
                                        if (passouSafe.equals("true")) {
                                            militarZ.alteraEstadoSave();
                                        }
                                        creatures.add(militarZ);
                                        break;
                                    case 3:
                                        IdosoZ idosoZ = new IdosoZ(id, tipo, nome, x, y);
                                        idosoZ.addXEquipamentos(numEqui);
                                        idosoZ.addXInteracoes(numInt);
                                        if (passouSafe.equals("true")) {
                                            idosoZ.alteraEstadoSave();
                                        }
                                        creatures.add(idosoZ);
                                        break;
                                    case 4:
                                        VampiroZ vampiroZ = new VampiroZ(id, tipo, nome, x, y);
                                        vampiroZ.addXEquipamentos(numEqui);
                                        vampiroZ.addXInteracoes(numInt);
                                        if (passouSafe.equals("true")) {
                                            vampiroZ.alteraEstadoSave();
                                        }
                                        creatures.add(vampiroZ);
                                        break;
                                    case 5:
                                        CriancaH criancaH = new CriancaH(id, tipo, nome, x, y);
                                        criancaH.addXEquipamentos(numEqui);
                                        criancaH.addXInteracoes(numInt);
                                        if (passouSafe.equals("true")) {
                                            criancaH.alteraEstadoSave();
                                        }
                                        creatures.add(criancaH);
                                        break;
                                    case 6:
                                        AdultoH adultoH = new AdultoH(id, tipo, nome, x, y);
                                        adultoH.addXEquipamentos(numEqui);
                                        adultoH.addXInteracoes(numInt);
                                        if (passouSafe.equals("true")) {
                                            adultoH.alteraEstadoSave();
                                        }
                                        creatures.add(adultoH);
                                        break;
                                    case 7:
                                        MilitarH militarH = new MilitarH(id, tipo, nome, x, y);
                                        militarH.addXEquipamentos(numEqui);
                                        militarH.addXInteracoes(numInt);
                                        if (passouSafe.equals("true")) {
                                            militarH.alteraEstadoSave();
                                        }
                                        creatures.add(militarH);
                                        break;
                                    case 8:
                                        IdosoH idosoH = new IdosoH(id, tipo, nome, x, y);
                                        idosoH.addXEquipamentos(numEqui);
                                        idosoH.addXInteracoes(numInt);
                                        if (passouSafe.equals("true")) {
                                            idosoH.alteraEstadoSave();
                                        }
                                        creatures.add(idosoH);
                                        break;
                                    case 9:
                                        CaoH caoH = new CaoH(id, tipo, nome, x, y);
                                        caoH.addXEquipamentos(numEqui);
                                        caoH.addXInteracoes(numInt);
                                        if (passouSafe.equals("true")) {
                                            caoH.alteraEstadoSave();
                                        }
                                        creatures.add(caoH);
                                        break;
                                }
                            }
                        }
                        count++;
                        break;
                    case 5:
                        linha = leitorFicheiro.nextLine();
                        try {
                            numMortos = Integer.parseInt(linha);
                        } catch (NumberFormatException exception) {
                            numMortos = 0;
                        }
                        count++;
                        break;
                    case 6:
                        for (int i = 0;i<numMortos;i++) {
                            linha = leitorFicheiro.nextLine();
                            dados = linha.split(" : ");
                            if (dados.length == 6) {
                                int id = Integer.parseInt(dados[0]);
                                int tipo = Integer.parseInt(dados[1]);
                                String nome = dados[2];
                                int x = Integer.parseInt(dados[3]);
                                int y = Integer.parseInt(dados[4]);
                                int numEqui = Integer.parseInt(dados[5]);
                                switch (tipo) {
                                    case 0:
                                        CriancaZ criancaZ = new CriancaZ(id, tipo, nome, x, y);
                                        criancaZ.addXEquipamentos(numEqui);
                                        criancaZ.mata();
                                        mortos.add(criancaZ);
                                        break;
                                    case 1:
                                        AdultoZ adultoZ = new AdultoZ(id, tipo, nome, x, y);
                                        adultoZ.addXEquipamentos(numEqui);
                                        adultoZ.mata();
                                        mortos.add(adultoZ);
                                        break;
                                    case 2:
                                        MilitarZ militarZ = new MilitarZ(id, tipo, nome, x, y);
                                        militarZ.addXEquipamentos(numEqui);
                                        militarZ.mata();
                                        mortos.add(militarZ);
                                        break;
                                    case 3:
                                        IdosoZ idosoZ = new IdosoZ(id, tipo, nome, x, y);
                                        idosoZ.addXEquipamentos(numEqui);
                                        idosoZ.mata();
                                        mortos.add(idosoZ);
                                        break;
                                    case 4:
                                        VampiroZ vampiroZ = new VampiroZ(id, tipo, nome, x, y);
                                        vampiroZ.addXEquipamentos(numEqui);
                                        vampiroZ.mata();
                                        mortos.add(vampiroZ);
                                        break;
                                    case 5:
                                        CriancaH criancaH = new CriancaH(id, tipo, nome, x, y);
                                        criancaH.addXEquipamentos(numEqui);
                                        criancaH.mata();
                                        mortos.add(criancaH);
                                        break;
                                    case 6:
                                        AdultoH adultoH = new AdultoH(id, tipo, nome, x, y);
                                        adultoH.addXEquipamentos(numEqui);
                                        adultoH.mata();
                                        mortos.add(adultoH);
                                        break;
                                    case 7:
                                        MilitarH militarH = new MilitarH(id, tipo, nome, x, y);
                                        militarH.addXEquipamentos(numEqui);
                                        militarH.mata();
                                        mortos.add(militarH);
                                        break;
                                    case 8:
                                        IdosoH idosoH = new IdosoH(id, tipo, nome, x, y);
                                        idosoH.addXEquipamentos(numEqui);
                                        idosoH.mata();
                                        mortos.add(idosoH);
                                        break;
                                    case 9:
                                        CaoH caoH = new CaoH(id, tipo, nome, x, y);
                                        caoH.addXEquipamentos(numEqui);
                                        caoH.mata();
                                        mortos.add(caoH);
                                        break;
                                }
                            }
                        }
                        count++;
                        break;
                    case 7:
                        linha = leitorFicheiro.nextLine();
                        try {
                            equipamentosJogo = Integer.parseInt(linha);
                        } catch (NumberFormatException exception) {
                            equipamentosJogo = 0;
                        }
                        count++;
                        break;
                    case 8:
                        for (int i = 0;i<equipamentosJogo;i++) {
                            linha = leitorFicheiro.nextLine();
                            dados = linha.split(" : ");
                            if (dados.length == 5) {
                                int id = Integer.parseInt(dados[0]);
                                int tipo = Integer.parseInt(dados[1]);
                                int x = Integer.parseInt(dados[2]);
                                int y = Integer.parseInt(dados[3]);
                                int numSaves = Integer.parseInt(dados[4]);
                                switch (tipo) {
                                    case 0:
                                        EscudoMadeira escudoMadeira = new EscudoMadeira(id, tipo, x, y);
                                        escudoMadeira.alteraNrVezesQueSafou(numSaves);
                                        listaEquipamento.add(escudoMadeira);
                                        break;
                                    case 1:
                                        EspadaHanzo espadaHanzo = new EspadaHanzo(id, tipo, x, y);
                                        espadaHanzo.alteraNrVezesQueSafou(numSaves);
                                        listaEquipamento.add(espadaHanzo);
                                        break;
                                    case 2:
                                        PistolaPPK pistolaPPK = new PistolaPPK(id, tipo, x, y);
                                        pistolaPPK.alteraNrVezesQueSafou(numSaves);
                                        listaEquipamento.add(pistolaPPK);
                                        break;
                                    case 3:
                                        EscudoTactico escudoTactico = new EscudoTactico(id, tipo, x, y);
                                        escudoTactico.alteraNrVezesQueSafou(numSaves);
                                        listaEquipamento.add(escudoTactico);
                                        break;
                                    case 4:
                                        RevistaMaria revistaMaria = new RevistaMaria(id, tipo, x, y);
                                        revistaMaria.alteraNrVezesQueSafou(numSaves);
                                        listaEquipamento.add(revistaMaria);
                                        break;
                                    case 5:
                                        CabecaAlho cabecaAlho = new CabecaAlho(id, tipo, x, y);
                                        cabecaAlho.alteraNrVezesQueSafou(numSaves);
                                        listaEquipamento.add(cabecaAlho);
                                        break;
                                    case 6:
                                        EstacaMadeira estacaMadeira = new EstacaMadeira(id, tipo, x, y);
                                        estacaMadeira.alteraNrVezesQueSafou(numSaves);
                                        listaEquipamento.add(estacaMadeira);
                                        break;
                                    case 7:
                                        GarrafaLixivia garrafaLixivia = new GarrafaLixivia(id, tipo, x, y);
                                        garrafaLixivia.alteraNrVezesQueSafou(numSaves);
                                        listaEquipamento.add(garrafaLixivia);
                                        break;
                                    case 8:
                                        Veneno veneno = new Veneno(id, tipo, x, y);
                                        veneno.alteraNrVezesQueSafou(numSaves);
                                        listaEquipamento.add(veneno);
                                        break;
                                    case 9:
                                        Antidoto antidoto = new Antidoto(id, tipo, x, y);
                                        antidoto.alteraNrVezesQueSafou(numSaves);
                                        listaEquipamento.add(antidoto);
                                        break;
                                    case 10:
                                        BeskarHelmet beskarHelmet = new BeskarHelmet(id, tipo, x, y);
                                        beskarHelmet.alteraNrVezesQueSafou(numSaves);
                                        listaEquipamento.add(beskarHelmet);
                                        break;
                                }
                            }
                        }
                        count++;
                        break;
                    case 9:
                        linha = leitorFicheiro.nextLine();
                        try {
                            saveHeavenJogo = Integer.parseInt(linha);
                        } catch (NumberFormatException exception) {
                            saveHeavenJogo = 0;
                        }
                        count++;
                        break;
                    case 10:
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
                    case 11:
                        linha = leitorFicheiro.nextLine();
                        try {
                            numeroEquipNasCreaturas = Integer.parseInt(linha);
                        } catch (NumberFormatException exception) {
                            numeroEquipNasCreaturas = 0;
                        }
                        count++;
                        break;
                    case 12:
                        for (int i = 0;i<numeroEquipNasCreaturas;i++) {
                            linha = leitorFicheiro.nextLine();
                            dados = linha.split(" : ");
                            if (dados.length == 6) {
                                int idCreatura = Integer.parseInt(dados[0]);
                                int id = Integer.parseInt(dados[1]);
                                int tipo = Integer.parseInt(dados[2]);
                                int x = Integer.parseInt(dados[3]);
                                int y = Integer.parseInt(dados[4]);
                                int numSaves = Integer.parseInt(dados[5]);
                                switch (tipo) {
                                    case 0:
                                        EscudoMadeira escudoMadeira = new EscudoMadeira(id, tipo, x, y);
                                        escudoMadeira.alteraNrVezesQueSafou(numSaves);
                                        equipsCreaturas.put(idCreatura,escudoMadeira);
                                        break;
                                    case 1:
                                        EspadaHanzo espadaHanzo = new EspadaHanzo(id, tipo, x, y);
                                        espadaHanzo.alteraNrVezesQueSafou(numSaves);
                                        equipsCreaturas.put(idCreatura,espadaHanzo);
                                        break;
                                    case 2:
                                        PistolaPPK pistolaPPK = new PistolaPPK(id, tipo, x, y);
                                        pistolaPPK.alteraNrVezesQueSafou(numSaves);
                                        equipsCreaturas.put(idCreatura,pistolaPPK);
                                        break;
                                    case 3:
                                        EscudoTactico escudoTactico = new EscudoTactico(id, tipo, x, y);
                                        escudoTactico.alteraNrVezesQueSafou(numSaves);
                                        equipsCreaturas.put(idCreatura,escudoTactico);
                                        break;
                                    case 4:
                                        RevistaMaria revistaMaria = new RevistaMaria(id, tipo, x, y);
                                        revistaMaria.alteraNrVezesQueSafou(numSaves);
                                        equipsCreaturas.put(idCreatura,revistaMaria);
                                        break;
                                    case 5:
                                        CabecaAlho cabecaAlho = new CabecaAlho(id, tipo, x, y);
                                        cabecaAlho.alteraNrVezesQueSafou(numSaves);
                                        equipsCreaturas.put(idCreatura,cabecaAlho);
                                        break;
                                    case 6:
                                        EstacaMadeira estacaMadeira = new EstacaMadeira(id, tipo, x, y);
                                        estacaMadeira.alteraNrVezesQueSafou(numSaves);
                                        equipsCreaturas.put(idCreatura,estacaMadeira);
                                        break;
                                    case 7:
                                        GarrafaLixivia garrafaLixivia = new GarrafaLixivia(id, tipo, x, y);
                                        garrafaLixivia.alteraNrVezesQueSafou(numSaves);
                                        equipsCreaturas.put(idCreatura,garrafaLixivia);
                                        break;
                                    case 8:
                                        Veneno veneno = new Veneno(id, tipo, x, y);
                                        veneno.alteraNrVezesQueSafou(numSaves);
                                        equipsCreaturas.put(idCreatura,veneno);
                                        break;
                                    case 9:
                                        Antidoto antidoto = new Antidoto(id, tipo, x, y);
                                        antidoto.alteraNrVezesQueSafou(numSaves);
                                        equipsCreaturas.put(idCreatura,antidoto);
                                        break;
                                    case 10:
                                        BeskarHelmet beskarHelmet = new BeskarHelmet(id, tipo, x, y);
                                        beskarHelmet.alteraNrVezesQueSafou(numSaves);
                                        equipsCreaturas.put(idCreatura,beskarHelmet);
                                        break;
                                }
                            }
                        }
                        for (Map.Entry entrada : equipsCreaturas.entrySet()) {
                            for (Creature creature : creatures) {
                                if (creature.getId() == (int)entrada.getKey()) {
                                    ((Humano) creature).setEquipamentosAtual((Equipamento) entrada.getValue());
                                }
                            }
                        }
                        count++;
                        break;
                }
            }
            leitorFicheiro.close();
        } catch(FileNotFoundException exception) {
            System.out.println("Erro: o ficheiro nao foi encontrado.");
            return false;
        }
        return true;
    }

    public String[] popCultureExtravaganza() {
        String[] respostas = new String[14];
        respostas[0] = "Resident Evil";
        respostas[1] = "Evil Dead";
        respostas[2] = "I Am Legend";
        respostas[3] = "I Am Legend";
        respostas[4] = "Dragon Ball";
        respostas[5] = "World War Z";
        respostas[6] = "Mandalorians";
        respostas[7] = "1972";
        respostas[8] = "Kill Bill";
        respostas[9] = "1978";
        respostas[10] = "Bond, James Bond.";
        respostas[11] = "Lost";
        respostas[12] = "Chocho";
        respostas[13] = "Farrokh Bulsara";
        return respostas;
    }

    public void transforma(Creature humano) {
        numeroDeJogadasParaReset = -1;
        int id = humano.getId();
        String nome = humano.getNome();
        int posX = humano.getX();
        int posY= humano.getY();
        if(humano.getTipo() == 5) {
            Zombie zombieNovo = new CriancaZ(id,0,nome,posX,posY);
            creatures.add(zombieNovo);
        }

        if(humano.getTipo() == 6) {
            Zombie zombieNovo = new AdultoZ(id,1,nome,posX,posY);
            creatures.add(zombieNovo);
        }
        if(humano.getTipo() == 7) {
            Zombie zombieNovo = new MilitarZ(id,2,nome,posX,posY);
            creatures.add(zombieNovo);
        }
        if(humano.getTipo() == 8) {
            Zombie zombieNovo = new IdosoZ(id,3,nome,posX,posY);
            creatures.add(zombieNovo);
        }
    }

    public void tiraTurnosVeneno() {
        Creature creatureRemover = null;
        for(Creature creature : creatures) {
            if(creature.getTipo() >= 5 && creature.getTipo() <= 9) {
                if (((Humano)creature).estadoVeneno()) {
                    if(!((Humano)creature).tiraTurnosPoison()) {
                        creatureRemover = creature;
                    }
                }
            }
        }
        if (creatureRemover != null) {
            adicionaMortos(creatureRemover);
            creatures.remove(creatureRemover);
        }
    }

    public void organizaListas() {
        creatures.sort(creatureComparator);
        listaEquipamento.sort(equipamentoComparator);
        mortos.sort(creatureComparator);
    }

    public void adicionaMortos(Creature creature) {
        creature.mata();
        mortos.add(creature);
    }

    public boolean passarPorCima(Creature creature, int xD, int yD) {
        for(Creature creature1 : creatures) {
            //Vertical
            if(creature.getX() == xD && creature.getY() + 2 == yD) {
                if(creature1.getX() == xD && creature1.getY() + 1== yD) {
                    return true;
                }
            }
            if(creature.getX() == xD && creature.getY() - 2 == yD) {
                if(creature1.getX() == xD && creature1.getY() - 1== yD) {
                    return true;
                }
            }
            if(creature.getX() == xD && creature.getY() + 3 == yD) {
                if(creature1.getX() == xD && creature1.getY() + 1 == yD) {
                    return true;
                }
            }
            if(creature.getX() == xD && creature.getY() + 3 == yD) {
                if(creature1.getX() == xD && creature1.getY() + 2 == yD) {
                    return true;
                }
            }
            if(creature.getX() == xD && creature.getY() - 3 == yD) {
                if(creature1.getX() == xD && creature1.getY() - 2 == yD) {
                    return true;
                }
            }
            if(creature.getX() == xD && creature.getY() - 3 == yD) {
                if(creature1.getX() == xD && creature1.getY() - 1 == yD) {
                    return true;
                }

            }
            //Horizontal
            if(creature.getX() + 2 == xD && creature.getY() == yD) {
                if(creature1.getX() + 1 == xD && creature1.getY()== yD) {
                    return true;
                }
            }
            if(creature.getX() - 2 == xD && creature.getY() == yD) {
                if(creature1.getX() - 1 == xD && creature1.getY() == yD) {
                    return true;
                }
            }
            if(creature.getX() + 3 == xD && creature.getY() == yD) {
                if(creature1.getX() + 2 == xD && creature1.getY() == yD) {
                    return true;
                }
            }
            if(creature.getX() + 3 == xD && creature.getY() == yD) {
                if(creature1.getX() + 1 == xD && creature1.getY() == yD) {
                    return true;
                }
            }
            if(creature.getX() - 3 == xD && creature.getY() == yD) {
                if(creature1.getX() - 2 == xD && creature1.getY() == yD) {
                    return true;
                }
            }
            if(creature.getX() - 3 == xD && creature.getY() == yD) {
                if(creature1.getX() - 1 == xD && creature1.getY() == yD) {
                    return true;
                }

            }
            //Diagonal
            if(creature.getX() - 2 == xD && creature.getY() - 2 == yD) {
                if (creature1.getX() - 1 == xD && creature1.getY() - 1 == yD) {
                    return true;
                }
            }
            if(creature.getX() + 2 == xD && creature.getY() + 2 == yD) {
                if (creature1.getX() + 1 == xD && creature1.getY() + 1 == yD) {
                    return true;
                }
            }
            if(creature.getX() + 3 == xD && creature.getY() + 3 == yD) {
                if (creature1.getX() + 1 == xD&& creature1.getY() + 1 == yD) {
                    return true;
                }
                if (creature1.getX() + 2 == xD&& creature1.getY() + 2 == yD) {
                    return true;
                }
            }
            if(creature.getX() - 3 == xD && creature.getY() - 3 == yD) {
                if (creature1.getX() - 1 == xD && creature1.getY() - 1 == yD) {
                    return true;
                }
                if (creature1.getX() - 2 == xD && creature1.getY() - 2 == yD) {
                    return true;
                }
            }
            if(creature.getX() - 2 == xD && creature.getY() + 2 == yD) {
                if (creature1.getX() - 1 == xD && creature1.getY() + 1 == yD) {
                    return true;
                }
            }
            if(creature.getX() + 2 == xD && creature.getY() - 2 == yD) {
                if (creature1.getX() + 1 == xD && creature1.getY() - 1 == yD) {
                    return true;
                }
            }
            if(creature.getX() - 3 == xD && creature.getY() + 3 == yD) {
                if (creature1.getX() - 1 == xD && creature1.getY() + 1 == yD) {
                    return true;
                }
                if (creature1.getX() - 2 == xD && creature1.getY() + 2 == yD) {
                    return true;
                }
            }
            if(creature.getX() + 3 == xD && creature.getY() - 3 == yD) {
                if (creature1.getX() + 1== xD && creature1.getY() - 1 == yD) {
                    return true;
                }
                if (creature1.getX() + 2== xD && creature1.getY() - 2 == yD) {
                    return true;
                }
            }
        }


        for(Equipamento equipamento : listaEquipamento) {
            //Vertical
            if(creature.getX() == xD && creature.getY() + 2 == yD) {
                if(equipamento.getX() == xD && equipamento.getY() + 1== yD) {
                    return true;
                }
            }
            if(creature.getX() == xD && creature.getY() - 2 == yD) {
                if(equipamento.getX() == xD && equipamento.getY() - 1== yD) {
                    return true;
                }
            }
            if(creature.getX() == xD && creature.getY() + 3 == yD) {
                if(equipamento.getX() == xD && equipamento.getY() + 2 == yD) {
                    return true;
                }
            }
            if(creature.getX() == xD && creature.getY() + 3 == yD) {
                if(equipamento.getX() == xD && equipamento.getY() + 1 == yD) {
                    return true;
                }

            }
            if(creature.getX() == xD && creature.getY() - 3 == yD) {
                if(equipamento.getX() == xD && equipamento.getY() - 2 == yD) {
                    return true;
                }
            }
            if(creature.getX() == xD && creature.getY() - 3 == yD) {
                if(equipamento.getX() == xD && equipamento.getY() - 1 == yD) {
                    return true;
                }

            }
            //Horizontal
            if(creature.getX() + 2 == xD && creature.getY() == yD) {
                if(equipamento.getX() + 1 == xD && equipamento.getY()== yD) {
                    return true;
                }
            }
            if(creature.getX() - 2 == xD && creature.getY() == yD) {
                if(equipamento.getX() - 1 == xD && equipamento.getY() == yD) {
                    return true;
                }
            }
            if(creature.getX() + 3 == xD && creature.getY() == yD) {
                if(equipamento.getX() + 2 == xD && equipamento.getY() == yD) {
                    return true;
                }
            }
            if(creature.getX() + 3 == xD && creature.getY() == yD) {
                if(equipamento.getX() + 1 == xD && equipamento.getY() == yD) {
                    return true;
                }
            }
            if(creature.getX() - 3 == xD && creature.getY() == yD) {
                if(equipamento.getX() - 2 == xD && equipamento.getY() == yD) {
                    return true;
                }
            }
            if(creature.getX() - 3 == xD && creature.getY() == yD) {
                if(equipamento.getX() - 1 == xD && equipamento.getY() == yD) {
                    return true;
                }
            }
            //Diagonal
            if(creature.getX() - 2 == xD && creature.getY() - 2 == yD) {
                if (equipamento.getX() - 1 == xD && equipamento.getY() - 1 == yD) {
                    return true;
                }
            }
            if(creature.getX() + 2 == xD && creature.getY() + 2 == yD) {
                if (equipamento.getX() + 1 == xD && equipamento.getY() + 1 == yD) {
                    return true;
                }
            }
            if(creature.getX() + 3 == xD && creature.getY() + 3 == yD) {
                if (equipamento.getX() + 1 == xD&& equipamento.getY() + 1 == yD) {
                    return true;
                }
                if (equipamento.getX() + 2 == xD&& equipamento.getY() + 2 == yD) {
                    return true;
                }
            }
            if(creature.getX() - 3 == xD && creature.getY() - 3 == yD) {
                if (equipamento.getX() - 1 == xD && equipamento.getY() - 1 == yD) {
                    return true;
                }
                if (equipamento.getX() - 2 == xD && equipamento.getY() - 2 == yD) {
                    return true;
                }
            }
            if(creature.getX() - 2 == xD && creature.getY() + 2 == yD) {
                if (equipamento.getX() - 1 == xD && equipamento.getY() + 1 == yD) {
                    return true;
                }
            }
            if(creature.getX() + 2 == xD && creature.getY() - 2 == yD) {
                if (equipamento.getX() + 1 == xD && equipamento.getY() - 1 == yD) {
                    return true;
                }
            }
            if(creature.getX() - 3 == xD && creature.getY() + 3 == yD) {
                if (equipamento.getX() - 1 == xD && equipamento.getY() + 1 == yD) {
                    return true;
                }
                if (equipamento.getX() - 2 == xD && equipamento.getY() + 2 == yD) {
                    return true;
                }
            }
            if(creature.getX() + 3 == xD && creature.getY() - 3 == yD) {
                if (equipamento.getX() + 1== xD && equipamento.getY() - 1 == yD) {
                    return true;
                }
                if (equipamento.getX() + 2== xD && equipamento.getY() - 2 == yD) {
                    return true;
                }
            }
        }
        return false;
    }

    public Map<String, List<String>> getGameStatistics() {
        Map<String, List<String>> mapa = new HashMap<>();

        //juntar todas as creaturas que já jogaram ou que ainda jogam
        List<Creature> geral = new ArrayList<>();
        geral.addAll(creatures);
        geral.addAll(mortos);

        //os3ZombiesMaisTramados
        List<String> listA;
        listA = creatures.stream()
                    .filter(z -> z.getTipo() >= 0 && z.getTipo() <= 4)
                    .filter(h -> h.getNumInteracoes() >= 1)
                    .sorted((z1,z2) -> z2.getNumInteracoes() - z1.getNumInteracoes())
                    .limit(3)
                    .map(z -> z.getId() + ":" + z.getNome() + ":" + z.getNumInteracoes())
                    .collect(Collectors.toList());
        if(listA.size() < 3) {
            listA = creatures.stream()
                        .filter(z -> z.getTipo() >= 0 && z.getTipo() <= 4)
                        .filter(z -> z.getNumInteracoes() >= 1)
                        .map(z -> z.getId() + ":" + z.getNome() + ":" + z.getNumInteracoes())
                        .collect(Collectors.toList());
        }
        mapa.put("os3ZombiesMaisTramados",listA);

        //os3VivosMaisDuros
        List<String> listB;
        listB = geral.stream()
                .filter(h -> h.getTipo() >= 5 && h.getTipo() <= 9)
                .filter(h -> !h.passouSaveHeaven())
                .filter(h -> h.getNumInteracoes() >= 1)
                .sorted((h1,h2) -> h2.getNumInteracoes() - h1.getNumInteracoes())
                .limit(3)
                .map(h -> h.getId() + ":" + h.getNome() + ":" + h.getNumInteracoes())
                .collect(Collectors.toList());
        if(listB.size() < 3) {
            listB = geral.stream()
                    .filter(h -> h.getTipo() >= 5 && h.getTipo() <= 9)
                    .filter(h -> h.getNumInteracoes() >= 1)
                    .sorted((h1,h2) -> h2.getNumInteracoes() - h1.getNumInteracoes())
                    .map(h -> h.getId() + ":" + h.getNome() + ":" + h.getNumInteracoes())
                    .collect(Collectors.toList());
        }
        mapa.put("os3VivosMaisDuros",listB);

        //tiposDeEquipamentosMaisUteis
        List<String> listC;
        Stream<Equipamento> equipamentosLivres = listaEquipamento.stream();
        Stream<Equipamento> equipamentosHumano = geral.stream()
                .map(Creature::getEquipamentoAtual)
                .filter(Objects::nonNull);
        Stream<Equipamento> equipamentosAll = Stream.concat(equipamentosLivres,equipamentosHumano);
        Map<Integer,Integer> juncao = equipamentosAll
                .filter(e -> e.getNrVezesQueSafou() >= 1)
                .collect(Collectors.groupingBy(Equipamento::getTipo))
                .entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> e.getValue().stream().mapToInt(Equipamento::getNrVezesQueSafou).sum()
                ));
        listC = juncao.entrySet().stream()
                .sorted((n1,n2) -> (n1.getValue() - n2.getValue()))
                .map(n -> n.getKey() + ":" + n.getValue())
                .collect(Collectors.toList());
        mapa.put("tiposDeEquipamentoMaisUteis",listC);

        //tipodesDeZombiesESeusEquipamentosDestruidos
        List<String> listD = new ArrayList<>();
        listD.add("");
        mapa.put("tiposDeZombieESeusEquipamentosDestruidos",listD);

        //criaturasMaisEquipadas
        List<String> listE;
        listE = creatures.stream()
                .filter(c -> !c.passouSaveHeaven())
                .sorted((c1,c2) -> c2.getNumEquipamentos() - c1.getNumEquipamentos())
                .limit(5)
                .map(c -> c.getId() + ":" + c.getNome() + ":" + c.getNumEquipamentos())
                .collect(Collectors.toList());
        if(listE.size() < 5) {
            listE = creatures.stream()
                    .filter(c -> !c.passouSaveHeaven())
                    .sorted((c1,c2) -> c2.getNumEquipamentos() - c1.getNumEquipamentos())
                    .map(c -> c.getId() + ":" + c.getNome() + ":" + c.getNumEquipamentos())
                    .collect(Collectors.toList());
        }
        mapa.put("criaturasMaisEquipadas",listE);
        return mapa;
    }
}

/*
AS CRIATURAS:
0 = “Criança (Zombie)”
1 = “Adulto (Zombie)”
2 = “Militar (Zombie)”
3 = “Idoso (Zombie)”
4 = “Zombie Vampiro”
5 = “Criança (Vivo)”
6 = “Adulto (Vivo)”
7 = “Militar (Vivo)”
8 = “Idoso (Vivo)”
9 = “Cão”

AS EQUIPAS:
Os Vivos (ID = 10)
Os Outros (ID = 20)

OS EQUIPAMENTOS:
0 = "Escudo de Madeira"
1 = "Espada Hattori"
2 = "Pistola PPK"
3 = "Escudo Tatico"
4 = "Revista Maria"
5 = "Cabeça de Alho"
6 = "Estaca de Madeira"
7 = "Garrafa de Lixivia"
8 = "Veneno"
9 = "Antidoto"
10 = "Beskar Helmet"
 */
