package FeeReport;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import Accountant.Accountant;
import Accountant.AccountantImpleDB;
import Accountant.AccountantImplefile;
import Accountant.AccountantInterFace;
import Admin.Admin;
import Student.Student;
import Student.StudentInterface;
import Student.Studentdb;
import Student.Studentwithfile;

public class feeReport {
	Login db = new Login();
	Accountant ac = new Accountant();
	Student stu = new Student();
	public static boolean FD;
	Scanner sc = new Scanner(System.in);

	public void Start() {
		int a = 0;
		while (a == 0) {
			System.out.print("*** Choose one Where You want to store a data **** \n");
			System.out.println("1. Operation on  Database");
			System.out.println("2. Operation on File");
			System.out.println("0. Exit");
			int b = sc.nextInt();

			if (b == 1) {
				feeReport.FD = true;
				db.login();

			} else if (b == 2) {
				feeReport.FD = false;
				db.login();

			} else if (b == 0)

				a++;
			else
				System.out.println("Enter number is invalid");
		}

	}

	public static Connection conn;

	public static Connection connection() {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/miniproject", "root", "Kanha");
			System.out.println("Connection success");

			return conn;
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	public static void main(String[] args) {
		connection();

		feeReport ml = new feeReport();
		ml.Start();

	}

	public class Login {

		ArrayList<Accountant> li = new ArrayList<Accountant>();
		ArrayList<Student> ar = new ArrayList<Student>();

		Scanner sc = new Scanner(System.in);
		Admin ad = new Admin();

		AccountantInterFace acfile = new AccountantImplefile();
		AccountantInterFace acdb = new AccountantImpleDB();
		StudentInterface stufile = new Studentwithfile();
		StudentInterface studb = new Studentdb();

		public void login() {
			int j = 0;
			while (j == 0) {
				System.out.println("Welcome");
				System.out.println("1. Admin Login =>");
				System.out.println("2. Accountant Login=>");
				System.out.println("0. Exit =>");
				int k = sc.nextInt();
				if (k == 1)

					AdminLogin();
				else if (k == 2) {
					ACLogin();
				} else if (k == 0)
					j++;
				else
					System.out.println("number is invalid");

			}
		}

		// set andmin username and password
		public boolean login(String Username, String Password)
		// Set username and password
		{
			ad.SetAdminName("Admin");
			ad.SetAdminPassWord("Admin123");
			boolean f = false;
			if (Username.equals(ad.GetAdminName()) && Password.equals(ad.GetAdminPassword())) {
				f = true;
			} else {
				f = false;
			}

			return f;

		}

		// Admin Login
		public void AdminLogin() {
			System.out.println("Welcome to AdminLogin\n");

			System.out.print("Enter your name=>");
			String name = sc.next();
			System.out.print("Enter your password =>");
			String Password = sc.next();
			if (login(name, Password)) {

				int k = 0;
				while (k == 0) {
					System.out.println("*** Admin Login ****");
					System.out.println("Press 1. Add Accountant=>");
					System.out.println("press 2. View Accountant=>");
					System.out.println("press 3 delete accountant=>");
					System.out.println("press 0. Logout=>");

					int a = sc.nextInt();
					if (a == 1)
						addAC();
					else if (a == 2)
						displayAC();
					else if (a == 3)
						delete();
					else if (a == 0)
						k++;
					else
						System.out.println("number is invalid");

				}
			} else
				System.out.println("Invalid Usename and password");

		}

		// *************************

		public void addAC() {

			String name;
			String email;
			String password;
			String contact;
			Accountant ac = new Accountant();
			boolean b = false;

			while (!b) {
				System.out.print("Name : ");
				while (!(name = sc.nextLine()).matches("[A-Za-z]+")) {
					System.out.print("\nInvalid name please re-enter : ");
				}
				ac.setAcName(name);

				System.out.print("Email : ");
				while (!(email = sc.nextLine()).matches("[A-Za-z0-9]+[@][a-z]+[.][a-z]+")) {
					System.out.print("\nInvalid email re-enter : ");
				}
				ac.setAcEmail(email);

				System.out.print("Password : ");
				while (!((password = sc.nextLine()).trim().length() > 4)) {
					System.out.print("\nShort password re-enter : ");
				}
				ac.setAcPassword(password);

				System.out.println("Enter contact");
				while (!(contact = sc.nextLine()).trim().matches("[0-9]+")) {
					System.out.print("Invalid contact re-enter : ");
				}
				ac.setAcContact(Long.parseLong(contact));

				try {
					if (feeReport.FD) {
						if (acdb.AcAdd(ac)) {
							System.out.print("\nAccountant added succesfully ...\n");
						} else
							System.err.print("\n db Error please try again later...\n");

					} else {
						if (acfile.AcAdd(ac)) {
							System.out.print("\nAccountant added succesfully ...\n");
						} else
							System.err.print("\n file Error please try again later...\n");

					}
					break;

				} catch (Exception e) {
					System.out.println(e);
					e.printStackTrace();
				}

			}
		}

		// ***************************
		public void displayAC() {
			li.clear();
			if (feeReport.FD)
				li = acdb.AcDisplay();
			else
				li = acfile.AcDisplay();
			for (Accountant k : li) {
				System.out.print("Name = " + k.getAcName() + "\t");
				System.out.print("Password = " + k.getAcPassword() + "\t");
				System.out.print("Email = " + k.getAcEmail() + "\t");
				System.out.print("Contact = " + k.getAcContact() + "\n");

//				System.out.printf("%-30s  %-30s  %-10s  %s\n", k.getAcName(), k.getAcPassword(), k.getAcEmail(),
//				 k.getAcContact());

			}

		}

		// ****************************
		public void delete() {
			li.clear();
			if (feeReport.FD) {

				li = acdb.Acdelete();
			} else

				li = acfile.Acdelete();

		}

// ******************************

		// Accountant Login
		public void ACLogin() {

			System.out.println("*** Accountant Login ****");
			System.out.print("Enter AC name=>");
			String name = sc.next();
			System.out.print("Enter AC password =>");
			String password = sc.next();

			if (feeReport.FD) {

				if (acdb.CheckAcPassUsername(name, password)) {
					int k = 0;
					while (k == 0) {
						System.out.println("*** Student login ****");
						System.out.println("1. Add Student    =>");
						System.out.println("2. Display student=>");
						System.out.println("3. Loading student");
						System.out.println("4. Edit student   =>");
						System.out.println("5. Due fee student    =>");
						System.out.print("0 . exit           =>");
						int a = sc.nextInt();
						if (a == 1)
							Stuadd();
						else if (a == 2)
							stuDisplay();
						else if (a == 3)
							loadStudent();
						else if (a == 4)
							StuUpdate();
						else if (a == 5)
							stuDuefee();
						else if (a == 0)
							k++;
						else
							System.out.println("number is invalid");

					}
				}

			} else

			if (acfile.CheckAcPassUsername(name, password)) {
				int k = 0;
				while (k == 0) {
					System.out.println("*** Student login ****");
					System.out.println("1. Add Student    =>");
					System.out.println("2. Display student=>");
					System.out.println("3. Loading student");
					System.out.println("4. Edit student   =>");
					System.out.println("5. Due fee student    =>");
					System.out.print("0 . exit           =>");
					int a = sc.nextInt();
					if (a == 1)
						Stuadd();
					else if (a == 2)
						stuDisplay();
					else if (a == 3)
						loadStudent();
					else if (a == 4)
						StuUpdate();
					else if (a == 5)
						stuDuefee();
					else if (a == 0)
						k++;
					else
						System.out.println("number is invalid");

				}
			}

		}

//*********************************************
		public void Stuadd() {
			Student stu = new Student();
			boolean b = false;
			while (!b) {
				System.out.print("Enter Roll No=>");
				String rollno;
				while (!(rollno = sc.nextLine()).trim().matches("[0-9]+")) {
					System.out.print("Invalid contact re-enter : ");
				}
				stu.setContact(Integer.parseInt(rollno));

				// vairables
				String name, email, course, fee, paid, address, city, state, country,

						contact;
				System.out.println("Enter name");
				while (!(name = sc.nextLine()).trim().matches("[A-Za-z ]+")) {
					System.out.print("\n Invalid re-enter : ");

				}
				stu.setSName(name);

				System.out.println("Enter Email");
				while (!(email = sc.nextLine()).trim().matches("[A-Za-z0-9]+[@][a-z]+[.][a-z]+")) {
					System.out.print("Invalid e-mail re-enter : ");
				}
				stu.setSEmail(email);

				System.out.println("Enter course");
				while (!(course = sc.nextLine()).trim().matches("[A-Za-z]+")) {
					System.out.print("Invalid re-enter : ");
				}
				stu.setSCourse(course);

				System.out.println("Enter fee");
				while (!(fee = sc.nextLine()).trim().matches("[0-9]+")) {
					System.out.print("Invalid fee re-enter : ");
				}
				stu.setSFee(Integer.parseInt(fee));

				System.out.println("Enter paid");
				while (!(paid = sc.nextLine()).trim().matches("[0-9]+")) {
					System.out.print("Invalid paid re-enter : ");
				}
				stu.setSPaid(Integer.parseInt(paid));

				int add = stu.getSDue();
				System.out.println("Due fees=" + add);

				System.out.println("Enter address");
				while (!(address = sc.nextLine()).trim().matches("[A-Za-z0-9 -]+")) {
					System.out.print("Invalid characters re-enter : ");
				}
				stu.setSAddress(address);

				System.out.println("Enter city");
				while (!(city = sc.nextLine()).trim().matches("[A-Za-z]+")) {
					System.out.print("Invalid characters re-enter : ");
				}
				stu.setSCity(city);

				System.out.println("Enter state");
				while (!(state = sc.nextLine()).trim().matches("[A-Za-z]+")) {
					System.out.print("Invalid characters re-enter : ");
				}
				stu.setSState(state);

				System.out.println("Enter country");
				while (!(country = sc.nextLine()).trim().matches("[A-Za-z ]+")) {
					System.out.print("Invalid characters re-enter : ");

				}
				stu.setSCountry(country);

				System.out.println("Enter contact");
				while (!(contact = sc.nextLine()).trim().matches("[0-9]+")) {
					System.out.print("Invalid contact re-enter : ");
				}
				stu.setContact(Long.parseLong(contact));

				if (feeReport.FD) {
					if (studb.AddStu(stu)) {
						System.out.print("\nAccountant added succesfully ...\n");
					} else
						System.err.print("\nError please try again later...\n");

				} else {
					if (stufile.AddStu(stu)) {
						System.out.print("\nAccountant added succesfully ...\n");
					} else
						System.err.print("\nError please try again later...\n");
				}
				break;

			}

		}

		public void stuDisplay() {
			ar.clear();
			if (feeReport.FD)
				ar = studb.DisplayStu();
			else
				ar = stufile.DisplayStu();

			for (Student s : ar) {

				System.out.print("RollNO = " + s.getSRollNo() + "\t");
				System.out.print("Name = " + s.getSName() + "\t");
				System.out.print("Email = " + s.getSEmail() + "\t");
				System.out.print("Course = " + s.getSCourse() + "\t");
				System.out.print("Fee = " + s.getSFee() + "\t");
				System.out.print("Paid = " + s.getSpaid() + "\t");
				System.out.print("Due = " + s.getSDue() + "\t");
				System.out.print("Address = " + s.getSAddress() + "\t");
				System.out.print("City = " + s.getSCity() + "\t");
				System.out.print("State = " + s.getSState() + "\t");
				System.out.print("Country = " + s.getSCountry() + "\t");
				System.out.print("Contact = " + s.getContact() + "\n");
				System.out.println();

			}

		}

		public void loadStudent() {

			System.out.println("Enter student id");
			int rollno = sc.nextInt();
			if (feeReport.FD)
				ar = studb.searchStudent(rollno);
			else
				ar = stufile.searchStudent(rollno);
			if (ar.size() > 0) {
				for (Student s : ar) {
					System.out.print("Rollno = " + s.getSRollNo() + "\t");
					System.out.print("Name = " + s.getSName() + "\t");
					System.out.print("Email = " + s.getSEmail() + "\t");
					System.out.print("Course = " + s.getSCourse() + "\t");
					System.out.print("Fee = " + s.getSFee() + "\t");
					System.out.print("Paid = " + s.getSpaid() + "\t");
					System.out.print("Due = " + s.getSDue() + "\t");
					System.out.print("Address = " + s.getSAddress() + "\t");
					System.out.print("City = " + s.getSCity() + "\t");
					System.out.print("State = " + s.getSState() + "\t");
					System.out.print("Country = " + s.getSCountry() + "\t");
					System.out.print("Contact = " + s.getContact() + "\n");
				}
				System.out.println();
			} else
				System.out.println("Record not Found");
		}

		public void StuUpdate() {

			ar.clear();

			String rollno;
			System.out.println("Enter student Rollno");
			while (!(rollno = sc.nextLine()).trim().matches("[0-9]+")) {
				System.out.print("Invalid id re-enter : ");
			}

			if (feeReport.FD)
				ar = studb.searchStudent(Integer.parseInt(rollno));
			else
				ar = stufile.searchStudent(Integer.parseInt(rollno));

			if (ar.size() > 0) {
				for (Student s : ar) {
					System.out.print("Rollno = " + s.getSRollNo() + "\t");
					System.out.print("Name = " + s.getSName() + "\t");
					System.out.print("Email = " + s.getSEmail() + "\t");
					System.out.print("Course = " + s.getSCourse() + "\t");
					System.out.print("Fee = " + s.getSFee() + "\t");
					System.out.print("Paid = " + s.getSpaid() + "\t");
					System.out.print("Due = " + s.getSDue() + "\t");
					System.out.print("Address = " + s.getSAddress() + "\t");
					System.out.print("City = " + s.getSCity() + "\t");
					System.out.print("State = " + s.getSState() + "\t");
					System.out.print("Country = " + s.getSCountry() + "\t");
					System.out.print("Contact = " + s.getContact() + "\n");

					// vairables
					String name, email, course, fee, paid, address, city, state, country,

							contact;
					System.out.println("Enter name");
					while (!(name = sc.nextLine()).trim().matches("[A-Za-z ]+")) {
						System.out.print("\n Invalid re-enter : ");

					}
					stu.setSName(name);

					System.out.println("Enter Email");
					while (!(email = sc.nextLine()).trim().matches("[A-Za-z0-9]+[@][a-z]+[.][a-z]+")) {
						System.out.print("Invalid e-mail re-enter : ");
					}
					stu.setSEmail(email);

					System.out.println("Enter course");
					while (!(course = sc.nextLine()).trim().matches("[A-Za-z]+")) {
						System.out.print("Invalid re-enter : ");
					}
					stu.setSCourse(course);

					System.out.println("Enter fee");
					while (!(fee = sc.nextLine()).trim().matches("[0-9]+")) {
						System.out.print("Invalid fee re-enter : ");
					}
					stu.setSFee(Integer.parseInt(fee));

					System.out.println("Enter paid");
					while (!(paid = sc.nextLine()).trim().matches("[0-9]+")) {
						System.out.print("Invalid paid re-enter : ");
					}
					stu.setSPaid(Integer.parseInt(paid));

					int add = stu.getSDue();
					System.out.println("Due fees=" + add);

					System.out.println("Enter address");
					while (!(address = sc.nextLine()).trim().matches("[A-Za-z0-9 -]+")) {
						System.out.print("Invalid characters re-enter : ");
					}
					stu.setSAddress(address);

					System.out.println("Enter city");
					while (!(city = sc.nextLine()).trim().matches("[A-Za-z]+")) {
						System.out.print("Invalid characters re-enter : ");
					}
					stu.setSCity(city);

					System.out.println("Enter state");
					while (!(state = sc.nextLine()).trim().matches("[A-Za-z]+")) {
						System.out.print("Invalid characters re-enter : ");
					}
					stu.setSState(state);

					System.out.println("Enter country");
					while (!(country = sc.nextLine()).trim().matches("[A-Za-z ]+")) {
						System.out.print("Invalid characters re-enter : ");

					}
					stu.setSCountry(country);

					System.out.println("Enter contact");
					while (!(contact = sc.nextLine()).trim().matches("[0-9]+")) {
						System.out.print("Invalid contact re-enter : ");
					}
					stu.setContact(Long.parseLong(contact));

					if (feeReport.FD) {
						if (studb.updatestu(Integer.parseInt(rollno), stu)) {
							System.out.println("DB Record edit Succrssfully");
						} else
							System.out.println("DB Record not Found");
					} else

					if (stufile.updatestu(Integer.parseInt(rollno), stu)) {
						System.out.println("File record edit");
					} else
						System.out.println("file record not fount");

				}
			}
		}

		public void stuDuefee() {
			ar.clear();
			if (feeReport.FD)
				ar = studb.feestu();
			else
				ar = stufile.feestu();
			for (Student sa : ar) {
				System.out.print("Rollno = " + sa.getSRollNo() + "\t");
				System.out.print("Name = " + sa.getSName() + "\t");
				System.out.print("Fee= " + sa.getSFee() + "\t");
				System.out.print("Paid = " + sa.getSpaid() + "\t");
				System.out.print("Due = " + sa.getSDue() + "\t");

				System.out.println();
			}

		}

	}

}