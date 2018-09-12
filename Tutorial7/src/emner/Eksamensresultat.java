package emner;

import personer.Student;

public class Eksamensresultat {
	private Student studenten;
	private char karakter;
	private Emne emnet;
	
	public Eksamensresultat(Student studenten, Emne emnet, char karakter) {
		this.studenten = studenten;
		this.emnet = emnet;
		this.karakter = karakter;
	}

	public char getKarakter() {
		return karakter;
	}

	public void setKarakter(char karakter) {
		this.karakter = karakter;
	}

	public Student getStudenten() {
		return studenten;
	}

	public Emne getEmnet() {
		return emnet;
	}
	
	
}
