package pt.ulusofona.lp2.theWalkingDEISIGame.equipamentos;

import pt.ulusofona.lp2.theWalkingDEISIGame.Equipamento;

public class Antidoto extends Equipamento {
    private boolean cheio = true;

    public Antidoto(int id, int tipo, int x, int y) {
        super(id, tipo, x, y);
    }
    public String toString() {
        if (this.cheio) {
            return "Antídoto | Cheio";
        }
        return "Antídoto | Vazio";
    }
    public boolean isOfensivo() {
        return false;
    }
    public boolean isDefensivo() {
        return true;
    }

}
