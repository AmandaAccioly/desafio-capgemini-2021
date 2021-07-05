import java.util.ArrayList;

public class Cliente {

	private String nome;
	
	private ArrayList<Anuncio> anuncios;

	public Cliente(String nome) {
		this.setNome(nome);
		this.anuncios = new ArrayList<Anuncio>();
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public ArrayList<Anuncio> getAnuncios() {
		return anuncios;
	}

	public void setAnuncios(ArrayList<Anuncio> anuncios) {
		this.anuncios = anuncios;
	}
}
