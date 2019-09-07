package codepackage.RestAssured;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.equalTo;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

public class Test2_Post {
	
	@Test
	public void addPlace() {
		RestAssured.baseURI= "http://216.10.245.166";
		
		
		Response res =	given().
				queryParam("key","qaclick123").
				body("{"+
				"\"location\":{"+
				"\"lat\":-33.8669710,"+
				"\"lng\":151.1958750"+
				"},"+
				"\"accuracy\":50,"+
				"\"name\":\"Google Shoes!\","+
				"\"phone_number\":\"(02) 9374 4000\","+
				"\"address\":\"123\","+
				"\"types\":[\"shoe_store\"],"+
				"\"website\":\"http://www.google.com.au/\","+
				"\"language\":\"en-AU\""+
				"}").
				when().
				post("/maps/api/place/add/json").
				then().
				assertThat().statusCode(200).and().body("status", equalTo("OK")).
				extract().response();
		
		System.out.println(res.asString());
		
		
	}
}
