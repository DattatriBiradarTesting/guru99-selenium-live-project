package com.eCommerce.LiveProject.EmailUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.mail.MessagingException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestCase10 {
	private WebDriver driver;
	private String baseUrl = "http://live.techpanda.org/index.php/backendlogin";
	private String user = "user01";
	private String pass = "guru99com";

	@BeforeTest
	public void setUp() throws Exception {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
	}

	@Test
	public void testTestCase10() throws Throwable {
		driver.get(baseUrl);
		driver.findElement(By.id("username")).sendKeys(user);
		driver.findElement(By.id("login")).sendKeys(pass);
		driver.findElement(By.xpath("//input[@title='Login']")).click();

		try {
			Thread.sleep(3000);
		} catch (Exception e) {
			e.printStackTrace();
		}

		for (String handle : driver.getWindowHandles()) {
			driver.switchTo().window(handle);
		}
		driver.findElement(By.xpath("//span[contains(text(),'Sales')]")).click();

		try {
			Thread.sleep(3000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		driver.findElement(By.xpath("//span[normalize-space()='Orders']")).click();

		try {
			Thread.sleep(3000);
		} catch (Exception e) {
			e.printStackTrace();
		}

		driver.findElement(By.xpath("//span[contains(text(),'Export')]")).click();

		try {
			Thread.sleep(5000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String FilePath = System.getProperty("user.div") + "\\Downloads\\orders.csv";

		try {
			EmailUtil.emailUtil(FilePath);
		} catch (MessagingException e1) {
			e1.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		File file = new File(FilePath);
		try {
			FileReader read = new FileReader(file);
			BufferedReader buf = new BufferedReader(read);
			String Line = buf.readLine();
			while (Line != null) {
				System.out.println(Line);
				Line = buf.readLine();
			}
			read.close();
			buf.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@AfterTest
	public void tearDown() throws Throwable {
		Thread.sleep(5000);
		driver.quit();
	}
}