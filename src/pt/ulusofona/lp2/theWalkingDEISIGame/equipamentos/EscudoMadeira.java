package pt.ulusofona.lp2.theWalkingDEISIGame.equipamentos;

import pt.ulusofona.lp2.theWalkingDEISIGame.Equipamento;

public class EscudoMadeira extends Equipamento {
    private int hits = 1;
    private boolean utilizadoPorMilitar;
    public EscudoMadeira(int id, int tipo, int x, int y) {
        super(id, tipo, x, y);
    }
    public String toString() {
        return "Escudo de Madeira | " + this.hits;
    }
    @Override
    public boolean isOfensivo() {
        return false;
    }
    @Override
    public boolean isDefensivo() {
        return true;
    }
    public boolean retirar() {
        if(this.hits > 0) {
            this.hits -= 1;
            return true;
        } else {
            return false;
        }
    }
    public void foiUtilizadoPorMilitar() {
        this.utilizadoPorMilitar = true;
    }
    public boolean mostraSeFoiUtilizado() {
        return !this.utilizadoPorMilitar;
    }
    public int mostraHits() {
        return this.hits;
    }
    public void alteraHit() {
        if(!this.utilizadoPorMilitar) {
            this.hits = 2;
        } else {
            this.hits = 1;
        }
    }
    
}
