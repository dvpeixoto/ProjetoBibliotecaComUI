package controller.UI;

import dao.AluguelDao;
import dao.AluguelDaoBd;
import dao.LivroDao;
import dao.LivroDaoBd;
import java.util.Date;
import java.util.List;
import model.Aluguel;
import model.Cliente;
import model.Livro;
import util.DateUtil;
import util.PrintUtil;
import view.Aluguel.UI.AluguelTableModel;
import view.Aluguel.UI.JanelaCrudAluguel;
import view.Aluguel.UI.PainelAluguel;
import view.Aluguel.UI.PainelCadastroAluguel;

public class AluguelControllerUI {

    private final static int TABELA = 0;
    private final static int FORMCADASTRO = 1;
    private final static int FORMEDICAO = 2;
    private final static int FORMVISUALIZACAO = 3;

    private int telaAtual = 0;
    private int linhaSelecionada = -1;

    private JanelaCrudAluguel janela;
    private ClienteControllerUI controllerCliente;
    private LivroControllerUI controllerLivro;

    public AluguelControllerUI() {
        telaAtual = TABELA;
    }

    public void setJanela(JanelaCrudAluguel janela) {
        this.janela = janela;
     
    }
    
    /**
     * Método utilizado para visualização dos livros
     */
    public void visualizarLivro() {
        PainelAluguel painelTabela = this.janela.getPainelTabela();
        PainelCadastroAluguel form = janela.getPainelFormulario();
        AluguelTableModel tableModel = (AluguelTableModel) painelTabela.getTabelaAluguel().getModel();

        linhaSelecionada = painelTabela.getTabelaAluguel().getSelectedRow();
        if (linhaSelecionada < 0) {
            PrintUtil.printMessageError(janela, "Não há nenhum elemento selecionado na tabela");
            return;
        }
        Livro li = tableModel.getLivro(linhaSelecionada);
        form.carregaDados(Long.toString(li.getIsbn()), li.getTitulo(), li.getEditora(), li.getAutor(), li.getAnoPublicacao());
        form.getLabelPainelFormulario().setText("Visualizar Livro");
        form.getBotaoSalvar().setVisible(true);
        form.getBotaoSalvar().setText("Alugar");

        telaAtual = FORMVISUALIZACAO;
        this.janela.mostrarPainel(JanelaCrudAluguel.PAINELFORM);
    }

    /**
     * Método utilizado para fazer a inserção do aluguel no banco de dados
     * @param rg 
     */
    public void salvarAluguel(long rg) {
        PainelAluguel painelTabela = this.janela.getPainelTabela();
        AluguelTableModel tableModel = (AluguelTableModel) painelTabela.getTabelaAluguel().getModel();
        try {
            controllerCliente = new ClienteControllerUI();
            Cliente cli = controllerCliente.buscarClientePorRg(rg);
            if (cli.getLivrosAlugados() == 3) {
                PrintUtil.printMessageError(janela, "Você já possui o máximo permitido de livros alugados!");
                return;
            } else {
                Date dateFormat = new Date();
                java.sql.Date dataSql;
                dateFormat = new java.sql.Date(dateFormat.getTime());
                dataSql = (java.sql.Date) dateFormat;
                linhaSelecionada = painelTabela.getTabelaAluguel().getSelectedRow();
                int idLivro = tableModel.getLivro(linhaSelecionada).getCod();
                controllerLivro = new LivroControllerUI();
                Livro li = controllerLivro.buscarLivroPorCod(idLivro);
                if (li.isDisponibilidade() == false) {
                    PrintUtil.printMessageError(janela, "Livro já está alugado!");
                    return;
                } else {
                    AluguelDao dao = new AluguelDaoBd();
                    dao.inserir(new Aluguel(dateFormat, cli, li));
                    PrintUtil.printMessageSucesso(janela, "Livro alugado com sucesso!");
                }
            }
        } catch (Exception e) {
            PrintUtil.printMessageError(janela, "Campo Inválido!");
        }
    }

    /**
     * Método utilizado para voltar ao menu principal
     */
    public void voltarPrincipal() {
        telaAtual = TABELA;
        this.atualizaTabela();
        this.janela.mostrarPainel(JanelaCrudAluguel.PAINELTABELA);
    }

    /**
     * Método utilizado para atualizar dados na tabela
     */
    public void atualizaTabela() {
        PainelAluguel painelTabela = this.janela.getPainelTabela();
        AluguelTableModel tableModel = (AluguelTableModel) painelTabela.getTabelaAluguel().getModel();

        LivroDao dao = new LivroDaoBd();
        tableModel.setLivro(dao.listar());

        painelTabela.getTabelaAluguel().updateUI();
    }

    /**
     * Método booleano que se comunica com a classe AluguelDao para verificar se
     * o código do aluguel de um livro existe no banco de dados;
     *
     * @param id - recebe o ID de um livro;
     * @return retorna 'true' ou 'false';
     */
    public boolean CodigoExiste(int id) {
        AluguelDao dao = new AluguelDaoBd();
        Aluguel aluguel = dao.procurarPorId(id);
        if (aluguel != null) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Método que comunica a classe AluguelDao da inserção de um novo aluguel;
     *
     * @param a - recebe um objeto aluguel de um livro;
     */
    public void addAluguel(Aluguel a) {
        new AluguelDaoBd().inserir(a);
    }

    /**
     * Método que se comunica com a classe AluguelDao para listar os alugueis de
     * livros existentes;
     *
     * @return retorna a lista de alugueis do banco de dados;
     */
    public List<Aluguel> listarAluguel() {
        return (new AluguelDaoBd().listar());
    }

    /**
     * Método que solicita ao AluguelDao que busque as informações de um aluguel
     * pelo código do mesmo;
     *
     * @param id - recebe o ID de um aluguel;
     * @return retorna o objeto aluguel encontrado no banco de dados;
     */
    public Aluguel buscarPorCodigo(int id) {
        AluguelDao dao = new AluguelDaoBd();
        Aluguel aluguel = dao.procurarPorId(id);
        return aluguel;
    }

    /**
     * Método que mostra os dados de todos os alugueis realizados;
     */
    public void mostrarAluguel() {
        System.out.println("-----------------------------\n");
        System.out.println(String.format("%-20s", "|Código do Aluguel") + "\t"
                + String.format("%-20s", "|Nome do cliente")
                + String.format("%-20s", "  |Titulo do livro alugado")
                + String.format("%-20s", "    |Data do aluguel:"));
        for (Aluguel aluguel : listarAluguel()) {
            System.out.println(String.format("%-10s", aluguel.getId()) + "\t"
                    + String.format("%-20s", "        |" + aluguel.getC().getNome()) + "\t"
                    + String.format("%-20s", "      |" + aluguel.getLivrosAlugados().getTitulo() + "\t"
                            + String.format("%-20s", "                  |" + DateUtil.dateToString(aluguel.getDataAluguel()))));
        }
    };
    
}
