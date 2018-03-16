package com.example.victorgabriel.voaurora;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by i14584i on 18/08/2017.
 */

public class PizzasAdapter extends BaseAdapter {
    
	//CRIANDO VARIAVEIS
	
	List<Pizza> pizzas;
    
	Activity activity;
    
	Database db;
    
	ListView list;

	//CRIANDO PARAMETROS PARA FAZER A CLASSE SER EXECUTADA
    public PizzasAdapter(Activity activity, List<Pizza> pizzas,ListView list)
    {
        this.activity = activity;
        this.pizzas = pizzas;
        this.list = list;
        db = new Database(activity);
    }
    @Override
    public int getCount() {
        return pizzas.size();
    }

    @Override
    public Object getItem(int position) {
        //RETORNANDO POSIÇÃO DO OBJETO
		return pizzas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
	
	//CRIANDO UMA "TELA"
    class ViewHolder{
        TextView nome;
        TextView preco;
    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        
		//PEGANDO DADOS DA CLASSE NOTAS DE ACORDO COM O ITEM SELECIONADO
		final Pizza pizza = pizzas.get(position);
        
		ViewHolder holder = null;
        
		if(view == null)
        {
            holder = new ViewHolder();
			
			//INFLANDO OS DADOS 
            LayoutInflater inflater = LayoutInflater.from(activity);
            view = inflater.inflate(R.layout.linha_pizza_card,null);
            holder.nome = (TextView) view.findViewById(R.id.txt_nome);
            holder.preco = (TextView) view.findViewById(R.id.txt_preco);
            
			view.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) view.getTag();
        }
		
		
        holder.nome.setText(pizza.getPizza());
        holder.preco.setText(""+pizza.getPreco());

        return view;
    }
}
