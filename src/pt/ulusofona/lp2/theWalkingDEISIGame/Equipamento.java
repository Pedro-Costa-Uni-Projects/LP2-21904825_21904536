package pt.ulusofona.lp2.theWalkingDEISIGame;

public class Equipamento {
    private int id;
    private int tipo;
    private int x;
    private int y;

    public Equipamento() {
    }

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

    public void alteraCoordenada(int x,int y) {
        this.x = x;
        this.y = y;
    }
}
