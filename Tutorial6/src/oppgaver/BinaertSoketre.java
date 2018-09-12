package oppgaver;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.Stack;

import util.Pair;


public class BinaertSoketre <K extends Comparable<? super K>, V> implements SortedMap<K, V> {
	private class Binaertrenode {
		K nokkel;
		V verdi;
		Binaertrenode venstrebarn;
		Binaertrenode hoyrebarn;
		Binaertrenode forelder;
		
		public Binaertrenode(K key, V value) {
			nokkel = key;
			verdi = value;
			venstrebarn = null;
			hoyrebarn = null;
			forelder = null;
		}
		
		public boolean bladNode() {
			if (venstrebarn == null && hoyrebarn == null) return true;
			return false;
		}
	}
	
	private Binaertrenode rot = null;
	
	private int antallBladnoder;
	private int nivaaTeller;
			
	public int getAntallBladnoder() {
		return antallBladnoder;
	}

	public int antallBladnoder() {
		antallBladnoder = 0;
		antallBladnoder(rot);
		return antallBladnoder;
	}
	
	public void antallBladnoder(Binaertrenode noden) {
		if(noden.venstrebarn != null) antallBladnoder(noden.venstrebarn);
		if(noden.hoyrebarn != null) antallBladnoder(noden.hoyrebarn);
		if(noden.hoyrebarn != null && noden.venstrebarn != null) antallBladnoder++;
	}
	
	public void skrivUtNoderPaaNivaa(int nivaa) {
		nivaaTeller = 0;
		System.out.print("Verdi til noder på nivå " + nivaa + ": ");
		skrivUt(nivaa, rot);
	}
	
	public void skrivUt(int maalnivaa, Binaertrenode noden) {
		if(nivaaTeller == maalnivaa) {
			System.out.print(noden.verdi + " ");
			nivaaTeller--;
			return;
		}
		if(nivaaTeller != maalnivaa) {
			if (noden.venstrebarn != null) {
				nivaaTeller++;
				skrivUt(maalnivaa, noden.venstrebarn);
			}
			if (noden.hoyrebarn != null) {
				nivaaTeller++;
				skrivUt(maalnivaa, noden.hoyrebarn);
			}
		}
		nivaaTeller--;
	}
	
	@Override
	public void clear() {
		rot = null;
	}

	@Override
	public boolean containsKey(Object key) {
		if (get(key) == null) return false;
		return true;
	}

	/*
	 * M� traversere treet til man finner det. O(n). Ikke implementert!
	 */
	@Override
	public boolean containsValue(Object value) {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * Kj�retid O(h�yden til treet)
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

	@Override
	public boolean isEmpty() {
		if (rot == null) return true;
		return false;
	}

	/*
	 * Bin�re s�ketr�r: Venstre barn inneholder alle n�kler lavere enn noden, h�yre
	 * barn inneholder alle n�kler h�yere enn noden. Tillater ikke duplikater.
	 * 
	 * Kj�retid O(h�yden til treet)
	 * 
	 */
	@Override
	public V put(K key, V value) {
		if (key == null) throw new IllegalArgumentException("Dette s�ketreet tillater ikke null n�kler");
		if (value == null) throw new IllegalArgumentException("Dette s�ketreet tillater ikke null verdier");
		if (rot == null) {
			rot = new Binaertrenode(key, value);
		} else {
			return put(key, value, rot);
		}
		return null;
	}
	
	// Rekursiv metode som finner riktig plass i treet.
	private V put(K key, V value, Binaertrenode noden) {
		if (noden.nokkel.compareTo(key) > 0) {
			if (noden.venstrebarn != null) {
				return put(key, value, noden.venstrebarn);
			} else {
				noden.venstrebarn = new Binaertrenode(key, value);
				noden.venstrebarn.forelder = noden;
				return null;
			}
		} else if (noden.nokkel.compareTo(key) < 0) {
			if (noden.hoyrebarn != null) {
				return put(key, value, noden.hoyrebarn);
			} else {
				noden.hoyrebarn = new Binaertrenode(key, value);
				noden.hoyrebarn.forelder = noden;
				return null;
			}
		} else {
			V verdi = noden.verdi;
			noden.verdi = value;
			return verdi;
		}
	}

	@Override
	public void putAll(Map<? extends K, ? extends V> m) {
		// TODO Auto-generated method stub
		
	}

	/*
	 * Fjerner en node. O(h�yde) kj�retid for � finne det man skal fjerne og s�
	 * O(1) tid for � fjerne det.
	 */
	@Override
	public V remove(Object key) {
		if (rot == null) return null;
		K nokkel = (K)key;
		return remove(nokkel, rot);
	}
	
	/*
	 * Rekursiv metode som fjerner et element. Den g�r nedover i treet til den finner
	 * noden som skal fjernes. N�r noden skal fjernes er det tre alternativer:
	 * - Noden er en bladnode og kan fjernes uten problemer
	 * - Noden har kun ett barn. Forelder kan referere direkte til dette barnet.
	 * - Noden har to barn. Erstatter noden med laveste node i h�yre subtre og sletter
	 *   denne i stedet. Laveste node i et subtre har ingen venstrebarn.
	 */
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
			V fjernetVerdi = noden.verdi;
			// Tilfelle 1, noden er en bladnode
			if (noden.bladNode()) {
				if (noden.forelder.venstrebarn == noden) {
					noden.forelder.venstrebarn = null;
				} else {
					noden.forelder.hoyrebarn = null;
				}
			}
			// Tilfelle 2, noden har ett barn
			if (noden.venstrebarn != null && noden.hoyrebarn == null) {
				if (noden.forelder.venstrebarn == noden) {
					noden.forelder.venstrebarn = noden.venstrebarn;
				} else {
					noden.forelder.hoyrebarn = noden.venstrebarn;
				}				
			}
			if (noden.venstrebarn == null && noden.hoyrebarn != null) {
				if (noden.forelder.venstrebarn == noden) {
					noden.forelder.venstrebarn = noden.hoyrebarn;
				} else {
					noden.forelder.hoyrebarn = noden.hoyrebarn;
				}				
			}
			// Tilfelle 3, noden har to barn
			if (noden.venstrebarn != null && noden.hoyrebarn != null) {
				Binaertrenode minsteIHoyreSubtre = finnMinste(noden.hoyrebarn);
				noden.nokkel = minsteIHoyreSubtre.nokkel;
				noden.verdi = minsteIHoyreSubtre.verdi;
				remove(minsteIHoyreSubtre.nokkel, minsteIHoyreSubtre);
			}
			return fjernetVerdi;
		}
	}

	/*
	 * Typisk O(h�yden til treet)
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
		System.out.print("Nokkel: " + noden.nokkel + " verdi: " + noden.verdi + " ");
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
	 * den indre klassen i Map som returneres av entrySet metoden.
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
		BinaertSoketre<Integer, Integer> testliste = new BinaertSoketre<>();
		
		//Nivå 0 (Rot)
		testliste.put(10, 10);
		//Nivå 1
		testliste.put(5, 5);
		testliste.put(15, 15);
		
		//Nivå 2
		testliste.put(3, 3);
		testliste.put(7, 7);
		testliste.put(13, 13);
		testliste.put(19, 19);
		
		//Nivå 3
		testliste.put(11, 11);
		testliste.put(17, 17);
		testliste.put(23, 23);
		
		//Nivå 4
		testliste.put(27, 27);
		
		//testliste.skrivUtTre();
		
		//Tell antall bladnoder i treet
		System.out.println("Antall bladnoder: " + testliste.antallBladnoder());

		//Skriv ut alle noder på et valgt nivå
		testliste.skrivUtNoderPaaNivaa(4);
		
		//System.out.println("Antall noder: " + testliste.antallNoder(testliste.rot));
		

	}
}
