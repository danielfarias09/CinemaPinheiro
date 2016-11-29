package br.com.coalman.cinemapinheiro.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;

import br.com.coalman.cinemapinheiro.R;
import br.com.coalman.cinemapinheiro.tasks.Tarefa;


public class ListFilmeQx extends Fragment {

    private TextView nome;
    private TextView horario;
    private SimpleDraweeView cartaz;
    private ListView listView;

    public ListFilmeQx() {
        // Required empty public constructor;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void getFilmes(){

        Tarefa tarefa = new Tarefa(getContext(),cartaz,nome,horario,listView,
                "http://www.obomvizinho.com.br/cinemas/cinema-quixada");

        tarefa.execute();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_list_filme, container, false);
        Fresco.initialize(getContext());
        cartaz = (SimpleDraweeView) view.findViewById(R.id.cartaz);
        nome = (TextView) view.findViewById(R.id.nome);
        horario = (TextView) view.findViewById(R.id.horario);

        listView = (ListView) view.findViewById(R.id.list);
        getFilmes();

        return view;
    }

}
