package pages;
import com.microsoft.playwright.Page;

public class WeatherHistoryPage {
	Page page;
	
	public String searchResultMainGrid = ".maingrid";
	public String locationDD = "#locationDropdownMenuButton";
	
	public WeatherHistoryPage(Page page) {
		this.page = page;
	}
	
	public void waitForMainGrid() {
		page.locator(this.searchResultMainGrid).waitFor();
	}

}
