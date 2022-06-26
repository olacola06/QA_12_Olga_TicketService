package tests;

import manager.MyDataProvider;
import models.User;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class Registration extends TestBase{

    Logger logger = LoggerFactory.getLogger(Registration.class);
    @Test
    public void registrationPosLB(){
        int i = (int) (System.currentTimeMillis()/1000)%3600;
        String email = "olam"+i+"@gmail.com";//Fail with first UpperCase letter

        User user = User.builder().name("Ola").surname("Mar").email(email).password("Bo12345$").
                confirmPassword("Bo12345$").phone("+123456789").build();
        logger.info("Test starts with details:-->"+user.getName()+user.getSurname()+user.getEmail());
        app.regist().clickLogin();
        app.regist().clickRegister();
        app.regist().fillRegistrationForm(user);
        app.regist().agreeCheckBox();
        app.regist().registerButton();

        Assert.assertTrue(app.regist().registrationSuccess(user));
        logger.info("Registration test passed Success");

    }
    @Test(dataProvider = "registrationValidData",dataProviderClass = MyDataProvider.class)
    public void registrationPosDP(User user){
        logger.info("Test starts with details:-->"+user.getName()+", "+user.getSurname()+", "+user.getEmail());
        app.regist().clickLogin();
        app.regist().clickRegister();
        app.regist().fillRegistrationForm(user);
        app.regist().agreeCheckBox();
        app.regist().registerButton();

        Assert.assertTrue(app.regist().registrationSuccess(user));
        logger.info("Registration test passed Success");

    }
    @AfterMethod
    public void postCondition(){
        app.regist().clickReturn();
    }
}
