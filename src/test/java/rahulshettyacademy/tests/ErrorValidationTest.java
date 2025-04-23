package rahulshettyacademy.tests;
import java.io.IOException;
import java.util.List;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import rahulshettyacademy.testcomponents.Retry;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.ProductCataloguePage;
import rahulshettyacademy.testcomponents.BaseTest;
public class ErrorValidationTest extends BaseTest
{
	@Test(groups= {"ErrorHandling"},retryAnalyzer=Retry.class)
	public void loginErrorValidation() throws IOException, InterruptedException
	{
		//given wrong eamil/password to fail execution 
		landingPage.loginApplication("samtcvcvvcester007@mailinator.com", "Sam@tester1");
		Assert.assertEquals("Incorrect email or password.",landingPage.getErrorMessage());
	}
	@Test
	public void prodErrorValidation() throws IOException, InterruptedException
	{
		String productName="ZARA COAT 3";
		ProductCataloguePage productCataloguePage=landingPage.loginApplication("samtester07@mailinator.com", "Sam@tester1");
		List<WebElement> products=productCataloguePage.getProductList();
		productCataloguePage.addProductToCart(productName);
		CartPage cartPage=productCataloguePage.goToCartPage();
		Boolean match=cartPage.VerifyProductDisplay(productName);
		Assert.assertTrue(match);
	}
}
