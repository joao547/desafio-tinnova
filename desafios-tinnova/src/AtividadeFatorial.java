
public class AtividadeFatorial {

	public static void main(String[] args) {
		AtividadeFatorial fatorial = new AtividadeFatorial();
		int result = fatorial.fatorial(6);
	    System.out.println(result);
	}
	
	public int fatorial(int x) {
	    if (x == 0)
	      return 1;
	    return x * fatorial(x - 1);
	}

}
