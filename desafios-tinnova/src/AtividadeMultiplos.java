
public class AtividadeMultiplos {

	public static void main(String[] args) {
		AtividadeMultiplos multiplos = new AtividadeMultiplos();
		int result = multiplos.calcularMultiplos(10);
		System.out.println("O resultado da soma dos multiplos: " + result);
	}
	
	public int calcularMultiplos(int x) {
		int result = 0;
		for (int i = 0; i < x; i++) {
			if(i % 3 == 0 || i % 5 == 0)
				result += i;
		}
		return result;
	}

}
