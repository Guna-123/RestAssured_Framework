package api.Endpoints;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import api.Payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

/*
 Created to perform CRUD Operations - Create,Read,Update,Delete requests to USER API 
 */
public class User_Endpoints {
	public static Response create_User(User Payload) {
		Response response= 
		given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(Payload)
		.when()
			.post(Routes.post_url);
		return response ;
	}
	
	public static Response get_User(String userName) {
		Response response= 
		given()
			.pathParam("username", userName)
		.when()
			.get(Routes.get_url);
		return response ;
	}
	
	public static Response update_User(User Payload,String userName) {
		Response response= 
		given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(Payload)
			.pathParam("username", userName)
		.when()
			.put(Routes.update_url);
		return response ;
	}
	
	public static Response delete_User(String userName) {
		Response response= 
		given()
			.pathParam("username", userName)
		.when()
			.delete(Routes.delete_url);
		return response ;
	}

}
