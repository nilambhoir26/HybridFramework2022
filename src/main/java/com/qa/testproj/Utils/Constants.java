package com.qa.testproj.Utils;

import java.util.ArrayList;
import java.util.List;

public class Constants {

	public static final String Login_Page_Title ="Account Login";
	public static final String Accounts_Page_Title ="My Account";
	public static final String Accounts_Page_header ="Your Store";
	public static final int Accounts_Sections_Count = 4;
	public static final String Search_Text = "imac";
	
	public  static List<String> getAccountsHeadersList() {
		List<String> accountsSections = new ArrayList<String>();
		accountsSections.add("My Account");
		accountsSections.add("My Orders");
		accountsSections.add("My Affiliate Account");
		accountsSections.add("Newsletter");
		return accountsSections;
		
	}
	
	
	
}
