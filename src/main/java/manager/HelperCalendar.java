package manager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.Format;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class HelperCalendar extends HelperBase{
    public HelperCalendar(WebDriver driver){
        super(driver);

    }
    public void selectEventDate(String today, String date) { //("30/10/2022","10/02/2023");
        new WebDriverWait(wd,30).until(ExpectedConditions
                .visibilityOf(wd.findElement(By.xpath("//a[.='EVENTS']"))));
        String[]startDate = date.split("/");
        String[]todayDate = today.split("/");
        int startYear = Integer.parseInt(startDate[2]);
        int todayYear = Integer.parseInt(todayDate[2]);
        int startMonth = Integer.parseInt(startDate[1]);
        int todayMonth = Integer.parseInt(todayDate[1]);
        chooseMonth(startYear,todayYear,startMonth,todayMonth);
        chooseDay(Integer.parseInt(startDate[0]));

    }

    private void chooseMonth(int startYear, int todayYear,int startMonth, int todayMonth) {
        if (startYear - todayYear != 0) {
            int amountOfClicks = (startMonth - todayMonth) + 12;
            for (; amountOfClicks > 0; amountOfClicks--) {
                click(By.cssSelector("span[aria-label='Next Month']"));
            }
        } else {
            int amountOfClicks = startMonth - todayMonth;
            for (; amountOfClicks > 0; amountOfClicks--) {
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
    public boolean getAvailableEventsLD(String dateOfEvent){
        String[]eventData = dateOfEvent.split("/");
        String yearVerify = wd.findElement(By.xpath("//div[contains(text(),'"+eventData[2]+"')]")).getText();
        String dayVerify = wd.findElement(By.xpath("//div[.='"+eventData[0]+"']")).getText();
        return yearVerify.contains(eventData[2])&&dayVerify.contains(eventData[0]);
    }

    public void selectEventDateLD(String date) {
        LocalDate eventDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        LocalDate todayDate = LocalDate.now();

        int diff = eventDate.getYear()-todayDate.getYear()==0? eventDate.getMonthValue()-todayDate.getMonthValue():
                       12-todayDate.getMonthValue()+eventDate.getMonthValue();
        System.out.println("Total clicks to do->>"+diff);
        for(;diff>0;diff--){
            click(By.cssSelector("span[aria-label='Next Month']"));
        }
        String locator = String.format("//div[.='%s']",eventDate.getDayOfMonth());
        click(By.xpath(locator));

    }
}
