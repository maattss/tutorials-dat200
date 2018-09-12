package oppgave1;

public class Rekursiv {
	
	//Finn det n-te elementet A(n) i formelen A(n) = A(n-1) + 1/n, A(1) = 1. Metoden skal ta n som parameter.
	public static double nElement(int n) {
		if(n == 1) return 1;
		return nElement(n - 1) + (double)1/n;
	}
	
	public static void main(String [] args) {
		System.out.printf("n = 5: %.2f\n", nElement(5));
		System.out.printf("n = 9: %.2f\n", nElement(9));
		System.out.printf("n = 17: %.2f\n", nElement(17));
		System.out.printf("n = 324: %.2f\n", nElement(324));
	}
}
