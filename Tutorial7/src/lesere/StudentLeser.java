package lesere;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.TreeMap;

import personer.Student;

public class StudentLeser {
	private HashMap<Integer, Student> studentPaaID;
	
	public HashMap<Integer, Student> getStudentPaaID() {
		return studentPaaID;
	}

	public Student lesStudent(String innStreng) {
		String[] komponenter = innStreng.split("\t");
		try {
			int id = Integer.parseInt(komponenter[0]);
			int fodselsaar = Integer.parseInt(komponenter[3]);
			int aarskurs = Integer.parseInt(komponenter[5]);
			Student studenten = new Student(id, komponenter[2], komponenter[1], fodselsaar);
			studenten.setStudieprogram(komponenter[4]);
			studenten.setAarskurs(aarskurs);
			return studenten;
		} catch (NumberFormatException e) {
			System.err.println("Strengen : " + innStreng + " er i feil format!");
			return null;
		}
	}
	
	public TreeMap<String, Student> lesStudenterFraFil() {
		TreeMap<String, Student> resultat = new TreeMap<>();
		studentPaaID = new HashMap<>();
		Scanner inn = new Scanner(System.in);
		System.out.print("Filnavn for studenter: ");
		String filnavn = inn.nextLine();
		try {
			Scanner filleser = new Scanner(new File(filnavn));
			String linje;
			StudentLeser leser = new StudentLeser();
			while (filleser.hasNextLine()) {
				linje = filleser.nextLine();
				Student studenten = leser.lesStudent(linje);
				String key = studenten.getEtternavn() + ", " + studenten.getFornavn();
				resultat.put(key, studenten);
				studentPaaID.put(studenten.getID(), studenten);
			}
			return resultat;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
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
			StudentLeser leser = new StudentLeser();
			while (filleser.hasNextLine()) {
				linje = filleser.nextLine();
				Student studenten = leser.lesStudent(linje);
				System.out.println(studenten);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
