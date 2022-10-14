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

public class TestCase5 {

	private WebDriver driver;
	private String baseUrl = "http://live.techpanda.org";
	public String firstName = "selenium";
	public String lastName = "automation";

	@BeforeTest
	public void setUp() throws Exception {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void testTestCase5() throws Throwable {
		driver.get(baseUrl);
		driver.findElement(By.linkText("MY ACCOUNT")).click();
		Thread.sleep(2000);

		for (String handel : driver.getWindowHandles()) {
			driver.switchTo().window(handel);
		}
		driver.findElement(By.xpath("//span[contains(text(),'Create an Account')]")).click();
		Thread.sleep(2000);

		for (String handel : driver.getWindowHandles()) {
			driver.switchTo().window(handel);
		}
		// Fill new user name and details
		String IDfrist = "firstname";
		String IDlast = "lastname";
		String IDemail = "email_address";
		String IDpass = "password";
		String IDconfirm = "confirmation";
		String IDsubscr = "is_subscribed";
		String XPATHRegister = "//span[contains(text(),'Register')]";

		driver.findElement(By.id(IDfrist)).sendKeys(firstName);
		driver.findElement(By.id(IDlast)).sendKeys(lastName);
		driver.findElement(By.id(IDemail)).sendKeys("selenium_javaTest@gmail.com");
		driver.findElement(By.id(IDpass)).sendKeys("testing@#1233");
		driver.findElement(By.id(IDconfirm)).sendKeys("testing@#1233");
		driver.findElement(By.id(IDsubscr)).click();
		driver.findElement(By.xpath(XPATHRegister)).click();
		Thread.sleep(2000);

		for (String handle : driver.getWindowHandles()) {
			driver.switchTo().window(handle);
		}
		String vWelcome = ("WELCOME, " + firstName.toUpperCase() + " " + lastName.toUpperCase() + "!");
		String tWelCome = driver.findElement(By.xpath("//*[@id='top']/body/div/div/div[1]/div/p")).getText();
		System.out.println(tWelCome);

		try {
			assertEquals(vWelcome, tWelCome);
		} catch (Exception e) {
			e.printStackTrace();
		}
		driver.findElement(By.xpath("//a[normalize-space()='TV']")).click();
		Thread.sleep(2000);

		for (String handle : driver.getWindowHandles()) {
			driver.switchTo().window(handle);
		}
		driver.findElement(By.xpath("//li/a[@class='link-wishlist']")).click();
		Thread.sleep(2000);

		for (String handle : driver.getWindowHandles()) {
			driver.switchTo().window(handle);
		}
		driver.findElement(By.name("save_and_share")).click();
		Thread.sleep(2000);

		for (String handel : driver.getWindowHandles()) {
			driver.switchTo().window(handel);
		}
		WebElement msgAddreddBox = driver.findElement(By.id("email_address"));
		msgAddreddBox.sendKeys("Tesing_Selenium@gmail.com");
		driver.findElement(By.xpath("//span[contains(text(),'Share Wishlist')]")).click();
		Thread.sleep(2000);

		String VwishList = "Your Wishlist has been shared.";
		String XWishlistshared = "//span[normalize-space()='Your Wishlist has been shared.']";
		String WishList = driver.findElement(By.xpath(XWishlistshared)).getText();
		System.out.println("Wishlist has been shared :: " + WishList);

		try {
			assertEquals(VwishList, WishList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Thread.sleep(3000);
	}

	@AfterTest
	public void tearDown() throws Throwable {
		Thread.sleep(3000);
		driver.quit();

	}

}
