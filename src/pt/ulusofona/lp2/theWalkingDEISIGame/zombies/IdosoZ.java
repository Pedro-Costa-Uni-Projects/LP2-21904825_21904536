package pt.ulusofona.lp2.theWalkingDEISIGame.zombies;

import pt.ulusofona.lp2.theWalkingDEISIGame.Zombie;

import java.util.ArrayList;

public class IdosoZ  extends Zombie {
    public IdosoZ(int id, int tipo, String nome, int x, int y) {
        super(id, tipo, nome, x, y);
    }
    public String toString() {
        if(!this.vivo) {
            return this.id + " | Idoso (Zombie) | Os Outros | " + this.nome + " " + this.equipamentos + " @ RIP";
        } else {
            return this.id + " | Idoso (Zombie) | Os Outros | " + this.nome + " " + this.equipamentos + " @ ("
                    + this.x + ", " + this.y +")";
        }
    }
    @Override
    public boolean movimento(int xO, int yO, int xD, int yD) {
        ArrayList<int[]> listaPossiveis = new ArrayList<>();
        listaPossiveis.add(new int[]{xO, yO - 1});
        listaPossiveis.add(new int[]{xO, yO + 1});
        listaPossiveis.add(new int[]{xO + 1, yO});
        listaPossiveis.add(new int[]{xO - 1, yO});
        for(int[] posicao : listaPossiveis) {
            if (posicao[0] == xD && posicao[1] == yD) {
                return true;
            }
        }
        return false;
    }
    public String getImagePNG() {
        return "IdosoZ.png";
    }
}
