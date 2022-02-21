package Accountant;

import java.util.ArrayList;

public interface AccountantInterFace {
	public boolean AcAdd(Accountant ac);
	public ArrayList<Accountant>AcDisplay();
	public boolean CheckAcPassUsername(String name, String password);
	public ArrayList<Accountant> Acdelete();
	
	

}
