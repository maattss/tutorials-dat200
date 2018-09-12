package oppgave2;

import java.util.ArrayList;
import java.util.List;

public class QuickSort {
	
	public static <T extends Comparable<? super T>> void swap(List<T> liste, int forsteIndex, int andreIndex) {
		T temp = liste.get(forsteIndex);
		liste.set(forsteIndex, liste.get(andreIndex));
		liste.set(andreIndex, temp);
	}
	
	public static <T extends Comparable<? super T>> void skrivUtListe(List<T> liste) {
		for(T element: liste) {
			System.out.print("[" + element + "] ");
		}
		System.out.println();
	}
	
	public static <T extends Comparable<? super T>> void insertionSort(List<T> lista) {
		for (int i=1; i<lista.size(); i++) {
			int j = i;
			 while (j > 0 && lista.get(j).compareTo(lista.get(j-1)) < 0) {
	                T temp = lista.get(j);
	                lista.set(j, lista.get(j-1));
	                lista.set(j-1, temp);
	                j--;
			 }
		}
	}
	
	public static <T extends Comparable<? super T>> int splittPaaPivot(List<T> liste, int start, int slutt) {
		int startPeker = start + 1;
		int sluttPeker = slutt;
		T pivot = liste.get(start);
		//System.out.println("Pivot: " + pivot);
		
		while (startPeker < sluttPeker) {
			if (liste.get(startPeker).compareTo(pivot) <= 0 && liste.get(sluttPeker).compareTo(pivot) > 0) {
				//Pivot er mellom start- og sluttpeker, fortsetter jakten
				startPeker++;
				sluttPeker--;
				continue;
			}
			if (liste.get(startPeker).compareTo(pivot) > 0 && liste.get(sluttPeker).compareTo(pivot) <= 0) {
				//Start- og sluttpeker har blitt tilordnet motsatt. Swaper disse
				swap(liste, startPeker, sluttPeker);
				startPeker++;
				sluttPeker--;
				continue;
			}
			if (liste.get(startPeker).compareTo(pivot) > 0 && liste.get(sluttPeker).compareTo(pivot) > 0) {
				sluttPeker--;
				continue;
			}
			if (liste.get(startPeker).compareTo(pivot) <= 0 && liste.get(sluttPeker).compareTo(pivot) < 0) {
				startPeker++;
				continue;
			}
		}
		if (liste.get(startPeker).compareTo(pivot) > 0) {
			startPeker--;
		}
		swap(liste, start, startPeker);
		return startPeker;
	}
	
	public static <T extends Comparable<? super T>> void medianAvTrePivot (List<T> liste, int startIndex, int sluttIndex) {
		T forsteElement = liste.get(startIndex);
		T sisteElement = liste.get(sluttIndex);
		int midtIndex = startIndex + (sluttIndex-startIndex)/2;
		T midtElement = liste.get(midtIndex);
		
		
		if (forsteElement.compareTo(sisteElement) < 0) { //Første element < siste element
			if (forsteElement.compareTo(midtElement) < 0) { //Første element < midtelement
				if (midtElement.compareTo(sisteElement) < 0) { //Midtelement < siste element
					swap(liste, startIndex, midtIndex);
				} else { //Midtelement > siste element
					swap(liste, startIndex, sluttIndex);
				}
			} else { //Første element > midtelement
				return;
			}
		} else { //Første element > siste element
			if(forsteElement.compareTo(midtElement) < 0) { //Første element < midtelement
				return;
			} else { //Første element > midtelement
				if (sisteElement.compareTo(midtElement) < 0) { //Siste element < midtelement
					swap(liste, startIndex, midtIndex);
				} else { //Siste element > midtelement
					swap(liste, startIndex, sluttIndex);
				}
			}
			
		}
	}
	
	public static <T extends Comparable<? super T>> void quickSort(List<T> liste, int start, int slutt) {
		//skrivUtListe(liste.subList(start, slutt+1));
		if (slutt - start < 3) {
			// Kjører en vanlig Insertion Sort når lista inneholder færre enn 3 elementer
			insertionSort(liste.subList(start, slutt+1));
			return;
		}
		
		/*
		 *	Ser på første, midterste og siste element i lista og setter førsteelement 
		 *	til å være medianen av disse verdiene. Første element blir valgt som 
		 *	pivotPos i splittPaaPivot
		*/
		
		medianAvTrePivot(liste, start, slutt);
		// Sorterer lista slik at alle element mindre enn pivotverdi blir lagt foran pivotelement
		// og alle verdier høyere enn pivotverdi, blir lalgt etter.
		int pivotPos = splittPaaPivot(liste, start, slutt); 
		
		//Sorterer lista før og etter pivotPos.
		quickSort(liste, start, pivotPos - 1);
		quickSort(liste, pivotPos + 1, slutt);
		
	}
	
	public static <T extends Comparable<? super T>> void quickSort(List<T> lista) {
		quickSort(lista, 0, lista.size()-1);
	}
	
	
	
}
