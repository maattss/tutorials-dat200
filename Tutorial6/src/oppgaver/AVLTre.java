package oppgaver;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.Stack;

import personer.Emne;
import personer.Student;
import util.Pair;

/*
 * Bin�rtre som holder seg balansert. Fungerer korrekt for put, men har ikke
 * implementert rotasjonssjekker for remove. Rotasjon for remove er oppgave 2g) i
 * �ving 6.
 * 
 */
public class AVLTre <K extends Comparable<? super K>, V> implements SortedMap<K, V> {
	private class Binaertrenode {
		K nokkel;
		V verdi;
		int hoyde;			// Trenger dette for å holde treet balansert
		Binaertrenode venstrebarn;
		Binaertrenode hoyrebarn;
		Binaertrenode forelder;
		
		public Binaertrenode(K key, V value) {
			nokkel = key;
			verdi = value;
			hoyde = 1;
			venstrebarn = null;
			hoyrebarn = null;
			forelder = null;
		}
	}
	
	private Binaertrenode rot = null;
	
	/*
	 * Kj�retid O(1) for selve programmet, O(n) for s�ppelt�mmeren.
	 */
	@Override
	public void clear() {
		rot = null;
	}

	/*
	 * Kj�retid som get(key), O(log(n))
	 */
	@Override
	public boolean containsKey(Object key) {
		if (get(key) == null) return false;
		return true;
	}

	/*
	 * M� traversere treet for � sjekke om verdien ligger i det. O(n) kj�retid.
	 */
	@Override
	public boolean containsValue(Object value) {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * Kj�retid O(log(n))
	 */
	@Override
	public V get(Object key) {
		if (rot == null) return null;
		K nokkel = (K)key;
		return get(nokkel, rot);
	}
	
	private V get(K key, Binaertrenode noden) {
		if (noden.nokkel.compareTo(key) > 0) {
			if (noden.venstrebarn != null) {
				return get(key, noden.venstrebarn);
			} else {
				return null;
			}
		} else if (noden.nokkel.compareTo(key) < 0) {
			if (noden.hoyrebarn != null) {
				return get(key, noden.hoyrebarn);
			} else {
				return null;
			}
		} else {
			return noden.verdi;
		}

	}
	
	/*
	 * Kj�retid O(log(n))
	 */
	public K getNextKey(K key) {
		if (rot == null) return null;
		return getNextKey(key, rot);
	}
	
	private K getNextKey(K key, Binaertrenode noden) {
		if (noden.nokkel.compareTo(key) > 0) {
			K kandidat = getNextKey(key, noden.venstrebarn);
			if (kandidat.compareTo(noden.nokkel) < 0) {
				return kandidat;
			} else return noden.nokkel;
		} else {
			if (noden.hoyrebarn != null) {
				return getNextKey(key, noden.hoyrebarn);
			} else return null;
		}
	}

	/*
	 * Kj�retid O(1)
	 */
	@Override
	public boolean isEmpty() {
		if (rot == null) return true;
		return false;
	}

	/*
	 * Kj�retid O(log(n))
	 * 
	 */
	@Override
	public V put(K key, V value) {
		System.out.println("Setter inn nøkkel: " + key);
		if (key == null) throw new IllegalArgumentException("Dette søketreet tillater ikke null nøkler");
		if (value == null) throw new IllegalArgumentException("Dette søketreet tillater ikke null verdier");
		if (rot == null) {
			rot = new Binaertrenode(key, value);
		} else {
			return put(key, value, rot);
		}
		return null;
	}
	
	private V put(K key, V value, Binaertrenode noden) {
		V verdi;
		if (noden.nokkel.compareTo(key) > 0) {
			if (noden.venstrebarn != null) {
				verdi = put(key, value, noden.venstrebarn);
			} else {
				noden.venstrebarn = new Binaertrenode(key, value);
				noden.venstrebarn.forelder = noden;
				verdi =  null;
			}
		} else if (noden.nokkel.compareTo(key) < 0) {
			if (noden.hoyrebarn != null) {
				verdi = put(key, value, noden.hoyrebarn);
			} else {
				noden.hoyrebarn = new Binaertrenode(key, value);
				noden.hoyrebarn.forelder = noden;
				verdi =  null;
			}
		} else {
			verdi = noden.verdi;
			noden.verdi = value;
		}
		sjekkNodeHoyde(noden);
		return verdi;
	}
	
	// Hjelpemetode for � redusere mengden kode for rotasjonene.
	private int hoyde(Binaertrenode noden) {
		if (noden == null) return 0;
		return noden.hoyde;
	}
	
	// Regner ut ny h�yde for noden og sjekker om den m� roteres
	private void sjekkNodeHoyde(Binaertrenode noden) {
		int venstrehoyde = hoyde(noden.venstrebarn);
		int hoyrehoyde = hoyde(noden.hoyrebarn);
		if (venstrehoyde > (hoyrehoyde + 1)) {
			if (hoyde(noden.venstrebarn.venstrebarn) > hoyde(noden.venstrebarn.hoyrebarn)) {
				roterMedVenstreBarn(noden);
			} else {
				dobbeltVenstre(noden);
			}
		}
		if (hoyrehoyde > (venstrehoyde + 1)) {
			if (hoyde(noden.hoyrebarn.hoyrebarn) > hoyde(noden.hoyrebarn.venstrebarn)) {
				roterMedHoyreBarn(noden);
			} else {
				dobbeltHoyre(noden);
			}
		}
		noden.hoyde = Math.max(venstrehoyde, hoyrehoyde) + 1;		
	}
	
	// Regner ut h�yde til noden uten � sjekke for rotasjoner. Brukes fra rotasjonsmetodene
	private void sjekkNodeHoydeUtenRotasjon(Binaertrenode noden) {
		int venstrehoyde = hoyde(noden.venstrebarn);
		int hoyrehoyde = hoyde(noden.hoyrebarn);
		noden.hoyde = Math.max(venstrehoyde, hoyrehoyde) + 1;		
	}
	
	private void roterMedVenstreBarn(Binaertrenode noden) {
		Binaertrenode venstrebarn = noden.venstrebarn;
		Binaertrenode forelder = noden.forelder;
		System.out.println("Roterer " + noden.nokkel + " med " + venstrebarn.nokkel);
		if (forelder != null) {
			if (forelder.venstrebarn == noden) {
				forelder.venstrebarn = venstrebarn;
			} else forelder.hoyrebarn = venstrebarn;
		} else {
			rot = venstrebarn;
		}
		Binaertrenode subtre2 = venstrebarn.hoyrebarn;
		venstrebarn.hoyrebarn = noden;
		noden.venstrebarn = subtre2;
		noden.forelder = venstrebarn;
		venstrebarn.forelder = forelder;
		sjekkNodeHoydeUtenRotasjon(noden);
		sjekkNodeHoydeUtenRotasjon(venstrebarn);
	}

	private void roterMedHoyreBarn(Binaertrenode noden) {
		Binaertrenode hoyrebarn = noden.hoyrebarn;
		Binaertrenode forelder = noden.forelder;
		System.out.println("Roterer " + noden.nokkel + " med " + hoyrebarn.nokkel);
		if (forelder != null) {
			if (forelder.venstrebarn == noden) {
				forelder.venstrebarn = hoyrebarn;
			} else forelder.hoyrebarn = hoyrebarn;
		} else {
			rot = hoyrebarn;
		}
		Binaertrenode subtre2 = hoyrebarn.venstrebarn;
		hoyrebarn.venstrebarn = noden;
		noden.hoyrebarn = subtre2;
		noden.forelder = hoyrebarn;
		hoyrebarn.forelder = forelder;
		sjekkNodeHoydeUtenRotasjon(noden);
		sjekkNodeHoydeUtenRotasjon(hoyrebarn);
	}

	private void dobbeltVenstre(Binaertrenode noden) {
		roterMedHoyreBarn(noden.venstrebarn);
		roterMedVenstreBarn(noden);
	}

	private void dobbeltHoyre(Binaertrenode noden) {
		roterMedVenstreBarn(noden.hoyrebarn);
		roterMedHoyreBarn(noden);
	}

	@Override
	public void putAll(Map<? extends K, ? extends V> m) {
		// TODO Auto-generated method stub
		
	}

	/*
	 * Kj�retid O(log(n))
	 */
	@Override
	public V remove(Object key) {
		if (rot == null) return null;
		K nokkel = (K)key;
		return remove(nokkel, rot);
	}
	
	private V remove(K key, Binaertrenode noden) {
		if (noden.nokkel.compareTo(key) > 0) {
			if (noden.venstrebarn != null) {
				return remove(key, noden.venstrebarn);
			} else {
				return null;
			}
		} else if (noden.nokkel.compareTo(key) < 0) {
			if (noden.hoyrebarn != null) {
				return remove(key, noden.hoyrebarn);
			} else {
				return null;
			}
		} else {
			V fjernetVerdi = null;
			// Tilfelle 1, noden er en bladnode
			if (noden.venstrebarn == null && noden.hoyrebarn == null) {
				if (noden.forelder.venstrebarn == noden) {
					noden.forelder.venstrebarn = null;
				} else {
					noden.forelder.hoyrebarn = null;
				}
				justerTrehoyde(noden.forelder);
			}
			// Tilfelle 2, noden har ett barn
			if (noden.venstrebarn != null && noden.hoyrebarn == null) {
				if (noden.forelder.venstrebarn == noden) {
					noden.forelder.venstrebarn = noden.venstrebarn;
				} else {
					noden.forelder.hoyrebarn = noden.venstrebarn;
				}				
				justerTrehoyde(noden.forelder);
			}
			if (noden.venstrebarn == null && noden.hoyrebarn != null) {
				if (noden.forelder.venstrebarn == noden) {
					noden.forelder.venstrebarn = noden.hoyrebarn;
				} else {
					noden.forelder.hoyrebarn = noden.hoyrebarn;
				}				
				justerTrehoyde(noden.forelder);
			}
			if (noden.venstrebarn != null && noden.hoyrebarn != null) {
				// Tilfelle 3, noden har to barn
				Binaertrenode minsteIHoyreSubtre = finnMinste(noden.hoyrebarn);
				fjernetVerdi = noden.verdi;
				noden.verdi = minsteIHoyreSubtre.verdi;
				remove(minsteIHoyreSubtre.nokkel, minsteIHoyreSubtre);
				justerTrehoyde(minsteIHoyreSubtre.forelder);
			}
			return fjernetVerdi;
		}
	}
	
	// Justerer treh�yde etter en remove, g�r automatisk oppover i treet og
	// oppdaterer h�ydene ved behov.
	private void justerTrehoyde(Binaertrenode noden) {
		while (noden != null) {
			int venstrehoyde = hoyde(noden.venstrebarn);
			int hoyrehoyde = hoyde(noden.hoyrebarn);
			noden.hoyde = Math.max(venstrehoyde, hoyrehoyde) + 1;
			noden = noden.forelder;
		}
	}

	/*
	 * Kj�retid O(log(n))
	 */
	public V finnMinste() {
		if (rot == null) return null;
		return finnMinste(rot).verdi;
	}
	
	private Binaertrenode finnMinste(Binaertrenode noden) {
		if (noden.venstrebarn == null) {
			return noden;
		} else return finnMinste(noden.venstrebarn);
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Comparator<? super K> comparator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Entry<K, V>> entrySet() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public K firstKey() {
		if (rot == null) return null;
		return finnMinste(rot).nokkel;
	}

	@Override
	public SortedMap<K, V> headMap(K toKey) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<K> keySet() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public K lastKey() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SortedMap<K, V> subMap(K fromKey, K toKey) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SortedMap<K, V> tailMap(K fromKey) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<V> values() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/*
	 * Metode for � skrive ut et tre. Brukes for � teste trestrukturen.
	 */
	public void skrivUtTre() {
		skrivUtTre(rot);
	}
	
	private void skrivUtTre(Binaertrenode noden) {
		if (noden == null) return;
		System.out.print("Nøkkel: " + noden.nokkel + " verdi: " + noden.verdi + " ");
		if (noden.venstrebarn != null) {
			System.out.print("Venstre: " + noden.venstrebarn.nokkel + " ");
		}
		if (noden.hoyrebarn != null) {
			System.out.print("Høyre: " + noden.hoyrebarn.nokkel + " ");			
		}
		System.out.println();
		skrivUtTre(noden.venstrebarn);
		skrivUtTre(noden.hoyrebarn);
	}

	/*
	 * Iterator laget for testing. Returnerer et egendefinert Pair objekt heller enn
	 * den indre klassen i Map som returneres av entrySet metoden. Dette er en
	 * InOrder iterator.
	 */
	public Iterator<Pair<K, V>> iterator() {
		return new TreeIterator();
	}

	private class TreeIterator implements Iterator<Pair<K, V>> {

		private class StabelElement {
			Binaertrenode noden;
			int teller;
			
			public StabelElement(Binaertrenode noden, int teller) {
				this.noden = noden;
				this.teller = teller;
			}
		}
		
		private Stack<StabelElement> stabelen;
		
		public TreeIterator() {
			stabelen = new Stack<>();
			stabelen.push(new StabelElement(rot, 0));
		}
		
		@Override
		public boolean hasNext() {
			if (stabelen.isEmpty()) return false;
			return true;
		}

		@Override
		public Pair<K, V> next() {
			while (!stabelen.isEmpty()) {
				StabelElement elementet = stabelen.pop();
				if (elementet.teller == 0) {
					elementet.teller++;
					stabelen.push(elementet);
					if (elementet.noden.venstrebarn != null) {
						stabelen.push(new StabelElement(elementet.noden.venstrebarn, 0));
					}
					continue;
				}
				if (elementet.teller == 1) {
					elementet.teller++;
					if (!(elementet.noden.hoyrebarn == null)) stabelen.push(new StabelElement(elementet.noden.hoyrebarn, 0));
					return new Pair<>(elementet.noden.nokkel, elementet.noden.verdi);					
				}
			}
			return null;
		}
		
	}

	public static void main(String[] args) {
		//Key: Etternavn, String
		//Value: Studentobjekt
		AVLTre<String, Student> studentliste = new AVLTre<>();
		System.out.println("Oppsett av treet");		
		Emne DAT100 = new Emne("DAT100", "Objektorientert programmering", 'V');
		Emne DAT200 = new Emne("DAT200", "Algoritmer og datastrukturer", 'H');
				
		Student student1 = new Student(1, "Fornavn1", "Etternavn1", 1991);
		student1.meldOppStudet(DAT100);
		student1.meldOppStudet(DAT200);
		student1.setAarskurs(2);
		student1.setStudieprogram("Data");
			
		Student student2 = new Student(2, "Fornavn2", "Etternavn2", 1992);	
		student2.meldOppStudet(DAT100);
		student2.setAarskurs(1);
		student2.setStudieprogram("Elektro");
				
		studentliste.put("Etternavn1", student1);
		studentliste.put("Etternavn2",student2);
				
		studentliste.put("Etternavn3", new Student(3, "Fornavn3", "Etternavn3", 1993));
		studentliste.put("Etternavn4", new Student(4, "Fornavn4", "Etternavn4", 1994));
		studentliste.put("Etternavn5", new Student(5, "Fornavn5", "Etternavn5", 1994));
						
		System.out.println(student1 + ".\n	Oppmeldt i: " + student1.getOppmeldtI());
		System.out.println(student2 + ".\n	Oppmeldt i: " + student2.getOppmeldtI());
		
		System.out.println("\nUtskrift av hele studentliste-treet: ");
		studentliste.skrivUtTre();
	
	}
}
