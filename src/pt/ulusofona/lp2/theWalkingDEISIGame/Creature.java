package pt.ulusofona.lp2.theWalkingDEISIGame;

public abstract class Creature {
    protected int id;
    protected int tipo;
    protected String nome;
    protected int x;
    protected int y;
    protected int equipamentos; //apanhado -> Humano
                                //destruido -> Zombie
    protected boolean verificaSaveHeaven;
    protected boolean estaEmJogo = true;

    public int getId(){
        return this.id;
    }
    public String getNome() {
        return this.nome;
    }
    public void addEquipamentos(){
        this.equipamentos++;
    }
    public void alteraCoordenada(int x,int y) {
        this.x = x;
        this.y = y;
    }
    public int getX () {
        return this.x;
    }
    public int getY () {
        return this.y;
    }
    public int getTipo() {
        return this.tipo;
    }
    public abstract Equipamento getEquipamentoAtual();
    public abstract String getImagePNG();
    public abstract String toString();
    public abstract boolean movimento(int xO, int yO, int xD, int yD);

    public boolean passouSaveHeaven() {
        return this.verificaSaveHeaven;
    }

    public boolean verificaEstaEmJogo() {
        return this.estaEmJogo;
    }

    public void alteraEstadoSave() {
        this.verificaSaveHeaven = true;
    }
}
