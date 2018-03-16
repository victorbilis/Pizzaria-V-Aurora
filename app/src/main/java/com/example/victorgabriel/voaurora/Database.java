package com.example.victorgabriel.voaurora;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by bruno on 06/07/17.
 */

public class Database {
	//VARIAVEL PARA UTILIZAR O SQL LITE
    SQLiteDatabase db;
    public Database(Context context)
    {
		//CRIANDO O BANCO DE DADOS
        db = context.openOrCreateDatabase("banco_DB",context.MODE_PRIVATE,null);
        create_tables();
    }
    public void sql(String sql)
    {
		//EXECUTAR COMANDOS SQL, POR EXEMPLO, INSERT, UPDATE OU DELETE
        db.execSQL(sql);
    }
    public Cursor select(String sql)
    {
		//EXECUTAR COMANDO SELECT
        return db.rawQuery(sql,null);
    }

    public void create_tables()
    {
		//CRIANDO TABELAS
        db.execSQL("CREATE TABLE IF NOT EXISTS pizzas(" +
                "cod INTEGER not null," +
                "pizza varchar(500) not null," +
                "preco decimal(8,2) not null," +
                "desc varchar(500) not null," +
                "Primary key(cod));");
        db.execSQL("CREATE TABLE IF NOT EXISTS login(" +
                "cod int not null," +
                "user varchar(500)," +
                "senha varchar(500)," +
                "Primary key(cod));");

    }
}


