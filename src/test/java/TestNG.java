import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import util.ReadData;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;

public class TestNG {

    //declare objects
    public WebDriver driver;
    ReadData data = new ReadData();
    SoftAssert softassert = new SoftAssert();
    WebDriverWait wait;
    ArrayList<String> facebookGroupList;
    Actions action;

    //Constants
    String facebookEmailId = "kalamtechcorp@gmail.com";
    String facebookPassword = "Vivek@Abhi2020";
    String pageName = "Kalam Tech Corp";
    String websiteName = "https://www.facebook.com/";
    String firefoxDriverPathMacOS = Paths.get("").toAbsolutePath().toString() + "//jars//geckodriver";
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
    String personNameInDropDown ="//*[contains(text(),'Kalam Techcorp')]/span[contains(text(),'(You)')]";

    String groupNameInSuggestionDropDownXpath = "//ul[@role='listbox']//span[text()='%']";
    String groupImageInGroupNameFieldXpath = "//input[@placeholder='Group name']/ancestor::span/img";
    String saySomethingElseTextFieldXpath = "//*[@aria-label='Say something about this...']//descendant::span";
    String postLinkXpath = "//button[@type='submit' and text()='Cancel']/following-sibling::button[@type='submit' and text()='Post']";
    String successMessageXpath = "//div[@id='ariaPoliteAlert']//following-sibling::div//*[contains(text(),'This has been successfully shared with ')] ";
    String groupNameOnSuccessMessageXpath = "//div[@id='ariaPoliteAlert']//following-sibling::div//strong/a";
    String cancelLinkXpath = "//button[@type='submit' and text()='Cancel']";

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
                WebElement groupNameInputField = driver.findElement(By.xpath(groupNameInputFieldXpath));
                groupNameInputField.click();

                //Expect share by dropdown icon is displayed & click
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(shareByDropdownXpath)));
                WebElement shareByDropdown = driver.findElement(By.xpath(shareByDropdownXpath));
                shareByDropdown.click();

                //Select person Name from dropdown
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(personNameInDropDown)));
                WebElement pageNameInDropDown = driver.findElement(By.xpath(personNameInDropDown));
                pageNameInDropDown.click();

                /*//expect Page Name is selected in share-By Option
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(selectedShareByNameXpath)));
                WebElement selectedPersonalName = driver.findElement(By.xpath(selectedShareByNameXpath));
                selectedPersonalName.click();*/

                //enter group Name
                groupNameInputField.sendKeys(groupName);

                //wait for group Name to be displayed in Suggestion-DropDown & click
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(getDynamicXPath(groupNameInSuggestionDropDownXpath, groupName))));
                WebElement groupNameInSuggestionDropDown = driver.findElement(By.xpath(getDynamicXPath(groupNameInSuggestionDropDownXpath, groupName)));
                groupNameInSuggestionDropDown.click();

                //Expect group Image is displayed in group Name field
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(groupImageInGroupNameFieldXpath)));

                /*//Add Say Something Else Description
                WebElement saySomethingElseTextField  = driver.findElement(By.xpath(saySomethingElseTextFieldXpath));
                saySomethingElseTextField.sendKeys(saySomethingElseDescription);*/

                //Click on Post & verify that post has been re-shared
                WebElement postLink = driver.findElement(By.xpath(postLinkXpath));
                postLink.click();

                //wait for successful Message to be displayed
                //wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(successMessageXpath)));

                //assert if post been shared successfully for group in a loop
                //WebElement  groupNameOnSuccessMessage = driver.findElement(By.xpath(groupNameOnSuccessMessageXpath));
                //softassert.assertEquals(groupName,groupNameOnSuccessMessage.getText());
                System.out.println("Post successfully shared for group: " + facebookGroupList.get(i));

            } catch (Exception e) {
                System.out.println("Error occured while sharing. Post is not shared on group " + facebookGroupList.get(i) + ".");
                //Click on Cancel & verify that popup has been closed
                WebElement cancelLink = driver.findElement(By.xpath(cancelLinkXpath));
                if (cancelLink.isDisplayed())
                    cancelLink.click();
                else {
                    action = new Actions(driver);
                    action.sendKeys(Keys.ESCAPE);
                }
            }
        }
    }

    @BeforeClass
    public void beforeClass() {

        FirefoxOptions options = new FirefoxOptions();
        options.addPreference("dom.disable_beforeunload", true);
        options.addPreference("dom.webnotifications.enabled", false);

        System.setProperty("webdriver.gecko.driver", firefoxDriverPathMacOS);
        driver = new FirefoxDriver(options);
        driver.manage().window().maximize();
        driver.get(websiteName);
        wait = new WebDriverWait(driver, 10);
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
}