package com.moodle.tlh.tests;

import java.util.LinkedHashMap;
import org.testng.annotations.Test;
import com.framework.utils.Utility;
import com.netsuite.tlh.operations.Navigator;
import com.netsuite.tlh.testdata.CreateBackupData;


public class MoodleCourseTestSanity extends BaseTest{
	
	@Test(priority=1,description = "MFD-221 ::MFD-222 ::Create a backup and Restore for the course", dataProvider = "getData", dataProviderClass = com.netsuite.tlh.dataprovider.NetsuiteTLHTestDataProvider.class)
	public void CreateABackupAndRestoreTheCourse(LinkedHashMap<String, ?> testData) throws Throwable {
		System.out.println("TCS 1");
		CreateBackupData createBackupData = Utility.getDataPojo(testData.get("Form"), CreateBackupData.class);
		rightNavOperations.acceptSitePolicyAgreement();
		rightNavOperations.getRestoreCoursePage();
		Navigator.doRestore(createBackupData);	
		
	}
	
	@Test(priority=2,description = "MFD-223 ::MFD-264 ::Enrolling the Users", dataProvider = "getData", dataProviderClass = com.netsuite.tlh.dataprovider.NetsuiteTLHTestDataProvider.class)
	public void EnrollingTheUsers(LinkedHashMap<String, ?> testData) throws Throwable {
		System.out.println("TCS 2");
		CreateBackupData createBackupData = Utility.getDataPojo(testData.get("Form"), CreateBackupData.class);
		rightNavOperations.getEnrollParticipantsPage();
		Navigator.GetParticipationOperationsPage().enrollStudent(createBackupData,createBackupData.getUserName1())
		.enrollFacilitator(createBackupData,createBackupData.getUserName2())
		.enrollFacilitationManager(createBackupData,createBackupData.getUserName3())
		.clickOnRespectiveCourse(createBackupData);;
		
	}
	
	
	@Test(priority=3,description = "MFD-224 ::Login as student and Complete the assignment", dataProvider = "getData", dataProviderClass = com.netsuite.tlh.dataprovider.NetsuiteTLHTestDataProvider.class)
	public void LoginAsStudentAndCompleteAssignment(LinkedHashMap<String, ?> testData) throws Throwable {
		System.out.println("TCS 3");
		CreateBackupData createBackupData = Utility.getDataPojo(testData.get("Form"), CreateBackupData.class);
		rightNavOperations.getEnrollParticipantsPage();
		Navigator.GetParticipationOperationsPage().loginAsRespectiveUser(createBackupData.getRole1(),createBackupData.getUserName1());
		rightNavOperations.getAssignmentsPage();
		Navigator.GetAssignmentsOperations().completeAssingments();
		rightNavOperations.getCoursePage(createBackupData);
		menuBarOperations.doLogOut();
		loginOperations.doSecondLogin(userName, passWord);
		
	}
	
	@Test(priority=4,description = "MFD-225 ::MFD-245::MFD-267::Facilitation Dashboard :Grading,Verify Rubic View, Grading Filters", dataProvider = "getData", dataProviderClass = com.netsuite.tlh.dataprovider.NetsuiteTLHTestDataProvider.class)
	public void FacilitationDashboardGradingTheAssignment(LinkedHashMap<String, ?> testData) throws Throwable {
		System.out.println("TCS 4");
		CreateBackupData createBackupData = Utility.getDataPojo(testData.get("Form"), CreateBackupData.class);
		
		rightNavOperations.getFacilitationManagerDashboard();
		Navigator.FacilitationManagerDashboardOperations().gradeAssigment(createBackupData);	
		
	}
	
	
		@Test(priority=5,description = "MFD-226 :: MFD-270::MFD-259:: Sign Off the assignment, Verify the Sign Off Button,sign-off button and process", dataProvider = "getData", dataProviderClass = com.netsuite.tlh.dataprovider.NetsuiteTLHTestDataProvider.class)
		public void FacilitationDashboardSignOffTheAssignment(LinkedHashMap<String, ?> testData) throws Throwable {
			System.out.println("TCS 5");
			CreateBackupData createBackupData = Utility.getDataPojo(testData.get("Form"), CreateBackupData.class);
			rightNavOperations.getFacilitationManagerDashboard();
			Navigator.FacilitationManagerDashboardOperations().verifySignOffFunctionality();
			Navigator.FacilitationManagerDashboardOperations().signOff(createBackupData);		
		}
		
		
	
	@Test(priority=6,description = "MFD-227 :: Deleting the respective course", dataProvider = "getData", dataProviderClass = com.netsuite.tlh.dataprovider.NetsuiteTLHTestDataProvider.class)
	public void DeletingTheRespectiveCourse(LinkedHashMap<String, ?> testData) throws Throwable {
		System.out.println("TCS 6");
		
		rightNavOperations.clickCoursesLink();
		Navigator.GetCoursePageOperations().deleteRespectiveCourse();
		
	}

}
