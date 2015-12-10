package view.Devolucao.UI;

import controller.UI.DevolucaoControllerUI;
import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *Classe responsável por iniciar o frame com o CRUD da Devolução;
 * @author Diego Peixoto
 * @author Tainara Specht
 */

public class JanelaCrudDevolucao extends JFrame {
    private DevolucaoControllerUI controller;
    
    public final static String PAINELFORM = "Formulario";
    public final static String PAINELTABELA = "Tabela";
    private JPanel painelPrincipal; 
    private PainelCadastroDevolucao painelCadastroDevolucao;
    private PainelDevolucao painelDevolucao;

    /**
     *Método para criar a janela e atualizar dados nela;
     * @param pai - recebe o JFrame pai;
     * @param controller - recebe o controller da UI;
     */
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

    /**
     * Método para iniciar os componentes da janela;
     */
    private void iniciaComponentes() {
        painelPrincipal = new JPanel(new CardLayout());
        painelDevolucao = new PainelDevolucao(controller);
        painelPrincipal.add(painelDevolucao, PAINELTABELA);
        painelCadastroDevolucao = new PainelCadastroDevolucao(controller);
        painelPrincipal.add(painelCadastroDevolucao, PAINELFORM);
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
