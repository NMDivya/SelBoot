package week1.day1;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.*;
//import org.openqa.selenium.WebDriver.Options.*;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.support.ui.*;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class NewOpportunity {

	//@SuppressWarnings("deprecation")
	public static void main(String[] args)throws InterruptedException {
		// TODO Auto-generated method stub
		// DISABLE BROWSER NOTIFICATIONS AND LAUNCH THE WEBPAGE
		ChromeOptions options=new ChromeOptions();
		options.addArguments("--disable-notifications");
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver(options);
		driver.get("https://login.salesforce.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		
		//LOGIN
		//Thread.sleep(3000);
		//driver.findElementById("username").sendKeys("makaia@testleaf.com");
		driver.findElement(By.id("username")).sendKeys("makaia@testleaf.com");
		driver.findElement(By.id("password")).sendKeys("SelBootcamp$1234");
		driver.findElement(By.id("Login")).click();
		
		//CHOOSE SALES APP FROM APP LAUNCHER
		//Thread.sleep(20000);
		driver.findElement(By.className("slds-icon-waffle")).click();
		//Thread.sleep(10000);
		driver.findElement(By.xpath("//button[text()='View All']")).click(); 
		//Thread.sleep(10000);
		driver.findElement(By.xpath("//p[text()='Sales']")).click();
		
		//SELECT CREATE NEW OPPORTUNITY
		WebElement opportunity =driver.findElement(By.xpath("//a[@title='Opportunities']"));
		//***********JavascriptExecutor executor = (JavascriptExecutor) driver; //type casted********
		driver.executeScript("arguments[0].click();", opportunity);
		//driver.findElement(By.xpath("//a[@title='Opportunities']/span")).click(); 
		//driver.findElement(By.xpath("//span[text()='Opportunities']")).click();
		driver.findElement(By.xpath("//div[text()='New']")).click();
		/*driver.findElement(By.xpath("//lightning-icon[@class='slds-icon-utility-chevrondown slds-icon_container']/lightning-primitive-icon")).click();
		driver.findElement(By.xpath("//span[text()='New Opportunity']")).click();*/
		//Thread.sleep(10000);
		
		//CREATE NEW OPPORTUNITY BY ENTERING VALUES AND SAVE
		driver.findElement(By.xpath("//input[@name='Name']")).sendKeys("SalesForce Automation By Divya");
		/*String OppName=new String();
		OppName=driver.getAttribute();
		System.out.println(OppName);*/
		//driver.findElement(By.xpath("//input[@name='CloseDate']")).sendKeys("11/18/2021");
		WebElement calender = driver.findElement(By.xpath("//button[@title='Select a date']"));
		driver.executeScript("arguments[0].click();", calender);
		driver.findElement(By.xpath("//td[@class='slds-is-today']")).click();
		
				//driver.findElement(By.xpath("//label[text()='Stage']/following::input")).click();
		WebElement Stage = driver.findElement(By.xpath("(//input[@class='slds-input slds-combobox__input'])[3]"));
		driver.executeScript("arguments[0].click();", Stage);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//span[text()='Needs Analysis']/ancestor::lightning-base-combobox-item")).click();
		/*WebElement Needs =driver.findElement(By.xpath("//span[text()='Needs Analysis']/ancestor::lightning-base-combobox-item"));
		JavascriptExecutor executor1 = (JavascriptExecutor) driver;
	    executor1.executeScript("arguments[0].click();", Needs);*/
	    driver.findElement(By.xpath("//button[text()='Save']")).click();
	    
	   	/*Select dropdown= new Select(Needs);
		dropdown.selectByVisibleText("Needs Analysis");*/
		//String Today=driver.findElement(By.xpath("//td[contains(@class,'today')]")).getAttribute();--> doubt
		//driver.findElement(By.xpath("//span[text()='Needs Analysis']/ancestor::lightning-base-combobox-item")).click();
		
		//VERIFY IF NEW OPPORTUNITY IS CREATED
		WebElement verify = driver.findElement(By.xpath("//span[text()='Opportunity']//div"));
	    String VerifyTxt = verify.getAttribute("title");
		//"SalesForce Automation By Divya"
		//New Opportunity not created successfully
		
		//String VerifyTxt = verify.getText();--> blank value
		System.out.println(VerifyTxt);
		if (VerifyTxt.contains("SalesForce Automation By Divya"))
			System.out.println("New Opportunity created successfully");
		else
			System.out.println("New Opportunity not created successfully");
		
		//CLOSE THE BROWSER
		driver.close();
	}

}
