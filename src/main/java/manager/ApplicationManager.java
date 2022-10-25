package manager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {
   EventFiringWebDriver wd;
    String browser;
    public ApplicationManager(String browser){
        this.browser = browser;
    }
    Logger logger = LoggerFactory.getLogger(ApplicationManager.class);
    HelperRegist regist;
    HelperLogin login;
    HelperEvent event;

    public void start(){
        if(browser.equals(BrowserType.CHROME)){
            ChromeOptions options =new ChromeOptions();
            options.addArguments("--lang=en");
            wd = new EventFiringWebDriver(new ChromeDriver(options));
        }
        else if (browser.equals(BrowserType.FIREFOX)){
            wd = new EventFiringWebDriver(new FirefoxDriver());
        }
        else if(browser.equals(BrowserType.EDGE)){
            wd = new EventFiringWebDriver(new EdgeDriver());
        }
        wd.manage().window().maximize();
        wd.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        wd.navigate().to("https://ticket-service-69443.firebaseapp.com/");
        wd.register(new MyListener());

        regist = new HelperRegist(wd);
        login = new HelperLogin(wd);
        event = new HelperEvent(wd);

    }

    public HelperRegist regist() {
        return regist;
    }

    public HelperLogin login() {
        return login;
    }
    public HelperEvent event(){
        return event;
    }

    public void stop(){
        logger.info("All tests passed");
        wd.quit();
    }
}
