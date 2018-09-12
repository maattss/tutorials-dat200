package problem1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFileChooser;

import student.Student;

public class Innleser {
	
	public static void lagreStudenter(ArrayList<Student> studentene) {
		JFileChooser fildialog = new JFileChooser();
		int valg = fildialog.showSaveDialog(null);
		if (valg == JFileChooser.APPROVE_OPTION) {
			File fila = fildialog.getSelectedFile();
			try (FileOutputStream filstrom = new FileOutputStream(fila);
					ObjectOutputStream objectstrom = new ObjectOutputStream(filstrom)) {
				objectstrom.writeObject(studentene);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static Student lesInnStudent(Scanner inn) {
		System.out.println("Skriv inn student: ");
		System.out.print("Fornavn, avslutt med tom linje: ");
		String fornavn = inn.nextLine();
		if (fornavn.equals("")) return null;
		System.out.print("Etternavn: ");
		String etternavn = inn.nextLine();
		System.out.print("Studieretning: ");
		String studieretning = inn.nextLine();
		Student studenten = new Student(fornavn, etternavn, studieretning);
		System.out.print("�rskurs, default 1: ");
		String testAarskurs = inn.nextLine();
		int aarskurs;
		try {
			aarskurs = Integer.parseInt(testAarskurs);
			studenten.setYear(aarskurs);
		} catch (NumberFormatException e) {
			// Lar �rskurs v�re 1
		}
		return studenten;
	}

	public static void main(String[] args) {
		ArrayList<Student> studentene = new ArrayList<>();
		Student studenten;
		Scanner inn = new Scanner(System.in);
		while (true) {
			studenten = lesInnStudent(inn);
			if (studenten == null) break;
			studentene.add(studenten);
		}
		lagreStudenter(studentene);
	}

}
