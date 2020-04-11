import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import util.ReadData;

import java.nio.file.Paths;

public class TestNG {

    //declare objects
    public WebDriver driver;
    ReadData dataList = new ReadData();
    SoftAssert softassert = new SoftAssert();
    WebDriverWait wait;

    //ObjectRepositories
    String loginTextFieldXpath = "//*[@id = 'email']";
    String passwordTextFieldXpath = "//*[@id = 'pass']";
    String profileTextXpath = "//*[@title='Profile']//*[text()='Kalam']";
    
    //Constants
    String facebookEmailId = "kalamtechcorp@gmail.com";
    String facebookPassword = "Vivek@Abhi2020";



    @Test
    public void facebookLogin() {

        //loginFLow
        WebElement loginTextField = driver.findElement(By.xpath(loginTextFieldXpath));
        loginTextField.sendKeys(facebookEmailId);
        WebElement passwordTextField = driver.findElement(By.xpath("//*[@id = 'pass']"));
        passwordTextField.sendKeys(facebookPassword);
        passwordTextField.submit();

        //ExpectHomePage
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(profileTextXpath)));
        WebElement profileText = driver.findElement(By.xpath(profileTextXpath));
        softassert.assertEquals (true, profileText.isDisplayed());

        //FindRequiredPost

        //Start Sharing

    }

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.gecko.driver", Paths.get("").toAbsolutePath().toString()+"//jars//geckodriver");
        driver = new FirefoxDriver();
        driver.get("https://www.facebook.com/");
        wait = new WebDriverWait(driver, 10);
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}