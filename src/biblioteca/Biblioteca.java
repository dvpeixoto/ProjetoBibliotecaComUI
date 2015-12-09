package biblioteca;

import view.menu.UI.PainelMenu;

/**
 * Classe responsável por executar o Main.UI;
 * @author Tainara Specht
 * @author Diego Peixoto
 */
public class Biblioteca {

    /**
     * Método para chamar o MainUI, que exibe todos os menus principais.
     * @param args
     */
    public static void main(String[] args) {  
          PainelMenu menu = new PainelMenu();
          menu.setVisible(true);
    }
    
}
