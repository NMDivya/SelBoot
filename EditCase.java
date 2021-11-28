package week1.day1;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class EditCase {

	public static void main(String[] args) throws InterruptedException {
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
		
	//CHOOSE 'CASE' MENU ITEM FROM 'MAIN MENU' 
		Thread.sleep(5000);
		boolean displayed = driver.findElement(By.xpath("//a[@title='Cases']//span[text()='Cases']")).isDisplayed();
		if (displayed==true)
			driver.findElement(By.xpath("//a[@title='Cases']//span[text()='Cases']")).click();
		
	//IF ABOVE SCENARIO FAILS THEN CHOOSE 'CASE' MENU ITEM FROM 'MORE' DROPDOWN
		else
		{
		//Thread.sleep(5000);
		System.out.println("'Case' tab not listed");
		driver.findElement(By.xpath("//span[text()='More']")).click();
		Thread.sleep(5000);
		/*WebElement More = driver.findElement(By.xpath("//span[text()='More']/ancestor::one-app-nav-bar-menu-button"));
		driver.executeScript("arguments[0].click();", More);
		driver.findElement(By.xpath("//span[text()='More']/ancestor::one-app-nav-bar-menu-button")).click();*/
		
		//driver.findElement(By.xpath("//a[@role='menuitem']/following::span[text()='Cases']")).click();
		WebElement Case = driver.findElement(By.xpath("//a[@role='menuitem']//span[text()='Cases']"));
		driver.executeScript("arguments[0].click();", Case);
		}
		
		String CaseOwner = driver.findElement(By.xpath("(//button[@title='Locked Case Owner Alias: Item 1']/parent::span)/preceding-sibling::span")).getText();
		if(CaseOwner.equals("DDsou"))
		{
		//WebElement ShowActions = driver.findElement(By.xpath("//a[@title='Show 3 more actions']"));
		WebElement ShowActions = driver.findElement(By.xpath("(//span[text()='Show Actions'])[1]"));
		driver.executeScript("arguments[0].click();", ShowActions);
		driver.findElement(By.xpath("//a[@title='Edit']")).click();
		}
		else
			System.out.println("Case Owner NOT found");
		
	//EDIT VALUES OF THE SELECTED ENTRY AND SAVE
		//Get the Case number for verification
		Thread.sleep(5000);
		String CaseNum = driver.findElement(By.xpath("(//span[text()='Case Number']/following::span[@class='uiOutputText'])[2]")).getText();
		System.out.println("CaseNum="+CaseNum);
		
		WebElement SelectStatus = driver.findElement(By.xpath("//label[text()='Status']/following::input"));
		driver.executeScript("arguments[0].click();", SelectStatus);
		driver.findElement(By.xpath("//span[text()='Working']")).click();
		
		WebElement SelectPriority = driver.findElement(By.xpath("//span[text()='Priority']/following::a"));
		driver.executeScript("arguments[0].click();", SelectPriority);
		driver.findElement(By.xpath("//a[@title='Low']")).click();
		
		WebElement SelectCaseOrigin = driver.findElement(By.xpath("//span[text()='Case Origin']/following::a"));
		driver.executeScript("arguments[0].click();", SelectCaseOrigin);
		driver.findElement(By.xpath("//a[@title='Phone']")).click();
		
		WebElement SelectSLA = driver.findElement(By.xpath("//span[text()='SLA Violation']/following::a[text()='--None--']"));
		driver.executeScript("arguments[0].click();", SelectSLA);
		driver.findElement(By.xpath("//a[@title='No']")).click();
		
		driver.findElement(By.xpath("//button[@title='Save']")).click();
		
	//SEARCH THE LIST AND VERIFY IF THE STATUS IS UPDATED
		Thread.sleep(6000);
		driver.findElement(By.xpath("//input[@placeholder='Search this list...']")).sendKeys(CaseNum,Keys.ENTER);
		String Status = driver.findElement(By.xpath("(//span[@data-aura-class='forceInlineEditCell'])[3]/span")).getText();
		System.out.println(Status);
		if(Status.equals("Working"))
			System.out.println("Status Updated as Working");
		else
			System.out.println("Status NOT Updated as Working");
		
	// CLOSE THE BROWSER
		driver.close();
	}

}
