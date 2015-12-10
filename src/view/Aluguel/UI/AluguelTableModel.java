/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package view.Aluguel.UI;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Livro;

/**
 *Classe responsável pela definição de tamanho de colunas/linhas do frame;
 * @author Tainara Specht
 * @author Diego Peixoto
 */
public class AluguelTableModel extends AbstractTableModel{

    private String header[];
    private List<Livro> livros;

    /**
     * Método responsável por definir os nomes dos headers;
     */
    public AluguelTableModel()
    {
        this.header = new String[]{"ISBN","Título","Editora","Autor(es)","Ano de Publicação","Disponibilidade"};
        this.livros = new ArrayList<Livro>();
    }

    /**
     * Método responsável por acessar as informações de header e lista de livros;
     * @param header - recebe uma variável do tipo string com os headers;
     * @param livros - recebe uma lista de livros;
     */
    public AluguelTableModel(String[] header, List<Livro> livros)
    {
        this.header = header;
        this.livros = livros;

    }

    @Override
    public int getRowCount() {
        return(this.livros.size());
    }

    @Override
    public int getColumnCount() {
        return(6);
    }

    @Override
    public String getColumnName(int column) {
        return header[column]; 
    }
 
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if(columnIndex == 0)
            return(this.livros.get(rowIndex).getIsbn());
        else if(columnIndex == 1)
            return(this.livros.get(rowIndex).getTitulo());
        else if(columnIndex == 2)
            return(this.livros.get(rowIndex).getEditora());
        else if(columnIndex == 3)
            return(this.livros.get(rowIndex).getAutor());
        else if(columnIndex == 4)
            return(this.livros.get(rowIndex).getAnoPublicacao());
        else if(columnIndex == 5)
            return(this.livros.get(rowIndex).isDisponibilidade());
        else 
           return null;
    }

    public void setLivro(List<Livro> livros)
    {
        this.livros = livros;
    }

    public Livro getLivro(int linha)
    {
        return(livros.get(linha));
    }
}
