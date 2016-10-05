package com.example.leo.superdupermart;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Leo on 14/09/2016.
 */
public class CarrinhoDAO {
    private SQLiteDatabase bancoDeDados;

    public CarrinhoDAO (Context context){
        this.bancoDeDados =(new BancoDeDados2(context)).getWritableDatabase();
    }

    public boolean addProduto(Carrinho carrinho){
        try {
            String sqlCmd = "INSERT INTO MeuCarrinho VALUES ('" + carrinho.getLogin() +"', '" + carrinho.getNome() +
                    "', '" +  carrinho.getDescricao() + "', '" + carrinho.getPreco() +  "', '" + carrinho.getQuantidade() + "', '"
                    + carrinho.getData() + "', '" + carrinho.getEstado() + "')";
            this.bancoDeDados.execSQL(sqlCmd);
            return true;
        }
        catch (SQLException e) {
            Log.e("SuperDuperMartBD", e.getMessage());
            return false;
        }
    }

    public List<Carrinho> listaCarrinho(String login){
        Carrinho carrinho = null;
        ArrayList<Carrinho> list = new ArrayList();

        String sqlQuery = "SELECT * FROM MeuCarrinho WHERE login='"+login+"' AND estado=0";

        Cursor cursor = this.bancoDeDados.rawQuery(sqlQuery,null);

        cursor.moveToFirst();
        while(cursor.isAfterLast()!=true)
        {
            carrinho = new Carrinho(cursor.getString(0), cursor.getString(1), cursor.getString(2),cursor.getString(3),cursor.getString(4),
                    cursor.getString(5),cursor.getString(6));

            list.add(carrinho);
            cursor.moveToNext();
        }
        cursor.close();

        return list;
    }

    public List<Carrinho> listaHistorico(String login){
        Carrinho carrinho = null;
        ArrayList<Carrinho> list = new ArrayList();

        String sqlQuery = "SELECT * FROM MeuCarrinho WHERE login='"+login+"' AND estado=1";

        Cursor cursor = this.bancoDeDados.rawQuery(sqlQuery,null);

        cursor.moveToFirst();
        while(cursor.isAfterLast()!=true)
        {
            carrinho = new Carrinho(cursor.getString(0), cursor.getString(1), cursor.getString(2),cursor.getString(3),cursor.getString(4),
                    cursor.getString(5),cursor.getString(6));

            list.add(carrinho);
            cursor.moveToNext();
        }
        cursor.close();

        return list;
    }

    public boolean removerCarrinho(String login,String nome){
        String sqlCmd = "DELETE FROM MeuCarrinho WHERE login='"+login+"' AND nome='"+nome+"'";
        this.bancoDeDados.execSQL(sqlCmd);
        return true;
    }

    public boolean limparHistorico(String login){
        String sqlCmd = "DELETE FROM MeuCarrinho WHERE login='"+login+"' AND estado=1";
        this.bancoDeDados.execSQL(sqlCmd);
        return true;
    }

    public boolean alteraEstadoComprado(Carrinho carrinho){
        String sqlCmd = "UPDATE MeuCarrinho SET estado=1 WHERE nome='"+carrinho.getNome()+"'";
        this.bancoDeDados.execSQL(sqlCmd);
        return true;
    }


}
