package edu.ifam.daic.dad;

import soap.Cep;
import soap.CepSOAPService;

import soap.CepSOAPServiceImplService;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.net.URL;
import java.text.ParseException;

public class CepSoapClientGUI extends JFrame {

    private JFormattedTextField cepField; // Campo formatado para #####-###
    private JButton buscarButton;
    private JTextField logradouroField;
    private JTextField bairroField;
    private JTextField cidadeField;
    private JTextField ufField;
    private JLabel statusLabel;

    private CepSOAPService port;

    public CepSoapClientGUI() {
        // 1. Inicializa o cliente SOAP primeiro
        try {
            initSoapClient();
        } catch (Exception e) {
            // Se não puder conectar, mostra erro e fecha
            JOptionPane.showMessageDialog(this,
                    "Erro ao conectar ao serviço SOAP.\nVerifique se o servidor (PublicaServico) está no ar.\n\n" + e.getMessage(),
                    "Erro de Conexão",
                    JOptionPane.ERROR_MESSAGE);
            System.exit(1); // Fecha a aplicação
        }

        // 2. Inicializa a interface gráfica
        initComponents();

        // 3. Configura a janela principal
        setTitle("Cliente SOAP - Consulta CEP");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centraliza na tela
    }

    /**
     * Conecta-se ao WSDL e prepara o "port" para fazer chamadas.
     */
    private void initSoapClient() throws Exception {

        CepSOAPServiceImplService service = new CepSOAPServiceImplService();

        port = service.getCepSOAPServiceImplPort();

    }

    /**
     * Monta a interface gráfica (JFrame, JPanels, etc.)
     */
    private void initComponents() {
        // --- Painel de Input (CEP e Botão) ---
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());

        try {
            // Cria uma máscara para o campo de CEP
            MaskFormatter cepMask = new MaskFormatter("#####-###");
            cepField = new JFormattedTextField(cepMask);
            cepField.setColumns(10); // Tamanho do campo
        } catch (ParseException e) {
            cepField = new JFormattedTextField(); // Fallback
        }

        buscarButton = new JButton("Buscar");

        inputPanel.add(new JLabel("Digite o CEP:"));
        inputPanel.add(cepField);
        inputPanel.add(buscarButton);

        // --- Painel de Resultados (Labels e Campos de Texto) ---
        JPanel resultPanel = new JPanel();
        resultPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Espaçamento
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.LINE_START;

        // Label
        gbc.gridx = 0; gbc.gridy = 0;
        resultPanel.add(new JLabel("Logradouro:"), gbc);
        // Campo
        gbc.gridx = 1; gbc.weightx = 1.0; // Faz o campo esticar
        logradouroField = new JTextField();
        logradouroField.setEditable(false);
        resultPanel.add(logradouroField, gbc);

        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0.0;
        resultPanel.add(new JLabel("Bairro:"), gbc);
        gbc.gridx = 1; gbc.gridy = 1;
        bairroField = new JTextField();
        bairroField.setEditable(false);
        resultPanel.add(bairroField, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        resultPanel.add(new JLabel("Cidade:"), gbc);
        gbc.gridx = 1; gbc.gridy = 2;
        cidadeField = new JTextField();
        cidadeField.setEditable(false);
        resultPanel.add(cidadeField, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        resultPanel.add(new JLabel("UF:"), gbc);
        gbc.gridx = 1; gbc.gridy = 3;
        ufField = new JTextField();
        ufField.setEditable(false);
        resultPanel.add(ufField, gbc);

        // --- Painel de Status ---
        JPanel statusPanel = new JPanel();
        statusLabel = new JLabel("Aguardando consulta...");
        statusLabel.setForeground(Color.GRAY);
        statusPanel.add(statusLabel);

        // --- Monta a Janela Principal ---
        setLayout(new BorderLayout());
        add(inputPanel, BorderLayout.NORTH);
        add(resultPanel, BorderLayout.CENTER);
        add(statusPanel, BorderLayout.SOUTH);

        // --- Adiciona a Ação (Evento) ao Botão ---
        buscarButton.addActionListener(e -> onBuscarClick());
    }

    /**
     * Ação executada quando o botão "Buscar" é clicado.
     */
    private void onBuscarClick() {
        String cep = cepField.getText();

        // Desabilita o botão para evitar cliques duplos
        buscarButton.setEnabled(false);
        statusLabel.setText("Buscando CEP: " + cep + "...");
        statusLabel.setForeground(Color.BLUE);
        limparCampos();

        // --- IMPORTANTE: Roda a chamada de rede em uma Thread separada ---
        // Isso impede que a interface gráfica trave
        SwingWorker<Cep, Void> worker = new SwingWorker<Cep, Void>() {
            private String erro = null;

            @Override
            protected Cep doInBackground() throws Exception {
                // Esta é a chamada SOAP
                try {
                    return port.buscarCep(cep);
                } catch (Exception e) {
                    erro = e.getMessage();
                    return null;
                }
            }

            @Override
            protected void done() {
                // Este código roda de volta na thread da GUI
                try {
                    Cep resultado = get(); // Pega o resultado do doInBackground()

                    if (resultado != null) {
                        // Sucesso! Preenche os campos
                        logradouroField.setText(resultado.getLogradouro());
                        bairroField.setText(resultado.getBairro());
                        cidadeField.setText(resultado.getCidade());
                        ufField.setText(resultado.getUf());
                        statusLabel.setText("Endereço encontrado!");
                        statusLabel.setForeground(new Color(0, 128, 0)); // Verde
                    } else if (erro != null) {
                        // Erro de rede/servidor
                        statusLabel.setText("Erro na consulta: " + erro);
                        statusLabel.setForeground(Color.RED);
                    } else {
                        // Erro de lógica (CEP não encontrado)
                        statusLabel.setText("ENDEREÇO NÃO ENCONTRADO.");
                        statusLabel.setForeground(Color.RED);
                    }

                } catch (Exception e) {
                    // Erro inesperado
                    statusLabel.setText("Erro fatal: " + e.getMessage());
                    statusLabel.setForeground(Color.RED);
                } finally {
                    // Reabilita o botão
                    buscarButton.setEnabled(true);
                }
            }
        };

        worker.execute(); // Inicia a thread
    }

    /**
     * Limpa os campos de resultado
     */
    private void limparCampos() {
        logradouroField.setText("");
        bairroField.setText("");
        cidadeField.setText("");
        ufField.setText("");
    }

    // --- Método Main para rodar o cliente ---
    public static void main(String[] args) {
        // Garante que a GUI rode na Thread de Eventos do Swing
        SwingUtilities.invokeLater(() -> {
            new CepSoapClientGUI().setVisible(true);
        });
    }
}
