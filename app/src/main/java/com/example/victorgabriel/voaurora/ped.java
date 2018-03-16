package com.example.victorgabriel.voaurora;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;

public class ped extends AppCompatActivity {

    Spinner sp,sp2,sp3;
    EditText qtd1,qtd2;
    TextView total1,total2;
    Button btn1, btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ped);

        final TabHost host = (TabHost)findViewById(R.id.tabhost);
        host.setup();
        //Tab 1
        TabHost.TabSpec spec = host.newTabSpec("Tab One");
        spec.setContent(R.id.tab1);
        spec.setIndicator("1 Sabor");
        host.addTab(spec);

        //Tab 3
        spec = host.newTabSpec("Tab Two");
        spec.setContent(R.id.tab2);
        spec.setIndicator("2 Sabores");
        host.addTab(spec);

        host.setCurrentTab(0);

        sp = (Spinner) findViewById(R.id.spinner);
        sp2 = (Spinner) findViewById(R.id.spinner2);
        sp3 = (Spinner) findViewById(R.id.spinner3);

        new listSabores(this,sp,sp2,sp3,"SELECT * FROM pizzas;").execute("");

        qtd1 = (EditText) findViewById(R.id.txt_qtd1);
        qtd2 = (EditText) findViewById(R.id.txt_qtd2);
        total1 = (TextView) findViewById(R.id.txt_valor1);
        total2 = (TextView) findViewById(R.id.txt_valor2);
        btn1 = (Button) findViewById(R.id.btn_calc1);
        btn2 = (Button) findViewById(R.id.btn_calc2);

        final int qtd = Integer.parseInt(qtd1.getText().toString());
        final int qtd_dois = Integer.parseInt(qtd2.getText().toString());
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new listPizza(ped.this,total1,"SELECT * FROM pizza WHERE nome_pizza = '"+sp.getSelectedItem().toString()+"'",qtd).execute("");
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new listPizza2(ped.this,total2,"SELECT * FROM pizza WHERE nome_pizza = '"+sp2.getSelectedItem().toString()+"'","SELECT * FROM pizza WHERE nome_pizza = '"+sp3.getSelectedItem().toString()+"'",qtd_dois).execute("");
            }
        });

    }
}
