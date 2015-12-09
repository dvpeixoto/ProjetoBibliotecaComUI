
package dao;

import java.util.List;
import model.Aluguel;
/**
 * Interface da classe AluguelDaoBd
 * @author Tainara Specht
 * @author Diego Peixoto
 */
public interface AluguelDao {
    /**
     * Método responsável pela inserção do objeto aluguel no banco de dados;
     * @param aluguel recebe objeto aluguel a ser inserido;
     */
    public void inserir(Aluguel aluguel);
    
    /**
     * Método responsável pela atualização das quantidades de livros alugados por um cliente no banco de dados;
     * @param aluguel recebe objeto aluguel a ser atualizado;
     */
    public void atualizarCliente(Aluguel aluguel);
    
    /**
     * Método responsável pela atualização da disponibilidade dos livros no banco de dados;
     * @param aluguel recebe objeto aluguel a ser atualizado;
     */
    public void atualizarLivro(Aluguel aluguel);
    
    /**
     * Método responsável pela atualização da quantidade de vezes que um livro já foi alugado;
     * @param aluguel recebe objeto aluguel a ser atualizado;
     */
    public void atualizarQntLivro(Aluguel aluguel);
    
    /**
     * Método responsável pela atualização da quantidade de vezes que um cliente alugou um livro;
     * @param aluguel recebe objeto aluguel a ser atualizado;
     */
    public void atualizarQntLivrosAlugadosCliente(Aluguel aluguel);
    
    /**
     * Método responsável por listar todos os aluguéis;
     * @return lista do aluguel;
     */
    public List<Aluguel> listar();
    
    /**
     * Método responsável por procurar um aluguel por ID do aluguel;
     * @param id recebe a variável ID do aluguel a ser procurado;
     * @return livro alugado com dados do aluguel;
     */
    public Aluguel procurarPorId(int id);

    
    
}
