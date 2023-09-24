package page.com;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import com.aventstack.extentreports.ExtentTest;

import baseclass.ProjectBaseClass;

import java.io.IOException;
public class LoginPage extends ProjectBaseClass {

	//CustomWaits sWait =new CustomWaits(driver);
	public LoginPage(RemoteWebDriver driver,ExtentTest node) {

		this.driver=driver;	
		this.node=node;
	}


	By enterUsername=By.id("username");
	By enterPassword=By.id("password");
	By submit=By.id("Login");

	public void setUsername(String username) throws IOException {

		try {
			driver.findElement(enterUsername).sendKeys(username);
			reportStep("Username as enter succesfully", "pass");
		} catch (Exception e) {
			reportStep("Username as enter failed", "fail");
			e.printStackTrace();
		}

	}

	public void setPassword(String password) throws IOException {

		try {
			driver.findElement(enterPassword).sendKeys(password);
			reportStep("Password as enter succesfully", "pass");
		} catch (Exception e) {
			reportStep("Password as enter failed", "fail");
			e.printStackTrace();
		}
	}
	public void submitOn() throws IOException {

		try {
			driver.findElement(submit).click();
			reportStep(" succesfully submitted", "pass");
		} catch (Exception e) {
			reportStep("fail to submit", "fail");
			e.printStackTrace();
		}

	}

	public DashBoardPage loginUser(String username, String password) throws InterruptedException, IOException {

		try {
			setUsername(username);
		} catch (IOException e) {
			e.printStackTrace();
		}
		log.fatal("username entered");

		try {
			setPassword(password);
		} catch (IOException e) {
			e.printStackTrace();
		}
		log.fatal("password entered");
		submitOn();
		log.fatal("submitted");
		log.info("Navigated to Dashboard page ");
		Thread.sleep(7000);
		return new DashBoardPage(driver,node);

	}



}
