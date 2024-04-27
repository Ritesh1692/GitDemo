package demo;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddLocation;
import pojo.Location;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;
public class SpecificationTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		AddLocation ad=new AddLocation();
		Location location=new Location();
		location.setLat(-88.383494);
		location.setLng(33.427362);
		ad.setLocation(location);
		
		ad.setAccuracy(54);
		ad.setName("Frontline house");
		ad.setPhone_number("(+91) 983 893 3937");
		ad.setAddress("29, side layout, cohen 09");
		
		List<String> type=new ArrayList<String>();
		type.add("shoe park");
		type.add("shop");
		ad.setTypes(type);
		
		ad.setWebsite("http://google.com");
		ad.setLanguage("French-IN");
		
		RequestSpecification reqspec=new RequestSpecBuilder().
	    setBaseUri("https://rahulshettyacademy.com").addQueryParam("key", "qaclick123")
	    .setContentType(ContentType.JSON).build();
		
		ResponseSpecification respSpec=new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		
		
		RequestSpecification respgiven=given().spec(reqspec).body(ad);
		
		Response whenResp=respgiven.when().post("/maps/api/place/add/json")
		.then().spec(respSpec).extract().response();
		
		System.out.println(whenResp.asString());
		
		
		
		/*
		 * List<String> type=new ArrayList<String>(); type.add("shoe park");
		 * type.add("shop"); ad.setTypes(type);
		 * 
		 * ad.setWebsite("http://google.com"); ad.setLanguage("French-IN");
		 * 
		 * RequestSpecification reqspec=new RequestSpecBuilder().
		 * setBaseUri("https://rahulshettyacademy.com").addQueryParam("key",
		 * "qaclick123") .setContentType(ContentType.JSON).build();
		 * 
		 * ResponseSpecification respSpec=new
		 * ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.
		 * JSON).build();
		 * 
		 * 
		 * RequestSpecification respgiven=given().spec(reqspec).body(ad);
		 * 
		 * Response whenResp=respgiven.when().post("/maps/api/place/add/json")
		 * .then().spec(respSpec).extract().response();
		 * 
		 * System.out.println(whenResp.asString());
		 */	}

}
