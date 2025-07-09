package persistencia;

import model.*;
import java.io.*;
import java.util.*;

public class PetRepositorio {
    private List<Pet> pets = new ArrayList<>();
    private final String arquivo = "pets.csv";

    public PetRepositorio() {
        carregar();
    }

    public List<Pet> getTodos() {
        return pets;
    }

    public void adicionar(Pet p) {
        pets.add(p);
        salvar();
    }

    public void atualizar(int index, Pet p) {
        pets.set(index, p);
        salvar();
    }

    public void remover(int index) {
        pets.remove(index);
        salvar();
    }

    private void salvar() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(arquivo))) {
            for (Pet p : pets) {
                writer.printf("%s;%s;%d;%s;%s;%s%n",
                    p.getTipo(), p.getNome(), p.getIdade(),
                    p.getDono().getNome(), p.getDono().getTelefone(),
                    p.getDescricaoExtra().replace("Raça: ", "")
                );
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void carregar() {
        pets.clear();
        File f = new File(arquivo);
        if (!f.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(";");
                if (partes.length == 6) {
                    String tipo = partes[0];
                    String nome = partes[1];
                    int idade = Integer.parseInt(partes[2]);
                    Dono dono = new Dono(partes[3], partes[4]);
                    String extra = partes[5];

                    Pet p = tipo.equals("Gato")
                        ? new Gato(nome, idade, dono, extra.equalsIgnoreCase("Castrado"))
                        : new Cachorro(nome, idade, dono, extra);

                    pets.add(p);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
