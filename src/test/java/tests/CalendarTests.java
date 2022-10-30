package tests;

import org.testng.annotations.Test;

public class CalendarTests extends TestBase{

    @Test
    public void selectEventsDates(){
        app.calendar().selectFirstDate("30/10/2022","10/02/2023");
    }
}
