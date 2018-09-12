package personer;

import java.util.ArrayList;

/*
 * Oppgave d, markerte metoder og variabler fra andre deloppgaver
 */
public class Person implements Comparable<Person> {
	private int ID;
	private String fornavn;
	private String etternavn;
	private int fodselsaar;

	public Person(int ID, String fornavn, String etternavn, int fodselsaar) {
		this.ID = ID;
		this.fornavn = fornavn;
		this.etternavn = etternavn;
		this.fodselsaar = fodselsaar;
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
				" f¯dt i " + fodselsaar;
	}
	
	public String getNavnestreng() {
		return fornavn + " " + etternavn + 
		" født i " + fodselsaar;
	}

	@Override
	public int compareTo(Person arg0) {
		int sammenlikning = etternavn.compareTo(arg0.getEtternavn());
		if (sammenlikning != 0) return sammenlikning;
		return fornavn.compareTo(arg0.getFornavn());
	}
}
