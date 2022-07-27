package manager;

import models.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HelperLogin extends HelperBase{

    public HelperLogin(WebDriver wd) {
        super(wd);
    }

    public void toLogin(User user) {
        type((By.cssSelector("div aria-label='Email'")),user.getEmail());
        type((By.cssSelector("div aria-label='Password'")),user.getPassword());
        wd.findElement(By.xpath("//*[text()=' Login'])")).click();
    }
}
