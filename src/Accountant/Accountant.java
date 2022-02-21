package Accountant;

import java.io.Serializable;


public class Accountant implements Serializable{

	private String AcName;
	private String AcPassword;
	private String AcEmail;
	private Long AcContact;
	public String getAcName() {
		return AcName;
	}
	public void setAcName(String acName) {
		AcName = acName;
	}
	public String getAcPassword() {
		return AcPassword;
	}
	public void setAcPassword(String acPassword) {
		AcPassword = acPassword;
	}
	public String getAcEmail() {
		return AcEmail;
	}
	public void setAcEmail(String acEmail) {
		AcEmail = acEmail;
	}
	public Long getAcContact() {
		return AcContact;
	}
	public void setAcContact(Long acContact) {
		AcContact = acContact;
	}
	
	
}
