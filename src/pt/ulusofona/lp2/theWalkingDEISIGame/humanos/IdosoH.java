package pt.ulusofona.lp2.theWalkingDEISIGame.humanos;

import pt.ulusofona.lp2.theWalkingDEISIGame.Humano;

import java.util.ArrayList;

public class IdosoH extends Humano {
    public IdosoH(int id, int tipo, String nome, int x, int y) {
        super(id, tipo, nome, x, y);
    }
    public String toString() {
        if (this.verificaSaveHeaven) {
            return this.id + " | Idoso (Vivo) | Os Vivos | " + this.nome + " " + this.equipamentos + " @ A salvo";
        } else if (!this.vivo) {
            return this.id + " | Idoso (Vivo) | Os Vivos | " + this.nome + " " + this.equipamentos + " @ RIP";
        } else {
            return this.id + " | Idoso (Vivo) | Os Vivos | " + this.nome + " " + this.equipamentos + " @ (" + this.x
                    + ", " + this.y + ")";
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
        return "Humano.png";
    }
}
