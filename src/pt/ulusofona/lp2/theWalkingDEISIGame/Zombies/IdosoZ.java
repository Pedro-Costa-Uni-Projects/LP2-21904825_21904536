package pt.ulusofona.lp2.theWalkingDEISIGame.Zombies;

import pt.ulusofona.lp2.theWalkingDEISIGame.Zombie;

public class IdosoZ  extends Zombie {
    public IdosoZ(int id, int tipo, String nome, int x, int y) {
        super(id, tipo, nome, x, y);
    }

    public String toString() {
        return this.id + " | Idoso (Zombie) | Os Outros | " + this.nome + " | " + this.equipamentos + " @ (" + this.x
                + "," + this.y +")";
    }
}
