package page.com;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import com.aventstack.extentreports.ExtentTest;

import baseclass.ProjectBaseClass;

public class DashBoardPage extends ProjectBaseClass   {
	
	
public DashBoardPage(RemoteWebDriver driver,ExtentTest node) {
		
		this.driver=driver;	
		this.node=node;
	}
	
	public DashBoardPage AppLauncher() {
		
		 WebElement appLaunch = driver.findElement(By.xpath("//div[@class='slds-r4']"));
		 appLaunch.click();
		 return this;
	}
	
	public DashBoardPage viewAll() throws InterruptedException, IOException {	
		Thread.sleep(2000);
		try {
			driver.findElement(By.xpath("//button[text()='View All']")).click();
			reportStep("View All is clicked", "pass");
		} catch (Exception e) {
			reportStep("View All is not clicked", "fail");
			e.printStackTrace();
		}	
		return this;
		
	}
	public DashBoardPage Searchbar(String keyword) throws InterruptedException{
		Thread.sleep(2000);
	WebElement clicksearchKeyword=driver.findElement(By.xpath("//input[@class='slds-input']"));
	clicksearchKeyword.sendKeys(keyword);
	return this;
		
	}
	public void ClickSearchedContent() throws InterruptedException {
		Thread.sleep(2000);
		try {
			driver.findElement(By.xpath("//p[@class='slds-truncate']")).click();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public DashBoardPage ChatterMenu() throws InterruptedException {
		Thread.sleep(2000);
	WebElement chatterMenu = driver.findElement(By.xpath("//a[@title='Chatter']"));
	driver.executeScript("arguments[0].click()", chatterMenu);
	return this;
	}

}
