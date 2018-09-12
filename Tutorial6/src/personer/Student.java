package personer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Oppgave c)
public class Student extends Person {
	private String studieprogram;
	private int aarskurs;
	private ArrayList<Emne> oppmeldtI;
	
	public Student(int ID, String fornavn, String etternavn, int fodselsaar) {
		super(ID, fornavn, etternavn, fodselsaar);
		oppmeldtI = new ArrayList<>();
	}
	
	public void meldOppStudet(Emne emnet) {
		oppmeldtI.add(emnet);
	}
	
	public boolean avmeldStudent(Emne emnet) {
		return oppmeldtI.remove(emnet);
	}

	public String getStudieprogram() {
		return studieprogram;
	}

	public void setStudieprogram(String studieprogram) {
		this.studieprogram = studieprogram;
	}

	public int getAarskurs() {
		return aarskurs;
	}

	public void setAarskurs(int aarskurs) {
		this.aarskurs = aarskurs;
	}

	public List<Emne> getOppmeldtI() {
		return Collections.unmodifiableList(oppmeldtI);
	}

	// Oppgave f)
	@Override public String toString() {
		return "Student " + getID() + ": " + 
				getNavnestreng() + " studerer " +
				studieprogram + " og er i " + aarskurs +
				" årskurs";
	}
}
