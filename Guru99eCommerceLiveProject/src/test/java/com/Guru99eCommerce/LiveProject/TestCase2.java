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

public class TestCase2 {
	private WebDriver driver;
	private String baseURL = "http://live.techpanda.org/";

	@BeforeTest
	public void setUp() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void testTestCase2() {
		driver.get(baseURL);
		driver.findElement(By.xpath("//a[normalize-space()='Mobile']")).click();

		String xperiaPrice = driver.findElement(By.cssSelector("#product-price-1 > span.price")).getText();
		System.out.println(xperiaPrice);

		driver.findElement(By.xpath("//*[@id='product-collection-image-1']")).click();

		String DetailsPrice = driver.findElement(By.xpath("//*[@id='product-price-1']/span")).getText();
		System.out.println(DetailsPrice);

		try {
			assertEquals(xperiaPrice, DetailsPrice);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@AfterTest
	public void teatDown() {
		driver.quit();
	}

}
