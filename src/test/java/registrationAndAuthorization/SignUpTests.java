package registrationAndAuthorization;
//TestNG Todo : Sample App
        import org.openqa.selenium.By;
        import org.openqa.selenium.WebDriver;
        import org.openqa.selenium.WebElement;
        import org.openqa.selenium.chrome.ChromeDriver;
        import org.testng.Assert;
        import org.testng.annotations.*;

        import java.util.concurrent.TimeUnit;

public class SignUpTests {

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

    // Registration with all valid data
    @Test
    public void validRegistrationTest(){


        WebElement fullName = driver.findElement(By.name("username"));
        fullName.sendKeys("TestName");

        WebElement email = driver.findElement(By.name("email"));
        email.sendKeys("test.reactapp@gmail.com");

        WebElement password = driver.findElement(By.name("password"));
        password.sendKeys("Test@12345");

        WebElement confirmPassword = driver.findElement(By.name("password2"));
        confirmPassword.sendKeys("Test@12345");

        WebElement register = driver.findElement(By.xpath("//button[contains(@class,'login-btn')]"));
        register.click();

        String expectedURL = "http://34.222.107.139/dashboard";
        String actualURL = driver.getCurrentUrl();
        Assert.assertEquals(actualURL, expectedURL);


    }

    // Registration without providing Name field
    @Test
    public void emptyNameTest()
    {

        WebElement fullName = driver.findElement(By.name("username"));
        fullName.sendKeys("");

        WebElement email = driver.findElement(By.name("email"));
        email.sendKeys("test2.reactapp@gmail.com");

        WebElement password = driver.findElement(By.name("password"));
        password.sendKeys("Test@12345");

        WebElement confirmPassword = driver.findElement(By.name("password2"));
        confirmPassword.sendKeys("Test@12345");

        WebElement register = driver.findElement(By.xpath("//button[contains(@class,'login-btn')]"));
        register.click();

        String expectedErrorMsg = "Username required";

        WebElement exp = driver.findElement(By.xpath("//p[contains(text(),'Username required')]"));
        String actualErrorMsg = exp.getText();

        Assert.assertEquals(actualErrorMsg, expectedErrorMsg);

    }

    // Registration without providing Email field
    @Test
    public void emptyEmailTest()
    {

        WebElement fullName = driver.findElement(By.name("username"));
        fullName.sendKeys("TestUser");

        WebElement email = driver.findElement(By.name("email"));
        email.sendKeys("");

        WebElement password = driver.findElement(By.name("password"));
        password.sendKeys("Test@12345");

        WebElement confirmPassword = driver.findElement(By.name("password2"));
        confirmPassword.sendKeys("Test@12345");

        WebElement register = driver.findElement(By.xpath("//button[contains(@class,'login-btn')]"));
        register.click();

        String expectedErrorMsg = "Email required";

        WebElement exp = driver.findElement(By.xpath("//p[contains(text(),'Email required')]"));
        String actualErrorMsg = exp.getText();

        Assert.assertEquals(actualErrorMsg, expectedErrorMsg);

    }

    // Registration without providing Password field
    @Test
    public void emptyPasswordTest(){


        WebElement fullName = driver.findElement(By.name("username"));
        fullName.sendKeys("TestName");

        WebElement email = driver.findElement(By.name("email"));
        email.sendKeys("test3.reactapp@gmail.com");

        WebElement password = driver.findElement(By.name("password"));
        password.sendKeys("");

        WebElement confirmPassword = driver.findElement(By.name("password2"));
        confirmPassword.sendKeys("");

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