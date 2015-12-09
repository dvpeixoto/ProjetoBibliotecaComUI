
package dao;

import java.util.List;
import model.Cliente;
/**
 * Interface da classe ClienteDaoBd;
 * @author Tainara Specht
 * @author Diego Peixoto
 */

public interface ClienteDao {
    
    /**
     * Método responsável pela inserção do objeto cliente no banco de dados;
     * @param cliente recebe objeto cliente a ser inserido;
     */
    public void inserir(Cliente cliente);
    
    /**
     * Método responsável por deletar cliente do banco de dados;
     * @param cliente recebe objeto cliente a ser deletado;
     */
    public void deletar(Cliente cliente);
    
    /**
     * Método responsável por editar um dado do tipo String de um cliente no banco de dados;
     * @param cliente recebe objeto cliente para edição;
     * @param novoX recebe variável a ser editada no banco de dados (nome/telefone);
     * @param coluna recebe variável com o nome da coluna a ser editada no banco de dados(nome/telefone);
     */
    public void editar(Cliente cliente, String novoX, String coluna);
    
    /**
     * Método responsável por editar um dado do tipo long de um cliente no banco de dados;
     * @param cliente recebe objeto cliente para edição;
     * @param novoX recebe variável a ser editada no banco de dados (RG);
     * @param coluna recebe variável com o nome da coluna a ser editada no banco de dados;
     */
    public void editar(Cliente cliente, long novoX, String coluna);
    
    /**
     * Método responsável por listar os clientes cadastrados no banco de dados;
     * @return lista de clientes;
     */
    public List<Cliente> listar();
    
    /**
     * Método responsável por procurar um cliente pela sua ID;
     * @param id recebe a variável ID do cliente a ser procurado;
     * @return informações do cliente procurado;
     */
    public Cliente procurarPorId(int id);
    
    /**
     * Método responsável por procurar um cliente pelo seu RG;     * 
     * @param rg recebe a variável RG do cliente a ser procurado;
     * @return informações do cliente procurado;
     */
    public Cliente procurarPorRg(long rg);
    
    /**
     * Método responsável por procurar um cliente pelo seu nome;
     * @param nome recebe a variável NOME do cliente a ser procurado;
     * @return informações do cliente procurado;
     */
    public Cliente procurarPorNome(String nome);
    
    /**
     * Método responsável por procurar um cliente pela sua matrícula;
     * @param matricula recebe a variável MATRÍCULA do cliente a ser procurado;
     * @return informações do cliente procurado;
     */
    public Cliente procurarPorMatricula(long matricula);
    
    /**
     * Método responsável por listar todos os clientes com o nome recebido na variável;
     * @param nome recebe a variável NOME do cliente;
     * @return lista com todos os clientes com o nome recebido na variável;
     */
    public List<Cliente> listarPorNome(String nome);
    
    /**
     * Método responsável pela inserção do objeto cliente editado no banco de dados
     * @param c
     * @param idCli 
     */
    public void editarCliente(Cliente c, int idCli);
}