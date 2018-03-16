package com.example.victorgabriel.voaurora;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class login extends AppCompatActivity {

    Button btn;
    Button btn2;
    EditText login;
    EditText senha;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btn = (Button) findViewById(R.id.button);
        btn2 = (Button) findViewById(R.id.button2);

        login = (EditText) findViewById(R.id.editText);
        senha = (EditText) findViewById(R.id.editText2);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new getLogin("SELECT * FROM login WHERE nome_user = '"+login.getText()+"' AND senha_user='"+senha.getText()+"'",login.this,senha,login).execute();
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(login.this,cad.class);
                startActivity(i);
            }
        });
    }
}
