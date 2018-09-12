package lesere;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

import emner.Emne;

public class EmneLeser {
	public Emne lesEmne(String innStreng) {
		String[] komponenter = innStreng.split("\t");
		Emne emnet = new Emne(komponenter[0], komponenter[1], komponenter[2].charAt(0));
		emnet.setStudiepoeng(Integer.parseInt(komponenter[3]));
		return emnet;
	}
	
	public HashMap<String, Emne> lesEmnerFraFil() {
		HashMap<String, Emne> resultat = new HashMap<>();
		Scanner inn = new Scanner(System.in);
		System.out.print("Filnavn for emner: ");
		String filnavn = inn.nextLine();
		try {
			Scanner filleser = new Scanner(new File(filnavn));
			String linje;
			EmneLeser leser = new EmneLeser();
			while (filleser.hasNextLine()) {
				linje = filleser.nextLine();
				Emne emnet = leser.lesEmne(linje);
				resultat.put(emnet.getEmnekode(), emnet);
			}
			return resultat;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	
	}
	
	public static void main(String[] args) {
		Scanner inn = new Scanner(System.in);
		System.out.print("Filnavn: ");
		String filnavn = inn.nextLine();
		try {
			Scanner filleser = new Scanner(new File(filnavn));
			String linje;
			EmneLeser leser = new EmneLeser();
			while (filleser.hasNextLine()) {
				linje = filleser.nextLine();
				Emne emnet = leser.lesEmne(linje);
				System.out.println(emnet);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
