package pt.ulusofona.lp2.theWalkingDEISIGame.equipamentos;

import pt.ulusofona.lp2.theWalkingDEISIGame.Equipamento;

public class Veneno extends Equipamento {
    private int turnos;
    public Veneno(int id, int tipo, int x, int y) {
        super(id, tipo, x, y);
    }

    public String toString() {
        return "Veneno";
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