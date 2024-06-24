package api.Test;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.Endpoints.User_Endpoints;
import api.Payload.User;
import api.Utilities.DataProviders;
import io.restassured.response.Response;

public class DataDrivenTest {
	
	@Test (priority = 1 , dataProvider ="Data" , dataProviderClass = DataProviders.class)
	public void test_POST_User(String userId, String userName, String firstName, String lastName ,String userMail,String pwd,String ph ) {
		
		User userPayload = new User() ;
		userPayload.setId(Integer.parseInt(userId));
		userPayload.setUsername(userName);
		userPayload.setFirstName(firstName);
		userPayload.setLastName(lastName);
		userPayload.setEmail(userMail);
		userPayload.setPassword(pwd);
		userPayload.setPhone(ph);
		
		Response r =User_Endpoints.create_User(userPayload);
		r.then().log().body();
		Assert.assertEquals(r.getStatusCode(), 200);
	}
	
	@Test (priority = 2 , dataProvider ="UserNames" , dataProviderClass = DataProviders.class)
	public void test_DELEE_UserByUserName(String userName) {
		
		Response r =User_Endpoints.delete_User(userName);
//		r.then().log().body();
		Assert.assertEquals(r.getStatusCode(), 200);
	}
	
	
	

}
