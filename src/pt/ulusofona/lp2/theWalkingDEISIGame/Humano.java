package pt.ulusofona.lp2.theWalkingDEISIGame;

public abstract class Humano extends Creature{
    private Equipamento equipamentoAtual;

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

    public String getImagePNG() {
        return "Humano.png";
    }


}
