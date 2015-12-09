/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package view.Livro.UI;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Livro;

/**
 * @author Diego Peixoto e Tainara Specht
 */
public class LivroTableModel extends AbstractTableModel{

    private String header[];
    private List<Livro> livros;

    public LivroTableModel()
    {
        this.header = new String[]{"ISBN","Titulo","Editora","Autor(es)","Ano de Publicação","Qnt Alugado"};
        this.livros = new ArrayList<Livro>();
    }

    public LivroTableModel(String[] header, List<Livro> livros)
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
            return(this.livros.get(rowIndex).getQntdeTotalAlugado());
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
