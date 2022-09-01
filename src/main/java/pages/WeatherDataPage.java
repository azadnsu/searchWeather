package pages;

import com.microsoft.playwright.Page;

public class WeatherDataPage {
	Page page;
	
	private String enterLocationField = "[placeholder=\"Enter a location\"]";
	private String searchBtn = "button:has-text(\"Search\")";
	
	public WeatherDataPage(Page page) {
		this.page = page;
	}
	
	public void clickEnterLocation(String city) {
		page.locator(this.enterLocationField).click();
	    page.locator(this.enterLocationField).fill(city);
	}
	

    public void clickOnSearchButton() {
    	page.locator(this.searchBtn).click();
    }
    


}
