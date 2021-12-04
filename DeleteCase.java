package week1.day1;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DeleteCase {

	public static void main(String[] args) throws InterruptedException, IOException {
		// TODO Auto-generated method stub
		// DISABLE BROWSER NOTIFICATIONS AND LAUNCH THE WEBPAGE
		ChromeOptions options1 = new ChromeOptions();
		options1.addArguments("--disable-notifications");
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver(options1);
		driver.get("https://login.salesforce.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));

		// LOGIN
		driver.findElement(By.xpath("//input[@name='username']")).sendKeys("makaia@testleaf.com");
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("SelBootcamp$1234");
		driver.findElement(By.id("Login")).click();

		// CHOOSE SALES APP FROM APP LAUNCHER
		Thread.sleep(5000);
		driver.findElement(By.xpath("//span[text()='App Launcher']/parent::div")).click();
		driver.findElement(By.xpath("//button[text()='View All']")).click();
		driver.findElement(By.xpath("//p[text()='Sales']")).click();

		// CHOOSE 'CASE' MENU ITEM FROM 'MORE' DROPDOWN
		Thread.sleep(5000);
		driver.findElement(By.xpath("//span[text()='More']")).click();
		Thread.sleep(5000);
		WebElement Case = driver.findElement(By.xpath("//a[@role='menuitem']//span[text()='Cases']"));
		driver.executeScript("arguments[0].click();", Case);

		// SEARCH THE LIST AND SELECT A RECORD FOR DELETION
		Thread.sleep(6000);
		driver.findElement(By.xpath("//input[@placeholder='Search this list...']")).sendKeys("00001444", Keys.ENTER);
		Thread.sleep(6000);
		WebElement ShowActions = driver.findElement(By.xpath("//span[text()='Show Actions']"));
		driver.executeScript("arguments[0].click();", ShowActions);
		driver.findElement(By.xpath("//a[@title='Delete']")).click();
		boolean displayed1 = driver.findElement(By.xpath("//div[text()='Are you sure you want to delete this case?']")).isDisplayed();
		if (displayed1 == true) {
			driver.findElement(By.xpath("//span[text()='Delete']/parent::button")).click();
			String VerifyTxt = driver.findElement(By.xpath("//span[contains(@class,'toastMessage')]")).getText();
			System.out.println(VerifyTxt);

			/*
			 * Thread.sleep(1000);
			 * driver.findElement(By.xpath("//span[text()='success']")).isDisplayed();
			 * driver.findElement(By.xpath("//span[contains(text(),'00001444')]")).
			 * isDisplayed();
			 * driver.findElement(By.xpath("//button[@title='Close']")).isDisplayed();
			 */
			
			File DelScreenshot = driver.getScreenshotAs(OutputType.FILE);
			File Del = new File("./deletion_success.png");
			FileUtils.copyFile(DelScreenshot, Del);
			
			// VERIFY THE LIST FOR THE DELETED RECORD
			driver.findElement(By.xpath("//input[@placeholder='Search this list...']")).clear();
			driver.findElement(By.xpath("//input[@placeholder='Search this list...']")).sendKeys("00001444",Keys.ENTER);
			Thread.sleep(2000);
			boolean displayed2 = driver.findElement(By.xpath("//span[text()='No items to display.']")).isDisplayed();
			if (!displayed2)
				System.out.println("Case not deleted");
			else
			{
				System.out.println("Case deleted successfully");
				File DelScreenshot1 = driver.getScreenshotAs(OutputType.FILE);
				File Del1 = new File("./deletion_verified.png");
				FileUtils.copyFile(DelScreenshot1, Del1);
			}
		} 
		else
			System.out.println("Case not deleted");

		// CLOSE THE BROWSER
		driver.close();

	}

}
