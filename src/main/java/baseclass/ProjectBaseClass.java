package baseclass;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class ProjectBaseClass{

	public RemoteWebDriver driver;
	public Properties property;
	public static Logger log;
	public ExtentReports extent;
	public ExtentTest test,node;
	public String testName,testDesc,testAuthor,testCategory;

	//TODO: Will help to fetch data from properties file
	public Properties propertyFiles()  {
		try {
			//FileInputStrem will read the file location
			FileInputStream fileReadLocation =new FileInputStream("./files/config.properties");
			property =new Properties();
			property.load(fileReadLocation);
		} catch (FileNotFoundException e) {
			System.err.println(e.getMessage());  
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
		return property;

	}
	@BeforeTest
	//set data for extent report
	public void testData() {
		testName ="SalesForce";
		testDesc ="Login";
		testAuthor ="Magesh";
		testCategory ="Smoke";
	}


	@BeforeSuite 
	public void startReport()
	{
		ExtentHtmlReporter report =new ExtentHtmlReporter("./Report/result.html");
		report.setAppendExisting(true);
		extent =new ExtentReports();
		extent.attachReporter(report);
	}

	@AfterSuite
	public void stopReport() {		
		extent.flush();
	}

	@BeforeClass
	public void testDetails()
	{
		test= extent.createTest(testName,testDesc);
		test.assignAuthor(testAuthor);
		test.assignCategory(testCategory);
	}


	public void reportStep(String message, String status) throws IOException {

		if(status.equalsIgnoreCase("pass")) {
			node.pass(message, MediaEntityBuilder.createScreenCaptureFromPath(".././screenshot/img"+takeSnap()+".png").build());
		}
		else if (status.equalsIgnoreCase("fail")) {
			node.fail(message, MediaEntityBuilder.createScreenCaptureFromPath(".././screenshot/img"+takeSnap()+".png").build());
		}

	}

	public int takeSnap() {

		//math.random method is used to create new integer for every run so it will be usefull for save image in unique ID

		int random =(int) (Math.random()*999999);
		File sourFile=driver.getScreenshotAs(OutputType.FILE);
		File desFile=new File("./screenshot/img"+random+".png");
		try {
			FileUtils.copyFile(sourFile, desFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return random;
	}


	@BeforeMethod
	public void BrowserLauch() throws IOException {

		/*Node is subset of extent set if pass and fail run parallel in single driver throws nullPointer Exepection
		 * so we are created node
		 */
		node = test.createNode(testName);
		log=LogManager.getLogger(ProjectBaseClass.class);
		//Calling propertyFiles() function to get the data
		propertyFiles();

		//This will conifgure the log4j properties file
		PropertyConfigurator.configure("log4j.properties");

		//get property is to read the specific data
		String URL=property.getProperty("URL");	
		String getBrowser=property.getProperty("Browser");
		setupDriver(getBrowser);
		driver.get(URL);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		log.info("Navigated to URl successfully");
		log.info("Logged into Application");
		driver.manage().window().maximize();
		log.info("Window maximizes");
		driver.manage().deleteAllCookies();

	}

	//TODO: Will help to trigger browser depending upon user selection
	public void setupDriver(String Browser) {
		if (Browser.equalsIgnoreCase("Chrome")) 
		{	
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--disable-notifications");
			driver=new ChromeDriver(options);		
		}
		else if (Browser.equalsIgnoreCase("Edge")) 
		{
			EdgeOptions options = new EdgeOptions();
			options.addArguments("--disable-notifications");
			driver=new EdgeDriver(options);
		}
		else if (Browser.equalsIgnoreCase("FireFox")) 
		{
			driver =new FirefoxDriver();
		}
		else
		{
			System.out.println(driver);
			log.error("Driver not supported!!!");
		}

	}

	@AfterMethod
	public void tearDown() {

		driver.quit();
		log.info("Application closed succesfully");
	}
}






