package ohtu;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import static org.junit.Assert.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class Stepdefs {
    // WebDriver driver = new ChromeDriver();
    WebDriver driver = new HtmlUnitDriver();
    String baseUrl = "http://localhost:4567";

    @Given("login is selected")
    public void loginIsSelected() {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("login"));
        element.click();
    }

    @Given("command new user is selected")
    public void newUserSelected() {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("register new user"));
        element.click();
    }

    @Given("user with username {string} with password {string} is successfully created")
    public void createNewUserBeforeLogin(String username, String password) {
        newUserSelected();
        createNewUser(username, password);
    }

    @Given("user with username {string} and password {string} is tried to be created")
    public void createUserWithIneligibleUsernameAndPassword(String username, String password) {
        newUserSelected();
        createNewUser(username, password);
        WebElement element = driver.findElement(By.linkText("back to home"));
        element.click();
    }

    @When("correct username {string} and password {string} are given")
    public void correctUsernameAndPasswordAreGiven(String username, String password) {
        logInWith(username, password);
    }

    @When("nonexistent username {string} is given")
    public void nonexistentUsernameIsGiven(String username) {
        logInWith(username, "");

    }

    @When("too short username {string} and password too short {string} are given")
    public void loginWithBadCredentials(String username, String password) {
        logInWith(username, password);

    }

    @When("a valid username {string} and password {string} and matching password confirmation are entered")
    public void createNewUser(String username, String password) {
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys(password);

        element = driver.findElement(By.name("signup"));
        element.submit();

    }

    @When("a too short username {string} and valid password {string} are given")
    public void userCreationNotSuccessfulWithTooShortUsername(String username, String password) {
        createNewUser(username, password);
    }

    @When("a valid username {string} and too short password {string} are given")
    public void userCreationNotSuccessfulWithTooShortPassword(String username, String password) {
        createNewUser(username, password);
    }

    @Then("user is logged in")
    public void userIsLoggedIn() {
        pageHasContent("Ohtu Application main page");
    }

    @When("correct username {string} and incorrect password {string} are given")
    public void correctUsernameAndIncorrectPasswordAreGiven(String username, String password) {
        logInWith(username, password);
    }

    @When("a valid username {string} and mismatching passwords {string} and {string} are given")
    public void correctUsernameAndMismatchingPasswordAreGiven(String username, String pw1, String pw2) {
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(pw1);
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys(pw2);

        element = driver.findElement(By.name("signup"));
        element.submit();

    }

    @Then("user is not logged in and error message is given")
    public void userIsNotLoggedInAndErrorMessageIsGiven() {
        pageHasContent("invalid username or password");
        pageHasContent("Give your credentials to login");
    }

    @Then("system will respond {string}")
    public void systemWillRespond(String pageContent) throws Throwable {
        assertTrue(driver.getPageSource().contains(pageContent));
    }

    @Then("a new user is created")
    public void aNewUserIsCreated() {
        pageHasContent("Welcome to Ohtu Application!");

    }

    @Then("user is not created and error {string} is reported")
    public void userNotCreated(String error_msg) {
        pageHasContent(error_msg);

    }

    @After
    public void tearDown() {
        driver.quit();
    }

    /* helper methods */

    private void pageHasContent(String content) {
        assertTrue(driver.getPageSource().contains(content));
    }

    private void logInWith(String username, String password) {
        assertTrue(driver.getPageSource().contains("Give your credentials to login"));
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("login"));
        element.submit();
    }
}
