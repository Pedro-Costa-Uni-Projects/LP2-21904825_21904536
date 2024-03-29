package pt.ulusofona.lp2.theWalkingDEISIGame;

public abstract class Humano extends Creature{
    private Equipamento equipamentoAtual;
    private boolean poison;
    private int turnosPoison = 2;

    public Humano(int id, int tipo, String nome, int x, int y) {
        this.id = id;
        this.tipo = tipo;
        this.nome = nome;
        this.x = x;
        this.y = y;
    }
    public Equipamento getEquipamentoAtual() {
        return this.equipamentoAtual;
    }
    public void setEquipamentosAtual(Equipamento equipamento){
        this.equipamentoAtual = equipamento;
    }
    public boolean estadoVeneno() {
        return this.poison;
    }
    public void alteraVeneno(boolean estado) {
        this.poison = estado;
    }
    public boolean tiraTurnosPoison() {
        if (this.turnosPoison > 0) {
            this.turnosPoison--;
            return true;
        }
        return false;
    }
    public void reporTurnosPoison() {
        this.turnosPoison = 2;
    }
    public int mostraTurnos() {
        return this.turnosPoison;
    }

}
