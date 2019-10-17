package com.accion.utility;

public class Constants {

		// Excel sheets
		public static String TESTCASES_SHEET="TestCases";
		public static String DATA_SHEET="TestData";
		
		// Column names
		public static String RUNMODE_COL="Runmode";
		public static String TESTCASES_COL="TestCaseName";
		
		// Runmode
		public static String RUNMODE_YES="Y";
		public static String RUNMODE_NO="N";
			
		// Xpath for HomePageStepDef page
		public static final String USERNAME = "//input[contains(@id,'AuthenticationFG.USER_PRINCIPAL') and contains(@placeholder,'User ID')]";
		public static final String PASSWORD = "//input[contains(@id,'AuthenticationFG.ACCESS_CODE') and contains(@placeholder,'Password')]";
		public static final String LOGIN = "//input[contains(@id,'VALIDATE_CREDENTIALS') and contains(@value,'HomePageStepDef')]";
		public static final String LOGOUT = "//*[@title='Logout']";
		
		// Xpath for Main Tabs	
		public static final String MYPRODUCTS = "//a[contains(@href,'javascript:void(0)') and contains(text(),'My Products')]";
		public static final String PAYMENTS = "//a[contains(@href,'javascript:void(0)') and contains(text(),'Payments')]";
		public static final String SENDMONEY = "//a[contains(@href,'javascript:void(0)') and contains(text(),'Send Money')]";
		public static final String SERVICES = "//a[contains(@href,'javascript:void(0)') and contains(text(),'Services')]";
		public static final String PRICING = "//a[contains(@href,'javascript:void(0)') and contains(text(),'Discover & Apply')]";
		public static final String DISCOVERANDAPPLY = "//a[contains(@href,'javascript:void(0)') and contains(text(),'Shipment')]";
		public static final String ISLAMICPRODUCTS = "//a[contains(@href,'javascript:void(0)') and contains(text(),'Islamic Products')]";
		
		public static final String OPERATIONS = "//a[contains(@href,'javascript:void(0)') and contains(text(),'Operations')]";
		public static final String ACCOUNTING = "//a[contains(@href,'javascript:void(0)') and contains(text(),'Accounting')]";
		public static final String MIS = "//a[contains(@href,'javascript:void(0)') and contains(text(),'MIS')]";
		public static final String CLAIMS = "//a[contains(@href,'javascript:void(0)') and contains(text(),'Claims')]";
		public static final String EIX = "//a[contains(@href,'javascript:void(0)') and contains(text(),'EIX')]";
		public static final String ADMINISTRATION = "//a[contains(@href,'javascript:void(0)') and contains(text(),'Administration')]";
		
		public static final String EMBARGO = "//a[contains(text(),'Embargo')]";
		public static final String MAINTAIN_TIME_ZONE_AND_DAY_LIGHT_SAVING = "//a[contains(text(),'Maintain Time Zone and Day Light Saving')]";
		
	}
