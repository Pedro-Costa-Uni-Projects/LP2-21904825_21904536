package pt.ulusofona.lp2.theWalkingDEISIGame;

public class Equipamento {
    int id;
    int tipo;
    int x;
    int y;

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
}
