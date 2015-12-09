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
import model.Devolucao;

/**
 * Classe que implementa o DevolucaoDao;
 * @author Tainara Specht
 * @author Diego Peixoto
 */
public class DevolucaoDaoBd implements DevolucaoDao {

    private Connection conexao;
    private PreparedStatement comando;

    //Metodo inserir alterado para trabalhar com data e receber o id auto 
    //increment e já inserir no objeto paciente (recebido por parâmetro)
    //Caso queira retornar, só retornar id.
    @Override
    public void inserir(Devolucao devolucao, boolean atrasado) {
        int id;
        try {
            String sql = "INSERT INTO devolucao (idAluguel, idCliente, codLivro, dataDevolucao) "
                    + "VALUES (?,?,?,?)";

            //Foi criado um novo método conectar para obter o id
            conectarObtendoId(sql);
            comando.setInt(1, devolucao.getAluguel().getId());
            comando.setInt(2, devolucao.getAluguel().getC().getId());
            comando.setInt(3, devolucao.getAluguel().getLivrosAlugados().getCod());
            //Trabalhando com data: lembrando dataUtil -> dataSql
            java.sql.Date dataSql = new java.sql.Date(devolucao.getDataDevolucao().getTime());
            comando.setDate(4, dataSql);
            comando.executeUpdate();
            //Obtém o resultSet para pegar o id
            ResultSet resultado = comando.getGeneratedKeys();
            if (resultado.next()) {
                //seta o id para o objeto
                id = resultado.getInt(1);
                devolucao.setIdDevolucao(id);
            }
            atualizarCliente(devolucao);
            atualizarLivro(devolucao);
            if (atrasado == true) {
                atualizarAtrasoCliente(devolucao);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DevolucaoDaoBd.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fecharConexao();
        }

    }

    @Override
    public void atualizarCliente(Devolucao devolucao) {
        try {
            String sql = "UPDATE cliente SET livrosAlugados=(livrosAlugados-1) "
                    + "WHERE id=(?)";

            conectar(sql);
            comando.setInt(1, devolucao.getAluguel().getC().getId());
            comando.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DevolucaoDaoBd.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fecharConexao();
        }
    }

    @Override
    public void atualizarAtrasoCliente(Devolucao devolucao) {
        try {
            String sql = "UPDATE cliente SET qntdeatraso=(qntdeatraso+1) "
                    + "WHERE id=(?)";

            conectar(sql);
            comando.setInt(1, devolucao.getAluguel().getC().getId());
            comando.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DevolucaoDaoBd.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fecharConexao();
        }
    }

    @Override
    public void atualizarLivro(Devolucao devolucao) {
        try {
            String sql = "UPDATE livro SET disponibilidade=true WHERE cod=(?)";
            conectar(sql);
            comando.setInt(1, devolucao.getAluguel().getLivrosAlugados().getCod());
            comando.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DevolucaoDaoBd.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fecharConexao();
        }
    }

    @Override
    public List<Devolucao> listar() {
        List<Devolucao> listaDevolucao = new ArrayList<>();
        String sql = "SELECT * FROM devolucao ORDER BY idDevolucao";
        try {
            conectar(sql);
            ResultSet resultado = comando.executeQuery();
            while (resultado.next()) {
                int idDevolucao = resultado.getInt("idDevolucao");
                int idAluguel = resultado.getInt("idAluguel");
                //Trabalhando com data: lembrando dataSql -> dataUtil
                java.sql.Date dataSql = resultado.getDate("dataDevolucao");
                java.util.Date dataUtil = new java.util.Date(dataSql.getTime());
                AluguelDao aluguelDao = new AluguelDaoBd();
                Aluguel aluguel = aluguelDao.procurarPorId(idAluguel);
                Devolucao devolucao = new Devolucao(idDevolucao, aluguel, dataUtil);
                listaDevolucao.add(devolucao);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DevolucaoDaoBd.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fecharConexao();
        }
        return (listaDevolucao);
    }

    @Override
    public Devolucao procurarPorId(int id) {
        String sql = "SELECT * FROM devolucao WHERE idDevolucao = ?";

        try {
            conectar(sql);
            comando.setInt(1, id);

            ResultSet resultado = comando.executeQuery();

            if (resultado.next()) {
                int idDevolucao = resultado.getInt("idDevolucao");
                int idAluguel = resultado.getInt("idAluguel");
                //Trabalhando com data: lembrando dataSql -> dataUtil
                java.sql.Date dataSql = resultado.getDate("dataDevolucao");
                java.util.Date dataUtil = new java.util.Date(dataSql.getTime());
                AluguelDao aluguelDao = new AluguelDaoBd();
                Aluguel aluguel = aluguelDao.procurarPorId(idAluguel);
                Devolucao devolucao = new Devolucao(idDevolucao, aluguel, dataUtil);
                return devolucao;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DevolucaoDaoBd.class.getName()).log(Level.SEVERE, null, ex);
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
