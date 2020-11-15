package pt.ulusofona.lp2.theWalkingDEISIGame;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class TWDGameManager {
    int[] linhaColuna = new int[2];
    int idEquipaStart;
    int criaturasJogo;
    int equipamentosJogo;
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
        }
        return false;
    }
    //
    
}