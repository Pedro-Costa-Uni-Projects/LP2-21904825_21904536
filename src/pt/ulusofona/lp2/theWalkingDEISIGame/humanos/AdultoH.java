package pt.ulusofona.lp2.theWalkingDEISIGame.humanos;

import pt.ulusofona.lp2.theWalkingDEISIGame.Humano;

public class AdultoH extends Humano {
    public AdultoH(int id, int tipo, String nome, int x, int y) {
        super(id, tipo, nome, x, y);
    }

    public String toString() {
        return this.id + " | Adulto (Vivo) | Os Vivos | " + this.nome + " " + this.equipamentos + " @ (" + this.x
                + ", " + this.y +")";
    }
}
