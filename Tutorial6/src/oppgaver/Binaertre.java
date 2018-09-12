package oppgaver;

import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Binaertre <T> {
	private class Binaertrenode {
		T objekt;
		Binaertrenode venstre;
		Binaertrenode hoyre;
	}
	
	private Binaertrenode rot;
	
	public Iterator<T> preOrderIterator() {
		return new PreOrderIterator();
	}
	
	private class PreOrderIterator implements Iterator<T> {
		
		private class StabelElement {
			Binaertrenode noden;
			
			public StabelElement(Binaertrenode noden) {
				this.noden = noden;
			} 
		}
		
		private Stack<StabelElement> stabelen;
		
		public PreOrderIterator() {
			stabelen = new Stack<>();
			stabelen.push(new StabelElement(rot));
		}

		public boolean hasNext() {
			if (stabelen.isEmpty()) return false;
			return true;
		}

		
		public T next() {
			while (true) {
				StabelElement elementet = stabelen.pop();
				if (elementet.noden.hoyre != null) {
					stabelen.push(new StabelElement(elementet.noden.hoyre));
				}
				if (elementet.noden.venstre != null) {
					stabelen.push(new StabelElement(elementet.noden.venstre));
				}
				return elementet.noden.objekt;
			}
		}
		
	}
	
	public Iterator<T> postOrderIterator() {
		return new postOrderIterator();
	}
	
	private class postOrderIterator implements Iterator<T> {
		
		private class StabelElement {
			Binaertrenode noden;
			int teller;
			
			public StabelElement(Binaertrenode noden, int teller) {
				this.noden = noden;
				this.teller = teller;
			}
		}
		
		private Stack<StabelElement> stabelen;
		
		public postOrderIterator() {
			stabelen = new Stack<>();
			stabelen.push(new StabelElement(rot, 0));
		}
		
		@Override
		public boolean hasNext() {
			if (stabelen.isEmpty()) return false;
			return true;
		}

		@Override
		public T next() {
			while (true) {
				StabelElement elementet = stabelen.pop();
				if (elementet.teller == 0) {
					elementet.teller++;
					stabelen.push(elementet);
					if (elementet.noden.venstre != null) {
						stabelen.push(new StabelElement(elementet.noden.venstre, 0));
					}
					continue;
				}
				if (elementet.teller == 1) {
					elementet.teller++;
					stabelen.push(elementet);
					if (elementet.noden.hoyre != null) {
						stabelen.push(new StabelElement(elementet.noden.hoyre, 0));
					}
					continue;					
				}
				return elementet.noden.objekt;
			}
		}
		
	}
	
	public int postorderKalkulator(Binaertre<String> treet) {
		//Iterator<String> it = postOrderIterator();
		Iterator<T> it = postOrderIterator();
		Stack<Integer> stabel  = new Stack<>();
		
		while(it.hasNext()) {
			String next = (String)it.next();
			
			if(next.equals("+")) {
				stabel.push(stabel.pop() + stabel.pop());
			} else if(next.equals("-")) {
				stabel.push(stabel.pop() - stabel.pop());
			
			} else if(next.equals("*")) {
				stabel.push(stabel.pop() * stabel.pop());
				
			} else if(next.equals("/")) {
				stabel.push(stabel.pop() * stabel.pop());
					
			} else {
				stabel.push(Integer.parseInt(next));
			
			}
			
		}
		return stabel.pop();
	}
	
	public void printTre(Binaertrenode tmpRot) {
        Queue<Binaertrenode> currentLevel = new LinkedList<Binaertrenode>();
        Queue<Binaertrenode> nextLevel = new LinkedList<Binaertrenode>();

        currentLevel.add(tmpRot);

        while (!currentLevel.isEmpty()) {
            Iterator<Binaertrenode> iterator = currentLevel.iterator();
            while (iterator.hasNext()) {
            	Binaertrenode nvNode = iterator.next();
                if (nvNode.venstre != null) {
                    nextLevel.add(nvNode.venstre);
                }
                if (nvNode.hoyre != null) {
                    nextLevel.add(nvNode.hoyre);
                }
                System.out.print(nvNode.objekt.toString() + " ");
            }
            System.out.println();
            currentLevel = nextLevel;
            nextLevel = new LinkedList<Binaertrenode>();

        }

    }
	
	
	public static void main(String[] args) {
		Binaertre<String> testetre = new Binaertre<>();
		//Rot
		testetre.rot = testetre.new Binaertrenode();
		testetre.rot.objekt = "+";
		
		//Barn til roten
		testetre.rot.venstre = testetre.new Binaertrenode();
		testetre.rot.venstre.objekt = "-";
		testetre.rot.hoyre = testetre.new Binaertrenode();
		testetre.rot.hoyre.objekt = "*";
		
		//Barn til rot.venstre
		testetre.rot.venstre.venstre = testetre.new Binaertrenode();
		testetre.rot.venstre.venstre.objekt = "1";
		testetre.rot.venstre.hoyre = testetre.new Binaertrenode();
		testetre.rot.venstre.hoyre.objekt = "2";
		
		//Barn til rot.høyre
		testetre.rot.hoyre.venstre = testetre.new Binaertrenode();
		testetre.rot.hoyre.venstre.objekt = "3";
		testetre.rot.hoyre.hoyre = testetre.new Binaertrenode();
		testetre.rot.hoyre.hoyre.objekt = "4";
		
		Iterator<String> preIt = testetre.preOrderIterator();
		System.out.print("Preorderiterator: ");
		while (preIt.hasNext()) {
			System.out.print(preIt.next() + " ");
		}
		Iterator<String> postIt = testetre.postOrderIterator();
		System.out.print("\nPostorderiterator: ");
		while (postIt.hasNext()) {
			System.out.print(postIt.next() + " ");
		}
		
		System.out.println("\nPostorderkalkulator. Resultat: " + testetre.postorderKalkulator(testetre));
		System.out.println("Utkrift av tre");
	
		testetre.printTre(testetre.rot);
	}
}
