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
import model.Cliente;
/**
 * Classe que implementa o ClienteDao;
 * @author Tainara Specht
 * @author Diego Peixoto
 */

public class ClienteDaoBd implements ClienteDao {

    private Connection conexao;
    private PreparedStatement comando;

    //Metodo inserir alterado para trabalhar com data e receber o id auto 
    //increment e já inserir no objeto paciente (recebido por parâmetro)
    //Caso queira retornar, só retornar id.
    @Override
    public void inserir(Cliente cliente) {
        int id;
        try {
            String sql = "INSERT INTO cliente (nome, rg, telefone, matricula, livrosAlugados, qntdelivrosalugados, qntdeatraso) "
                    + "VALUES (?,?,?,?,?,?,?)";

            //Foi criado um novo método conectar para obter o id
            conectarObtendoId(sql);
            comando.setString(1, cliente.getNome());
            comando.setLong(2, cliente.getRg());
            comando.setString(3, cliente.getTelefone());
            comando.setLong(4, cliente.getMatricula());
            comando.setInt(5, cliente.getLivrosAlugados());
            comando.setInt(6, cliente.getQntdelivrosalugados());
            comando.setInt(7, cliente.getQntdeatraso());
            comando.executeUpdate();
            //Obtém o resultSet para pegar o id
            ResultSet resultado = comando.getGeneratedKeys();
            if (resultado.next()) {
                //seta o id para o objeto
                id = resultado.getInt(1);
                cliente.setId(id);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ClienteDaoBd.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fecharConexao();
        }

        //Caso queira retornar id:
        //return (id);
    }

    @Override
    public void deletar(Cliente cliente) {
        try {
            String sql = "DELETE FROM cliente WHERE rg=(?)";
            cliente.getRg();
            conectar(sql);
            comando.setLong(1, cliente.getRg());
            comando.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ClienteDaoBd.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fecharConexao();
        }

    }

    @Override
    public void editar(Cliente c, String novoX, String coluna) {
        String sql = "UPDATE cliente SET " + coluna + "=(?) WHERE id=(?)";
        try {
            conectar(sql);
            comando.setString(1, novoX);
            comando.setInt(2, c.getId());
            comando.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDaoBd.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fecharConexao();
        }
    }

    @Override
    public void editarCliente(Cliente c, int idCli) {
        String sql = "UPDATE cliente SET nome=(?), rg=(?), telefone=(?) WHERE id=(?)";
        try {
            conectar(sql);
            comando.setString(1, c.getNome());
            comando.setLong(2, c.getRg());
            comando.setString(3, c.getTelefone());
            comando.setInt(4, idCli);
            comando.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDaoBd.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fecharConexao();
        }
    }
      
    @Override
    public void editar(Cliente c, long novoX, String coluna) {
        String sql = "UPDATE cliente SET " + coluna + "=(?) WHERE id=(?)";
        try {
            conectar(sql);
            comando.setLong(1, novoX);
            comando.setInt(2, c.getId());
            comando.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDaoBd.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fecharConexao();
        }
    }
    
    @Override
    public List<Cliente> listar() {
        List<Cliente> listaClientes = new ArrayList<>();

        String sql = "SELECT * FROM cliente ORDER BY nome";

        try {
            conectar(sql);

            ResultSet resultado = comando.executeQuery();

            while (resultado.next()) {
                int id = resultado.getInt("id");
                String nome = resultado.getString("nome");
                long rg = resultado.getLong("rg");
                String telefone = resultado.getString("telefone");
                long matricula = resultado.getLong("matricula");
                int livrosAlugados = resultado.getInt("livrosAlugados");
                int qntdelivrosalugados = resultado.getInt("qntdelivrosalugados");
                int qntdeatraso = resultado.getInt("qntdeatraso");

                Cliente cli = new Cliente(id, nome, rg, telefone, matricula, livrosAlugados, qntdelivrosalugados, qntdeatraso);

                listaClientes.add(cli);

            }

        } catch (SQLException ex) {
            Logger.getLogger(ClienteDaoBd.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fecharConexao();
        }

        return (listaClientes);
    }

    @Override
    public Cliente procurarPorId(int id) {
        String sql = "SELECT * FROM cliente WHERE id = ?";

        try {
            conectar(sql);
            comando.setInt(1, id);

            ResultSet resultado = comando.executeQuery();

            if (resultado.next()) {
                String nome = resultado.getString("nome");
                long rg = resultado.getLong("rg");
                String telefone = resultado.getString("telefone");
                long matricula = resultado.getLong("matricula");
                int livrosAlugados = resultado.getInt("livrosAlugados");
                int qntdelivrosalugados = resultado.getInt("qntdelivrosalugados");
                int qntdeatraso = resultado.getInt("qntdeatraso");

                Cliente cli = new Cliente(id, nome, rg, telefone, matricula, livrosAlugados, qntdelivrosalugados, qntdeatraso);

                return cli;

            }

        } catch (SQLException ex) {
            Logger.getLogger(ClienteDaoBd.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fecharConexao();
        }

        return (null);
    }

    @Override
    public Cliente procurarPorRg(long rg) {
        String sql = "SELECT * FROM cliente WHERE rg = ?";

        try {
            conectar(sql);
            comando.setLong(1, rg);

            ResultSet resultado = comando.executeQuery();

            if (resultado.next()) {
                int id = resultado.getInt("id");
                String nome = resultado.getString("nome");
                long rgX = resultado.getLong("rg");
                String telefone = resultado.getString("telefone");
                long matricula = resultado.getLong("matricula");
                int livrosAlugados = resultado.getInt("livrosAlugados");
                int qntdelivrosalugados = resultado.getInt("qntdelivrosalugados");
                int qntdeatraso = resultado.getInt("qntdeatraso");

                Cliente cli = new Cliente(id, nome, rgX, telefone, matricula, livrosAlugados, qntdelivrosalugados, qntdeatraso);

                return cli;

            }

        } catch (SQLException ex) {
            Logger.getLogger(ClienteDaoBd.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fecharConexao();
        }

        return (null);
    }

    @Override
    public Cliente procurarPorNome(String nome) {
        String sql = "SELECT * FROM cliente WHERE nome = ?";

        try {
            conectar(sql);
            comando.setString(1, nome);

            ResultSet resultado = comando.executeQuery();

            if (resultado.next()) {
                int id = resultado.getInt("id");
                String nomeX = resultado.getString("nome");
                long rg = resultado.getLong("rg");
                String telefone = resultado.getString("telefone");
                long matricula = resultado.getLong("matricula");
                int livrosAlugados = resultado.getInt("livrosAlugados");
                int qntdelivrosalugados = resultado.getInt("qntdelivrosalugados");
                int qntdeatraso = resultado.getInt("qntdeatraso");

                Cliente cli = new Cliente(id, nomeX, rg, telefone, matricula, livrosAlugados, qntdelivrosalugados, qntdeatraso);

                return cli;

            }

        } catch (SQLException ex) {
            Logger.getLogger(ClienteDaoBd.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fecharConexao();
        }

        return (null);
    }

    @Override
    public Cliente procurarPorMatricula(long matricula) {
        String sql = "SELECT * FROM cliente WHERE matricula = ?";

        try {
            conectar(sql);
            comando.setLong(1, matricula);

            ResultSet resultado = comando.executeQuery();

            if (resultado.next()) {
                int id = resultado.getInt("id");
                String nome = resultado.getString("nome");
                long rg = resultado.getLong("rg");
                String telefone = resultado.getString("telefone");
                long matriculaX = resultado.getLong("matricula");
                int livrosAlugados = resultado.getInt("livrosAlugados");
                int qntdelivrosalugados = resultado.getInt("qntdelivrosalugados");
                int qntdeatraso = resultado.getInt("qntdeatraso");

                Cliente cli = new Cliente(id, nome, rg, telefone, matriculaX, livrosAlugados, qntdelivrosalugados, qntdeatraso);

                return cli;

            }

        } catch (SQLException ex) {
            Logger.getLogger(ClienteDaoBd.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fecharConexao();
        }

        return (null);
    }

    @Override
    public List<Cliente> listarPorNome(String nome) {
        List<Cliente> listaClientes = new ArrayList<>();
        String sql = "SELECT * FROM cliente WHERE nome LIKE ?";

        try {
            conectar(sql);
            comando.setString(1, "%" + nome + "%");
            ResultSet resultado = comando.executeQuery();

            while (resultado.next()) {
                int id = resultado.getInt("id");
                String nomeX = resultado.getString("nome");
                long rg = resultado.getLong("rg");
                String telefone = resultado.getString("telefone");
                long matricula = resultado.getLong("matricula");
                int livrosAlugados = resultado.getInt("livrosAlugados");
                int qntdelivrosalugados = resultado.getInt("qntdelivrosalugados");
                int qntdeatraso = resultado.getInt("qntdeatraso");

                Cliente cli = new Cliente(id, nomeX, rg, telefone, matricula, livrosAlugados, qntdelivrosalugados, qntdeatraso);

                listaClientes.add(cli);

            }

        } catch (SQLException ex) {
            Logger.getLogger(ClienteDaoBd.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fecharConexao();
        }

        return (listaClientes);
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
            Logger.getLogger(ClienteDaoBd.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
