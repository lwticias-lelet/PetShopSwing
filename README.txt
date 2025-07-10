para rodar 
javac -d bin src\model\*.java src\persistencia\*.java src\view\*.java src\Main.java
java -cp bin Main




  ------ Requisitos da P3 e ond encontra-los no codigo
  temos uma bse de dados, funções de adicionar, remover e alterar
               Uso de Collections
Onde está? na classe PetRepositorio.java
private List<Pet> pets = new ArrayList<>();
A lista pets guarda os objetos do tipo Pet (e suas subclasses Gato e Cachorro).
Usamos add(), set(), remove() e get() para manipular os dados.
public List<Pet> getTodos() {
    return pets;
}
--------------------------------------------------------------------------------------------
Uso de Herança e Polimorfismo (sem contar toString)
                         Classes envolvidas:

abstract class Pet { ... }
class Gato extends Pet { ... }
class Cachorro extends Pet { ... }
Pet é a superclasse abstrata, que define os métodos getTipo() e getDescricaoExtra() como abstratos.
Gato e Cachorro são subclasses que implementam esses métodos de forma diferente (polimorfismo real).

// Polimorfismo
Pet p = new Gato(...);  // ou: new Cachorro(...)
repositorio.adicionar(p);  // aceita ambos
No repositório, e principalmente na GUI, manipulamos pets sem saber o tipo específico (Gato ou Cachorro)       
-------------------------------------------------------------------------------------------------------------
                                                Uso de armazenamento permanente
Onde está? na classe PetRepositorio.java
private final String arquivo = "pets.csv";   
Durante cada manipulação o registro é inserido nocsv que é gerado automaticamentes em pets.csv
---------------------------------------------------------------------------------------------------------------
5. Interface Gráfica
Onde está: na classe TelaPetShop.java (em src/view/)        
