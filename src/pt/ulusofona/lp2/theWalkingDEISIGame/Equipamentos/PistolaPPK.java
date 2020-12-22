package pt.ulusofona.lp2.theWalkingDEISIGame.Equipamentos;

import pt.ulusofona.lp2.theWalkingDEISIGame.Equipamento;

public class PistolaPPK extends Equipamento {
    private int balas = 3;
    public PistolaPPK(int id, int tipo, int x, int y) {
        super(id, tipo, x, y);
    }

    public String toString() {
        return "Pistola Walther PPK | " + this.balas;
    }
}
