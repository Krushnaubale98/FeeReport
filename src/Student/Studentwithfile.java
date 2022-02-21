package Student;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Scanner;

public class Studentwithfile implements StudentInterface {
	Student stu = new Student();
	Scanner sc = new Scanner(System.in);

	ArrayList<Student> list = new ArrayList<Student>();
	File file = new File("Stu.txt");
	ListIterator<Student> li = null;
	ObjectOutputStream oos = null;
	ObjectInputStream ois = null;

	@Override
	public boolean AddStu(Student stu) {
		boolean b = false;
		try {

			// read the data
			list.add(stu);
			// Create a file and write a data into a file
			oos = new ObjectOutputStream(new FileOutputStream(file));
			oos.writeObject(list);
			oos.close();

			b = true;
		} catch (Exception e) {
			System.out.println(e);
		}
		return b;

	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Student> DisplayStu() {

		try {
			ois = new ObjectInputStream(new FileInputStream(file));
			list = (ArrayList<Student>) ois.readObject();
			ois.close();

		} catch (Exception e) {
			System.out.println(e);

		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Student> searchStudent(int rollno) {
		list.clear();
		ArrayList<Student> list1 = new ArrayList<Student>();

		try {
			ois = new ObjectInputStream(new FileInputStream(file));
			list = (ArrayList<Student>) ois.readObject();
			ois.close();
			list1.clear();
			ListIterator<Student> li = list.listIterator();
			while (li.hasNext()) {
				Student s = (Student) li.next();
				if (rollno == s.getSRollNo()) {
					list1.add(s);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list1;

	}

	@Override
	public boolean updatestu(int rollno, Student stu) {

		boolean b = false;
		try {
			list.clear();

			list = DisplayStu();
			for (Student s : list) {
				if (rollno == s.getSRollNo()) {
					s.setSName(stu.getSName());
					s.setSEmail(stu.getSEmail());
					s.setSCourse(stu.getSCourse());
					s.setSFee(stu.getSFee());
					s.setSDue(stu.getSDue());
					s.setSPaid(stu.getSpaid());
					s.setSAddress(stu.getSAddress());
					s.setSCity(stu.getSCity());
					s.setSState(stu.getSState());
					s.setSCountry(stu.getSCountry());
					s.setContact(stu.getContact());

					oos = new ObjectOutputStream(new FileOutputStream(file));
					oos.writeObject(list);
					oos.close();

					b = true;

				}
			}
		} catch (

		Exception e) {
			System.out.println(e);

		}
		return b;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Student> feestu() {
		list.clear();
		try {
			ois = new ObjectInputStream(new FileInputStream(file));
			list = (ArrayList<Student>) ois.readObject();
			ois.close();

		} catch (

		Exception e) {
			System.out.println(e);
		}
		return list;
	}

}
