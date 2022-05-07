package manager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {

    WebDriver wd = new ChromeDriver();
    HelperRegist regist;
    HelperLogin login;

    public void start(){
        wd.manage().window().maximize();
        wd.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        wd.navigate().to("https://ticket-service-69443.firebaseapp.com/");
        regist = new HelperRegist(wd);
        login = new HelperLogin(wd);

    }

    public HelperRegist regist() {
        return regist;
    }

    public HelperLogin login() {
        return login;
    }

    public void stop(){
        wd.quit();
    }

}
