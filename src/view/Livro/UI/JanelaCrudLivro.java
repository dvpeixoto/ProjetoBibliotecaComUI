package view.Livro.UI;

import controller.UI.LivroControllerUI;
import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *Classe responsável por iniciar o frame com o CRUD do Livro;
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

    /**
     *Método para criar a janela e atualizar dados nela;
     * @param pai - recebe o JFrame pai;
     * @param controller - recebe o controller da UI;
     */
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
    /**
     * Método para iniciar os componentes da janela;
     */
    private void iniciaComponentes() {
        painelPrincipal = new JPanel(new CardLayout());
        painelLivro = new PainelLivro(controller);
        painelPrincipal.add(painelLivro, PAINELTABELA);
        painelCadastroLivro = new PainelCadastroLivro(controller);
        painelPrincipal.add(painelCadastroLivro, PAINELFORM);
        this.add(painelPrincipal);
    }
    /**
     * Método para mostrar o painel;
     * @param painel - recebe a variável painel do tipo string;
     */
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
