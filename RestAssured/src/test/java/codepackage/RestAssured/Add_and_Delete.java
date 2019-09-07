package codepackage.RestAssured;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

public class Add_and_Delete {
	
	@Test
	public void addDelete() {
		//first we call the POST api to add the place.
		//in the response we will get place id.
		//using the place id we will call the DELETE api.
		
		//POST to add place
		
		RestAssured.baseURI="http://216.10.245.166";
		
		Response res = given().
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
				assertThat().statusCode(200).and().body("status",equalTo("OK")).
				extract().response();
		
		JsonPath js = new JsonPath(res.asString());// give String input to convert it into Json.
		String placeid = js.get("place_id");
		System.out.println(placeid);
		
		//Use this place id to DELETE.
		
		given().
				queryParam("key","qaclick123").
				body("{"+
				"\"place_id\":\""+placeid+"\""+
				"}").
				when().post("/maps/api/place/delete/json").
				then().
				assertThat().statusCode(200);
		
		
	}

}
