package demo;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import pojo.AddLocation;
import pojo.Location;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;
public class SerializationTest {

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
		
		
		/*AddLocation ad=new AddLocation();
		Location location=new Location();
		location.setLat(-88.383494);
		location.setLng(33.427362);
		ad.setLocation(location);
		
		ad.setAccuracy(54);
		ad.setName("Frontline house");
		ad.setPhone_number("(+91) 983 893 3937");
		ad.setAddress("29, side layout, cohen 09");*/
		
		List<String> type=new ArrayList<String>();
		type.add("shoe park");
		type.add("shop");
		ad.setTypes(type);
		
		ad.setWebsite("http://google.com");
		ad.setLanguage("French-IN");
		
		
		RestAssured.baseURI="https://rahulshettyacademy.com";
		
		Response resp=given().queryParam("key", "qaclick123").body(ad).when().post("/maps/api/place/add/json")
		.then().assertThat().statusCode(200).extract().response();
		
		System.out.println(resp.asString()); 
	}

}
