package br.com.coalman.cinemapinheiro.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.coalman.cinemapinheiro.R;
import br.com.coalman.cinemapinheiro.models.Filme;

/**
 * Created by Daniel on 22/11/2015.
 */
@SuppressLint("ViewHolder")
public class FileAdapter extends BaseAdapter {

    private ArrayList<Filme> filmes;
    private LayoutInflater inflater;
    private Context context;

    public FileAdapter(Context context, ArrayList<Filme> filmes) {
        super();
        this.context = context;
        this.filmes = filmes;
        inflater = LayoutInflater.from(context);

    }

    @Override
    public int getCount() {
        return filmes.size();
    }

    @Override
    public Object getItem(int position) {

        return filmes.get(position);
    }

    @Override
    public long getItemId(int position) {

        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Filme filme = filmes.get(position);

        convertView = inflater.inflate(R.layout.filme, null);

        /*((TextView)convertView.findViewById(R.id.sala)).setText(filme.getSala());
        ((TextView)convertView.findViewById(R.id.nome)).setText(filme.getNome());
        ((TextView)convertView.findViewById(R.id.horario)).setText(filme.getHorario());
        ((TextView)convertView.findViewById(R.id.genero)).setText(filme.getGenero());
        ((TextView)convertView.findViewById(R.id.censura)).setText(filme.getCensura());
        ((TextView)convertView.findViewById(R.id.duracao)).setText(filme.getDuracao());
        ((TextView)convertView.findViewById(R.id.sinopse)).setText(filme.getSinopse());*/
        //((ImageView) convertView.findViewById(R.id.cartaz)).setImageURI(Uri.parse(filme.getUrlImg()));
        ((TextView)convertView.findViewById(R.id.nome)).setText(filme.getNome());
        ((TextView)convertView.findViewById(R.id.horario)).setText(filme.getHorario());

        return convertView;
    }
}