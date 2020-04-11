package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.TestNG;

public class LoginPage{


    public void facebookLogin( WebDriver driver) {
        WebElement element = driver.findElement(By.xpath("//*[@id = 'email']"));
        element.sendKeys("kalamtechcorp@gmail.com");
        WebElement element2 = driver.findElement(By.xpath("//*[@id = 'pass']"));
        element2.sendKeys("Vivek@Abhi2020");
        element2.submit();
    }



}
