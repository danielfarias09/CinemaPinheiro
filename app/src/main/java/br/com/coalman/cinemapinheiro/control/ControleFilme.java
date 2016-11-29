package br.com.coalman.cinemapinheiro.control;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import br.com.coalman.cinemapinheiro.models.Filme;

public class ControleFilme {

	private String url;
	private final String userAgent;
	
	public ControleFilme(String url){
		this.url=url;
		userAgent = "Mozilla/5.0 (jsoup)";
	}
	
	public ArrayList<Filme> getFilmes(){
		ArrayList<Filme> filmes = new ArrayList<>();
		
		
		Document documento=null;
		try {
			documento = Jsoup.connect(url).userAgent(userAgent).get();
			Elements aux = documento.getElementsByClass("span6");
			Elements gambs = documento.getElementsByClass("span12");
			
			int conn=5;
			
			for(Element a: aux){
				Filme filme = new Filme();
				
				filme.setNome(a.getElementsByTag("h3").text());
				filme.setSala(a.getElementsByTag("h4").text());
				filme.setUrlImg(a.getElementsByTag("img").attr("src"));
				
				Elements valor = a.getElementsByTag("span");
				
				filme.setHorario(valor.get(0).text());
				filme.setGenero(valor.get(1).text());
				filme.setCensura(valor.get(2).text());
				filme.setDuracao(valor.get(3).text());
				
				Elements sp = gambs.get(conn).getElementsByTag("div");
				Elements p = sp.get(0).getElementsByTag("p");
				
				filme.setSinopse(p.get(1).text());
				conn++;
				filmes.add(filme);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return filmes;
	}
}
