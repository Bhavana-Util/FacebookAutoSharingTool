import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import util.ReadData;

import java.nio.file.Paths;

public class TestNG extends LoginPage {

    public WebDriver driver;
    ReadData dataList = new ReadData();


    @Test
    public void facebookLogin() {
        driver.get("https://www.facebook.com/");
        WebElement loginTextField = driver.findElement(By.xpath("//*[@id = 'email']"));
        loginTextField.sendKeys("kalamtechcorp@gmail.com");
        WebElement passwordTextField = driver.findElement(By.xpath("//*[@id = 'pass']"));
        passwordTextField.sendKeys("Vivek@Abhi2020");
        passwordTextField.submit();

        WebElement profileText = driver.findElement(By.xpath(""));


    }

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.gecko.driver", Paths.get("").toAbsolutePath().toString()+"//jars//geckodriver");
        driver = new FirefoxDriver();
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

}