package com.example.victorgabriel.voaurora;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;

/**
 * Created by Victor Gabriel on 01/08/2017.
 */

public class getLogin extends AsyncTask<String,String,String> {
    Activity ac;
    String data = "";
    String res = "";
    String senha2;
    String login2;
    ProgressDialog dialog;
    Database db;

    public getLogin(String data, Activity ac, EditText senha, EditText login)
    {


        this.ac = ac;
        db = new Database(ac);
        this.data = data;
        senha2 = senha.getText().toString();
        login2 = login.getText().toString().trim();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        dialog = new ProgressDialog(ac);
        dialog.setTitle("Aviso");
        dialog.setIcon(R.drawable.foto_dois);
        dialog.setMessage("Entrando");
        dialog.show();
    }

    @Override
    protected String doInBackground(String... strings) {
        try{
            URL url = new URL("http://pizzariavoaurora.000webhostapp.com/php/listLogin.php");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setDoInput(true);
            con.setDoOutput(true);
            con.setUseCaches(true);
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-type","application/x-www-form-urlencoded");

            DataOutputStream printer = new DataOutputStream(con.getOutputStream());
            printer.writeBytes("sql="+data+"&login="+login2+"&senha="+senha2);
            printer.flush();
            printer.close();

            BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream(),"UTF-8"));
            String line = "";

            while((line=reader.readLine()) != null)
            {
                res+=line;
            }
        }
        catch(Exception e)
        {
            res = ""+e;
        }
        return res;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        try {
            String partes[] = s.split(";");
            Log.i("Script:",s);
            if (partes[0].equals("a")) {
                db.sql("INSERT INTO login VALUES(" + partes[1] + ",'" + login2 + "','" + senha2 + "')");
                dialog.dismiss();
                Intent i = new Intent(ac, ini.class);
                ac.startActivity(i);
                ac.finish();
            } else {
                dialog.dismiss();
                AlertDialog.Builder detalhes = new AlertDialog.Builder(ac);
                detalhes.setTitle("Aviso");
                detalhes.setMessage("Login ou senha incorretas!");
                detalhes.setIcon(R.drawable.foto_dois);
                detalhes.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                detalhes.show();
            }
        }
        catch(Exception e)
        {
            dialog.dismiss();
            AlertDialog.Builder detalhes = new AlertDialog.Builder(ac);
            detalhes.setTitle("Aviso");
            detalhes.setMessage("Esta conta não existe");
            detalhes.setIcon(R.drawable.foto_dois);
            detalhes.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            detalhes.show();
        }
    }

}
