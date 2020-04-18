import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import util.ReadData;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TestNG {

    //run Configurations
    boolean isFirefoxBrowser = true;
    boolean isMacOS = true;
    //Non-Editable run Configurations
    String osName = isMacOS ? "mac" : "windows";
    String browserName = isFirefoxBrowser ? "firefox" : "chrome";

    //declare objects
    public WebDriver driver;
    ReadData data = new ReadData();
    SoftAssert softassert = new SoftAssert();
    Wait<WebDriver> wait;
    ArrayList<String> facebookGroupList;
    Actions action;
    HashMap<String, String> macDriverPathList = new HashMap<String, String>();
    HashMap<String, String> windowsDriverPathList = new HashMap<String, String>();

    //constants
    String facebookEmailId = "kalamtechcorp@gmail.com";
    String facebookPassword = "Vivek@Abhi2020";
    String pageName = "Kalam Tech Corp";
    String websiteURL = "https://www.facebook.com/";
    public final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_RESET = "\u001B[0m";



    String firefoxDriverPathMacOS = Paths.get("").toAbsolutePath().toString() + "//jars//geckodriver";
    String chromeDriverPathMacOS = Paths.get("").toAbsolutePath().toString() + "//jars//chromedriver";
    String firefoxDriverPathWindowsOS = Paths.get("").toAbsolutePath().toString() + "//jars//geckodriver.exe";
    String chromeDriverPathWindowsOS = Paths.get("").toAbsolutePath().toString() + "//jars//chromedriver.exe";
    String personalName = "Kalam Techcorp";
    String groupName;
    String saySomethingElseDescription = "Test Post";

    //ObjectRepositories
    String loginTextFieldXpath = "//*[@id = 'email']";
    String passwordTextFieldXpath = "//*[@id = 'pass']";
    String profileTextXpath = "//*[@title='Profile']//*[text()='Kalam']";
    String PageLinkXpath = "//*[text()='Your Page']/parent::div/parent::div/following-sibling::div//*[text()='" + pageName + "']";
    String pageProfilePicXpath = "//a[@aria-label='Profile picture']";
    String PostTabXpath = "//*[@data-key='tab_posts']/child::a/*[text()='Posts']";
    String recentPostXpath = "//*[@data-insertion-position=\"0\"]/parent::div";
    String recentPostShareLinkXpath = "//*[@data-insertion-position=\"0\"]/parent::div//*[text()='Share']";
    String shareInAGroupLinkXpath = "//li[@role='presentation']//span[text()='Share in a group']";
    String groupNameInputFieldXpath = "//input[@placeholder='Group name']";
    String shareByDropdownXpath = "//*[text()='Group:'] /ancestor::div[1]/preceding-sibling::div//i";
    String selectedShareByNameXpath = "//*[text()='Group:'] /ancestor::div[1]/preceding-sibling::div//span[contains(text(),'" + personalName + "')]";
    String pageNameInDropdownXpath = "//*[@data-tooltip-content='" + pageName + "']";
    String personNameInDropDown = "//*[contains(text(),'Kalam Techcorp')]/span[contains(text(),'(You)')]";
    String groupNameInSuggestionDropDownXpath = "//ul[@role='listbox']//span[text()='%']";
    String groupImageInGroupNameFieldXpath = "//input[@placeholder='Group name']/ancestor::span/img";
    String saySomethingElseTextFieldXpath = "//*[@aria-label='Say something about this...']/descendant::div[1]";
    String postLinkXpath = "//button[@type='submit' and text()='Cancel']/following-sibling::button[@type='submit' and text()='Post']";
    String successMessageXpath = "//div[@id='ariaPoliteAlert']//following-sibling::div//*[contains(text(),'This has been successfully shared with ')]";
    String groupNameOnSuccessMessageXpath = "//div[@id='ariaPoliteAlert']//following-sibling::div//strong/a";
    String cancelLinkXpath = "//button[@type='submit' and text()='Cancel']";
    String shareSuccessfulPopUpCloseButtonXpath = "//button[contains(@class,'layerCancel')]";


    @Test
    public void facebookLogin() throws IOException {

        //loginFLow
        WebElement loginTextField = driver.findElement(By.xpath(loginTextFieldXpath));
        loginTextField.sendKeys(facebookEmailId);
        WebElement passwordTextField = driver.findElement(By.xpath(passwordTextFieldXpath));
        passwordTextField.sendKeys(facebookPassword);
        passwordTextField.submit();

        //ExpectHomePage
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(profileTextXpath)));
        WebElement profileText = driver.findElement(By.xpath(profileTextXpath));
        softassert.assertEquals(true, profileText.isDisplayed());

        //Find & Open Page
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PageLinkXpath)));
        WebElement pageLink = driver.findElement(By.xpath(PageLinkXpath));
        pageLink.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(pageProfilePicXpath)));
        WebElement pageProfilePic = driver.findElement(By.xpath(pageProfilePicXpath));
        softassert.assertEquals(true, pageProfilePic.isDisplayed());

        //Go to Post
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PostTabXpath)));
        WebElement postTab = driver.findElement(By.xpath(PostTabXpath));
        postTab.click();

        facebookGroupList = data.getData();

        for (int i = 0; i < facebookGroupList.size(); i++) {

            //Expect recent post displayed
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(recentPostXpath)));
            WebElement recentPost = driver.findElement(By.xpath(recentPostXpath));

            System.out.println("Post re-share begin for group: " + facebookGroupList.get(i));
            this.groupName = facebookGroupList.get(i);

            try {
                //click on share button for recent post
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(recentPostShareLinkXpath)));
                WebElement recentPostShareLink = driver.findElement(By.xpath(recentPostShareLinkXpath));
                recentPostShareLink.click();

                //Expect 'share in a group' link displayed & click
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(shareInAGroupLinkXpath)));
                WebElement shareInAGroupLink = driver.findElement(By.xpath(shareInAGroupLinkXpath));
                shareInAGroupLink.click();

                //Expect group Name input field displayed
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(groupNameInputFieldXpath)));
                List<WebElement> groupNameInputFieldList = driver.findElements(By.xpath(groupNameInputFieldXpath));
                WebElement groupNameInputField = groupNameInputFieldList.get(groupNameInputFieldList.size()-1);
                groupNameInputField.click();

                //Expect share by dropdown icon is displayed & click
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(shareByDropdownXpath)));
                WebElement shareByDropdown = driver.findElement(By.xpath(shareByDropdownXpath));
                shareByDropdown.click();

                //Select person Name from dropdown
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(personNameInDropDown)));
                WebElement pageNameInDropDown = driver.findElement(By.xpath(personNameInDropDown));
                pageNameInDropDown.click();

                //expect Page Name is selected in share-By Option
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(selectedShareByNameXpath)));
                WebElement selectedPersonalName = driver.findElement(By.xpath(selectedShareByNameXpath));
                selectedPersonalName.click();

                //enter group Name
                groupNameInputField.sendKeys(groupName);

                //wait for group Name to be displayed in Suggestion-DropDown & click
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(getDynamicXPath(groupNameInSuggestionDropDownXpath, groupName))));
                WebElement groupNameInSuggestionDropDown = driver.findElement(By.xpath(getDynamicXPath(groupNameInSuggestionDropDownXpath, groupName)));
                groupNameInSuggestionDropDown.click();

                //Expect group Image is displayed in group Name field
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(groupImageInGroupNameFieldXpath)));

                //Add Say Something Else Description
                /*List<WebElement> saySomethingElseTextFieldList = driver.findElements(By.xpath(saySomethingElseTextFieldXpath));
                WebElement saySomethingElseTextField = saySomethingElseTextFieldList.get(saySomethingElseTextFieldList.size()-1);
                saySomethingElseTextField.sendKeys(saySomethingElseDescription);*/

                //Click on Post
                WebElement postLink = driver.findElement(By.xpath(postLinkXpath));
                postLink.click();
                System.out.println(ANSI_GREEN + "Post successfully shared for group. Waiting for success message." + facebookGroupList.get(i)+ ANSI_RESET);
            } catch (Exception e) {
                System.out.println("Error occured while sharing. Post is not shared on group " + facebookGroupList.get(i) + ".");
                System.out.println(ANSI_RED +  e.getCause().getStackTrace() + ANSI_RESET);

                //Click on Cancel & verify that popup has been closed
                if (driver.findElements(By.xpath(cancelLinkXpath)).size() > 0) {
                    driver.findElement(By.xpath(cancelLinkXpath)).click();
                } else {
                    action.sendKeys(Keys.ESCAPE);
                }
                continue;
            }
            try {
                //wait for successful Message to be displayed
                    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(successMessageXpath)));
                if (driver.findElements(By.xpath(successMessageXpath)).size() > 0) {
                    List<WebElement> successMessageList = driver.findElements(By.xpath(successMessageXpath));
                    WebElement successMessage = successMessageList.get(successMessageList.size() - 1);
                    softassert.assertTrue(successMessage.isDisplayed());
                }
                //assert if post been shared successfully for group
                List<WebElement> groupNameOnSuccessMessageList = driver.findElements(By.xpath(groupNameOnSuccessMessageXpath));
                WebElement groupNameOnSuccessMessage = groupNameOnSuccessMessageList.get(groupNameOnSuccessMessageList.size()-1);
                softassert.assertEquals(groupName, groupNameOnSuccessMessage.getText());
                System.out.println("Success message verified for " + facebookGroupList.get(i));

            } catch (NoSuchElementException e2){
                System.out.println(ANSI_GREEN + "Ignoring No Such Element Exception for after click on post purposely"+ANSI_RESET);
                System.out.println( e2.getCause().getStackTrace());
                if (driver.findElements(By.xpath(shareSuccessfulPopUpCloseButtonXpath)).size() > 0) {
                    driver.findElement(By.xpath(shareSuccessfulPopUpCloseButtonXpath)).click();
                }

            } catch (Exception e) {
                System.out.println(ANSI_YELLOW + "Error occured during success message verification for " + facebookGroupList.get(i) + "."+ ANSI_RESET);
                System.out.println(ANSI_RED + e.getCause().getStackTrace()+ANSI_RESET);
                //Click on Cancel & verify that popup has been closed
                if (driver.findElements(By.xpath(shareSuccessfulPopUpCloseButtonXpath)).size() > 0) {
                    driver.findElement(By.xpath(shareSuccessfulPopUpCloseButtonXpath)).click();
                }
            }
            action.sendKeys(Keys.ESCAPE);
        }
    }

    @BeforeClass
    public void beforeClass() {
        setUpPreferencesAndLaunchBrowser(browserName);
        OpenWebsiteHomePage(websiteURL);
        setupExplicitWait(15);
        action = new Actions(driver);
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    public String getDynamicXPath(String xpath, String variable) {
        // find || in xpath
        String dynamicXpath = xpath.replaceAll("%", variable);
        //replace || with variable name
        return dynamicXpath;
    }

    public String getDriverPath(String OSName, String browserName) {

        String driverPath = "";

        setDriverPath();
        if (osName.equalsIgnoreCase("mac") && browserName.equalsIgnoreCase("firefox")) {
            driverPath = macDriverPathList.get("firefox");
        } else if (osName.equalsIgnoreCase("mac") && browserName.equalsIgnoreCase("chrome")) {
            driverPath = macDriverPathList.get("chrome");
        } else if (osName.equalsIgnoreCase("windows") && browserName.equalsIgnoreCase("firefox")) {
            driverPath = windowsDriverPathList.get("firefox");
        } else if (osName.equalsIgnoreCase("windows") && browserName.equalsIgnoreCase("chrome")) {
            driverPath = windowsDriverPathList.get("chrome");
        } else
            System.out.println("OS Name or Browser name is not correct");

        return driverPath;
    }

    public void setDriverPath() {
        macDriverPathList.put("firefox", firefoxDriverPathMacOS);
        macDriverPathList.put("chrome", chromeDriverPathMacOS);
        windowsDriverPathList.put("firefox", firefoxDriverPathWindowsOS);
        windowsDriverPathList.put("chrome", chromeDriverPathWindowsOS);
    }

    public void setUpPreferencesAndLaunchBrowser(String browserName) {
        if (browserName.equalsIgnoreCase("firefox")) {
            System.setProperty("webdriver.gecko.driver", getDriverPath(osName, browserName));
            driver = new FirefoxDriver(getFireFoxPreferences());

        } else if (browserName.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver", getDriverPath(osName, browserName));
            driver = new ChromeDriver(getChromePreferences());
        } else
            System.out.println("Browser name is not correct");

        driver.manage().window().maximize();
    }

    public FirefoxOptions getFireFoxPreferences() {
        FirefoxOptions options = new FirefoxOptions();
        options.addPreference("dom.disable_beforeunload", true);
        options.addPreference("dom.webnotifications.enabled", false);
        options.addPreference("app.update.doorhanger", false);
        return options;
    }

    public void OpenWebsiteHomePage(String url) {
        driver.get(url);
    }

    public void setupExplicitWait(int timeOutInSeconds) {

        wait = new FluentWait<>(driver)
                .withTimeout(timeOutInSeconds, TimeUnit.SECONDS)
                .pollingEvery(timeOutInSeconds/(timeOutInSeconds*2), TimeUnit.SECONDS)
                .ignoring(NoSuchElementException.class);
    }

    public ChromeOptions getChromePreferences() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        return options;
    }
}