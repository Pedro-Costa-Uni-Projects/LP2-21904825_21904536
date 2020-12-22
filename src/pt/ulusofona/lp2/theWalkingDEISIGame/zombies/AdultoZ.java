package pt.ulusofona.lp2.theWalkingDEISIGame.zombies;


import pt.ulusofona.lp2.theWalkingDEISIGame.Zombie;

public class AdultoZ extends Zombie {
    public AdultoZ(int id, int tipo, String nome, int x, int y) {
        super(id, tipo, nome, x, y);
    }

    public String toString() {
        return this.id + " | Adulto (Zombie) | Os Outros | " + this.nome + " | " + this.equipamentos + " @ (" + this.x
                + ", " + this.y +")";
    }
}
