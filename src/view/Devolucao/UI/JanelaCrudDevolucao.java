package view.Devolucao.UI;

import controller.UI.DevolucaoControllerUI;
import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class JanelaCrudDevolucao extends JFrame {
    private DevolucaoControllerUI controller;
    
    public final static String PAINELFORM = "Formulario";
    public final static String PAINELTABELA = "Tabela";
    private JPanel painelPrincipal; 
    private PainelCadastroDevolucao painelCadastroDevolucao;
    private PainelDevolucao painelDevolucao;

    public JanelaCrudDevolucao(JFrame pai, DevolucaoControllerUI controller) {
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
        painelDevolucao = new PainelDevolucao(controller);
        painelPrincipal.add(painelDevolucao, PAINELTABELA);
        painelCadastroDevolucao = new PainelCadastroDevolucao(controller);
        painelPrincipal.add(painelCadastroDevolucao, PAINELFORM);
        this.add(painelPrincipal);
    }

    public void mostrarPainel(String painel) {
        CardLayout card = (CardLayout) (painelPrincipal.getLayout());
        card.show(painelPrincipal, painel);

    }

    public PainelDevolucao getPainelTabela() {
        return painelDevolucao;
    }

    public void setController(DevolucaoControllerUI controller) {
        this.controller = controller;
    }
    
    public PainelCadastroDevolucao getPainelFormulario() {
        return painelCadastroDevolucao;
    }
}
