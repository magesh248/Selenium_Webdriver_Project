package utils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer{
//IRetryAnalyzer Interface is to implement a chance to retry a failed test.
	int failedCount =0;
	int retryCount =1;

	public boolean retry(ITestResult result) {

		while(failedCount<retryCount) {
			failedCount++;
			return true;				
		}
		return false;
	}





}
