package personer;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Oppgave c)
public class Hendelse {
	private String beskrivelse;  
	private LocalDate startDato; 
	private LocalDate sluttDato;
	private ArrayList<Person> invitertePersoner; // Oppgave e)
	
	/* Konstruktør for å lage invitertePersoner lista. Denne lista
	 * må bli laget, men oppgaven spesifiserte ikke hvordan. Dette er
	 * en mulig løsning.
	 * 
	 */
	public Hendelse() {
		invitertePersoner = new ArrayList<>();
	}
	
	public String getBeskrivelse() {
		return beskrivelse;
	}

	public void setBeskrivelse(String beskrivelse) {
		this.beskrivelse = beskrivelse;
	}
	
	public LocalDate getStartDato() {
		return startDato;
	}
	
	public void setStartDato(LocalDate startDato) throws IllegalArgumentException {
		if (sluttDato != null && sluttDato.isBefore(startDato)) {
			throw new IllegalArgumentException("Startdato kan ikke være etter sluttdato!");
		}
		this.startDato = startDato;
	}
	
	public LocalDate getSluttDato() {
		return sluttDato;
	}
	
	public void setSluttDato(LocalDate sluttDato) throws IllegalArgumentException {
		if (startDato != null && sluttDato.isBefore(startDato)) {
			throw new IllegalArgumentException("Sluttdato kan ikke være før startdato!");
		}
		this.sluttDato = sluttDato;
	}
	
	public String toString() {
		return "Hendelsen \"" + beskrivelse + "\" starter på " + 
				startDato.toString() + " og slutter på " + sluttDato.toString();
	}
	
	// Trengs for oppgave k)
	public boolean personInvitert(Person personen) {
		return invitertePersoner.contains(personen);
	}
	
	/*
	 * Metodesignaturen samt den markerte linja er oppgave f). Metoden inneholder
	 * også oppgave j) og en del av oppgave k) 
	 */
	public void leggTilPerson(Person personen) {		
		// Oppgave j
		ArrayList<Hendelse> hendelsesliste = personen.getHendelser();
		for (Hendelse hendelsen: hendelsesliste) {
			if (kolliderer(hendelsen)) {
				System.out.println("Den nye hendelsen " + toString() + 
						"kolliderer med hendelsen " + hendelsen.toString());
			}
		}
		
		// Oppgave f)
		invitertePersoner.add(personen);
		
		// Oppgave k)
		if (!personen.invitertTilHendelse(this)) personen.leggTilHendelse(this);
	}
	
	public List<Person> getInvitertePersoner() {
//		return new ArrayList<Person>(invitertePersoner);
		return Collections.unmodifiableList(invitertePersoner);
	}
	
	public static boolean kolliderer(Hendelse en, Hendelse to) {
		LocalDate start1 = en.getStartDato();
		LocalDate start2 = to.getStartDato();
		LocalDate slutt1 = en.getSluttDato();
		LocalDate slutt2 = to.getSluttDato();
		if (slutt1.isBefore(start2)) {
			return false;
		}
		if (slutt2.isBefore(start1)) {
			return false;
		}
		return true;
	}
	
	public boolean kolliderer(Hendelse denAndre) {
		LocalDate start2 = denAndre.getStartDato();
		LocalDate slutt2 = denAndre.getSluttDato();
		if (sluttDato.equals(start2)) return false;
		if (startDato.equals(slutt2)) return false;
		if (sluttDato.isBefore(start2)) {
			return false;
		}
		if (slutt2.isBefore(startDato)) {
			return false;
		}
		return true;		
	}
}
