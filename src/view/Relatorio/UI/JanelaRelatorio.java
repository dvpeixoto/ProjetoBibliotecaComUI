/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.Relatorio.UI;

import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class JanelaRelatorio extends JFrame {
    
    public final static String PAINELFORM = "Formulario";
    public final static String PAINELTABELA = "Tabela";
    private JPanel painelPrincipal;  


    public JanelaRelatorio(JFrame pai) {
        iniciaComponentes();
        this.setTitle("Relatório");
        this.pack();
        this.setLocationRelativeTo(pai);
        this.setVisible(true);
    }

    private void iniciaComponentes() {
        painelPrincipal = new JPanel(new CardLayout()); 
        painelPrincipal.add(this, PAINELTABELA); 
        this.add(painelPrincipal);
    }

    public void mostrarPainel(String painel) {
        CardLayout card = (CardLayout) (painelPrincipal.getLayout());
        card.show(painelPrincipal, painel);

    }
    
}
    
    