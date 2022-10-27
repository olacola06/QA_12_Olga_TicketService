package tests;

import models.User;
import org.testng.Assert;
import org.testng.annotations.*;

public class Login extends TestBase {

    @BeforeClass
    public void preCondition(){
        app.login().clickLogin();
    }
    User user = User.builder().name("Ola").surname("Mar").email("olamm@gmail.com").password("Bo12345$").
            confirmPassword("Bo12345$").phone("+123456789").build();

    @Test
    public void loginSuccess(){
        app.login().toLogin(user);
        app.login().loginSuccess(user);
        app.login().clickLogin();
        app.login().logout();
    }
    @Test
    public void loginWrongEmail(){
        user.setEmail("olammmm@gmail.com");
        app.login().toLogin(user);

        Assert.assertTrue(app.login().loginFailed());
        app.login().clickLogin();
        app.login().logout();
        app.login().pause(3000);
    }
    @Test
    public void loginWrongFormat(){
        user.setEmail("olammgmail.com");
        app.login().toLogin(user);

        Assert.assertTrue(app.login().loginFailedWrongData().contains("Please include"));
        app.login().pause(3000);
    }
    @Test
    public void loginWrongPassword(){
        user.setPassword("Bo12345@");
        app.login().toLogin(user);

        Assert.assertTrue(app.login().loginFailedPass());
        app.login().clickLogin();
        app.login().logout();
        app.login().pause(3000);
    }
    @Test
    public void loginWrongDataPass(){
        user.setPassword("bo12345");
        app.login().toLogin(user);

        Assert.assertTrue(app.login().loginFailedWrongDataPass());
        app.login().pause(3000);
    }
}
