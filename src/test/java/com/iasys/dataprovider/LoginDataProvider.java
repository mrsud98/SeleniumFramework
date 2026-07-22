package com.iasys.dataprovider;

import org.testng.annotations.DataProvider;

import com.iasys.utilities.ExcelUtil;

public class LoginDataProvider {

	@DataProvider(name = "loginData")
	public Object[][] loginData() {

		ExcelUtil excel = new ExcelUtil("src/test/resources/testdata/LoginData.xlsx");

		Object[][] data = excel.getSheetData("Login");

		excel.close();

		return data;

	}

}
