
public class AtividadeBubbleSort {

	public static void main(String[] args) {
		int[] vetor = {5, 2, 4, 3, 0, 9, 7, 8, 1, 6};
		AtividadeBubbleSort bubbleSort = new AtividadeBubbleSort();
		bubbleSort.ordenar(vetor);
		for(int num : vetor) {
			System.out.print(num + " ");
		}
	}
	
	public void ordenar(int[] vetor) {
	    for(int i = 0; i < vetor.length - 1; i++) {
	      boolean estaOrdenado = true;
	      for(int j = 0; j < vetor.length - 1 - i; j++) {
	        if(vetor[j] > vetor[j + 1]) {
	          int aux = vetor[j];
	          vetor[j] = vetor[j + 1];
	          vetor[j + 1] = aux;
	          estaOrdenado = false;
	        }
	      }
	      // Se o vetor está ordenado então para as iterações sobre ele.
	      if(estaOrdenado)
	        break;
	    }
	}
}
