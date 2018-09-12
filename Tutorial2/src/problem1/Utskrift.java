package problem1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import javax.swing.JFileChooser;

import student.Student;


public class Utskrift {
	
	public static ArrayList<Student> lesInnStudenter() {
		ArrayList<Student> lista = null;
		Object o;
		JFileChooser fildialog = new JFileChooser();
		int valg = fildialog.showOpenDialog(null);
		if (valg == JFileChooser.APPROVE_OPTION) {
			File fila = fildialog.getSelectedFile();
			try (FileInputStream filstrom = new FileInputStream("/Users/mats/Downloads/studentliste_oving_9");
					ObjectInputStream objectstrom = new ObjectInputStream(filstrom)) {
				o = objectstrom.readObject();
				if (o instanceof ArrayList<?>) {
					lista = (ArrayList<Student>)o;
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return lista;
	}

	public static void main(String[] args) {
		ArrayList<Student> studentene = lesInnStudenter();
		for (Student s: studentene) {
			System.out.println(s);
		}
	}

}
