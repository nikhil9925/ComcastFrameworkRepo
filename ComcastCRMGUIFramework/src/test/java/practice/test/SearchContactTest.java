package practice.test;
/**
 * Test class for contact module
 *  @author Nikhil
 * 
 */

import org.testng.annotations.Test;

import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.objectrepositoryutility.LoginPage;

public class SearchContactTest extends BaseClass{
 
	/**
	 * Scenario:login()===>navigateContact===>crateContact()==>verify
	 * 
	 */
	@Test
	public void seatchContactTest() {
		 /* Step 1: Login to app*/
	  LoginPage lp=new LoginPage(driver);
	  lp.loginToApp("username", "password");
	}
}
