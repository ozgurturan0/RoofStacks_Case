package tests.UI;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class Page {
    public Page() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "//*[@id=\"root\"]/section/form/input")
    public WebElement btnLogin;
    @FindBy(xpath = "//*[@id=\"root\"]/section/form/div[1]/input")
    public WebElement mail;
    @FindBy(xpath = "//*[@id=\"root\"]/section/form/div[2]/input")
    public WebElement password;
    @FindBy(xpath = "//*[@id=\"root\"]/section/h1")
    public WebElement banner;
    @FindBy(linkText = "Posts")
    public WebElement btnPosts;
    @FindBy(xpath = "//*[@id=\"root\"]/section/div[1]/form/textarea")
    public WebElement inputTextBox;
    @FindBy(xpath = "//*[@id=\"root\"]/section/div[1]/form/input")
    public WebElement btnSubmit;
    @FindBy(partialLinkText = "Post Created")
    public WebElement alert_created;
    @FindBy(partialLinkText = "Post Removed")
    public WebElement alert_deleted;
    @FindBy(xpath = "//*[@id=\"root\"]/section/div[2]/div[1]/div[2]/button[3]")
    public WebElement btnDeletePost;
    @FindBy(className = "posts")
    public WebElement postList;
}
