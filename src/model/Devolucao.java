/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;

/**
 * Classe MODEL para a classe Devolução
 *
 * @author Tainara Specht
 * @author Diego Peixoto Classe Models para classe Devolucao
 */
public class Devolucao {

    private int idDevolucao;
    private Date dataDevolucao;
    private Aluguel aluguel;

    /**
     * Construtor da classe
     *
     * @param aluguelIn - recebe o aluguel de um livro;
     * @param dataDevolucao - recebe a data de devolução de um livro;
     */
    public Devolucao(Aluguel aluguelIn, Date dataDevolucao) {
        this.aluguel = aluguelIn;
        Date data = new Date();
        this.dataDevolucao = data;
    }

    /**
     * Construtor de acesso às informações do banco
     * @param idDevolucaoIn - recebe o ID da devolução de um livro;
     * @param aluguelIn - recebe o aluguel de um livro;
     * @param dataDevolucao - recebe a data de devolução de um livro;
     */
    public Devolucao(int idDevolucaoIn, Aluguel aluguelIn, Date dataDevolucao) {
        this.idDevolucao = idDevolucaoIn;
        this.aluguel = aluguelIn;
        Date data = new Date();
        this.dataDevolucao = data;
    }

    public int getIdDevolucao() {
        return idDevolucao;
    }

    public void setIdDevolucao(int idDevolucao) {
        this.idDevolucao = idDevolucao;
    }

    public Date getDataDevolucao() {
        return dataDevolucao;
    }

    public Aluguel getAluguel() {
        return aluguel;
    }

    public void setAluguel(Aluguel aluguel) {
        this.aluguel = aluguel;
    }

    public void setDataDevolucao(Date dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    @Override
    public String toString() {
        return "Dados da devolução: " + "\nCódigo da devolução: " + idDevolucao + "\nData da devolução: " + dataDevolucao
                + "\nNome do cliente: " + aluguel.getC().getNome()
                + "\nTitulo do livro: "+aluguel.getLivrosAlugados().getTitulo();
    }

}
