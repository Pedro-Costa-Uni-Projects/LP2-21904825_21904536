package pt.ulusofona.lp2.theWalkingDEISIGame;

public class InvalidTWDInitialFileException extends Exception{
    private boolean creaturas = true;
    private boolean validoDefinicao = true;
    private String linhaFalhada = "";


    public boolean validNrOfCreatures() {
        return this.creaturas;
    }

    public boolean validCreatureDefinition() {
        return this.validoDefinicao;
    }

    public String getErroneousLine() {
        return this.linhaFalhada;
    }

    public void setNumeroCreatures(boolean creaturas) {
        this.creaturas = creaturas;
    }

    public void setValidoDefinicao(boolean validoDefinicao) {
        this.validoDefinicao = validoDefinicao;
    }

    public void setLinhaFalhada(String linhaFalhada) {
        this.linhaFalhada = linhaFalhada;
    }
}
