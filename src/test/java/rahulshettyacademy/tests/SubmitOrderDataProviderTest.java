package rahulshettyacademy.tests;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckOutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.OrderPage;
import rahulshettyacademy.pageobjects.ProductCataloguePage;
import rahulshettyacademy.testcomponents.BaseTest;
public class SubmitOrderDataProviderTest extends BaseTest
{
	String productName="ZARA COAT 3";
//	@Test(dataProvider="getData",groups= {"Purchase"})
//	public void submitOrder(String email,String password, String productName) throws IOException, InterruptedException
//	{		
//		ProductCataloguePage productCataloguePage=landingPage.loginApplication("samtester07@mailinator.com", "Sam@tester1");
//		List<WebElement> products=productCataloguePage.getProductList();
//		productCataloguePage.addProductToCart(productName);
//		CartPage cartPage=productCataloguePage.goToCartPage();
//		Boolean match=cartPage.VerifyProductDisplay(productName);
//		Assert.assertTrue(match);
//		CheckOutPage checkOutPage=cartPage.goToCheckout();
//		checkOutPage.selectCountry("India");
//		ConfirmationPage confirmationPage=checkOutPage.submitOrder();
//		String confirMessage=confirmationPage.getConfirmationMessage();
//		Assert.assertTrue(confirMessage.equalsIgnoreCase("Thankyou for the order."));
//	}
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
//		@DataProvider
//		public Object[][] getData()
//		{
//			return new Object[][] {{"samtester07@mailinator.com","Sam@tester1","ZARA COAT 3"},
//				{"samtester07@mailinator.com","Sam@tester1","IPHONE 13 PRO"},};
//		}
		@Test(dataProvider="getData",groups= {"Purchase"})
		public void submitOrder(HashMap<String,String> input) throws IOException, InterruptedException
		{		
			ProductCataloguePage productCataloguePage=landingPage.loginApplication(input.get("email"),input.get("password"));
			List<WebElement> products=productCataloguePage.getProductList();
			productCataloguePage.addProductToCart(input.get("product"));
			CartPage cartPage=productCataloguePage.goToCartPage();
			Boolean match=cartPage.VerifyProductDisplay(input.get("product"));
			Assert.assertTrue(match);
			CheckOutPage checkOutPage=cartPage.goToCheckout();
			checkOutPage.selectCountry("India");
			ConfirmationPage confirmationPage=checkOutPage.submitOrder();
			String confirMessage=confirmationPage.getConfirmationMessage();
			Assert.assertTrue(confirMessage.equalsIgnoreCase("Thankyou for the order."));
		}
		@DataProvider
		public Object[][] getData() throws IOException
		{
			List<HashMap<String,String>> data1=getJsonDataToMap("E:\\UDEMY\\SOFTWARE TESTING\\Selenium Workspace\\SeleniumFrameworkDesign\\src\\test\\java\\rahulshettyacademy\\data\\PurchaseOrder.json");			
			return new Object[][] {{data1.get(0)},{data1.get(1)}};
		}
}
