package dao;

import java.util.List;
import model.Livro;

/**
 * Interface da classe DevolucaoDaoBd;
 * @author Tainara Specht
 * @author Diego Peixoto
 */

public interface LivroDao {
    
    /**
     * Método responsável pela inserção do objeto livro no banco de dados;
     * @param livro recebe objeto livro a ser inserido;
     */
    public void inserir(Livro livro);
    
    /**
     * Método responsável por deletar o objeto livro no banco de dados;
     * @param livro recebe objeto livro a ser deletado;
     */
    public void deletar(Livro livro);
    
    /**
     * Método responsável por editar um dado do tipo long de um livro no banco de dados;
     * @param livro recebe o objeto livro para edição;
     * @param novoX recebe a variável para ser editada;
     * @param coluna recebe a coluna para ser editada;
     */
    public void editar(Livro livro, long novoX, String coluna);
    
    /**
     * Método responsável por editar um dado do tipo String de um livro no banco de dados;
     * @param livro recebe o objeto livro para edição;
     * @param novoX recebe a variável para ser editada;
     * @param coluna recebe a coluna para ser editada;
     */
    public void editar(Livro livro, String novoX, String coluna);
    
    /**
     * Método responsável por listar os livros cadastrados no banco de dados;
     * @return lista ordenada por título;
     */
    public List<Livro> listar();
    
    /**
     * Método responsável por procurar um livro pelo ISBN;
     * @param isbn recebe a variável ISBN;
     * @return livro com o ISBN procurado;
     */
    public Livro procurarPorIsbn(long isbn);
    
    /**
     * Método responsável por procurar um livro pelo ID;
     * @param id recebe a variável ID;
     * @return livro com o ID procurado;
     */
    public Livro procurarPorId(int id);
    
    /**
     * Método responsável por procurar um livro pelo TÍTULO;
     * @param titulo recebe a variável TÍTULO;
     * @return livro com o TÍTULO procurado;
     */
    public Livro procurarPorTitulo(String titulo);
    
    /**
     * Método responsável por listar os livros com o título recebido na variável;
     * @param titulo recebe a variável TÍTULO;
     * @return lista com todos os livros com o TÍTULO recebido na variável;
     */
    public List<Livro> listarPorTitulo(String titulo);    
    
    /**
     * Método responsável pela edição dos livros no banco de dados
     * @param livro
     * @param idLivro 
     */
    public void editarLivro(Livro livro, int idLivro);
}