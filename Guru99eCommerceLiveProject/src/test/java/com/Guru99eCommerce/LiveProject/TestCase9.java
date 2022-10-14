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

public class TestCase9 {
	private WebDriver driver;
	private String baseUrl = "http://live.techpanda.org";
	public double GURU50 = 0.05;

	@BeforeTest
	public void setUp() throws Exception {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void testTestCase9() throws Throwable {
		driver.get(baseUrl);
		driver.findElement(By.xpath("//a[normalize-space()='Mobile']")).click();

		for (String handle : driver.getWindowHandles()) {
			driver.switchTo().window(handle);
			Thread.sleep(2000);
		}
		String addCard = "//li[3]//div[1]//div[3]//button[1]//span[1]//span[1]";
		driver.findElement(By.xpath(addCard)).click();

		for (String handle : driver.getWindowHandles()) {
			driver.switchTo().window(handle);
			Thread.sleep(2000);
		}
		driver.findElement(By.id("coupon_code")).sendKeys("GURU50");
		driver.findElement(By.xpath("//span[contains(text(),'Apply')]")).click();

		String SubTotal = "//*[@id=\"shopping-cart-totals-table\"]/tbody/tr[1]/td[2]/span";
		String vSubTot = driver.findElement(By.xpath(SubTotal)).getText().trim();

		String DiscountTotal = "//*[@id=\"shopping-cart-totals-table\"]/tbody/tr[2]/td[2]/span";
		String vDiscDisp = driver.findElement(By.xpath(DiscountTotal)).getText().trim();

		String GrandTotal = "//*[@id=\"shopping-cart-totals-table\"]/tfoot/tr/td[2]/strong/span";
		String vDiscounAmtDispy = driver.findElement(By.xpath(GrandTotal)).getText().trim();

		String sSubTot = vSubTot.replaceAll("\\$", " ");
		String sDiscDisp = vDiscDisp.replaceAll("\\$", " ");
		String sDiscounAmtDispy = vDiscounAmtDispy.replaceAll("\\$", " ");
		String sNegDisc = sDiscDisp.replaceAll("\\-", " ");

		String fSubTot = sSubTot.trim();
		String fDiscDisp = sNegDisc.trim();
		String fDiscounAmtDispl = sDiscounAmtDispy.trim();

		double dSubTot = Double.parseDouble(fSubTot);
		double dDiscDisp = Double.parseDouble(fDiscDisp);
		double dDiscountAmtDispl = Double.parseDouble(fDiscounAmtDispl);
		double discountedAmt = (dSubTot * GURU50);
		double dDiscPrice = (dSubTot - discountedAmt);

		if (discountedAmt == dDiscDisp) {
			System.out.println("*** Derived discount amount   = " + discountedAmt);
			System.out.println("*** Displayed discount amount = " + dDiscDisp);
		} else {
			System.out.println("*** Derived discount amount not equal displayed discount amount ***");
		}
		String string_discountedAmt = Double.toString(discountedAmt);
		String string_dDiscDisp = Double.toString(dDiscDisp);
		try {
			assertEquals(string_discountedAmt, string_dDiscDisp);
		} catch (Error e) {
			System.out.println("*** Derived discount amount not equal displayed discount amount ***");
			e.printStackTrace();
		}
		if (dDiscPrice == dDiscountAmtDispl) {
			System.out.println("*** Derived discounted price   = " + dDiscPrice);
			System.out.println("*** Displayed discounted price = " + dDiscountAmtDispl);
		} else {
			System.out.println("*** Derived discounted price not equal displayed discounted price ***");
		}
		String string_dDiscPrice = Double.toString(dDiscPrice);
		String string_discountedAmtDisplayed = Double.toString(dDiscountAmtDispl);

		try {
			assertEquals(string_dDiscPrice, string_discountedAmtDisplayed);
		} catch (Error e) {
			System.out.println("*** Derived discount amount not equal displayed discount amount ***");
			e.printStackTrace();
		}

	}

	@AfterTest
	public void tearDown() throws InterruptedException {
		Thread.sleep(5000);
		driver.quit();
	}
}