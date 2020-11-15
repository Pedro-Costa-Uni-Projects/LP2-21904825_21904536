package pt.ulusofona.lp2.theWalkingDEISIGame;

public class Zombie {
    int id;
    int tipo;
    String nome;
    int x;
    int y;

    public Zombie(int id, int tipo, String nome, int x, int y) {
        this.id = id;
        this.tipo = tipo;
        this.nome = nome;
        this.x = x;
        this.y = y;
    }
    //
    public int getId(){
        return this.id;
    }
    //
    public String getImagePNG() {
        return null;
    }
    //
    public String toString() {
        return "";
    }
}
