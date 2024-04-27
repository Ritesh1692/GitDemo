package demo;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import pojo.API;
import pojo.Getcourse;
import pojo.WebAutomation;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
public class OauthTest {

	public static void main(String[] args) 
	{
		
		// TODO Auto-generated method stub
		
		/*
		 * RestAssured.baseURI="https://rahulshettyacademy.com"; String resp=
		 * given().formParam("client_id",
		 * "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
		 * .formParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W").
		 * formParam("grant_type", "client_credentials").formParam("scope",
		 * "trust").when().log().all()
		 * .post("/oauthapi/oauth2/resourceOwner/token").then().assertThat().statusCode(
		 * 200).extract().response().asString();
		 */
		
        RestAssured.baseURI="https://rahulshettyacademy.com";
        String resp= given().formParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
        .formParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W").
        formParam("grant_type", "client_credentials").formParam("scope", "trust").when().log().all()
        .post("/oauthapi/oauth2/resourceOwner/token").then().assertThat().statusCode(200).extract().response().asString();
        
       String[] expectedList= {"Selenium Webdriver Java","Cypress","Protractor"};
		/*
		 * JsonPath js=new JsonPath(resp); String access_token=js.get("access_token");
		 * given().queryParam("access_token",access_token
		 * ).when().log().all().get("oauthapi/getCourseDetails").then().log().all().
		 * assertThat().statusCode(401);
		 */
	
        JsonPath js=new JsonPath(resp);
        String access_token=js.get("access_token");
 	   
        Getcourse gc= given().queryParam("access_token",access_token ).when().log().all().get("oauthapi/getCourseDetails").as(Getcourse.class);
 	   
	   System.out.println("Instructor Name:- "+gc.getInstructor());
	   
	   System.out.println("API course Title at Index 1====> "+gc.getCourses().getApi().get(1).getCourseTitle());
	    
	   //Get me price of API course title->SoapUI Webservices testing

	   List<API> ap=gc.getCourses().getApi();
	   for (int i = 0; i < ap.size(); i++) 
	   {
		    if(ap.get(i).getCourseTitle().equalsIgnoreCase("SoapUI Webservices testing")) {
		    	System.out.println(" Price of API course title->SoapUI Webservices testing=> "+ap.get(i).getPrice());
		    }
		
	   }
	   
	  // Get Course name of Web Automation
	   List<WebAutomation> wb=gc.getCourses().getWebAutomation();
	   System.out.println("List of Course Titles:- ");
	   System.out.println();
	   
	   ArrayList<String> courseTitleToCompare=new ArrayList<String>();

	   for (int j = 0; j < wb.size(); j++) 
	   {
		
		   courseTitleToCompare.add(wb.get(j).getCourseTitle());
		   System.out.println(wb.get(j).getCourseTitle());
		   System.out.println();
		
	   }
	   
	   List<String> expectedCourseTitle= Arrays.asList(expectedList);
	   for (int i = 0; i < expectedCourseTitle.size(); i++) {
		  System.out.println(expectedCourseTitle.get(i));
	    }
	   Assert.assertTrue(courseTitleToCompare.equals(expectedCourseTitle));
	}

}
