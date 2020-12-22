package pt.ulusofona.lp2.theWalkingDEISIGame.zombies;

import pt.ulusofona.lp2.theWalkingDEISIGame.Zombie;

public class MilitarZ extends Zombie {
    public MilitarZ(int id, int tipo, String nome, int x, int y) {
        super(id, tipo, nome, x, y);
    }

    public String toString() {
        return this.id + " | Militar (Zombie) | Os Outros | " + this.nome + " | " + this.equipamentos + " @ (" + this.x
                + ", " + this.y +")";
    }
}
