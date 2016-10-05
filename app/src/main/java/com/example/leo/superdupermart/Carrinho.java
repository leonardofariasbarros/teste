package com.example.leo.superdupermart;

/**
 * Created by Leo on 14/09/2016.
 */
public class Carrinho {
    private static final long serialVersionUID = 1L;
    private String login,nome, descricao, preco, quantidade, data, estado;

    public Carrinho(String login, String nome, String descricao, String preco, String quantidade, String data, String estado){
        this.login = login;
        this.nome = nome;
        this.descricao  = descricao;
        this.preco = preco;
        this.quantidade = quantidade;
        this.data = data;
        this.estado = estado;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getPreco() {
        return preco;
    }

    public void setPreco(String preco) {
        this.preco = preco;
    }

    public String getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(String quantidade) {
        this.quantidade = quantidade;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
