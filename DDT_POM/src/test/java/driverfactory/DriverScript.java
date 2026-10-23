package driverfactory;

import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.Test;

import HRM.pages.AddEmployee;
import Utils.ExcelFileUtil;
import config.AppUtil;

public class DriverScript extends AppUtil {
String inputpath ="./datatables/HRMData.xlsx";
String outputpath ="./datatables/AddEmpResults.xlsx";
@Test
public void validateEmp() throws Throwable
{
	//create object for ExcelFileUtil class
	ExcelFileUtil xl = new ExcelFileUtil(inputpath);
	 //count no of rows in a sheet
	int rc = xl.rowCount("EmpData");
	Reporter.log("No of roes are::"+rc,true);
	for(int i=1;i<=rc;i++)
	{
		String fname =xl.getCellData("EmpData", i, 0);
		String mname =xl.getCellData("EmpData", i, 1);
		String lname =xl.getCellData("EmpData", i, 2);
		//call addemployee page class
		AddEmployee emp = PageFactory.initElements(driver, AddEmployee.class);
		boolean res = emp.addEmp(fname, mname, lname);
		if(res)
		{
			//if res is true write as pass into output path
			xl.setCelldata("EmpData", i, 3, "pass", outputpath);
	
		}
		else
		{
			//if res is false write as fail into outputpath
			xl.setCelldata("EmpData", i, 3, "Fail",outputpath);
		}
	}
}
}
