package oppgave1;

public class Iterativ {

	//Formel: A(n) = A(n-1) + 1/n, A(1)=1.
	//Kjøretid: O(n) pga for-loop
	public static double nElement(int n) {
		double resultat = 1;
		for(int i = 2; i <=n; i++) {
			resultat += 1/(double)i;
		}
		return resultat;
	}
	
	public static void main(String[] args) {
		System.out.printf("n = 5: %.2f\n", nElement(5));
		System.out.printf("n = 9: %.2f\n", nElement(9));
		System.out.printf("n = 17: %.2f\n", nElement(17));
		System.out.printf("n = 324: %.2f\n", nElement(324));
	}

}
