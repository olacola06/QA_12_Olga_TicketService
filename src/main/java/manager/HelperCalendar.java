package manager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.Format;
import java.time.LocalDate;

public class HelperCalendar extends HelperBase{
    public HelperCalendar(WebDriver driver){
        super(driver);

    }
    public void selectEventDate(String today, String date) { //("30/10/2022","10/02/2023");
        new WebDriverWait(wd,30).until(ExpectedConditions
                .visibilityOf(wd.findElement(By.xpath("//a[.='EVENTS']"))));
        String[]startDate = date.split("/");
        String[]todayDate = today.split("/");
        chooseMonth(Integer.parseInt(startDate[1]),Integer.parseInt(todayDate[1]));
        chooseDay(Integer.parseInt(startDate[0]));

    }

    private void chooseMonth(int startMonth, int todayMonth) {
        if(startMonth!=todayMonth){
            int amountOfClicks = (startMonth-todayMonth)+12;
            for(;amountOfClicks>0;amountOfClicks--){
                click(By.cssSelector("span[aria-label='Next Month']"));
            }
        }
    }
    private void chooseDay(int startDay){
        String locator = String.format("//div[.='%s']",startDay);
        click(By.xpath(locator));
    }
    public boolean getAvailableEvents(){
        Boolean bool = wd.findElement(By.cssSelector("div[class='f35 note']")).isDisplayed();
        if(bool) {
            String message = wd.findElement(By.cssSelector("div[class='f35 note']")).getText();
            System.out.println(message.toString());
        }
        return bool;
    }

    public void selectEventDateLD(String today, String date) {
        //LocalDate eventDate = LocalDate.of(date);
        LocalDate todayDate = LocalDate.now();

    }
}
