package Error.Console_log;

import java.net.URL;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ConsolelTest {

    private RemoteWebDriver driver;

    @BeforeTest
    public void setup() throws Exception {
        DesiredCapabilities dc = DesiredCapabilities.chrome();
        URL url = new URL("http://172.12.20.118:5555/wd/hub");
        driver = new RemoteWebDriver(url, dc);
    }
    @Test(priority=1)
	public void Homepage_Consolelog() throws InterruptedException
	{
		driver.get("https://apollo2.humanbrain.in/viewer/annotation/portal");
		 driver.manage().window().maximize();
		 System.out.println("Homepage consolelog having.....");
		 Consolelog();
	}
    @Test(priority=2)
    public void login_Consolelog() throws InterruptedException
    {
		 driver.findElement(By.xpath("//a[@title='Viewer']")).click();
		 
		 String parentWindow = driver.getWindowHandle();
		 driver.findElement(By.xpath("//button[text()=' Log In ']")).click();
		 Thread.sleep(4000);
		 Set<String> allWindows = driver.getWindowHandles();
		 for (String window : allWindows) {
			    if (!window.equals(parentWindow)) {
			        driver.switchTo().window(window);
			        break;
			    }
			}
		 Thread.sleep(4000);	
	            driver.findElement(By.xpath("//input[@type='email']")).sendKeys("teamsoftware457@gmail.com");
	            driver.findElement(By.xpath("//span[text()='Next']")).click();
	            Thread.sleep(3000);
	            driver.findElement(By.xpath("//input[@aria-label='Enter your password']")).sendKeys("Health#123");
	            driver.findElement(By.xpath("//span[text()='Next']")).click();
	            Thread.sleep(5000);
	            driver.switchTo().window(parentWindow);
	            Thread.sleep(5000);
	            System.out.println("Login consolelog having......");
	            Consolelog();
	        
	}
	
	@Test(priority=3)
	public void serieset_Consolelog() throws InterruptedException
	{
	     WebElement tableElement = driver.findElement(By.xpath("//table[@class='cdk-table nb-tree-grid']//tr[7]"));
	     tableElement.click();
	     Thread.sleep(3000);
	     WebElement tableElement1=driver.findElement(By.xpath("//table[@class='cdk-table nb-tree-grid']//tr[8]//td[2]"));
	     tableElement1.click();
	     Thread.sleep(3000);
	     driver.findElement(By.xpath("//table[@class='cdk-table nb-tree-grid']//tr[9]//td[3]")).click();
	     Thread.sleep(3000);
	     driver.findElement(By.xpath("(//span[text()='382'])[1]")).click();
	     System.out.println("Seriesset consolelog having .......");
	     Consolelog();
	}
	
    
    @Test(priority=4)
    public void HD_Consolelog() throws InterruptedException
    {
    	driver.findElement(By.xpath("//a[@title='View High Resolution Image']")).click();
    	Thread.sleep(2000);
    	driver.findElement(By.xpath("//a[@title='back']")).click();
    	Thread.sleep(2000);
    	System.out.println("HD consolelog haivng......");
    	Consolelog();
    }
    @Test(priority=5)
    public void Atlas_Consolelog() throws InterruptedException
    {
    	driver.findElement(By.xpath("//a[@title='Atlas Editor']")).click();
    	Thread.sleep(2000);
    	driver.findElement(By.xpath("//a[@title='Back']")).click();
    	Thread.sleep(2000);
    	System.out.println("Atlas Editor console log having......");
    	Consolelog();
    }
    
    @Test(priority=6)
    public void Registration_Consolelog() throws InterruptedException
    {
    	driver.findElement(By.xpath("//a[@title='Manual Registration']")).click();
    	Thread.sleep(5000);
    	driver.findElement(By.xpath("//a[@title='Back']")).click();
    	Thread.sleep(2000);
    	System.out.println("Registration console log having......");
    	Consolelog();
    	
    }
    @Test(priority=7)
    public void Cellannotation_Consolelog() throws InterruptedException
    {
    	driver.findElement(By.xpath("//a[@title='Cell Annotation']")).click();
    	Thread.sleep(3000);
    	driver.findElement(By.xpath("//a[@title='Back']")).click();
    	Thread.sleep(2000);
    	System.out.println("Cell annotation console log having......");
    	Consolelog();
    	
    }

    @AfterTest
    public void close() throws Exception {
        driver.quit();
    }
    
    private  void Consolelog()
    {

        LogEntries entry = driver.manage().logs().get(LogType.BROWSER);
        List<LogEntry> logs = entry.filter(Level.SEVERE); 

        int severeLogCount = logs.size(); 
        System.out.println("Number of SEVERE-level logs: " + severeLogCount);

        for (LogEntry log : logs) {																			
            System.out.println("Level is : \n" + log.getLevel());
            System.out.println("Message is : \n" + log.getMessage());
        }
        
        Assert.assertEquals(severeLogCount, 0, "SEVERE logs were found in the console.");
    }
    
}
