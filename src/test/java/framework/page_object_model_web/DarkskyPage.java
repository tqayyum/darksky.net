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
    private By timeline = By.xpath("//div[@id='timeline']//div[@class='hours']//span[1]");

    public void clearText() { clearOn(searchField); }

    public void sendText(String expectedText){ sendText(searchField, expectedText); }

    public void clickSearch() { clickOn(clickOnSearch); }

    public int getCurrentTemp() { return subStringToInt(currentTemp, 0, 2); }

    public int getLowTemp() {  return subStringToInt(lowTemp, 5, 7); }

    public int getHighTemp() { return subStringToInt(highTemp, 6, 8); }

    public String[] splitToString() { return splitString(currentTemp,"˚",2);}

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

}
