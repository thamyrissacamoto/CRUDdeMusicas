package Main;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Desktop;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import tools.CaixaDeFerramentas;

/**
 *
 * @author thamyris
 */
public class GUI extends JFrame {

    Container cp;
    CaixaDeFerramentas cf = new CaixaDeFerramentas();

    Font fonte = new Font("Dialog ", Font.PLAIN, 15);
    Font fonte1 = new Font("Dialog ", Font.BOLD, 15);

    Color azulAcinzentado = new Color(108, 166, 205);
    Color azulClaro = new Color(135, 206, 255);
    Color roxo = new Color(147, 112, 219);
    Color cor1 = new Color(240, 255, 255);
    Color verde = new Color(69, 139, 0);
    Color cor2 = new Color(255, 215, 0);

    JPanel pnNorte = new JPanel();
    JPanel pnCentro = new JPanel();
    JPanel pnLeste = new JPanel();
    JPanel pnOeste = new JPanel();
    JPanel pnSul = new JPanel();

    JLabel lbBanda = new JLabel("Banda ou Cantor");
    JTextField tfBanda = new JTextField(20);

    JLabel lbMusica = new JLabel("Música");
    JTextField tfMusica = new JTextField(50);

    JLabel lbData = new JLabel("Data de lançamento da música");
    JTextField tfData = new JTextField(15);

    JLabel lbIdadeBanda = new JLabel("Ano de formação");
    JTextField tfIdadeBanda = new JTextField(10);

    JCheckBox checkBoxFim = new JCheckBox("Chegou ao fim", false);

    JButton btBuscar = new JButton("Buscar");
    JButton btAdicionar = new JButton("Adicionar");
    JButton btSalvar = new JButton("Salvar");
    JButton btAlterar = new JButton("Alterar");
    JButton btExcluir = new JButton("Excluir");
    JButton btListar = new JButton("Listar");
    JButton btCancelar = new JButton("Cancelar");
    JButton btPesquisar = new JButton("Pesquisar");

    Controle controle = new Controle();
    Banda banda = new Banda();
    String acao = "";

    /////////////////////////////////////////////////
    String[] colunas = new String[]{"Banda", "Música", "IdadeBanda", "Fim", "Data"};
    String[][] dados = new String[0][5];

    DefaultTableModel model = new DefaultTableModel(dados, colunas);
    JTable tabela = new JTable(model);

    private JScrollPane scrollTabela = new JScrollPane();

    private JPanel pnAvisos = new JPanel(new GridLayout(1, 1));
    private JPanel pnListagem = new JPanel(new GridLayout(1, 1));
    private JPanel pnVazio = new JPanel(new GridLayout(6, 1));

    private CardLayout cardLayout;

    public GUI() {
        setIcon();
        
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        cp = getContentPane();
        cp.setLayout(new BorderLayout());
        setTitle("Lista de Músicas");

        cp.add(pnNorte, BorderLayout.NORTH);
        cp.add(pnCentro, BorderLayout.CENTER);
        cp.add(pnSul, BorderLayout.SOUTH);
        cp.add(pnLeste, BorderLayout.EAST);
        cp.add(pnOeste, BorderLayout.WEST);

        pnNorte.setBackground(azulAcinzentado);
        pnLeste.setBackground(azulAcinzentado);
        pnOeste.setBackground(azulAcinzentado);
        pnCentro.setBackground(azulClaro);
        pnCentro.setBorder(BorderFactory.createLineBorder(roxo));
        checkBoxFim.setBackground(azulClaro);
        tfBanda.setBackground(cor1);
        tfData.setBackground(cor1);
        tfIdadeBanda.setBackground(cor1);
        tfMusica.setBackground(cor1);
        tfMusica.setForeground(verde);
        tfBanda.setForeground(verde);
        tfData.setForeground(verde);
        tfIdadeBanda.setForeground(verde);
        lbBanda.setFont(fonte1);
        tfBanda.setFont(fonte1);
        lbIdadeBanda.setFont(fonte1);
        tfIdadeBanda.setFont(fonte1);
        lbMusica.setFont(fonte1);
        tfMusica.setFont(fonte1);
        checkBoxFim.setFont(fonte1);
        btAdicionar.setFont(fonte1);
        btAlterar.setFont(fonte1);
        btBuscar.setFont(fonte1);
        btCancelar.setFont(fonte1);
        btExcluir.setFont(fonte1);
        btListar.setFont(fonte1);
        btSalvar.setFont(fonte1);
        btPesquisar.setFont(fonte1);
        lbData.setFont(fonte1);
        tfData.setFont(fonte1);
        tabela.setBackground(azulAcinzentado);
        tabela.setForeground(cor2);
        tabela.setFont(fonte1);

        pnNorte.setLayout(new FlowLayout(FlowLayout.LEFT));
        pnNorte.add(lbBanda);
        pnNorte.add(tfBanda);
        pnNorte.add(btBuscar);
        pnNorte.add(btAdicionar);
        pnNorte.add(btAlterar);
        pnNorte.add(btExcluir);
        pnNorte.add(btListar);
        pnNorte.add(btSalvar);
        pnNorte.add(btCancelar);

        btSalvar.setVisible(false);
        btAdicionar.setVisible(false);
        btAlterar.setVisible(false);
        btExcluir.setVisible(false);
        btCancelar.setVisible(false);
        btPesquisar.setVisible(false);

        tfMusica.setEditable(false);
        tfIdadeBanda.setEditable(false);
        tfData.setEditable(false);
        checkBoxFim.setEnabled(false);

        pnCentro.setLayout(new GridLayout(4, 2));
        pnCentro.add(lbMusica);
        pnCentro.add(tfMusica);
        pnCentro.add(lbIdadeBanda);
        pnCentro.add(tfIdadeBanda);
        pnCentro.add(lbData);
        pnCentro.add(tfData);
        pnCentro.add(checkBoxFim);
        pnCentro.add(btPesquisar);

        cardLayout = new CardLayout();
        pnSul.setLayout(cardLayout);

        for (int i = 0; i < 5; i++) {
            pnVazio.add(new JLabel(" "));
        }
        pnSul.add(pnVazio, "vazio");
        pnSul.add(pnAvisos, "avisos");
        pnSul.add(pnListagem, "listagem");

        tabela.setEnabled(false);

        pnAvisos.add(new JLabel("Avisos"));

        String caminho = "Banda.csv";
        //carregar dados do HD para memória RAM
        controle.carregarDados(caminho);

        btBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(pnSul, "avisos");
                banda = controle.buscar(tfBanda.getText());
                if (banda != null) {//achou o banda na lista
                    //mostrar
                    btAdicionar.setVisible(false);
                    btAlterar.setVisible(true);
                    btPesquisar.setVisible(true);
                    btListar.setVisible(true);
                    btExcluir.setVisible(true);
                    tfMusica.setText(banda.getMusica());
                    tfIdadeBanda.setText(String.valueOf(banda.getIdadeBanda()));
                    tfData.setText(cf.converteDeDateParaString(banda.getDataLancamento()));
                    checkBoxFim.setSelected(banda.isFim());

                } else {//não achou na lista
                    //mostrar botão incluir

                    tfMusica.setText("");
                    tfIdadeBanda.setText("");
                    tfData.setText("");
                    checkBoxFim.setSelected(false);
                    btAdicionar.setVisible(true);
                    tfMusica.setEditable(false);
                    tfIdadeBanda.setEditable(false);
                    tfData.setEditable(false);
                    checkBoxFim.setEnabled(false);
                    btAlterar.setVisible(false);
                    btExcluir.setVisible(false);
                    btPesquisar.setVisible(false);
                }
            }
        });

        btAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tfBanda.setEditable(false);
               tfMusica.setEditable(true);
                tfMusica.requestFocus();
                tfIdadeBanda.setEditable(true);
                tfData.setEditable(true);
                checkBoxFim.setEnabled(true);
                btAdicionar.setVisible(false);
                btSalvar.setVisible(true);
                btCancelar.setVisible(true);
                btBuscar.setVisible(false);
                btListar.setVisible(false);
                btPesquisar.setVisible(true);
                acao = "adicionar";

            }
        });

        btSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Banda bandaAntigo = banda;
                if (acao.equals("adicionar")) {
                    banda = new Banda();
                }

                banda.setBanda(tfBanda.getText());
                banda.setMusica(tfMusica.getText());
                banda.setIdadeBanda(Integer.valueOf(tfIdadeBanda.getText()));
                banda.setFim(checkBoxFim.isSelected());
                banda.setDataLancamento(cf.converteDeStringParaDate(tfData.getText()));
                banda.setIdadeBanda(Integer.valueOf(tfIdadeBanda.getText()));
                btPesquisar.setVisible(false);
                if (acao.equals("adicionar")) {
                    controle.adicionar(banda);
                } else {
                    controle.alterar(banda, bandaAntigo);
                }
                btSalvar.setVisible(false);
                btCancelar.setVisible(false);
                tfBanda.setEnabled(true);
                tfBanda.setEditable(true);
                tfBanda.requestFocus();
                tfBanda.setText("");

                tfMusica.setText("");
                tfIdadeBanda.setText("");
                tfData.setText("");
                checkBoxFim.setSelected(false);
                btBuscar.setVisible(true);
                btListar.setVisible(true);

                tfMusica.setEditable(false);
                tfIdadeBanda.setEditable(false);
                tfData.setEditable(false);
                checkBoxFim.setEnabled(false);

            }
        });

        btAlterar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btBuscar.setVisible(false);
                btAlterar.setVisible(false);
                tfBanda.setEditable(false);
                tfMusica.setEditable(true);
                tfIdadeBanda.setEditable(true);
                tfData.setEditable(true);
                checkBoxFim.setEnabled(true);
                tfMusica.requestFocus();
                btSalvar.setVisible(true);
                btCancelar.setVisible(true);
                btListar.setVisible(false);
                btExcluir.setVisible(false);
                btPesquisar.setVisible(true);
                acao = "alterar";

            }
        });

        btExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int response = JOptionPane.showConfirmDialog(cp, "Deseja excluir?", "Confirmar exclusão",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                btExcluir.setVisible(false);
                tfBanda.setEnabled(true);
                tfBanda.setEditable(true);
                tfBanda.requestFocus();
                tfBanda.setText("");

                tfMusica.setText("");
                tfIdadeBanda.setText("");
                tfData.setText("");
                checkBoxFim.setSelected(false);
                btBuscar.setVisible(true);

                tfMusica.setEditable(false);
                tfIdadeBanda.setEditable(false);
                tfData.setEditable(false);
                checkBoxFim.setEnabled(false);

                btAlterar.setVisible(false);
                btPesquisar.setVisible(false);

                if (response == JOptionPane.YES_OPTION) {
                    controle.excluir(banda);
                }
            }
        });

        btListar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Banda> listaBanda = controle.listar();

                String[] colunas = {"Banda", "Musica", "Ano de formação", "Chegou ao fim", "Data de lançamento"};
                Object[][] dados = new Object[listaBanda.size()][colunas.length];
                String aux[];
                for (int i = 0; i < listaBanda.size(); i++) {
                    aux = listaBanda.get(i).toString().split(";");
                    for (int j = 0; j < colunas.length; j++) {
                        dados[i][j] = aux[j];
                    }
                }
                cardLayout.show(pnSul, "listagem");
                scrollTabela.setPreferredSize(tabela.getPreferredSize());
                pnListagem.add(scrollTabela);
                scrollTabela.setViewportView(tabela);
                model.setDataVector(dados, colunas);

                btAlterar.setVisible(false);
                btExcluir.setVisible(false);
                btAdicionar.setVisible(false);
                btPesquisar.setVisible(false);
            }
        });

        btCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btCancelar.setVisible(false);
                tfBanda.setText("");
                tfBanda.requestFocus();
                tfBanda.setEnabled(true);
                tfBanda.setEditable(true);

                tfMusica.setText("");
                tfIdadeBanda.setText("");
                tfData.setText("");
                tfMusica.setEditable(false);
                tfData.setEditable(false);
                tfIdadeBanda.setEditable(false);
                checkBoxFim.setEnabled(false);

                checkBoxFim.setSelected(false);
                btBuscar.setVisible(true);
                btListar.setVisible(true);
                btSalvar.setVisible(false);
                btCancelar.setVisible(false);
                btPesquisar.setVisible(false);

            }
        });

        btPesquisar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String texto = tfBanda.getText();
                String texto1 = tfMusica.getText();

                int tamanho = texto.length();
                for (int i = 0; i <= tamanho; i++) {
                    if (i == tamanho) {
                        texto = texto + " ";
                    }
                }

                String banda = texto.replace(" ", "+");
                String musica = texto1.replace(" ", "+");
                URI link = null;
                try {
                    link = new URI("https://www.youtube.com/results?search_query=" + banda + musica);
                } catch (URISyntaxException ex) {
                    Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    Desktop.getDesktop().browse(link);
                } catch (IOException ex) {
                    Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                //antes de sair, salvar a lista em disco
                controle.gravarLista(caminho);
                // Sai da classe
                dispose();
            }
        });

        setSize(800, 300);
        setLocationRelativeTo(null);//centraliza na tela
        setVisible(true);

    }

    private void setIcon() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("2.png")));
    }
}