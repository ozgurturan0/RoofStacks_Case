package tests.UI;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import testBase.TestBase;
import utilities.ConfigReader;
import utilities.Driver;

import static utilities.ReusableMethods.*;

public class Test02 extends TestBase {
    Page page = new Page();
    public void login(){
        waitForPageLoad(Driver.getDriver());
        page.mail.sendKeys(ConfigReader.getProperty("email"));
        page.password.sendKeys(ConfigReader.getProperty("password"));
        page.btnLogin.click();
        waitForPageLoad(Driver.getDriver());
        Assert.assertEquals(page.banner.getText(),"Dashboard");
    }
    @Test(priority = 4, description = "Creating a post")
    public void create_post(){
        //login();
        extentTest=extentReports.createTest("Creating a post");
        waitForPageLoad(Driver.getDriver());
        jsClick(page.btnPosts);
        Assert.assertEquals(page.banner.getText(),"Posts");
        page.inputTextBox.sendKeys(ConfigReader.getProperty("message"));
        page.btnSubmit.click();
        waitForPageLoad(Driver.getDriver());
        Assert.assertTrue(findElementWithText(ConfigReader.getProperty("message")).isDisplayed());
    }
    @Test(priority = 5, dependsOnMethods = "create_post", description = "Deleting a post")
    public void delete_post(){
        extentTest=extentReports.createTest("Deleting a post");
        waitForPageLoad(Driver.getDriver());
        jsClick(page.btnPosts);
        page.btnDeletePost.click();
        Assert.assertTrue(Driver.getDriver().findElements(By.linkText(ConfigReader.getProperty("message"))).isEmpty());
    }
}
