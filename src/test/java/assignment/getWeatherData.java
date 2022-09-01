package assignment;

import com.microsoft.playwright.*;

import static org.testng.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;

public class getWeatherData {
	Playwright playwright;
	APIRequestContext apiRequest;

	
	
	@BeforeTest
	public void setup() {
		playwright = Playwright.create();
		Map<String, String> headers = new HashMap<>();
	    headers.put("Content-Type", "application/json");
	    apiRequest = playwright.request().newContext(new APIRequest.NewContextOptions()
			      .setBaseURL("https://weather.visualcrossing.com/")
			      .setExtraHTTPHeaders(headers));

	}
	
	
	@Test
	public void getWeatherForecast() {
		String fullURL = "VisualCrossingWebServices/rest/services/timeline/Tallinn?unitGroup=metric&key=952YDPNG7SRVQCVWYP9PLMS5D&contentType=json";
		
		APIResponse weatherForecast = apiRequest.get(fullURL);
	    assertTrue(weatherForecast.ok());
	    Integer responseCode = weatherForecast.status();
	    Assert.assertEquals(200, responseCode);
	    assertTrue(weatherForecast.text().contains("Tallinn, Eesti"));
		
	}
	

	
	@AfterTest
	public void tearDown() {
		apiRequest.dispose();
		playwright.close();
	}

}
