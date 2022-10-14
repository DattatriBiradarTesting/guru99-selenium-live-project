package com.Guru99eCommerce.LiveProject;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestCase8 {
	private WebDriver driver;
	private String baseUrl = "http://live.techpanda.org";
	public String firstName = "selenium test";
	public String lastName = "automation tool";
	public String EmailAdd = "selenium_javaTest@gmail.com";
	public String password = "testing@#1233";
	public String vPrice, sPrice;

	@BeforeTest
	public void setUp() throws Exception {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void testTestCase8() throws Throwable {
		driver.get(baseUrl);
		driver.findElement(By.linkText("MY ACCOUNT")).click();
		Thread.sleep(1500);

		for (String handle : driver.getWindowHandles()) {
			driver.switchTo().window(handle);
		}
		driver.findElement(By.id("email")).sendKeys(EmailAdd);
		driver.findElement(By.id("pass")).sendKeys(password);
		driver.findElement(By.id("send2")).click();
		Thread.sleep(5000);

		for (String handle : driver.getWindowHandles()) {
			driver.switchTo().window(handle);
		}
		System.out.println("***** Before Reorder *****");
		driver.findElement(By.xpath("//a[@class='link-reorder']")).click();
		System.out.println("***** After Reorder *****");

		String GrandTotal = "//*[@id=\"shopping-cart-totals-table\"]/tfoot/tr/td[2]/strong/span";
		vPrice = driver.findElement(By.xpath(GrandTotal)).getText();
		System.out.println(vPrice);

		for (String handle : driver.getWindowHandles()) {
			driver.switchTo().window(handle);
		}
		WebElement Qty = driver.findElement(By.xpath("//input[@title='Qty']"));
		Qty.clear();
		Qty.sendKeys("10");
		System.out.println("----- Qulity Set -----");

		driver.findElement(By.xpath("//button[@title='Update']")).click();
		Thread.sleep(2000);

		String Grand = "//*[@id=\"shopping-cart-totals-table\"]/tfoot/tr/td[2]/strong/span";
		sPrice = driver.findElement(By.xpath(Grand)).getText();

		if (vPrice == sPrice) {
			System.out.println("Grand Total Price is Not Chenged");
		} else {
			System.out.println("Grand Total Price is Chenged");
		}
		String proced = "//*[@id=\"top\"]/body/div/div/div[2]/div/div/div/div[3]/div/ul/li[1]/button";
		driver.findElement(By.xpath(proced)).click();
		Thread.sleep(2000);

		for (String handle : driver.getWindowHandles()) {
			driver.switchTo().window(handle);
		}

		driver.findElement(By.xpath("//label[@for='billing:use_for_shipping_no']")).click();
		String continuee = "//*[@id=\"billing-buttons-container\"]/button";
		driver.findElement(By.xpath(continuee)).click();
		Thread.sleep(2000);

		driver.findElement(By.xpath("//label[@for='shipping:same_as_billing']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[@onclick='shipping.save()']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[@onclick='shippingMethod.save()']")).click();
		Thread.sleep(2000);

		for (String handle : driver.getWindowHandles()) {
			driver.switchTo().window(handle);
		}
		driver.findElement(By.xpath("//*[@id='dt_method_checkmo']/label")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[@onclick='payment.save()']")).click();
		Thread.sleep(2000);

		for (String handle : driver.getWindowHandles()) {
			driver.switchTo().window(handle);
		}
		String BILLING = "//div[@id='billing-progress-opcheckout']//address[contains(text(),'selenium test automation tool')]";
		String BILLINGADDRESS = driver.findElement(By.xpath(BILLING)).getText();
		System.out.println(BILLINGADDRESS);
		
		System.out.println("__________________________________________");
		
		String SHIPPING = "//div[@id='shipping-progress-opcheckout']//address[contains(text(),'selenium test automation tool')]";
		String SHIPPINGADDRESS = driver.findElement(By.xpath(SHIPPING)).getText();
		System.out.println(SHIPPINGADDRESS);

		driver.findElement(By.xpath("//button[@title='Place Order']")).click();
		Thread.sleep(2000);

		String NumOrder = driver.findElement(By.xpath("//div[@class='main-container col1-layout']//p[1]")).getText();
		System.out.println("*** Your order number for your record = " + NumOrder);

	}

	@AfterTest
	public void tearDown() throws Throwable {
		Thread.sleep(3000);
		driver.quit();
	}

}
