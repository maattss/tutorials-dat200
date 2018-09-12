package oppgave2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Duplikatfjerning {

	/*
	 * Quicksort: O(n*log(n))
	 * Sammenligninger: O(n)
	 * Totalt: O(n*log(n))
	 */
	
	
	public static <T extends Comparable<? super T>> List<T> quicksortFjerning(List<T> lista) {
		if(lista.isEmpty()) throw new IllegalArgumentException("Lista som skal sorteres kan ikke være tom");
		
		QuickSort.quickSort(lista);
		
		T forrigeElement;
		T nvElement;
		List<T> resultat = new ArrayList<>();
		resultat.add(lista.get(0));
		for(int i = 1; i < lista.size(); i++) {
			forrigeElement = lista.get(i-1);
			nvElement = lista.get(i);
			if(!(nvElement.equals(forrigeElement))) resultat.add(nvElement);
		}
		return resultat;
	}

	/*
	 * hash.add: O(1)*n = O(n)
	 * res.add O(1)*n = O(n)
	 * Totalt: O(n)
	 */
	public static <T> List<T> hashFjerning(List<T> lista) {
		if(lista.isEmpty()) throw new IllegalArgumentException("Lista som skal sorteres kan ikke være tom");
		List<T> res = new ArrayList<>();
		HashSet<T> hash = new HashSet<>();
		for(T element: lista) hash.add(element);
		for(T element: hash) res.add(element);
		return res;
	}
	
	public static void main(String[] args) {
		List<Integer> liste1 = new ArrayList<>();
		for(int i = 0; i < 100; i++) {
			if(i%2 == 0) {
				liste1.add(i);
				System.out.print(i + " ");
			} else {
				liste1.add(i-1);
				System.out.print(i-1 + " ");
			}
		}
		
		/*
		liste1 = hashFjerning(liste1);
		System.out.println();	
		for(Integer i: liste1) System.out.print(i + " ");
		*/
		
		System.out.println();
		List<String> liste2 = new ArrayList<>();
		for(int i = 0; i < 100; i++) {
			if(i%2 == 0) {
				liste2.add("s" + i);
				System.out.print(liste2.get(i) + " ");
			} else {
				liste2.add("s" + (i-1));
				System.out.print(liste2.get(i-1) + " ");
			}
		}
		
		
		
	}
}
