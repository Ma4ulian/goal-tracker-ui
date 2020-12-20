package registrationAndAuthorization;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class SignInTests {
    private WebDriver driver;
    private static final String REACTAPP_URL = "http://34.222.107.139/";


    @BeforeTest
    public void profileSetUp() {
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
    }

    @BeforeMethod
    public void testsSetUp() {
        driver = new ChromeDriver();
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.get(REACTAPP_URL);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);

        WebElement getStartedButton = driver.findElement(By.xpath("//*[contains(text(),'Get started')]"));
        getStartedButton.click();

    }
    // Sign in with all valid data
    @Test
    public void validSignInTest() {
        WebElement signIn = driver.findElement(By.xpath("//a[contains(@href,'/signin')]"));
        signIn.click();

        WebElement email = driver.findElement(By.name("email"));
        email.sendKeys("test.reactapp@gmail.com");

        WebElement password = driver.findElement(By.name("password"));
        password.sendKeys("Test@12345");

        WebElement login = driver.findElement(By.xpath("//button[contains(@class,'login-btn')]"));
        login.click();

        String expectedURL = "http://34.222.107.139/dashboard";
        String actualURL = driver.getCurrentUrl();
        Assert.assertEquals(actualURL, expectedURL);
    }
    // Sign in without providing Email field
    @Test
    public void emptyEmailTest()
    {
        WebElement signIn = driver.findElement(By.xpath("//a[contains(@href,'/signin')]"));
        signIn.click();

        WebElement email = driver.findElement(By.name("email"));
        email.sendKeys("");

        WebElement password = driver.findElement(By.name("password"));
        password.sendKeys("Test@12345");

        WebElement register = driver.findElement(By.xpath("//button[contains(@class,'login-btn')]"));
        register.click();

        String expectedErrorMsg = "Email required";

        WebElement exp = driver.findElement(By.xpath("//p[contains(text(),'Email required')]"));
        String actualErrorMsg = exp.getText();

        Assert.assertEquals(actualErrorMsg, expectedErrorMsg);

    }

    // Sign in without providing Password field
    @Test
    public void emptyPasswordTest(){

        WebElement signIn = driver.findElement(By.xpath("//a[contains(@href,'/signin')]"));
        signIn.click();

        WebElement email = driver.findElement(By.name("email"));
        email.sendKeys("test3.reactapp@gmail.com");

        WebElement password = driver.findElement(By.name("password"));
        password.sendKeys("");

        WebElement register = driver.findElement(By.xpath("//button[contains(@class,'login-btn')]"));
        register.click();

        String expectedErrorMsg = "Password is required";

        WebElement exp = driver.findElement(By.xpath("//p[contains(text(),'Password is required')]"));
        String actualErrorMsg = exp.getText();

        Assert.assertEquals(actualErrorMsg, expectedErrorMsg);
    }
    // Closing the browser session after completing each test case
    @AfterMethod
    public void tearDown() {
        driver.close();
    }
}
