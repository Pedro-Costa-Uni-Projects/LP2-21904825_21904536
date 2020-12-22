package pt.ulusofona.lp2.theWalkingDEISIGame.humanos;

import pt.ulusofona.lp2.theWalkingDEISIGame.Humano;

public class CriancaH extends Humano {
    public CriancaH(int id, int tipo, String nome, int x, int y) {
        super(id, tipo, nome, x, y);
    }

    public String toString() {
        return this.id + " | Criança (Vivo) | Os Vivos | " + this.nome + " " + this.equipamentos + " @ (" + this.x
                + ", " + this.y +")";
    }
}
