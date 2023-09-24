package testCases;
import java.io.IOException;

import org.testng.annotations.Test;

import baseclass.ProjectBaseClass;
import page.com.DashBoardPage;
import page.com.LoginPage;

public class TC001Login extends ProjectBaseClass {
	
	
	LoginPage login; 
	DashBoardPage dash;
	
	@Test(priority = 0,enabled = true)
	public void positiveUserLoginTest() throws InterruptedException, IOException {
		login=new LoginPage(driver,node);
		propertyFiles();
		String uName =property.getProperty("uName");
		String pName =property.getProperty("pName");
		login.loginUser(uName, pName);
		Dashboard();
		log.warn("Chatter menu is tested successfully");
	}
	
   @Test(priority = 1,enabled = true)
	public void negativeUserLoginTest() throws InterruptedException, IOException {
    	login=new LoginPage(driver,node);
		propertyFiles();
		String uName =property.getProperty("uName");
		String IncorectPname =property.getProperty("IncorectPname");
		login.loginUser(uName, IncorectPname);
	}
    
    public void Dashboard() throws InterruptedException, IOException {
		dash=new DashBoardPage(driver,node);
		log.info("AppLaucher is clicked");
		dash.AppLauncher().viewAll().Searchbar("content").ClickSearchedContent();
		dash.ChatterMenu();
		
	}
	
    

}
