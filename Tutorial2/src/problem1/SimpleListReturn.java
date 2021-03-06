package problem1;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;

import student.Student;

public class SimpleListReturn {
	
	public static List<Student> firstNameSearch(List<Student> studentlist, String fName) {
		
		List<Student> result = new ArrayList<>();
		fName = fName.substring(0, 1).toUpperCase() + fName.substring(1).toLowerCase();
		
		for(Student s: studentlist) {
			if (s.getFirstName().equals(fName)) result.add(s);
		}
		
		return result;
	}
	
	public static ArrayList<Student> readStudents() {
		ArrayList<Student> list = null;
		Object o;
		JFileChooser fildialog = new JFileChooser();
		int choice = fildialog.showOpenDialog(null);
		if (choice == JFileChooser.APPROVE_OPTION) {
			File f = fildialog.getSelectedFile();
			try (FileInputStream filstrom = new FileInputStream(f);
					ObjectInputStream objectstrom = new ObjectInputStream(filstrom)) {
				o = objectstrom.readObject();
				if (o instanceof ArrayList<?>) {
					list = (ArrayList<Student>)o;
				}
			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
			
		return list;
	}
	

	public static void main(String[] args) {
		System.out.println("All students:");
		List	<Student> allStudents = readStudents();
		for (Student s: allStudents ) {
			System.out.println(s);
		}
		
		System.out.println("\nStudents with first name Birger");
		List <Student> result = firstNameSearch(allStudents, "bIrger");
		for(Student s: result) {
			System.out.println(s);
		}
	}

}
