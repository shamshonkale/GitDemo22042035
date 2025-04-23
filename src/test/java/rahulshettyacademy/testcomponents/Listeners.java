package rahulshettyacademy.testcomponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import rahulshettyacademy.resources.ExtentReportNG;

public class Listeners extends BaseTest implements ITestListener
{
	ExtentTest test;
	ExtentReports extent=ExtentReportNG.getReportObject();
	//to make thread safe and syncronized
	ThreadLocal<ExtentTest> extentTest=new ThreadLocal<ExtentTest>();
	//when any TC/TC method executes then result variable(ITestResult result) contains all info about that TC
	@Override
	public void onTestStart(ITestResult result)
	{
		//to get method name which is given to the TC/TC method
		test=extent.createTest(result.getMethod().getMethodName());
		//to set unique thread id to the extentTest
		extentTest.set(test);
	}
	@Override
	public void onTestFailure(ITestResult result)
	{
		//to get method name which is given to the TC/TC method
		extentTest.get().fail(result.getThrowable());
		try {
			//to get driver information/life
			driver=(WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e) {
			e.printStackTrace();
		}
		String filePath=null;
		try {
			//if TC fail we are taking screenshot and adding that screen shot to extent reports
			filePath=getScreenshot(result.getMethod().getMethodName(),driver);
		} catch (IOException e) {
			e.printStackTrace();
		}
		extentTest.get().addScreenCaptureFromPath(filePath,result.getMethod().getMethodName());
	}
	@Override
	public void onTestSuccess(ITestResult result)
	{
		//to get method name which is given to the TC/TC method
		extentTest.get().log(Status.PASS, "Test Passed");
	}
	@Override
	public void onFinish(ITestContext context)
	{
		extent.flush();
	}
}
