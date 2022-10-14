package com.Guru99eCommerce.LiveProject;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestCase4 {
	private WebDriver driver;
	private String baseUrl = "http://live.techpanda.org";

	@BeforeTest
	public void setUp() throws Exception {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
	}

	@Test
	public void testTestCase4() throws Throwable {
		// 1.Go to http://live.techpanda.org
		driver.get(baseUrl);

		// 2.Click on mobile Menu
		driver.findElement(By.xpath("//a[normalize-space()='Mobile']")).click();

		// 3.In mobile Product list ,click on "Add To compare" for TWO mobile (Iphone
		// &SonyXperia)
		
		Thread.sleep(1000);
		driver.findElement(By.xpath("//li[3]//div[1]//div[3]//ul[1]//li[2]//a[1]")).click();
		String mainMobile1 = driver.findElement(By.xpath("//a[@title='IPhone'][normalize-space()='IPhone']")).getText();
		System.out.println("mainMobile1::" + mainMobile1);
		Thread.sleep(1000);
		
		driver.findElement(
				By.xpath("//*[@id='top']/body/div/div/div[2]/div/div[2]/div[1]/div[3]/ul/li[2]/div/div[3]/ul/li[2]/a"))
		.click();
		String mainMobile2 = driver.findElement(By.xpath("//a[@title='Sony Xperia']")).getText();
		System.out.println("mainMobile2::" + mainMobile2);
		Thread.sleep(1000);

		// 4.Click on popup window opens"Compare"
		driver.findElement(By.xpath("//button[@title='Compare']")).click();

		// 5.switching to new window

		for (String handle : driver.getWindowHandles()) {
			driver.switchTo().window(handle);
			driver.manage().window().maximize();
		}
		Thread.sleep(5000);
		String strHead = ("COMPARE PRODUCTS");
		String compHead = driver.findElement(By.xpath("//*[@id=\"top\"]/body/div/div[1]/h1")).getText();
		System.out.println(compHead);
		Thread.sleep(5000);

		String popupMobile1 = driver.findElement(By.xpath("//a[normalize-space()='IPhone']")).getText();
		String popupMobile2 = driver.findElement(By.xpath("//a[normalize-space()='Sony Xperia']")).getText();
		
		System.out.println("The Iphone titile::" + popupMobile1);
		System.out.println("The sony Xpreai title:: " + popupMobile2);
		Thread.sleep(5000);

		try {
			assertEquals(strHead, compHead);
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			assertEquals(mainMobile1, popupMobile1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			assertEquals(mainMobile2,popupMobile2);
		}catch(Exception e) {
			e.printStackTrace();
		}
		Thread.sleep(5000);
		driver.findElement(By.xpath("//span[contains(text(),'Close Window')]")).click();
		Thread.sleep(5000);
		
		for (String handle : driver.getWindowHandles()) {
			driver.switchTo().window(handle);
		}
	}

	@AfterTest
	public void tearDown() throws Throwable {
		Thread.sleep(5000);
		driver.quit();
	}
}
