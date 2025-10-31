package edu.ifam.daic.dad.view;

import edu.ifam.daic.dad.DTO.DadosDemograficosDTO;
import edu.ifam.daic.dad.DTO.UbsDTO;
import edu.ifam.daic.dad.clientes.CepSoapClient;
import edu.ifam.daic.dad.mocks.MockDemographicsService;
import edu.ifam.daic.dad.mocks.MockUbsService;
import soap.Cep;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.Vector;

public class UbsAppGUI extends JFrame {

    private JComboBox<String> ufComboBox;
    private JButton buscarButton;
    private JTable ubsTable;
    private DefaultTableModel tableModel;
    private JLabel statusLabel;

    private JTextField popField, homensField, mulheresField;
    private JTextField f0_10_Field, f11_20_Field, f21_30_Field, f31_40_Field, f40_mais_Field;

    // --- Serviços (Mocks e Real) ---
    private MockDemographicsService demoService;
    private MockUbsService ubsService;
    private CepSoapClient cepClient; // Nosso cliente SOAP real

    public UbsAppGUI() {
        // 1. Inicializa os serviços
        demoService = new MockDemographicsService();
        ubsService = new MockUbsService();
        try {
            cepClient = new CepSoapClient();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao conectar ao Serviço de CEP.\n" + e.getMessage(), "Erro Fatal", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }

        // 2. Configura a Janela
        setTitle("Dashboard de Saúde (UBS e Demografia)");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // 3. Monta a Interface
        initComponents();
    }

    private void initComponents() {
        // --- Painel Superior (Seleção) ---
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(new JLabel("Selecione o Estado (UF):"));
        String[] ufs = {"", "AM", "AC", "SP", "RJ"};
        ufComboBox = new JComboBox<>(ufs);
        topPanel.add(ufComboBox);
        buscarButton = new JButton("Buscar Dados");
        topPanel.add(buscarButton);
        statusLabel = new JLabel("Pronto.");
        topPanel.add(statusLabel);

        // --- Painel Central (Resultados) ---
        JPanel centerPanel = new JPanel(new BorderLayout());

        // ** NOVO PAINEL DE DEMOGRAFIA **
        JPanel demoPanel = new JPanel(new GridBagLayout());
        demoPanel.setBorder(BorderFactory.createTitledBorder("Dados Demográficos"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        // Linha 0
        gbc.gridx = 0; gbc.gridy = 0;
        demoPanel.add(new JLabel("População Total:"), gbc);
        gbc.gridx = 1;
        popField = new JTextField(12); popField.setEditable(false);
        demoPanel.add(popField, gbc);

        gbc.gridx = 2;
        demoPanel.add(new JLabel("Homens:"), gbc);
        gbc.gridx = 3;
        homensField = new JTextField(12); homensField.setEditable(false);
        demoPanel.add(homensField, gbc);

        gbc.gridx = 4;
        demoPanel.add(new JLabel("Mulheres:"), gbc);
        gbc.gridx = 5;
        mulheresField = new JTextField(12); mulheresField.setEditable(false);
        demoPanel.add(mulheresField, gbc);

        // Linha 1 (Faixas Etárias)
        gbc.gridy = 1;
        gbc.gridx = 0; demoPanel.add(new JLabel("0-10 anos:"), gbc);
        gbc.gridx = 1; f0_10_Field = new JTextField(12); f0_10_Field.setEditable(false); demoPanel.add(f0_10_Field, gbc);

        gbc.gridx = 2; demoPanel.add(new JLabel("11-20 anos:"), gbc);
        gbc.gridx = 3; f11_20_Field = new JTextField(12); f11_20_Field.setEditable(false); demoPanel.add(f11_20_Field, gbc);

        gbc.gridx = 4; demoPanel.add(new JLabel("21-30 anos:"), gbc);
        gbc.gridx = 5; f21_30_Field = new JTextField(12); f21_30_Field.setEditable(false); demoPanel.add(f21_30_Field, gbc);

        // Linha 2 (Faixas Etárias)
        gbc.gridy = 2;
        gbc.gridx = 0; demoPanel.add(new JLabel("31-40 anos:"), gbc);
        gbc.gridx = 1; f31_40_Field = new JTextField(12); f31_40_Field.setEditable(false); demoPanel.add(f31_40_Field, gbc);

        gbc.gridx = 2; demoPanel.add(new JLabel("Acima de 40:"), gbc);
        gbc.gridx = 3; f40_mais_Field = new JTextField(12); f40_mais_Field.setEditable(false); demoPanel.add(f40_mais_Field, gbc);

        centerPanel.add(demoPanel, BorderLayout.NORTH);

        // Tabela de UBS (Idêntica à anterior)
        String[] colunas = {"Nome UBS", "CEP", "Logradouro", "Bairro", "Cidade", "UF"};
        tableModel = new DefaultTableModel(colunas, 0);
        ubsTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(ubsTable);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Unidades Básicas de Saúde (UBS)"));

        centerPanel.add(scrollPane, BorderLayout.CENTER);

        // Adiciona painéis à janela principal
        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);

        // --- Ação do Botão ---
        buscarButton.addActionListener(e -> carregarDados());
    }

    private void limparCamposDemo() {
        popField.setText(""); homensField.setText(""); mulheresField.setText("");
        f0_10_Field.setText(""); f11_20_Field.setText(""); f21_30_Field.setText("");
        f31_40_Field.setText(""); f40_mais_Field.setText("");
    }
    /**
     * Orquestra a busca de dados (em background).
     */
    private void carregarDados() {
        String ufSelecionada = (String) ufComboBox.getSelectedItem();
        if (ufSelecionada == null || ufSelecionada.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, selecione um estado.");
            return;
        }

        // Limpa a interface
        statusLabel.setText("Buscando dados para " + ufSelecionada + "...");
        buscarButton.setEnabled(false);
        limparCamposDemo();
        tableModel.setRowCount(0); // Limpa a tabela

        // Roda a lógica em uma SwingWorker (background thread)
        new SwingWorker<List<UbsDTO>, Void>() { // Alterado para retornar List<Ubs>
            private DadosDemograficosDTO dadosDemo;
            private String erro = null;

            @Override
            protected List<UbsDTO> doInBackground() {
                try {
                    // 1. Chamar Serviço Mock de Demografia
                    dadosDemo = demoService.getDados(ufSelecionada);

                    // 2. Chamar Serviço Mock de UBS
                    List<UbsDTO> listaUbs = ubsService.getListUbs(ufSelecionada);

                    // 3. ** A LÓGICA PRINCIPAL DA SUA APLICAÇÃO **
                    // Processar a lista de UBS e chamar o serviço de CEP se necessário
                    for (UbsDTO ubs : listaUbs) {
                        if (ubs.getLogradouro() == null && ubs.getCep() != null) {
                            // Endereço incompleto! Chamar nosso serviço SOAP REAL

                            // (Seu serviço de CEP)
                            Cep cepCompleto = cepClient.buscarCep(ubs.getCep());

                            if (cepCompleto != null) {
                                // Atualiza o objeto UBS com os dados do serviço de CEP
                                ubs.setLogradouro(cepCompleto.getLogradouro());
                                ubs.setBairro(cepCompleto.getBairro());
                                ubs.setCidade(cepCompleto.getCidade());
                            }
                        }
                    }
                    return listaUbs; // Retorna a lista processada
                } catch (Exception e) {
                    erro = e.getMessage();
                    return null;
                }
            }

            @Override
            protected void done() {
                // De volta à thread da GUI
                try {
                    List<UbsDTO> listaUbs = get(); // Pega a lista do doInBackground()

                    if (erro != null) {
                        statusLabel.setText("Erro: " + erro);
                    } else {
                        // Formata números com "pontos" (ex: 4.207.714)
                        NumberFormat nf = NumberFormat.getInstance(new Locale("pt", "BR"));

                        // Preenche os campos de demografia
                        popField.setText(nf.format(dadosDemo.getPopulacaoTotal()));
                        homensField.setText(nf.format(dadosDemo.getTotalHomens()));
                        mulheresField.setText(nf.format(dadosDemo.getTotalMulheres()));
                        f0_10_Field.setText(nf.format(dadosDemo.getFaixa0_10()));
                        f11_20_Field.setText(nf.format(dadosDemo.getFaixa11_20()));
                        f21_30_Field.setText(nf.format(dadosDemo.getFaixa21_30()));
                        f31_40_Field.setText(nf.format(dadosDemo.getFaixa31_40()));
                        f40_mais_Field.setText(nf.format(dadosDemo.getFaixaAcima40()));

                        // Preenche a tabela de UBS
                        for (UbsDTO ubs : listaUbs) {
                            Vector<String> row = new Vector<>();
                            row.add(ubs.getNome());
                            row.add(ubs.getCep());
                            row.add(ubs.getLogradouro() != null ? ubs.getLogradouro() : "N/A");
                            row.add(ubs.getBairro() != null ? ubs.getBairro() : "N/A");
                            row.add(ubs.getCidade());
                            row.add(ubs.getUf());
                            tableModel.addRow(row);
                        }
                        statusLabel.setText("Dados para " + ufSelecionada + " carregados!");
                    }
                } catch (Exception e) {
                    statusLabel.setText("Erro ao processar resultados: " + e.getMessage());
                } finally {
                    buscarButton.setEnabled(true);
                }
            }
        }.execute();
    }

    // --- Main ---
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new UbsAppGUI().setVisible(true);
        });
    }
}
