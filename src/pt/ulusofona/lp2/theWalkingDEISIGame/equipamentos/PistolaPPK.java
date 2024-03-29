package pt.ulusofona.lp2.theWalkingDEISIGame.equipamentos;

import pt.ulusofona.lp2.theWalkingDEISIGame.Equipamento;

public class PistolaPPK extends Equipamento {
    private int balas = 3;

    public PistolaPPK(int id, int tipo, int x, int y) {
        super(id, tipo, x, y);
    }
    public String toString() {
        return "Pistola Walther PPK | " + this.balas;
    }
    @Override
    public boolean isOfensivo() {
        return true;
    }
    @Override
    public boolean isDefensivo() {
        return false;
    }
    public boolean disparar() { // Adicionado para disparar;
        if(this.balas > 0) {
            this.balas--;
            return true;
        } else {
            return false;
        }
    }

}
