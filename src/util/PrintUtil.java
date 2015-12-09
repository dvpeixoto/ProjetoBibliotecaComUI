package util;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Classe para enviar mensagens de erro e sucesso;
 *
 * @author Tainara Specht
 * @author Diego Peixoto
 */

public class PrintUtil {

    /**
     * Método para imprimir mensagem de erro;
     * @param janela - recebe o jFrame do evento;
     * @param msg - recebe a msg a ser exibida;
     */
    public static void printMessageError(JFrame janela, String msg) {
            JOptionPane.showMessageDialog(janela, 
                    msg,
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);        
    }

    /**
     * Método para imprimir mensagem de sucesso;
     * @param janela - recebe o jFrame do evento;
     * @param msg - recebe a msg a ser exibida;
     */
    public static void printMessageSucesso(JFrame janela, String msg) {
            JOptionPane.showMessageDialog(janela, 
                    msg,
                    "Sucesso",
                    JOptionPane.PLAIN_MESSAGE);        
    }    
}
