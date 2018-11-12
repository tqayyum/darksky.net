package framework.page_object_model_web;

import framework.actions_web.BasePage;
import org.openqa.selenium.By;
import org.testng.Assert;

public class DarkskyPage extends BasePage {

    private By searchField = By.xpath("//input[@type='text']");
    private By clickOnSearch = By.xpath("//a[@class='searchButton']");
    private By currentTemp = By.xpath("//span[@class='summary swap']");
    private By lowTemp = By.xpath("//span[@class='currently']//span[2]//span[2]//span[2]");
    private By highTemp = By.xpath("//span[@class='summary-high-low']//span[3]");

    public void clearText() { clearOn(searchField); }

    public void sendText(String expectedText){ sendText(searchField, expectedText); }

    public void clickSearch() { clickOn(clickOnSearch); }

    public int getCurrentTemp() { return subStringToInt(currentTemp, 0, 2); }

    public int getLowTemp() { return  subStringToInt(lowTemp, 5, 7); }

    public int getHighTemp() { return subStringToInt(highTemp, 6, 8); }

    public void verifyTempRange() {
        int    current = getCurrentTemp(),
                low = getLowTemp(),
                high = getHighTemp();

        Assert.assertTrue(low <= current && current <= high,
                "Current temp (" + current + "˚) is not in between low (" + low + "˚) and high (" + high + "˚)"
        );
    }
}
