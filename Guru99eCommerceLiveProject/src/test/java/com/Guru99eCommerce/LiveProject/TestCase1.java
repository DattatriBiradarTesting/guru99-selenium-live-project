package com.Guru99eCommerce.LiveProject;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestCase1 {

	private WebDriver driver;
	private String baseURL = "http://live.techpanda.org/";
	public int scc = 0;
	private StringBuffer verificationErrors = new StringBuffer();

	@BeforeMethod
	@AfterTest
	public void setUp() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void testDay1TestCase1() {
		driver.get(baseURL);
		String demosite = driver.findElement(By.xpath("//h2[1]")).getText();
		System.out.println(demosite);

		try {
			AssertJUnit.assertEquals("This is demo site for", demosite);
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}

		driver.findElement(By.xpath("//a[normalize-space()='Mobile']")).click();
		new Select(driver.findElement(
				By.xpath("//*[@id=\"top\"]/body/div/div/div[2]/div/div[2]/div[1]/div[3]/div[1]/div[1]/div/select")))
				.selectByVisibleText("Name");
		scc = (scc + 1);

		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String png = (".\\Screenshots\\Mobile+ssc" + ".png");
		try {
			FileUtils.copyFile(scrFile, new File(png));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}
