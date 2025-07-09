package view;

import model.*;
import persistencia.PetRepositorio;

import javax.swing.*;
import java.awt.*;

public class TelaPetShop extends JFrame {
    private JTextField nomeField = new JTextField(10);
    private JTextField idadeField = new JTextField(3);
    private JTextField donoField = new JTextField(10);
    private JTextField telefoneField = new JTextField(10);
    private JTextField racaField = new JTextField(10);
    private JCheckBox castradoCheck = new JCheckBox("Castrado");

    private JComboBox<String> tipoBox = new JComboBox<>(new String[]{"Gato", "Cachorro"});
    private DefaultListModel<Pet> listModel = new DefaultListModel<>();
    private JList<Pet> lista = new JList<>(listModel);

    private PetRepositorio repositorio = new PetRepositorio();

    public TelaPetShop() {
        super("🐾 Francisco Moedas PetShop 🐾");

        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(255, 240, 245));

        // Formulário
        JPanel painelForm = new JPanel(new GridLayout(8, 2, 5, 5));
        painelForm.setBorder(BorderFactory.createTitledBorder("🐶🐱 Cadastro de Pets"));
        painelForm.setBackground(new Color(255, 250, 240));

        painelForm.add(new JLabel("Nome do Pet:")); painelForm.add(nomeField);
        painelForm.add(new JLabel("Idade:")); painelForm.add(idadeField);
        painelForm.add(new JLabel("Tipo:")); painelForm.add(tipoBox);
        painelForm.add(new JLabel("Dono:")); painelForm.add(donoField);
        painelForm.add(new JLabel("Telefone:")); painelForm.add(telefoneField);
        painelForm.add(new JLabel("Raça (somente para cachorro):")); painelForm.add(racaField);
        painelForm.add(new JLabel("Castrado (somente para gato):")); painelForm.add(castradoCheck);

        // Botões
        JButton btnAdicionar = new JButton("Cadastrar");
        JButton btnAlterar = new JButton("Alterar");
        JButton btnRemover = new JButton(" Excluir");

        btnAdicionar.setBackground(new Color(255, 179, 71));
        btnAlterar.setBackground(new Color(135, 206, 250));
        btnRemover.setBackground(new Color(255, 99, 132));
        btnAdicionar.setForeground(Color.WHITE);
        btnAlterar.setForeground(Color.WHITE);
        btnRemover.setForeground(Color.WHITE);
        Font fonteBotao = new Font("Comic Sans MS", Font.BOLD, 14);
        btnAdicionar.setFont(fonteBotao);
        btnAlterar.setFont(fonteBotao);
        btnRemover.setFont(fonteBotao);

        JPanel painelBotoes = new JPanel();
        painelBotoes.setBackground(new Color(255, 240, 245));
        painelBotoes.add(btnAdicionar);
        painelBotoes.add(btnAlterar);
        painelBotoes.add(btnRemover);

        // Lista
        JPanel painelLista = new JPanel(new BorderLayout());
        painelLista.setBorder(BorderFactory.createTitledBorder(" Lista de Pets"));
        painelLista.setBackground(new Color(255, 250, 240));
        lista.setFont(new Font("Serif", Font.PLAIN, 14));
        painelLista.add(new JScrollPane(lista), BorderLayout.CENTER);

        // Título
        JLabel titulo = new JLabel(" Francisco Moedas PetShop ", JLabel.CENTER);
        titulo.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
        titulo.setForeground(new Color(255, 105, 180));
        titulo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        add(titulo, BorderLayout.NORTH);
        add(painelForm, BorderLayout.WEST);
        add(painelLista, BorderLayout.CENTER);
        add(painelBotoes, BorderLayout.SOUTH);

        // Ações
        carregarLista();

        btnAdicionar.addActionListener(e -> adicionar());
        btnAlterar.addActionListener(e -> alterar());
        btnRemover.addActionListener(e -> remover());

        tipoBox.addActionListener(e -> atualizarCampos());

        atualizarCampos();

        setSize(850, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void atualizarCampos() {
        String tipo = (String) tipoBox.getSelectedItem();
        boolean isGato = tipo.equals("Gato");

        racaField.setEnabled(!isGato);
        castradoCheck.setEnabled(isGato);
    }

    private void carregarLista() {
        listModel.clear();
        for (Pet p : repositorio.getTodos()) {
            listModel.addElement(p);
        }
    }

    private Pet criarPet() {
        try {
            String nome = nomeField.getText().trim();
            int idade = Integer.parseInt(idadeField.getText().trim());
            String tipo = (String) tipoBox.getSelectedItem();
            String donoNome = donoField.getText().trim();
            String telefone = telefoneField.getText().trim();
            Dono dono = new Dono(donoNome, telefone);

            if (tipo.equals("Gato")) {
                boolean castrado = castradoCheck.isSelected();
                return new Gato(nome, idade, dono, castrado);
            } else {
                String raca = racaField.getText().trim();
                return new Cachorro(nome, idade, dono, raca);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "❌ Erro ao preencher os campos.");
            return null;
        }
    }

    private void adicionar() {
        Pet p = criarPet();
        if (p != null) {
            repositorio.adicionar(p);
            carregarLista();
        }
    }

    private void alterar() {
        int idx = lista.getSelectedIndex();
        if (idx >= 0) {
            Pet p = criarPet();
            if (p != null) {
                repositorio.atualizar(idx, p);
                carregarLista();
            }
        }
    }

    private void remover() {
        int idx = lista.getSelectedIndex();
        if (idx >= 0) {
            repositorio.remover(idx);
            carregarLista();
        }
    }
}
