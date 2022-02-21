package Student;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Student implements Serializable {
	private int rollno;
	private String name;
	private String email;
	private String course;
	private int fee;
	private int paid;
	private int due;
	private String address;
	private String city;
	private String state;
	private String country;
	private long contact;

	public int getSRollNo() {
		return rollno;
	}

	public void setSRollNo(int rollno) {
		this.rollno = rollno;

	}

	public String getSName() {
		return name;
	}

	public void setSName(String name) {
		this.name = name;

	}

	public String getSEmail() {
		return email;
	}

	public void setSEmail(String email) {
		this.email = email;

	}

	public String getSCourse() {
		return course;
	}

	public void setSCourse(String course) {
		this.course = course;

	}

	public int getSFee() {
		return fee;
	}

	public void setSFee(int fee) {
		this.fee = fee;

	}

	public int getSpaid() {
		return paid;
	}

	public void setSPaid(int paid) {
		this.paid = paid;

	}

	public int getSDue() {
		return fee-paid;
	}

	public void setSDue(int due) {
		this.due = fee-paid;

	}

	public String getSAddress() {
		return address;
	}

	public void setSAddress(String address) {
		this.address = address;

	}

	public String getSCity() {
		return city;
	}

	public void setSCity(String city) {
		this.city = city;
	}

	public String getSState() {
		return state;
	}

	public void setSState(String state) {
		this.state = state;
	}

	public String getSCountry() {
		return country;
	}

	public void setSCountry(String country) {
		this.country = country;

	}

	public long getContact() {
		return contact;
	}

	public void setContact(long contact) {
		this.contact = contact;
	}
}
