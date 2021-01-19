package pt.ulusofona.lp2.theWalkingDEISIGame;

public class InvalidTWDInitialFileException extends Exception{
    private int numeroCreatures;
    private boolean validoDefinicao = true;
    private String linhaFalhada;


    public boolean validNrOfCreatures() {
        return numeroCreatures >= 2;
    }

    public boolean validCreatureDefinition() {
        return this.validoDefinicao;
    }

    public String getErroneousLine() {
        return "";
    }

    public void setNumeroCreatures(int numeroCreatures) {
        this.numeroCreatures = numeroCreatures;
    }

    public void setValidoDefinicao(boolean validoDefinicao) {
        this.validoDefinicao = validoDefinicao;
    }

    public void setLinhaFalhada(String linhaFalhada) {
        this.linhaFalhada = linhaFalhada;
    }
}
