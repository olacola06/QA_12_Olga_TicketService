package manager;

import models.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class HelperLogin extends HelperBase{

    public HelperLogin(WebDriver wd) {
        super(wd);
    }

    public void toLogin(User user) {
        type((By.cssSelector("div input[aria-label='Email']")),user.getEmail());
        type((By.cssSelector("div [aria-label='Password']")),user.getPassword());
        new WebDriverWait(wd,10).until(ExpectedConditions.
                elementToBeClickable(By.xpath("//*[text()=' Login']"))).click();
        //wd.findElement(By.xpath("//*[text()=' Login']")).click();
    }
    public void loginSuccess(User user){
        String message = wd.findElement(By.xpath("//p[@class = 'fl-login f35 text-center']")).getText();
        Assert.assertTrue(message.contains("Login user:") & message.contains(user.getEmail()));
        wd.findElement(By.xpath("//*[text()=' Return to main']")).click();
    }
}
