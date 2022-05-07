package tests;

import models.User;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class Registration extends TestBase{

    @Test
    public void registrationPos(){
        int i = (int) (System.currentTimeMillis()/1000)%3600;
        String email = "olam"+i+"@gmail.com";//Fail with first UpperCase letter

        User user = User.builder().name("Ola").surname("Mar").email(email).password("Bo12345$").
                confirmPassword("Bo12345$").phone("+123456789").build();
        System.out.println("Email: "+email);
        app.regist().clickLogin();
        app.regist().clickRegister();
        app.regist().fillRegistrationForm(user);
        app.regist().agreeCheckBox();
        app.regist().registerButton();

    }
}
