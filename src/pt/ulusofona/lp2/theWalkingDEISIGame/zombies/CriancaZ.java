package pt.ulusofona.lp2.theWalkingDEISIGame.zombies;

import pt.ulusofona.lp2.theWalkingDEISIGame.Zombie;

public class CriancaZ extends Zombie {
    public CriancaZ(int id, int tipo, String nome, int x, int y) {
        super(id, tipo, nome, x, y);
    }

    public String toString() {
        return this.id + " | Criança (Zombie) | Os Outros | " + this.nome + " " + this.equipamentos + " @ (" + this.x
                + ", " + this.y +")";
    }
}
