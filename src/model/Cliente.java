package model;

/**
 * Classe MODELS para a classe Cliente
 *
 * @author Tainara Specht
 * @author Diego Peixoto
 */
public class Cliente {

    private int id, livrosAlugados, qntdelivrosalugados, qntdeatraso;
    private String nome, telefone;
    private Long matricula, rg;

    /**
     * Construtor da classe
     *
     * @param nomeIn - recebe o nome do cliente;
     * @param rgIn - recebe o RG do cliente;
     * @param telefoneIn - recebe o telefone do cliente;
     * @param matriculaIn - recebe o número de matrícula do cliente;
     */
    public Cliente(String nomeIn, long rgIn, String telefoneIn, Long matriculaIn) {
        this.nome = nomeIn;
        this.rg = rgIn;
        this.telefone = telefoneIn;
        this.matricula = matriculaIn;
        this.livrosAlugados = 0;
        this.qntdelivrosalugados = 0;
        this.qntdeatraso = 0;
    }
    
    /**
     * Construtor da classe
     *
     * @param nomeIn - recebe o nome do cliente;
     * @param rgIn - recebe o RG do cliente;
     * @param telefoneIn - recebe o telefone do cliente;
     */
      public Cliente(String nomeIn, long rgIn, String telefoneIn) {
        this.nome = nomeIn;
        this.rg = rgIn;
        this.telefone = telefoneIn;
        this.livrosAlugados = 0;
        this.qntdelivrosalugados = 0;
        this.qntdeatraso = 0;
    }

    /**
     *Construtor de acesso às informações do banco
     *
     * @param idIn - recebe o ID do cliente;
     * @param nomeIn - recebe o nome do cliente;
     * @param rgIn - recebe o RG do cliente;
     * @param telefoneIn - recebe o telefone do cliente;
     * @param matriculaIn - recebe a matrícula do cliente;
     * @param livrosAlugadosIn - recebe os livros alugados;
     * @param qntdelivrosalugadosIn - recebe a quantidade de livros alugados;
     * @param qntdeatrasoIn - recebe a quantidade de dias dos livros em atraso;
     */
     public Cliente(int idIn, String nomeIn, long rgIn, String telefoneIn, long matriculaIn, int livrosAlugadosIn, int qntdelivrosalugadosIn, int qntdeatrasoIn) {
        this.id = idIn;
        this.nome = nomeIn;
        this.rg = rgIn;
        this.telefone = telefoneIn;
        this.matricula = matriculaIn;
        this.livrosAlugados = livrosAlugadosIn;
        this.qntdelivrosalugados = qntdelivrosalugadosIn;
        this.qntdeatraso = qntdeatrasoIn;
    }
     
    public void setNome(String nomeIn) {
        this.nome = nomeIn;
    }

    public String getNome() {
        return nome;
    }

    public void setTelefone(String telefoneIn) {
        this.telefone = telefoneIn;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setMatricula(Long matriculaIn) {
        this.matricula = matriculaIn;
    }

    public Long getMatricula() {
        return matricula;
    }

    public void setLivrosAlugados(int livrosAlugados) {
        this.livrosAlugados = livrosAlugados;
    }

    public int getLivrosAlugados() {
        return livrosAlugados;
    }

    public void setQntdelivrosalugados(int qntdelivrosalugados) {
        this.qntdelivrosalugados = qntdelivrosalugados;
    }

    public int getQntdelivrosalugados() {
        return qntdelivrosalugados;
    }

    public int getQntdeatraso() {
        return qntdeatraso;
    }

    public void setQntdeatraso(int qntdeatraso) {
        this.qntdeatraso = qntdeatraso;
    }

    public void setId(int idIn) {
        this.id = idIn;
    }

    public int getId() {
        return id;
    }

    public void setRg(Long rg) {
        this.rg = rg;
    }

    public Long getRg() {
        return rg;
    }

    @Override
    public String toString() {
        return "Nome: " + getNome() + "\nRG: " +getRg()+ "\nTelefone: " + getTelefone() + "\nMatricula: " + getMatricula() + "\nQuantidade de livros alugados: " + getLivrosAlugados() + "\n";
    }

}
