package tests;

import models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class EventTests extends TestBase{

    @BeforeClass
    public void preCondition(){
        app.login().clickLogin();
        User user = User.builder().email("olamm@gmail.com").password("Bo12345$").build();
        app.login().toLogin(user);
        app.event().clickReturnToMain();
    }

    @Test
    public void chooseEventPos(){
        int rowNum = 5, seatNum =12;
        app.event().chooseEvent(rowNum, seatNum);

        Assert.assertTrue(app.event().eventSuccess());

    }
}
