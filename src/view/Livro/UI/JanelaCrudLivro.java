package view.Livro.UI;

import controller.UI.LivroControllerUI;
import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Diego Peixoto
 * @author Tainara Specht
 */

public class JanelaCrudLivro extends JFrame {
    private LivroControllerUI controller;
    
    public final static String PAINELFORM = "Formulario";
    public final static String PAINELTABELA = "Tabela";
    private JPanel painelPrincipal;    
    private PainelCadastroLivro painelCadastroLivro;
    private PainelLivro painelLivro;

    public JanelaCrudLivro(JFrame pai, LivroControllerUI controller) {
        this.controller = controller;
        this.controller.setJanela(this);
        iniciaComponentes();
        controller.atualizaTabela();
        this.setTitle("Biblioteca");
        this.pack();
        this.setLocationRelativeTo(pai);
        this.setVisible(true);
    }

    private void iniciaComponentes() {
        painelPrincipal = new JPanel(new CardLayout());
        painelLivro = new PainelLivro(controller);
        painelPrincipal.add(painelLivro, PAINELTABELA);
        painelCadastroLivro = new PainelCadastroLivro(controller);
        painelPrincipal.add(painelCadastroLivro, PAINELFORM);
        this.add(painelPrincipal);
    }

    public void mostrarPainel(String painel) {
        CardLayout card = (CardLayout) (painelPrincipal.getLayout());
        card.show(painelPrincipal, painel);

    }

    public PainelCadastroLivro getPainelFormulario() {
        return painelCadastroLivro;
    }

    public PainelLivro getPainelTabela() {
        return painelLivro;
    }

    public void setController(LivroControllerUI controller) {
        this.controller = controller;
    }
    
    

}
