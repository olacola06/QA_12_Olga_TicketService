package tests;

import models.User;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Login extends TestBase {

    @BeforeClass
    public void preCondition(){
        app.login().clickLogin();
    }

    @Test
    public void loginSuccess(){
        User user = User.builder().name("Ola").surname("Mar").email("olamm@gmail.com").password("Bo12345$").
                confirmPassword("Bo12345$").phone("+123456789").build();
        app.login().toLogin(user);
    }
}
