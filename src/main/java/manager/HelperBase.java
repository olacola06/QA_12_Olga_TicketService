package manager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HelperBase {
    WebDriver wd;
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
        click(By.xpath("//*[text()=' Return']"));
    }

    public void refresh() {
        wd.navigate().refresh();
    }

}
