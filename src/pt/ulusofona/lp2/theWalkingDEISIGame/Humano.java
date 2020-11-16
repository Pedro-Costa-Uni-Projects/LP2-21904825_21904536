package pt.ulusofona.lp2.theWalkingDEISIGame;

public class Humano {
    int id;
    int tipo;
    String nome;
    int x;
    int y;
    int equipamentosApanhados;

    public Humano(int id, int tipo, String nome, int x, int y) {
        this.id = id;
        this.tipo = tipo;
        this.nome = nome;
        this.x = x;
        this.y = y;
    }

    public int getId(){
        return this.id;
    }

    public void addEquipamentosApanhados(){
        this.equipamentosApanhados++;
    }

    public void alteraCoordenada(int x,int y) {
        this.x = x;
        this.y = y;
    }

    public String getImagePNG() {
        return null;
    }

    public String toString() {
        return this.id + " | Humano" +  " | Os Vivos | " + this.nome + " " + this.equipamentosApanhados + " @ ("
                + this.x + ", " + this.y +")" ;
    }

    public int getX () {
        return this.x;
    }

    public int getY () {
        return this.y;
    }

}
