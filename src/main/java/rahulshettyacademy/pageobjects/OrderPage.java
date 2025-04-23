package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.abstractcomponents.AbstractComponent;

public class OrderPage extends AbstractComponent
{
	WebDriver driver;
	public OrderPage(WebDriver driver) 
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(css = "li.totalRow button")
	WebElement checkoutEle;

	@FindBy(css = "tr td:nth-child(3)")
	private List<WebElement> productNames;
	
	public Boolean VerifyOrderDisplay(String productName) 
	{
		Boolean match = productNames.stream().anyMatch(product -> product.getText().equalsIgnoreCase(productName));
		return match;
	}

	public CheckOutPage goToCheckout()
	{
		//checkoutEle.click();
		Actions actions = new Actions(driver);
		actions.moveToElement(checkoutEle).click().build().perform();
		driver.findElement(By.xpath("//button[text()='Checkout']")).click();
		return new CheckOutPage(driver);
	}

}
