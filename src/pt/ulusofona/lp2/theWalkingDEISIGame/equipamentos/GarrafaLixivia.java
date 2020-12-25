package pt.ulusofona.lp2.theWalkingDEISIGame.equipamentos;

import pt.ulusofona.lp2.theWalkingDEISIGame.Equipamento;

public class GarrafaLixivia extends Equipamento {
    float quantidade = 1;
    public GarrafaLixivia(int id, int tipo, int x, int y) {
        super(id, tipo, x, y);
    }

    public String toString() {
        return "Garrafa de lixívia (1 litro) | " + (int)this.quantidade;
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