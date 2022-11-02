package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class CalendarTests extends TestBase{

    @Test
    public void selectEventsDates(){
        app.calendar().selectEventDate("01/11/2022","10/05/2023");

        Assert.assertTrue(app.calendar().getAvailableEvents());
    }
    @Test
    public void selectEventLD(){
        app.calendar().selectEventDateLD("28/02/2023");

        Assert.assertTrue(app.calendar().getAvailableEventsLD("28/02/2023"));
    }
}
