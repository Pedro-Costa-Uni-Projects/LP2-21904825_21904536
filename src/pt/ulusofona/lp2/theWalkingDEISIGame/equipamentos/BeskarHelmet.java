package pt.ulusofona.lp2.theWalkingDEISIGame.equipamentos;

import pt.ulusofona.lp2.theWalkingDEISIGame.Equipamento;

public class BeskarHelmet extends Equipamento {
    public BeskarHelmet(int id, int tipo, int x, int y) {
        super(id, tipo, x, y);
    }

    public String toString() {
        return "Beskar Helmet";
    }

    public boolean isOfensivo() {
        return true;
    }

    public boolean isDefensivo() {
        return true;
    }
}
