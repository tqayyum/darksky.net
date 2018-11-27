package framework.page_object_model_web;

import framework.actions_web.BasePage;
import org.openqa.selenium.By;
import org.testng.Assert;
import util.DateUtil;
import util.StringUtil;

public class DarkskyPage extends BasePage {

    private By searchField = By.xpath("//input[@type='text']");
    private By clickOnSearch = By.xpath("//a[@class='searchButton']");
    private By currentTemp = By.xpath("//span[@class='summary swap']");
    private By lowTemp = By.xpath("//span[@class='currently']//span[2]//span[2]//span[2]");
    private By highTemp = By.xpath("//span[@class='summary-high-low']//span[3]");
    private By timeList = By.xpath("//div[@id='timeline']//div[@class='hours']//span");
    private By timeline = By.xpath("//body[@class='forecast']/div[@id='week']/a[1]");
    private By minTemp1 = By.cssSelector("#week > a.day.revealed > span.tempRange > span.minTemp");
    private By minTemp2 = By.xpath("//div[@class='dayDetails revealed']//span[@class='highTemp swip']");
    private By maxTemp1 = By.cssSelector("#week > a:nth-child(2) > span.tempRange > span.maxTemp");
    private By maxTemp2 = By.xpath("//div[@class='dayDetails revealed']//span[@class='lowTemp swap']");
    private By timeMachine = By.xpath("//a[@class='button']");
    private By today = By.xpath("//td[@class='is-today']");

    DateUtil dateUtil = new DateUtil();
    StringUtil stringUtil = new StringUtil();

    public void clearText() { clearOn(searchField); }

    public void sendText(String expectedText){ sendText(searchField, expectedText); }

    public void clickSearch() { clickOn(clickOnSearch); }

    public int getCurrentTemp() { return
            stringUtil.subStringToInt(currentTemp, 0, 2); }

    public int getLowTemp() {  return
            stringUtil.subStringToInt(lowTemp, 5, 7); }

    public int getHighTemp() { return
            stringUtil.subStringToInt(highTemp, 6, 8); }

    public void verifyTempRange() throws InterruptedException {
        int    current = getCurrentTemp(), low = getLowTemp(), high = getHighTemp();
        waitFor(3000);
        Assert.assertTrue(low <= current && current <= high);
    }

    public void verifyTimeline() throws  InterruptedException {
        int count = 0, range = 0, match = 0;
        waitFor(3000);

        for (int i = 3; i < 25; i+=2) {
            count +=1;
            range +=2;
            String name = webAction
                    (By.xpath("//div[@id='timeline']//div[@class='hours']//span["+ i +"]")).getText();
            if (dateUtil.getHr(range).contains(name)){
                match +=1;
            }
            if(count == 12){
                break;
            }
        }
    }

    public void clickOnTimeline() { clickOn(timeline);}

    public int getMinTemp1() {
        return stringUtil.subStringToInt(minTemp1, 0, 2); }

    public int getMinTemp2() {
        return stringUtil.subStringToInt(minTemp2, 0, 2); }

    public int getMaxTemp1() {
        return stringUtil.subStringToInt(maxTemp1, 0, 2); }

    public int getMaxTemp2() {
        return stringUtil.subStringToInt(maxTemp2, 0, 2); }

    public void verifyLowAndHighTemp() {
        int minTemp1 = getMinTemp1(), minTemp2 = getMinTemp2(), maxTemp1 = getMaxTemp1(), maxTemp2 = getMaxTemp2();

        Assert.assertTrue(minTemp1 == minTemp2);
        Assert.assertTrue(maxTemp1 == maxTemp2);
    }

    public void clickOnTimeMachine() { clickOn(timeMachine);}

    public void verifyCurrentDate() {
        String calendarDate = getText(today);
        String currentDate = dateUtil.getDate();

        if (calendarDate.equals(currentDate)) {
        }
    }
}
