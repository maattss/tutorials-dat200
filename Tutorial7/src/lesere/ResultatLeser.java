package lesere;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.TreeMap;

import emner.Eksamensresultat;
import emner.Emne;
import personer.Student;

public class ResultatLeser {
	private List<Student> studentene;
	private List<Emne> emnene;
	private List<Eksamensresultat> resultatene;
	
	private TreeMap<String, Student> studentTre;
	private HashMap<Integer, Student> studentPaaID;
	private HashMap<Integer, List<Eksamensresultat>> resultatHash;
	private HashMap<String, Emne> emneHash;
	
	public ResultatLeser() {
		//studentene = new StudentLeser().lesStudenterFraFil();
		//emnene = new EmneLeser().lesEmnerFraFil();
		//resultatene = new ArrayList<>();
		StudentLeser studentleser = new StudentLeser();
		studentTre = studentleser.lesStudenterFraFil();
		studentPaaID = studentleser.getStudentPaaID();
		emneHash = new EmneLeser().lesEmnerFraFil();
		resultatHash = new HashMap<>();
	}
	
	// O(1)
	private Student finnStudent(int id) {
		return studentPaaID.get(id);
	}
	
	// O(1)
	private Emne finnEmne(String emnekode) {
		return emneHash.get(emnekode);
	}
	
	private Eksamensresultat lesResultat(String innStreng) {
		String[] komponenter = innStreng.split("\t");
		Student student = finnStudent(Integer.parseInt(komponenter[0]));
		Emne emnet = finnEmne(komponenter[1]);
		Eksamensresultat resultatet = new Eksamensresultat(student, emnet, komponenter[2].charAt(0));
		
		return resultatet;
	}
	
	public HashMap<Integer, ArrayList<Eksamensresultat>> lesResultaterFraFil() {
		HashMap<Integer, ArrayList<Eksamensresultat>> resultat = new HashMap<>();
		Scanner inn = new Scanner(System.in);
		System.out.print("Filnavn for eksamensresultater: ");
		String filnavn = inn.nextLine();
		try {
			Scanner filleser = new Scanner(new File(filnavn));
			while (filleser.hasNextLine()) {
				String linje = filleser.nextLine();
				ArrayList<Eksamensresultat> resultatliste;
				Eksamensresultat resultatet = lesResultat(linje);
				Integer ID = resultatet.getStudenten().getID();
				if(resultat.containsKey(ID)) {
					resultatliste = resultat.get(ID);
				} else {
					resultatliste = new ArrayList<>();
				}
				resultatliste.add(resultatet);
				resultat.put(ID, resultatliste);
			}
			return resultat;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void main(String[] args) {
		ResultatLeser leser = new ResultatLeser();
		/*
		List<Eksamensresultat> resultatene = leser.lesResultaterFraFil();
		for (Eksamensresultat resultatet: resultatene) {
			System.out.println(resultatet.getStudenten().getEtternavn() + 
					", " + resultatet.getStudenten().getFornavn() + 
					" har f�tt " + resultatet.getKarakter() + " i " + 
					resultatet.getEmnet().getEmnenavn());
		}
		*/
	}
}
