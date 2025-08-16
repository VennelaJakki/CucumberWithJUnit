package com.qa.opencart.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer{
private int count=0;
private int maxCount=3;
	@Override
	public boolean retry(ITestResult result) {
		if(!result.isSuccess()) { // Check if test is not passed
			if(count<maxCount) {// Check if max count is reached
				count++;// Increase the max count by 1
				result.setStatus(result.FAILURE);// Mark test as failed
				return true;
			}else {
				result.setStatus(result.FAILURE);
				// If maxCount reached,test marked as failed
			}
			
		}else {
			result.setStatus(result.SUCCESS);
			// If test passes, TestNG marks it as passed
		}	
		
		
		
		return false;
	}

}
