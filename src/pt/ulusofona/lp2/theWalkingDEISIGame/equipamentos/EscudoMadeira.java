package pt.ulusofona.lp2.theWalkingDEISIGame.equipamentos;

import pt.ulusofona.lp2.theWalkingDEISIGame.Equipamento;

public class EscudoMadeira extends Equipamento {
    public EscudoMadeira(int id, int tipo, int x, int y) {
        super(id, tipo, x, y);
    }

    public String toString() {
        return "Escudo de Madeira | 1";
    }

    @Override
    public boolean isOfensivo() {
        return false;
    }

    @Override
    public boolean isDefensivo() {
        return true;
    }
}
