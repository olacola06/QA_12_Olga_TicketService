package manager;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import javax.swing.*;

public class HelperEvent extends HelperBase {
    public HelperEvent(WebDriver wd) {
        super(wd);
    }

    public void chooseEvent(int rowNum, int seatNum) {
        wd.findElement(By.xpath("//*[text()='Coldplay Tour 2019']")).click();
        wd.findElement(By.xpath("//*[text()='BUY TICKETS']")).click();
        chooseRowAndSeat(rowNum, seatNum);
        //readPolicy();
        markPolicy();
        wd.findElement(By.xpath("//*[text()=' PAY']")).click();
        wd.findElement(By.cssSelector("i[class='fa fa-cc-paypal ml-2']")).click();
        wd.findElement(By.xpath("//*[text()=' PAY']")).click();
    }

    private void chooseRowAndSeat(int rowNum, int seatNum) {
        if (rowNum <= 0 || rowNum > 9 || seatNum <= 0 || seatNum > 13) {
            System.out.println("Wrong data entered, please reenter correct data");
        }

        switch (rowNum) {
            case 1:
                rowNum = 1;
                break;
            case 2:
                rowNum = 3;
                break;
            case 3:
                rowNum = 5;
                break;
            case 4:
                rowNum = 7;
                break;
            case 5:
                rowNum = 9;
                break;
            case 6:
                rowNum = 11;
                break;
            case 7:
                rowNum = 13;
                break;
            case 8:
                rowNum = 15;
                break;
            case 9:
                rowNum = 17;
                break;
        }

        wd.findElement(By.cssSelector
                ("div[class='hall1 col-12 w-100-wv'] p:nth-child(" + rowNum + ") span:nth-child(" + seatNum + ")")).click();
        wd.findElement(By.xpath("//*[text()=' TO THE CART']")).click();
    }

    private void readPolicy() {
        wd.findElement(By.cssSelector("label[class='label-for-checkbox pos-cart-agreement']")).click();
        wd.findElement(By.xpath("//*[text()=' Return']")).click();
    }

    private void markPolicy() {
        WebElement checkBox = wd.findElement(By.cssSelector("label[class='label-for-checkbox pos-cart-agreement']"));
        Rectangle rect = checkBox.getRect();
        int y = rect.getHeight() / 2;
        int xTo = (int) (rect.getWidth() * 0.1);

        Actions action = new Actions(wd);
        action.moveToElement(checkBox).moveByOffset(xTo, y).click().release().perform();

    }

    public void clickReturnToMain() {
        wd.findElement(By.xpath("//*[text()=' Return to main']")).click();
    }

    public boolean eventSuccess() {
        String message = wd.findElement(By.xpath("//*[text()='SUCCESS']")).getText();
        return message.contains("SUCCESS");
    }
}
