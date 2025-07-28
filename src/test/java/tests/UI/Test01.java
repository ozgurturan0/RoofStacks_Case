package tests.UI;

import org.testng.Assert;
import org.testng.annotations.Test;
import testBase.TestBase;
import utilities.ConfigReader;
import utilities.Driver;

import static utilities.ReusableMethods.waitForPageLoad;

public class Test01 extends TestBase {
    Page page = new Page();

    @Test(priority = 1, description = "Negative scenario for login, wrong email address")
    public void login_01() {
        Driver.getDriver().get(ConfigReader.getProperty("url"));
        extentTest = extentReports.createTest("Negative scenario for login, wrong email address");
        page.mail.sendKeys("wrong@mail");
        page.password.sendKeys(ConfigReader.getProperty("password"));
        page.btnLogin.click();
        waitForPageLoad(Driver.getDriver());
        Assert.assertEquals(page.banner.getText(), "Sign In");
    }

    @Test(priority = 2, description = "Negative scenario for login, wrong password")
    public void login_02() {
        extentTest = extentReports.createTest("Negative scenario for login, wrong password");
        page.mail.clear();
        page.mail.sendKeys(ConfigReader.getProperty("email"));
        page.password.clear();
        page.password.sendKeys("wrongPassword");
        page.btnLogin.click();
        waitForPageLoad(Driver.getDriver());
        Assert.assertEquals(page.banner.getText(), "Sign In");
    }

    @Test(priority = 3, description = "positive scenario for login")
    public void login_03() {
        extentTest = extentReports.createTest("positive scenario for login");
        page.mail.clear();
        page.mail.sendKeys(ConfigReader.getProperty("email"));
        page.password.clear();
        page.password.sendKeys(ConfigReader.getProperty("password"));
        page.btnLogin.click();
        waitForPageLoad(Driver.getDriver());
        Assert.assertEquals(page.banner.getText(), "Dashboard");
    }
}
