package manager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HelperEvent extends HelperBase{
    public HelperEvent(WebDriver wd) {
        super(wd);
    }

    public void chooseEvent(){
        wd.findElement(By.xpath("//*[text()='Coldplay Tour 2019']")).click();
        wd.findElement(By.xpath("//*[text()='BUY TICKETS']")).click();


    }

    public void clickReturnToMain() {
        wd.findElement(By.xpath("//*[text()=' Return to main']")).click();
    }
}
