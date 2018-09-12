package emner;

public class Emne {
	private String emnekode;
	private String emnenavn;
	private String beskrivelse;
	private int studiepoeng;
	private char semester;		// 'H' for høst og 'V' for vår
	
	public Emne(String emnekode, String emnenavn, char semester) {
		this.emnekode = emnekode;
		this.emnenavn = emnenavn;
		this.semester = semester;
		
		// Default verdier
		beskrivelse = "";
		studiepoeng = 10;
	}

	public String getEmnenavn() {
		return emnenavn;
	}

	public void setEmnenavn(String emnenavn) {
		this.emnenavn = emnenavn;
	}

	public String getBeskrivelse() {
		return beskrivelse;
	}

	public void setBeskrivelse(String beskrivelse) {
		this.beskrivelse = beskrivelse;
	}

	public int getStudiepoeng() {
		return studiepoeng;
	}

	public void setStudiepoeng(int studiepoeng) {
		this.studiepoeng = studiepoeng;
	}

	public char getSemester() {
		return semester;
	}

	public void setSemester(char semester) {
		this.semester = semester;
	}

	public String getEmnekode() {
		return emnekode;
	}
	
	@Override public String toString() {
		return emnekode + ": " + emnenavn;
	}
}
