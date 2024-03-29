package pt.ulusofona.lp2.theWalkingDEISIGame;

public abstract class Equipamento {
    protected int id;
    protected int tipo;
    protected int x;
    protected int y;
    protected int nrVezesQueSafou;

    public Equipamento(int id, int tipo, int x, int y) {
        this.id = id;
        this.tipo = tipo;
        this.x = x;
        this.y = y;
    }
    public int getX () {
        return this.x;
    }
    public int getY () {
        return this.y;
    }
    public int getId () {
        return this.id;
    }
    public int getTipo () {
        return this.tipo;
    }
    public int getNrVezesQueSafou () {
        return this.nrVezesQueSafou;
    }
    public void aumentaNrVezesQueSafou() {
        this.nrVezesQueSafou++;
    }
    public void alteraNrVezesQueSafou(int num) {
        this.nrVezesQueSafou = num;
    }
    public void alteraCoordenada(int x,int y) {
        this.x = x;
        this.y = y;
    }
    public abstract String toString();
    public abstract boolean isOfensivo();
    public abstract boolean isDefensivo();

}
