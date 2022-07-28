package manager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HelperEvent extends HelperBase{
    public HelperEvent(WebDriver wd) {
        super(wd);
    }

    public void chooseEvent(int rowNum, int seatNum){
        wd.findElement(By.xpath("//*[text()='Coldplay Tour 2019']")).click();
        wd.findElement(By.xpath("//*[text()='BUY TICKETS']")).click();
        chooseRowAndSeat(rowNum,seatNum);

    }

    private void chooseRowAndSeat(int rowNum, int seatNum) {
        wd.findElement(By.cssSelector
                ("div[class='hall1 col-12 w-100-wv'] p:nth-child("+rowNum+") span:nth-child("+seatNum+")")).click();
        wd.findElement(By.xpath("//*[text()=' TO THE CART']")).click();
        markPolicy();
        wd.findElement(By.xpath("//*[text()=' PAY']")).click();
    }

    private void markPolicy() {
        wd.findElement(By.cssSelector("label[class='label-for-checkbox pos-cart-agreement']"));
        
    }

    public void clickReturnToMain() {
        wd.findElement(By.xpath("//*[text()=' Return to main']")).click();
    }
}
