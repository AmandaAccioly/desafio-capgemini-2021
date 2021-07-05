import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Application {
	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		int opcao = 0;		
		
		while (opcao != 5) {
			System.out.println("OP��ES\n" +
					"1 - Cadastrar cliente\n" + 
					"2 - Cadastrar an�ncio\n" +
					"3 - Ver relat�rios por intervalo de tempo\n" + 
					"4 - Ver relat�rios por cliente\n" +
					"5 - Sair");

			opcao = Integer.parseInt(input.nextLine());
			
			switch (opcao) {
			
				case 1:
					System.out.println("Nome: ");
					String nome = input.nextLine();
					Cliente cliente = new Cliente(nome);
					clientes.add(cliente);
					break;
					
				case 2:
					System.out.println("ESCOLHA UM CLIENTE PARA CADASTRAR O AN�NCIO\n");
					for (int i = 0; i < clientes.size() ; i++ ) {
						System.out.println((i+1) + " - " + clientes.get(i).getNome());
					}
					System.out.println((clientes.size()+1) + " - Voltar ao menu anterior e cadastrar novo cliente");
					int opcaoCadastrarAnuncio = Integer.parseInt(input.nextLine());
					if(opcaoCadastrarAnuncio == (clientes.size()+1)){
						continue;						
					}
					else {
						System.out.println("Nome do an�ncio: ");
						String nomeDoAnuncio = input.nextLine();
						
						System.out.println("Dia de in�cio do an�ncio: ");
						int diaInicio = Integer.parseInt(input.nextLine());
						
						System.out.println("M�s de in�cio do an�ncio: ");
						int mesInicio = Integer.parseInt(input.nextLine());
						
						System.out.println("Ano de in�cio do an�ncio: ");
						int anoInicio = Integer.parseInt(input.nextLine());
						
						System.out.println("Dia de t�rmino do an�ncio: ");
						int diaTermino = Integer.parseInt(input.nextLine());
						
						System.out.println("M�s de t�rmino do an�ncio: ");
						int mesTermino = Integer.parseInt(input.nextLine());
						
						System.out.println("Ano de t�rmino do an�ncio: ");
						int anoTermino = Integer.parseInt(input.nextLine());
						
						System.out.println("Investimento por dia: ");
						float investimentoPorDia = Float.parseFloat(input.nextLine());
						
						Anuncio anuncio = new Anuncio(nomeDoAnuncio, clientes.get(opcaoCadastrarAnuncio-1), diaInicio,  mesInicio, anoInicio, diaTermino, mesTermino, anoTermino, investimentoPorDia);
						
						clientes.get(opcaoCadastrarAnuncio-1).getAnuncios().add(anuncio);
					}
					break;
				
				case 3:
					System.out.println("Dia de in�cio do filtro de relat�rio: ");
					int diaInicio = Integer.parseInt(input.nextLine());
					
					System.out.println("M�s de in�cio do filtro de relat�rio: ");
					int mesInicio = Integer.parseInt(input.nextLine());
					
					System.out.println("Ano de in�cio do filtro de relat�rio: ");
					int anoInicio = Integer.parseInt(input.nextLine());
					
					String dataInicioRecebida = "";
					Date dataInicio = new Date();
					
					try {
						dataInicioRecebida = diaInicio + "/" + mesInicio + "/" + anoInicio;
						SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
						dataInicio = simpleDateFormat.parse(dataInicioRecebida);
					}
					catch (Exception ex) {
						ex.printStackTrace();
					}
					
					System.out.println("Dia de t�rmino do filtro de relat�rio: ");
					int diaTermino = Integer.parseInt(input.nextLine());
					
					System.out.println("M�s de t�rmino do filtro de relat�rio: ");
					int mesTermino = Integer.parseInt(input.nextLine());
					
					System.out.println("Ano de t�rmino do filtro de relat�rio: ");
					int anoTermino = Integer.parseInt(input.nextLine());
					
					String dataTerminoRecebida = "";
					Date dataTermino = new Date();
					
					try {
						dataTerminoRecebida = diaTermino + "/" + mesTermino + "/" + anoTermino;
						SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
						dataTermino = simpleDateFormat.parse(dataTerminoRecebida);
					}
					catch (Exception ex) {
						ex.printStackTrace();
					}
					
					System.out.println("RELAT�RIOS ATIVOS ENTRE " + dataInicioRecebida + " - " + dataTerminoRecebida);
					
					for (int i = 0; i < clientes.size() ; i++ ) {
						for (int j = 0; j < clientes.get(i).getAnuncios().size() ; j++ ) {
						
							boolean iniciaDuranteOFiltro = (dataInicio.compareTo(clientes.get(i).getAnuncios().get(j).getDataInicio()) <= 0) && (dataTermino.compareTo(clientes.get(i).getAnuncios().get(j).getDataInicio()) >= 0);
							boolean terminaDuranteOFiltro = (dataInicio.compareTo(clientes.get(i).getAnuncios().get(j).getDataTermino()) <= 0) && (dataTermino.compareTo(clientes.get(i).getAnuncios().get(j).getDataTermino()) >= 0);
							boolean excedeOFiltro = (dataInicio.compareTo(clientes.get(i).getAnuncios().get(j).getDataInicio()) >= 0) && (dataTermino.compareTo(clientes.get(i).getAnuncios().get(j).getDataTermino()) <= 0);
							
							if(iniciaDuranteOFiltro || terminaDuranteOFiltro || excedeOFiltro) {
							
								System.out.println("---------------------------------------------------\n" + 
													"An�ncio " + (i+1) + "\n" +
													"Nome do an�ncio: " + clientes.get(i).getAnuncios().get(j).getNome() +"\n" +
													"Cliente " + clientes.get(i).getNome() +"\n" +
													"Data de in�cio: " + clientes.get(i).getAnuncios().get(j).getDataInicio() +"\n" +
													"Data de t�rmino: " + clientes.get(i).getAnuncios().get(j).getDataTermino() +"\n" +
													"Investimento por dia: " + clientes.get(i).getAnuncios().get(j).getInvestimentoPorDia() +"\n" +
													"Valor total investido: " + clientes.get(i).getAnuncios().get(j).getTotalInvestido() +"\n" +
													"Quantidade m�xima de visualiza��es: " + clientes.get(i).getAnuncios().get(j).getVisualizacoesMax() +"\n" +
													"Quantidade m�xima de cliques: " + clientes.get(i).getAnuncios().get(j).getCliquesMax() +"\n" +
													"Quantidade m�xima de compartilhamentos: " + clientes.get(i).getAnuncios().get(j).getCompartilhamentoMax() +"\n");
							}
						}
					}
					
					break;
				case 4:
					System.out.println("ESCOLHA UM CLIENTE PARA GERAR O RELAT�RIO DE SEUS AN�NCIOS\n");
					for (int i = 0; i < clientes.size() ; i++ ) {
						System.out.println((i+1) + " - " + clientes.get(i).getNome());
					}
					System.out.println((clientes.size()+1) + " - Voltar ao menu anterior");
					int opcaoGerarRelatorio = Integer.parseInt(input.nextLine());
					if(opcaoGerarRelatorio == (clientes.size()+1)){
						continue;						
					}
					else {
						System.out.println("RELAT�RIO DO CLIENTE: " + clientes.get(opcaoGerarRelatorio-1).getNome());
						for (int i = 0; i < clientes.get(opcaoGerarRelatorio-1).getAnuncios().size() ; i++ ) {
							System.out.println("---------------------------------------------------\n" + 
												"An�ncio " + (i+1) + "\n" +
												"Nome do an�ncio: " + clientes.get(opcaoGerarRelatorio-1).getAnuncios().get(i).getNome() +"\n" +
												"Cliente " + clientes.get(opcaoGerarRelatorio-1).getNome() +"\n" +
												"Data de in�cio: " + clientes.get(opcaoGerarRelatorio-1).getAnuncios().get(i).getDataInicio() +"\n" +
												"Data de t�rmino: " + clientes.get(opcaoGerarRelatorio-1).getAnuncios().get(i).getDataTermino() +"\n" +
												"Investimento por dia: " + clientes.get(opcaoGerarRelatorio-1).getAnuncios().get(i).getInvestimentoPorDia() +"\n" +
												"Valor total investido: " + clientes.get(opcaoGerarRelatorio-1).getAnuncios().get(i).getTotalInvestido() +"\n" +
												"Quantidade m�xima de visualiza��es: " + clientes.get(opcaoGerarRelatorio-1).getAnuncios().get(i).getVisualizacoesMax() +"\n" +
												"Quantidade m�xima de cliques: " + clientes.get(opcaoGerarRelatorio-1).getAnuncios().get(i).getCliquesMax() +"\n" +
												"Quantidade m�xima de compartilhamentos: " + clientes.get(opcaoGerarRelatorio-1).getAnuncios().get(i).getCompartilhamentoMax() +"\n");
						}
					}
					break;
			}
		}
		input.close();
	}
}