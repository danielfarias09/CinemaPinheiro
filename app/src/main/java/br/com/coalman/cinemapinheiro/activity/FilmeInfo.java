package br.com.coalman.cinemapinheiro.activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;

import br.com.coalman.cinemapinheiro.R;
import br.com.coalman.cinemapinheiro.models.Filme;

public class FilmeInfo extends AppCompatActivity {
    private Filme filme;
    private TextView sala;
    private TextView nome;
    private TextView horario;
    private TextView genero;
    private TextView censura;
    private TextView duracao;
    private TextView sinopse;
    private SimpleDraweeView cartaz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_filme_info);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setLogo(R.drawable.ic_filme);
        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_arrow_left_bold));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        filme = (Filme) getIntent().getSerializableExtra("filme");

        sala = (TextView) findViewById(R.id.sala);
        nome = (TextView) findViewById(R.id.nome);
        horario = (TextView) findViewById(R.id.horario);
        genero = (TextView) findViewById(R.id.genero);
        censura = (TextView) findViewById(R.id.censura);
        duracao = (TextView) findViewById(R.id.duracao);
        sinopse = (TextView) findViewById(R.id.sinopse);
        cartaz = (SimpleDraweeView) findViewById(R.id.cartaz);

        sala.setText(filme.getSala());
        nome.setText(filme.getNome());
        horario.setText("Horário: " + filme.getHorario());
        genero.setText("Gênero: " + filme.getGenero());
        censura.setText("Censura: " + filme.getCensura());
        duracao.setText("Duração: " + filme.getDuracao());
        sinopse.setText("Sinopse: " + filme.getSinopse());
        //cartaz.setImageURI(Uri.parse(filme.getUrlImg()));


    }

}
