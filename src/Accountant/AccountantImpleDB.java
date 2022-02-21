package Accountant;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Scanner;

import FeeReport.feeReport;

public class AccountantImpleDB implements AccountantInterFace {
	ArrayList<Accountant> li = new ArrayList<Accountant>();

	Scanner sc = new Scanner(System.in);
	Accountant ac = new Accountant();

	@Override
	public boolean AcAdd(Accountant ac) {

		boolean b = false;

		try {

			String querry = "insert into accountant(Name,Password,Email,Contact) values(?,?,?,?)";
			PreparedStatement ps = feeReport.conn.prepareStatement(querry);
			System.out.println("Connection success");

			ps.setString(1, ac.getAcName());
			ps.setString(2, ac.getAcPassword());
			ps.setString(3, ac.getAcEmail());
			ps.setLong(4, ac.getAcContact());

			ps.executeUpdate();
			b = true;

		} catch (Exception e) {
			System.out.println(e);
		}
		return b;
	}

	@Override
	public ArrayList<Accountant> AcDisplay() {
		try {

			java.sql.Statement selstmt = feeReport.conn.createStatement();
			ResultSet rs = ((java.sql.Statement) selstmt).executeQuery("select * from accountant");

			while (rs.next()) {
				Accountant ac = new Accountant();

				ac.setAcName(rs.getString(1));
				ac.setAcPassword(rs.getString(2));
				ac.setAcEmail(rs.getString(3));
				ac.setAcContact(rs.getLong(4));
				li.add(ac);

			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return li;

	}

	@Override
	public boolean CheckAcPassUsername(String name, String password) {
		boolean b = false;

		try {

			String querry = "select * from accountant where  Name=? ";
			PreparedStatement pst = feeReport.conn.prepareStatement(querry);
			pst.setString(1, name);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {

				if (rs.getString(1).equals(name))
					b = true;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return b;
	}

	@Override
	public ArrayList<Accountant> Acdelete() {
		try {

			String querry = "delete from accountant where Name=?";
			PreparedStatement pst = feeReport.conn.prepareStatement(querry);

			System.out.println("Enter Name");
			ac.setAcName(sc.next());

			pst.setString(1, ac.getAcName());
			int rowdelete = pst.executeUpdate();
			if (rowdelete > 0) {
				System.out.println(" user deleted successfully");
			}

		} catch (Exception e) {
			System.out.println(e);
		}
		return li;

	}
}