package api.Test;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.Endpoints.User_Endpoints;
import api.Payload.User;
import io.restassured.response.Response;

public class UserTestclass {
	Faker faker ;
	User userPayload ;
	public Logger logger ;
	
	@BeforeClass
	public void setup_TestData() {
		faker=new Faker();
		userPayload=new User();
		
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5, 10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		//Logs
		logger = LogManager.getLogger(this.getClass());
	}
	
	
	
	
	@Test (priority = 1)
	public void Test_createUser() {
		logger.info("*********** Creating User *************");
		Response r =User_Endpoints.create_User(userPayload);
		r.then().log().all();
		r.then().log().body();
		
		Assert.assertEquals(r.getStatusCode(), 200);
		logger.info("***********  User Created *************");

	}
	
	@Test (priority = 2)
	public void Test_GetUser() {
		logger.info("***********  Getting User Details *************");

//		Response r =User_Endpoints.get_User(this.userPayload.getUsername());

		Response r =User_Endpoints.get_User(userPayload.getUsername());
		r.then().log().all();
//		r.statusCode();
		Assert.assertEquals(r.getStatusCode(), 200);
		logger.info("***********  User Info is Displayed *************");

	}
	@Test (priority = 3)
	public void Test_UpdateUser() {
		logger.info("*********** Updating User  Details *************");

//		Update data using PayLoad
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		
		Response r =User_Endpoints.update_User(userPayload, userPayload.getUsername());
		r.then().log().body().statusCode(200);  // CHAI Assertion

		Assert.assertEquals(r.getStatusCode(), 200);
		
		//Checking data after Update
		Response responseAfterUpdate =User_Endpoints.get_User(userPayload.getUsername());
		responseAfterUpdate.then().log().all();

		Assert.assertEquals(r.getStatusCode(), 200); 
		logger.info("***********  User  Details Updated *************");

	}
	@Test (priority = 4)
	public void Test_DeleteUser() { 
		logger.info("*********** deleting User  Details *************");

		Response r =User_Endpoints.delete_User(userPayload.getUsername());
		r.then().log().all();
		Assert.assertEquals(r.getStatusCode(), 200);
		logger.info("***********  User  Details Deleted *************");

	}

}
