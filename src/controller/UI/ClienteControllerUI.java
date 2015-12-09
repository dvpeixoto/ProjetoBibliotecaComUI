package controller.UI;

import dao.ClienteDao;
import dao.ClienteDaoBd;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import model.Cliente;
import view.Cliente.UI.JanelaCrudCliente;
import view.Cliente.UI.ClienteTableModel;
import view.Cliente.UI.PainelCadastroCliente;
import view.Cliente.UI.PainelCliente;
import util.PrintUtil;


/**
 * Classe controller para a classe Cliente;
 * @author Tainara Specht
 * @author Diego Peixoto
 */

public class ClienteControllerUI {

    private final static int TABELA = 0;
    private final static int FORMCADASTRO = 1;
    private final static int FORMEDICAO = 2;
    private final static int FORMVISUALIZACAO = 3;

    private int telaAtual = 0;
    private int linhaSelecionada = -1;

    private JanelaCrudCliente janela;

    /**
     *
     */
    public ClienteControllerUI() {
        telaAtual = TABELA;
    }

    public void setJanela(JanelaCrudCliente janela) {
        this.janela = janela;
    }

    /**
     * Método utilizado para inserção do cliente
     */
    public void inserirCliente() {
        PainelCadastroCliente painelForm = this.janela.getPainelFormulario();

        painelForm.zerarCampos();

        painelForm.getLabelPainelFormulario().setText("Cadastrar Cliente");
        painelForm.getBotaoSalvar().setVisible(true);
        painelForm.getBotaoSalvar().setText("Cadastrar");
        painelForm.habilitaEdicaoFormCliente(true);

        telaAtual = FORMCADASTRO;
        this.janela.mostrarPainel(JanelaCrudCliente.PAINELFORM);
    }

    /**
     * Método utilizado para edição de clientes
     */
    public void editarCliente() {
        PainelCliente painelTabela = this.janela.getPainelTabela();
        PainelCadastroCliente painelForm = this.janela.getPainelFormulario();
        ClienteTableModel tableModel = (ClienteTableModel) painelTabela.getTabelaClientes().getModel();

        linhaSelecionada = painelTabela.getTabelaClientes().getSelectedRow();
        if (linhaSelecionada < 0) {
            PrintUtil.printMessageError(janela, "Não há nenhum elemento selecionado na tabela");
            return;
        }
        Cliente cli = tableModel.getCliente(linhaSelecionada);
        painelForm.carregaDados(Long.toString(cli.getRg()), cli.getNome(), cli.getTelefone());

        painelForm.getLabelPainelFormulario().setText("Editar Cliente");
        painelForm.getBotaoSalvar().setVisible(true);
        painelForm.getBotaoSalvar().setText("Editar");
        painelForm.habilitaEdicaoFormCliente(true);

        telaAtual = FORMEDICAO;
        this.janela.mostrarPainel(JanelaCrudCliente.PAINELFORM);
    }

    /**
     * Método utilizado para visualização dos clientes
     */
    public void visualizarCliente() {
        PainelCliente painelTabela = this.janela.getPainelTabela();
        PainelCadastroCliente form = this.janela.getPainelFormulario();
        ClienteTableModel tableModel = (ClienteTableModel) painelTabela.getTabelaClientes().getModel();

        linhaSelecionada = painelTabela.getTabelaClientes().getSelectedRow();
        if (linhaSelecionada < 0) {
            PrintUtil.printMessageError(janela, "Não há nenhum elemento selecionado na tabela");
            return;
        }
        Cliente cli = tableModel.getCliente(linhaSelecionada);
        form.carregaDados(Long.toString(cli.getRg()), cli.getNome(), cli.getTelefone());

        form.getLabelPainelFormulario().setText("Visualizar Cliente");
        form.getBotaoSalvar().setVisible(false);
        form.getBotaoSalvar().setText("");
        form.habilitaEdicaoFormCliente(false);

        telaAtual = FORMVISUALIZACAO;
        this.janela.mostrarPainel(JanelaCrudCliente.PAINELFORM);
    }

    /**
     * Método utilizado para remoção do cliente selecionado
     */
    public void removerCliente() {
        PainelCliente painelTabela = this.janela.getPainelTabela();
        ClienteTableModel tableModel = (ClienteTableModel) painelTabela.getTabelaClientes().getModel();
        linhaSelecionada = painelTabela.getTabelaClientes().getSelectedRow();
        if (linhaSelecionada < 0) {
            PrintUtil.printMessageError(janela, "Não há nenhum elemento selecionado na tabela");
            return;
        }
        Cliente cli = tableModel.getCliente(linhaSelecionada);
        if (cli.getLivrosAlugados() > 0) {
            PrintUtil.printMessageError(janela, "Você possui livro alugado, para cancelar cadastro é necessário entregar todos os livros antes!");
            return;
        } else {
            ClienteDao dao = new ClienteDaoBd();
            dao.deletar(cli);
            PrintUtil.printMessageSucesso(janela, "Remoção realizada com sucesso!");

            this.atualizaTabela();
        }
    }

    /**
     * Método utilizado para salvar inserção de cliente e edição de cliente
     *
     * @param rg - recebe um número de rg para cadastro;
     * @param nome - recebe o nome  do cliente para cadastro;
     * @param telefone - recebe um número de telefone para cadastro;
     */
    public void salvarCliente(String rg, String nome, String telefone) {
        PainelCadastroCliente painelForm = this.janela.getPainelFormulario();
        PainelCliente painelTabela = this.janela.getPainelTabela();
        ClienteTableModel tableModel = (ClienteTableModel) painelTabela.getTabelaClientes().getModel();
        try {
            long rgTemp = Long.parseLong(rg);
            String nomeTemp = nome, telefoneTemp = telefone;
            if (telaAtual == FORMCADASTRO) {
                if (clienteExiste(rgTemp)) {
                    PrintUtil.printMessageError(janela, "RG já existe");
                } else if (validacaoRG(rgTemp) == false) {
                    PrintUtil.printMessageError(janela, "RG é inválido!");
                } else {
                    if (!validacaoNome(nomeTemp) || nome == null) {
                        PrintUtil.printMessageError(janela, "Nome é inválido!");
                    } else {
                        if (isTelefone(telefoneTemp) != true || telefone == null) {
                            PrintUtil.printMessageError(janela, "Número de telefone inválido! Digite no formato (xx) xxxx-xxxx: ");
                            return;
                        }
                        long matricula;
                        do {
                            matricula = gerarMatricula();
                        } while (matriculaExiste(matricula) == true);
                        Cliente cliente = new Cliente(nomeTemp, rgTemp, telefoneTemp, matricula);
                        ClienteDao dao = new ClienteDaoBd();
                        dao.inserir(cliente);
                        PrintUtil.printMessageSucesso(janela, "Cadastro realizado com sucesso!");
                        painelForm.zerarCampos();
                    }
                }
            } else if (telaAtual == FORMEDICAO) {
                if (clienteExiste(rgTemp)) {
                    PrintUtil.printMessageError(janela, "RG já existe.");
                } else if (validacaoRG(rgTemp) == false) {
                    PrintUtil.printMessageError(janela, "RG é inválido!");
                } else {
                    if (!validacaoNome(nomeTemp) || nome == null) {
                        PrintUtil.printMessageError(janela, "Nome é inválido!");
                    } else {
                        if (isTelefone(telefoneTemp) != true || telefone == null) {
                            PrintUtil.printMessageError(janela, "Número de telefone inválido! Digite no formato (xx) xxxx-xxxx: ");
                            return;
                        }
                        linhaSelecionada = painelTabela.getTabelaClientes().getSelectedRow();
                        int idCli = tableModel.getCliente(linhaSelecionada).getId();
                        Cliente cliente = new Cliente(nomeTemp, rgTemp, telefoneTemp);
                        ClienteDao dao = new ClienteDaoBd();
                        dao.editarCliente(cliente, idCli);
                        PrintUtil.printMessageSucesso(janela, "Edição realizada com sucesso!");
                    }
                }
            }
        } catch (Exception e) {
            PrintUtil.printMessageSucesso(janela, "RG inválido!");
        }
    }

    /**
     * Método utilizado para voltar ao menu principal
     */
    public void voltarPrincipal() {
        telaAtual = TABELA;
        this.atualizaTabela();
        this.janela.mostrarPainel(JanelaCrudCliente.PAINELTABELA);
    }

    /**
     * Método utilizado para atualizar dados na tabela
     */
    public void atualizaTabela() {
        PainelCliente painelTabela = this.janela.getPainelTabela();
        ClienteTableModel tableModel = (ClienteTableModel) painelTabela.getTabelaClientes().getModel();

        ClienteDao dao = new ClienteDaoBd();
        tableModel.setCliente(dao.listar());

        painelTabela.getTabelaClientes().updateUI();
    }

    /**
     * Método booleano que se comunica com a classe ClienteDao para verificar,
     * pelo número do RG, se um cliente existe no banco de dados;
     *
     * @param rg - recebe o RG do cliente;
     * @return retorna 'true' ou 'false';
     */
    public boolean clienteExiste(long rg) {
        ClienteDao dao = new ClienteDaoBd();
        Cliente cliente = dao.procurarPorRg(rg);
        if (cliente != null) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Método booleano que se comunica com a classe ClienteDao para verificar,
     * pelo número de matrícula, se um cliente existe no banco de dados;
     *
     * @param matricula - recebe o número da matrícula do cliente;
     * @return retorna 'true' ou 'false';
     */
    public boolean matriculaExiste(long matricula) {
        ClienteDao dao = new ClienteDaoBd();
        Cliente cliente = dao.procurarPorMatricula(matricula);
        if (cliente != null) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Método booleano que se comunica com a classe ClienteDao para verificar,
     * pelo nome do cliente, se ele existe no banco de dados;
     *
     * @param nome - recebe o nome do cliente;
     * @return retorna 'true' ou 'false';
     */
    public boolean nomeExiste(String nome) {
        ClienteDao dao = new ClienteDaoBd();
        Cliente cliente = dao.procurarPorNome(nome);
        if (cliente != null) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Método para randomizar e gerar um número de matrícula para o cliente,
     * baseado na data do sistema;
     *
     * @return retorna número de matrícula gerado;
     */
    public long gerarMatricula() {
        Date n = new Date();
        long matriculaNumero = n.getTime();
        return matriculaNumero;
    }

    /**
     * Método que se comunica com a classe ClienteDao para listar os clientes
     * existentes;
     *
     * @return retorna a lista de clientes no banco de dados;
     */
    public List<Cliente> listarClientes() {
        return (new ClienteDaoBd().listar());
    }

    /**
     * Método que solicita ao ClienteDao que busque as informações de um cliente
     * pelo RG do mesmo;
     *
     * @param rg - recebe o RG de um cliente;
     * @return retorna o objeto Cliente encontrado no banco de dados;
     */
    public Cliente buscarClientePorRg(long rg) {
        ClienteDao dao = new ClienteDaoBd();
        Cliente cliente = dao.procurarPorRg(rg);
        return (cliente);
    }

    /**
     * Método que solicita ao ClienteDao que busque as informações de um cliente
     * pelo nome do mesmo;
     *
     * @param nome - recebe o nome de um cliente;
     * @return retorna o objeto Cliente encontrado no banco de dados;
     */
    public Cliente buscarClientePorNome(String nome) {
        ClienteDao dao = new ClienteDaoBd();
        Cliente cliente = dao.procurarPorNome(nome);
        return (cliente);
    }

    /**
     * Método que solicita ao ClienteDao que valide se há espaço entre nome e
     * sobrenome de um cliente;
     *
     * @param nomeDigitado - recebe o nome digitado;
     * @return retorna 'true' ou 'false';
     */
    public boolean validacaoNome(String nomeDigitado) {
        nomeDigitado = nomeDigitado.trim();
        boolean espacos = false;

        for (int i = 0; i < nomeDigitado.length(); i++) {
            char carac = nomeDigitado.charAt(i);
            if (Character.isLetter(carac) || Character.isWhitespace(carac)) {
                if (Character.isWhitespace(carac)) {
                    espacos = true;
                }
            } else {
                return false;
            }
        }
        return espacos == true;
    }

    /**
     * Método para não deixar que um número de RG negativo seja inserido;
     *
     * @param rgDigitado - recebe o número de um RG;
     * @return retorna 'true' ou 'false';
     */
    public boolean validacaoRG(long rgDigitado) {
        if (rgDigitado >= 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Método booleano para verificar se a String digitada é um número de
     * telefone válido;
     *
     * @param novoTelefone - recebe o número de telefone;
     * @return retorna se o número de telefone é válido;
     */
    public boolean isTelefone(String novoTelefone) {
        return novoTelefone.matches(".((10)|([1-9][1-9]).)\\s9?[6-9][0-9]{3}-[0-9]{4}")
                || novoTelefone.matches(".((10)|([1-9][1-9]).)\\s[2-5][0-9]{3}-[0-9]{4}");
    }

    /**
     * Método que ordena clientes que mais alugam livros;
     */
    public List<Cliente> clientesQueMaisAlugam() {
        List<Cliente> listaClienteTemp = new ClienteDaoBd().listar();
        Collections.sort(listaClienteTemp, new ClienteCompAluguel());
        return listaClienteTemp;
    }

    /**
     * Método que ordena clientes que mais atrasam a entrega dos livros;
     */
    public List<Cliente> clientesQueMaisAtrasam() {
        List<Cliente> listaClienteTemp = new ClienteDaoBd().listar();
        Collections.sort(listaClienteTemp, new ClienteCompAtraso());
        return listaClienteTemp;
    }

    /**
     * Inner class para comparação de objetos do tipo cliente com a quantidade
     * de livros alugados e implementa a interface Comparator;
     */
    public class ClienteCompAluguel implements Comparator<Cliente> {

        @Override
        public int compare(Cliente c1, Cliente c2) {
            return c2.getQntdelivrosalugados() - c1.getQntdelivrosalugados();
        }
    }

    /**
     * Inner class para comparação de objetos do tipo cliente com a quantidade
     * de atrasos e implementa a interface Comparator;
     */
    public class ClienteCompAtraso implements Comparator<Cliente> {

        @Override
        public int compare(Cliente c1, Cliente c2) {
            return c2.getQntdeatraso() - c1.getQntdeatraso();
        }
    }

}
