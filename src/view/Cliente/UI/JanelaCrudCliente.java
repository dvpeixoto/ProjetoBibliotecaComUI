package view.Cliente.UI;

import controller.UI.ClienteControllerUI;
import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *Classe responsável por iniciar o frame com o CRUD do Cliente;
 * @author Tainara Specht
 * @author Diego Peixoto
 */

public class JanelaCrudCliente extends JFrame {
    private ClienteControllerUI controller;
    
    public final static String PAINELFORM = "Formulario";
    public final static String PAINELTABELA = "Tabela";
    private JPanel painelPrincipal;    
    private PainelCadastroCliente painelCadastroCliente;
    private PainelCliente painelCliente;

    /**
     *Método para criar a janela e atualizar dados nela;
     * @param pai - recebe o JFrame pai;
     * @param controller - recebe o controller da UI;
     */
    public JanelaCrudCliente(JFrame pai, ClienteControllerUI controller) {
        this.controller = controller;
        this.controller.setJanela(this);
        iniciaComponentes();
        controller.atualizaTabela();
        this.setTitle("Área do Cliente");
        this.pack();
        this.setLocationRelativeTo(pai);
        this.setVisible(true);
    }
    /**
     * Método para iniciar os componentes da janela;
     */
    private void iniciaComponentes() {
        painelPrincipal = new JPanel(new CardLayout());
        painelCliente = new PainelCliente(controller);
        painelPrincipal.add(painelCliente, PAINELTABELA);
        painelCadastroCliente = new PainelCadastroCliente(controller);
        painelPrincipal.add(painelCadastroCliente, PAINELFORM);
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

    public PainelCadastroCliente getPainelFormulario() {
        return painelCadastroCliente;
    }

    public PainelCliente getPainelTabela() {
        return painelCliente;
    }

    public void setController(ClienteControllerUI controller) {
        this.controller = controller;
    }
    
    

}
