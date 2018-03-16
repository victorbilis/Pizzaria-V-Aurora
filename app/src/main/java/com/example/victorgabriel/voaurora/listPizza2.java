package com.example.victorgabriel.voaurora;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Victor Gabriel on 01/08/2017.
 */

public class listPizza2 extends AsyncTask<String,String,String> {
    Activity context;
    String data1 = "";
    String data2 = "";
    TextView txt;

    int qtd = 0;

    ProgressDialog dialog;
    public listPizza2(Activity context, TextView txt, String data1,String data2, int qtd)
    {
        this.context = context;
        this.txt = txt;
        this.qtd = qtd;
        this.data1 = data1;
        this.data2 = data2;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        dialog = new ProgressDialog(context);
        dialog.setTitle("Aviso");
        dialog.setIcon(R.drawable.foto_dois);
        dialog.setMessage("Calculando...");
        dialog.show();
    }

    @Override
    protected String doInBackground(String... strings) {
        String res = "";
        String res2 = "";
        String res3 = "";

        try{
            URL url = new URL("http://pizzariavoaurora.000webhostapp.com/php/listPizza.php");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setDoInput(true);
            con.setDoOutput(true);
            con.setUseCaches(true);
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-type","application/x-www-form-urlencoded");

            DataOutputStream printer = new DataOutputStream(con.getOutputStream());
            printer.writeBytes("sql="+data1);
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

        try{
            URL url = new URL("http://pizzariavoaurora.000webhostapp.com/php/listPizza.php");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setDoInput(true);
            con.setDoOutput(true);
            con.setUseCaches(true);
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-type","application/x-www-form-urlencoded");

            DataOutputStream printer = new DataOutputStream(con.getOutputStream());
            printer.writeBytes("sql="+data2);
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
            res2 = ""+e;
        }

        String partes[] = res.split("&");
        String partes2[] = res2.split("&");

        Double preco1 = Double.parseDouble(partes[2]);
        Double preco2 = Double.parseDouble(partes2[2]);

        if(preco1 > preco2)
        {
            return res;
        }
        else
        {
            return res2;
        }



    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        String partes[] = s.split("&");
        Double preco = Double.parseDouble(partes[2]);
        Double total = preco * qtd;
        txt.setText(""+total);
        dialog.dismiss();
    }
}
