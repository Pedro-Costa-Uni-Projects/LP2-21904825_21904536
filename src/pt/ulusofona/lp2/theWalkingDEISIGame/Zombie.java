package pt.ulusofona.lp2.theWalkingDEISIGame;

public abstract class Zombie extends Creature{

    public Zombie(int id, int tipo, String nome, int x, int y) {
        this.id = id;
        this.tipo = tipo;
        this.nome = nome;
        this.x = x;
        this.y = y;
    }

    public Equipamento getEquipamentoAtual() {
        return null;
    }

}
