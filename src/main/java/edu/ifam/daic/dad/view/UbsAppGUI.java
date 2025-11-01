package edu.ifam.daic.dad.view;

import edu.ifam.daic.dad.DTO.DadosDemograficosDTO;
import edu.ifam.daic.dad.DTO.UbsDTO;
import edu.ifam.daic.dad.clientes.CepSoapClient;
import edu.ifam.daic.dad.mocks.MockDemographicsService;
import edu.ifam.daic.dad.mocks.MockMunicipioService;
import edu.ifam.daic.dad.mocks.MockUbsService;
import edu.soap.Estado;
import edu.soap.IBGEService;
import edu.soap.IBGEServiceImplementService;
import edu.soap.Municipio;
import soap.Cep;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.Vector;

public class UbsAppGUI extends JFrame {


    private JComboBox<String> estadoComboBox;
    private JComboBox<String> municipioComboBox;
    private JButton buscarButton;
    private JTable ubsTable;
    private DefaultTableModel tableModel;
    private JLabel statusLabel;

    // Campos de Demografia (com base na sua descrição)
    private JTextField popField, homensField, mulheresField;
    private JTextField f0_10_Field, f11_20_Field, f21_30_Field, f40_mais_Field;

    // --- Serviços (REAIS e Mocks) ---
    private IBGEService ibgePort;           // Cliente REAL do serviço IBGE
    private MockUbsService ubsService;      // Mock (simulação) do serviço de UBS
    private CepSoapClient cepClient;        // Cliente REAL do seu serviço de CEP

    // --- Dados de Estado ---
    private List<Municipio> municipiosAtuais; // Guarda a lista de municípios
    private Municipio municipioSelecionado; // Guarda o objeto completo

    public UbsAppGUI() {
        // 1. Inicializa serviços locais e mocks
        ubsService = new MockUbsService(); // (Mock de UBS ainda é usado)

        // 2. Configura a Janela
        setTitle("Dashboard de Saúde (UBS e Demografia)");
        setSize(800, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // 3. Monta a Interface (componentes visuais)
        initComponents();

        // 4. Inicia o carregamento de dados em background
        loadInitialData();
    }

    /**
     * Monta os componentes visuais da GUI.
     */
    private void initComponents() {
        // --- Painel Superior (Seleção) ---
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(new JLabel("Selecione o Estado:"));
        estadoComboBox = new JComboBox<>();
        estadoComboBox.setEnabled(false); // Começa desabilitado
        topPanel.add(estadoComboBox);

        topPanel.add(new JLabel("Município:"));
        municipioComboBox = new JComboBox<>();
        municipioComboBox.setEnabled(false); // Começa desabilitado
        topPanel.add(municipioComboBox);

        buscarButton = new JButton("Buscar Dados");
        buscarButton.setEnabled(false); // Começa desabilitado
        topPanel.add(buscarButton);
        statusLabel = new JLabel("Iniciando...");
        topPanel.add(statusLabel);

        // --- Painel Central (Resultados) ---
        JPanel centerPanel = new JPanel(new BorderLayout());
        JPanel demoPanel = createDemoPanel(); // Painel demográfico
        centerPanel.add(demoPanel, BorderLayout.NORTH);
        centerPanel.add(createUbsTablePanel(), BorderLayout.CENTER); // Tabela de UBS

        // Adiciona painéis à janela principal
        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);

        // --- AÇÕES (Eventos) ---

        // Ação 1: Quando selecionar um Estado -> Carrega Municípios
        estadoComboBox.addActionListener(e -> onEstadoSelect());

        // Ação 2: Quando selecionar um Município -> Habilita Busca
        municipioComboBox.addActionListener(e -> onMunicipioSelect());

        // Ação 3: Quando clicar em Buscar -> Carrega Dados de UBS
        buscarButton.addActionListener(e -> carregarDadosUbs());
    }

    /**
     * 1. (Background) Conecta-se aos serviços SOAP (CEP e IBGE).
     * 2. (Background) Carrega a lista de estados.
     * 3. (GUI) Preenche o dropdown de estados.
     */
    private void loadInitialData() {
        statusLabel.setText("Conectando aos serviços e carregando estados...");
        estadoComboBox.setEnabled(false);
        municipioComboBox.setEnabled(false);
        buscarButton.setEnabled(false);

        new SwingWorker<List<Estado>, Void>() {
            private String erro = null;

            @Override
            protected List<Estado> doInBackground() {
                try {
                    // Conecta ao seu serviço de CEP
                    cepClient = new CepSoapClient();

                    // Conecta ao serviço IBGE (como no TesteIBGE.java)
                    IBGEServiceImplementService serviceFactory = new IBGEServiceImplementService();
                    ibgePort = serviceFactory.getIBGEServiceImplementPort();

                    // Busca a lista de estados
                    return ibgePort.estados();
                } catch (Exception e) {
                    erro = e.getMessage();
                    return null;
                }
            }

            @Override
            protected void done() {
                try {
                    if (erro != null) {
                        throw new RuntimeException(erro);
                    }
                    List<Estado> estados = get();
                    estadoComboBox.addItem(""); // Item vazio
                    for (Estado est : estados) {
                        estadoComboBox.addItem(est.getNome());
                    }
                    estadoComboBox.setEnabled(true);
                    statusLabel.setText("Pronto. Selecione um estado.");
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(UbsAppGUI.this,
                            "Erro fatal ao conectar aos serviços SOAP.\nVerifique os servidores e a rede.\n" + e.getMessage(),
                            "Erro de Conexão", JOptionPane.ERROR_MESSAGE);
                    System.exit(1);
                }
            }
        }.execute();
    }

    /**
     * Ação 2: Chamado quando o ComboBox de Estado muda.
     * Carrega a lista de municípios do estado selecionado.
     */
    private void onEstadoSelect() {
        String estadoNome = (String) estadoComboBox.getSelectedItem();
        municipioComboBox.removeAllItems(); // Limpa

        if (estadoNome == null || estadoNome.isEmpty()) {
            municipioComboBox.setEnabled(false);
            buscarButton.setEnabled(false);
            return;
        }

        statusLabel.setText("Buscando municípios de " + estadoNome + "...");
        municipioComboBox.setEnabled(false);
        buscarButton.setEnabled(false);

        new SwingWorker<List<Municipio>, Void>() {
            @Override
            protected List<Municipio> doInBackground() {
                return ibgePort.listMunicipios(estadoNome);
            }

            @Override
            protected void done() {
                try {
                    municipiosAtuais = get(); // Salva a lista completa
                    municipioComboBox.addItem(""); // Item vazio
                    for (Municipio m : municipiosAtuais) {
                        municipioComboBox.addItem(m.getNmMun());
                    }
                    municipioComboBox.setEnabled(true);
                    statusLabel.setText("Pronto. Selecione um município.");
                } catch (Exception e) {
                    statusLabel.setText("Erro ao buscar municípios.");
                }
            }
        }.execute();
    }

    /**
     * Ação 3: Chamado quando o ComboBox de Município muda.
     * Guarda o objeto selecionado e habilita o botão "Buscar".
     */
    private void onMunicipioSelect() {
        int selectedIndex = municipioComboBox.getSelectedIndex();

        if (selectedIndex > 0) {
            // Pega o objeto completo da lista (índice -1 por causa do item vazio)
            municipioSelecionado = municipiosAtuais.get(selectedIndex - 1);
            buscarButton.setEnabled(true);

            // Pré-carrega os dados demográficos na GUI
            preencherCamposDemo(municipioSelecionado);
        } else {
            municipioSelecionado = null;
            buscarButton.setEnabled(false);
            limparCamposDemo();
        }
    }

    /**
     * Ação 4: Chamado pelo botão "Buscar".
     * Busca a lista de UBS (do mock) e completa com o serviço de CEP (real).
     */
    private void carregarDadosUbs() {
        if (municipioSelecionado == null) return;

        String nomeMun = municipioSelecionado.getNmMun();
        statusLabel.setText("Buscando UBS de " + nomeMun + "...");
        buscarButton.setEnabled(false);
        estadoComboBox.setEnabled(false);
        municipioComboBox.setEnabled(false);
        tableModel.setRowCount(0); // Limpa a tabela

        new SwingWorker<List<UbsDTO>, Void>() {
            private String erro = null;

            @Override
            protected List<UbsDTO> doInBackground() {
                try {
                    // 1. Chamar Serviço Mock de UBS (por NOME do MUNICÍPIO)
                    List<UbsDTO> listaUbs = ubsService.getListUbs(nomeMun);

                    // 2. ** LÓGICA DO SERVIÇO DE CEP (REAL) **
                    for (UbsDTO ubs : listaUbs) {
                        if (ubs.getLogradouro() == null && ubs.getCep() != null) {
                            // Chama seu serviço SOAP REAL
                            Cep cepCompleto = cepClient.buscarCep(ubs.getCep());
                            if (cepCompleto != null) {
                                ubs.setLogradouro(cepCompleto.getLogradouro());
                                ubs.setBairro(cepCompleto.getBairro());
                                ubs.setCidade(cepCompleto.getCidade());
                            }
                        }
                    }
                    return listaUbs;
                } catch (Exception e) {
                    erro = e.getMessage();
                    return null;
                }
            }

            @Override
            protected void done() {
                try {
                    if (erro != null) throw new RuntimeException(erro);

                    List<UbsDTO> listaUbs = get();
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
                    statusLabel.setText("UBS de " + nomeMun + " carregadas!");
                } catch (Exception e) {
                    statusLabel.setText("Erro ao buscar UBS: " + e.getMessage());
                } finally {
                    // Reabilita os controles
                    buscarButton.setEnabled(true);
                    estadoComboBox.setEnabled(true);
                    municipioComboBox.setEnabled(true);
                }
            }
        }.execute();
    }

    // --- Funções Helper (para limpar a GUI) ---

    private void preencherCamposDemo(Municipio m) {
        NumberFormat nf = NumberFormat.getInstance(new Locale("pt", "BR"));

        // ** AQUI USAMOS OS GETTERS DO OBJETO Municipio **
        // (Ajuste os nomes dos métodos se forem diferentes)
        popField.setText(nf.format(m.getCdMun()));
        homensField.setText(nf.format(m.getSexoMasculino()));
        mulheresField.setText(nf.format(m.getSexoFeminino()));
        f0_10_Field.setText(nf.format(m.getSexoIdade0A9())); // (Ex: 0-9 anos)
        f11_20_Field.setText(nf.format(m.getSexoIdade10A19()));// (Ex: 10-19 anos)
        f21_30_Field.setText(nf.format(m.getSexoIdade10A19()));// (Ex: 20-39 anos)
        f40_mais_Field.setText(nf.format(m.getSexoIdade40Mais()));// (Ex: 40+ anos)
    }

    private void limparCamposDemo() {
        popField.setText(""); homensField.setText(""); mulheresField.setText("");
        f0_10_Field.setText(""); f11_20_Field.setText(""); f21_30_Field.setText("");
        f40_mais_Field.setText("");
    }

    private JPanel createDemoPanel() {
        JPanel demoPanel = new JPanel(new GridBagLayout());
        demoPanel.setBorder(BorderFactory.createTitledBorder("Dados Demográficos"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0; gbc.gridy = 0; demoPanel.add(new JLabel("População Total:"), gbc);
        gbc.gridx = 1; popField = new JTextField(12); popField.setEditable(false); demoPanel.add(popField, gbc);
        gbc.gridx = 2; demoPanel.add(new JLabel("Homens:"), gbc);
        gbc.gridx = 3; homensField = new JTextField(12); homensField.setEditable(false); demoPanel.add(homensField, gbc);
        gbc.gridx = 4; demoPanel.add(new JLabel("Mulheres:"), gbc);
        gbc.gridx = 5; mulheresField = new JTextField(12); mulheresField.setEditable(false); demoPanel.add(mulheresField, gbc);

        gbc.gridy = 1;
        gbc.gridx = 0; demoPanel.add(new JLabel("0-10 anos:"), gbc);
        gbc.gridx = 1; f0_10_Field = new JTextField(12); f0_10_Field.setEditable(false); demoPanel.add(f0_10_Field, gbc);
        gbc.gridx = 2; demoPanel.add(new JLabel("11-20 anos:"), gbc);
        gbc.gridx = 3; f11_20_Field = new JTextField(12); f11_20_Field.setEditable(false); demoPanel.add(f11_20_Field, gbc);

        gbc.gridy = 2;
        gbc.gridx = 0; demoPanel.add(new JLabel("21-39 anos:"), gbc);
        gbc.gridx = 1; f21_30_Field = new JTextField(12); f21_30_Field.setEditable(false); demoPanel.add(f21_30_Field, gbc);
        gbc.gridx = 2; demoPanel.add(new JLabel("Acima de 40:"), gbc);
        gbc.gridx = 3; f40_mais_Field = new JTextField(12); f40_mais_Field.setEditable(false); demoPanel.add(f40_mais_Field, gbc);
        return demoPanel;
    }

    private JScrollPane createUbsTablePanel() {
        String[] colunas = {"Nome UBS", "CEP", "Logradouro", "Bairro", "Cidade", "UF"};
        tableModel = new DefaultTableModel(colunas, 0);
        ubsTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(ubsTable);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Unidades Básicas de Saúde (UBS)"));
        return scrollPane;
    }

    // --- Main ---
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new UbsAppGUI().setVisible(true);
        });
    }
}
