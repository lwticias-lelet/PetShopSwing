package model;

public class Cachorro extends Pet {
    private String raca;

    public Cachorro(String nome, int idade, Dono dono, String raca) {
        super(nome, idade, dono);
        this.raca = raca;
    }

    public String getRaca() { return raca; }

    @Override
    public String getTipo() {
        return "Cachorro";
    }

    @Override
    public String getDescricaoExtra() {
        return "Raça: " + raca;
    }
}
