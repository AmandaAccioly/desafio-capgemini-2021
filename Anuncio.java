import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;

public class Anuncio {
	
	private String nome;
	private Cliente cliente;
	private Date dataInicio;
	private Date dataTermino;
	private float investimentoPorDia;
	
	public Anuncio(String nome, Cliente cliente, int diaInicio, int mesInicio, int anoInicio, int diaTermino, int mesTermino, int anoTermino, float investimentoPorDia) {
		this.setNome(nome);
		this.setCliente(cliente);
		this.setDataInicio(diaInicio, mesInicio, anoInicio);
		this.setDataTermino(diaTermino, mesTermino, anoTermino);
		this.setInvestimentoPorDia(investimentoPorDia);
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Cliente getCliente() {
		return cliente;
	}
	
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public Date getDataInicio() {
		return dataInicio;
	}
	
	public void setDataInicio(int dia, int mes, int ano) {
		try {
			String dataInicioRecebida = dia + "/" + mes + "/" + ano;
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Date dataInicio = simpleDateFormat.parse(dataInicioRecebida);
			this.dataInicio = dataInicio;
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public Date getDataTermino() {
		return dataTermino;
	}
	
	public void setDataTermino(int dia, int mes, int ano) {
		try {
			String dataTerminoRecebida = dia + "/" + mes + "/" + ano;
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Date dataTermino = simpleDateFormat.parse(dataTerminoRecebida);
			this.dataTermino = dataTermino;
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public float getInvestimentoPorDia() {
		return investimentoPorDia;
	}
	
	public void setInvestimentoPorDia(float investimentoPorDia) {
		this.investimentoPorDia = investimentoPorDia;
	}
	
	public long getDuracao(){
		Instant instantInicio = dataInicio.toInstant();
		Instant instantTermino = dataTermino.toInstant();
		return Duration.between(instantInicio, instantTermino).toDays();
	}
	
	public float getTotalInvestido() {
		return this.getDuracao() * investimentoPorDia;
	}
	
	public int getVisualizacoesMax() {
		
			int pessoasQueVisualizam = (int) (getInvestimentoPorDia() * 30);
			
			int totalVisualizacoes = pessoasQueVisualizam;
			
			int visualizacoesNoCiclo = pessoasQueVisualizam;
			
			for (int i = 0; i < 3; i++) {
				int clicam = (int) (visualizacoesNoCiclo * 0.12);
				int compartilham = (int) (clicam * 0.15);
				
				if(compartilham >= 1) {
					visualizacoesNoCiclo = compartilham * 40;
					totalVisualizacoes += visualizacoesNoCiclo;
				}
				
				else {
					break;
				}
			}
			return (int) (totalVisualizacoes * getDuracao());
	}
	
	public int getCliquesMax() {
		
		int pessoasQueVisualizam = (int) (getInvestimentoPorDia() * 30);
		
		int totalCliques = 0;
		
		int visualizacoesNoCiclo = pessoasQueVisualizam;
		
		for (int i = 0; i < 3; i++) {
			int clicam = (int) (visualizacoesNoCiclo * 0.12);
			totalCliques += clicam;
			int compartilham = (int) (clicam * 0.15);
			
			if(compartilham >= 1) {
				visualizacoesNoCiclo = compartilham * 40;
			}
			
			else {
				break;
			}
		}
		return (int) (totalCliques * getDuracao());
	}
	
	public int getCompartilhamentoMax() {
		int pessoasQueVisualizam = (int) (getInvestimentoPorDia() * 30);
		
		int totalCompartilhamento = 0;
		
		int visualizacoesNoCiclo = pessoasQueVisualizam;
		
		for (int i = 0; i < 3; i++) {
			int clicam = (int) (visualizacoesNoCiclo * 0.12);
			int compartilham = (int) (clicam * 0.15);
			totalCompartilhamento += compartilham;
			
			if(compartilham >= 1) {
				visualizacoesNoCiclo = compartilham * 40;
			}
			
			else {
				break;
			}
		}
		return (int) (totalCompartilhamento * getDuracao());
	}

}
