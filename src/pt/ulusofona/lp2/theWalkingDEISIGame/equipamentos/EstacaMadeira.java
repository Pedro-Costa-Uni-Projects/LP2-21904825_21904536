package pt.ulusofona.lp2.theWalkingDEISIGame.equipamentos;

import pt.ulusofona.lp2.theWalkingDEISIGame.Equipamento;

public class EstacaMadeira extends Equipamento {
    public EstacaMadeira(int id, int tipo, int x, int y) {
        super(id, tipo, x, y);
    }

    public String toString() {
        return "Estaca de Madeira";
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
