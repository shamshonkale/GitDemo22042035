package rahulshettyacademy.testcomponents;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.openqa.selenium.Dimension;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.pageobjects.LandingPage;

public class BaseTest
{
	public WebDriver driver;
	public LandingPage landingPage;
	public WebDriver initializeDriver() throws IOException
	{
		//creating Properties class object to use our properties file
		Properties prop=new Properties();
		//converting .properties file into stream
		FileInputStream fis=new FileInputStream("E:\\UDEMY\\SOFTWARE TESTING\\Selenium Workspace\\SeleniumFrameworkDesign\\src\\main\\java\\rahulshettyacademy\\resources\\GlobalData.properties");
		//or insted of this long path you can use below
		//FileInputStream fis1=new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\rahulshettyacademy\\resources\\GlobalData.properties");
		//to load our prop file
		prop.load(fis);
		//to get property value using key from properties file
		//String browserName=prop.getProperty("browser");
		
		//Here we use java ternary operator
		String browserName=System.getProperty("browser")!=null ? System.getProperty("browser"): prop.getProperty("browser");
		if(browserName.contains("chrome"))
		{
		ChromeOptions options =new ChromeOptions();
		//This line automatically downloads and sets up the ChromeDriver for Selenium
		WebDriverManager.chromedriver().setup();
		//
		if(browserName.contains("headless"))
		{
		//to run test case in headless mode
		options.addArguments("headless");
		}
		driver=new ChromeDriver(options);
		//to make screen size in full screen mode
		driver.manage().window().setSize(new Dimension(1440, 900));
		}
		else if(browserName.equalsIgnoreCase("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			 driver=new FirefoxDriver();
		}
		else if(browserName.equalsIgnoreCase("edge"))
		{
			WebDriverManager.edgedriver().setup();
			 driver=new EdgeDriver();
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;
	}
	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {
		//Read Json to String 
		String jsonContent=FileUtils.readFileToString(new File(filePath),StandardCharsets.UTF_8);
		//Convert String data to List of Hashmap 
		ObjectMapper mapper=new ObjectMapper();
		List<HashMap<String,String>> data=mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>(){			
		});
		return data;
	}
	//method to get screenshot when test case failed
	//for screen shot functionalty this driver dont have life means its null so to get life from ITestResults result
	public String getScreenshot(String testCaseName,WebDriver driver) throws IOException
	{
		TakesScreenshot ts=(TakesScreenshot)driver;
		File source=ts.getScreenshotAs(OutputType.FILE);
		File file=new File(System.getProperty("user.dir")+"//reports//"+ testCaseName + ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir")+"//reports//"+ testCaseName + ".png";
	}
	
	//this method will run before @Test to open URL/App
	@BeforeMethod(alwaysRun = true)
	public LandingPage launchApplication() throws IOException
	{
		driver=initializeDriver();
		landingPage=new LandingPage(driver);
		landingPage.goTo();
		return landingPage;
	}
	@AfterMethod(alwaysRun = true)
	public void tearDown()
	{
		driver.close();
	}
}