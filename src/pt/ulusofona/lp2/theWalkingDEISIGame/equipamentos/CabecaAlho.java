package pt.ulusofona.lp2.theWalkingDEISIGame.equipamentos;

import pt.ulusofona.lp2.theWalkingDEISIGame.Equipamento;

public class CabecaAlho extends Equipamento {
    public CabecaAlho(int id, int tipo, int x, int y) {
        super(id, tipo, x, y);
    }
    public String toString() {
        return "Cabeça de Alho";
    }
    public boolean isOfensivo() {
        return false;
    }
    public boolean isDefensivo() {
        return true;
    }

}
