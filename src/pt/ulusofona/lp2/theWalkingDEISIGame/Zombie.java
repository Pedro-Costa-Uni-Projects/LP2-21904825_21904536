package pt.ulusofona.lp2.theWalkingDEISIGame;

public class Zombie {
    int id;
    int tipo;
    String nome;
    int x;
    int y;
    int equipamentosDestruidos;

    public Zombie(int id, int tipo, String nome, int x, int y) {
        this.id = id;
        this.tipo = tipo;
        this.nome = nome;
        this.x = x;
        this.y = y;
    }

    public int getId(){
        return this.id;
    }

    public String getImagePNG() {
        return "Zombie.png";
    }

    public String toString() {
        return this.id + " | " + this.tipo + " | Os Outros | " + this.nome + " " + this.equipamentosDestruidos + " @ ("
                + this.x + ", " + this.y +")" ;
    }

    public int getX () {
        return this.x;
    }

    public int getY () {
        return this.y;
    }
}
