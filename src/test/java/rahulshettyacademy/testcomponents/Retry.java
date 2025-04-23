package rahulshettyacademy.testcomponents;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
public class Retry implements IRetryAnalyzer{
	//this class we created for retrying operation for failed test cases 
	int count=0;
	int maxTry=1;
	@Override
	public boolean retry(ITestResult result) {
		if(count<maxTry)
		{
			count++;
			//to re run the method
			return true;
		}
		return false;
	}
}
