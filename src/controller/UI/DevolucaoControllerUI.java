package controller.UI;

import dao.DevolucaoDao;
import dao.DevolucaoDaoBd;
import dao.LivroDao;
import dao.LivroDaoBd;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import model.Aluguel;
import model.Devolucao;
import model.Livro;
import util.DateUtil;
import util.PrintUtil;
import view.Aluguel.UI.JanelaCrudAluguel;
import view.Devolucao.UI.DevolucaoTableModel;
import view.Devolucao.UI.JanelaCrudDevolucao;
import view.Devolucao.UI.PainelCadastroDevolucao;
import view.Devolucao.UI.PainelDevolucao;

public class DevolucaoControllerUI {

    private final static int TABELA = 0;
    private final static int FORMCADASTRO = 1;
    private final static int FORMEDICAO = 2;
    private final static int FORMVISUALIZACAO = 3;

    private int telaAtual = 0;
    private int linhaSelecionada = -1;

    private JanelaCrudDevolucao janela;
    private AluguelControllerUI controllerAluguel;
    private LivroControllerUI controllerLivro;

    public DevolucaoControllerUI() {
        telaAtual = TABELA;
    }

    public void setJanela(JanelaCrudDevolucao janela) {
        this.janela = janela;
    }

    /**
     * Método utilizado para visualização dos livros
     */
    public void visualizarLivro() {
        PainelDevolucao painelTabela = this.janela.getPainelTabela();
        PainelCadastroDevolucao form = janela.getPainelFormulario();
        DevolucaoTableModel tableModel = (DevolucaoTableModel) painelTabela.getTabelaDevolucao().getModel();

        linhaSelecionada = painelTabela.getTabelaDevolucao().getSelectedRow();
        if (linhaSelecionada < 0) {
            PrintUtil.printMessageError(janela, "Não há nenhum elemento selecionado na tabela");
            return;
        }
        Livro li = tableModel.getLivro(linhaSelecionada);
        form.carregaDados(Long.toString(li.getIsbn()), li.getTitulo(), li.getEditora(), li.getAutor(), li.getAnoPublicacao());
        form.getLabelPainelFormulario().setText("Visualizar Livro");
        form.getBotaoSalvar().setVisible(true);
        form.getBotaoSalvar().setText("Devolver");

        telaAtual = FORMVISUALIZACAO;
        this.janela.mostrarPainel(JanelaCrudDevolucao.PAINELFORM);
    }

    /**
     * Método utilizado para fazer a inserção do aluguel no banco de dados
     *
     * @param rg
     * @param idAluguel
     */
    public void salvarDevolucao(long rg, int idAluguel) {
        boolean atrasado = false;
        PainelDevolucao painelTabela = this.janela.getPainelTabela();
        DevolucaoTableModel tableModel = (DevolucaoTableModel) painelTabela.getTabelaDevolucao().getModel();
        try {
            controllerAluguel = new AluguelControllerUI();
            Aluguel alu = controllerAluguel.buscarPorCodigo(idAluguel);
            linhaSelecionada = painelTabela.getTabelaDevolucao().getSelectedRow();
            if (alu.getC().getRg() != rg) {
                PrintUtil.printMessageError(janela, "Você não possui livro alugado com este código!");
                return;
            } else if (alu.getLivrosAlugados().isDisponibilidade() == true) {
                PrintUtil.printMessageError(janela, "Este livro já foi devolvido!");
                return;
            } else {
                Date data = new Date();
                data = Date.from(Instant.now());
                int qntDias = difDatas(alu, data);
                if (qntDias > 7) {
                    double multa = 1.00 * qntDias;
                    PrintUtil.printMessageError(janela, "Este livro está atrasado, acerte a Multa no valor de " + multa + " com a administração!");
                    atrasado = true;
                }
                DevolucaoDao dao = new DevolucaoDaoBd();
                dao.inserir(new Devolucao(alu, data), atrasado);
                PrintUtil.printMessageSucesso(janela, "Livro devolvido com sucesso!");
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
        PainelDevolucao painelTabela = this.janela.getPainelTabela();
        DevolucaoTableModel tableModel = (DevolucaoTableModel) painelTabela.getTabelaDevolucao().getModel();

        LivroDao dao = new LivroDaoBd();
        tableModel.setLivro(dao.listar());

        painelTabela.getTabelaDevolucao().updateUI();
    }

    /**
     * Método que comunica a classe DevolucaoDao da inserção de uma devolução;
     *
     * @param d - recebe um objeto Devolucao;
     * @param atrasado - recebe um boolean 'true' ou 'false' da devolução do
     * livro;
     */
    public void addDevolucao(Devolucao d, boolean atrasado) {
        new DevolucaoDaoBd().inserir(d, atrasado);
    }

    /**
     * Método que se comunica com a classe DevolucaoDao para listar as
     * devoluções;
     *
     * @return retorna a lista de devoluções do banco de dados;
     */
    public List<Devolucao> listarDevolucao() {
        return (new DevolucaoDaoBd().listar());
    }

    /**
     * Método que solicita ao DevolucaoDao que busque, por código, a devolução
     * de um livro;
     *
     * @param id - recebe o ID de uma devolução;
     * @return retorna o objeto Devolucao encontrado no banco de dados;
     */
    public Devolucao buscarPorCodigo(int id) {
        DevolucaoDao dao = new DevolucaoDaoBd();
        Devolucao devolucao = dao.procurarPorId(id);
        return devolucao;
    }

    /**
     * Método que calcula a diferença de duas datas;
     *
     * @param aluguel - recebe a data do aluguel;
     * @param date - recebe a data da devolução;
     * @return
     */
    public int difDatas(Aluguel aluguel, Date date) {
        long tempo1 = aluguel.getDataAluguel().getTime();
        long tempo2 = date.getTime();
        long difTempo = tempo2 - tempo1;
        return (int) ((difTempo + 60L * 60 * 1000) / (24L * 60 * 60 * 1000)) + 1;
    }

    /**
     * Método para mostrar os dados de todas as devoluções;
     */
    public void mostrarDevolucao() {
        System.out.println("-----------------------------\n");
        System.out.println(String.format("%-20s", "|Código da devolução") + "\t"
                + String.format("%-20s", "|Nome do cliente")
                + String.format("%-20s", "  |Titulo do livro alugado")
                + String.format("%-20s", "    |Data da devolução"));
        for (Devolucao devolucao : listarDevolucao()) {
            System.out.println(String.format("%-10s", devolucao.getIdDevolucao()) + "\t"
                    + String.format("%-20s", "        |" + devolucao.getAluguel().getC().getNome()) + "\t"
                    + String.format("%-20s", "      |" + devolucao.getAluguel().getLivrosAlugados().getTitulo() + "\t"
                            + String.format("%-20s", "                  |" + DateUtil.dateToString(devolucao.getDataDevolucao()))));
        }
    }

}
