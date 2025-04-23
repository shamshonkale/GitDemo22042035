package rahulshettyacademy.tests;
import java.io.IOException;
import java.util.List;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckOutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.OrderPage;
import rahulshettyacademy.pageobjects.ProductCataloguePage;
import rahulshettyacademy.testcomponents.BaseTest;
public class SubmitOrderTest extends BaseTest
{
	String productName="ZARA COAT 3";
	@Test
	public void submitOrder() throws IOException, InterruptedException
	{		
		ProductCataloguePage productCataloguePage=landingPage.loginApplication("samtester07@mailinator.com", "Sam@tester1");
		List<WebElement> products=productCataloguePage.getProductList();
		productCataloguePage.addProductToCart(productName);
		CartPage cartPage=productCataloguePage.goToCartPage();
		Boolean match=cartPage.VerifyProductDisplay(productName);
		Assert.assertTrue(match);
		CheckOutPage checkOutPage=cartPage.goToCheckout();
		checkOutPage.selectCountry("India");
		ConfirmationPage confirmationPage=checkOutPage.submitOrder();
		String confirMessage=confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirMessage.equalsIgnoreCase("Thankyou for the order."));
	}
		//verify Zara coat 3 is displaying on orders page
		//when submitOrder() TC will execute after that we can only execute 
		//this method is depends on above method
		@Test(dependsOnMethods={"submitOrder"})
		public void verifyProductOnOrders()
		{
			ProductCataloguePage productCataloguePage=landingPage.loginApplication("samtester07@mailinator.com", "Sam@tester1");
			OrderPage orderPage=productCataloguePage.goToOrdersPage();
			Assert.assertTrue(orderPage.VerifyOrderDisplay(productName));
		}
}
