package com.Guru99eCommerce.LiveProject;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;


	public class TestCase3 {
	private WebDriver driver;
	private String baseUrl = "http://live.techpanda.org";

	@BeforeTest
	public void setUp() throws Exception {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void testTestCase3() throws Exception {

		// 1. Go to http://live.techpanda.org
		driver.get(baseUrl);

		// 2. Click on Mobile menu
		driver.findElement(By.xpath("//a[normalize-space()='Mobile']")).click();

		// 3. In the list of all mobile , click on ‘ADD TO CART’ for Sony Xperia mobile
		driver.findElement(By.xpath("//li[2]//div[1]//div[3]//button[1]//span[1]//span[1]")).click();

		// 4. Change ‘QTY’ value to 1000 and click ‘UPDATE’ button.
		// Expected "The requested quantity for "Sony Xperia" is not available." error
		// message is displayed.

		WebElement QTY = driver.findElement(By.xpath("//input[@title='Qty']"));
		QTY.clear();
		QTY.sendKeys("1000");

		Thread.sleep(3000);
		driver.findElement(By.xpath("//button[@title='Update']")).click();
		Thread.sleep(3000);
		
		// 5. Verify the error message
		String reqQty = driver.findElement(By.xpath("//*[@id=\"shopping-cart-table\"]/tbody/tr/td[2]/p")).getText();
		String msgQty = ("* The maximum quantity allowed for purchase is 500.");
		try {
			assertEquals(reqQty, msgQty);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Thread.sleep(3000);

		// 6. Click on ‘EMPTY CART’ link in the footer of list of all mobiles. A message
		// "SHOPPING CART IS EMPTY" is shown.

		driver.findElement(By.xpath("//*[@id=\"empty_cart_button\"]/span/span")).click();
		Thread.sleep(3000);

		// 7. Verify cart is empty
		String noItemsStg = ("You have no items in your shopping cart.");
		String noItemsMsg = driver.findElement(By.xpath("//*[@id=\"top\"]/body/div/div/div[2]/div/div/div[2]/p[1]"))
				.getText();
		System.out.println("You have no items msg = " + noItemsMsg);

		try {
			assertEquals(noItemsStg, noItemsMsg);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@AfterTest
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		driver.quit();
	}

}
