package Error.Console_log;

import java.util.Set;



import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;

import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.logging.LogEntries;

import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;



public class pp7v15_consolelog {
	


	 private RemoteWebDriver driver;
		
		@BeforeTest
		
		public void setup() throws MalformedURLException 
		{
			
			  DesiredCapabilities dc = DesiredCapabilities.chrome();
		        URL url = new URL("http://172.20.23.7:5555/wd/hub");
		        driver = new RemoteWebDriver(url, dc);
		
		       
		}
		//@Param

	@Test(priority=1)

	public void Login() throws InterruptedException {

		driver.get("http://apollo2.humanbrain.in/");
		driver.manage().window().maximize();

		WebDriverWait wait = new WebDriverWait(driver, 20);

		WebElement viewerSectionLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@class, 'ng-tns-c93-3')]")));

	    viewerSectionLink.click();

	 

		String parentWindow = driver.getWindowHandle();

		WebDriverWait wait1 = new WebDriverWait(driver, 20);

		WebElement login = wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()=' Log In ']")));

	    login.click();

		

		Thread.sleep(4000);

		Set<String> allWindows = driver.getWindowHandles();

		for (String window : allWindows) {

			if (!window.equals(parentWindow)) {

				driver.switchTo().window(window);

				break;

			}

		}

		Thread.sleep(4000);

		WebDriverWait wait2 = new WebDriverWait(driver,20);

		WebElement  emailInput = wait2.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='email']")));

		emailInput.sendKeys("teamsoftware457@gmail.com");

		WebDriverWait wait3 = new WebDriverWait(driver,20);

		WebElement  Next = wait3.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Next']")));

		Next.click();

		WebDriverWait wait4 = new WebDriverWait(driver,20);

		WebElement  PasswordInput = wait4.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='password']")));

		PasswordInput.sendKeys("Health#123");

		WebDriverWait wait5 = new WebDriverWait(driver,20);

		WebElement  Next2 = wait5.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Next']")));

		Next2.click();		

		Thread.sleep(5000);

		driver.switchTo().window(parentWindow);

		Thread.sleep(5000);
		

	}

	@Test (priority=2)

	public void Dashboard() {

	try {

		WebDriverWait wait6 = new WebDriverWait(driver,30);

		WebElement table1 = wait6.until(ExpectedConditions.elementToBeClickable(By.xpath("//img[@src='/viewer/assets/images/colorsvg/cellreports.svg']")));

		table1.click();

		System.out.println("The Dashboard is clicked");

		}

		catch (Exception e) {

			System.out.println("The Dashboard is not clicked");

		}

		try {

		WebDriverWait wait7 = new WebDriverWait(driver,30);

		WebElement table2 = wait7.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@class='value outcol click'])[3]")));

		table2.click();

		System.out.println("Total out  is clicked");

		}

		catch(Exception e) {

			System.out.println("Total out is not clicked");

		}

		try {

			WebDriverWait wait8 = new WebDriverWait(driver,30);

			WebElement table3 = wait8.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='https://apollo2.humanbrain.in/viewer/annotation/imaging/IIT/V1/SB-2498']")));

			table3.click();

			System.out.println("The Batch viewer is clicked");

			}

			catch(Exception e) {

				System.out.println("The Batch viewer not clicked");

			}

	}

	@Test(priority=3)

	public void console() {

	    LogEntries logEntries = driver.manage().logs().get("browser");

	    

	    for (LogEntry entry : logEntries) {

	        if (entry.getLevel().equals(java.util.logging.Level.SEVERE)) {

	            // Print only error messages (level.SEVERE)

	            System.out.println(new Date(entry.getTimestamp()) + " " + entry.getLevel() + " " + entry.getMessage());

	            String errorLogType = entry.getLevel().toString();

	            String errorLog = entry.getMessage().toString();

	            

	            if (errorLog.contains("404")) {

	                System.out.println("Error LogType: " + errorLogType + " Error Log message: " + errorLog);

	            }

	        }

	    }

	}
	@AfterClass
    public void tearDown() {
        driver.quit();
    }


	

}
