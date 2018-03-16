package com.example.victorgabriel.voaurora;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class cad extends AppCompatActivity {

    EditText nome;
    EditText senha;
    EditText end;
    EditText tel;
    EditText comp;

    Button btn_cad;
    Button btn_voltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        nome = (EditText) findViewById(R.id.txt_nome);
        senha = (EditText) findViewById(R.id.txt_senha);
        end = (EditText) findViewById(R.id.txt_end);
        tel = (EditText) findViewById(R.id.txt_tel);
        comp = (EditText) findViewById(R.id.txt_comp);

        btn_cad = (Button) findViewById(R.id.button6);
        btn_voltar = (Button) findViewById(R.id.button7);

        btn_cad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("String:","INSERT INTO login VALUES(null,'"+nome.getText()+"','"+senha.getText()+"','"+end.getText()+"','"+tel.getText()+"','"+comp.getText()+"');");
                new send("INSERT INTO login VALUES(null,'"+nome.getText()+"','"+senha.getText()+"','"+end.getText()+"','"+tel.getText()+"','"+comp.getText()+"');",cad.this).execute("");
            }
        });

        btn_voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

}
