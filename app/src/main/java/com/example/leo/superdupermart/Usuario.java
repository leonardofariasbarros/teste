package com.example.leo.superdupermart;

import java.io.Serializable;

/**
 * Created by Leo on 05/09/2016.
 */
public class Usuario implements Serializable{
    private static final long serialVersionUID = 1L;


    private String endereco;
    private String login;
    private String senha;
    private String email;
    private String rg;
    private String cpf;
    private String telefone;
    private String cep;
    private String nome;

    public Usuario(String login,String nome, String endereco, String senha, String email,String rg, String cpf, String cep, String telefone){
        this.nome = nome;
        this.endereco = endereco;
        this.login= login;
        this.senha = senha;
        this.email = email;
        this.rg = rg;
        this.cpf = cpf;
        this.telefone = telefone;
        this.cep = cep;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

}
