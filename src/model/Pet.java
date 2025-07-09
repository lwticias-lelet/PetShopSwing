package model;

public abstract class Pet {
    protected String nome;
    protected int idade;
    protected Dono dono;

    public Pet(String nome, int idade, Dono dono) {
        this.nome = nome;
        this.idade = idade;
        this.dono = dono;
    }

    public String getNome() { return nome; }
    public int getIdade() { return idade; }
    public Dono getDono() { return dono; }

    public void setNome(String nome) { this.nome = nome; }
    public void setIdade(int idade) { this.idade = idade; }
    public void setDono(Dono dono) { this.dono = dono; }

    public abstract String getTipo();
    public abstract String getDescricaoExtra();
}
