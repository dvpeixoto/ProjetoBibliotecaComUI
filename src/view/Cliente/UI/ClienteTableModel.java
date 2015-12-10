/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package view.Cliente.UI;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Cliente;

/**
 *Classe responsável pela definição de tamanho de colunas/linhas do frame;
 * @author Tainara Specht
 * @author Diego Peixoto
 */
public class ClienteTableModel extends AbstractTableModel{

    private String header[];
    private List<Cliente> clientes;

    /**
     * Método responsável por definir os nomes dos headers;
     */
    public ClienteTableModel()
    {
        this.header = new String[]{"RG","Nome","Telefone","Matrícula","Livros em posse","Quantidade de livros alugados","Quantidade de Atrasos"};
        this.clientes = new ArrayList<Cliente>();
    }
    /**
     * Método responsável por acessar as informações de header e lista de livros;
     * @param header - recebe uma variável do tipo string com os headers;
     * @param livros - recebe uma lista de livros;
     */
    public ClienteTableModel(String[] header, List<Cliente> clientes)
    {
        this.header = header;
        this.clientes = clientes;

    }

    @Override
    public int getRowCount() {
        return(this.clientes.size());
    }

    @Override
    public int getColumnCount() {
        return(7);
    }

    @Override
    public String getColumnName(int column) {
        return header[column]; 
    }
 
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if(columnIndex == 0)
            return(this.clientes.get(rowIndex).getRg());
        else if(columnIndex == 1)
            return(this.clientes.get(rowIndex).getNome());
        else if(columnIndex == 2)
            return(this.clientes.get(rowIndex).getTelefone());
        else if(columnIndex == 3)
            return(this.clientes.get(rowIndex).getMatricula());
        else if(columnIndex == 4)
            return(this.clientes.get(rowIndex).getLivrosAlugados());
        else if(columnIndex == 5)
            return(this.clientes.get(rowIndex).getQntdelivrosalugados());
        else if(columnIndex == 6)
            return(this.clientes.get(rowIndex).getQntdeatraso());
        else 
           return null;
    }

    public void setCliente(List<Cliente> clientes)
    {
        this.clientes = clientes;
    }

    public Cliente getCliente(int linha)
    {
        return(clientes.get(linha));
    }
}
