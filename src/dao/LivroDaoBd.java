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
import model.Livro;

/**
 * Classe que implementa o LivroDao;
 * @author Tainara Specht
 * @author Diego Peixoto
 */
public class LivroDaoBd implements LivroDao {

    private Connection conexao;
    private PreparedStatement comando;

    @Override
    public void inserir(Livro livro) {
        int cod;
        try {
            String sql = "INSERT INTO livro (isbn, titulo, editora, autor, anoPublicacao, disponibilidade, qntdeTotalAlugado) "
                    + "VALUES (?,?,?,?,?,?,?)";

            conectarObtendoId(sql);
            comando.setLong(1, livro.getIsbn());
            comando.setString(2, livro.getTitulo());
            comando.setString(3, livro.getEditora());
            comando.setString(4, livro.getAutor());
            comando.setString(5, livro.getAnoPublicacao());
            comando.setBoolean(6, livro.isDisponibilidade());
            comando.setInt(7, livro.getQntdeTotalAlugado());
            comando.executeUpdate();
            //Obtém o resultSet para pegar o id
            ResultSet resultado = comando.getGeneratedKeys();
            if (resultado.next()) {
                //seta o id para o objeto
                cod = resultado.getInt(1);
                livro.setCod(cod);
            }

        } catch (SQLException ex) {
            Logger.getLogger(LivroDaoBd.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fecharConexao();
        }

    }

    @Override
    public void deletar(Livro livro) {
        try {
            String sql = "DELETE FROM livro WHERE cod=?";

            conectar(sql);
            comando.setInt(1, livro.getCod());
            comando.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(LivroDaoBd.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fecharConexao();
        }

    }

    @Override
    public void editar(Livro l, String novoX, String coluna) {
        String sql = "UPDATE livro SET " + coluna + "=(?) WHERE cod=(?)";
        try {
            conectar(sql);
            comando.setString(1, novoX);
            comando.setInt(2, l.getCod());
            comando.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(LivroDaoBd.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fecharConexao();
        }
    }

    @Override
    public void editarLivro(Livro livro, int idLivro) {
        String sql = "UPDATE livro SET isbn=(?), titulo=(?), editora=(?), autor=(?), anoPublicacao=(?) WHERE cod=(?)";
        try {
            conectar(sql);
            comando.setLong(1, livro.getIsbn());
            comando.setString(2, livro.getTitulo());
            comando.setString(3, livro.getEditora());
            comando.setString(4, livro.getAutor());
            comando.setString(5, livro.getAnoPublicacao());
            comando.setInt(6, idLivro);
            comando.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(LivroDaoBd.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fecharConexao();
        }
    }
    
    @Override
    public void editar(Livro l, long novoX, String coluna) {
        String sql = "UPDATE livro SET " + coluna + "=(?) WHERE cod=(?)";
        try {
            conectar(sql);
            comando.setLong(1, novoX);
            comando.setInt(2, l.getCod());
            comando.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(LivroDaoBd.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fecharConexao();
        }
    }

    @Override
    public List<Livro> listar() {
        List<Livro> listaLivros = new ArrayList<>();

        String sql = "SELECT * FROM livro ORDER BY titulo";

        try {
            conectar(sql);

            ResultSet resultado = comando.executeQuery();

            while (resultado.next()) {
                int cod = resultado.getInt("cod");
                long isbn = resultado.getLong("isbn");
                String titulo = resultado.getString("titulo");
                String editora = resultado.getString("editora");
                String autor = resultado.getString("autor");
                String anoPublicacao = resultado.getString("anoPublicacao");
                Boolean disponibilidade = resultado.getBoolean("disponibilidade");
                int qntdeTotalAlugado = resultado.getInt("qntdeTotalAlugado");

                Livro livro = new Livro(cod, isbn, titulo, editora, autor, anoPublicacao, disponibilidade, qntdeTotalAlugado);

                listaLivros.add(livro);

            }

        } catch (SQLException ex) {
            Logger.getLogger(LivroDaoBd.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fecharConexao();
        }

        return (listaLivros);
    }

    @Override
    public Livro procurarPorIsbn(long isbn) {
        String sql = "SELECT * FROM livro WHERE isbn = ?";

        try {
            conectar(sql);
            comando.setLong(1, isbn);

            ResultSet resultado = comando.executeQuery();

            if (resultado.next()) {
                int cod = resultado.getInt("cod");
                long isbnX = resultado.getLong("isbn");
                String titulo = resultado.getString("titulo");
                String editora = resultado.getString("editora");
                String autor = resultado.getString("autor");
                String anoPublicacao = resultado.getString("anoPublicacao");
                boolean disponibilidade = resultado.getBoolean("disponibilidade");
                int qntdeTotalAlugado = resultado.getInt("qntdeTotalAlugado");

                Livro livro = new Livro(cod, isbnX, titulo, editora, autor, anoPublicacao, disponibilidade, qntdeTotalAlugado);

                return livro;

            }

        } catch (SQLException ex) {
            Logger.getLogger(LivroDaoBd.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fecharConexao();
        }

        return (null);
    }

    @Override
    public Livro procurarPorId(int id) {
        String sql = "SELECT * FROM livro WHERE cod = ?";

        try {
            conectar(sql);
            comando.setInt(1, id);

            ResultSet resultado = comando.executeQuery();

            if (resultado.next()) {
                int cod = resultado.getInt("cod");
                long isbn = resultado.getLong("isbn");
                String titulo = resultado.getString("titulo");
                String editora = resultado.getString("editora");
                String autor = resultado.getString("autor");
                String anoPublicacao = resultado.getString("anoPublicacao");
                boolean disponibilidade = resultado.getBoolean("disponibilidade");
                int qntdeTotalAlugado = resultado.getInt("qntdeTotalAlugado");

                Livro livro = new Livro(cod, isbn, titulo, editora, autor, anoPublicacao, disponibilidade, qntdeTotalAlugado);

                return livro;

            }

        } catch (SQLException ex) {
            Logger.getLogger(LivroDaoBd.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fecharConexao();
        }

        return (null);
    }

    @Override
    public Livro procurarPorTitulo(String titulo) {
        String sql = "SELECT * FROM livro WHERE titulo = ?";

        try {
            conectar(sql);
            comando.setString(1, titulo);

            ResultSet resultado = comando.executeQuery();

            if (resultado.next()) {
                int cod = resultado.getInt("cod");
                long isbn = resultado.getLong("isbn");
                String tituloX = resultado.getString("titulo");
                String editora = resultado.getString("editora");
                String autor = resultado.getString("autor");
                String anoPublicacao = resultado.getString("anoPublicacao");
                boolean disponibilidade = resultado.getBoolean("disponibilidade");
                int qntdeTotalAlugado = resultado.getInt("qntdeTotalAlugado");

                Livro livro = new Livro(cod, isbn, tituloX, editora, autor, anoPublicacao, disponibilidade, qntdeTotalAlugado);

                return livro;

            }

        } catch (SQLException ex) {
            Logger.getLogger(LivroDaoBd.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fecharConexao();
        }

        return (null);
    }

    @Override
    public List<Livro> listarPorTitulo(String titulo) {
        List<Livro> listaLivros = new ArrayList<>();
        String sql = "SELECT * FROM livro WHERE titulo LIKE ?";

        try {
            conectar(sql);
            comando.setString(1, "%" + titulo + "%");
            ResultSet resultado = comando.executeQuery();

            while (resultado.next()) {
                int cod = resultado.getInt("cod");
                long isbn = resultado.getLong("isbn");
                String tituloX = resultado.getString("titulo");
                String editora = resultado.getString("editora");
                String autor = resultado.getString("autor");
                String anoPublicacao = resultado.getString("anoPublicacao");
                boolean disponibilidade = resultado.getBoolean("disponibilidade");
                int qntdeTotalAlugado = resultado.getInt("qntdeTotalAlugado");

                Livro livro = new Livro(cod, isbn, tituloX, editora, autor, anoPublicacao, disponibilidade, qntdeTotalAlugado);

                listaLivros.add(livro);
            }

        } catch (SQLException ex) {
            Logger.getLogger(LivroDaoBd.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fecharConexao();
        }

        return (listaLivros);
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
            Logger.getLogger(LivroDaoBd.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
