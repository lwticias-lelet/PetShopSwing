package model;

public class Gato extends Pet {
    private boolean castrado;

    public Gato(String nome, int idade, Dono dono, boolean castrado) {
        super(nome, idade, dono);
        this.castrado = castrado;
    }

    public boolean isCastrado() { return castrado; }

    @Override
    public String getTipo() {
        return "Gato";
    }

    @Override
    public String getDescricaoExtra() {
        return castrado ? "Castrado" : "Não castrado";
    }
}
