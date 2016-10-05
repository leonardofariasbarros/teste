package com.example.leo.superdupermart;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by Leo on 05/09/2016.
 */
public class UsuarioDAO {
    private SQLiteDatabase bancoDeDados;

    public UsuarioDAO (Context context){
        this.bancoDeDados =(new BancoDeDados2(context)).getWritableDatabase();
    }


    public Usuario getLogin(String login){
        Usuario usuario = null;

        String sqlQuery = "SELECT * FROM Usuarios WHERE " +
                "login = '" + login + "'";
        Cursor cursor = this.bancoDeDados.rawQuery(sqlQuery, null);

        if(cursor.moveToNext()){
            usuario = new Usuario(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3),
                    cursor.getString(4),cursor.getString(5), cursor.getString(6), cursor.getString(7), cursor.getString(8));
        }

        cursor.close();
        return usuario;

    }

    public Usuario getUsuario(String login, String senha){
        Usuario usuario = null;

        String sqlQuery = "SELECT * FROM Usuarios WHERE " +
                "login ='" + login + "' AND senha='" + senha +"'";
        Cursor cursor = this.bancoDeDados.rawQuery(sqlQuery, null);

        if(cursor.moveToNext()){
            usuario = new Usuario(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3),
                    cursor.getString(4),cursor.getString(5), cursor.getString(6), cursor.getString(7), cursor.getString(8));
        }

        cursor.close();
        return usuario;
    }

    public Usuario getEmail(String login){
        Usuario usuario = null;

        String sqlQuery = "SELECT * FROM Usuarios WHERE login ='"+login+"'";
        Cursor cursor = this.bancoDeDados.rawQuery(sqlQuery, null);

        if(cursor.moveToNext()){
            usuario = new Usuario(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3),
                    cursor.getString(4),cursor.getString(5), cursor.getString(6), cursor.getString(7), cursor.getString(8));
        }

        cursor.close();
        return usuario;
    }


    public boolean addUsuario(Usuario u){
        try {
            String sqlCmd = "INSERT INTO Usuarios VALUES ('" + u.getLogin() +"', '" + u.getNome() +
                   "', '" +  u.getEndereco() + "', '" + u.getSenha() +  "', '" + u.getEmail() + "', '" + u.getRg() + "', '" + u.getCpf() +
                    "', '" + u.getCep() + "', '" + u.getTelefone() + "')";
            this.bancoDeDados.execSQL(sqlCmd);
            return true;
        }
        catch (SQLException e) {
            Log.e("SuperDuperMartBD", e.getMessage());
            return false;
        }
    }

    public Cursor getUsuarios(){
       return this.bancoDeDados.rawQuery("SELECT rowid AS _id, " +
                "login, nome, "+"FROM Usuarios ORDER BY login",null);

    }


}
