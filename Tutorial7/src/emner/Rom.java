package emner;

public class Rom {
	private char bygning;
	private int etasje;
	private int romnummer;
	private double areal;
	
	public Rom(char bygning, int etasje, int romnummer, double areal) {
		this.bygning = bygning;
		this.etasje = etasje;
		this.romnummer = romnummer;
		this.areal = areal;
	}
		
	public char getBygning() {
		return bygning;
	}
	
	public void setBygning(char bygning) {
		this.bygning = bygning;
	}
	
	public int getEtasje() {
		return etasje;
	}
	
	public void setEtasje(int etasje) {
		this.etasje = etasje;
	}
	
	public int getRomnummer() {
		return romnummer;
	}
	
	public void setRomnummer(int romnummer) {
		this.romnummer = romnummer;
	}
	
	public double getAreal() {
		return areal;
	}
	
	public void setAreal(double areal) {
		this.areal = areal;
	}
	
	public String formattertRomnummer() {
		String resultat = bygning + "-" + etasje;
		if (romnummer < 10) {
			resultat += "0";
		}
		resultat += romnummer;
		return resultat;
	}
	
	@Override public String toString() {
		return "Rom: " + formattertRomnummer() + " med areal: " + areal;
	}
}
