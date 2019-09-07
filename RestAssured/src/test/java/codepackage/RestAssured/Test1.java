package codepackage.RestAssured;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

public class Test1 {
	
	@Test
	public void API_check() {
		
		System.out.println("First API Test");
		
		RestAssured.baseURI = "https://maps.googleapis.com";
		
		//https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=-33.8670522,151.1957362&radius=1500&type=restaurant&keyword=cruise&key
		
		given().
				param("key","AIzaSyCYBPUcEx9AqEhGNZgQ0RsXw8LX0teuxVU").
				param("location","-33.8670522,151.1957362").
				param("radius","1500").
				param("type","restaurant").
				param("keyword","cruise").
				when().
					get("/maps/api/place/nearbysearch/json").
				then().
					assertThat().statusCode(200).and().
					body("results[0].name", equalTo("Cruise Bar")).and().
					body("results[8].name",equalTo("Mercantile Hotel"));
					//body("results[8].opening_hours.open_now",equalTo("true"));
				
	}

}
