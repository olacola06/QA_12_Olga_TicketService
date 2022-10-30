package manager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.Format;

public class HelperCalendar extends HelperBase{
    public HelperCalendar(WebDriver driver){
        super(driver);

    }

    public void selectFirstDate(String today, String date) { //("30/10/2022","10/02/2023");
        new WebDriverWait(wd,30).until(ExpectedConditions
                .visibilityOf(wd.findElement(By.xpath("//a[.='EVENTS']"))));
        String[]startDate = date.split("/");
        String[]todayDate = today.split("/");
        chooseMonth(Integer.parseInt(startDate[1]),Integer.parseInt(todayDate[1]));

    }

    private void chooseMonth(int startMonth, int todayMonth) {
        if(startMonth!=todayMonth){
            int amountOfClicks = (startMonth-todayMonth)+12;
            for(;amountOfClicks>0;amountOfClicks--){
                click(By.cssSelector("span[aria-label='Next Month']"));
            }
        }
    }
}
