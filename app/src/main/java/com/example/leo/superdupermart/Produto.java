package com.example.leo.superdupermart;

/**
 * Created by Leo on 06/09/2016.
 */
public class Produto {
    private static final long serialVersionUID = 1L;
    private String id;
    private String nome;
    private String descricao;
    private String preco;

    public Produto(String id,String nome, String descricao,String preco){
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.preco= preco;

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

    public  String getId(){ return id; }

    public void setId(String id){ this.id = id; }

    public String getPreco() {
        return preco;
    }
    public void setPreco(String preco) {
        this.preco = preco;
    }
}
