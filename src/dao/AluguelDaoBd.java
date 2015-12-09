package dao;

import banco.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Aluguel;
import model.Cliente;
import model.Livro;
import util.PrintUtil;
/**
 * Classe que implementa o AluguelDao;
 * @author Tainara Specht
 * @author Diego Peixoto
 */
public class AluguelDaoBd implements AluguelDao {

    private Connection conexao;
    private PreparedStatement comando;

    //Metodo inserir alterado para trabalhar com data e receber o id auto 
    //increment e já inserir no objeto paciente (recebido por parâmetro)
    //Caso queira retornar, só retornar id.
    @Override
    public void inserir(Aluguel aluguel) {
        int id;
        try {
            String sql = "INSERT INTO aluguel (dataAluguel, idCliente, codLivro) "
                    + "VALUES (?,?,?)";
            //Foi criado um novo método conectar para obter o id
            conectarObtendoId(sql);
            //Trabalhando com data: lembrando dataUtil -> dataSql
            java.sql.Date dataSql = new java.sql.Date(aluguel.getDataAluguel().getTime());
            comando.setDate(1, dataSql);
            comando.setInt(2, aluguel.getC().getId());
            comando.setInt(3, aluguel.getLivrosAlugados().getCod());
            comando.executeUpdate();
            //Obtém o resultSet para pegar o id
            ResultSet resultado = comando.getGeneratedKeys();
            if (resultado.next()) {
                //seta o id para o objeto
                id = resultado.getInt(1);
                aluguel.setId(id);
            }
            atualizarCliente(aluguel);
            atualizarLivro(aluguel);
            atualizarQntLivro(aluguel);
            atualizarQntLivrosAlugadosCliente(aluguel);
        } catch (SQLException ex) {
            Logger.getLogger(AluguelDaoBd.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fecharConexao();
        }

        //Caso queira retornar id:
        //return (id);
    }

    @Override
    public void atualizarCliente(Aluguel aluguel) {
        try {
            String sql = "UPDATE cliente SET livrosAlugados=(livrosAlugados+1) "
                    + "WHERE id=?";

            conectar(sql);
            comando.setInt(1, aluguel.getC().getId());
            comando.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(AluguelDaoBd.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fecharConexao();
        }
    }

    @Override
    public void atualizarLivro(Aluguel aluguel) {
        try {
            String sql = "UPDATE livro SET disponibilidade=false "
                    + "WHERE cod=?";
            conectar(sql);
            comando.setInt(1, aluguel.getLivrosAlugados().getCod());
            comando.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(AluguelDaoBd.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fecharConexao();
        }
    }

    @Override
    public void atualizarQntLivro(Aluguel aluguel) {
        try {
            String sql = "UPDATE livro SET qntdeTotalAlugado=(qntdeTotalAlugado+1) "
                    + "WHERE cod=?";
            conectar(sql);
            comando.setInt(1, aluguel.getLivrosAlugados().getCod());
            comando.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(AluguelDaoBd.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fecharConexao();
        }
    }

    @Override
    public void atualizarQntLivrosAlugadosCliente(Aluguel aluguel) {
        try {
            String sql = "UPDATE cliente SET qntdelivrosalugados=(qntdelivrosalugados+1) "
                    + "WHERE id=?";
            conectar(sql);
            comando.setInt(1, aluguel.getC().getId());
            comando.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(AluguelDaoBd.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fecharConexao();
        }
    }

    @Override
    public List<Aluguel> listar() {
        List<Aluguel> listaAluguel = new ArrayList<>();
        String sql = "SELECT * FROM aluguel ORDER BY idAluguel";
        try {
            conectar(sql);
            ResultSet resultado = comando.executeQuery();
            while (resultado.next()) {
                int idAluguel = resultado.getInt("idAluguel");
                //Trabalhando com data: lembrando dataSql -> dataUtil
                java.sql.Date dataSql = resultado.getDate("dataAluguel");
                java.util.Date dataUtil = new java.util.Date(dataSql.getTime());
                int id = resultado.getInt("idCliente");
                int cod = resultado.getInt("codLivro");
                ClienteDao clienteDao = new ClienteDaoBd();
                LivroDao livroDao = new LivroDaoBd();
                Cliente cli = clienteDao.procurarPorId(id);
                Livro livro = livroDao.procurarPorId(cod);

                Aluguel aluguel = new Aluguel(idAluguel, dataUtil, cli, livro);
                listaAluguel.add(aluguel);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AluguelDaoBd.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fecharConexao();
        }
        return (listaAluguel);
    }

    @Override
    public Aluguel procurarPorId(int id) {
        String sql = "SELECT * FROM aluguel WHERE idAluguel = ?";

        try {
            conectar(sql);
            comando.setLong(1, id);

            ResultSet resultado = comando.executeQuery();

            if (resultado.next()) {
               int idAluguel = resultado.getInt("idAluguel");
                //Trabalhando com data: lembrando dataSql -> dataUtil
                java.sql.Date dataSql = resultado.getDate("dataAluguel");
                java.util.Date dataUtil = new java.util.Date(dataSql.getTime());
                int idX = resultado.getInt("idCliente");
                int cod = resultado.getInt("codLivro");
                ClienteDao clienteDao = new ClienteDaoBd();
                LivroDao livroDao = new LivroDaoBd();
                Cliente cli = clienteDao.procurarPorId(idX);
                Livro livro = livroDao.procurarPorId(cod);

                Aluguel aluguel = new Aluguel(idAluguel, dataUtil, cli, livro);
                return aluguel;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AluguelDaoBd.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fecharConexao();
        }

        return (null);
    }

    public void conectar(String sql) throws SQLException {
        conexao = ConnectionFactory.getConnection();
        comando = conexao.prepareStatement(sql);
    }

    public void conectarObtendoId(String sql) throws SQLException {
        conexao = ConnectionFactory.getConnection();
        comando = conexao.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
    }

    public void fecharConexao() {
        try {
            if (comando != null) {
                comando.close();
            }
            if (conexao != null) {
                conexao.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(AluguelDaoBd.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
