package manager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelperBase {
    WebDriver wd;
    Logger logger = LoggerFactory.getLogger(HelperBase.class);
    public HelperBase(WebDriver wd){
        this.wd = wd;
    }

    public void click(By locator){
        wd.findElement(locator).click();
    }
    public void type(By locator, String text){
        WebElement element = wd.findElement(locator);
        element.click();
        element.clear();
        element.sendKeys(text);
    }
    public void pause(int milles) {
        try{
            Thread.sleep(milles);
        }catch (InterruptedException e){
                throw new RuntimeException(e);
            }
    }
    public void clickLogin(){
        click(By.cssSelector("span [href='/login']"));

    }
    public void clickReturn(){
        new WebDriverWait(wd,10).until(ExpectedConditions
                .elementToBeClickable(wd.findElement(By.xpath("//*[text()=' Return']")))).click();
        //click(By.xpath("//*[text()=' Return']"));
    }

    public void refresh() {
        //wd.navigate().refresh();
        wd.get("https://ticket-service-69443.firebaseapp.com/");
    }

}
