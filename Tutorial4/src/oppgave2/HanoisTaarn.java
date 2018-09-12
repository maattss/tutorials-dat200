package oppgave2;

public class HanoisTaarn {
	
	//Step 1: Flytter n-1 øverste diskene til pinne C
	//Step 2: Flytter nederste disk n til pinne B
	//Step 3: Flytter n-1 diskene fra B
	
	private static int antallflytt = 0;
	
	
	public static void hanoisTaarn(int n, String start, String slutt, String mellomlagring) {
		if(n <= 0) System.err.println("Skive < 0");
		if(n == 1) {
			System.out.println("Flytter skive " + n + " fra " + start + " til " + slutt);
			antallflytt++;
		} else {
			hanoisTaarn(n - 1, start, mellomlagring, slutt);
			System.out.println("Flytter skive " + n + " fra " + start + " til " + slutt);
			antallflytt++;
			hanoisTaarn(n - 1, mellomlagring, slutt, start);
			
		}
		
	}
	
	public static void main(String[] args) {
		hanoisTaarn(5, "A", "B","C");
		System.out.println("Totalt antall flytt: " + antallflytt);
	}

}
