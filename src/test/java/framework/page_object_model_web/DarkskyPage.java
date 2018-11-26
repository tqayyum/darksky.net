package framework.page_object_model_web;

import framework.actions_web.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import stepdefinition.SharedSD;

import java.text.ParseException;
import java.util.List;

public class DarkskyPage extends BasePage {

    private By searchField = By.xpath("//input[@type='text']");
    private By clickOnSearch = By.xpath("//a[@class='searchButton']");
    private By currentTemp = By.xpath("//span[@class='summary swap']");
    private By lowTemp = By.xpath("//span[@class='currently']//span[2]//span[2]//span[2]");
    private By highTemp = By.xpath("//span[@class='summary-high-low']//span[3]");
    private By timeline = By.xpath("//body[@class='forecast']/div[@id='week']/a[1]");
    private By minTemp1 = By.cssSelector("#week > a.day.revealed > span.tempRange > span.minTemp");
    private By minTemp2 = By.xpath("//div[@class='dayDetails revealed']//span[@class='highTemp swip']");
    private By maxTemp1 = By.cssSelector("#week > a:nth-child(2) > span.tempRange > span.maxTemp");
    private By maxTemp2 = By.xpath("//div[@class='dayDetails revealed']//span[@class='lowTemp swap']");

    public void clearText() { clearOn(searchField); }

    public void sendText(String expectedText){ sendText(searchField, expectedText); }

    public void clickSearch() { clickOn(clickOnSearch); }

    public int getCurrentTemp() { return subStringToInt(currentTemp, 0, 2); }

    public int getLowTemp() {  return subStringToInt(lowTemp, 5, 7); }

    public int getHighTemp() { return subStringToInt(highTemp, 6, 8); }

    public String[] splitToString() { return splitString(currentTemp,"˚",2);}

    public void verifyTempRange() {
        int    current = getCurrentTemp(), low = getLowTemp(), high = getHighTemp();

        Assert.assertTrue(low <= current && current <= high,
                "Current temp (" + current + "˚) is not in between low (" + low + "˚) and high (" + high + "˚)"
        );
    }

    public void verifyTimeline() throws  InterruptedException {
        int count = 0, range = 0, match = 0;
        waitFor(3000);

        for (int i = 3; i < 25; i+=2) {

            count +=1;
            range +=2;

            String name = SharedSD.getDriver().findElement(By.xpath("//div[@id='timeline']//div[@class='hours']//span["+ i +"]")).getText();
            if (getCurrentDate(range).contains(name)){
                match +=1;
            }
            if(count == 12){
                break;
            }

        }
    }

    public void clickOnTimeline() { clickOn(timeline);}

    public int getMinTemp1() { return subStringToInt(minTemp1, 0, 2); }

    public int getMinTemp2() { return subStringToInt(minTemp2, 0, 2); }

    public int getMaxTemp1() { return subStringToInt(maxTemp1, 0, 2); }

    public int getMaxTemp2() { return subStringToInt(maxTemp2, 0, 2); }

    public void verifyLowAndHighTemp() {
        int minTemp1 = getMinTemp1(),
            minTemp2 = getMinTemp2(),
            maxTemp1 = getMaxTemp1(),
            maxTemp2 = getMaxTemp2();

        Assert.assertTrue(minTemp1 == minTemp2);
        Assert.assertTrue(maxTemp1 == maxTemp2);
    }

}
