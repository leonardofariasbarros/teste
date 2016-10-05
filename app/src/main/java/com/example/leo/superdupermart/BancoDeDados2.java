package com.example.leo.superdupermart;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;

import java.io.ByteArrayOutputStream;

/**
 * Created by Leo on 05/09/2016.
 */
public class BancoDeDados2 extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 5;
    public static final String DATABASE_NAME = "SuperDuperMart.db";
    private static final String SQL_CREATE_TABLES = "CREATE TABLE Usuarios("+
            "login TEXT PRIMARY KEY, nome TEXT, endereco TEXT, senha TEXT, email TEXT, rg TEXT, cpf TEXT, cep TEXT, telefone TEXT)";
    private static final String SQL_CREATE_TABLES2 = "CREATE TABLE Produtos(codigo TEXT, nome TEXT, descricao TEXT, preco TEXT)";
    private static final String SQL_CREATE_TABLES3 = "CREATE TABLE MeuCarrinho(login TEXT, nome TEXT, descricao TEXT, preco TEXT, quantidade TEXT" +
            ", data TEXT, estado TEXT)";
    private static  final String SQL_POPULATE_TABLES = "INSERT INTO Usuarios VALUES ('Yza', 'Thayza Ramos dos Santos'," +
            "'Rua Benjamin Silva, n103, Centro', 'yza123', 'tata.thayza@gmail.com','21400059', '02706715286', '69010480', '991738190')";

    private static final String SQL_DELETE_TABLES = "DROP TABLE IF EXISTS Usuarios";
    private static final String SQL_DELETE_TABLES2 = "DROP TABLE IF EXISTS Produtos";
    private static final String SQL_DELETE_TABLES3 = "DROP TABLE IF EXISTS MeuCarrinho";

    private static final String SQL_POPULATE_TABLES3 = "INSERT INTO Produtos VALUES('1','Biscoito Cream Cracker','Marca: Marilan 400g'," +
            "'3.85')";
    private static final String SQL_POPULATE_TABLES4 = "INSERT INTO Produtos VALUES('2','Iogurte Danoninho','Marca: Danone 50g'," +
            "'3.85')";
    private static final String SQL_POPULATE_TABLES5 = "INSERT INTO Produtos VALUES('3','Refrigerante de Cola','Marca: Coca-Cola 2L'," +
            "'5.60')";
    private static final String SQL_POPULATE_TABLES6 = "INSERT INTO Produtos VALUES('4','Achocolatado em pó','Marca: Toddy 800g'," +
            "'12.45')";
    private static final String SQL_POPULATE_TABLES7 = "INSERT INTO Produtos VALUES('5','Leite condensado','Marca: Itambé 395g'," +
            "'3.99')";
    private static final String SQL_POPULATE_TABLES8 = "INSERT INTO Produtos VALUES('6','Feijão','Marca: Meu Biju 1kg'," +
            "'7.54')";
    private static final String SQL_POPULATE_TABLES9 = "INSERT INTO Produtos VALUES('7','Caldo para feijoada','Marca: Knorr 57g'," +
            "'3.26')";
    private static final String SQL_POPULATE_TABLES10 = "INSERT INTO Produtos VALUES('8','Shampoo','Marca: Aussie 400ml'," +
            "'20.86')";
    private static final String SQL_POPULATE_TABLES11 = "INSERT INTO Produtos VALUES('9','Creme Dental com Fluor','Marca: Colgate 90g'," +
            "'2.45')";
    private static final String SQL_POPULATE_TABLES12 = "INSERT INTO Produtos VALUES('10','Frango Congelado','Marca: Sadia 2kg'," +
            "'16.30')";
    private static final String SQL_POPULATE_TABLES13 = "INSERT INTO Produtos VALUES('11','Suco','Marca: Del Valle 1L'," +
            "'4.85')";
    private static final String SQL_POPULATE_TABLES14 = "INSERT INTO Produtos VALUES('12','Refresco','Marca: Tang 30g'," +
            "'0.55')";
    private static final String SQL_POPULATE_TABLES15 = "INSERT INTO Produtos VALUES('13','Requeijão Cremoso','Marca: Polenghi 200g'," +
            "'5.15')";
    private static final String SQL_POPULATE_TABLES16 = "INSERT INTO Produtos VALUES('14','Mini Chicken','Marca: Perdigão 300g'," +
            "'10.43')";
    private static final String SQL_POPULATE_TABLES17 = "INSERT INTO Produtos VALUES('15','Biscoito recheado','Marca: Oreo 470g'," +
            "'4.32')";
    private static final String SQL_POPULATE_TABLES18 = "INSERT INTO Produtos VALUES('16','Papel higiênico','Marca: Neve Neutra Care'," +
            "'5.17')";
    private static final String SQL_POPULATE_TABLES19 = "INSERT INTO Produtos VALUES('17','Sabão em Pó','Marca: Omo Multiação 2kg'," +
            "'10.16')";
    private static final String SQL_POPULATE_TABLES20 = "INSERT INTO Produtos VALUES('18','Refrigerante','Marca: Baré 350ml'," +
            "'3.85')";
    private static final String SQL_POPULATE_TABLES21 = "INSERT INTO Produtos VALUES('19','Achocolatado em Pó Nescau','Marca: Nestlé 200g'," +
            "'7.39')";
    private static final String SQL_POPULATE_TABLES22 = "INSERT INTO Produtos VALUES('20','Sucrilhos','Marca: Kelloggs 300g'," +
            "'8.54')";
    private static final String SQL_POPULATE_TABLES23 = "INSERT INTO Produtos VALUES('21','Nescau Cereal','Marca: Nestlé 560g'," +
            "'8.80')";
    private static final String SQL_POPULATE_TABLES24 = "INSERT INTO Produtos VALUES('22','Leite em Pó Ninho','Marca: Nestlé 400g'," +
            "'10.50')";
    private static final String SQL_POPULATE_TABLES25 = "INSERT INTO Produtos VALUES('23','Leite condensado Moça','Marca: Nestlé 395g'," +
            "'4.99')";
    private static final String SQL_POPULATE_TABLES26 = "INSERT INTO Produtos VALUES('24','Bebida achocolatada','Marca: Toddynho 200ml'," +
            "'1.19')";
    private static final String SQL_POPULATE_TABLES27 = "INSERT INTO Produtos VALUES('25','Arroz Integral Orgânico','Marca: Tio João 1kg'," +
            "'10.13')";
    private static final String SQL_POPULATE_TABLES28 = "INSERT INTO Produtos VALUES('26','Margarina','Marca: Becel 500g'," +
            "'5.95')";
    private static final String SQL_POPULATE_TABLES29 = "INSERT INTO Produtos VALUES('27','Margarina Qualy','Marca: Sadia 250g'," +
            "'2.54')";
    private static final String SQL_POPULATE_TABLES30 = "INSERT INTO Produtos VALUES('28','Farinha Láctea','Marca: Nestlé 380g'," +
            "'7.98')";
    private static final String SQL_POPULATE_TABLES31 = "INSERT INTO Produtos VALUES('29','Arroz','Marca: Tio João 5kg'," +
            "'20.65')";
    private static final String SQL_POPULATE_TABLES32 = "INSERT INTO Produtos VALUES('30','Água Sanitária','Marca: Brilux 5L'," +
            "'4.32')";

        public BancoDeDados2(Context context){
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        public void onCreate(SQLiteDatabase db) {
            db.execSQL(SQL_CREATE_TABLES);
            db.execSQL(SQL_CREATE_TABLES2);
            db.execSQL(SQL_CREATE_TABLES3);
            db.execSQL(SQL_POPULATE_TABLES);
            //db.execSQL(SQL_POPULATE_TABLES2);
            db.execSQL(SQL_POPULATE_TABLES3);
            db.execSQL(SQL_POPULATE_TABLES4);
            db.execSQL(SQL_POPULATE_TABLES5);
            db.execSQL(SQL_POPULATE_TABLES6);
            db.execSQL(SQL_POPULATE_TABLES7);
            db.execSQL(SQL_POPULATE_TABLES8);
            db.execSQL(SQL_POPULATE_TABLES9);
            db.execSQL(SQL_POPULATE_TABLES10);
            db.execSQL(SQL_POPULATE_TABLES11);
            db.execSQL(SQL_POPULATE_TABLES12);
            db.execSQL(SQL_POPULATE_TABLES13);
            db.execSQL(SQL_POPULATE_TABLES14);
            db.execSQL(SQL_POPULATE_TABLES15);
            db.execSQL(SQL_POPULATE_TABLES16);
            db.execSQL(SQL_POPULATE_TABLES17);
            db.execSQL(SQL_POPULATE_TABLES18);
            db.execSQL(SQL_POPULATE_TABLES19);
            db.execSQL(SQL_POPULATE_TABLES20);
            db.execSQL(SQL_POPULATE_TABLES21);
            db.execSQL(SQL_POPULATE_TABLES22);
            db.execSQL(SQL_POPULATE_TABLES23);
            db.execSQL(SQL_POPULATE_TABLES24);
            db.execSQL(SQL_POPULATE_TABLES25);
            db.execSQL(SQL_POPULATE_TABLES26);
            db.execSQL(SQL_POPULATE_TABLES27);
            db.execSQL(SQL_POPULATE_TABLES28);
            db.execSQL(SQL_POPULATE_TABLES29);
            db.execSQL(SQL_POPULATE_TABLES30);
            db.execSQL(SQL_POPULATE_TABLES31);
            db.execSQL(SQL_POPULATE_TABLES32);

        }

        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
            db.execSQL(SQL_DELETE_TABLES);
            db.execSQL(SQL_DELETE_TABLES2);
            db.execSQL(SQL_DELETE_TABLES3);
            onCreate(db);
        }




}
