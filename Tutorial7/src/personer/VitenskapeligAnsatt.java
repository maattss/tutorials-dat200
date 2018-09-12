package personer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import emner.Emne;

// Oppgave d)
public class VitenskapeligAnsatt extends Ansatt {
	private String fagfelt;
	private ArrayList<Emne> underviser;

	public VitenskapeligAnsatt(int ID, String fornavn, String etternavn, int fodselsaar) {
		super(ID, fornavn, etternavn, fodselsaar);
		underviser = new ArrayList<>();
	}

	public String getFagfelt() {
		return fagfelt;
	}

	public void setFagfelt(String fagfelt) {
		this.fagfelt = fagfelt;
	}

	public List<Emne> getUnderviser() {
		return Collections.unmodifiableList(underviser);
	}

	public void addUnderviser(Emne emne) {
		underviser.add(emne);
	}
	
	public boolean removeUnderviser(Emne emne) {
		return underviser.remove(emne);
	}
	
	// Oppgave f)
	// Kallet super.toString() er et kall til toString() i klassen
	// Ansatt, superklassen til vitenskapeligAnsatt. Dette er en måte
	// å kalle en overkjørt metode fra en superklasse på.
	@Override public String toString() {
		String emnestreng = "\n";
		for (Emne emne: underviser) {
			emnestreng += emne.toString() + "\n";
		}
		return "Vitenskapelig " + super.toString() + 
				" er innen " + fagfelt + " og underviser: " + emnestreng;
	}
}
