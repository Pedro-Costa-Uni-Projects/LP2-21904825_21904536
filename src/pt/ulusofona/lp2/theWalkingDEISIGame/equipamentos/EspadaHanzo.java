package pt.ulusofona.lp2.theWalkingDEISIGame.equipamentos;

import pt.ulusofona.lp2.theWalkingDEISIGame.Equipamento;

public class EspadaHanzo extends Equipamento {
    public EspadaHanzo(int id, int tipo, int x, int y) {
        super(id, tipo, x, y);
    }

    public String toString() {
        return "Espada Hattori Hanzo";
    }

    @Override
    public boolean isOfensivo() {
        return true;
    }

    @Override
    public boolean isDefensivo() {
        return false;
    }
}
