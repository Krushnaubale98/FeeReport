package Student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Scanner;

import FeeReport.feeReport;

public class Studentdb implements StudentInterface {
	Student stu = new Student();
	Scanner sc = new Scanner(System.in);
	ArrayList<Student> list = new ArrayList<Student>();

	public boolean AddStu(Student stu) {
		boolean b = false;
		try {

			String querry = "insert into student( RollNo,Name,Email,Course,Fee,Paid,Due,Address,City,State,Country,Contact)values(?,?,?,?,?,?,?,?,?,?,?,?)";

			PreparedStatement stm = feeReport.conn.prepareStatement(querry);
			stm.setInt(1, stu.getSRollNo());
			stm.setString(2, stu.getSName());
			stm.setString(3, stu.getSEmail());
			stm.setString(4, stu.getSCourse());
			stm.setInt(5, stu.getSFee());
			stm.setInt(6, stu.getSpaid());
			stm.setInt(7, stu.getSDue());
			stm.setString(8, stu.getSAddress());
			stm.setString(9, stu.getSCity());
			stm.setString(10, stu.getSState());
			stm.setString(11, stu.getSCountry());
			stm.setLong(12, stu.getContact());
			stm.executeUpdate();
			b = true;

		} catch (Exception e) {
			System.out.println(e);
		}
		return b;

	}

	public ArrayList<Student> DisplayStu() {
		try {

			java.sql.Statement selectstmt = feeReport.conn.createStatement();
			ResultSet rs = ((java.sql.Statement) selectstmt).executeQuery("select * from student");
			list.clear();

			System.out.println();

			while (rs.next()) {
				Student stu = new Student();

				stu.setSRollNo(rs.getInt(1));
				stu.setSName(rs.getString(2));
				stu.setSEmail(rs.getString(3));
				stu.setSCourse(rs.getString(4));
				stu.setSFee(rs.getInt(5));
				stu.setSPaid(rs.getInt(6));
				stu.setSDue(rs.getInt(7));
				stu.setSAddress(rs.getString(8));
				stu.setSCity(rs.getString(9));
				stu.setSState(rs.getString(10));
				stu.setSCountry(rs.getString(11));
				stu.setContact(rs.getLong(12));
				list.add(stu);

			}

		} catch (Exception e) {

			System.out.println(e);
		}
		return list;
	}

	@Override
	public ArrayList<Student> searchStudent(int rollno) {

		list.clear();
		ArrayList<Student> list1 = new ArrayList<Student>();

		try {

			String sqlq = ("SELECT * FROM student WHERE RollNo=?");
			PreparedStatement st = feeReport.conn.prepareStatement(sqlq);
			st.setInt(1, rollno);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				stu.setSRollNo(rs.getInt(1));
				stu.setSName(rs.getString(2));
				stu.setSEmail(rs.getString(3));
				stu.setSCourse(rs.getString(4));
				stu.setSFee(rs.getInt(5));
				stu.setSPaid(rs.getInt(6));
				stu.setSDue(rs.getInt(7));
				stu.setSAddress(rs.getString(8));
				stu.setSCity(rs.getString(9));
				stu.setSState(rs.getString(10));
				stu.setSCountry(rs.getString(11));
				stu.setContact(rs.getLong(12));
				list1.add(stu);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list1;
	}

	@Override
	public boolean updatestu(int rollno, Student stu) {
		boolean b = false;
		int count = 0;
		try {

			String sqlq = ("SELECT * FROM student WHERE RollNo=?");
			PreparedStatement st = feeReport.conn.prepareStatement(sqlq);
			st.setInt(1, stu.getSRollNo());
			ResultSet rs = st.executeQuery();
			rs.next();
			count = rs.getInt(1);

		} catch (Exception k) {
			System.out.println(k);
		}
		if (count > 0)
			try {
				String querry = "UPDATE student SET Name=?,Email=?,Course=?,Fee=?,Paid=?,Due=?,Address=?,City=?,State=?,Country=?,Contact=? WHERE RollNo=?";
				PreparedStatement st = feeReport.conn.prepareStatement(querry);
				st.setInt(1, stu.getSRollNo());
				st.setString(2, stu.getSName());
				st.setString(3, stu.getSEmail());
				st.setString(4, stu.getSCourse());
				st.setInt(5, stu.getSFee());
				st.setInt(6, stu.getSpaid());
				st.setInt(7, stu.getSDue());
				st.setString(8, stu.getSAddress());
				st.setString(9, stu.getSCity());
				st.setString(10, stu.getSState());
				st.setString(11, stu.getSCountry());
				st.setLong(12, stu.getContact());
				st.executeUpdate();
				b = true;
			} catch (Exception f) {
				System.out.println(f);
			}
		return b;
	}

	public ArrayList<Student> feestu() {
		try {
//			Class.forName("com.mysql.cj.jdbc.Driver");
//			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/miniproject", "root", "Kanha");
//			System.out.println("connected");
//			

			java.sql.Statement selectStmt = feeReport.conn.createStatement();
			ResultSet rs = ((java.sql.Statement) selectStmt)
					.executeQuery("SELECT RollNo,Name,Fee,Paid,Due FROM student");
			System.out.println(" rollno \t\t name \t\t fee \t\t paid \t\t due ");
			while (rs.next()) {
				if (rs.getInt(5) > 0) {

					stu.setSRollNo(rs.getInt(1));
					stu.setSName(rs.getString(2));
					stu.setSFee(rs.getInt(3));
					stu.setSPaid(rs.getInt(4));
					stu.setSDue(rs.getInt(5));

					System.out.println();
				}
			}
		} catch (Exception f) {
			System.out.println(f);
		}
		return list;
	}

}
