package Student;

import java.util.ArrayList;

public interface StudentInterface {
	public boolean AddStu(Student stu);
	public ArrayList<Student> DisplayStu();
	public ArrayList<Student> searchStudent(int rollno);
	public boolean updatestu(int rollno,Student stu);
	public ArrayList<Student> feestu();



}
