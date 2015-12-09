/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;

/**
 * * Classe MODEL para a classe Aluguel
 *
 * @author Tainara Specht
 * @author Diego Peixoto
 *
 */
public class Aluguel {

    private int idAluguel;
    private Cliente c;
    private Livro livrosAlugados;
    private Date dataAluguel;

    /**
     * Construtor da classe
     *
     * @param cIn recebe o objeto cliente;
     * @param livrosAlugadosIn recebe o objeto livros alugados;
     * @param dataAluguelIn recebe o objeto data do aluguel do livro;
     */
    public Aluguel(Date dataAluguelIn, Cliente cIn, Livro livrosAlugadosIn) {
        this.dataAluguel = dataAluguelIn;
        this.c = cIn;
        this.livrosAlugados = livrosAlugadosIn;
    }

    /**
     *
     * Construtor de acesso às informações do banco
     *
     * @param idAluguelIn recebe o objeto ID do aluguel;
     * @param dataAluguelIn recebe o objeto data do aluguel do livro;
     * @param cIn recebe o objeto cliente;
     * @param livrosAlugadosIn recebe o objeto livros alugados;
     */
    public Aluguel(int idAluguelIn, Date dataAluguelIn, Cliente cIn, Livro livrosAlugadosIn) {
        this.idAluguel = idAluguelIn;
        this.dataAluguel = dataAluguelIn;
        this.c = cIn;
        this.livrosAlugados = livrosAlugadosIn;

    }

    public void setId(int id) {
        this.idAluguel = id;
    }

    public int getId() {
        return idAluguel;
    }

    public void setC(Cliente c) {
        this.c = c;
    }

    public Cliente getC() {
        return c;
    }

    public void setLivrosAlugados(Livro livrosAlugados) {
        this.livrosAlugados = livrosAlugados;
    }

    public Livro getLivrosAlugados() {
        return livrosAlugados;
    }

    public void setDataAluguel(Date dataAluguel) {
        this.dataAluguel = dataAluguel;
    }

    public Date getDataAluguel() {
        return dataAluguel;
    }

    @Override
    public String toString() {
        return "Dados do aluguel: " + "\nCódigo do Aluguel: " + idAluguel + " \nNome do cliente: " + c.getNome()
                +"\nTitulo do livro alugado: " + livrosAlugados.getTitulo()
                + "\nData do aluguel: " + dataAluguel;
    }

}
