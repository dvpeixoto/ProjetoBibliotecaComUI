/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.Aluguel.UI;

import controller.UI.AluguelControllerUI;
import controller.UI.ClienteControllerUI;
import controller.UI.LivroControllerUI;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import model.Livro;

/**
 *Classe responsável por realizar as ações dos botões na janela;
 * @author Tainara Specht
 * @author Diego Peixoto
 */
public class PainelAluguel extends javax.swing.JPanel {

    private ClienteControllerUI controllerCliente;
    private AluguelControllerUI controller;
    private LivroControllerUI controllerLivro;

    /**
     * Método responsável por acessar as informações da controller;
     * @param controller - recebe o objeto controller da UI;
     */
    public PainelAluguel(AluguelControllerUI controller) {
        initComponents();
        this.controller = controller;
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        botaoVisualizar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaLivros = new javax.swing.JTable();
        botaoInserir = new javax.swing.JButton();
        botaoEditar = new javax.swing.JButton();
        botaoRemover = new javax.swing.JButton();

        botaoVisualizar.setText("Visualizar");
        botaoVisualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoVisualizarActionPerformed(evt);
            }
        });

        tabelaLivros.setModel(new AluguelTableModel());
        jScrollPane1.setViewportView(tabelaLivros);

        botaoInserir.setText("Alugar");
        botaoInserir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoInserirActionPerformed(evt);
            }
        });

        botaoEditar.setText("Procurar livro por titulo");
        botaoEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoEditarActionPerformed(evt);
            }
        });

        botaoRemover.setText("Procurar livro por isbn");
        botaoRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoRemoverActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(botaoInserir)
                        .addGap(18, 18, 18)
                        .addComponent(botaoEditar)
                        .addGap(18, 18, 18)
                        .addComponent(botaoRemover)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(botaoVisualizar))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 474, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoInserir)
                    .addComponent(botaoEditar)
                    .addComponent(botaoRemover)
                    .addComponent(botaoVisualizar))
                .addGap(19, 19, 19))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 494, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 302, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents


    private void botaoVisualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoVisualizarActionPerformed
        controller.visualizarLivro();
    }//GEN-LAST:event_botaoVisualizarActionPerformed

    private void botaoInserirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoInserirActionPerformed
        try {
            String[] opcoes = {"sim", "não"};
            String rg = JOptionPane.showInputDialog("Digite seu RG");
            controllerCliente = new ClienteControllerUI();
            if (controllerCliente.clienteExiste(Long.parseLong(rg))) {
                int op = JOptionPane.showOptionDialog(this, "Deseja alugar este livro?", "Cliente encontrado!", JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE, null, opcoes, opcoes[0]);
                if (op == 0) {
                    JOptionPane.showMessageDialog(this, "Processando Dados...");
                    controller.salvarAluguel(Long.parseLong(rg));
                } else {
                    JOptionPane.showMessageDialog(this, "Livro não pode ser alugado!");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Cliente não encontrado!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Campo inválido!");
        }
    }//GEN-LAST:event_botaoInserirActionPerformed

    private void botaoEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoEditarActionPerformed
        try {
            String[] opcoes = {"sim", "não"};
            String titulo = JOptionPane.showInputDialog("Digite o titulo desejado:");
            controllerLivro = new LivroControllerUI();
            if (controllerLivro.LivroExiste(titulo)) {
                Livro li = controllerLivro.buscarLivroPorTitulo(titulo);
                JOptionPane.showMessageDialog(this, "Livro Encontrado! \nTitulo: "+li.getTitulo()+"\nAutor: "+li.getAutor()+"\nAno de Publicação: "+li.getAnoPublicacao());
                int op = JOptionPane.showOptionDialog(this, "Deseja alugar este livro?", "Livro encontrado!", JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE, null, opcoes, opcoes[0]);
                if (op == 0) {
                    String rg = JOptionPane.showInputDialog("Digite seu RG: ");
                    controllerCliente = new ClienteControllerUI();
                    if (controllerCliente.clienteExiste(Long.parseLong(rg))) {
                        controller.salvarAluguel(Long.parseLong(rg));
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Livro não foi alugado!");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Livro não encontrado!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Campo inválido!");
        }
    }//GEN-LAST:event_botaoEditarActionPerformed

    private void botaoRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoRemoverActionPerformed
         try {
            String[] opcoes = {"sim", "não"};
            String isbn = JOptionPane.showInputDialog("Digite o ISBN desejado:");
            controllerLivro = new LivroControllerUI();
            if (controllerLivro.LivroExiste(Long.parseLong(isbn))) {
                Livro li = controllerLivro.buscarLivroPorIsbn(Long.parseLong(isbn));
                JOptionPane.showMessageDialog(this, "Livro Encontrado! \nTitulo: "+li.getTitulo()+"\nAutor: "+li.getAutor()+"\nAno de Publicação: "+li.getAnoPublicacao());
                int op = JOptionPane.showOptionDialog(this, "Deseja alugar este livro?", "Livro encontrado!", JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE, null, opcoes, opcoes[0]);
                if (op == 0) {
                    String rg = JOptionPane.showInputDialog("Digite seu RG: ");
                    controllerCliente = new ClienteControllerUI();
                    if (controllerCliente.clienteExiste(Long.parseLong(rg))) {
                        controller.salvarAluguel(Long.parseLong(rg));
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Livro não foi alugado!");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Livro não encontrado!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Campo inválido!");
        }
    }//GEN-LAST:event_botaoRemoverActionPerformed

    public JButton getBotaoEditar() {
        return botaoEditar;
    }

    public JButton getBotaoInserir() {
        return botaoInserir;
    }

    public JButton getBotaoRemover() {
        return botaoRemover;
    }

    public JButton getBotaoVisualizar() {
        return botaoVisualizar;
    }

    public JScrollPane getjScrollPane1() {
        return jScrollPane1;
    }

    public JTable getTabelaAluguel() {
        return tabelaLivros;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoEditar;
    private javax.swing.JButton botaoInserir;
    private javax.swing.JButton botaoRemover;
    private javax.swing.JButton botaoVisualizar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabelaLivros;
    // End of variables declaration//GEN-END:variables
}
