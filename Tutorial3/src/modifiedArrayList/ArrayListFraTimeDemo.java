package modifiedArrayList;

import java.util.Iterator;

/*
 * Demonstrasjon av bruk av ArryaList implementasjonen
 */
public class ArrayListFraTimeDemo {

	public static void main(String[] args) {
		// Lager en MinArrayList med typeparameter String
		ArrayListFraTime<String> strengliste = new ArrayListFraTime<>();
		strengliste.add("En");
		strengliste.add("tre");
		strengliste.add("fire");
		strengliste.add("fem");
		strengliste.add("ergg");
		strengliste.add("tradgade");
		strengliste.add("fiadgre");
		strengliste.add("En");
		strengliste.add("tre");
		strengliste.add("fire");
		strengliste.add("fem");
		strengliste.add("ergg");
		strengliste.add("tradgade");
		strengliste.add("fiadgre");
		strengliste.remove(3);
		
		/* List<T> grensesnittet utvider Iterable<T> grensesnittet.
		 * det formelle kravet for � bruke en for each l�kke er at man
		 * utvider Iterable<T>. Denne fungerer s� lenge iteratoren
		 * er korrekt laget.
		 */
		for (String streng: strengliste) {
			System.out.println(streng);
		}
		
		// Ekvevalent formulering av for each l�kka som viser hvordan
		// iteratoren faktisk brukes av for each l�kka.
		String streng;
		Iterator<String> strengiterator = strengliste.iterator();
		while (strengiterator.hasNext()) {
			streng = strengiterator.next();
			System.out.println(streng);			
		}
	}

}
