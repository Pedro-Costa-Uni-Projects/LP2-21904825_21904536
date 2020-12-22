package pt.ulusofona.lp2.theWalkingDEISIGame.zombies;

import pt.ulusofona.lp2.theWalkingDEISIGame.Zombie;

public class VampiroZ extends Zombie {
    public VampiroZ(int id, int tipo, String nome, int x, int y) {
        super(id, tipo, nome, x, y);
    }

    public String toString() {
        return this.id + " | Zombie Vampiro | Os Outros | " + this.nome + " " + this.equipamentos + " @ (" + this.x
                + ", "  + this.y +")";
    }
}
