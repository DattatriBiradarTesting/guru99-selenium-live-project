package com.Guru99eCommerce.LiveProject;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestCase6 {
	private WebDriver driver;
	private String baseUrl = "http://live.techpanda.org";
	public String firstName = "selenium test";
	public String lastName = "automation tool";
	public String EmailAdd = "selenium_javaTest@gmail.com";
	public String password = "testing@#1233";

	// "mahiniki@gmail.com";
	// "vinayak123@#";
	// "testing@#1233";// "12345@#";//
	// selenium_javaTest@gmail.com";// "testing123@gmail.com";
	@BeforeTest
	public void setUp() throws Exception {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void testTestCase6() throws Throwable {
		driver.get(baseUrl);
		driver.findElement(By.linkText("MY ACCOUNT")).click();

		for (String handle : driver.getWindowHandles()) {
			driver.switchTo().window(handle);
		}
		driver.findElement(By.id("email")).sendKeys(EmailAdd);
		driver.findElement(By.id("pass")).sendKeys(password);
		driver.findElement(By.id("send2")).click();
		Thread.sleep(3000);

		for (String handle : driver.getWindowHandles()) {
			driver.switchTo().window(handle);
		}
		driver.findElement(By.linkText("MY WISHLIST")).click();
		Thread.sleep(3000);

		for (String handle : driver.getWindowHandles()) {
			driver.switchTo().window(handle);
		}
		driver.findElement(By.xpath("//span[contains(text(),'Add to Cart')]")).click();
		Thread.sleep(3000);

		for (String handle : driver.getWindowHandles()) {
			driver.switchTo().window(handle);
		}
		new Select(driver.findElement(By.id("country"))).selectByIndex(14);
		driver.findElement(By.id("postcode")).sendKeys("585413");
		driver.findElement(By.xpath("//span[contains(text(),'Estimate')]")).click();

		String FlatRate = "Flat Rate";
		String RateFlat = driver.findElement(By.xpath("//*[@id='co-shipping-method-form']/dl/dt")).getText();

		try {
			System.out.println("The 1 FlatRate: " + FlatRate);
			System.out.println("The 2 RateFlat: " + RateFlat);
			assertEquals(FlatRate, RateFlat);

		} catch (Exception e) {
			e.printStackTrace();
		}

		String sFaltRatePrice = "Fixed - $5.00";
		String rate = "//*[@id='co-shipping-method-form']/dl/dd/ul/li/label";
		String vFaltRatePrice = driver.findElement(By.xpath(rate)).getText();

		try {
			System.out.println(sFaltRatePrice);
			System.out.println(vFaltRatePrice);
			assertEquals(sFaltRatePrice, vFaltRatePrice);
		} catch (Exception e) {
			e.printStackTrace();
		}

		driver.findElement(By.id("s_method_flatrate_flatrate")).click();
		String upate = "//span[contains(text(),'Update Total')]";
		driver.findElement(By.xpath(upate)).click();

		String vFaltRaePrice = "$5.00";
		String verify = "//*[@id='shopping-cart-totals-table']/tbody/tr[2]/td[2]/span";
		String ShoppingCard = driver.findElement(By.xpath(verify)).getText();

		try {
			System.out.println(vFaltRaePrice);
			System.out.println(ShoppingCard);
			assertEquals(vFaltRaePrice, ShoppingCard);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String ProcedCheck = "//*[@id='top']/body/div/div/div[2]/div/div/div/div[3]/div/ul/li[1]/button";
		driver.findElement(By.xpath(ProcedCheck)).click();
		Thread.sleep(3000);

		for (String handle : driver.getWindowHandles()) {
			driver.switchTo().window(handle);
		}
		Thread.sleep(2000);

		try {
			Select bAddr = new Select(driver.findElement(By.xpath("//li[@id='billing-new-address-form']")));
			int bAddrSize = bAddr.getOptions().size();
			bAddr.selectByIndex(bAddrSize - 1);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("No DropDown element present");
		}
		WebElement billing_firstname = driver.findElement(By.id("billing:firstname"));
		billing_firstname.clear();
		Thread.sleep(3000);
		billing_firstname.sendKeys(firstName);

		WebElement billing_lastname = driver.findElement(By.id("billing:lastname"));
		billing_lastname.clear();
		Thread.sleep(3000);
		billing_lastname.sendKeys(lastName);

		WebElement road = driver.findElement(By.id("billing:street1"));
		road.clear();
		road.sendKeys("148 Crown Street Road");
		Thread.sleep(3000);

		WebElement city = driver.findElement(By.name("billing[city]"));
		city.clear();
		city.sendKeys("BidarDist");
		Thread.sleep(3000);

		WebElement postcode = driver.findElement(By.id("billing:postcode"));
		postcode.clear();
		postcode.sendKeys("585413");
		Thread.sleep(3000);

		new Select(driver.findElement(By.id("billing:country_id"))).selectByVisibleText("Australia");
		Thread.sleep(3000);

		WebElement phone = driver.findElement(By.name("billing[telephone]"));
		phone.clear();
		phone.sendKeys("6781235097");
		Thread.sleep(3000);

		String shipping = "//label[@for='billing:use_for_shipping_no']";
		Thread.sleep(3000);

		driver.findElement(By.xpath(shipping)).click();
		Thread.sleep(3000);

		String continue2 = "//*[@id=\"billing-buttons-container\"]/button";
		driver.findElement(By.xpath(continue2)).click();
		Thread.sleep(3000);

		for (String handle : driver.getWindowHandles()) {
			driver.switchTo().window(handle);
		}
		WebElement first = driver.findElement(By.id("shipping:firstname"));
		first.clear();
		Thread.sleep(3000);
		first.sendKeys("vinayak");

		WebElement last = driver.findElement(By.id("shipping:lastname"));
		last.clear();
		Thread.sleep(3000);
		last.sendKeys("Biradar");

		WebElement add = driver.findElement(By.id("shipping:street1"));
		add.clear();
		Thread.sleep(3000);
		add.sendKeys("148 Crown Street Road Bidar");

		WebElement city1 = driver.findElement(By.name("shipping[city]"));
		city1.clear();
		Thread.sleep(3000);
		city1.sendKeys("hyderabad");

		WebElement zip = driver.findElement(By.id("shipping:postcode"));
		zip.clear();
		Thread.sleep(3000);
		zip.sendKeys("500001");

		new Select(driver.findElement(By.id("shipping:country_id"))).selectByVisibleText("Australia");
		Thread.sleep(3000);

		WebElement phone1 = driver.findElement(By.id("shipping:telephone"));
		phone1.clear();
		Thread.sleep(3000);
		phone1.sendKeys("123400092");

		driver.findElement(By.xpath("//label[@for='shipping:same_as_billing']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//button[@onclick='shipping.save()']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//button[@onclick='shippingMethod.save()']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//label[@for='p_method_checkmo']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//button[@onclick='payment.save()']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//button[@title='Place Order']")).click();
		Thread.sleep(3000);

		String oderNum = driver.findElement(By.xpath("//div[@class='main-container col1-layout']//p[1]")).getText();
		System.out.println("----- Your Oder Number has been Recored:: " + oderNum);
	}

	@AfterTest
	public void tearDown() throws Throwable {
		Thread.sleep(3000);
		driver.quit();
	}
}
