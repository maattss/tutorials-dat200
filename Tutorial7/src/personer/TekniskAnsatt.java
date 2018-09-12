package personer;

import emner.Rom;

// Oppgave e)
public class TekniskAnsatt extends Ansatt {
	private Rom ansvarlig;

	public TekniskAnsatt(int ID, String fornavn, String etternavn, int fodselsaar) {
		super(ID, fornavn, etternavn, fodselsaar);
		// Trenger ikke mer her
	}

	public Rom getAnsvarlig() {
		return ansvarlig;
	}

	public void setAnsvarlig(Rom ansvarlig) {
		this.ansvarlig = ansvarlig;
	}
	
	// Oppgave f)
	@Override public String toString() {
		return "Teknisk " + super.toString() + 
				" har ansvar for " + ansvarlig.formattertRomnummer();
	}

}
