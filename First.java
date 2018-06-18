import java.awt.Desktop.Action;
import java.awt.List;
import java.util.ArrayList;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class First {
	public static void main (String[] args) throws InterruptedException 
	{
	System.setProperty("webdriver.chrome.driver", "C:\\Users\\arpitmittal\\chromedriver.exe");
	WebDriver driver = new ChromeDriver();
	
	driver.get("http://10.0.1.86/tatoc");
	driver.findElement(By.cssSelector("a")).click();
	driver.findElement(By.className("greenbox")).click();
	WebDriverWait wait = new WebDriverWait(driver, 10);
	wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("main")));

	String result = driver.findElement(By.id("answer")).getAttribute("class");
	Boolean condition = true;
	while(condition) {
		driver.findElement(By.cssSelector("body > center > a:nth-child(7)")).click();
		driver.switchTo().frame(driver.findElement(By.id("child")));
		String secondresult = driver.findElement(By.id("answer")).getAttribute("class");
		driver.switchTo().parentFrame();
		
		if(result.equals(secondresult))
		{
			condition = false;
		}
		
		}
	
	driver.findElement(By.cssSelector("body > center > a:nth-child(9)")).click();
	driver.switchTo().defaultContent();

	WebElement From=driver.findElement(By.xpath("//*[@id='dragbox']"));
	WebElement To=driver.findElement(By.xpath("//*[@id='dropbox']"));
	Actions act=new Actions(driver);
	
	act.dragAndDrop(From, To).build().perform();	
	driver.findElement(By.cssSelector("body > div > div.page > a")).click();
	driver.findElement(By.cssSelector("body > div > div.page > a:nth-child(4)")).click();


	ArrayList<String> str = new ArrayList<String> (driver.getWindowHandles());
	driver.switchTo().window(str.get(1));
	driver.findElement(By.cssSelector("#name")).sendKeys("Arpit");
	driver.findElement(By.cssSelector("#submit")).click();
	 driver.switchTo().window(str.get(0));
	    driver.findElement(By.cssSelector("body > div > div.page > a:nth-child(6)")).click();
	
	    driver.findElement(By.xpath("//a[contains(text(),'Generate Token')]")).click();
	   
	   // driver.findElement(By.cssSelector("body > div > div.page > a:nth-child(8)")).click();
	 
	  String s=driver.findElement(By.xpath("//*[@id='token']")).getText();
	  System.out.println(s);
	  String[] tokenValue= s.split("\\s");//
	  System.out.println(tokenValue[1]);
	  String token= tokenValue[1];
	  Cookie cookie= new Cookie("Token",token);
	  driver.manage().addCookie(cookie);
	driver.findElement(By.xpath("//a[contains(text(),'Proceed')]")).click();
	   

	}

}


