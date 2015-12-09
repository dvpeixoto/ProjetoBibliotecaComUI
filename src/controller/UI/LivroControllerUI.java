package controller.UI;

import dao.LivroDao;
import dao.LivroDaoBd;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import model.Livro;
import util.PrintUtil;
import view.Livro.UI.JanelaCrudLivro;
import view.Livro.UI.LivroTableModel;
import view.Livro.UI.PainelCadastroLivro;
import view.Livro.UI.PainelLivro;

/**
 * Classe controller para a classe Livro;
 * @author Tainara Specht
 * @author Diego Peixoto
 */

public class LivroControllerUI {

    private final static int TABELA = 0;
    private final static int FORMCADASTRO = 1;
    private final static int FORMEDICAO = 2;
    private final static int FORMVISUALIZACAO = 3;

    private int telaAtual = 0;
    private int linhaSelecionada = -1;

    private JanelaCrudLivro janela;

    public LivroControllerUI() {
        telaAtual = TABELA;
    }

    public void setJanela(JanelaCrudLivro janela) {
        this.janela = janela;
    }

    /**
     * Método utilizado para inserção do livro
     */
    public void inserirLivro() {
        PainelCadastroLivro painelForm = this.janela.getPainelFormulario();

        painelForm.zerarCampos();

        painelForm.getLabelPainelFormulario().setText("Cadastrar Livro");
        painelForm.getBotaoSalvar().setVisible(true);
        painelForm.getBotaoSalvar().setText("Cadastrar");
        painelForm.habilitaEdicaoFormLivro(true);

        telaAtual = FORMCADASTRO;
        this.janela.mostrarPainel(JanelaCrudLivro.PAINELFORM);
    }

    /**
     * Método utilizado para edição de livros
     */
    public void editarLivro() {
        PainelLivro painelTabela = this.janela.getPainelTabela();
        PainelCadastroLivro painelForm = this.janela.getPainelFormulario();
        LivroTableModel tableModel = (LivroTableModel) painelTabela.getTabelaLivros().getModel();

        linhaSelecionada = painelTabela.getTabelaLivros().getSelectedRow();
        if (linhaSelecionada < 0) {
            PrintUtil.printMessageError(janela, "Não há nenhum elemento selecionado na tabela");
            return;
        }
        Livro li = tableModel.getLivro(linhaSelecionada);
        painelForm.carregaDados(Long.toString(li.getIsbn()), li.getTitulo(), li.getEditora(), li.getAutor(), li.getAnoPublicacao());

        painelForm.getLabelPainelFormulario().setText("Editar Livro");
        painelForm.getBotaoSalvar().setVisible(true);
        painelForm.getBotaoSalvar().setText("Editar");
        painelForm.habilitaEdicaoFormLivro(true);

        telaAtual = FORMEDICAO;
        this.janela.mostrarPainel(JanelaCrudLivro.PAINELFORM);
    }

    /**
     * Método utilizado para visualização dos livros
     */
    public void visualizarLivro() {
        PainelLivro painelTabela = this.janela.getPainelTabela();
        PainelCadastroLivro form = this.janela.getPainelFormulario();
        LivroTableModel tableModel = (LivroTableModel) painelTabela.getTabelaLivros().getModel();

        linhaSelecionada = painelTabela.getTabelaLivros().getSelectedRow();
        if (linhaSelecionada < 0) {
            PrintUtil.printMessageError(janela, "Não há nenhum elemento selecionado na tabela");
            return;
        }
        Livro li = tableModel.getLivro(linhaSelecionada);
        form.carregaDados(Long.toString(li.getIsbn()), li.getTitulo(), li.getEditora(), li.getAutor(), li.getAnoPublicacao());

        form.getLabelPainelFormulario().setText("Visualizar Livro");
        form.getBotaoSalvar().setVisible(false);
        form.getBotaoSalvar().setText("");
        form.habilitaEdicaoFormLivro(false);

        telaAtual = FORMVISUALIZACAO;
        this.janela.mostrarPainel(JanelaCrudLivro.PAINELFORM);
    }

    /**
     * Método utilizado para remoção do cliente selecionado
     */
    public void removerLivro() {
        PainelLivro painelTabela = this.janela.getPainelTabela();
        LivroTableModel tableModel = (LivroTableModel) painelTabela.getTabelaLivros().getModel();
        linhaSelecionada = painelTabela.getTabelaLivros().getSelectedRow();
        if (linhaSelecionada < 0) {
            PrintUtil.printMessageError(janela, "Não há nenhum elemento selecionado na tabela");
            return;
        }
        Livro li = tableModel.getLivro(linhaSelecionada);
        if (li.isDisponibilidade() == false) {
            PrintUtil.printMessageError(janela, "Livro está alugado, espere efetuarem a devolução para efetuar operação!");
            return;
        } else {
            LivroDao dao = new LivroDaoBd();
            dao.deletar(li);
            PrintUtil.printMessageSucesso(janela, "Remoção realizada com sucesso!");
        }
        this.atualizaTabela();

    }

    /**
     * Método utilizado para salvar inserção e edição de livros
     *
     * @param isbn - recebe um isbn de um livro;
     * @param titulo - recebe o título de um livro;
     * @param editora - recebe a editora de um livro;
     * @param autor - recebe o(s) autor(es) de um livro;
     * @param anoPublicacao - recebe o ano de publicação de um livro;
     */
    public void salvarLivro(String isbn, String titulo, String editora, String autor, String anoPublicacao) {
        PainelCadastroLivro painelForm = this.janela.getPainelFormulario();
        PainelLivro painelTabela = this.janela.getPainelTabela();
        LivroTableModel tableModel = (LivroTableModel) painelTabela.getTabelaLivros().getModel();
        try{
        long isbnTemp = Long.parseLong(isbn);
        String tituloTemp = titulo, editoraTemp = editora, autorTemp = autor, anoPublicacaoTemp = anoPublicacao;
        if (telaAtual == FORMCADASTRO) {
            if (LivroExiste(isbnTemp)) {
                PrintUtil.printMessageError(janela, "ISBN já existe");
            } else if (validacaoIsbn(isbnTemp) == false) {
                PrintUtil.printMessageError(janela, "Campo ISBN é inválido!");
            } else if (titulo == null || titulo.trim().length() == 0) {
                PrintUtil.printMessageError(janela, "Campo Titulo é inválido!");
            } else if (editora == null || editora.trim().length() == 0) {
                PrintUtil.printMessageError(janela, "Campo Editora é inválido");

            } else if (autor == null || autor.trim().length() == 0) {
                PrintUtil.printMessageError(janela, "Campo Autor(es) é inválido");

            } else if (validarAnoPublicacao(anoPublicacao) != true || anoPublicacao == null) {
                PrintUtil.printMessageError(janela, "Campo Ano de Publição é inválido! Digite: xxxx");
            } else {
                Livro livro = new Livro(isbnTemp, tituloTemp, editoraTemp, autorTemp, anoPublicacaoTemp);
                LivroDao dao = new LivroDaoBd();
                dao.inserir(livro);
                PrintUtil.printMessageSucesso(janela, "Cadastro realizado com sucesso!");
                painelForm.zerarCampos();
            }

        } else if (telaAtual == FORMEDICAO) {
            if (LivroExiste(isbnTemp)) {
                PrintUtil.printMessageError(janela, "ISBN já existe");
            } else if (validacaoIsbn(isbnTemp) == false) {
                PrintUtil.printMessageError(janela, "Campo ISBN é inválido!");
            } else if (titulo == null || titulo.trim().length() == 0) {
                PrintUtil.printMessageError(janela, "Campo Titulo é inválido!");
            } else if (editora == null || editora.trim().length() == 0) {
                PrintUtil.printMessageError(janela, "Campo Editora é inválido");

            } else if (autor == null || autor.trim().length() == 0) {
                PrintUtil.printMessageError(janela, "Campo Autor(es) é inválido");
            } else if (validarAnoPublicacao(anoPublicacao) != true || anoPublicacao == null) {
                PrintUtil.printMessageError(janela, "Campo Ano de Publição é inválido! Digite: xxxx");
            } else {
                linhaSelecionada = painelTabela.getTabelaLivros().getSelectedRow();
                int idLivro = tableModel.getLivro(linhaSelecionada).getCod();
                Livro livro = new Livro(isbnTemp, tituloTemp, editoraTemp, autorTemp, anoPublicacaoTemp);
                LivroDao dao = new LivroDaoBd();
                dao.editarLivro(livro, idLivro);
                PrintUtil.printMessageSucesso(janela, "Edição realizada com sucesso!");
            }
        }
        }catch(Exception e){
            PrintUtil.printMessageError(janela, "Campo ISBN é inválido!");
        }
    }

    /**
     * Método utilizado para voltar ao menu principal
     */
    public void voltarPrincipal() {
        telaAtual = TABELA;
        this.atualizaTabela();
        this.janela.mostrarPainel(JanelaCrudLivro.PAINELTABELA);
    }

    /**
     * Método utilizado para atualizar dados na tabela
     */
    public void atualizaTabela() {
        PainelLivro painelTabela = this.janela.getPainelTabela();
        LivroTableModel tableModel = (LivroTableModel) painelTabela.getTabelaLivros().getModel();

        LivroDao dao = new LivroDaoBd();
        tableModel.setLivro(dao.listar());

        painelTabela.getTabelaLivros().updateUI();
    }

    /**
     * Método booleano que se comunica com a classe LivroDao para verificar,
     * pelo título do livro, se ele existe no banco de dados;
     *
     * @param titulo - recebe o título do livro;
     * @return retorna 'true' ou 'false';
     */
    public boolean LivroExiste(String titulo) {
        LivroDao dao = new LivroDaoBd();
        Livro livro = dao.procurarPorTitulo(titulo);
        return (livro != null);
    }

    /**
     * Método booleano que se comunica com a classe LivroDao para verificar,
     * pelo ISBN do livro, se ele existe no banco de dados;
     *
     * @param isbn - recebe o ISBN do livro;
     * @return retorna 'true' ou 'false'
     */
    public boolean LivroExiste(long isbn) {
        LivroDao dao = new LivroDaoBd();
        Livro livro = dao.procurarPorIsbn(isbn);
        if (livro != null) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Método que comunica a classe LivroDao da inserção de um novo livro;
     * @param l - recebe um objeto Livro;
     */
    public void addLivro(Livro l) {
        new LivroDaoBd().inserir(l);
    }

    /**
     * Método que se comunica com a classe CLivroDao para listar os livros
     * existentes;
     *
     * @return retorna a lista de livros no banco de dados;
     */
    public List<Livro> listarLivros() {
        return (new LivroDaoBd().listar());
    }

    /**
     * Método que solicita ao LivroDao que busque as informações de um livro
     * pelo ISBN do mesmo;
     * @param isbn - recebe o ISBN do livro;
     * @return retorna o objeto Livro encontrado no banco de dados;
     */
    public Livro buscarLivroPorIsbn(long isbn) {
        LivroDao dao = new LivroDaoBd();
        Livro livro = dao.procurarPorIsbn(isbn);
        return (livro);
    }

     /**
     * Método que solicita ao LivroDao que busque as informações de um livro
     * pelo código do mesmo;
     * @param cod - recebe o ISBN do livro;
     * @return retorna o objeto Livro encontrado no banco de dados;
     */
    public Livro buscarLivroPorCod(int cod) {
        LivroDao dao = new LivroDaoBd();
        Livro livro = dao.procurarPorId(cod);
        return (livro);
    }
    
    /**
     * Método que solicita ao LivroDao que busque as informações de um livro
     * pelo título do mesmo;
     * @param titulo - recebe o título do livro;
     * @return retorna o objeto Livro encontrado no banco de dados;
     */
    public Livro buscarLivroPorTitulo(String titulo) {
        LivroDao dao = new LivroDaoBd();
        Livro livro = dao.procurarPorTitulo(titulo);
        return (livro);
    }

    /**
     * Método que solicita ao LivroDao que edite as informações de variável tipo
     * long de um livro;
     * @param op - recebe o número do operador que indica as opções do menu;
     * @param novoX - recebe o novo valor a ser editado;
     * @param l - recebe o objeto Livro que terá algum valor alterado;
     */
    public void editarLivro(String op, long novoX, Livro l) {
        LivroDao dao = new LivroDaoBd();
        if (op.equals("1")) {
            dao.editar(l, novoX, "isbn");
        }
    }

    /**
     * Método que solicita ao LivroDao que edite as informações de variável tipo
     * String de um livro;
     * @param op - recebe o número do operador que indica as opções do menu;
     * @param novoX - recebe o novo valor a ser editado;
     * @param l - recebe o objeto Livro que terá algum valor alterado;
     */
    public void editarLivro(String op, String novoX, Livro l) {
        LivroDao dao = new LivroDaoBd();
        if (op.equals("2")) {
            dao.editar(l, novoX, "titulo");
        }
        if (op.equals("3")) {
            dao.editar(l, novoX, "editora");
        }
        if (op.equals("4")) {
            dao.editar(l, novoX, "autor");
        }
        if (op.equals("5")) {
            dao.editar(l, novoX, "anoPublicacao");
        }
    }

    /**
     * Método que solicita ao LivroDao que delete um livro do banco de dados;
     * @param l - recebe o objeto Livro;
     */
    public void deletarLivro(Livro l) {
        LivroDao dao = new LivroDaoBd();
        dao.deletar(l);
    }

    /**
     * Método que solicita ao LivroDao que verifica se um ISBN existe;
     * @param isbn - recebe o ISBN de um livro;
     * @return retorna 'true' ou 'false'
     */
    public boolean verificaIsbn(long isbn) {
        LivroDao dao = new LivroDaoBd();
        Livro livro = dao.procurarPorIsbn(isbn);
        if (livro != null) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Método para não deixar que um número de ISBN negativo seja inserido;
     *
     * @param isbnDigitado - recebe o ISBN digitado;
     * @return retorna 'true' ou 'false'
     */
    public boolean validacaoIsbn(long isbnDigitado) {
        if (isbnDigitado >= 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Método que permite a visualização de uma lista de livros disponíveis;
     */
    public void VisualizarLivroDisponivel() {
        List<Livro> listaLivroTemp = new LivroDaoBd().listar();    
        }

    /**
     * Método booleano para validar se o ano informado é válido;
     *
     * @param campo - recebe a String campo (do ano);
     * @return retorna se o campo do ano contém caracateres válidos;
     */
    public boolean validarAnoPublicacao(String campo) {
        return campo.matches("[0-9]{4}+");
    }

    /**
     * Método que permite a visualização de uma lista de livros mais retirados;
     */
    public List<Livro> livrosMaisRetirados() {
        List<Livro> listaLivroTemp = new LivroDaoBd().listar();
        Collections.sort(listaLivroTemp, new livroCompRetirados());
        return listaLivroTemp;
    }

    /**
     * Inner class para comparação de objetos do tipo Livros com a quantidade de
     * livros retirados e implementa a interface Comparator;
     */
    public class livroCompRetirados implements Comparator<Livro> {

        @Override
        public int compare(Livro l1, Livro l2) {
            return l2.getQntdeTotalAlugado() - l1.getQntdeTotalAlugado();
        }
    }

}
