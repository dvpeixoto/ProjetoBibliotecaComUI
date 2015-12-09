
package dao;

import java.util.List;
import model.Devolucao;

/**
 * Interface da classe DevolucaoDaoBd;
 * @author Tainara Specht
 * @author Diego Peixoto
 */

public interface DevolucaoDao {
    /**
     * Método responsável pela inserção do objeto devolução no banco de dados e por definir se aquela devolução foi feita com atraso ou não;
     * @param devolucao recebe objeto devolução a ser inserido;
     * @param atrasado recebe boolean atrasado como true ou false;
     */
    public void inserir(Devolucao devolucao, boolean atrasado);
    
    /**
     * Método responsável pela atualização do cliente que realizou uma devolução no banco de dados;
     * @param devolucao recebe o objeto devolução a ser atualizado;
     */
    public void atualizarCliente(Devolucao devolucao);
    
    /**
     * Método responsável pela atualização da disponibilidade do livro que foi devolvido no banco de dados;
     * @param devolucao 
     */
    public void atualizarLivro(Devolucao devolucao);
    
    /**
     * Método responsável por listar todas as devoluções;
     * @return lista ordenada pelo ID da devolução;
     */
    public List<Devolucao> listar();
    
    /**
     * Método responsável por procurar dados de uma devolução pelo ID da devolução;
     * @param id recebe a variável ID da devolução a ser procurada;
     * @return informações da devolução procurada;
     */
    public Devolucao procurarPorId(int id);
    
    /**
     * Método responsável por atualizar quantas vezes um determinado cliente atrasou a entrega de livros;
     * @param devolucao recebe objeto devolução;
     */
    public void atualizarAtrasoCliente(Devolucao devolucao);
  
    
}
