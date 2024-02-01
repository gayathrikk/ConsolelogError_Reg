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

public class IITM_MRI_COnsolelog {

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
		driver.get("https://dataportal.iitm.humanbrain.in/viewer/annotation/public");
		 driver.manage().window().maximize();
		 System.out.println("Homepage consolelog having.....");
		 Consolelog();
	}
   
    @AfterTest
    public void close() 
    {
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
