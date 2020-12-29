package pt.ulusofona.lp2.theWalkingDEISIGame.equipamentos;

import pt.ulusofona.lp2.theWalkingDEISIGame.Equipamento;

public class Veneno extends Equipamento {
    private boolean cheio = true;

    public Veneno(int id, int tipo, int x, int y) {
        super(id, tipo, x, y);
    }
    public String toString() {
        if (this.cheio) {
            return "Veneno | Cheio";
        }
        return "Veneno | Vazio";
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
