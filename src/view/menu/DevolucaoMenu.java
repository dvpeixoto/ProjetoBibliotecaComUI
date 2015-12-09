package view.menu;

/**
 * Classe que gera o MENU Devolução;
 *
 * @author Tainara Specht
 * @author Diego Peixoto
 *
 */

public class DevolucaoMenu {
    public static final String OP_DEVOLVERLIVRO = "1";
    public static final String OP_LISTARDEVOLUCAO = "2";
    public static final String OP_VOLTAR = "0";

    /**
     * Método que mostra o menu;
     * @return retorna as opções do menu;
     */

    public static String getOpcoes() {
        return ("\n--------------------------------------\n"
                + "1- Devolver Livro\n"
                + "2- Listar Devolução\n"
                + "0- Voltar"
                + "\n--------------------------------------");
    }    
}