package pt.ulusofona.lp2.theWalkingDEISIGame.zombies;


import pt.ulusofona.lp2.theWalkingDEISIGame.Zombie;

import java.util.ArrayList;

public class AdultoZ extends Zombie {
    public AdultoZ(int id, int tipo, String nome, int x, int y) {
        super(id, tipo, nome, x, y);
    }
    public String toString() {
        if(!this.vivo) {
            return this.id + " | Adulto (Zombie) | Os Outros | " + this.nome + " " + this.equipamentos + " @ RIP";
        } else {
            return this.id + " | Adulto (Zombie) | Os Outros | " + this.nome + " " + this.equipamentos + " @ (" + this.x
                    + ", " + this.y +")";
        }
    }
    @Override
    public boolean movimento(int xO, int yO, int xD, int yD) {
        ArrayList<int[]> listaPossiveis = new ArrayList<>();
        listaPossiveis.add(new int[]{xO, yO - 1});
        listaPossiveis.add(new int[]{xO, yO + 1});
        listaPossiveis.add(new int[]{xO + 1, yO});
        listaPossiveis.add(new int[]{xO - 1, yO});
        listaPossiveis.add(new int[]{xO, yO - 2});
        listaPossiveis.add(new int[]{xO, yO + 2});
        listaPossiveis.add(new int[]{xO + 2, yO});
        listaPossiveis.add(new int[]{xO - 2, yO});
        listaPossiveis.add(new int[]{xO - 1, yO - 1});
        listaPossiveis.add(new int[]{xO - 1, yO + 1});
        listaPossiveis.add(new int[]{xO + 1, yO - 1});
        listaPossiveis.add(new int[]{xO + 1, yO + 1});
        listaPossiveis.add(new int[]{xO - 2, yO - 2});
        listaPossiveis.add(new int[]{xO - 2, yO + 2});
        listaPossiveis.add(new int[]{xO + 2, yO - 2});
        listaPossiveis.add(new int[]{xO + 2, yO + 2});

        for(int[] posicao : listaPossiveis) {
            if (posicao[0] == xD && posicao[1] == yD) {
                return true;
            }
        }
        return false;
    }
    public String getImagePNG() {
        return "AdultoZ.png";
    }

}
