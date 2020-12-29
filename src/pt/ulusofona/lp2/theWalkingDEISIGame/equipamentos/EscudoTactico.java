package pt.ulusofona.lp2.theWalkingDEISIGame.equipamentos;

import pt.ulusofona.lp2.theWalkingDEISIGame.Equipamento;

public class EscudoTactico extends Equipamento {
    public EscudoTactico(int id, int tipo, int x, int y) {
        super(id, tipo, x, y);
    }
    public String toString() {
        return "Escudo Táctico";
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
