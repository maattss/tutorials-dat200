package personer;

import java.util.ArrayList;

/*
 * Oppgave d, markerte metoder og variabler fra andre deloppgaver
 */
public class Person implements Comparable<Person>{
	private int ID;
	private String fornavn;
	private String etternavn;
	private int fodselsaar;
	private ArrayList<Hendelse> hendelser; // Oppgave h)
	
	/*
	 * Konstrukt�ren var ikke spesifisert i oppgaveteksten. Dette er bare
	 * en m�te den kan gj�res p�. Poenget er at man m� ha en metode
	 * � sette alle egenskapene p� selv de som ikke har settere.
	 */
	public Person(int ID, String fornavn, String etternavn, int fodselsaar) {
		this.ID = ID;
		this.fornavn = fornavn;
		this.etternavn = etternavn;
		this.fodselsaar = fodselsaar;
		hendelser = new ArrayList<>();
	}
	
	// Trengs for oppgave j
	public ArrayList<Hendelse> getHendelser() {
		return hendelser;
	}
	
	// Trengs for oppgave k
	public boolean invitertTilHendelse(Hendelse hendelsen) {
		return hendelser.contains(hendelsen);
	}
	
	// Oppgave k
	public void leggTilHendelse(Hendelse hendelsen) {
		hendelser.add(hendelsen);
		if (!hendelsen.personInvitert(this)) hendelsen.leggTilPerson(this);
	}
	
	public String getFornavn() {
		return fornavn;
	}
	
	public void setFornavn(String fornavn) {
		this.fornavn = fornavn;
	}
	
	public String getEtternavn() {
		return etternavn;
	}
	
	public void setEtternavn(String etternavn) {
		this.etternavn = etternavn;
	}
	
	public int getID() {
		return ID;
	}
	
	public int getFodselsaar() {
		return fodselsaar;
	}
	
	@Override public String toString() {
		return "Person " + ID + ": " + fornavn + " " + etternavn + 
				" født i " + fodselsaar;
	}
	
	public String getNavnestreng() {
		return fornavn + " " + etternavn + 
		" født i " + fodselsaar;
	}

	@Override
	public int compareTo(Person o) {
		return etternavn.compareTo(o.etternavn);
	}
}
