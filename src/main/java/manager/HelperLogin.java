package manager;

import models.User;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class HelperLogin extends HelperBase {

    public HelperLogin(WebDriver wd) {
        super(wd);
    }

    public void toLogin(User user) {
        type((By.cssSelector("div input[aria-label='Email']")), user.getEmail());
        type((By.cssSelector("div [aria-label='Password']")), user.getPassword());
        new WebDriverWait(wd, 10).until(ExpectedConditions.
                elementToBeClickable(By.xpath("//*[text()=' Login']"))).click();
        //wd.findElement(By.xpath("//*[text()=' Login']")).click();
    }

    public void loginSuccess(User user) {
        String message = wd.findElement(By.xpath("//p[@class = 'fl-login f35 text-center']")).getText();
        Assert.assertTrue(message.contains("Login user:") & message.contains(user.getEmail()));
        wd.findElement(By.xpath("//*[text()=' Return to main']")).click();
    }

    public boolean loginFailed() {
        try {
            new WebDriverWait(wd, 20).until(ExpectedConditions.visibilityOf(
                    wd.findElement(By.xpath("//p[@class='fl-login f35 text-center']"))));
            String message = wd.findElement(By.xpath("//p[@class='fl-login f35 text-center']")).getText();
                System.out.println(message.toString());
                logger.info("Login failed because of: "+wd.findElement
                        (By.xpath("//span[@class='red-text f18']")).getText().toString());
                return true;
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return false;
    }

    public String loginFailedWrongData() {
        String message = wd.findElement(By.cssSelector("div input[aria-label='Email']"))
                .getAttribute("validationMessage");
        System.out.println(message.toString());
        return message;

    }
    public void logout() {
        click(By.cssSelector("div[class='form-btn mb-4 px-5']"));
        new WebDriverWait(wd, 20).until(ExpectedConditions.visibilityOf(
                wd.findElement(By.cssSelector("div input[aria-label='Email']"))));
    }

    public boolean loginFailedPass() {
        Boolean bool = wd.findElement(By.xpath("//p[@class='fl-login f35 text-center']")).isDisplayed();
        if(bool){
            String message = wd.findElement(By.xpath("//p[@class='fl-login f35 text-center']")).getText();
            System.out.println(message.toString());
            logger.info("Login failed because of: "+wd.findElement
                    (By.xpath("//span[@class='red-text f18']")).getText().toString());
        }
        return bool;
    }

    public boolean loginFailedWrongDataPass() {
        WebElement passwordField = wd.findElement(By.cssSelector("div [aria-label='Password']"));
        JavascriptExecutor js = (JavascriptExecutor)wd;
        String validationMessage = (String) js.executeScript("return arguments[0].validationMessage",passwordField);
        System.out.println(validationMessage.toString());
        logger.info("Test failed, reason is: "+validationMessage.toString());
        return validationMessage.contains("Please match");
    }
}
