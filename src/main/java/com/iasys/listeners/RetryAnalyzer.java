package com.iasys.listeners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

	public class RetryAnalyzer implements IRetryAnalyzer {
		private static final Logger logger = LogManager.getLogger(RetryAnalyzer.class);
		
		private int retryCount =0;
		private static final int MAX_COUNT =1;
		
		@Override
		public boolean retry(ITestResult result) {
			
			if(retryCount<MAX_COUNT) {
				
				logger.warn("Retrying test: "
				        + result.getName()
				        + " Attempt: "
				        + retryCount);
				
				retryCount++;
				return true;
			}
			
			return false;
		
	}

}
