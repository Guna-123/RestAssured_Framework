package api.Endpoints;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.ResourceBundle;

import api.Payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

/*
 Created to perform CRUD Operations - Create,Read,Update,Delete requests to USER API 
 */
public class User_Endpoints2 {
	static ResourceBundle getURL(){
		ResourceBundle routes = ResourceBundle.getBundle("RoutesFile"); // Load Property file
		return routes ;
	}
	public static Response create_User(User Payload) {
		String post_url= getURL().getString("post_url");
		Response response= 
		given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(Payload)
		.when()
			.post(post_url);
		return response ;
	}
	
	public static Response get_User(String userName) {
		String get_url= getURL().getString("get_url");

		Response response= 
		given()
			.pathParam("username", userName)
		.when()
			.get(get_url);
		return response ;
	}
	
	public static Response update_User(User Payload,String userName) {
		String update_url= getURL().getString("update_url");

		Response response= 
		given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(Payload)
			.pathParam("username", userName)
		.when()
			.put(update_url);
		return response ;
	}
	
	public static Response delete_User(String userName) {
		String delete_url= getURL().getString("delete_url");

		Response response= 
		given()
			.pathParam("username", userName)
		.when()
			.delete(delete_url);
		return response ;
	}

}
