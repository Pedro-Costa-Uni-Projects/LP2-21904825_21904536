package pt.ulusofona.lp2.theWalkingDEISIGame;

public class Humano extends Creature{
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

    public void addEquipamentosAtual(Equipamento equipamento){
        this.equipamentoAtual = equipamento;
    }

    public String getImagePNG() {
        return "Humano.png";
    }

    public String toString() {
        return this.id + " | Humano" +  " | Os Vivos | " + this.nome + " " + this.equipamentos + " @ ("
                + this.x + ", " + this.y +")" ;
    }

}
