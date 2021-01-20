package pt.ulusofona.lp2.theWalkingDEISIGame;

public abstract class Creature {
    protected int id;
    protected int tipo;
    protected String nome;
    protected int x;
    protected int y;
    protected int equipamentos; //apanhado -> Humano - : - destruido -> Zombie
    protected int interacoes;
    protected boolean verificaSaveHeaven;
    protected boolean vivo = true;

    public int getId(){
        return this.id;
    }
    public String getNome() {
        return this.nome;
    }
    public void addEquipamentos(){
        this.equipamentos++;
    }
    public int getNumEquipamentos() {
        return this.equipamentos;
    }
    public void addXEquipamentos(int num) {
        this.equipamentos = num;
    }
    public void addXInteracoes(int num) {
        this.interacoes = num;
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
    public void mata() {
        this.vivo = false;
    }
    public void alteraEstadoSave() {
        this.verificaSaveHeaven = true;
    }
    public void addInteracoes(){
        this.interacoes++;
    }
    public int getNumInteracoes() {
        return this.interacoes;
    }

}
