package com.example.leo.superdupermart;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Leo on 06/09/2016.
 */
public class ProdutoDAO {
    private SQLiteDatabase bancoDeDados;

    public ProdutoDAO (Context context){
        this.bancoDeDados =(new BancoDeDados2(context)).getWritableDatabase();
    }

    public Produto getProduto(String nome){
        Produto produto = null;

        String sqlQuery = "SELECT * FROM Produtos WHERE " +
                "nome = '" + nome +"'";
        Cursor cursor = this.bancoDeDados.rawQuery(sqlQuery, null);

        if(cursor.moveToNext()){
            produto = new Produto(cursor.getString(0), cursor.getString(1), cursor.getString(2),cursor.getString(3));
        }

        cursor.close();
        return produto;

    }

    public Cursor getProdutos(){
        return this.bancoDeDados.rawQuery("SELECT rowid AS _id,nome,descricao,preco FROM Produtos ORDER BY nome",null);

    }

    public List<Produto> listaProdutos(){
        Produto produto = null;
        ArrayList<Produto> list = new ArrayList();

        String sqlQuery = "SELECT * FROM Produtos";

        Cursor cursor = this.bancoDeDados.rawQuery(sqlQuery,null);

        cursor.moveToFirst();
        while(cursor.isAfterLast()!=true)
        {
            produto = new Produto(cursor.getString(0), cursor.getString(1), cursor.getString(2),cursor.getString(3));

            list.add(produto);
            cursor.moveToNext();
        }
        cursor.close();

        return list;
    }
}
