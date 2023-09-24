package utils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.IAnnotationTransformer;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.ITestAnnotation;

public class SuiteListener implements ITestListener, IAnnotationTransformer {



	public void onTestFailure(ITestResult result) { 

		//It will snapshot the failed testcases 

		String fileName=System.getProperty("user.dir")+File.separator+"screenshot"+File.
				separator+result.getMethod().getMethodName();

		File destFile=((TakesScreenshot).driver).getScreenshotAs(OutputType.FILE);

		try { FileUtils.copyFile(destFile, new File(fileName+".png")); 

		} 

		catch
		(IOException e) { e.printStackTrace(); 

		} 

	}


	public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {

		annotation.setRetryAnalyzer(RetryAnalyzer.class);

	}

}
