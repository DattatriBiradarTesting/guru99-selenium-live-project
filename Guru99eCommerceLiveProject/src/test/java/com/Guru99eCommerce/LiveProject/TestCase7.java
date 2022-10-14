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

public class TestCase7 {
	private WebDriver driver;
	private String baseUrl = "http://live.techpanda.org";
	public String firstName = "selenium test";
	public String lastName = "automation tool";
	public String EmailAdd = "selenium_javaTest@gmail.com";
	public String password = "testing@#1233";

	@BeforeTest
	public void setUp() throws Exception {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void testTestCase7() throws Throwable {
		driver.get(baseUrl);
		driver.findElement(By.linkText("MY ACCOUNT")).click();
		Thread.sleep(5000);

		for (String handle : driver.getWindowHandles()) {
			driver.switchTo().window(handle);
		}
		driver.findElement(By.id("email")).sendKeys(EmailAdd);
		driver.findElement(By.id("pass")).sendKeys(password);
		driver.findElement(By.id("send2")).click();
		Thread.sleep(2000);

		for (String handle : driver.getWindowHandles()) {
			driver.switchTo().window(handle);
		}

		try {
			assertEquals("Recent Orders", driver.findElement(By.cssSelector("h2")).getText());
			System.out.println("** Recent Order is confirmed as the corrent page to be in **");
		} catch (Error e) {
			System.out.println("** Recent Order failed get displayed");
			e.printStackTrace();
		}
		try {
			assertEquals("Pending", driver.findElement(By.cssSelector("em")).getText());
			System.out.println("** Statsu pending is correctly displyed for this recent order*** ");
		} catch (Exception e) {
			System.out.println("** Statuc of Pending failed to be confiremed for this recent order");
			e.printStackTrace();
		}
		driver.findElement(By.linkText("MY ORDERS")).click();
		driver.findElement(By.xpath("//a[normalize-space()='View Order']")).click();

		for (String handle : driver.getWindowHandles()) {
			driver.switchTo().window(handle);

		}

		driver.findElement(By.xpath("//a[@class='link-print']")).click();
	}

	@AfterTest
	public void tearDown() throws Throwable {
		Thread.sleep(2000);
		driver.quit();
	}
}
