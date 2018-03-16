package com.example.victorgabriel.voaurora;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class card extends AppCompatActivity {

    Database db;

    ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);

        db = new Database(this);

        db.sql("INSERT INTO pizzas VALUES(null,'Mussarela',25,'Tomate, Mussarela e Presunto')");

        lista = (ListView) findViewById(R.id.list);


        List<Pizza> pizzas=new ArrayList<>();

        //EXECUTANDO SELECT
        Cursor c = db.select("SELECT * FROM pizzas;");

        Double pizza2 = null;
        //PEGANDO OS DADOS E SETANDO ELES NA CLASSE NOTAS
        while(c.moveToNext())
        {
            Pizza pizza = new Pizza();
            pizza.setCod(c.getInt(c.getColumnIndex("cod")));
            pizza.setPizza(c.getString(c.getColumnIndex("pizza")));
            pizza.setPreco(c.getDouble(c.getColumnIndex("preco")));
            pizza.setDesc(c.getString(c.getColumnIndex("desc")));
            pizza2 = c.getDouble(c.getColumnIndex("pizza"));
            pizzas.add(pizza);
        }




        //COLOCANDO O ADAPTER NA LISTA
        lista.setAdapter(new PizzasAdapter(this,pizzas,lista));
    }
}
