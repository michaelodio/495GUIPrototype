package bank;
import java.util.*;

public class Check {
	
	//checks have routing number, check number and bank account number
	
	private int mrouteNum;
	private int mcheckNum;
	private int mAccnum;
	
	public int getRoute()
	{
		return mrouteNum;
	}
	public void setRoute(int routeNum)
	{
		if(routeNum >=1) {
			this.mrouteNum = routeNum;
		}
		else {
			System.out.print("Must be a non-negative integer"); 
		}
		
	}
	
	

}
