package framework.actions_web;

import com.google.common.base.Function;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;

import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import stepdefinition.SharedSD;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Tahir Qayyum on 10/10/18.
 */
public class BasePage {
    Actions a = new Actions(SharedSD.getDriver());

    //This method allows the chrome driver to wait.
    public static WebElement webAction(final By locator) {
        Wait<WebDriver> wait = new FluentWait<WebDriver>(SharedSD.getDriver())
                .withTimeout(15, TimeUnit.SECONDS)
                .pollingEvery(1, TimeUnit.SECONDS)
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class)
                .withMessage("Webdriver waited for 15 seconds but still could not find the element therefore Timeout Exception has been thrown");
        WebElement element = wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver){
                return driver.findElement(locator);
            }
        });
        return element;
    }

    //This method is to click on an element
	public void clickOn(By locator) {
		try {
			webAction(locator).click();
		} catch (NoSuchElementException e) {
			Assert.fail("Element is not found with this locator: " + locator.toString());
			e.printStackTrace();
		}
	}

    public void clearOn(By locator) {
        try {
            webAction(locator).clear();
        } catch (NoSuchElementException e) {
            Assert.fail("Element is not found with this locator: " + locator.toString());
            e.printStackTrace();
        }
    }

	//This method is to send text from an element
	public void sendText(By locator, String text) {
		try {
			webAction(locator).sendKeys(text);
		} catch (NoSuchElementException e) {
			Assert.fail("Element is not found with this locator: " + locator.toString());
			e.printStackTrace();
		}
	}

	//This method is a get the text of a an element
	public String getText(By locator) {
		String text = null;
		try {
			text = webAction(locator).getText();
		} catch (NoSuchElementException e) {
			Assert.fail("Element is not found with this locator: " + locator.toString());
			e.printStackTrace();
		}
		return text;
	}

	public int subStringToInt(By locator, int start, int end) {
        String result = null;
        try {
            result = getText(locator).substring(start,end);
        } catch (NoSuchElementException e) {
            Assert.fail("Element is not found with this locator: " + locator.toString());
            e.printStackTrace();
        }
        return Integer.parseInt(result);
    }

    public String[] splitString(By locator, String regex, int index) {
      return  getText(locator).split(regex, index);
    }

    public String getCurrentDate(int range) {
        SimpleDateFormat df = new SimpleDateFormat("ha");
        Date date = new Date();
        String currentDate = df.format(date);
        return currentDate;
    }

	//This method is to select in a dropdown with an array
    public void setDropDownValue(By locator, String expectedText) {
        try {
            List<WebElement> options = SharedSD.getDriver().findElements(locator);
            for (WebElement option : options) {
                if (option.getText().equals(expectedText)) {
                    option.click();
                    break;
                }
            }
        } catch (NoSuchElementException e) {
            Assert.fail("Element is not found with this locator: " + locator.toString());
            e.printStackTrace();
        }
    }

    //This method is to switch to an iframe
    public static void switchToFrame(By locator) {
        try {
            WebElement myFrame = webAction(locator);
            SharedSD.getDriver().switchTo().frame(myFrame);
        } catch (NoSuchElementException e) {
            Assert.fail("Element is not found with this locator: " + locator.toString());
            e.printStackTrace();
        }
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

    //This method is to right on an element
    public void rightClick(By locator) {
        try {
            WebElement element = webAction(locator);
            a.contextClick(element).perform();
        } catch (NoSuchElementException e) {
            Assert.fail("Element is not found with this locator: " + locator.toString());
            e.printStackTrace();
        }
    }

    //This method allows the chrome driver to wait.
    public void waitFor(int millisecond) throws InterruptedException { Thread.sleep(millisecond); }

    //This method allows the compare a files
    public void compareFiles(String filePath1, String filePath2) throws IOException {
        try {
            File file1 = new File(filePath1);
            File file2 = new File(filePath2);
            FileUtils.contentEquals(file1,file2);
        } catch (NoSuchElementException e) {
            Assert.fail("No file found in this path: " + filePath1 + "and "+ filePath2);
            e.printStackTrace();
        }
    }

    //This method is to infect text into an element via javascript
    public void injectTxtWithJS(WebElement element, String expectedText) {
        //WebElement element = webAction(locator);
        JavascriptExecutor js = (JavascriptExecutor) SharedSD.getDriver();
        String command = String.format("arguments[0].value='%s';", expectedText );
        js.executeScript(command, element);
    }

    //This method is to switch the tab in an open window
    public static void switchToWindow(int index) {
        try {
            List<String> listOfWindows =
                    new ArrayList<String>(SharedSD.getDriver().getWindowHandles());
            SharedSD.getDriver().switchTo().window(listOfWindows.get(index));
        } catch (NoSuchElementException e) {
            Assert.fail("There is no tab at: " + index);
            e.printStackTrace();
        }

    }
}
