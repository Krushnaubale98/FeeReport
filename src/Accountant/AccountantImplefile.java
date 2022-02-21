package Accountant;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Scanner;

public class AccountantImplefile implements AccountantInterFace {

	Scanner sc = new Scanner(System.in);
	Accountant ac = new Accountant();

	ArrayList<Accountant> al = new ArrayList<Accountant>();
	File file = new File("AC.txt");

	ListIterator<Accountant> li = null;
	ObjectOutputStream oos = null;
	ObjectInputStream ois = null;

	@SuppressWarnings("unchecked")
	@Override
	public boolean AcAdd(Accountant ac) {
		boolean b = false;
		al.clear();
		try {

			if (file.isFile()) {

				ois = new ObjectInputStream(new FileInputStream(file));
				al = (ArrayList<Accountant>) ois.readObject();
				ois.close();

				al.add(ac);
				oos = new ObjectOutputStream(new FileOutputStream(file));
				oos.writeObject(al);
				oos.close();
				al.add(ac);
				b = true;
			}

		} catch (Exception e) {
			System.out.println(e);
		}
		return b;

	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Accountant> AcDisplay() {

		if (file.isFile()) {

			try {

				ois = new ObjectInputStream(new FileInputStream(file));
				al = (ArrayList<Accountant>) ois.readObject();
				ois.close();

			} catch (FileNotFoundException e) {

				e.printStackTrace();
			} catch (ClassNotFoundException e) {

				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		} else {

		}

		return al;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean CheckAcPassUsername(String name, String password) {
		boolean b = false;
		try {

			if (file.isFile()) {
				ois = new ObjectInputStream(new FileInputStream(file));
				al = (ArrayList<Accountant>) ois.readObject();
				ois.close();

				li = al.listIterator();
				while (li.hasNext()) {
					Accountant a = (Accountant) li.next();
					if (a.getAcName().equals(name))
						if (a.getAcPassword().equals(password))
							b = true;

				}
				System.out.println("Wrong pass ans user");

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return b;

	}

	@Override
	public ArrayList<Accountant> Acdelete() {
		// TODO Auto-generated method stub
		return null;
	}

}
