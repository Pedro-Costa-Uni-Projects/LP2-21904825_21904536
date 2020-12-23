package pt.ulusofona.lp2.theWalkingDEISIGame.equipamentos;

import pt.ulusofona.lp2.theWalkingDEISIGame.Equipamento;

public class Antidoto extends Equipamento {
    public Antidoto(int id, int tipo, int x, int y) {
        super(id, tipo, x, y);
    }

    public String toString() {
        return "Antídoto";
    }

    public boolean isOfensivo() {
        return false;
    }

    public boolean isDefensivo() {
        return true;
    }
}
