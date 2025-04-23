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

public class CartPage extends AbstractComponent
{
	WebDriver driver;
	public CartPage(WebDriver driver) 
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(css = "li.totalRow button")
	WebElement checkoutEle;

	@FindBy(css = ".cartSection h3")
	private List<WebElement> cartProducts;
	
	public Boolean VerifyProductDisplay(String productName) 
	{
		Boolean match = cartProducts.stream().anyMatch(product -> product.getText().equalsIgnoreCase(productName));
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
