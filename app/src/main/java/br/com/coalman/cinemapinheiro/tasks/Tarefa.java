package br.com.coalman.cinemapinheiro.tasks;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;

import br.com.coalman.cinemapinheiro.activity.FilmeInfo;
import br.com.coalman.cinemapinheiro.adapters.FileAdapter;
import br.com.coalman.cinemapinheiro.control.ControleFilme;
import br.com.coalman.cinemapinheiro.models.Filme;


/**
 * Created by Daniel on 21/11/2015.
 */
public class Tarefa extends AsyncTask<String, Void, ArrayList<Filme>> {
    public static String titulo;
    private static final String userAgent = "Mozilla/5.0 (jsoup)";
    private Context context;
    private ProgressDialog dialog;
    private TextView nome;
    private TextView horario;
    private SimpleDraweeView cartaz;
    private ListView listView;
    private FileAdapter adapter;
    private ControleFilme controle;
    private ArrayList<Filme> filmes;
    private String url;

    public Tarefa(Context context, SimpleDraweeView cartaz, TextView nome, TextView horario, ListView listView, String url){
        this.context = context;
        this.cartaz = cartaz;
        this.nome = nome;
        this.horario = horario;
        this.listView = listView;
        this.url=url;

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        dialog = ProgressDialog.show(context, "Aguarde!",
                "Carregando Filmes");
    }


    @Override
    protected ArrayList<Filme> doInBackground(String... params) {

        controle = new ControleFilme(url);

        filmes = controle.getFilmes();

        return filmes;

    }

    //Recebe como parâmetro o retorno do doInbackground
    protected void onPostExecute(final ArrayList<Filme> listaFilmes) {
        if(listaFilmes.size()>0 && listaFilmes!=null) {
            adapter = new FileAdapter(context, listaFilmes);
            listView.setAdapter(adapter);

            dialog.dismiss();

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Filme filme = listaFilmes.get(position);
                    context.startActivity(new Intent(context, FilmeInfo.class).putExtra("filme", filme));
                }
            });
        }else{
            dialog.dismiss();
            mostrarAlerta();
        }


    }

    private void mostrarAlerta(){
        AlertDialog alerta;
        //Cria o gerador do AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        //define o titulo
        builder.setTitle("Sem Conexão com a Internet");
        //define a mensagem
        builder.setMessage("Se Conecte com a Internet e Tente de Novo");
        builder.setNeutralButton("Sair", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);

            }
        });
        //cria o AlertDialog
        alerta = builder.create();
        //Exibe
        alerta.show();






    }
}
