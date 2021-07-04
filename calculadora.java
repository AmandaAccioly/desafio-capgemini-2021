import java.util.Scanner;

public class calculadora {
	
	public static void main(String[]args) {
		
		Scanner input = new Scanner(System.in);
		
		float valorInvestido = Float.parseFloat(input.nextLine());
		
		int pessoasQueVisualizam = (int) (valorInvestido * 30);
		
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
		
		System.out.println(totalVisualizacoes);
		
		input.close();
	}
}
	
	
