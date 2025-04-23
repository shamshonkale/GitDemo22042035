package rahulshettyacademy.tests;
import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.pageobjects.LandingPage;
public class StandaloneTest 
{
	public static void main(String args[])
	{
		String productName="ZARA COAT 3";
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		LandingPage landPage=new LandingPage(driver);
		landPage.goTo();
		landPage.loginApplication("samtester07@mailinator.com", "Sam@tester1");
		driver.findElement(By.id("userEmail")).sendKeys("samtester07@mailinator.com");
		driver.findElement(By.id("userPassword")).sendKeys("Sam@tester1");
		driver.findElement(By.id("login")).click();
		
		//explicit wait
		//sam github
		System.out.println("Git Hub");
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		//waiting to load all products
		//or to wait until a specific element is visible on the web page before interacting with it
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.mb-3")));
		List<WebElement> products=driver.findElements(By.cssSelector("div.mb-3"));
		WebElement prod=products.stream().filter(product->product.findElement(By.cssSelector("b")).getText().equals("ZARA COAT 3")).findFirst().orElse(null);
		prod.findElement(By.cssSelector("div.card-body button:last-of-type")).click();
		
		//waiting to get message for product added to cart
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		//waiting to complete loading here we already know locator for loading if you don't get 
		//take help of developer bcoz it difficult to find sometime
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		//to click on cart
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		
		//verify what product is added to cart is added  
		List<WebElement> cartProducts=driver.findElements(By.cssSelector("div.cartSection h3"));
		Boolean match=cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(match);
		
		//to click on check out button
		driver.findElement(By.cssSelector("li.totalRow button")).click();
		
		//to enter india in select country dropdown
		Actions ac=new Actions(driver);
		ac.sendKeys(driver.findElement(By.cssSelector("input[placeholder=\"Select Country\"]")), "india").build().perform();
		
		//wait for india value should be visible in auto suggestive dropdown
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button.ta-item")));
		
		//to select india from dropdown
		driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
		//to click on place order button
		driver.findElement(By.cssSelector("a.action__submit")).click();
		
		String conMsg=driver.findElement(By.cssSelector("hero-primary")).getText();
		Assert.assertTrue(conMsg.equalsIgnoreCase("Thankyou for the order."));
		driver.close();
	}
}
