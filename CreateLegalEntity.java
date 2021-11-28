package week1.day1;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateLegalEntity {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// DISABLE BROWSER NOTIFICATIONS AND LAUNCH THE WEBPAGE
		ChromeOptions options1 = new ChromeOptions();
		options1.addArguments("--disable-notifications");
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver(options1);
		driver.get("https://login.salesforce.com/");
		driver.manage().window().maximize();
		//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);// deprecated-not a good practice to use
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

		// LOGIN
		driver.findElement(By.xpath("//input[@name='username']")).sendKeys("makaia@testleaf.com");
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("SelBootcamp$1234");
		driver.findElement(By.id("Login")).click();

		// CHOOSE LEGAL ENTITY APP FROM APP LAUNCHER
		driver.findElement(By.className("slds-icon-waffle")).click();
		driver.findElement(By.xpath("//button[text()='View All']")).click();
		WebElement legal = driver.findElement(By.xpath("//p[text()='Legal Entities']"));
		driver.executeScript("arguments[0].scrollIntoView();", legal);
		driver.findElement(By.xpath("//p[text()='Legal Entities']")).click();
		
		//CREATE NEW LEGAL ENTITY FROM DROPDOWN
		driver.findElement(By.xpath("//a[contains(@title,'Legal Entities')]/following::one-app-nav-bar-item-dropdown")).click();
//		driver.findElement(By.xpath("//span[text()='New Legal Entity']")).click();-->JavascriptException: javascript error: Cannot read properties of undefined 
		WebElement newlegal = driver.findElement(By.xpath("//span[text()='New Legal Entity']"));
		driver.executeScript("arguments[0].click();", newlegal);
		
		//ENTER VALUES IN THE CREATE NEW ENTITY WINDOW
		driver.findElement(By.xpath("//span[text()='Company Name']/following::input")).sendKeys("TESTLEAF");
		driver.findElement(By.xpath("//span[text()='Description']/following::TEXTAREA")).sendKeys("SalesForce");
		WebElement SelectStatus = driver.findElement(By.linkText("--None--"));
		driver.executeScript("arguments[0].click();", SelectStatus);
		driver.findElement(By.linkText("Active")).click();
		driver.findElement(By.xpath("//button[@title='Save']")).click();
		boolean AlertMsg = driver.findElement(By.xpath("//li[text()='Complete this field.']")).isDisplayed();
		if(AlertMsg==true)
			System.out.println("Alert message displayed");
		else
			System.err.println("Alert message not displayed");
		
		// CLOSE THE BROWSER
		driver.close();
	}

}
