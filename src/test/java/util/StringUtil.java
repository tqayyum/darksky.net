package util;

import framework.actions_web.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;

public class StringUtil extends BasePage {

    public int subStringToInt(By locator, int start, int end) {
        String result = null;
        try {
            result = getText(locator).substring(start, end);
        } catch (NoSuchElementException e) {
            Assert.fail("Element is not found with this locator: " + locator.toString());
            e.printStackTrace();
        }
        return Integer.parseInt(result);
    }

    public String[] splitString(By locator, String regex, int index) {
        return  getText(locator).split(regex, index);
    }

    //This method is to compare the text
    public void compareText(By locator, String expectTxt){
        try {
            Assert.assertEquals(getText(locator), expectTxt);
        } catch (NoSuchElementException e) {
            Assert.fail("Element is not found with this locator: " + locator.toString());
            e.printStackTrace();
        }
    }

}
