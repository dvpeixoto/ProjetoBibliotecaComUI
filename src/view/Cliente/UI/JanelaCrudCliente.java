package view.Cliente.UI;

import controller.UI.ClienteControllerUI;
import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class JanelaCrudCliente extends JFrame {
    private ClienteControllerUI controller;
    
    public final static String PAINELFORM = "Formulario";
    public final static String PAINELTABELA = "Tabela";
    private JPanel painelPrincipal;    
    private PainelCadastroCliente painelCadastroCliente;
    private PainelCliente painelCliente;

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

    private void iniciaComponentes() {
        painelPrincipal = new JPanel(new CardLayout());
        painelCliente = new PainelCliente(controller);
        painelPrincipal.add(painelCliente, PAINELTABELA);
        painelCadastroCliente = new PainelCadastroCliente(controller);
        painelPrincipal.add(painelCadastroCliente, PAINELFORM);
        this.add(painelPrincipal);
    }

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
