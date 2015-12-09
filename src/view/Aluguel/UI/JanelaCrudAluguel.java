package view.Aluguel.UI;

import controller.UI.AluguelControllerUI;
import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class JanelaCrudAluguel extends JFrame {
    private AluguelControllerUI controller;
    
    public final static String PAINELFORM = "Formulario";
    public final static String PAINELTABELA = "Tabela";
    private JPanel painelPrincipal; 
    private PainelCadastroAluguel painelCadastroAluguel;
    private PainelAluguel painelAluguel;

    public JanelaCrudAluguel(JFrame pai, AluguelControllerUI controller) {
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
        painelAluguel = new PainelAluguel(controller);
        painelPrincipal.add(painelAluguel, PAINELTABELA);
        painelCadastroAluguel = new PainelCadastroAluguel(controller);
        painelPrincipal.add(painelCadastroAluguel, PAINELFORM);
        this.add(painelPrincipal);
    }

    public void mostrarPainel(String painel) {
        CardLayout card = (CardLayout) (painelPrincipal.getLayout());
        card.show(painelPrincipal, painel);

    }

    public PainelAluguel getPainelTabela() {
        return painelAluguel;
    }

    public void setController(AluguelControllerUI controller) {
        this.controller = controller;
    }
    
    public PainelCadastroAluguel getPainelFormulario() {
        return painelCadastroAluguel;
    }
}
