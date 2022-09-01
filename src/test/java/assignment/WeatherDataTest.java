package assignment;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.WaitUntilState;

import pages.HomePage;
import pages.WeatherDataPage;
import pages.WeatherHistoryPage;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class WeatherDataTest {
	Playwright playwright;
	APIRequestContext apiRequestContext;
	Browser browser;
	Page page;
	
	
	HomePage homePage;
	WeatherDataPage weatherDataPage;
	WeatherHistoryPage weatherHistoryPage;
	
	@BeforeTest
	public void setup() {
		playwright = Playwright.create();
		browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
		page = browser.newPage();
		page.navigate("https://www.visualcrossing.com/", new Page.NavigateOptions().setWaitUntil(WaitUntilState.NETWORKIDLE));
		homePage = new HomePage(page);	
		weatherDataPage = new WeatherDataPage(page);
		weatherHistoryPage = new WeatherHistoryPage(page);
	}
	
	@Test
	public void searchWeatherByCityTests() {
		assertThat(page).hasURL("https://www.visualcrossing.com/");
		homePage.allowCookies();
		homePage.clickOnWeatherDataNavBar();
		page.locator("text=Total Weather Data").waitFor();

		weatherDataPage.clickEnterLocation("Tallinn");
	    weatherDataPage.clickOnSearchButton();
	    
	    weatherHistoryPage.waitForMainGrid();
	    
	    assertThat(page).hasURL("https://www.visualcrossing.com/weather-history/Tallinn");
	    assertThat(page.locator(weatherHistoryPage.locationDD)).hasText("Tallinn");
	    assertThat(page.locator(weatherHistoryPage.searchResultMainGrid)).isVisible();
	    assertThat(page.locator("text=TALLINN, EN")).isVisible();	
		
	}

	

	
	@AfterTest
	public void tearDown() {
		browser.close();
	}

}
