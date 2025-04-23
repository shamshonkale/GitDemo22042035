package rahulshettyacademy.resources;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
public class ExtentReportNG 
{
	public static ExtentReports getReportObject()
	{
			//String path="E:\\UDEMY\\SOFTWARE TESTING\\Selenium Workspace\\SeleniumFrameworkDesign\\reports\\index.html";
			String path=System.getProperty("user.dir")+"\\reports\\index.html";
			ExtentSparkReporter reporter=new ExtentSparkReporter(path);
			reporter.config().setReportName("Web Automation Result");
			reporter.config().setDocumentTitle("Test Results");
			
			ExtentReports extent=new ExtentReports();
			extent.attachReporter(reporter);
			extent.setSystemInfo("Tester","Rahul Shetty");
			return extent;
	}
}
