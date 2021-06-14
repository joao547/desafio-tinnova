
public class AtividadeVotos {

	public static void main(String[] args) {
		AtividadeVotos atividadeVotos = new AtividadeVotos();
		Double resultValidos = atividadeVotos.validosToTotal(800, 1000);
		Double resultBrancos = atividadeVotos.brancosToTotal(150, 1000);
		Double resultNulos = atividadeVotos.nulosToTotal(50, 1000);
		System.out.println("Porcentagem de validos: " + resultValidos + " %");
		System.out.println("Porcentagem de brancos: " + resultBrancos + " %");
		System.out.println("Porcentagem de nulos: " + resultNulos + " %");
	}
	
	public double validosToTotal(double validos, double total) {
		return (validos / total) * 100 ;
	}
	
	public double brancosToTotal(double brancos, double total) {
		return (brancos / total) * 100 ;
	}
	
	public double nulosToTotal(double nulos, double total) {
		return (nulos / total) * 100 ;
	}
}
