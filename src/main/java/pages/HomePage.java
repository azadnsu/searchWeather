package pages;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import com.microsoft.playwright.Page;

public class HomePage {
	
	Page page;
	
	private String allowCookies = ".modal-body .btn-primary";
	private String weatherDataNavMenu = "#navbarNav >> text=Weather Data";
	
	public HomePage(Page page) {
		this.page = page;
	}
	
	public void assertPageURL(String URL) {
		assertThat(page).hasURL(URL);
	}
	
	public void allowCookies() {
		page.locator(allowCookies).click();
	}
	
	public void clickOnWeatherDataNavBar() {
		page.locator(this.weatherDataNavMenu).click();
	}
	
	
}