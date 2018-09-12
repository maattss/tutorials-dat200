package heap;

import java.util.ArrayList;

/*
 * Prioritetsk� basert p� en minimum bin�rhaug.
 * 
 * En minimum bin�rhaug (minimum binary heap) er et bin�rtre hvor begge barna m� v�re
 * st�rre enn forelderen.
 */
public class MaxHeap <T> {
	private class Innslag {
		int prioritet;
		T verdi;
		
		public Innslag(int prioritet, T verdi) {
			this.prioritet = prioritet;
			this.verdi = verdi;
		}
	}
	
	// En binærhaug kan lagres som en arraylist
	private ArrayList<Innslag> innslagene = new ArrayList<>();

	// Legger inn et dummy element i posisjon 0 for � f� enkel matematikk
	public MaxHeap() {
		innslagene.add(null);
	}
	
	/*
	 * Kj�retid O(n). Det kan i utgangspunktet se ut som den har n*log(n) kj�retid,
	 * men antall noder den bytter om er aldri h�yere enn n.
	 */
	public MaxHeap(ArrayList<Integer> prioriteter, ArrayList<? extends T> startliste) {
		innslagene.add(null);
		for (int i=0;i<startliste.size();i++) {
			innslagene.add(new Innslag(prioriteter.get(i), startliste.get(i)));
		}
		for (int i=innslagene.size()/2;i>0;i--) {
			bobleNed(i);
		}
	}
	
	/*
	 * Kj�retid O(1). Henter alltid ut f�rste element.
	 */
	public T findMax() {
		if (innslagene.size() < 2) return null;
		return innslagene.get(1).verdi;
	}
	
	/*
	 * Kj�retid O(log(n)) fra bobleOpp
	 */
	public void insert(int prioritet, T objekt) {
		Innslag i = new Innslag(prioritet, objekt);
		innslagene.add(i);
		bobleOpp(innslagene.size() -  1);
	}
	
	/*
	 * Kj�retid O(log(n)) siden denne g�r oppover i treet potensielt helt til rota.
	 */
	private void bobleOpp(int index) {
		if (index == 1) return;
		int forelder = index/2;
		while (index > 1 && innslagene.get(forelder).prioritet < innslagene.get(index).prioritet) {
			bytt(forelder, index);
			index = forelder;
			forelder = index/2;
		}
	}
	
	/*
	 * Kj�retid O(1)
	 */
	private void bytt(int index1, int index2) {
		Innslag temp = innslagene.get(index1);
		innslagene.set(index1, innslagene.get(index2));
		innslagene.set(index2, temp);
		
	}
	
	/*
	 * Kj�retid O(log(n)) fra bobleNed
	 */
	public T removeMax() {
		Innslag resultat = innslagene.get(1);
		if (innslagene.size() > 2) {
			innslagene.set(1, innslagene.remove(innslagene.size() - 1));
			bobleNed(1);
		} else {
			innslagene.remove(1);
		}
		return resultat.verdi;
	}
	
	/*
	 * Kj�retid O(1). Hjelpemetode for � gj�re bobleNed litt kortere gjennom � si at
	 * noder som ikke fins i treet har en veldig stor verdi.
	 */
	private int finnPrioritet(int index) {
		if (index < innslagene.size()) return innslagene.get(index).prioritet;
		return Integer.MAX_VALUE;
	}
	
	/*
	 * Kj�retid O(log(n)) siden den g�r nedover i treet potensielt helt ned til bladnodene
	 */
	private void bobleNed(int index) {
		int venstrebarn = index*2;
		int hoyrebarn = venstrebarn + 1;
		
		int venstrePrioritet = finnPrioritet(venstrebarn);
		int hoyrePrioritet = finnPrioritet(hoyrebarn);
		int maxPrioritet = innslagene.get(index).prioritet;
		
		while (!(maxPrioritet > venstrePrioritet && maxPrioritet > hoyrePrioritet)) {
			if (venstrePrioritet > hoyrePrioritet) {
				bytt(index, venstrebarn);
				index = venstrebarn;
			} else {
				bytt(index, hoyrebarn);
				index = hoyrebarn;
			}
			venstrebarn = index*2;
			hoyrebarn = venstrebarn + 1;
			venstrePrioritet = finnPrioritet(venstrebarn);
			hoyrePrioritet = finnPrioritet(hoyrebarn);
			maxPrioritet = innslagene.get(index).prioritet;
		}
	}
	
	/*
	 * Kj�retid O(log(n)) fra bobleOpp
	 */
	public void increaseKey(int index, int prioritet) {
		Innslag i = innslagene.get(index);
		if (prioritet < i.prioritet) throw new IllegalArgumentException("Prøver å senke i stedet for å øke nøkkel!");
		i.prioritet = prioritet;
		bobleOpp(index);
	}
	
	/*
	 * Hjelpemetode for debugging og testing
	 */
	public String toString() {
		StringBuilder resultat = new StringBuilder();
		resultat.append("Max heap: \n");
		Innslag innslaget;
		for (int i=1;i<innslagene.size();i++) {
			innslaget = innslagene.get(i);
			resultat.append(i + ": " + innslaget.prioritet + " - " + innslaget.verdi + "\n");
		}
		return resultat.toString();
	}
	
	public int size() {
		return innslagene.size() -1;
	}
	
	public boolean isEmpty() {
		if (innslagene.size() < 2) return true;
		return false;
	}
	
	/*
	 * Testemetode
	 */
	public static void main(String[] args) {
		MaxHeap<String> testehaug = new MaxHeap<>();
		testehaug.insert(10, "Ti");
		testehaug.insert(25, "Tjuefem");
		testehaug.insert(12, "Tolv");
		testehaug.insert(8, "�tte");
		testehaug.insert(33, "Trettitre");
		testehaug.insert(11, "Elleve");
		testehaug.insert(19, "Nitten");
		System.out.println(testehaug);
		System.out.println();
		System.out.println(testehaug.findMax());
		System.out.println(testehaug.removeMax());
		System.out.println(testehaug);
		System.out.println(testehaug.removeMax());
		testehaug.insert(15, "Femten");
		System.out.println(testehaug);
		System.out.println(testehaug.removeMax());
		System.out.println(testehaug.removeMax());
		System.out.println(testehaug.removeMax());
		System.out.println(testehaug.removeMax());
		System.out.println(testehaug);
		System.out.println(testehaug.removeMax());
		System.out.println(testehaug.removeMax());
		System.out.println(testehaug.removeMax());
	}
}
