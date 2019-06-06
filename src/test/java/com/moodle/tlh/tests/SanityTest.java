package com.moodle.tlh.tests;

import java.util.LinkedHashMap;
import org.testng.annotations.Test;

public class SanityTest extends BaseTest{
	
	@Test(priority=1,description = "MFD-0000::SanityTest", dataProvider = "getData", dataProviderClass = com.netsuite.tlh.dataprovider.NetsuiteTLHTestDataProvider.class)
	public void VerifySiteLoadsAndlogin(LinkedHashMap<String, ?> testData) throws Throwable {
		System.out.println("1");
		rightNavOperations.getHome();
			
	}
	
	
}
