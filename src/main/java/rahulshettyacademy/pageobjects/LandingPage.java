package rahulshettyacademy.pageobjects;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import rahulshettyacademy.abstractcomponents.AbstractComponent;
public class LandingPage extends AbstractComponent
{
	WebDriver driver;
	
	//constructor
	public LandingPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	//WebElement userEmails=driver.findElement(By.id("userEmail"));
	//PageFactory
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	@FindBy(id="userPassword")
	WebElement passwordEle;
	
	@FindBy(id="login")
	WebElement submitEle;
	
	//this element we find by using selector hub tool or dev 
	//because this element is we are not able to inspect its going
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMsg;
	
	public ProductCataloguePage loginApplication(String email,String password)
	{
		userEmail.sendKeys(email);
		passwordEle.sendKeys(password);
		submitEle.click();
		ProductCataloguePage productCataloguePage=new ProductCataloguePage(driver);
		return productCataloguePage;
	}
	public String getErrorMessage()
	{
		waitForWebElementToAppear(errorMsg);
		return errorMsg.getText();
	}
	//url of application
	public void goTo()
	{
		driver.get("https://rahulshettyacademy.com/client");
	}
}
