package stepdefinition;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import framework.page_object_model_web.DarkskyPage;

import java.text.ParseException;

public class DarkskySD {

    private DarkskyPage ds = new DarkskyPage();

    @Given("^I am on darksky website homepage$")
    public void darkskyHomepage() { }

    @When("^I clear search text field$")
    public void clearSearch() { ds.clearText(); }

    @And("^I enter an (.+) into the search field$")
    public void setText(String expectedText){ ds.sendText(expectedText); }

    @And("^I click on search magnifying glass$")
    public void clickOnSearch() { ds.clickSearch(); }

    @Then("^Verify current temperature is between low and high$")
    public void verifyTemp() throws InterruptedException { ds.verifyTempRange(); }

    //@darksky-2
    @Then("^I verify timeline is displayed with two hours incremented$")
    public void verifyTimeline() throws InterruptedException { ds.verifyTimeline(); }

    //@darksky-3
    @When("^I expand today's timeline$")
    public void clickTimeline() { ds.clickOnTimeline(); }

    @Then("^I verify lowest and highest temp is displayed correctly$")
    public void verifyLowAndHigh() { ds.verifyLowAndHighTemp(); }

    //@darksky-4
    @When("^I click on Time Machine$")
    public void clickOnTime() {ds.clickOnTimeMachine();}

    @Then("^I verify date is selected to today's date$")
    public void verifyCurrentDate() { ds.verifyCurrentDate(); }
}
