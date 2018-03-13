package Annotation;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.But;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Annotation {
    WebDriver driver = null;
    WebDriverWait wait;

    @Before
    public void setup() {
        System.setProperty("webdriver.gecko.driver", "C:\\Users\\Andrei\\IdeaProjects\\CucumberTest\\src\\test\\resources\\geckodriver.exe");
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, 60);
    }

    @Given("^I am on Facebook login page$")
    public void goToFacebook() {
        driver.navigate().to("https://www.facebook.com/");
    }

    @When("^I enter username as \"(.*)\"$")
    public void enterUsername(String arg1) {
        driver.findElement(By.id("email")).sendKeys(arg1);
    }

    @When ("^I enter password as \"(.*)\"$")
    public void enterPassword(String arg1) {
        driver.findElement(By.id("pass")).sendKeys(arg1);
        driver.findElement(By.cssSelector("label#loginbutton input")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a._42ft._4jy0._62c3._4jy4._517h._51sy")));
    }

    @Then("^Login should fail$")
    public void checkFail() {
        driver.getCurrentUrl().equalsIgnoreCase("https://www.facebook.com/login.php?login_attempt=1&lwv=110");
    }

    @But("^Relogin option should be available$")
    public void checkRelogin() {
        driver.getCurrentUrl().equalsIgnoreCase("https://www.facebook.com/login.php?login_attempt=1&lwv=110");
    }

    @After
    public void cleanUp() {
        driver.close();
    }
}