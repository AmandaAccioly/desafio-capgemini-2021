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
			System.out.println("OPÇÕES\n" +
					"1 - Cadastrar cliente\n" + 
					"2 - Cadastrar anúncio\n" +
					"3 - Ver relatórios por intervalo de tempo\n" + 
					"4 - Ver relatórios por cliente\n" +
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
					System.out.println("ESCOLHA UM CLIENTE PARA CADASTRAR O ANÚNCIO\n");
					for (int i = 0; i < clientes.size() ; i++ ) {
						System.out.println((i+1) + " - " + clientes.get(i).getNome());
					}
					System.out.println((clientes.size()+1) + " - Voltar ao menu anterior e cadastrar novo cliente");
					int opcaoCadastrarAnuncio = Integer.parseInt(input.nextLine());
					if(opcaoCadastrarAnuncio == (clientes.size()+1)){
						continue;						
					}
					else {
						System.out.println("Nome do anúncio: ");
						String nomeDoAnuncio = input.nextLine();
						
						System.out.println("Dia de início do anúncio: ");
						int diaInicio = Integer.parseInt(input.nextLine());
						
						System.out.println("Mês de início do anúncio: ");
						int mesInicio = Integer.parseInt(input.nextLine());
						
						System.out.println("Ano de início do anúncio: ");
						int anoInicio = Integer.parseInt(input.nextLine());
						
						System.out.println("Dia de término do anúncio: ");
						int diaTermino = Integer.parseInt(input.nextLine());
						
						System.out.println("Mês de término do anúncio: ");
						int mesTermino = Integer.parseInt(input.nextLine());
						
						System.out.println("Ano de término do anúncio: ");
						int anoTermino = Integer.parseInt(input.nextLine());
						
						System.out.println("Investimento por dia: ");
						float investimentoPorDia = Float.parseFloat(input.nextLine());
						
						Anuncio anuncio = new Anuncio(nomeDoAnuncio, clientes.get(opcaoCadastrarAnuncio-1), diaInicio,  mesInicio, anoInicio, diaTermino, mesTermino, anoTermino, investimentoPorDia);
						
						clientes.get(opcaoCadastrarAnuncio-1).getAnuncios().add(anuncio);
					}
					break;
				
				case 3:
					System.out.println("Dia de início do filtro de relatório: ");
					int diaInicio = Integer.parseInt(input.nextLine());
					
					System.out.println("Mês de início do filtro de relatório: ");
					int mesInicio = Integer.parseInt(input.nextLine());
					
					System.out.println("Ano de início do filtro de relatório: ");
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
					
					System.out.println("Dia de término do filtro de relatório: ");
					int diaTermino = Integer.parseInt(input.nextLine());
					
					System.out.println("Mês de término do filtro de relatório: ");
					int mesTermino = Integer.parseInt(input.nextLine());
					
					System.out.println("Ano de término do filtro de relatório: ");
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
					
					System.out.println("RELATÓRIOS ATIVOS ENTRE " + dataInicioRecebida + " - " + dataTerminoRecebida);
					
					for (int i = 0; i < clientes.size() ; i++ ) {
						for (int j = 0; j < clientes.get(i).getAnuncios().size() ; j++ ) {
						
							boolean iniciaDuranteOFiltro = (dataInicio.compareTo(clientes.get(i).getAnuncios().get(j).getDataInicio()) <= 0) && (dataTermino.compareTo(clientes.get(i).getAnuncios().get(j).getDataInicio()) >= 0);
							boolean terminaDuranteOFiltro = (dataInicio.compareTo(clientes.get(i).getAnuncios().get(j).getDataTermino()) <= 0) && (dataTermino.compareTo(clientes.get(i).getAnuncios().get(j).getDataTermino()) >= 0);
							boolean excedeOFiltro = (dataInicio.compareTo(clientes.get(i).getAnuncios().get(j).getDataInicio()) >= 0) && (dataTermino.compareTo(clientes.get(i).getAnuncios().get(j).getDataTermino()) <= 0);
							
							if(iniciaDuranteOFiltro || terminaDuranteOFiltro || excedeOFiltro) {
							
								System.out.println("---------------------------------------------------\n" + 
													"Anúncio " + (i+1) + "\n" +
													"Nome do anúncio: " + clientes.get(i).getAnuncios().get(j).getNome() +"\n" +
													"Cliente " + clientes.get(i).getNome() +"\n" +
													"Data de início: " + clientes.get(i).getAnuncios().get(j).getDataInicio() +"\n" +
													"Data de término: " + clientes.get(i).getAnuncios().get(j).getDataTermino() +"\n" +
													"Investimento por dia: " + clientes.get(i).getAnuncios().get(j).getInvestimentoPorDia() +"\n" +
													"Valor total investido: " + clientes.get(i).getAnuncios().get(j).getTotalInvestido() +"\n" +
													"Quantidade máxima de visualizações: " + clientes.get(i).getAnuncios().get(j).getVisualizacoesMax() +"\n" +
													"Quantidade máxima de cliques: " + clientes.get(i).getAnuncios().get(j).getCliquesMax() +"\n" +
													"Quantidade máxima de compartilhamentos: " + clientes.get(i).getAnuncios().get(j).getCompartilhamentoMax() +"\n");
							}
						}
					}
					
					break;
				case 4:
					System.out.println("ESCOLHA UM CLIENTE PARA GERAR O RELATÓRIO DE SEUS ANÚNCIOS\n");
					for (int i = 0; i < clientes.size() ; i++ ) {
						System.out.println((i+1) + " - " + clientes.get(i).getNome());
					}
					System.out.println((clientes.size()+1) + " - Voltar ao menu anterior");
					int opcaoGerarRelatorio = Integer.parseInt(input.nextLine());
					if(opcaoGerarRelatorio == (clientes.size()+1)){
						continue;						
					}
					else {
						System.out.println("RELATÓRIO DO CLIENTE: " + clientes.get(opcaoGerarRelatorio-1).getNome());
						for (int i = 0; i < clientes.get(opcaoGerarRelatorio-1).getAnuncios().size() ; i++ ) {
							System.out.println("---------------------------------------------------\n" + 
												"Anúncio " + (i+1) + "\n" +
												"Nome do anúncio: " + clientes.get(opcaoGerarRelatorio-1).getAnuncios().get(i).getNome() +"\n" +
												"Cliente " + clientes.get(opcaoGerarRelatorio-1).getNome() +"\n" +
												"Data de início: " + clientes.get(opcaoGerarRelatorio-1).getAnuncios().get(i).getDataInicio() +"\n" +
												"Data de término: " + clientes.get(opcaoGerarRelatorio-1).getAnuncios().get(i).getDataTermino() +"\n" +
												"Investimento por dia: " + clientes.get(opcaoGerarRelatorio-1).getAnuncios().get(i).getInvestimentoPorDia() +"\n" +
												"Valor total investido: " + clientes.get(opcaoGerarRelatorio-1).getAnuncios().get(i).getTotalInvestido() +"\n" +
												"Quantidade máxima de visualizações: " + clientes.get(opcaoGerarRelatorio-1).getAnuncios().get(i).getVisualizacoesMax() +"\n" +
												"Quantidade máxima de cliques: " + clientes.get(opcaoGerarRelatorio-1).getAnuncios().get(i).getCliquesMax() +"\n" +
												"Quantidade máxima de compartilhamentos: " + clientes.get(opcaoGerarRelatorio-1).getAnuncios().get(i).getCompartilhamentoMax() +"\n");
						}
					}
					break;
			}
		}
		input.close();
	}
}